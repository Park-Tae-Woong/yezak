package yezak.api.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import yezak.api.security.redis.RedisDao;

import java.security.Key;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenProvider {

	private static final String AUTHORITIES_KEY = "roleId";
	private static final String BEARER_TYPE = "Bearer";
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 480 * 60 * 1000L;    // 1515분
	private static final long REFRESH_TOKEN_EXPIRE_TIME = 6* 60 * 60 * 1000L;   // 7일
	private final Key key;
	private final RedisDao redisDao;
	private static final String JWT_HEADER_PARAM_TYPE = "typ";

	public JwtTokenProvider(@Value("${jwt.secret}") String secret, RedisDao redisDao) {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.redisDao = redisDao;
	}

	private boolean isAccessTokenExpired(String accessToken) {
		Date expiration = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(accessToken)
				.getBody()
				.getExpiration();

		// 현재 시간
		Date now = new Date();

		// 엑세스 토큰의 만료 시간과 현재 시간을 비교하여 확인
		return expiration.before(now);
	}

	public TokenResponse generateToken(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		long now = (new Date()).getTime();

		Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

		String accessToken = Jwts.builder()
				.signWith(key, SignatureAlgorithm.HS512)
				.setHeaderParam(JWT_HEADER_PARAM_TYPE, "JWT")
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.setExpiration(accessTokenExpiresIn)
				.compact();

		if (isAccessTokenExpired(accessToken)) {
			String refreshToken = (String) redisDao.getValues(authentication.getName());

			if (refreshToken != null) {
				// 리프레시 토큰이 유효한 경우, 새로운 엑세스 토큰 발급
				accessToken = Jwts.builder()
						.signWith(key, SignatureAlgorithm.HS512)
						.setHeaderParam(JWT_HEADER_PARAM_TYPE, "JWT")
						.setSubject(authentication.getName())
						.claim(AUTHORITIES_KEY, authorities)
						.setExpiration(accessTokenExpiresIn)
						.compact();
				log.info("Access token has been regenerated.");

			}
		}
		String refreshToken = Jwts.builder()
				.setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();

		redisDao.setValues(authentication.getName(), refreshToken, Duration.ofMillis(REFRESH_TOKEN_EXPIRE_TIME));

		return TokenResponse.builder()
				.grantType(BEARER_TYPE)
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.refreshTokenExpirationTime(REFRESH_TOKEN_EXPIRE_TIME)
				.build();
	}

	// Token에 담겨있는 정보를 이용해 Authentication 객체를 리턴
	public Authentication getAuthentication(String accessToken) {

		Claims claims = parseClaims(accessToken);

		if (claims.get(AUTHORITIES_KEY) == null) {
			throw new RuntimeException("Invalid token");
		}

		Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());

		// UserDetails 객체를 만들어서 Authentication 리턴
		UserDetails principal = new User(claims.getSubject(), "", authorities);
		return new UsernamePasswordAuthenticationToken(principal, "", authorities);
	}

	//토큰 정보 추출
	public Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}

	public Long getExpiration(String accessToken) {
		// accessToken 남은 유효시간
		Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().getExpiration();
		// 현재 시간
		long now = new Date().getTime();
		return (expiration.getTime() - now);
	}

	// 토큰의 유효성 검증, 토큰을 파싱하여 exception들을 캐치
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("JWT 토큰이 잘못되었습니다.");
		}

		return false;
	}

}
package yezak.api.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	public static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_TYPE = "Bearer";
	private final JwtTokenProvider jwtTokenProvider;
	private final RedisTemplate<String, Object> redisTemplate;

	public JwtFilter(JwtTokenProvider jwtTokenProvider, RedisTemplate<String, Object> redisTemplate) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.redisTemplate = redisTemplate;
	}

	// JWT token 인증정보를 현재 실행중인 SecurityContext 저장
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		log.info("### 인증이나 권한이 필요한 주소 요청 ###");
		// 1. Request Header 에서 JWT 토큰 추출
		String token = resolveToken(httpServletRequest);
		String requestURI = httpServletRequest.getRequestURI();

		// 2. validateToken 으로 토큰 유효성 검사
		if(token != null && jwtTokenProvider.validateToken(token)) {
			String isLogout = (String)redisTemplate.opsForValue().get(token);
			if (ObjectUtils.isEmpty(isLogout)) {
				Authentication authentication = jwtTokenProvider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} else {
			log.info("유효한 JWT 토큰이 없습니다, URI : {}", requestURI);
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	// Request Header에서 token 정보를 가져옴
	private String resolveToken(HttpServletRequest httpServletRequest) {
		String bearerToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER);

		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
			return bearerToken.substring(7);
		}
		return null;
	}
}

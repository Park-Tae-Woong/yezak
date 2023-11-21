package yezak.api.security;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import yezak.api.domain.User;
import yezak.api.api.user.login.UserLoginMapper;
import org.springframework.http.HttpHeaders;
import yezak.api.security.jwt.JwtTokenProvider;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
@ControllerAdvice

public class Interceptor implements HandlerInterceptor {

    private UserLoginMapper userLoginMapper;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            // "Bearer " 이후의 문자열을 추출
            String jwtToken = token.substring(7);

            Claims claims = jwtTokenProvider.parseClaims(jwtToken);

            Long id = Long.valueOf(claims.getSubject());

            User user = userLoginMapper.findById(id);

            if (user == null) {
                throw new IllegalArgumentException("없는 유저입니다.");
            }

            Long userId = user.getId();
            Long roleId = user.getRoleId();

            request.setAttribute("userId", userId);
            request.setAttribute("roleId", roleId);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);

    }
}

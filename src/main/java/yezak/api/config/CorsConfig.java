package yezak.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")// 허용할 도메인을 지정
                .allowedMethods("*") // 허용할 HTTP 메서드 지정
                .allowedHeaders("*") // 모든 요청 헤더 허용
                .allowCredentials(true) // 인증 정보 포함 허용
                .maxAge(3600);
    }
}

package ei.algobaroapi.global.config;

import ei.algobaroapi.global.jwt.JwtAuthenticationFilter;
import ei.algobaroapi.global.jwt.JwtProvider;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@EnableMethodSecurity
public class WebSecurityConfig {

    private final JwtProvider jwtTokenProvider;

    @Value("${server.port}")
    private String serverPort;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:" + serverPort));  // 포트번호
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
                        corsConfigurationSource()))

                // csrf
                .csrf(AbstractHttpConfigurer::disable)

                // 기존 세션 로그인 방식 제외
                .sessionManagement(manage ->
                        manage.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // api 별 권한 처리
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/members/sign-up").permitAll()
                        .requestMatchers("/api/v1/members/sign-in").permitAll()
                        .requestMatchers("/api/v1/members/test").hasRole("USER")
                        .anyRequest().permitAll())

                // JWT 권한 필터 적용
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}

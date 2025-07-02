package me.yeseonghan.random.config;

import lombok.RequiredArgsConstructor;
import me.yeseonghan.random.config.jwt.TokenProvider;
import me.yeseonghan.random.service.UserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {
    private final UserDetailService userService;
    private final TokenProvider tokenProvider;

    @Value("${spring.security.remember-me.key}")
    private String rememberKey;

    //시큐리티 기능 비활성화(이미지나 html등 정적인 리소스에 설정함)
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers("/js/**","/css/**","/images/**","/favicon.ico","/common-html/**");
    }

    //특정 http요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                //인증, 인가 설정
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login","/signup","/user/**","/map","/api/**").permitAll()     // requestMatchers: /login,/signup,/user 요청에 대해 + permitAll: 누구나 접근 가능하다
                    .anyRequest().authenticated()     //anyRequest: 그 외의 요청에 대해 + authenticated: 인증-필요, 인가-불필요
                )

                //폼 기반 로그인 설정
                .formLogin(form -> form
                    .loginPage("/login")  // loginPage: 로그인 페이지는 여기다
                    .permitAll()
                    .defaultSuccessUrl("/index", true)   //defaultSuccessUrl: 로그인 성공하면 /articles로 간다
                )

                //로그아웃 설정
                .logout(logout -> logout
                    .logoutSuccessUrl("/login")   // logoutSuccessUrl: 로그아웃되면 /login으로 간다
                    .deleteCookies("JSESSIONID", "remember-me")   //로그아웃 시 쿠키 지움
                    .invalidateHttpSession(true)  //invalidateHttpSession: 로그아웃하면 세션 전체삭제여부가 true
                    .clearAuthentication(true)  //인증정보 제거
                )

                //유저 정보 유지 설정
                .rememberMe(remember -> remember
                    .rememberMeParameter("remember-me")
                    .tokenValiditySeconds(60*60*24)
                    .key(rememberKey)
                )

                .csrf(csrf -> csrf.disable())   //csrf 비활성화(원래는 활성화하는게 좋음)

                .build();
    }

    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService) throws Exception {

        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder);

        return auth.build();
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

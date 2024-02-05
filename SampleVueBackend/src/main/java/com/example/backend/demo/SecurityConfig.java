package com.example.backend.demo;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	// 그냥 접근 가능한 uri
    private String[] PERMIT_ALL = {
    	"/"
        , "/test/**"
		, "/css/**"
		, "/images/**"
		, "/js/**"
		, "/login/**"
		, "/board/get/**"
		, "/index.html"
		, "/favicon.ico"
    };
    
	// 사용자 접근 가능한 uri
    private String[] ONLY_USER = {
    	"/board/delete/**"
    	, "/board/post/**"
    	, "/board/put/**"
    	, "/board/put/**"
    };


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	//http.authorizeRequests()	URL 경로에 대한 인가 규칙을 설정합니다.
    	//.antMatchers().permitAll()	특정 URL 경로를 인증 없이 허용합니다.
    	//.anyRequest().authenticated()	모든 요청에 대해 인증을 요구합니다.
    	//.hasRole()	특정 역할을 가진 사용자만 접근을 허용합니다.
    	//.formLogin()	폼 기반 로그인을 활성화합니다.
    	//.loginPage()	로그인 페이지의 경로를 지정합니다.
    	//.defaultSuccessUrl()	로그인 성공 후 이동할 기본 URL을 설정합니다.
    	//.logout()	로그아웃을 처리하는 설정을 추가합니다.
    	//.logoutUrl()	로그아웃 URL을 지정합니다.
    	//.logoutSuccessUrl()	로그아웃 성공 후 이동할 URL을 설정합니다.
    	//.csrf()	CSRF(Cross-Site Request Forgery) 공격 방어 설정을 활성화합니다.
    	//.sessionManagement()	세션 관리를 설정합니다.
    	//.sessionCreationPolicy()	세션 생성 정책을 설정합니다.	
//    	http 
//    	.csrf(csrf -> csrf 
//    			  //.ignoringRequestMatchers(PERMIT_ALL)	// 특정 URL 경로에 대해서는 CSRF 검증을 무시하도록 설정
//    		      .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())	// 토큰이 저장될 곳
//    		      .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))		// CSRF 토큰을 요청 핸들러에 설정
//    	.cors((corsConfig) ->	// cors 설정
//    			corsConfig.configurationSource(corsConfigurationSource())
//    	)
//		.headers((headerConfig) ->
//				headerConfig.frameOptions(frameOptionsConfig ->		// x-frame-Options, 	iframe을 모든 도메인에서 허용하기 위해 disable
//						frameOptionsConfig.disable()
//				)
//				.xssProtection(xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))	// xss 방어
//				.contentSecurityPolicy(		// csp 정책 설정
//		                cps -> cps.policyDirectives("script-src 'self'")
//		        )
//		)
//		.authorizeHttpRequests((authorizeRequests) ->
//				authorizeRequests
//						.requestMatchers(PERMIT_ALL).permitAll()	// 모두 접근 허용
//						.requestMatchers(ONLY_USER).hasRole(UserRoleEnum.USER.name())	// 일반유저만 허용	
//						.anyRequest().authenticated()
//		)
//		.exceptionHandling((exceptionConfig) ->
//				exceptionConfig
//				.authenticationEntryPoint(customAuthenticationEntryPoint)	// 인증안된 사용자가 end point로 접근하려고 할때 예외를 핸들링
//				.accessDeniedHandler(customAccessDeniedHandler)	// 인증은 완료됐으나 요청에 대한 권한을 가지고 있지 않은 사용자가 엔드포인트에 접근 하려고할 때 발생한 예외를 핸들링
//		) 
//		.formLogin((formLogin) ->
//				formLogin
//						.loginPage("/login")		// 로그인 페이지
//						.usernameParameter("username")	// 로그인에 사용할 파라미터
//						.passwordParameter("password")	// 로그인에 사용할 파라미터 
//						.loginProcessingUrl("/login/post/user") // 로그인 처리할 url
//						.defaultSuccessUrl("/", true)   // 로그인 성공 후의 표시할 페이지
//		)
//		.logout((logoutConfig) ->
//				logoutConfig.logoutSuccessUrl("/") 		// 로그아웃 성공시 홈으로 redirect
//		)
//		.userDetailsService(loginService); 
    	
	    return http.build();
	}
    
    // cors 설정
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	// 일단 모든 요청 허용하도록 설정
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");	// 특정 패턴의 origin으로 부터 오는 것만 허용 설정
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE", "PATCH"));	// 특정 메서드만 허용 설정
        configuration.setAllowedHeaders(Arrays.asList("*"));	// 특정 헤더만 허용 설정
        configuration.setAllowCredentials(true);	// 서버가 응답할 때 json을 JS에서 처리할 수 있게 설정
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    
    // 암호화에 필요한 PasswordEncoder
    @Bean
    PasswordEncoder PasswordEncoder () {
    	//return new MessageDigestPasswordEncoder("SHA-256");
    	return new BCryptPasswordEncoder();
    	//return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
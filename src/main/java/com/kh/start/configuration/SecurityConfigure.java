package com.kh.start.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.kh.start.configuration.filter.JwtFilter;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfigure {
	private final JwtFilter filter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// return httpSecurity.formLogin().disable().build();
		/*
		return httpSecurity.formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {			
			@Override
			public void customize(FormLoginConfigurer<HttpSecurity> t) {
				t.disable();
			}
		}).build();
		*/
		/*
			# Cross Site Request Forgery
			: 사용자가 자신의 의지와는 무관하게
			공격자가 의도한 행위(수정,삭제,등록 등)를
			특정 웹사이트에 요청하게 하는 공격
			
			<img src="http://도메인/logout" />
			
			<form action="http://도메인/logout" action="post">
				<button></button>
			</form>
		*/
		return httpSecurity
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable)
			.cors(Customizer.withDefaults())
			.authorizeHttpRequests(requests -> {
				requests.requestMatchers(HttpMethod.POST, "/auth/login", "/auth/refresh", "/members", "/boards")
				.permitAll();
				// requests.requestMatchers("/admin/**").hasRole("ROLE_ADMIN");
				requests.requestMatchers(HttpMethod.GET, "/uploads/**", "/boards/**", "/comments/**")
				.permitAll();
				requests.requestMatchers(HttpMethod.PUT, "/members", "/boards/**")
				.authenticated();
				requests.requestMatchers(HttpMethod.DELETE, "/members", "/boards/**")
				.authenticated();
				requests.requestMatchers(HttpMethod.POST, "/boards", "/comments")
				.authenticated();
			})
			/*
				sessionManagement
				: 세션을 어떻게 관리할것인지 지정
				sessionCreationPolicy
				: 세션 사용 정책을 설정
			*/
			.sessionManagement(manager ->
				manager.sessionCreationPolicy(
					SessionCreationPolicy.STATELESS))
			.addFilterBefore(
				filter, UsernamePasswordAuthenticationFilter.class)
			.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
package com.library.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.library.project.config.auth.oauth2.PrincipalOauth2UserService;
import com.library.project.handler.CustomAuthFailureHandler;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PrincipalOauth2UserService principalOauth2UserService;
	private final CustomAuthFailureHandler customFailureHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //사이트간 위조 방지
		http.authorizeRequests()
			.antMatchers("/user/**") // URI 로그인 한 후 들어갈 수 있는 페이지경로 지정
			.authenticated() // 인증을 거치고 위 페이지로 들어가게 함.
			.antMatchers("/user/**") // 페이지
			.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // 위 페이지에 들어갈 수 있는 권한(사람)
			.antMatchers("/admin/**") //관리자만 들어갈 수 있는 페이지 설정
			.access("hasRole('ROLE_ADMIN')")
			.anyRequest() // 다른 모든 요청들은
			.permitAll() // 모두에게 권한을 주겠다(권한이 필요없음)
			.and()
			.formLogin()
			.loginPage("/auth/login") // 로그인페이지 요청(뷰)
			.loginProcessingUrl("/auth/login") // 로그인 post 요청(PrincipalDetailsService -> loadUserByUsername() 호출)
			.failureHandler(customFailureHandler) //로그인 실패시 
			.defaultSuccessUrl("/mainpage") //메인페이지 주소 넣어야함 / 원래는 열어논 페이지로 들어가지는데 그 경로를 설정
			.and() //이후 oauth2 관련
			.logout() 
			.logoutUrl("/logout") //로그아웃 a태그 버튼에 연결해줘야함
			.logoutSuccessUrl("/auth/login")
			.and()
			.oauth2Login()
			.loginPage("/auth/login")
			.userInfoEndpoint()
			.userService(principalOauth2UserService)
			.and()
			.defaultSuccessUrl("/mainpage");
			
	}
}

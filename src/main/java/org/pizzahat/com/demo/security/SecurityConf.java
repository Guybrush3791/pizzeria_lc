package org.pizzahat.com.demo.security;

import org.pizzahat.com.demo.serv.UserServ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
public class SecurityConf {

	@Bean
	public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeHttpRequests()	
				.requestMatchers("/*/user/", "/*/user/**").hasAuthority("USER")
				.requestMatchers("/*/admin/", "/*/admin/**").hasAuthority("ADMIN")
				.requestMatchers("/**").permitAll()
			.and().formLogin()
			.and().logout()
		;
		
		return http.build();
	}
	
	@Bean
	public SpringSecurityDialect securityDialect() {
	    return new SpringSecurityDialect();
	}
	
	@Bean
	public UserDetailsService getUserDetailService() {
		
		return new UserServ();
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider getAuthProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(getUserDetailService());
		provider.setPasswordEncoder(getPasswordEncoder());
		
		return provider;
	}
}

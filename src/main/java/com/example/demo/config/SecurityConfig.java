package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.component.JwtTokenFilter;

@Configuration
//@EnableWebSecurity(debug = true)
public class SecurityConfig {
	
	@Autowired
	JwtTokenFilter jwtTokenFilter;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security.authorizeHttpRequests().anyRequest().authenticated();
		security.csrf().disable();
		security.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		return security.build();
	}

}

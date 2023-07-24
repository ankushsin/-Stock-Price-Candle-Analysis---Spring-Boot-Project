package com.investoBull.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig {

	@Bean
    public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .authorizeRequests()
            .requestMatchers(new MvcRequestMatcher()).permitAll() // Assuming MvcRequestMatcher handles Spring MVC endpoints
            .anyRequest().authenticated() // Require authentication for any other requests
            .and()
            .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
            .formLogin()
            .and()
            .httpBasic();

        return http.build();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
	
	private static class MvcRequestMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            
            return false;
        }
    }

}

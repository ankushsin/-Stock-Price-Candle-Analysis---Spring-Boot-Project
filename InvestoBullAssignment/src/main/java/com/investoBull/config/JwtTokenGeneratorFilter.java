package com.investoBull.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

	/**
	 * this is main method responsible for generating and storing the JWT.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//		System.out.println("inside doFilter....");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication) {

//			System.out.println("authentication " + authentication);

			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());

			String jwt = Jwts.builder()
					                  .setIssuer("Invest_Bull")
					                  .setSubject("JWT Token")
					                  .claim("username", authentication.getName())
					                  .claim("role", getRole(authentication.getAuthorities()))
					                  .setIssuedAt(new Date())
					                  .setExpiration(new Date(new Date().getTime() + SecurityConstants.JWT_VALID_TILL)) // expiration time
					                  .signWith(key)
					                  .compact();
			
			// setting the "JWT" to header with name "Authorization"
			response.setHeader(SecurityConstants.JWT_HEADER, jwt);
		}
		
		// Passing it to next filter in the chain.
		filterChain.doFilter(request, response);
	}
	
	
	/**
	 * This is responsible for extracting Role's from GrantedAuthority and return List<String>,
	 * @param authorities
	 * @return List<String>
	 */
	private List<String> getRole(Collection<? extends GrantedAuthority> authorities) {

		return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	}

    
	/**
	 * This method make sure that, this filter will execute only for first when the user calls "users/signIn" end-point;
	 */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		return !request.getServletPath().equals("/customers/signIn");
	}
	

}


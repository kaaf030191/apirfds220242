package com.codideep.main.Interceptor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codideep.main.Business.BusinessPerson;
import com.codideep.main.Helper.JwtUtil;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private BusinessPerson businessPerson;

	@Override
	protected void doFilterInternal(
		@SuppressWarnings("null") HttpServletRequest request,
		@SuppressWarnings("null") HttpServletResponse response,
		@SuppressWarnings("null") FilterChain chain
	) throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");

		String idPerson = null;
		String jwt = null;

		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			idPerson = jwtUtil.extractId(jwt);
		}

		if(idPerson != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				if(businessPerson.getByIdPerson(idPerson) != null) {
					UserDetails userDetails = new User(idPerson, "{noop}password", Collections.emptyList());

					if(jwtUtil.isTokenValid(jwt, userDetails.getUsername())) {
						UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		chain.doFilter(request, response);
	}
}
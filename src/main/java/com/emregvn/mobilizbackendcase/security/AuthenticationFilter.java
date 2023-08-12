package com.emregvn.mobilizbackendcase.security;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.emregvn.mobilizbackendcase.entity.User;
import com.emregvn.mobilizbackendcase.service.UserService;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
	private final UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		User user = extractXUserHeaderFromRequest(request).orElseThrow();
		
		UserPrincipal principal = UserPrincipal.builder()
				.userId(user.getUserId())
				.name(user.getName())
				.surname(user.getSurname())
				.role(user.getRole())
				.company(user.getCompany())
				.authorizations(user.getAuthorizations())
				.authorities(List.of(new SimpleGrantedAuthority(user.getRole())))
				.build();
		
		UserPrincipalAuthenticationToken authenticationToken = new UserPrincipalAuthenticationToken(principal);
		
		//Giriş yapan kullanıcının bilgileri SecurityContextHolder nesnesi içerisinde saklanır.
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		
		filterChain.doFilter(request, response);
	}

	//Kullanıcının API'ye attığı istek içerisinden X-User headerını çekip içerisindeki bilgilerle bir User classı yaratan fonksiyon.
	private Optional<User> extractXUserHeaderFromRequest(HttpServletRequest request) {
		var header = request.getHeader("X-User");
		
		if(!StringUtils.hasText(header)) {
			return Optional.empty();
		}
		
		Gson gson = new Gson();
		User jsonObject = gson.fromJson(header, User.class);
		User user = userService.getById(jsonObject.getUserId()).orElseThrow();
		
		return Optional.of(user);
	}
	
}

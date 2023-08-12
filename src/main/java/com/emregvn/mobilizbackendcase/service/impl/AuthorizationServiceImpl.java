package com.emregvn.mobilizbackendcase.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.emregvn.mobilizbackendcase.entity.Authorization;
import com.emregvn.mobilizbackendcase.entity.Group;
import com.emregvn.mobilizbackendcase.entity.User;
import com.emregvn.mobilizbackendcase.model.requests.CreateAuthorizationRequest;
import com.emregvn.mobilizbackendcase.repository.AuthorizationRepository;
import com.emregvn.mobilizbackendcase.security.UserPrincipal;
import com.emregvn.mobilizbackendcase.service.AuthorizationService;
import com.emregvn.mobilizbackendcase.service.GroupService;
import com.emregvn.mobilizbackendcase.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
	private final AuthorizationRepository authorizationRepository;
	private final GroupService groupService;
	private final UserService userService;

	@Override
	public void create(CreateAuthorizationRequest createAuthorizationRequest) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Group group = groupService.getById(createAuthorizationRequest.getGroupId()).orElseThrow();
		User user = userService.getById(createAuthorizationRequest.getUserId()).orElseThrow();
		
		if(principal.getCompany().getCompanyId() != user.getCompany().getCompanyId()) {
			throw new RuntimeException("You are not able to do anything for this user");
		}
		
		Authorization authorization = Authorization.builder()
				.group(group)
				.user(user)
				.build();
		
		authorizationRepository.save(authorization);
	}
	
	@Override
	public void delete(int authorizationId) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authorization authorization = authorizationRepository.findById(authorizationId).orElseThrow();
		
		if(principal.getCompany().getCompanyId() != authorization.getUser().getCompany().getCompanyId()) {
			throw new RuntimeException("You are not able to do anything for this user");
		}
		
		authorization.getUser().getAuthorizations().remove(authorization);
		
		authorizationRepository.delete(authorization);
	}

}

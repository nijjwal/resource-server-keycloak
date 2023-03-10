package com.appsdeveloperblog.ws.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.ws.api.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	Environment env;
	
	@GetMapping("/status/check")
	public String status() {
		return "Working..." + env.getProperty("local.server.port");
	}
	
	//@Secured("ROLE_developer")
	@PreAuthorize("hasAuthority('ROLE_monkey') or #id == #jwt.subject")
	@DeleteMapping(path="/{id}")
	public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
		return "Deleted user with id" + id + " and JWT subject" + jwt.getSubject();
	}
	
	@PostAuthorize("returnObject.userId == #jwt.subject")
	@GetMapping(path="/{id}")
	public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
		return new UserRest("Nijjwal","Shrestha","47090bff-a983-46eb-bc80-d7d94623e944x");
	}

}

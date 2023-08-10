package com.lawencon.jobportaladmin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportaladmin.dto.login.LoginReqDto;
import com.lawencon.jobportaladmin.dto.login.LoginResDto;
import com.lawencon.jobportaladmin.service.UserService;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
//	@PostMapping
//	public ResponseEntity<LoginResDto> login(@Valid @RequestBody final LoginReqDto user) {
//		final Authentication auth = new UsernamePasswordAuthenticationToken(user.getUserEmail(),
//				user.getUserPassword());
//
//		authenticationManager.authenticate(auth);
//		final LoginResDto userDb = userService.login(user);

	
		
		
//		return new ResponseEntity<>(userDb, HttpStatus.OK);
//	}
	
	
}

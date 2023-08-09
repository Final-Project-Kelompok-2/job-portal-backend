package com.lawencon.auth.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.auth.dto.TokenReqDto;
import com.lawencon.auth.filter.AuthorizationFilter;
import com.lawencon.auth.service.JwtService;

@RestController
@RequestMapping("token")
public class TokenController {

	@Autowired
	private JwtService jwtService;
	private AuthorizationFilter authorizationFilter;
	
	@PostMapping
	public ResponseEntity<String> getToken(@RequestBody TokenReqDto tokenDto){
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		
		final Map<String, Object> claims = new HashMap<>();
		claims.put("exp", cal.getTime());
		claims.put("id",tokenDto.getId());
		final String response = jwtService.generateJwt(claims);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Boolean> validateToken(@RequestBody String token){
		
		final Boolean result = true;
		
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
}

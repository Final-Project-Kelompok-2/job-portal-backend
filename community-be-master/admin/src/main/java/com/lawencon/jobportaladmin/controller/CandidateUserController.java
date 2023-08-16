package com.lawencon.jobportaladmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.candidate.CandidateMasterInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidateuser.CandidateUserInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidateuser.CandidateUserResDto;
import com.lawencon.jobportaladmin.service.CandidateUserService;

@RestController
@RequestMapping("candidate-user")
public class CandidateUserController {

	@Autowired
	private CandidateUserService candidateUserService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertCandidateUser(@RequestBody CandidateUserInsertReqDto candidate){
		final InsertResDto response = candidateUserService.insertCandidateuser(candidate);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/register")
	public ResponseEntity<InsertResDto> insertCandidateUserFromAdmin(@RequestBody CandidateMasterInsertReqDto candidate){
		final InsertResDto response = candidateUserService.insertCandidateFromAdmin(candidate);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CandidateUserResDto>> getAll(){
		final List<CandidateUserResDto> response = candidateUserService.getAll();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}

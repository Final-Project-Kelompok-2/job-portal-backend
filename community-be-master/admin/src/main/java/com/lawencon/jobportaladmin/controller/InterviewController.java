package com.lawencon.jobportaladmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.interview.InterviewInsertReqDto;
import com.lawencon.jobportaladmin.service.InterviewService;

@RestController
@RequestMapping("interviews")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;

	@PostMapping
	private ResponseEntity<InsertResDto> insertInterview(@RequestBody InterviewInsertReqDto interviewData) {
		final InsertResDto response = interviewService.insertInterview(interviewData);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}

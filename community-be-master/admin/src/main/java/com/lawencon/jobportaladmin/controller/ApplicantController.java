package com.lawencon.jobportaladmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.applicant.ApplicantInsertReqDto;
import com.lawencon.jobportaladmin.service.ApplicantService;

@RestController
@RequestMapping("applicants")
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;

	@PostMapping
	public ResponseEntity<InsertResDto> insertApplicant(@RequestBody ApplicantInsertReqDto applicantData) {
		final InsertResDto response = applicantService.insertApplicant(applicantData);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}

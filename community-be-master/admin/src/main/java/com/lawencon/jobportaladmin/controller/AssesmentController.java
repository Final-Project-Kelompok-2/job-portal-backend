package com.lawencon.jobportaladmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.assesment.AssesmentInsertReqDto;
import com.lawencon.jobportaladmin.service.AssesmentService;

@RestController
@RequestMapping("assesments")
public class AssesmentController {

	@Autowired
	private AssesmentService assesmentService;
	
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertAssesment(@RequestBody AssesmentInsertReqDto assesmentData){
		final InsertResDto response = assesmentService.insertAssesment(assesmentData);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
		
	}
	
}

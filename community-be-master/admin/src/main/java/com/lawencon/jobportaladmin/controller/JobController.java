package com.lawencon.jobportaladmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.job.JobInsertReqDto;
import com.lawencon.jobportaladmin.dto.job.JobResDto;
import com.lawencon.jobportaladmin.service.JobService;

@RestController
@RequestMapping("jobs")
public class JobController {

	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping
	public ResponseEntity<List<JobResDto>> getAll(){
		final List<JobResDto> response = jobService.getAllJobs();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertJob(@RequestBody JobInsertReqDto job){
		InsertResDto  response = jobService.insertJob(job);	
		
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	
}

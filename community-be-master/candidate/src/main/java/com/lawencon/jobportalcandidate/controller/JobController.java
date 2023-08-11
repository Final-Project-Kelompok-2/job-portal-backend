package com.lawencon.jobportalcandidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.job.JobInsertReqDto;
import com.lawencon.jobportalcandidate.dto.job.JobResDto;
import com.lawencon.jobportalcandidate.service.JobService;

@RestController
@RequestMapping("jobs")
public class JobController {

	@Autowired
	private JobService jobService;
	
	@GetMapping
	public ResponseEntity<List<JobResDto>> getAll() {
		final List<JobResDto> data = jobService.getJobs();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("/company")
	public ResponseEntity<List<JobResDto>> getByCompany(@Param("code")String code) {
		final List<JobResDto> data = jobService.getJobsByCompany(code);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("/salary")
	public ResponseEntity<List<JobResDto>> getBySalary(@Param("salary")Float salary) {
		final List<JobResDto> data = jobService.getJobsBySalary(salary);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertJob(@RequestBody JobInsertReqDto job){
		final InsertResDto response = jobService.insertJob(job);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	
	
	
}

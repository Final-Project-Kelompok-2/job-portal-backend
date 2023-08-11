package com.lawencon.jobportalcandidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.savedjob.SavedJobInsertReqDto;
import com.lawencon.jobportalcandidate.dto.savedjob.SavedJobResDto;
import com.lawencon.jobportalcandidate.service.SavedJobService;

@RestController
@RequestMapping("savedjobs")
public class SavedJobsController {
	@Autowired
	private SavedJobService savedJobService;
	
	@GetMapping
	public ResponseEntity<List<SavedJobResDto>> getByCandidate(@Param("id") String id) {
		final List<SavedJobResDto> data = savedJobService.getSavedJobByCandidate(id);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody SavedJobInsertReqDto data) {
		final InsertResDto response = savedJobService.insertSavedJob(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteResDto> delete(@PathVariable("id") String id) {
		final DeleteResDto data = savedJobService.removeSavedJob(id);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	
}

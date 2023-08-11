package com.lawencon.jobportalcandidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidatetrainingexp.CandidateTrainingExpInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidatetrainingexp.CandidateTrainingExpUpdateReqDto;
import com.lawencon.jobportalcandidate.service.CandidateTrainingExpService;

@RestController
@RequestMapping("training-experiences")
public class CandidateTrainingExpController {

	@Autowired
	private CandidateTrainingExpService candidateTrainingExpService;
	
	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody CandidateTrainingExpInsertReqDto data) {
		final InsertResDto result = candidateTrainingExpService.insertCandidateTrainingExp(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> update(@RequestBody CandidateTrainingExpUpdateReqDto data) {
		final UpdateResDto result = candidateTrainingExpService.updateCandidateTrainingExp(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteResDto> delete(@PathVariable("id") String id) {
		final DeleteResDto data = candidateTrainingExpService.deleteCandidateTrainingExp(id);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}

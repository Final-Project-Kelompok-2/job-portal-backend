package com.lawencon.jobportaladmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportaladmin.dto.DeleteResDto;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.UpdateResDto;
import com.lawencon.jobportaladmin.dto.candidateaddress.CandidateAddressInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidateaddress.CandidateAddressResDto;
import com.lawencon.jobportaladmin.dto.candidateaddress.CandidateAddressUpdateReqDto;
import com.lawencon.jobportaladmin.service.CandidateAddressService;

@RestController
@RequestMapping("candidate-address")
public class CandidateAddressController {

	@Autowired
	private CandidateAddressService addressService;

	@GetMapping
	public ResponseEntity<List<CandidateAddressResDto>> getAddresses(@RequestParam("id") String id) {
		final List<CandidateAddressResDto> response = addressService.getAllByCandidate(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insert(@RequestBody CandidateAddressInsertReqDto data) {
		final InsertResDto response = addressService.insertCandidateAddress(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> update(@RequestBody CandidateAddressUpdateReqDto data){
		final UpdateResDto response = addressService.updateCandidateAdress(data);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteResDto> delete(@PathVariable("id") String id) {
		final DeleteResDto response = addressService.deleteCandidateAddress(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAddress/{code}")
	public ResponseEntity<DeleteResDto> deleteFromCandidate(@PathVariable("code") String code) {
		final DeleteResDto response = addressService.deleteCandidateAddressFromCandidate(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

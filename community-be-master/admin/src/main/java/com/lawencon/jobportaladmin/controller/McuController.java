package com.lawencon.jobportaladmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.mcu.McusInsertReqDto;
import com.lawencon.jobportaladmin.service.McuService;

@RestController
@RequestMapping("mcus")
public class McuController {

	@Autowired
	private McuService mcuService;
	
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertMcuFiles(@RequestBody McusInsertReqDto mcuFiles){
		final InsertResDto response = mcuService.insertMcuFiles(mcuFiles);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<>abc(){}
	
}

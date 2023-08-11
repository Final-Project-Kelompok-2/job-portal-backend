package com.lawencon.jobportaladmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportaladmin.dto.company.CompanyResDto;
import com.lawencon.jobportaladmin.service.CompanyService;

@RestController
@RequestMapping("companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping
	public ResponseEntity<List<CompanyResDto>> getAll() {
		final List<CompanyResDto> response = companyService.getAllCompany();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

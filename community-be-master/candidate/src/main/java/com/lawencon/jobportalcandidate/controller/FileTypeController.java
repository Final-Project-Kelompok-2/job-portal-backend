package com.lawencon.jobportalcandidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportalcandidate.dto.filetype.FileTypesResDto;
import com.lawencon.jobportalcandidate.service.FileTypeService;

@RestController
@RequestMapping("file-types")
public class FileTypeController {

	@Autowired
	private FileTypeService fileTypeService;
	
	@GetMapping
	public ResponseEntity<List<FileTypesResDto>> getFileTypes(){
		final List<FileTypesResDto> response = fileTypeService.getFileTypes();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}

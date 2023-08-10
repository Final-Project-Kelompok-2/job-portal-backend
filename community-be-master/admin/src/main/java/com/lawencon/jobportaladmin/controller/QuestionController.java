package com.lawencon.jobportaladmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.question.QuestionsInsertReqDto;
import com.lawencon.jobportaladmin.service.QuestionService;

@RestController
@RequestMapping("questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertQuestion(@RequestBody QuestionsInsertReqDto data) {
		final InsertResDto response = questionService.insertQuestion(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
}

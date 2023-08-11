package com.lawencon.jobportalcandidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.question.QuestionsInsertReqDto;
import com.lawencon.jobportalcandidate.service.QuestionService;


@RestController
@RequestMapping("questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping
	public ResponseEntity<InsertResDto> insertQuestions(@RequestBody QuestionsInsertReqDto newQuestions) {
		
		final InsertResDto response = questionService.insertQuestion(newQuestions);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}

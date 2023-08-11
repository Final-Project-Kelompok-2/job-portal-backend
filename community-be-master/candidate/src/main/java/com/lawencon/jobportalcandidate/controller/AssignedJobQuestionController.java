package com.lawencon.jobportalcandidate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportalcandidate.dto.assignedjobquestion.AssignedJobQuestionResDto;
import com.lawencon.jobportalcandidate.service.AssignedJobQuestionService;

@RestController
@RequestMapping("assigned-job")
public class AssignedJobQuestionController {

	@Autowired
	private AssignedJobQuestionService assignedJobQuestionService;
	
	@GetMapping
	public ResponseEntity<List<AssignedJobQuestionResDto>> getAssignedJobQuestion(@RequestParam("job") String id) {
		final List<AssignedJobQuestionResDto> response = assignedJobQuestionService.getAssignedJobQuestionByJob(null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

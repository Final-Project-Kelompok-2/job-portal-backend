package com.lawencon.jobportaladmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportaladmin.dto.UpdateResDto;
import com.lawencon.jobportaladmin.dto.review.ReviewUpdateScoreReqDto;
import com.lawencon.jobportaladmin.service.ReviewService;

@RestController
@RequestMapping("reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PatchMapping
	public ResponseEntity<UpdateResDto> updateReviewScore(@RequestBody ReviewUpdateScoreReqDto data){
		final UpdateResDto response = reviewService.updateReviewScore(data);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
}

package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.jobportalcandidate.dao.AssignedJobQuestionDao;
import com.lawencon.jobportalcandidate.dto.assignedjobquestion.AssignedJobQuestionResDto;
import com.lawencon.jobportalcandidate.model.AssignedJobQuestion;

public class AssignedJobQuestionService {
	@Autowired
	private AssignedJobQuestionDao assignedJobQuestionDao;
	
	public List<AssignedJobQuestionResDto> getAssignedJobQuestionByJob(String jobId){
		final List<AssignedJobQuestion> assignedJobQuestion = assignedJobQuestionDao.getByJob(jobId);
		final List<AssignedJobQuestionResDto> jobQuestionRes = new ArrayList<>();
		for(int i = 0 ; i < assignedJobQuestion.size() ; i++) {
			final AssignedJobQuestionResDto assignedQuestion = new AssignedJobQuestionResDto();
			assignedQuestion.setId(assignedJobQuestion.get(i).getId());
			assignedQuestion.setQuestionId(assignedJobQuestion.get(i).getId());
			assignedQuestion.setQuestionDetail(assignedJobQuestion.get(i).getQuestion().getQuestionDetail());
			jobQuestionRes.add(assignedQuestion);
		}
		
		
		return jobQuestionRes;
	}
	
}

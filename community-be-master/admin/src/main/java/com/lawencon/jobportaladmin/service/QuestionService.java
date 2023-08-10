package com.lawencon.jobportaladmin.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.AssignedJobQuestionDao;
import com.lawencon.jobportaladmin.dao.QuestionDao;
import com.lawencon.jobportaladmin.dao.QuestionOptionDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.question.QuestionInsertReqDto;import com.lawencon.jobportaladmin.dto.question.QuestionResDto;
import com.lawencon.jobportaladmin.dto.question.QuestionsInsertReqDto;
import com.lawencon.jobportaladmin.dto.questionoption.QuestionOptionInsertReqDto;
import com.lawencon.jobportaladmin.dto.questionoption.QuestionOptionResDto;
import com.lawencon.jobportaladmin.model.AssignedJobQuestion;
import com.lawencon.jobportaladmin.model.Question;
import com.lawencon.jobportaladmin.model.QuestionOption;
import com.lawencon.jobportaladmin.util.GenerateCode;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private QuestionOptionDao questionOptionDao;
	
	@Autowired
	private AssignedJobQuestionDao assignedJobQuestionDao;
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public InsertResDto insertQuestion(QuestionsInsertReqDto newQuestions) {
		final InsertResDto insertRes = new InsertResDto();
		
		try {
			em().getTransaction().begin();
			
			for(int i=0;i<newQuestions.getNewQuestions().size();i++) {
				final QuestionInsertReqDto question = newQuestions.getNewQuestions().get(i);
							
				Question newQuestion = new Question();
				newQuestion.setQuestionDetail(question.getQuestionDetail());
				newQuestion.setQuestionCode(GenerateCode.generateTicket(5));
				newQuestion.setCreatedBy("0");

				newQuestion = questionDao.save(newQuestion);
				
				for(int j=0;j<question.getOptions().size();j++) {
					final QuestionOptionInsertReqDto option = question.getOptions().get(j);
					
					QuestionOption newOption = new QuestionOption();
					newOption.setOptionLabel(option.getOptionLabel());
					newOption.setIsCorrect(option.getIsCorrect());
					newOption.setQuestion(newQuestion);
					newOption.setCreatedBy("0");
					
					newOption = questionOptionDao.save(newOption);
				}
			}
			
			insertRes.setMessage("Insert Question Success");
			em().getTransaction().commit();
			
			
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return insertRes;
	}
	
	public List<QuestionResDto> getByJob(String id){
		final List<QuestionResDto> questions = new ArrayList<>();
		
		final List<AssignedJobQuestion> assignedJobQuestions = assignedJobQuestionDao.getByJob(id);
		
		for(int i=0;i<assignedJobQuestions.size();i++) {
			final Question question = questionDao.getById(Question.class, assignedJobQuestions.get(i).getQuestion().getId());
			final QuestionResDto questionDto = new QuestionResDto();
			
			final List<QuestionOption> options = questionOptionDao.getByQuestion(question.getId());
			final List<QuestionOptionResDto> optionsDto = new ArrayList<>();
			
			for(int j=0;j<options.size();j++) {
				final QuestionOptionResDto optionDto = new QuestionOptionResDto();
				optionDto.setId(options.get(j).getId());
				optionDto.setOptionLabel(options.get(j).getOptionLabel());
				optionsDto.add(optionDto);
			}
			
			questionDto.setId(question.getId());
			questionDto.setQuestionDetail(question.getQuestionDetail());
			questionDto.setOptions(optionsDto);
			
			questions.add(questionDto);
		}
		
		return questions;
		
	}
	
}

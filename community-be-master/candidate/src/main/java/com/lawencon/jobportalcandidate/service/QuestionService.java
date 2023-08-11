package com.lawencon.jobportalcandidate.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.QuestionDao;
import com.lawencon.jobportalcandidate.dao.QuestionOptionDao;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.question.QuestionInsertReqDto;
import com.lawencon.jobportalcandidate.dto.question.QuestionsInsertReqDto;
import com.lawencon.jobportalcandidate.dto.questionoption.QuestionOptionInsertReqDto;
import com.lawencon.jobportalcandidate.model.Question;
import com.lawencon.jobportalcandidate.model.QuestionOption;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private QuestionOptionDao questionOptionDao;

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
				newQuestion.setQuestionCode(newQuestions.getNewQuestions().get(i).getQuestionCode());
				newQuestion = questionDao.save(newQuestion);
				
				for(int j=0;j<question.getOptions().size();j++) {
					final QuestionOptionInsertReqDto option = question.getOptions().get(j);
					QuestionOption newOption = new QuestionOption();
					newOption.setOptionLabel(option.getOptionLabel());
					newOption.setIsCorrect(option.getIsCorrect());
					newOption.setQuestion(newQuestion);
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
}

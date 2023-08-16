package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.ApplicantDao;
import com.lawencon.jobportalcandidate.dao.AssignedJobQuestionDao;
import com.lawencon.jobportalcandidate.dao.QuestionDao;
import com.lawencon.jobportalcandidate.dao.QuestionOptionDao;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.question.QuestionInsertReqDto;
import com.lawencon.jobportalcandidate.dto.question.QuestionResDto;
import com.lawencon.jobportalcandidate.dto.question.QuestionsInsertReqDto;
import com.lawencon.jobportalcandidate.dto.questionoption.QuestionOptionInsertReqDto;
import com.lawencon.jobportalcandidate.dto.questionoption.QuestionOptionResDto;
import com.lawencon.jobportalcandidate.model.Applicant;
import com.lawencon.jobportalcandidate.model.AssignedJobQuestion;
import com.lawencon.jobportalcandidate.model.Question;
import com.lawencon.jobportalcandidate.model.QuestionOption;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private QuestionOptionDao questionOptionDao;

	@Autowired
	private AssignedJobQuestionDao assignedJobQuestionDao;

	@Autowired
	private ApplicantDao applicantDao;

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	public InsertResDto insertQuestion(QuestionsInsertReqDto newQuestions) {
		final InsertResDto insertRes = new InsertResDto();

		try {
			em().getTransaction().begin();

			for (int i = 0; i < newQuestions.getNewQuestions().size(); i++) {
				final QuestionInsertReqDto question = newQuestions.getNewQuestions().get(i);

				Question newQuestion = new Question();
				newQuestion.setQuestionDetail(question.getQuestionDetail());
				newQuestion.setQuestionCode(newQuestions.getNewQuestions().get(i).getQuestionCode());
				newQuestion = questionDao.save(newQuestion);

				for (int j = 0; j < question.getOptions().size(); j++) {
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

	public List<QuestionResDto> getByApplicant(String applicantId) {
		System.out.println(applicantId);
		final Applicant applicant = applicantDao.getById(Applicant.class, applicantId);
		System.out.println(applicant.getId());
		System.out.println(applicant.getApplicantCode());
		final List<QuestionResDto> questionsDto = new ArrayList<>();
		System.out.println(applicantId);
		System.out.println(applicant.getJob().getId());
		final List<AssignedJobQuestion> jobQuestions = assignedJobQuestionDao.getByJob(applicant.getJob().getId());

		for (int i = 0; i < jobQuestions.size(); i++) {
			// Question
			final QuestionResDto questionDto = new QuestionResDto();
			final Question question = questionDao.getById(Question.class, jobQuestions.get(i).getQuestion().getId());
			questionDto.setId(question.getId());
			questionDto.setQuestionDetail(question.getQuestionDetail());

			// Options
			final List<QuestionOption> questionOptions = questionOptionDao.getByQuestion(question.getId());
			final List<QuestionOptionResDto> questionOptionsDto = new ArrayList<>();

			for (int j = 0; j < questionOptions.size(); j++) {
				final QuestionOptionResDto questionOptionDto = new QuestionOptionResDto();
				questionOptionDto.setId(questionOptions.get(j).getId());
				questionOptionDto.setOptionLabel(questionOptions.get(j).getOptionLabel());
				questionOptionsDto.add(questionOptionDto);
			}
			questionDto.setOptions(questionOptionsDto);
			questionsDto.add(questionDto);
		}

		return questionsDto;
	}
}

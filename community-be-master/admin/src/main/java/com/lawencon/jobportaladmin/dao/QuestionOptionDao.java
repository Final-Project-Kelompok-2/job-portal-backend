package com.lawencon.jobportaladmin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.QuestionOption;

@Repository
public class QuestionOptionDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public List<QuestionOption> getByQuestion(String id){
	final String sql = "SELECT tqo.id, tqo.option_label FROM t_question_option tqo "
				+ " WHERE tqo.question_id = :id ";

		final List<?> questionOptionObjs = em().createNativeQuery(sql)
				.setParameter("id", id)
				.getResultList();
		
		
		final List<QuestionOption> questionOptions= new ArrayList<>();
		
		if(questionOptionObjs.size()>0) {
			for(Object questionOptionObj : questionOptionObjs) {
				final Object[] questionOptionObjArr = (Object []) questionOptionObj;
				final QuestionOption option = new QuestionOption();
				option.setId(questionOptionObjArr[0].toString());
				option.setOptionLabel(questionOptionObjArr[1].toString());
				questionOptions.add(option);
			}
		}
		
		return questionOptions;
	}
	
}

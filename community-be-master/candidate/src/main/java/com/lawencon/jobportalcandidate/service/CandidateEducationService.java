package com.lawencon.jobportalcandidate.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateEducationDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidateeducation.CandidateEducationInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateeducation.CandidateEducationResDto;
import com.lawencon.jobportalcandidate.dto.candidateeducation.CandidateEducationUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateEducation;
import com.lawencon.jobportalcandidate.model.CandidateUser;

@Service
public class CandidateEducationService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private CandidateEducationDao candidateEducationDao;

	@Autowired
	private CandidateUserDao candidateUserDao;

	public List<CandidateEducationResDto> getEducationByCandidate(String id) {
		final List<CandidateEducationResDto> educationsDto = new ArrayList<>();
		final List<CandidateEducation> educations = candidateEducationDao.getEducationByCandidate(id);

		for (int i = 0; i < educations.size(); i++) {
			final CandidateEducationResDto education = new CandidateEducationResDto();
			education.setId(educations.get(i).getId());
			education.setDegreeName(educations.get(i).getDegreeName());
			education.setInstituitionName(educations.get(i).getInstitutionName());
			education.setMajors(educations.get(i).getMajors());
			education.setCgpa(educations.get(i).getCgpa());
			education.setStartYear(educations.get(i).getStartYear().toString());
			education.setEndYear(educations.get(i).getEndYear().toString());

			educationsDto.add(education);
		}

		return educationsDto;
	}

	public InsertResDto insertEducation(CandidateEducationInsertReqDto data) {
		final CandidateEducation education = new CandidateEducation();

		InsertResDto result = null;

		try {
			em().getTransaction().begin();

			education.setDegreeName(data.getDegreeName());
			education.setInstitutionName(data.getInstituitionName());
			education.setMajors(data.getMajors());
			education.setCgpa(data.getCgpa());
			education.setStartYear(LocalDate.parse(data.getStartYear().toString()));
			education.setEndYear(LocalDate.parse(data.getEndYear().toString()));

			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, "ID Principal");
			education.setCandidateUser(candidate);
			education.setCreatedBy("ID Principal");
			candidateEducationDao.save(education);

			result = new InsertResDto();
			result.setId(education.getId());
			result.setMessage("Education has been added");

			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}

		return result;
	}

	public UpdateResDto updateEducation(CandidateEducationUpdateReqDto data) {
		final CandidateEducation education = candidateEducationDao.getById(CandidateEducation.class, data.getId());

		UpdateResDto result = null;

		try {
			em().getTransaction().begin();
			education.setId(data.getId());
			education.setDegreeName(data.getDegreeName());
			education.setInstitutionName(data.getInstituitionName());
			education.setMajors(data.getMajors());
			education.setCgpa(data.getCgpa());
			education.setStartYear(LocalDate.parse(data.getStartYear()));
			education.setEndYear(LocalDate.parse(data.getEndYear()));

			final CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, "ID Principal");
			education.setCandidateUser(candidate);
			education.setUpdatedBy("ID Principal");
			candidateEducationDao.saveAndFlush(education);

			result = new UpdateResDto();
			result.setVersion(education.getVersion());
			result.setMessage("Education has been updated");

			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
		}

		return result;
	}

	public DeleteResDto deleteEducation(String id) {
		candidateEducationDao.deleteById(CandidateEducation.class, id);

		final DeleteResDto response = new DeleteResDto();
		response.setMessage("Education has been removed");

		return response;
	}
}

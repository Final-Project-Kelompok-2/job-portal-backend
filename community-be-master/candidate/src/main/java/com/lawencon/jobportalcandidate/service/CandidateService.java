package com.lawencon.jobportalcandidate.service;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateAddressDao;
import com.lawencon.jobportalcandidate.dao.CandidateDocumentsDao;
import com.lawencon.jobportalcandidate.dao.CandidateEducationDao;
import com.lawencon.jobportalcandidate.dao.CandidateFamilyDao;
import com.lawencon.jobportalcandidate.dao.CandidateLanguageDao;
import com.lawencon.jobportalcandidate.dao.CandidateProfileDao;
import com.lawencon.jobportalcandidate.dao.CandidateProjectExpDao;
import com.lawencon.jobportalcandidate.dao.CandidateReferencesDao;
import com.lawencon.jobportalcandidate.dao.CandidateSkillDao;
import com.lawencon.jobportalcandidate.dao.CandidateStatusDao;
import com.lawencon.jobportalcandidate.dao.CandidateTrainingExpDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dao.CandidateWorkExpDao;
import com.lawencon.jobportalcandidate.dao.FileDao;
import com.lawencon.jobportalcandidate.dao.MartialStatusDao;
import com.lawencon.jobportalcandidate.dao.PersonTypeDao;
import com.lawencon.jobportalcandidate.dao.ReligionDao;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidate.CandidateMasterInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateprofile.CandidateProfileUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateProfile;
import com.lawencon.jobportalcandidate.model.CandidateStatus;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.jobportalcandidate.model.File;
import com.lawencon.jobportalcandidate.model.MaritalStatus;
import com.lawencon.jobportalcandidate.model.PersonType;
import com.lawencon.jobportalcandidate.model.Religion;

@Service
public class CandidateService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private CandidateUserDao candidateUserDao;
	@Autowired
	private CandidateAddressDao candidateAddressDao;
	@Autowired
	private CandidateDocumentsDao candidateDocumentDao;
	@Autowired
	private CandidateEducationDao candidateEducationDao;
	@Autowired
	private CandidateFamilyDao candidateFamilyDao;
	@Autowired
	private CandidateLanguageDao candidateLanguageDao;
	@Autowired
	private CandidateProfileDao candidateProfileDao;
	@Autowired
	private CandidateProjectExpDao candidateProjectExpDao;
	@Autowired
	private CandidateReferencesDao candidateReferencesDao;
	@Autowired
	private CandidateSkillDao candidateSkillDao;
	@Autowired
	private CandidateStatusDao candidateStatusDao;
	@Autowired
	private CandidateTrainingExpDao candidateTrainingDao;
	@Autowired
	private CandidateWorkExpDao candidateWorkExpDao;
	@Autowired
	private MartialStatusDao maritalStatusDao;
	@Autowired
	private ReligionDao religionDao;
	@Autowired
	private PersonTypeDao personTypeDao;
	@Autowired
	private FileDao fileDao;

	public InsertResDto InsertCandidate(CandidateMasterInsertReqDto data) {
		InsertResDto result = null;
		try {
			em().getTransaction().begin();

			final CandidateProfile candidateProfile = new CandidateProfile();
			candidateProfile.setFullname(data.getCandidateProfile().getFullname());
			candidateProfile.setCreatedBy("ID Principal");
			candidateProfileDao.save(candidateProfile);

			final CandidateUser candidateuser = new CandidateUser();
			candidateuser.setUserEmail(data.getCandidateUser().getUserEmail());
			candidateuser.setUserPassword(data.getCandidateUser().getUserPassword());
			candidateuser.setCandidateProfile(candidateProfile);
			candidateuser.setCreatedBy("ID Principal");

			candidateUserDao.save(candidateuser);

			result = new InsertResDto();
			result.setId(candidateuser.getId());
			result.setMessage("Welcome new Member!");

			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return result;
	}

	public UpdateResDto updateCandidateProfile(CandidateProfileUpdateReqDto data) {
		UpdateResDto result = null;
		
		try {
			em().getTransaction().begin();

			final CandidateProfile profile = candidateProfileDao.getById(CandidateProfile.class, data.getId());
			profile.setId(data.getId());
			profile.setSalutation(data.getSalutation());
			profile.setFullname(data.getFullname());
			profile.setGender(data.getGender());
			profile.setExperience(data.getExperience());
			profile.setExpectedSalary(Float.valueOf(data.getExpectedSalary().toString()));
			profile.setPhoneNumber(data.getPhoneNumber());
			profile.setMobileNumber(data.getMobileNumber());
			profile.setNik(data.getNik());
			profile.setBirthDate(LocalDate.parse(data.getBirthDate().toString()));
			profile.setBirthPlace(data.getBirthPlace());
			final MaritalStatus status = maritalStatusDao.getById(MaritalStatus.class, data.getMaritalStatusId());
			profile.setMaritalStatus(status);
			
			final Religion religion = religionDao.getById(Religion.class, data.getReligionId());
			profile.setReligion(religion);
			
			final PersonType type = personTypeDao.getById(PersonType.class, data.getPersonTypeId());
			profile.setPersonType(type);
			
			if (data.getFile() != null) {
				final File file = new File();
				file.setFileName(data.getFile());
				file.setFileExtension(data.getFileExtension());
				file.setCreatedBy("ID Principal");
				fileDao.save(file);
				profile.setFile(file);
				fileDao.deleteById(File.class, data.getFileId());
			}
			 
			final CandidateStatus candidatestatus = candidateStatusDao.getById(CandidateStatus.class, data.getCandidateStatusId());
			profile.setCandidateStatus(candidatestatus);
			profile.setUpdatedBy("ID Principal");
			candidateProfileDao.saveAndFlush(profile);
			
			result = new UpdateResDto();
			result.setVersion(profile.getVersion());
			result.setMessage("Education has been updated");
		
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return result;
	}

}

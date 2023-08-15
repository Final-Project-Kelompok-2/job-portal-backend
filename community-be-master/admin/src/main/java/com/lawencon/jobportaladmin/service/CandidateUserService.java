package com.lawencon.jobportaladmin.service;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.constant.PersonTypes;
import com.lawencon.jobportaladmin.dao.CandidateProfileDao;
import com.lawencon.jobportaladmin.dao.CandidateStatusDao;
import com.lawencon.jobportaladmin.dao.CandidateUserDao;
import com.lawencon.jobportaladmin.dao.FileDao;
import com.lawencon.jobportaladmin.dao.MaritalStatusDao;
import com.lawencon.jobportaladmin.dao.PersonTypeDao;
import com.lawencon.jobportaladmin.dao.ReligionDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.candidateuser.CandidateUserInsertReqDto;
import com.lawencon.jobportaladmin.model.CandidateProfile;
import com.lawencon.jobportaladmin.model.CandidateStatus;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.jobportaladmin.model.File;
import com.lawencon.jobportaladmin.model.MaritalStatus;
import com.lawencon.jobportaladmin.model.PersonType;
import com.lawencon.jobportaladmin.model.Religion;
import com.lawencon.jobportaladmin.util.GenerateCode;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateUserService {

	@Autowired
	private CandidateUserDao candidateUserDao;
	
	@Autowired
	private CandidateProfileDao candidateProfileDao;
	
	@Autowired
	private MaritalStatusDao maritalStatusDao;
	
	@Autowired
	private PersonTypeDao personTypeDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private ReligionDao religionDao;
	
	@Autowired
	private CandidateStatusDao candidateStatusDao;
	
	@Autowired
	private PrincipalService<String> principalService;
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	public InsertResDto insertCandidateFromAdmin(CandidateUserInsertReqDto candidateData) {
		final InsertResDto insertResDto = new InsertResDto();
		try {
			em().getTransaction().begin();
			CandidateUser candidateUser = new CandidateUser();
			candidateUser.setUserEmail(candidateData.getUserEmail());
			
			CandidateProfile candidateProfile =new CandidateProfile();
			candidateProfile.setSalutation(candidateData.getProfile().getSalutation());
			candidateProfile.setFullname(candidateData.getProfile().getFullname());
			candidateProfile.setGender(candidateData.getProfile().getGender());
			candidateProfile.setExperience(candidateData.getProfile().getExperience());
			candidateProfile.setExpectedSalary(Float.valueOf(candidateData.getProfile().getExpectedSalary().toString()));
			candidateProfile.setPhoneNumber(candidateData.getProfile().getPhoneNumber());
			candidateProfile.setMobileNumber(candidateData.getProfile().getMobileNumber());
			candidateProfile.setNik(candidateData.getProfile().getNik());
			candidateProfile.setBirthDate(LocalDate.parse(candidateData.getProfile().getBirthDate()));
			
			final MaritalStatus status = maritalStatusDao.getById(MaritalStatus.class, candidateData.getProfile().getMaritalStatusId());
			candidateProfile.setMaritalStatus(status);

			final Religion religion = religionDao.getById(Religion.class, candidateData.getProfile().getReligionId());
			candidateProfile.setReligion(religion);

			final PersonType type = personTypeDao.getById(PersonType.class, candidateData.getProfile().getPersonTypeId());
			candidateProfile.setPersonType(type);

			if (candidateData.getProfile().getFile() != null) {
				final File file = new File();
				file.setFileName(candidateData.getProfile().getFile());
				file.setFileExtension(candidateData.getProfile().getFileExtension());
				file.setCreatedBy(principalService.getAuthPrincipal());
				fileDao.save(file);
				candidateProfile.setFile(file);
			}

			final CandidateStatus candidatestatus = candidateStatusDao.getById(CandidateStatus.class,
					candidateData.getProfile().getCandidateStatusId());
			candidateProfile.setCandidateStatus(candidatestatus);
			candidateProfile.setUpdatedBy(principalService.getAuthPrincipal());
			candidateProfileDao.save(candidateProfile);
			candidateUser.setCandidateProfile(candidateProfile);
			candidateUserDao.save(candidateUser);
			
			insertResDto.setId(candidateUser.getId());
			insertResDto.setMessage("Candidate has been added!");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return insertResDto;
	}
	
	public InsertResDto insertCandidateuser(CandidateUserInsertReqDto candidateData) {
		final InsertResDto insertResDto = new InsertResDto();
		
		try {
			em().getTransaction().begin();
			
			CandidateUser candidateUser = new CandidateUser();
			candidateUser.setUserEmail(candidateData.getUserEmail());
			
			CandidateProfile candidateProfile =new CandidateProfile();
			candidateProfile.setFullname(candidateData.getProfile().getFullname());
			final PersonType personType = personTypeDao.getByCode(PersonTypes.CANDIDATE.typeCode);
			candidateProfile.setPersonType(personType);
			candidateProfile = candidateProfileDao.saveNoLogin(candidateProfile,()-> GenerateCode.generateCode());
			candidateUser.setCandidateProfile(candidateProfile);
			candidateUser = candidateUserDao.saveNoLogin(candidateUser,()-> GenerateCode.generateCode());
			
			insertResDto.setId(candidateUser.getId());
			insertResDto.setMessage("Candidate has been added!");
			
			em().getTransaction().commit();	
			
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return insertResDto;
	}
	
}

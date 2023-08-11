package com.lawencon.jobportalcandidate.service;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.base.ConnHandler;
import com.lawencon.config.JwtConfig;
import com.lawencon.jobportalcandidate.dao.CandidateProfileDao;
import com.lawencon.jobportalcandidate.dao.CandidateStatusDao;
import com.lawencon.jobportalcandidate.dao.CandidateTrainingExpDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dao.CandidateWorkExpDao;
import com.lawencon.jobportalcandidate.dao.FileDao;
import com.lawencon.jobportalcandidate.dao.FileTypeDao;
import com.lawencon.jobportalcandidate.dao.MartialStatusDao;
import com.lawencon.jobportalcandidate.dao.PersonTypeDao;
import com.lawencon.jobportalcandidate.dao.ReligionDao;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidate.CandidateMasterInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidate.CandidateMasterResDto;
import com.lawencon.jobportalcandidate.dto.candidateprofile.CandidateProfileResDto;
import com.lawencon.jobportalcandidate.dto.candidateprofile.CandidateProfileUpdateReqDto;

import com.lawencon.jobportalcandidate.dto.candidateuser.CandidateUserResDto;

import com.lawencon.jobportalcandidate.login.LoginReqDto;
import com.lawencon.jobportalcandidate.login.LoginResDto;

import com.lawencon.jobportalcandidate.model.CandidateProfile;
import com.lawencon.jobportalcandidate.model.CandidateStatus;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.jobportalcandidate.model.File;
import com.lawencon.jobportalcandidate.model.MaritalStatus;
import com.lawencon.jobportalcandidate.model.PersonType;
import com.lawencon.jobportalcandidate.model.Religion;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateService implements UserDetailsService{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CandidateUserDao candidateUserDao;
	
	@Autowired
	private CandidateProfileDao candidateProfileDao;
	
	@Autowired
	private CandidateStatusDao candidateStatusDao;

	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private MartialStatusDao maritalStatusDao;
	
	@Autowired
	private ReligionDao religionDao;
	
	@Autowired
	private PersonTypeDao personTypeDao;
	
	@Autowired
	private PrincipalService<String> principalService;
	
	public CandidateMasterResDto getCandidateProfile(String id) {
		final CandidateMasterResDto data = new CandidateMasterResDto();
		
		final CandidateUserResDto candidateuserDto = new CandidateUserResDto();
		final CandidateUser candidateuser = candidateUserDao.getById(CandidateUser.class, id);
		candidateuserDto.setId(candidateuser.getId());
		candidateuserDto.setUserEmail(candidateuser.getUserEmail());
		candidateuserDto.setUserPassword(candidateuser.getUserPassword());
		
		final CandidateProfileResDto candidateprofileDto = new CandidateProfileResDto();
		final CandidateProfile candidateprofile = candidateProfileDao.getById(CandidateProfile.class, candidateuser.getCandidateProfile().getId());
		candidateprofileDto.setId(candidateprofile.getId());
		candidateprofileDto.setFullname(candidateprofile.getFullname());
		candidateprofileDto.setGender(candidateprofile.getGender());
		candidateprofileDto.setExperience(candidateprofile.getExperience());
		candidateprofileDto.setExpectedSalary(candidateprofile.getExpectedSalary().toString());
		candidateprofileDto.setPhoneNumber(candidateprofile.getPhoneNumber());
		candidateprofileDto.setMobileNumber(candidateprofile.getMobileNumber());
		candidateprofileDto.setNik(candidateprofile.getNik());
		candidateprofileDto.setBirthDate(candidateprofile.getBirthDate().toString());
		candidateprofileDto.setBirthPlace(candidateprofile.getBirthPlace());
		candidateprofileDto.setMaritalStatusId(candidateprofile.getMaritalStatus().getId());
		data.setCandidateProfile(candidateprofileDto);
		
		candidateuserDto.setProfileId(candidateprofile.getId());
		data.setCandidateUser(candidateuserDto);
		
		return data;
	}

	public InsertResDto InsertCandidate(CandidateMasterInsertReqDto data) {
		InsertResDto result = null;
		try {
			em().getTransaction().begin();

			final CandidateProfile candidateProfile = new CandidateProfile();
			candidateProfile.setFullname(data.getCandidateProfile().getFullname());
			candidateProfile.setCreatedBy(principalService.getAuthPrincipal());
			candidateProfileDao.save(candidateProfile);

			final CandidateUser candidateuser = new CandidateUser();
			candidateuser.setUserEmail(data.getCandidateUser().getUserEmail());
			candidateuser.setUserPassword(data.getCandidateUser().getUserPassword());
			candidateuser.setCandidateProfile(candidateProfile);
			candidateuser.setCreatedBy(principalService.getAuthPrincipal());

			candidateUserDao.save(candidateuser);

//			final String jobInsertCandidateAPI = "http://localhost:8080/candidate-user";
//			
//			final HttpHeaders headers = new HttpHeaders();
//		    headers.setContentType(MediaType.APPLICATION_JSON);
//		    headers.setBearerAuth(JwtConfig.get());
//			
//			final RequestEntity<JobInsertReqDto> jobInsert = RequestEntity.post(jobInsertCandidateAPI).headers(headers).body(jobDto);
//			
//			final ResponseEntity<InsertResDto> responseCandidate  =restTemplate.exchange(jobInsert,InsertResDto.class);
//			
		
//			if(responseCandidate.getStatusCode().equals(HttpStatus.CREATED)){
				
//				result = new InsertResDto();
//				result.setId(job.getId());
//				result.setMessage("New job vacancy added!");
//				em().getTransaction().commit();
//			}
//			else {
//				em().getTransaction().rollback();
//				
//				throw new RuntimeException("Insert Failed");
//				
//			}
			
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
				file.setCreatedBy(principalService.getAuthPrincipal());
				fileDao.save(file);
				profile.setFile(file);
				fileDao.deleteById(File.class, data.getFileId());
			}

			final CandidateStatus candidatestatus = candidateStatusDao.getById(CandidateStatus.class,
					data.getCandidateStatusId());
			profile.setCandidateStatus(candidatestatus);
			profile.setUpdatedBy(principalService.getAuthPrincipal());
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
	
	
	public LoginResDto login(LoginReqDto loginData) {
		final CandidateUser user = candidateUserDao.getByUsername(loginData.getUserEmail());
		final LoginResDto loginRes = new LoginResDto();
		
		if(!user.getIsActive()) {
			loginRes.setMessage("Akun anda nonaktif");
			return loginRes;
		}
		else {
			loginRes.setUserId(user.getId());
			loginRes.setFullName(user.getCandidateProfile().getFullname());
			loginRes.setProfileId(user.getCandidateProfile().getId());
			loginRes.setPhotoId(user.getCandidateProfile().getFile().getId());
		}
		
		return loginRes;
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final CandidateUser candidateUser = candidateUserDao.getByUsername(username);

		if (candidateUser != null) {
			return new org.springframework.security.core.userdetails.User(username, candidateUser.getUserPassword(),
					new ArrayList<>());
		}

		throw new UsernameNotFoundException("Email / Password salah");
	}

}

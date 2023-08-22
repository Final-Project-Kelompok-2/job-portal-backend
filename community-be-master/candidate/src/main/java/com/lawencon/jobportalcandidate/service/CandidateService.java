package com.lawencon.jobportalcandidate.service;

import java.math.BigDecimal;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.constant.PersonTypes;
import com.lawencon.jobportalcandidate.dao.CandidateProfileDao;
import com.lawencon.jobportalcandidate.dao.CandidateStatusDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dao.FileDao;
import com.lawencon.jobportalcandidate.dao.MartialStatusDao;
import com.lawencon.jobportalcandidate.dao.PersonTypeDao;
import com.lawencon.jobportalcandidate.dao.ReligionDao;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidate.CandidateMasterResDto;
import com.lawencon.jobportalcandidate.dto.candidateprofile.CandidateProfileResDto;
import com.lawencon.jobportalcandidate.dto.candidateuser.CandidateUserInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateuser.CandidateUserResDto;
import com.lawencon.jobportalcandidate.dto.candidateuser.CandidateUserUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidateuser.ChangePasswordReqDto;
import com.lawencon.jobportalcandidate.login.LoginReqDto;
import com.lawencon.jobportalcandidate.login.LoginResDto;
import com.lawencon.jobportalcandidate.model.CandidateProfile;
import com.lawencon.jobportalcandidate.model.CandidateStatus;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.jobportalcandidate.model.File;
import com.lawencon.jobportalcandidate.model.MaritalStatus;
import com.lawencon.jobportalcandidate.model.PersonType;
import com.lawencon.jobportalcandidate.model.Religion;
import com.lawencon.jobportalcandidate.util.GenerateCode;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateService implements UserDetailsService {

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
	private PasswordEncoder passwordEncoder;

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
		final CandidateProfile candidateprofile = candidateProfileDao.getById(CandidateProfile.class,
				candidateuser.getCandidateProfile().getId());
		candidateprofileDto.setId(candidateprofile.getId());
		candidateprofileDto.setSalutation(candidateprofile.getSalutation());
		candidateprofileDto.setFullname(candidateprofile.getFullname());
		candidateprofileDto.setGender(candidateprofile.getGender());
		candidateprofileDto.setExperience(candidateprofile.getExperience());

		if (candidateprofile.getExpectedSalary() != null) {
			candidateprofileDto.setExpectedSalary(candidateprofile.getExpectedSalary().toString());
		}

		candidateprofileDto.setPhoneNumber(candidateprofile.getPhoneNumber());
		candidateprofileDto.setMobileNumber(candidateprofile.getMobileNumber());
		candidateprofileDto.setNik(candidateprofile.getNik());
		if (candidateprofile.getBirthDate() != null) {
			candidateprofileDto.setBirthDate(candidateprofile.getBirthDate().toString());
		}

		candidateprofileDto.setBirthPlace(candidateprofile.getBirthPlace());
		if (candidateprofile.getMaritalStatus() != null) {
			candidateprofileDto.setMaritalStatusId(candidateprofile.getMaritalStatus().getId());
		}
		if (candidateprofile.getReligion() != null) {
			candidateprofileDto.setReligionId(candidateprofile.getReligion().getId());
		}
		if (candidateprofile.getPersonType() != null) {
			candidateprofileDto.setPersonTypeId(candidateprofile.getPersonType().getId());
		}
		if (candidateprofile.getFile() != null) {			
			candidateprofileDto.setFileId(candidateprofile.getFile().getId());
		}
		if (candidateprofile.getCandidateStatus() != null) {
			candidateprofileDto.setCandidateStatusId(candidateprofile.getCandidateStatus().getId());
		}

		data.setCandidateProfile(candidateprofileDto);

		candidateuserDto.setProfileId(candidateprofile.getId());
		data.setCandidateUser(candidateuserDto);
		data.setCandidateProfile(candidateprofileDto);

		return data;
	}

	public InsertResDto insertCandidate(CandidateUserInsertReqDto data) {
		final InsertResDto result = new InsertResDto();

		try {
			em().getTransaction().begin();

			final CandidateProfile candidateProfile = new CandidateProfile();
			candidateProfile.setFullname(data.getProfile().getFullname());
			final PersonType personType = personTypeDao.getByCode(PersonTypes.CANDIDATE.typeCode);
			candidateProfile.setPersonType(personType);

			final CandidateStatus candidateStatus = candidateStatusDao
					.getByCode(com.lawencon.jobportalcandidate.constant.CandidateStatus.ACTIVE.statusCode);
			candidateProfile.setCandidateStatus(candidateStatus);
			candidateProfileDao.saveNoLogin(candidateProfile, () -> GenerateCode.generateCode());

			CandidateUser candidateUser = new CandidateUser();
			candidateUser.setUserEmail(data.getUserEmail());

			final String encodedPassword = passwordEncoder.encode(data.getUserPassword());

			candidateUser.setUserPassword(encodedPassword);
			candidateUser.setCandidateProfile(candidateProfile);
			candidateUser = candidateUserDao.saveNoLogin(candidateUser, () -> GenerateCode.generateCode());

			final String jobInsertCandidateAPI = "http://localhost:8080/candidate-user";

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			final RequestEntity<CandidateUserInsertReqDto> candidateInsert = RequestEntity.post(jobInsertCandidateAPI)
					.headers(headers).body(data);

			final ResponseEntity<InsertResDto> responseAdmin = restTemplate.exchange(candidateInsert,
					InsertResDto.class);

			if (responseAdmin.getStatusCode().equals(HttpStatus.CREATED)) {
				result.setId(candidateUser.getId());
				result.setMessage("Welcome new Member!");
				em().getTransaction().commit();
			} else {
				em().getTransaction().rollback();
				throw new RuntimeException("Insert Failed");

			}

		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	public UpdateResDto updateCandidateProfile(CandidateUserUpdateReqDto data) {
		UpdateResDto result = null;

		try {
			em().getTransaction().begin();

			final CandidateUser user = candidateUserDao.getById(CandidateUser.class, data.getId());
			user.setId(data.getId());
			user.setUserEmail(data.getUserEmail());
			user.setUpdatedBy(principalService.getAuthPrincipal());

			final CandidateProfile profile = candidateProfileDao.getById(CandidateProfile.class,
					user.getCandidateProfile().getId());
			profile.setId(data.getProfile().getId());
			profile.setSalutation(data.getProfile().getSalutation());
			profile.setFullname(data.getProfile().getFullname());
			profile.setGender(data.getProfile().getGender());
			profile.setExperience(data.getProfile().getExperience());
			profile.setExpectedSalary(BigDecimal.valueOf(Long.valueOf(data.getProfile().getExpectedSalary())));
			profile.setPhoneNumber(data.getProfile().getPhoneNumber());
			profile.setMobileNumber(data.getProfile().getMobileNumber());
			profile.setNik(data.getProfile().getNik());
			profile.setBirthDate(LocalDate.parse(data.getProfile().getBirthDate().toString()));
			profile.setBirthPlace(data.getProfile().getBirthPlace());
			final MaritalStatus status = maritalStatusDao.getById(MaritalStatus.class,
					data.getProfile().getMaritalStatusId());
			profile.setMaritalStatus(status);

			final Religion religion = religionDao.getById(Religion.class, data.getProfile().getReligionId());
			profile.setReligion(religion);

			final PersonType type = personTypeDao.getById(PersonType.class, data.getProfile().getPersonTypeId());
			profile.setPersonType(type);

			if (data.getProfile().getFile() != null) {
				final String fileId = data.getProfile().getFileId();
				File file = new File();
				file.setFileName(data.getProfile().getFile());
				file.setFileExtension(data.getProfile().getFileExtension());
				file.setCreatedBy(principalService.getAuthPrincipal());
				file = fileDao.save(file);
				profile.setFile(file);
				fileDao.deleteById(File.class, fileId);
			}

			final CandidateStatus candidatestatus = candidateStatusDao.getById(CandidateStatus.class,
					data.getProfile().getCandidateStatusId());
			profile.setCandidateStatus(candidatestatus);
			profile.setUpdatedBy(principalService.getAuthPrincipal());
			candidateProfileDao.saveAndFlush(profile);
			user.setCandidateProfile(profile);
			candidateUserDao.saveAndFlush(user);

			result = new UpdateResDto();
			result.setVersion(profile.getVersion());
			result.setMessage("Candidate User has been updated");

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

		if (!user.getIsActive()) {
			loginRes.setMessage("Akun anda nonaktif");
			return loginRes;
		} else {
			loginRes.setUserId(user.getId());
			loginRes.setFullName(user.getCandidateProfile().getFullname());
			loginRes.setProfileId(user.getCandidateProfile().getId());
			if (user.getCandidateProfile().getFile() != null) {
				loginRes.setPhotoId(user.getCandidateProfile().getFile().getId());
			}
		}

		return loginRes;
	}

	public UpdateResDto changePassword(ChangePasswordReqDto data) {
		final UpdateResDto resDto = new UpdateResDto();
		CandidateUser candidate = candidateUserDao.getById(CandidateUser.class, principalService.getAuthPrincipal());
		try {
			em().getTransaction().begin();
			if (passwordEncoder.matches(data.getOldPassword(), candidate.getUserPassword())) {
				final String encodedPassword = passwordEncoder.encode(data.getNewPassword());
				candidate.setUserPassword(encodedPassword);
				candidate = candidateUserDao.save(candidate);
				resDto.setVersion(candidate.getVersion());
				resDto.setMessage("Update Password Success");
				em().getTransaction().commit();
			} else {
				em().getTransaction().rollback();
				resDto.setMessage("Update Password Failed");
			}

		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		return resDto;
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

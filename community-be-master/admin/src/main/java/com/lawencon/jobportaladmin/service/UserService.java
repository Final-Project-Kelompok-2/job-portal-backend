package com.lawencon.jobportaladmin.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.FileDao;
import com.lawencon.jobportaladmin.dao.PersonTypeDao;
import com.lawencon.jobportaladmin.dao.ProfileDao;
import com.lawencon.jobportaladmin.dao.RoleDao;
import com.lawencon.jobportaladmin.dao.UserDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.login.LoginReqDto;
import com.lawencon.jobportaladmin.dto.login.LoginResDto;
import com.lawencon.jobportaladmin.dto.user.UserInsertReqDto;
import com.lawencon.jobportaladmin.dto.user.UsersResDto;
import com.lawencon.jobportaladmin.model.File;
import com.lawencon.jobportaladmin.model.PersonType;
import com.lawencon.jobportaladmin.model.Profile;
import com.lawencon.jobportaladmin.model.Role;
import com.lawencon.jobportaladmin.model.User;
import com.lawencon.jobportaladmin.util.GenerateCode;

@Service
public class UserService implements UserDetailsService{

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PersonTypeDao personTypeDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private EmailService emailService;
	
	
	public LoginResDto login(LoginReqDto loginData) {
		final User user = userDao.getByUsername(loginData.getUserEmail());
		final LoginResDto loginRes = new LoginResDto();
		
		if(!user.getIsActive()) {
			loginRes.setMessage("Akun anda nonaktif");
			return loginRes;
		}
		else {
			loginRes.setUserId(user.getId());
			loginRes.setFullName(user.getProfile().getFullName());
			loginRes.setProfileId(user.getProfile().getId());
			loginRes.setRoleCode(user.getRole().getRoleCode());
			loginRes.setPhotoId(user.getProfile().getPhoto().getId());
		}
		
		return loginRes;
	}
	
	public InsertResDto registerUser(UserInsertReqDto userData) {
		final InsertResDto insertResDto = new InsertResDto();
		
		try {
			em().getTransaction().begin();
			User newUser = new User();
			
			newUser.setUserEmail(userData.getUserEmail());

			final Role role = roleDao.getById(Role.class, userData.getRoleId());
			newUser.setRole(role);
			
			final String generatePassword = GenerateCode.generateCode();
			final String encodedPassword = passwordEncoder.encode(generatePassword);

			newUser.setUserPassword(encodedPassword);
			
			final PersonType personType = personTypeDao.getById(PersonType.class, userData.getPersonTypeId());
			
			Profile profile = new Profile();
			profile.setFullName(userData.getFullName());
			profile.setAddress(userData.getAddress());
			profile.setPhoneNumber(userData.getPhoneNumber());
			profile.setPersonType(personType);
			
			File photo = new File();
			photo.setFileName(userData.getPhotoName());
			photo.setFileExtension(userData.getExtensionName());
			
			photo = fileDao.save(photo);
			
			profile.setPhoto(photo);
			
			profile = profileDao.save(profile);
			newUser.setProfile(profile);
			newUser= userDao.save(newUser);
			
			final String emailSubject = "Job Portal Account Registration";
			final String emailbody = "Halo " + userData.getFullName() + ", "
					+ " Akun kamu dengan Role : "+ role.getRoleName() + " berhasil dibuat. Silahkan login dengan menggunakan "
					+ "password yang sudah diberikan : "
					+ " Email : " + userData.getUserEmail() 
					+ " Password : " + generatePassword;
			emailService.sendEmail(userData.getUserEmail(), emailSubject, emailbody);
			
			insertResDto.setId(newUser.getId());
			insertResDto.setMessage("Insert User Success");
			
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		
		return insertResDto;
	}
	
	public List<UsersResDto> getAll(){
		final List<UsersResDto> usersDto = new ArrayList<>();
		final List<User> usersDb = userDao.getAll(User.class);
		
		for(int i=0;i<usersDb.size();i++) {
			final UsersResDto userDto = new UsersResDto();
			userDto.setId(usersDb.get(i).getId());
			userDto.setFullName(usersDb.get(i).getProfile().getFullName());
			userDto.setRole(usersDb.get(i).getRole().getRoleName());
			userDto.setEmail(usersDb.get(i).getUserEmail());
			userDto.setRole(usersDb.get(i).getRole().getRoleName());
			usersDto.add(userDto);	
		}
		return usersDto;
	}
	
	public List<UsersResDto> getUsersByRoleCode(String roleCode){
		final List<UsersResDto> usersDto=  new ArrayList<>();
		final List<User> usersDb = userDao.getByRoleCode(roleCode);
		
		for(int i=0;i<usersDb.size();i++) {
			final UsersResDto userDto = new UsersResDto();
			userDto.setId(usersDb.get(i).getId());
			userDto.setFullName(usersDb.get(i).getProfile().getFullName());
			userDto.setRole(usersDb.get(i).getRole().getRoleName());
			userDto.setEmail(usersDb.get(i).getUserEmail());
			userDto.setRole(usersDb.get(i).getRole().getRoleName());
			usersDto.add(userDto);	
		}
		return usersDto;
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userDao.getByUsername(username);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(username, user.getUserPassword(),
					new ArrayList<>());
		}

		throw new UsernameNotFoundException("Email / Password salah");
	}


	
	
}
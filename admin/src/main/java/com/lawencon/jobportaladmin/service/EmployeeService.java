package com.lawencon.jobportaladmin.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.EmployeeDao;
import com.lawencon.jobportaladmin.dto.employee.EmployeeResDto;
import com.lawencon.jobportaladmin.model.Employee;
import com.lawencon.security.principal.PrincipalService;

@Service
public class EmployeeService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private PrincipalService<String> principalService;
	
	
	public List<EmployeeResDto>getAll(){
		final List<Employee> getList = employeeDao.getAll(Employee.class);
		final List<EmployeeResDto> employeeRes = new ArrayList<>();
		for(int i = 0 ; i < getList.size() ; i++) {
			final EmployeeResDto resDto = new EmployeeResDto();
			resDto.setCandidateName(getList.get(i).getCandidate().getCandidateProfile().getFullname());
			resDto.setPhoneNumber(getList.get(i).getCandidate().getCandidateProfile().getPhoneNumber());
			resDto.setJobName(getList.get(i).getJob().getJobName());
			resDto.setCompanyUrl(getList.get(i).getJob().getCompany().getCompanyUrl());
			resDto.setEmploymentTypeName(getList.get(i).getJob().getEmploymentType().getEmploymentTypeName());
			resDto.setCandidateEmail(getList.get(i).getCandidate().getUserEmail());
			employeeRes.add(resDto);
		}
		return employeeRes;
	}

	
	
}

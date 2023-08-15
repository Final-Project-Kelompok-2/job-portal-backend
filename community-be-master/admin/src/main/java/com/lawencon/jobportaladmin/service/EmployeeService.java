package com.lawencon.jobportaladmin.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.CandidateUserDao;
import com.lawencon.jobportaladmin.dao.EmployeeDao;
import com.lawencon.jobportaladmin.dao.JobDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.employee.EmployeeInsertReqDto;
import com.lawencon.jobportaladmin.dto.employee.EmployeeResDto;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.jobportaladmin.model.Employee;
import com.lawencon.jobportaladmin.model.Job;
import com.lawencon.jobportaladmin.util.GenerateCode;
import com.lawencon.security.principal.PrincipalService;

@Service
public class EmployeeService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private CandidateUserDao candidateDao;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private PrincipalService<String> principalService;
	
	
	public List<EmployeeResDto>getAll(){
		final List<Employee>getList = employeeDao.getAll(Employee.class);
		final List<EmployeeResDto> employeeRes = new ArrayList<>();
		for(int i = 0 ; i < getList.size() ; i++) {
			final EmployeeResDto resDto = new EmployeeResDto();
			resDto.setCandidateId(getList.get(i).getCandidate().getId());
			resDto.setEmployeeCode(getList.get(i).getEmployeeCode());
			resDto.setId(getList.get(i).getId());
			resDto.setJobId(getList.get(i).getJob().getId());
			
		}
		return employeeRes;
	}

	
	
}

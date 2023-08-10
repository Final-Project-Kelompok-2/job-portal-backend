package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CompanyDao;
import com.lawencon.jobportalcandidate.dto.company.CompanyResDto;
import com.lawencon.jobportalcandidate.model.Company;

public class CompanyService {
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private CompanyDao companyDao;

	public List<CompanyResDto> getAllCompany() {
		final List<Company> company = companyDao.getAll(Company.class);
		final List<CompanyResDto> companyResList = new ArrayList<>();
		for (int i = 0; i < company.size(); i++) {
			final CompanyResDto companyRes = new CompanyResDto();
			companyRes.setId(company.get(i).getId());
			companyRes.setCompanyName(company.get(i).getCompanyName());
			companyRes.setCompanyCode(company.get(i).getCompanyCode());
			companyRes.setCompanyPhone(company.get(i).getCompanyPhone());
			if (company.get(i).getCompanyUrl() != null) {
				companyRes.setCompanyUrl(company.get(i).getCompanyUrl());
			}
			companyRes.setPhotoId(company.get(i).getPhoto().getId());
			companyResList.add(companyRes);

		}
		
		return companyResList;
	}

}

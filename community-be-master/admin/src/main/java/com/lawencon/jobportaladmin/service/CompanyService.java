package com.lawencon.jobportaladmin.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.CompanyDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.model.Company;
import com.lawencon.jobportaladmin.model.File;


public class CompanyService {

	
	public InsertResDto insertCompany(CompanyInsertReqDto data) {
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
		
		public InsertResDto insertCompany(CompanyInsertReqDto data) {
			final Company company = new Company();
			final InsertResDto insertRes = new InsertResDto();
			try {
				em().getTransaction().begin();
				company.setCompanyName(data.getCompanyName());
				company.setCompanyCode(data.getCompanyCode());
				company.setCompanyPhone(data.getCompanyPhone());
				if(data.getCompanyUrl()!= null ) {
					company.setCompanyUrl(data.getCompanyUrl());
				}
				final File file = new File();
				file.setFileName(data.getFileName());
				file.setFileExtension(data.getFileExtension());
				file.setCreatedBy("Id principal");
				company.setCreatedBy("Id Principal");
				insertRes.setId(company.getId());
				insertRes.setMessage("Company Insert Success");
				em().getTransaction().commit();
			}catch (Exception e) {
				e.printStackTrace();
				em().getTransaction().rollback();;
			}
			
			
			
			
			return insertRes;
		}
}

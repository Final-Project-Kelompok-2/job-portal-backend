package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CompanyDao;
import com.lawencon.jobportalcandidate.dao.FileDao;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.company.CompanyInsertReqDto;
import com.lawencon.jobportalcandidate.dto.company.CompanyResDto;
import com.lawencon.jobportalcandidate.dto.company.CompanyUpdateReqDto;
import com.lawencon.jobportalcandidate.model.Company;
import com.lawencon.jobportalcandidate.model.File;

@Service
public class CompanyService {
	
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private FileDao fileDao;
	public List<CompanyResDto> getAllCompany() {
		final List<Company> company = companyDao.getAll(Company.class);
		final List<CompanyResDto> companyResList = new ArrayList<>();
		for (int i = 0; i < company.size(); i++) {
			final CompanyResDto companyRes = new CompanyResDto();
			companyRes.setId(company.get(i).getId());
			companyRes.setCompanyName(company.get(i).getCompanyName());
			companyRes.setCompanyCode(company.get(i).getCompanyCode());
			companyRes.setCompanyPhone(company.get(i).getCompanyPhone());
			companyRes.setAddress(company.get(i).getAddress());
			if (company.get(i).getCompanyUrl() != null) {
				companyRes.setCompanyUrl(company.get(i).getCompanyUrl());
			}
			companyRes.setPhotoId(company.get(i).getPhoto().getId());
			companyResList.add(companyRes);

		}
		
		return companyResList;
	}
	public InsertResDto insertCompany(CompanyInsertReqDto data) {
		
		final InsertResDto insertRes = new InsertResDto();
		try {
			em().getTransaction().begin();
			Company company = new Company();
			company.setCompanyName(data.getCompanyName());
			company.setCompanyCode(data.getCompanyCode());
			company.setCompanyPhone(data.getCompanyPhone());
			company.setAddress(data.getAddress());
			
			if (data.getCompanyUrl() != null) {
				company.setCompanyUrl(data.getCompanyUrl());
			}
			
			final File file = new File();
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExtension());
			fileDao.save(file);
			
			company.setPhoto(file);
			
			company = companyDao.save(company);
			
			insertRes.setId(company.getId());
			insertRes.setMessage("Insert Company Success");
			
			em().getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			em().getTransaction().rollback();
			
		}

		return insertRes;
	}
	
	public UpdateResDto updateCompany(CompanyUpdateReqDto data) {
		final Company company = companyDao.getById(Company.class, data.getId());
		final UpdateResDto updateRes = new UpdateResDto();
		try {
			em().getTransaction().begin();
			company.setCompanyName(data.getCompanyName());
			company.setCompanyCode(data.getCompanyCode());
			company.setCompanyPhone(data.getCompanyPhone());
			if (data.getCompanyUrl() != null) {
				company.setCompanyUrl(data.getCompanyUrl());
			}
			final File file = new File();
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExtension());
			file.setCreatedBy("Id principal");
			fileDao.save(file);
			company.setCreatedBy("Id Principal");
			final Company companyId = companyDao.saveAndFlush(company);
			updateRes.setVersion(companyId.getVersion());
			updateRes.setMessage("Company Update Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em().getTransaction().rollback();
			
		}

		return updateRes;
	}

}
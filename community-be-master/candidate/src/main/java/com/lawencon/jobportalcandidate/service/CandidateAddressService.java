package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.dao.CandidateAddressDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidateaddress.CandidateAddressInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateaddress.CandidateAddressResDto;
import com.lawencon.jobportalcandidate.dto.candidateaddress.CandidateAddressUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateAddress;
import com.lawencon.jobportalcandidate.model.CandidateUser;

@Service
public class CandidateAddressService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private CandidateUserDao candidateUserDao;
	@Autowired
	private CandidateAddressDao candidateAddressDao;

	public List<CandidateAddressResDto> getAllByCandidate(String id){
		final List<CandidateAddressResDto> candidateAddressResList = new ArrayList<>();
		final List<CandidateAddress> candidateAddress = candidateAddressDao.getByCandidateId(id);
		for(int i = 0 ; i < candidateAddress.size() ; i++) {
			final CandidateAddressResDto addressRes = new CandidateAddressResDto();
			addressRes.setAddress(candidateAddress.get(i).getAddress());
			addressRes.setCity(candidateAddress.get(i).getCity());
			addressRes.setCountry(candidateAddress.get(i).getCountry());
			addressRes.setPostalCode(candidateAddress.get(i).getPostalCode());
			addressRes.setProvince(candidateAddress.get(i).getProvince());
			addressRes.setResidenceType(candidateAddress.get(i).getResidenceType());
			addressRes.setCandidateId(candidateAddress.get(i).getCandidateUser().getId());
			addressRes.setId(candidateAddress.get(i).getId());
			candidateAddressResList.add(addressRes);
		}
		return candidateAddressResList;
	}
	
	
	public InsertResDto insertCandidateAddress(CandidateAddressInsertReqDto data) {
		final InsertResDto insertResDto = new InsertResDto();
		try {
			em().getTransaction().begin();
			final CandidateAddress candidateAddress = new CandidateAddress();
			candidateAddress.setAddress(data.getAddress());
			candidateAddress.setCity(data.getCity());
			candidateAddress.setCountry(data.getCountry());
			candidateAddress.setProvince(data.getProvince());
			candidateAddress.setPostalCode(data.getPostalCode());
			candidateAddress.setResidenceType(data.getResidenceType());
			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, data.getCandidateId());
			candidateAddress.setCandidateUser(candidateUser);
			candidateAddress.setCreatedBy("Id Principal");
			final CandidateAddress candidateAddressId = candidateAddressDao.save(candidateAddress);
			insertResDto.setId(candidateAddressId.getId());
			insertResDto.setMessage("Insert Candidate Address Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		return insertResDto;
	}
	
	public UpdateResDto updateCandidateAdress(CandidateAddressUpdateReqDto data) {
		final UpdateResDto updateResDto = new UpdateResDto();
		try {
			em().getTransaction().begin();
			final CandidateAddress candidateAddress = candidateAddressDao.getById(CandidateAddress.class, data.getId());
			candidateAddress.setAddress(data.getAddress());
			candidateAddress.setCity(data.getCity());
			candidateAddress.setCountry(data.getCountry());
			candidateAddress.setProvince(data.getProvince());
			candidateAddress.setPostalCode(data.getPostalCode());
			candidateAddress.setResidenceType(data.getResidenceType());
			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, data.getCandidateId());
			candidateAddress.setCandidateUser(candidateUser);
			candidateAddress.setCreatedBy("Id Principal");
			final CandidateAddress candidateAddressId = candidateAddressDao.save(candidateAddress);
			
			updateResDto.setMessage("Update Candidate Address Success");
			updateResDto.setVersion(candidateAddressId.getVersion());
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		return updateResDto;
	}
	
	public DeleteResDto deleteCandidateAddress(String id) {
		candidateAddressDao.deleteById(CandidateAddress.class, id);
		final DeleteResDto deleteRes = new DeleteResDto();
		deleteRes.setMessage("Delete Candidate Address Success");
		return deleteRes;
	}
}

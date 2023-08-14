package com.lawencon.jobportalcandidate.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.base.ConnHandler;
import com.lawencon.config.JwtConfig;
import com.lawencon.jobportalcandidate.dao.CandidateDocumentsDao;
import com.lawencon.jobportalcandidate.dao.CandidateUserDao;
import com.lawencon.jobportalcandidate.dao.FileDao;
import com.lawencon.jobportalcandidate.dao.FileTypeDao;
import com.lawencon.jobportalcandidate.dto.DeleteResDto;
import com.lawencon.jobportalcandidate.dto.InsertResDto;
import com.lawencon.jobportalcandidate.dto.UpdateResDto;
import com.lawencon.jobportalcandidate.dto.candidatedocument.CandidateDocumentInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidatedocument.CandidateDocumentResDto;
import com.lawencon.jobportalcandidate.dto.candidatedocument.CandidateDocumentUpdateReqDto;
import com.lawencon.jobportalcandidate.model.CandidateDocuments;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.jobportalcandidate.model.File;
import com.lawencon.jobportalcandidate.model.FileType;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateDocumentService {
	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CandidateDocumentsDao candidateDocumentDao;
	@Autowired
	private CandidateUserDao candidateUserDao;
	@Autowired
	private FileTypeDao fileTypeDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private PrincipalService<String> principalService;
	
	public List<CandidateDocumentResDto> getCandidateDocumentByCandidate(String id){
		final List<CandidateDocuments> candidateDocuments = candidateDocumentDao.getByCandidateEmail(id);
		final List<CandidateDocumentResDto> candidateDocumentResList = new ArrayList<>();
		for(int i = 0 ; i < candidateDocuments.size() ; i++) {
			final CandidateDocumentResDto document = new CandidateDocumentResDto();
			document.setCandidateId(candidateDocuments.get(i).getCandidateUser().getId());
			document.setDocName(candidateDocuments.get(i).getDocName());
			document.setId(candidateDocuments.get(i).getId());
			document.setFileId(candidateDocuments.get(i).getFile().getId());
			document.setFileTypeId(candidateDocuments.get(i).getFileType().getId());
			candidateDocumentResList.add(document);
		}
		return candidateDocumentResList;
	}

	public InsertResDto insertCandidateDocument(CandidateDocumentInsertReqDto data) {

		final InsertResDto insertRes = new InsertResDto();
		try {
			em().getTransaction().begin();
			final CandidateDocuments candidateDocument = new CandidateDocuments();
			candidateDocument.setDocName(data.getDocName());
			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, principalService.getAuthPrincipal());
			candidateDocument.setCandidateUser(candidateUser);
			data.setEmail(candidateUser.getUserEmail());
			final FileType fileType = fileTypeDao.getByCode(data.getFileTypeCode());
			candidateDocument.setFileType(fileType);

			final File file = new File();
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExtension());
			file.setCreatedBy(principalService.getAuthPrincipal());
			fileDao.save(file);

			candidateDocument.setFile(file);
			candidateDocument.setCreatedBy(principalService.getAuthPrincipal());
			final CandidateDocuments candidateDocumentId = candidateDocumentDao.save(candidateDocument);
	
			
			final String candidateDocumentApi = "http://localhost:8080/candidate-documents";
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(JwtConfig.get());
			
			final RequestEntity<CandidateDocumentInsertReqDto>documentInsert = RequestEntity.post(candidateDocumentApi).headers(headers).body(data);
			final ResponseEntity<InsertResDto> responseAdmin = restTemplate.exchange(documentInsert, InsertResDto.class);
			if(responseAdmin.getStatusCode().equals(HttpStatus.CREATED)){
				insertRes.setId(candidateDocumentId.getId());
				insertRes.setMessage("Document Insert Success !");
				em().getTransaction().commit();
			}
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return insertRes;

	}

	public UpdateResDto updateCandidateDocument(CandidateDocumentUpdateReqDto data) {
		final UpdateResDto updateResDto = new UpdateResDto();
		try {
			em().getTransaction().begin();
			final CandidateDocuments candidateDocument = candidateDocumentDao.getById(CandidateDocuments.class,
					data.getId());
			candidateDocument.setDocName(data.getDocName());
			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, data.getCandidateId());
			candidateDocument.setCandidateUser(candidateUser);

			final FileType fileType = fileTypeDao.getById(FileType.class, data.getFileTypeId());
			candidateDocument.setFileType(fileType);

			final File file = new File();
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExtension());
			file.setCreatedBy(principalService.getAuthPrincipal());
			fileDao.save(file);

			candidateDocument.setFile(file);
			candidateDocument.setCreatedBy(principalService.getAuthPrincipal());
			final CandidateDocuments candidateDocumentId = candidateDocumentDao.saveAndFlush(candidateDocument);
			fileDao.deleteById(File.class, candidateDocument.getFile().getId());
			updateResDto.setVersion(candidateDocumentId.getVersion());
			updateResDto.setMessage("Document Update Success");
			em().getTransaction().commit();
		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}
		return updateResDto;
	}

	public DeleteResDto deleteDCandidateDocument(String id) {
		candidateDocumentDao.deleteById(CandidateDocuments.class, id);
		final DeleteResDto deleteRes = new DeleteResDto();
		deleteRes.setMessage("Delete Candidate Document Success");
		return deleteRes;
	}

}

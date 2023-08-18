package com.lawencon.jobportaladmin.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.CandidateDocumentsDao;
import com.lawencon.jobportaladmin.dao.CandidateUserDao;
import com.lawencon.jobportaladmin.dao.FileDao;
import com.lawencon.jobportaladmin.dao.FileTypeDao;
import com.lawencon.jobportaladmin.dto.DeleteResDto;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.candidatedocument.CandidateDocumentInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidatedocument.CandidateDocumentResDto;
import com.lawencon.jobportaladmin.model.CandidateDocuments;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.jobportaladmin.model.File;
import com.lawencon.jobportaladmin.model.FileType;
import com.lawencon.security.principal.PrincipalService;

@Service
public class CandidateDocumentService {
	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
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

	public List<CandidateDocumentResDto> getCandidateDocumentByCandidate(String id) {
		final List<CandidateDocuments> candidateDocuments = candidateDocumentDao.getCandidateDocumentsByCandidate(id);
		final List<CandidateDocumentResDto> candidateDocumentResList = new ArrayList<>();
		for(int i = 0 ; i < candidateDocuments.size() ; i++) {
			final CandidateDocumentResDto document = new CandidateDocumentResDto();
			document.setCandidateId(candidateDocuments.get(i).getCandidateUser().getUserEmail());
			document.setDocName(candidateDocuments.get(i).getDocName());
			document.setId(candidateDocuments.get(i).getId());
			document.setFileId(candidateDocuments.get(i).getFile().getId());
			document.setFileTypeId(candidateDocuments.get(i).getFileType().getTypeName());
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
			final CandidateUser candidateUser = candidateUserDao.getByEmail(data.getEmail());
			candidateDocument.setCandidateUser(candidateUser);

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
			insertRes.setId(candidateDocumentId.getId());
			insertRes.setMessage("Document Insert Success");

			em().getTransaction().commit();

		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return insertRes;

	}

//	public UpdateResDto updateCandidateDocument(CandidateDocumentUpdateReqDto data) {
//		final UpdateResDto updateResDto = new UpdateResDto();
//		try {
//			em().getTransaction().begin();
//			final CandidateDocuments candidateDocument = candidateDocumentDao.getById(CandidateDocuments.class,
//					data.getId());
//			candidateDocument.setDocName(data.getDocName());
//			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, data.getCandidateId());
//			candidateDocument.setCandidateUser(candidateUser);
//
//			final FileType fileType = fileTypeDao.getById(FileType.class, data.getFileTypeId());
//			candidateDocument.setFileType(fileType);
//
//			final File file = new File();
//			file.setFileName(data.getFileName());
//			file.setFileExtension(data.getFileExtension());
//			file.setCreatedBy(principalService.getAuthPrincipal());
//			fileDao.save(file);
//
//			candidateDocument.setFile(file);
//			candidateDocument.setCreatedBy(principalService.getAuthPrincipal());
//			final CandidateDocuments candidateDocumentId = candidateDocumentDao.saveAndFlush(candidateDocument);
//			fileDao.deleteById(File.class, candidateDocument.getFile().getId());
//			updateResDto.setVersion(candidateDocumentId.getVersion());
//			updateResDto.setMessage("Document Update Success");
//			em().getTransaction().commit();
//		} catch (Exception e) {
//			em().getTransaction().rollback();
//			e.printStackTrace();
//		}
//		return updateResDto;
//	}
//
	public DeleteResDto deleteDCandidateDocument(String id) {
		candidateDocumentDao.deleteById(CandidateDocuments.class, id);
		final DeleteResDto deleteRes = new DeleteResDto();
		deleteRes.setMessage("Delete Candidate Document Success");
		return deleteRes;
	}

}

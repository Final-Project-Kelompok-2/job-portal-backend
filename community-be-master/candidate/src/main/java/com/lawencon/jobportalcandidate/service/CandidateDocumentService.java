package com.lawencon.jobportalcandidate.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
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
	
	public List<CandidateDocumentResDto> getCandidateDocumentByCandidate(String id){
		final List<CandidateDocuments> candidateDocuments = candidateDocumentDao.getCandidateDocumentsByCandidate(id);
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
			final CandidateDocuments candidateDocument = new CandidateDocuments();
			candidateDocument.setDocName(data.getDocName());
			final CandidateUser candidateUser = candidateUserDao.getById(CandidateUser.class, data.getCandidateId());
			candidateDocument.setCandidateUser(candidateUser);

			final FileType fileType = fileTypeDao.getById(FileType.class, data.getFileTypeId());
			candidateDocument.setFileType(fileType);

			final File file = new File();
			file.setFileName(data.getFileName());
			file.setFileExtension(data.getFileExtension());
			file.setCreatedBy("ID Principal");
			fileDao.save(file);

			candidateDocument.setFile(file);
			candidateDocument.setCreatedBy("ID Principal");
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
			file.setCreatedBy("ID Principal");
			fileDao.save(file);

			candidateDocument.setFile(file);
			candidateDocument.setCreatedBy("ID Principal");
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

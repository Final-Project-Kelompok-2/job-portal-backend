package com.lawencon.jobportalcandidate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportalcandidate.model.CandidateDocuments;
import com.lawencon.jobportalcandidate.model.CandidateProfile;
import com.lawencon.jobportalcandidate.model.CandidateUser;
import com.lawencon.jobportalcandidate.model.File;
import com.lawencon.jobportalcandidate.model.FileType;

@Repository
public class CandidateDocumentsDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	
	public List<CandidateDocuments> getCandidateDocumentsByCandidate (String id){
		final String sql = "SELECT   "
				+ "	tcd.id,  "
				+ "	tcd.doc_name,  "
//				+ "	tcu.id,  "
//				+ "	tft.id,  "
				+ "	tft.type_code,  "
				+ "	tft.type_name  "
				+ "FROM   "
				+ "	t_candidate_documents tcd   "
				+ "INNER JOIN   "
				+ "	t_candidate_user tcu   "
				+ "ON  "
				+ "	tcd.user_id  = tcu.id   "
				+ "INNER JOIN   "
				+ "	t_file_type tft   "
				+ "ON   "
				+ "	tcd.file_type_id = tft.id   "
				+ "WHERE   "
				+ "	tcd.user_id  = :candidate";

		final List<?>documentObjs = em().createNativeQuery(sql)
				.setParameter("candidate", id)
				.getResultList();
		final List<CandidateDocuments> candidateDocumentsList = new ArrayList<>();
		if(documentObjs.size() > 0) {
			for(Object documentObj : documentObjs ) {
				final Object[] documentArr = (Object[]) documentObj;
				final CandidateDocuments candidateDocument = new CandidateDocuments();
				candidateDocument.setId(documentArr[0].toString());
				candidateDocument.setDocName(documentArr[1].toString());
				
//				
//				final CandidateUser candidateUser = new CandidateUser();
//				candidateUser.setId(documentArr[2].toString());
//				candidateDocument.setCandidateUser(candidateUser);
				
				final FileType fileType = new FileType();
//				fileType.setId(documentArr[3].toString());
				fileType.setTypeCode(documentArr[2].toString());
				fileType.setTypeName(documentArr[3].toString());
				
				candidateDocument.setFileType(fileType);
				candidateDocumentsList.add(candidateDocument);
				
			}
		}
		
		return candidateDocumentsList;
	}
	
	public List<CandidateDocuments> getByCandidateEmail(String id){
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT cd ");
		sql.append("FROM ");
		sql.append("CandidateDocuments cd ");
		sql.append("WHERE cd.candidateUser.id = :id");
		
		final List<CandidateDocuments> documentList = em().createQuery(sql.toString(), CandidateDocuments.class)
				.setParameter("id", id)
				.getResultList();
		return documentList;
		
	}
}

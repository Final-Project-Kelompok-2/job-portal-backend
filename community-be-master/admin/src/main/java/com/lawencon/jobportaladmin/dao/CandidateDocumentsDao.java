package com.lawencon.jobportaladmin.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.model.CandidateDocuments;
import com.lawencon.jobportaladmin.model.CandidateProfile;
import com.lawencon.jobportaladmin.model.CandidateUser;
import com.lawencon.jobportaladmin.model.File;
import com.lawencon.jobportaladmin.model.FileType;

@Repository
public class CandidateDocumentsDao extends AbstractJpaDao{

	private EntityManager em() {
		return ConnHandler.getManager();
	}
	
	
	public List<CandidateDocuments> getCandidateDocumentsByCandidate (String id){
		final String sql = "SELECT  "
				+ "	tcd.id, "
				+ "	doc_name, "
				+ "	tcu.id, "
				+ "	tcp.id, "
				+ "	tcp.fullname, "
				+ "	tf.id, "
				+ "	tf.filename, "
				+ "	tf.file_extension, "
				+ "	tft.id, "
				+ "	tft.type_code, "
				+ "	tft.type_name "
				+ "FROM  "
				+ "	t_candidate_documents tcd  "
				+ "INNER JOIN  "
				+ "	t_candidate_user tcu  "
				+ "ON "
				+ "	tcd.user_id  = tcu.id  "
				+ "INNER JOIN  "
				+ "	t_candidate_profile tcp  "
				+ "ON  "
				+ "	tcp.id = tcu.profile_id  "
				+ "INNER JOIN  "
				+ "	t_file tf 	 "
				+ "ON  "
				+ "	tf.id = tcp.file_id  "
				+ "INNER JOIN  "
				+ "	t_file_type tft  "
				+ "ON  "
				+ "	tcd.file_type_id = tft.id  "
				+ "WHERE  "
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
				
				final File file = new File();
				file.setId(documentArr[5].toString());
				file.setFileName(documentArr[6].toString());
				file.setFileExtension(documentArr[7].toString());
												
				final CandidateProfile candidateProfile = new CandidateProfile();
				candidateProfile.setId(documentArr[3].toString());
				candidateProfile.setFullname(documentArr[4].toString());
				candidateProfile.setFile(file);
				
				final CandidateUser candidateUser = new CandidateUser();
				candidateUser.setId(documentArr[2].toString());
				candidateUser.setCandidateProfile(candidateProfile);
				candidateDocument.setCandidateUser(null);
				
				final FileType fileType = new FileType();
				fileType.setId(documentArr[8].toString());
				fileType.setTypeCode(documentArr[9].toString());
				fileType.setTypeName(documentArr[10].toString());
				
				candidateDocument.setFileType(fileType);
				candidateDocumentsList.add(candidateDocument);
				
			}
		}
		
		return candidateDocumentsList;
	}
	
	public List<CandidateDocuments> getByCandidateEmail(String email){
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT cd ");
		sql.append("FROM ");
		sql.append("CandidateDocuments cd ");
		sql.append("INNER JOIN CandidateUser cu ");
		sql.append("WHERE cu.userEmail = :email");
		
		final List<CandidateDocuments> documentList = em().createNamedQuery(sql.toString(), CandidateDocuments.class)
				.setParameter("email", email)
				.getResultList();
		return documentList;
		
	}
}

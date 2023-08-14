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
		final StringBuilder sql = new StringBuilder(); 
				sql.append("SELECT  ");
				sql.append ("	tcd.id, ");
				sql.append ("	doc_name, ");
				sql.append ("	tcu.id, ");
				sql.append ("	tcp.id, ");
				sql.append ("	tcp.fullname, ");
				sql.append ("	tf.id, ");
				sql.append ("	tf.filename, ");
				sql.append ("	tf.file_extension, ");
				sql.append ("	tft.id, ");
				sql.append ("	tft.type_code, ");
				sql.append ("	tft.type_name ");
				sql.append ("FROM  ");
				sql.append ("	t_candidate_documents tcd  ");
				sql.append ("INNER JOIN  ");
				sql.append ("	t_candidate_user tcu  ");
				sql.append ("ON ");
				sql.append ("	tcd.user_id  = tcu.id  ");
				sql.append ("INNER JOIN  ");
				sql.append ("	t_candidate_profile tcp  ");
				sql.append ("ON  ");
				sql.append ("	tcp.id = tcu.profile_id  ");
				sql.append ("INNER JOIN  ");
				sql.append ("	t_file tf 	 ");
				sql.append ("ON  ");
				sql.append ("	tf.id = tcp.file_id  ");
				sql.append ("INNER JOIN  ");
				sql.append ("	t_file_type tft  ");
				sql.append ("ON  ");
				sql.append ("	tcd.file_type_id = tft.id  ");
				sql.append ("WHERE  ");
				sql.append ("	tcd.user_id  = :candidate");
		final List<?>documentObjs = em().createNativeQuery(sql.toString())
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

package com.lawencon.jobportaladmin.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.jobportaladmin.dao.ApplicantDao;
import com.lawencon.jobportaladmin.dao.FileDao;
import com.lawencon.jobportaladmin.dao.McuDao;
import com.lawencon.jobportaladmin.dto.InsertResDto;
import com.lawencon.jobportaladmin.dto.mcu.McuInsertReqDto;
import com.lawencon.jobportaladmin.dto.mcu.McusInsertReqDto;
import com.lawencon.jobportaladmin.model.Applicant;
import com.lawencon.jobportaladmin.model.File;
import com.lawencon.jobportaladmin.model.Mcu;

@Service
public class McuService {

	private EntityManager em() {
		return ConnHandler.getManager();
	}

	@Autowired
	private McuDao mcuDao;

	@Autowired
	private FileDao fileDao;

	@Autowired
	private ApplicantDao applicantDao;

	public InsertResDto insertMcuFiles(McusInsertReqDto data) {
		final InsertResDto resDto = new InsertResDto();

		try {
			em().getTransaction().begin();
			final Applicant applicant = applicantDao.getById(Applicant.class, data.getApplicantId());

			for (int i = 0; i < data.getMcuData().size(); i++) {
				final McuInsertReqDto mcuData = data.getMcuData().get(i);
				Mcu newMcu = new Mcu();

				File newMcuFile = new File();
				newMcuFile.setFileName(mcuData.getFileName());
				newMcuFile.setFileExtension(mcuData.getFileExtension());
				newMcuFile = fileDao.save(newMcuFile);

				newMcu.setApplicant(applicant);
				newMcu.setFile(newMcuFile);
				newMcu = mcuDao.save(newMcu);
			}
			
			resDto.setMessage("Insert Mcu Files Success");
			em().getTransaction().commit();

		} catch (Exception e) {
			em().getTransaction().rollback();
			e.printStackTrace();
		}

		return resDto;
	}

}

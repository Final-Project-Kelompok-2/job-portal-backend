package com.lawencon.jobportaladmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.jobportaladmin.dao.ReportDao;
import com.lawencon.jobportaladmin.dto.report.ReportResDto;

@Service
public class ReportService {

	@Autowired
	private ReportDao reportDao;
	
	public byte[] downloadReport() {
		//TODO : Ngoding disini ya dit
		
		return null;
		
	}
	
	public List<ReportResDto> getReport(){
		final List<ReportResDto> reports = reportDao.getReport();
		return reports;
	}
	
}

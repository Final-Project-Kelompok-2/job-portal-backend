package com.lawencon.jobportaladmin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.jobportaladmin.dao.ReportDao;
import com.lawencon.jobportaladmin.dto.report.ReportResDto;
import com.lawencon.util.JasperUtil;

@Service
public class ReportService {

	@Autowired
	private ReportDao reportDao;
	
  @Autowired
	private JasperUtil jasperUtil;
  
	public List<ReportResDto> getReport(){
		final List<ReportResDto> reports = reportDao.getReport();
		return reports;
	}
	
	

	public byte[] downloadReport(List<ReportResDto> reportDatas) throws Exception {

		return  jasperUtil.responseToByteArray
				(reportDatas, null, "Report");		
	}

}

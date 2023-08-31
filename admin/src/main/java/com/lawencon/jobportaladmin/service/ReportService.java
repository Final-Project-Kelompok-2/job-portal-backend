package com.lawencon.jobportaladmin.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

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

	public List<ReportResDto> getReport(String startDate, String endDate) {
		Timestamp newStartDate = null;
		Timestamp newEndDate = null;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 23);
		
		if(startDate != null) {
			newStartDate = Timestamp.valueOf(startDate);
			
		}
		if(endDate != null) {
			newEndDate = Timestamp.valueOf(endDate);
			
		}		
		final List<ReportResDto> reports = reportDao.getReport(newStartDate,newEndDate);
		System.out.println("Start date ===========================         " + newStartDate);
		System.out.println("End date ===========================         " + newEndDate);
		System.out.println("Reporttt    ======    " + reports);
		return reports;
	}

  public byte[] downloadReport(List<ReportResDto> reportDatas) throws Exception {

		return jasperUtil.responseToByteArray(reportDatas, null, "Report");
	}

}

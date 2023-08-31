package com.lawencon.jobportaladmin.service;

import java.sql.Timestamp;
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

		if (startDate != null) {
			newStartDate = Timestamp.valueOf(startDate);

		}
		if (endDate != null || "".equalsIgnoreCase(endDate)) {
			newEndDate = Timestamp.valueOf(endDate);

		}
		final List<ReportResDto> reports = reportDao.getReport(newStartDate, newEndDate);
		return reports;
	}

	public byte[] downloadReport(String startDate, String endDate) throws Exception {

		Timestamp newStartDate = null;
		Timestamp newEndDate = null;

		if (startDate != null) {
			newStartDate = Timestamp.valueOf(startDate);

		}
		if (endDate != null || "".equalsIgnoreCase(endDate)) {
			newEndDate = Timestamp.valueOf(endDate);

		}
		final List<ReportResDto> reports = reportDao.getReport(newStartDate, newEndDate);
		
		return jasperUtil.responseToByteArray(reports, null, "Report");
	}

}

package com.lawencon.jobportaladmin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.jobportaladmin.dto.report.ReportResDto;
import com.lawencon.util.JasperUtil;

@Service
public class ReportService {

	@Autowired
	private JasperUtil jasperUtil;

	public byte[] downloadReport(List<ReportResDto> reportDatas) throws Exception {

		return  jasperUtil.responseToByteArray
				(reportDatas, null, "Report");		
	}

}

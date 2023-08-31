package com.lawencon.jobportaladmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.jobportaladmin.dto.report.ReportResDto;
import com.lawencon.jobportaladmin.service.ReportService;

@RestController
@RequestMapping("reports")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/download")
    public ResponseEntity<?> getReport(List<ReportResDto> reportDatas) throws Exception {
        final String fileName = "Report";
        final byte[] fileBytes = reportService.downloadReport(reportDatas);
        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf")
                .body(fileBytes);
   }
	
	@GetMapping
	public ResponseEntity<List<ReportResDto>> getReports(@Param("startDate")String startDate, @Param("endDate")String endDate ){
		final List<ReportResDto> response = reportService.getReport(startDate,endDate);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}


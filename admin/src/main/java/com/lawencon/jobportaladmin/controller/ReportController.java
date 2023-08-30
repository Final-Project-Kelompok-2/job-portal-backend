package com.lawencon.jobportaladmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
//	@GetMapping
//    public ResponseEntity<?> getFileById(@PathVariable("id") String id) {
//        final String fileName = "attachment";
//        final byte[] fileBytes = reportService.downloadReport(null);
//        return ResponseEntity.ok()
//                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".pdf")
//                .body(fileBytes);
//   }
	
	@GetMapping
	public ResponseEntity<List<ReportResDto>> getReports(){
		System.out.println("==================");
		final List<ReportResDto> response = reportService.getReport();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}


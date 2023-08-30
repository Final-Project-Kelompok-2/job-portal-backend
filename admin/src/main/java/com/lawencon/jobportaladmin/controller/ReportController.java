package com.lawencon.jobportaladmin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("reports")
@RequestMapping
public class ReportController {
	
	
//	@GetMapping("{id}")
//    public ResponseEntity<?> getFileById(@PathVariable("id") String id) {
//        final File file = fileService.getById(id);
//        System.out.println(file.getFileName());
//        final String fileName = "attachment";
//        final byte[] fileBytes = Base64.getDecoder().decode(file.getFileName());
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + "." + file.getFileExtension())
//                .body(fileBytes);
//   }
}

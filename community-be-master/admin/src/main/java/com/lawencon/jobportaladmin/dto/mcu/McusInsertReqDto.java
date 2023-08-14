package com.lawencon.jobportaladmin.dto.mcu;

import java.util.List;

public class McusInsertReqDto {

	private String applicantId;
	private List<McuInsertReqDto> mcuData;

	public List<McuInsertReqDto> getMcuData() {
		return mcuData;
	}

	public void setMcuData(List<McuInsertReqDto> mcuData) {
		this.mcuData = mcuData;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

}

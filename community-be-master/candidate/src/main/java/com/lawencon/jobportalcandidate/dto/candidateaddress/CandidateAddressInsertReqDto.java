package com.lawencon.jobportalcandidate.dto.candidateaddress;

public class CandidateAddressInsertReqDto {

	private String address;
	private String residenceType;
	private String country;
	private String province;
	private String city;
	private String postalCode;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}

package com.lawencon.jobportaladmin.dto.candidate;

import java.util.List;

import com.lawencon.jobportaladmin.dto.candidateaddress.CandidateAddressInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidatedocument.CandidateDocumentInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidateeducation.CandidateEducationInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidatefamily.CandidateFamilyInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidatelanguage.CandidateLanguageInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidateprofile.CandidateProfileInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidateprojectexp.CandidateProjectExpInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidatereferences.CandidateReferencesInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidateskill.CandidateSkillInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidatetrainingexp.CandidateTrainingExpInsertReqDto;
import com.lawencon.jobportaladmin.dto.candidateworkexp.CandidateWorkExpInsertReqDto;

public class CandidateMasterInsertReqDto {
	private CandidateProfileInsertReqDto candidateProfile;
	private CandidateAddressInsertReqDto candidateAddress;
	private List<CandidateDocumentInsertReqDto> candidateDocuments;
	private List<CandidateEducationInsertReqDto> candidateEducations;
	private List<CandidateFamilyInsertReqDto> candidateFamily;
	private List<CandidateLanguageInsertReqDto> candidateLanguage;
	private List<CandidateProjectExpInsertReqDto> candidateProjectExp;
	private List<CandidateReferencesInsertReqDto> candidateReferences;
	private List<CandidateSkillInsertReqDto> candidateSkill;
	private List<CandidateTrainingExpInsertReqDto> candidateTrainingExp;
	private List<CandidateWorkExpInsertReqDto> candidateWorkExp;

	public CandidateProfileInsertReqDto getCandidateProfile() {
		return candidateProfile;
	}

	public void setCandidateProfile(CandidateProfileInsertReqDto candidateProfile) {
		this.candidateProfile = candidateProfile;
	}

	public CandidateAddressInsertReqDto getCandidateAddress() {
		return candidateAddress;
	}

	public void setCandidateAddress(CandidateAddressInsertReqDto candidateAddress) {
		this.candidateAddress = candidateAddress;
	}

	public List<CandidateDocumentInsertReqDto> getCandidateDocuments() {
		return candidateDocuments;
	}

	public void setCandidateDocuments(List<CandidateDocumentInsertReqDto> candidateDocuments) {
		this.candidateDocuments = candidateDocuments;
	}

	public List<CandidateEducationInsertReqDto> getCandidateEducations() {
		return candidateEducations;
	}

	public void setCandidateEducations(List<CandidateEducationInsertReqDto> candidateEducations) {
		this.candidateEducations = candidateEducations;
	}

	public List<CandidateFamilyInsertReqDto> getCandidateFamily() {
		return candidateFamily;
	}

	public void setCandidateFamily(List<CandidateFamilyInsertReqDto> candidateFamily) {
		this.candidateFamily = candidateFamily;
	}

	public List<CandidateLanguageInsertReqDto> getCandidateLanguage() {
		return candidateLanguage;
	}

	public void setCandidateLanguage(List<CandidateLanguageInsertReqDto> candidateLanguage) {
		this.candidateLanguage = candidateLanguage;
	}

	public List<CandidateProjectExpInsertReqDto> getCandidateProjectExp() {
		return candidateProjectExp;
	}

	public void setCandidateProjectExp(List<CandidateProjectExpInsertReqDto> candidateProjectExp) {
		this.candidateProjectExp = candidateProjectExp;
	}

	public List<CandidateReferencesInsertReqDto> getCandidateReferences() {
		return candidateReferences;
	}

	public void setCandidateReferences(List<CandidateReferencesInsertReqDto> candidateReferences) {
		this.candidateReferences = candidateReferences;
	}

	public List<CandidateSkillInsertReqDto> getCandidateSkill() {
		return candidateSkill;
	}

	public void setCandidateSkill(List<CandidateSkillInsertReqDto> candidateSkill) {
		this.candidateSkill = candidateSkill;
	}

	public List<CandidateTrainingExpInsertReqDto> getCandidateTrainingExp() {
		return candidateTrainingExp;
	}

	public void setCandidateTrainingExp(List<CandidateTrainingExpInsertReqDto> candidateTrainingExp) {
		this.candidateTrainingExp = candidateTrainingExp;
	}

	public List<CandidateWorkExpInsertReqDto> getCandidateWorkExp() {
		return candidateWorkExp;
	}

	public void setCandidateWorkExp(List<CandidateWorkExpInsertReqDto> candidateWorkExp) {
		this.candidateWorkExp = candidateWorkExp;
	}

}

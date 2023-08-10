package com.lawencon.jobportaladmin.dto.candidate;

import java.util.List;

import com.lawencon.jobportalcandidate.dto.candidateaddress.CandidateAddressInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidatedocument.CandidateDocumentInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateeducation.CandidateEducationInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidatefamily.CandidateFamilyInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidatelanguage.CandidateLanguageInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateprofile.CandidateProfileInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateprojectexp.CandidateProjectExpInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidatereferences.CandidateReferencesInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateskill.CandidateSkillInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidatetrainingexp.CandidateTrainingExpInsertReqDto;
import com.lawencon.jobportalcandidate.dto.candidateworkexp.CandidateWorkExpInsertReqDto;

public class CandidateMasterResDto {
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
	public CandidateAddressInsertReqDto getCandidateAddress() {
		return candidateAddress;
	}
	public List<CandidateDocumentInsertReqDto> getCandidateDocuments() {
		return candidateDocuments;
	}
	public List<CandidateEducationInsertReqDto> getCandidateEducations() {
		return candidateEducations;
	}
	public List<CandidateFamilyInsertReqDto> getCandidateFamily() {
		return candidateFamily;
	}
	public List<CandidateLanguageInsertReqDto> getCandidateLanguage() {
		return candidateLanguage;
	}
	public List<CandidateProjectExpInsertReqDto> getCandidateProjectExp() {
		return candidateProjectExp;
	}
	public List<CandidateReferencesInsertReqDto> getCandidateReferences() {
		return candidateReferences;
	}
	public List<CandidateSkillInsertReqDto> getCandidateSkill() {
		return candidateSkill;
	}
	public List<CandidateTrainingExpInsertReqDto> getCandidateTrainingExp() {
		return candidateTrainingExp;
	}
	public List<CandidateWorkExpInsertReqDto> getCandidateWorkExp() {
		return candidateWorkExp;
	}

	
}

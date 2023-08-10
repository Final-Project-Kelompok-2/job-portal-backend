package com.lawencon.jobportalcandidate.dto.candidate;

import java.util.List;

import com.lawencon.jobportalcandidate.dto.candidateaddress.CandidateAddressResDto;
import com.lawencon.jobportalcandidate.dto.candidatedocument.CandidateDocumentResDto;
import com.lawencon.jobportalcandidate.dto.candidateeducation.CandidateEducationResDto;
import com.lawencon.jobportalcandidate.dto.candidatefamily.CandidateFamilyResDto;
import com.lawencon.jobportalcandidate.dto.candidatelanguage.CandidateLanguageResDto;
import com.lawencon.jobportalcandidate.dto.candidateprofile.CandidateProfileResDto;
import com.lawencon.jobportalcandidate.dto.candidateprojectexp.CandidateProjectExpResDto;
import com.lawencon.jobportalcandidate.dto.candidatereferences.CandidateReferencesResDto;
import com.lawencon.jobportalcandidate.dto.candidateskill.CandidateSkillResDto;
import com.lawencon.jobportalcandidate.dto.candidatetrainingexp.CandidateTrainingExpResDto;
import com.lawencon.jobportalcandidate.dto.candidateworkexp.CandidateWorkExpResDto;

public class CandidateMasterResDto {
	private CandidateProfileResDto candidateProfile;
	private CandidateAddressResDto candidateAddress;
	private List<CandidateDocumentResDto> candidateDocuments;
	private List<CandidateEducationResDto> candidateEducations;
	private List<CandidateFamilyResDto> candidateFamily;
	private List<CandidateLanguageResDto> candidateLanguage;
	private List<CandidateProjectExpResDto> candidateProjectExp;
	private List<CandidateReferencesResDto> candidateReferences;
	private List<CandidateSkillResDto> candidateSkill;
	private List<CandidateTrainingExpResDto> candidateTrainingExp;
	private List<CandidateWorkExpResDto> candidateWorkExp;
	public CandidateProfileResDto getCandidateProfile() {
		return candidateProfile;
	}
	public CandidateAddressResDto getCandidateAddress() {
		return candidateAddress;
	}
	public List<CandidateDocumentResDto> getCandidateDocuments() {
		return candidateDocuments;
	}
	public List<CandidateEducationResDto> getCandidateEducations() {
		return candidateEducations;
	}
	public List<CandidateFamilyResDto> getCandidateFamily() {
		return candidateFamily;
	}
	public List<CandidateLanguageResDto> getCandidateLanguage() {
		return candidateLanguage;
	}
	public List<CandidateProjectExpResDto> getCandidateProjectExp() {
		return candidateProjectExp;
	}
	public List<CandidateReferencesResDto> getCandidateReferences() {
		return candidateReferences;
	}
	public List<CandidateSkillResDto> getCandidateSkill() {
		return candidateSkill;
	}
	public List<CandidateTrainingExpResDto> getCandidateTrainingExp() {
		return candidateTrainingExp;
	}
	public List<CandidateWorkExpResDto> getCandidateWorkExp() {
		return candidateWorkExp;
	}

	
}

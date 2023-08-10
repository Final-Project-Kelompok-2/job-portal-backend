package com.lawencon.jobportalcandidate.dto.candidate;

import java.util.List;

import com.lawencon.jobportalcandidate.dto.candidateaddress.CandidateAddressUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidatedocument.CandidateDocumentUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidateeducation.CandidateEducationUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidatefamily.CandidateFamilyUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidatelanguage.CandidateLanguageUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidateprofile.CandidateProfileUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidateprojectexp.CandidateProjectExpUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidatereferences.CandidateReferencesUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidateskill.CandidateSkillUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidatetrainingexp.CandidateTrainingExpUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidateuser.CandidateUserUpdateReqDto;
import com.lawencon.jobportalcandidate.dto.candidateworkexp.CandidateWorkExpUpdateReqDto;

public class CandidateMasterUpdateReqDto {
	private CandidateUserUpdateReqDto candidateUser;
	private CandidateProfileUpdateReqDto candidateProfile;
	private CandidateAddressUpdateReqDto candidateAddress;
	private List<CandidateDocumentUpdateReqDto> candidateDocuments;
	private List<CandidateEducationUpdateReqDto> candidateEducations;
	private List<CandidateFamilyUpdateReqDto> candidateFamily;
	private List<CandidateLanguageUpdateReqDto> candidateLanguage;
	private List<CandidateProjectExpUpdateReqDto> candidateProjectExp;
	private List<CandidateReferencesUpdateReqDto> candidateReferences;
	private List<CandidateSkillUpdateReqDto> candidateSkill;
	private List<CandidateTrainingExpUpdateReqDto> candidateTrainingExp;
	private List<CandidateWorkExpUpdateReqDto> candidateWorkExp;

	public CandidateProfileUpdateReqDto getCandidateProfile() {
		return candidateProfile;
	}

	public CandidateAddressUpdateReqDto getCandidateAddress() {
		return candidateAddress;
	}

	public List<CandidateDocumentUpdateReqDto> getCandidateDocuments() {
		return candidateDocuments;
	}

	public List<CandidateEducationUpdateReqDto> getCandidateEducations() {
		return candidateEducations;
	}

	public List<CandidateFamilyUpdateReqDto> getCandidateFamily() {
		return candidateFamily;
	}

	public List<CandidateLanguageUpdateReqDto> getCandidateLanguage() {
		return candidateLanguage;
	}

	public List<CandidateProjectExpUpdateReqDto> getCandidateProjectExp() {
		return candidateProjectExp;
	}

	public List<CandidateReferencesUpdateReqDto> getCandidateReferences() {
		return candidateReferences;
	}

	public List<CandidateSkillUpdateReqDto> getCandidateSkill() {
		return candidateSkill;
	}

	public List<CandidateTrainingExpUpdateReqDto> getCandidateTrainingExp() {
		return candidateTrainingExp;
	}

	public List<CandidateWorkExpUpdateReqDto> getCandidateWorkExp() {
		return candidateWorkExp;
	}
}

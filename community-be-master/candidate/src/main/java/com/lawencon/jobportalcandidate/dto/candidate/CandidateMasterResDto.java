package com.lawencon.jobportalcandidate.dto.candidate;

import java.util.List;

import com.lawencon.jobportalcandidate.model.CandidateAddress;
import com.lawencon.jobportalcandidate.model.CandidateDocuments;
import com.lawencon.jobportalcandidate.model.CandidateEducation;
import com.lawencon.jobportalcandidate.model.CandidateFamily;
import com.lawencon.jobportalcandidate.model.CandidateLanguage;
import com.lawencon.jobportalcandidate.model.CandidateProfile;
import com.lawencon.jobportalcandidate.model.CandidateProjectExp;
import com.lawencon.jobportalcandidate.model.CandidateReferences;
import com.lawencon.jobportalcandidate.model.CandidateSkill;
import com.lawencon.jobportalcandidate.model.CandidateTrainingExp;
import com.lawencon.jobportalcandidate.model.CandidateWorkExp;

public class CandidateMasterResDto {
	private CandidateProfile candidateProfile;
	private List<CandidateAddress> candidateAddress;
	private List<CandidateDocuments> candidateDocuments;
	private List<CandidateEducation> candidateEducations;
	private List<CandidateFamily> candidateFamily;
	private List<CandidateLanguage> candidateLanguage;
	private List<CandidateProjectExp> candidateProjectExp;
	private List<CandidateReferences> candidateReferences;
	private List<CandidateSkill> candidateSkill;
	private List<CandidateTrainingExp> candidateTrainingExp;
	private List<CandidateWorkExp> candidateWorkExp;

	public CandidateProfile getCandidateProfile() {
		return candidateProfile;
	}

	public List<CandidateAddress> getCandidateAddress() {
		return candidateAddress;
	}

	public void setCandidateAddress(List<CandidateAddress> candidateAddress) {
		this.candidateAddress = candidateAddress;
	}

	public List<CandidateDocuments> getCandidateDocuments() {
		return candidateDocuments;
	}

	public List<CandidateEducation> getCandidateEducations() {
		return candidateEducations;
	}

	public void setCandidateProfile(CandidateProfile candidateProfile) {
		this.candidateProfile = candidateProfile;
	}

	public void setCandidateDocuments(List<CandidateDocuments> candidateDocuments) {
		this.candidateDocuments = candidateDocuments;
	}

	public void setCandidateEducations(List<CandidateEducation> candidateEducations) {
		this.candidateEducations = candidateEducations;
	}

	public void setCandidateFamily(List<CandidateFamily> candidateFamily) {
		this.candidateFamily = candidateFamily;
	}

	public void setCandidateLanguage(List<CandidateLanguage> candidateLanguage) {
		this.candidateLanguage = candidateLanguage;
	}

	public void setCandidateProjectExp(List<CandidateProjectExp> candidateProjectExp) {
		this.candidateProjectExp = candidateProjectExp;
	}

	public void setCandidateReferences(List<CandidateReferences> candidateReferences) {
		this.candidateReferences = candidateReferences;
	}

	public void setCandidateSkill(List<CandidateSkill> candidateSkill) {
		this.candidateSkill = candidateSkill;
	}

	public void setCandidateTrainingExp(List<CandidateTrainingExp> candidateTrainingExp) {
		this.candidateTrainingExp = candidateTrainingExp;
	}

	public void setCandidateWorkExp(List<CandidateWorkExp> candidateWorkExp) {
		this.candidateWorkExp = candidateWorkExp;
	}

	public List<CandidateFamily> getCandidateFamily() {
		return candidateFamily;
	}

	public List<CandidateLanguage> getCandidateLanguage() {
		return candidateLanguage;
	}

	public List<CandidateProjectExp> getCandidateProjectExp() {
		return candidateProjectExp;
	}

	public List<CandidateReferences> getCandidateReferences() {
		return candidateReferences;
	}

	public List<CandidateSkill> getCandidateSkill() {
		return candidateSkill;
	}

	public List<CandidateTrainingExp> getCandidateTrainingExp() {
		return candidateTrainingExp;
	}

	public List<CandidateWorkExp> getCandidateWorkExp() {
		return candidateWorkExp;
	}

}

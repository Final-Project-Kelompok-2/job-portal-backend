package com.lawencon.jobportaladmin.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_hired")
public class Hired {
	
	@OneToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;
	
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	
	
	@Column(name = "end_date", nullable = true)
	private LocalDate endDate;
	

}

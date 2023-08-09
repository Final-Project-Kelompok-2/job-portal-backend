package com.lawencon.jobportaladmin.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_mcu")
public class Mcu {

	@OneToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
}

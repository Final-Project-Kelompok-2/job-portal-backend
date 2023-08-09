package com.lawencon.jobportalcandidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="t_person_type")
public class PersonType {

	@Column(name ="type_code",  nullable = false)
	private String typeCode;
	
	@Column(name ="type_name",  nullable = false)
	private String typeName;
	
}
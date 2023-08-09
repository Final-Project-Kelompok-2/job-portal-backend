package com.lawencon.jobportalcandidate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name ="t_person_type")
public class PersonType extends BaseEntity {

	@Column(name ="type_code",length = 5,  nullable = false)
	private String typeCode;
	
	@Column(name ="type_name",length = 20,  nullable = false)
	private String typeName;
	
}

package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_file_type")
public class FileType {
	
	@Column(name = "type_code",length = 5, nullable = false)
	private String typeCode;
	
	@Column(name = "type_name",length = 20, nullable = false)
	private String typeName;

}

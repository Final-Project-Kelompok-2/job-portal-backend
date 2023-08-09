package com.lawencon.jobportaladmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_file")
public class File extends BaseEntity{
	
	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@Column(name = "file_extension",length = 5, nullable = false)
	private String fileExtension;

}

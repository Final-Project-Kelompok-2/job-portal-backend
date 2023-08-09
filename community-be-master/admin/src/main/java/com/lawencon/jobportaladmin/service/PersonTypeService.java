package com.lawencon.jobportaladmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.jobportaladmin.dao.PersonTypeDao;
import com.lawencon.jobportaladmin.dto.persontype.PersonTypeGetResDto;
import com.lawencon.jobportaladmin.model.PersonType;

@Service
public class PersonTypeService {

	@Autowired
	private PersonTypeDao personTypeDao;

	public List<PersonTypeGetResDto> getPersonTypes() {
		final List<PersonTypeGetResDto> personTypesDto = new ArrayList<>();
		final List<PersonType> personTypes = personTypeDao.getAll(PersonType.class);

		for (int i = 0; i < personTypes.size(); i++) {
			final PersonTypeGetResDto personTypeDto = new PersonTypeGetResDto();
			personTypeDto.setId(personTypes.get(i).getId());
			personTypeDto.setTypeName(personTypes.get(i).getTypeName());
			personTypesDto.add(personTypeDto);
		}

		return personTypesDto;

	}

}

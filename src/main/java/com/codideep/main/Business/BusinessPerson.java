package com.codideep.main.Business;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codideep.main.Dto.DtoPerson;
import com.codideep.main.Entity.TPerson;
import com.codideep.main.Repository.RepoPerson;

@Service
public class BusinessPerson {
	@Autowired
	private RepoPerson repoPerson;

	public void insert(DtoPerson dtoPerson) {
		dtoPerson.setIdPerson(UUID.randomUUID().toString());
		dtoPerson.setCreatedAt(new Date());
		dtoPerson.setUpdatedAt(new Date());

		TPerson tPerson = new TPerson();

		tPerson.setIdPerson(dtoPerson.getIdPerson());
		tPerson.setFirstName(dtoPerson.getFirstName());
		tPerson.setSurName(dtoPerson.getSurName());
		tPerson.setDni(dtoPerson.getDni());
		tPerson.setGender(dtoPerson.isGender());
		tPerson.setBirthDate(dtoPerson.getBirthDate());
		tPerson.setCreatedAt(dtoPerson.getCreatedAt());
		tPerson.setUpdatedAt(dtoPerson.getUpdatedAt());

		repoPerson.save(tPerson);
	}
}
package com.codideep.main.Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codideep.main.Dto.DtoPerson;
import com.codideep.main.Entity.TPerson;
import com.codideep.main.Helper.AesUtil;
import com.codideep.main.Helper.JwtUtil;
import com.codideep.main.Repository.RepoPerson;

import jakarta.transaction.Transactional;

@Service
public class BusinessPerson {
	@Autowired
	private RepoPerson repoPerson;

	@Transactional
	public void insert(DtoPerson dtoPerson) throws Exception {
		dtoPerson.setIdPerson(UUID.randomUUID().toString());
		dtoPerson.setCreatedAt(new Date());
		dtoPerson.setUpdatedAt(new Date());

		TPerson tPerson = new TPerson();

		tPerson.setIdPerson(dtoPerson.getIdPerson());
		tPerson.setFirstName(dtoPerson.getFirstName());
		tPerson.setSurName(dtoPerson.getSurName());
		tPerson.setDni(dtoPerson.getDni());
		tPerson.setPassword(AesUtil.encrypt(dtoPerson.getPassword()));
		tPerson.setGender(dtoPerson.isGender());
		tPerson.setBirthDate(dtoPerson.getBirthDate());
		tPerson.setCreatedAt(dtoPerson.getCreatedAt());
		tPerson.setUpdatedAt(dtoPerson.getUpdatedAt());

		repoPerson.save(tPerson);
	}

	public DtoPerson getByIdPerson(String idPerson) throws Exception {
		Optional<TPerson> tPerson = repoPerson.findById(idPerson);

		DtoPerson dtoPerson = null;

		if(tPerson.isPresent()) {
			dtoPerson = new DtoPerson();

			dtoPerson.setIdPerson(tPerson.get().getIdPerson());
			dtoPerson.setFirstName(tPerson.get().getFirstName());
			dtoPerson.setSurName(tPerson.get().getSurName());
			dtoPerson.setDni(tPerson.get().getDni());
			dtoPerson.setGender(tPerson.get().isGender());
			dtoPerson.setBirthDate(tPerson.get().getBirthDate());
			dtoPerson.setCreatedAt(tPerson.get().getCreatedAt());
			dtoPerson.setUpdatedAt(tPerson.get().getUpdatedAt());
		}

		return dtoPerson;
	}

	public List<DtoPerson> getAll() {
		List<TPerson> listTPerson = repoPerson.findAll();

		List<DtoPerson> listDtoPerson = new ArrayList<>();

		for (TPerson item : listTPerson) {
			DtoPerson dtoPerson = new DtoPerson();

			dtoPerson.setIdPerson(item.getIdPerson());
			dtoPerson.setFirstName(item.getFirstName());
			dtoPerson.setSurName(item.getSurName());
			dtoPerson.setDni(item.getDni());
			dtoPerson.setGender(item.isGender());
			dtoPerson.setBirthDate(item.getBirthDate());
			dtoPerson.setCreatedAt(item.getCreatedAt());
			dtoPerson.setUpdatedAt(item.getUpdatedAt());

			listDtoPerson.add(dtoPerson);
		}

		return listDtoPerson;
	}

	@Transactional
	public boolean delete(String idPerson) {
		Optional<TPerson> tPerson = repoPerson.findById(idPerson);

		if (tPerson.isPresent()) {
			repoPerson.deleteById(idPerson);
		}

		return true;
	}

	public DtoPerson login(String dni, String password) throws Exception {
		Optional<TPerson> tPerson = repoPerson.getByDni(dni);

		DtoPerson dtoPerson = null;

		if(tPerson.isPresent() && AesUtil.decrypt(tPerson.get().getPassword()).equals(password)) {
			dtoPerson = new DtoPerson();

			dtoPerson.setIdPerson(tPerson.get().getIdPerson());
			dtoPerson.setFirstName(tPerson.get().getFirstName());
			dtoPerson.setSurName(tPerson.get().getSurName());
			dtoPerson.setDni(tPerson.get().getDni());
			dtoPerson.setGender(tPerson.get().isGender());
			dtoPerson.setBirthDate(tPerson.get().getBirthDate());
			dtoPerson.setCreatedAt(tPerson.get().getCreatedAt());
			dtoPerson.setUpdatedAt(tPerson.get().getUpdatedAt());

			dtoPerson.setJwtToken(new JwtUtil().generateToken(dtoPerson.getIdPerson(), dtoPerson.getFirstName()));
		}

		return dtoPerson;
	}
}
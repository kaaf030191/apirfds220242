package com.codideep.main.Service.Person;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codideep.main.Business.BusinessPerson;
import com.codideep.main.Dto.DtoPerson;
import com.codideep.main.Service.Person.RequestObject.RequestInsert;
import com.codideep.main.Service.Person.ResponseObject.ResponseGetAll;
import com.codideep.main.Service.Person.ResponseObject.ResponseGetData;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("person")
public class PersonController {
	@Autowired
	private BusinessPerson businessPerson;

	@GetMapping(path = "getdata")
	public ResponseEntity<ResponseGetData> getData() {
		ResponseGetData responseGetData = new ResponseGetData();

		responseGetData.firstName = "Kevin Arnold";
		responseGetData.surName = "Arias Figueroa";
		responseGetData.dni = "77777777";

		return new ResponseEntity<>(responseGetData, HttpStatus.OK);
	}

	@PostMapping(path = "insert", consumes = "multipart/form-data")
	public ResponseEntity<String> insert(@ModelAttribute RequestInsert request) {
		try {
			DtoPerson dtoPerson = new DtoPerson();

			dtoPerson.setFirstName(request.getFirstName());
			dtoPerson.setSurName(request.getSurName());
			dtoPerson.setDni(request.getDni());
			dtoPerson.setGender(request.isGender());
			dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-mm-dd").parse(request.getBirthDate()));

			businessPerson.insert(dtoPerson);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Registro realizado correctamente.", HttpStatus.CREATED);
	}

	@GetMapping(path = "getall")
	public ResponseEntity<ResponseGetAll> getAll() {
		ResponseGetAll responseGetAll = new ResponseGetAll();

		List<DtoPerson> listDtoPerson = businessPerson.getAll();

		for (DtoPerson item : listDtoPerson) {
			Map<String, Object> map = new HashMap<>();

			map.put("idPerson", item.getIdPerson());
			map.put("firstName", item.getFirstName());
			map.put("surName", item.getSurName());
			map.put("dni", item.getDni());
			map.put("gender", item.isGender());
			map.put("birthDate", item.getBirthDate());
			map.put("createdAt", item.getCreatedAt());
			map.put("updatedAt", item.getUpdatedAt());

			responseGetAll.dto.listPerson.add(map);
		}

		return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
	}
}
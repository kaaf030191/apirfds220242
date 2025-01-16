package com.codideep.main.Service.Person;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codideep.main.Business.BusinessPerson;
import com.codideep.main.Dto.DtoPerson;
import com.codideep.main.Service.Person.RequestObject.RequestInsert;
import com.codideep.main.Service.Person.RequestObject.RequestLogin;
import com.codideep.main.Service.Person.ResponseObject.ResponseDelete;
import com.codideep.main.Service.Person.ResponseObject.ResponseGetAll;
import com.codideep.main.Service.Person.ResponseObject.ResponseGetData;
import com.codideep.main.Service.Person.ResponseObject.ResponseInsert;
import com.codideep.main.Service.Person.ResponseObject.ResponseLogin;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("person")
public class PersonController {
	@Autowired
	private BusinessPerson businessPerson;

	@GetMapping(path = "getdata")
	public ResponseEntity<ResponseGetData> getData() {
		ResponseGetData responseGetData = new ResponseGetData();

		try {
			responseGetData.firstName = "Kevin Arnold";
			responseGetData.surName = "Arias Figueroa";
			responseGetData.dni = "77777777";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(responseGetData, HttpStatus.OK);
	}

	@PostMapping(path = "insert", consumes = "multipart/form-data")
	public ResponseEntity<ResponseInsert> insert(@Valid @ModelAttribute RequestInsert request, BindingResult bindingResult) {
		ResponseInsert response = new ResponseInsert();

		try {
			if (bindingResult.hasErrors()) {
				bindingResult.getAllErrors().forEach(error -> {
					response.mo.addMessage(error.getDefaultMessage());
				});

				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			DtoPerson dtoPerson = new DtoPerson();

			dtoPerson.setFirstName(request.getFirstName());
			dtoPerson.setSurName(request.getSurName());
			dtoPerson.setDni(request.getDni());
			dtoPerson.setPassword(request.getPassword());
			dtoPerson.setGender(request.isGender());
			dtoPerson.setBirthDate(new SimpleDateFormat("yyyy-mm-dd").parse(request.getBirthDate()));

			businessPerson.insert(dtoPerson);

			response.mo.addMessage("Registro realizado correctamente");
			response.mo.setSuccess();
		} catch (Exception e) {
			response.mo.addMessage("Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping(path = "getall")
	public ResponseEntity<ResponseGetAll> getAll() {
		ResponseGetAll response = new ResponseGetAll();

		try {
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

				response.dto.listPerson.add(map);

				response.mo.setSuccess();
			}
		} catch (Exception e) {
			response.mo.addMessage("Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path = "delete/{idPerson}")
	public ResponseEntity<ResponseDelete> delete(@PathVariable String idPerson) {
		ResponseDelete response = new ResponseDelete();

		try {
			businessPerson.delete(idPerson);

			response.mo.addMessage("Eliminación realizada correctamente");
			response.mo.setSuccess();
		} catch (Exception e) {
			response.mo.addMessage("Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "login", consumes = "multipart/form-data")
	public ResponseEntity<ResponseLogin> login(@ModelAttribute RequestLogin request) {
		ResponseLogin response = new ResponseLogin();

		try {
			DtoPerson dtoPerson = businessPerson.login(request.getDni(), request.getPassword());

			if(dtoPerson != null) {
				Map<String, Object> map = new HashMap<>();

				map.put("idPerson", dtoPerson.getIdPerson());
				map.put("firstName", dtoPerson.getFirstName());
				map.put("surName", dtoPerson.getSurName());
				map.put("dni", dtoPerson.getDni());
				map.put("gender", dtoPerson.isGender());
				map.put("birthDate", dtoPerson.getBirthDate());
				map.put("createdAt", dtoPerson.getCreatedAt());
				map.put("updatedAt", dtoPerson.getUpdatedAt());

				response.dto.person = map;

				response.mo.addMessage("Bienvenido(a) al sistema: " + dtoPerson.getFirstName());
				response.mo.setSuccess();
			} else {
				response.mo.addMessage("Usuario o contraseña incorrecto.");
			}
		} catch (Exception e) {
			response.mo.addMessage("Ocurrió un error inesperado, estamos trabajando para resolvero. Gracias por su paciencia.");
			response.mo.setException();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
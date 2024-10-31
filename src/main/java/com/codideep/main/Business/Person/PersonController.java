package com.codideep.main.Business.Person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codideep.main.Business.Person.ResponseObject.ResponseGetData;

@RestController
@RequestMapping("person")
public class PersonController {
	@GetMapping(path = "getdata")
	public ResponseEntity<ResponseGetData> getData() {
		ResponseGetData responseGetData = new ResponseGetData();

		responseGetData.firstName = "Kevin Arnold";
		responseGetData.surName = "Arias Figueroa";
		responseGetData.dni = "77777777";

		return new ResponseEntity<>(responseGetData, HttpStatus.OK);
	}
}
package com.codideep.main.Service.Person.RequestObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestInsert {
	private String firstName;
	private String surName;
	private String dni;
	private boolean gender;
	private String birthDate;
}
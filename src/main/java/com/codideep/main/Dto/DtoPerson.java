package com.codideep.main.Dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoPerson {
	private String idPerson;
	private String firstName;
	private String surName;
	private String dni;
	private String password;
	private boolean gender;
	private Date birthDate;
	private Date createdAt;
	private Date updatedAt;

	private String jwtToken;
}
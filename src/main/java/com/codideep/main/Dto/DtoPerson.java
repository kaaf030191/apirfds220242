package com.codideep.main.Dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DtoPerson {
	private String idPerson;
	private String firstName;
	private String surName;
	private String dni;
	private Boolean gender;
	private Date birthDate;
	private Date createdAt;
	private Date updatedAt;
}
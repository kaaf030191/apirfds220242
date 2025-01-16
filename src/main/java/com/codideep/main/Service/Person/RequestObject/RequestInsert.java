package com.codideep.main.Service.Person.RequestObject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestInsert {
	@NotBlank(message = "El campo \"firstName\" es requerido")
	private String firstName;
	@NotBlank(message = "El campo \"surName\" es requerido")
	private String surName;
	@NotBlank(message = "El campo \"dni\" es requerido")
	private String dni;
	@NotBlank(message = "El campo \"password\" es requerido")
	private String password;
	@NotNull(message = "El campo \"firstName\" es requerido")
	private boolean gender;
	@NotBlank(message = "El campo \"birthDate\" es requerido")
	private String birthDate;
}
package com.codideep.main.Entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tperson")
@NoArgsConstructor
@Getter
@Setter
public class TPerson implements Serializable {
	@Id
	@Column(name = "idPerson")
	private String idPerson;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "surName")
	private String surName;

	@Column(name = "dni")
	private String dni;

	@Column(name = "gender")
	private Boolean gender;

	@Column(name = "birthDate")
	private Date birthDate;

	@Column(name = "createdAt")
	private Date createdAt;

	@Column(name = "updatedAt")
	private Date updatedAt;
}
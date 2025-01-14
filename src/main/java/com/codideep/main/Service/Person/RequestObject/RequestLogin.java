package com.codideep.main.Service.Person.RequestObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLogin {
	private String dni;
	private String password;
}
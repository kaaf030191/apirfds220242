package com.codideep.main.Service.Person.ResponseObject;

import java.util.ArrayList;
import java.util.List;

import com.codideep.main.Service.Generic.ResponseGeneric;

public class ResponseGetAll extends ResponseGeneric {
	public class Response {
		public List<Object> listPerson = new ArrayList<>();
	}

	public Response dto = new Response();
}
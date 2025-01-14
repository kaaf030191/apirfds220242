package com.codideep.main.Service.Person.ResponseObject;

import com.codideep.main.Service.Generic.ResponseGeneric;

public class ResponseLogin extends ResponseGeneric {
	public class Response {
		public Object person = null;
	}

	public Response dto = new Response();
}

package com.codideep.main.Dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class MessageObject {
	@Getter
	private String type;
	@Getter
	private List<String> listMessage;

	public MessageObject() {
		this.type = "error";
		this.listMessage = new ArrayList<>();
	}

	public boolean existsMessage() {
		return this.listMessage.size() > 0;
	}

	public void addMessage(String message) {
		this.listMessage.add(message);
	}

	public void setSuccess() {
		this.type = "success";
	}

	public void setWarning() {
		this.type = "warning";
	}

	public void setError() {
		this.type = "error";
	}

	public void setException() {
		this.type = "exception";
	}
}
package com.v2stech.bulkupload.bean;

import lombok.Data;

@Data
public class ExceptionDetails {

	private String message;

	public ExceptionDetails(String message) {
		super();
		this.message = message;
	}
}

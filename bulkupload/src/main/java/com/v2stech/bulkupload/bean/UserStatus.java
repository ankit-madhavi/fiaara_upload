package com.v2stech.bulkupload.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserStatus {

	private String userStatusCode;
	
	private String description;
}

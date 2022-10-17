package com.v2stech.bulkupload.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class User {

	private int userId;
	
	private String username;
	
	private String forename;
	
	private String familyNname;
	
	private String email;
	
	private String pincode;
	
	private String typeName;
	
	private String userStatusCode;
	
}

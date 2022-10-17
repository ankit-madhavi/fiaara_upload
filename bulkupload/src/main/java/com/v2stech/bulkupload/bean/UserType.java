package com.v2stech.bulkupload.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserType {

	private int id;
	
	private String typeName;
}

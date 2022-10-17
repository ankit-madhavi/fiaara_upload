package com.v2stech.bulkupload.dao.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.v2stech.bulkupload.dao.UploadDao;
import com.v2stech.bulkuploadrepository.UploadRepository;

public class UploadDaoImpl implements UploadDao{

	@Autowired
	UploadRepository repository;
	
	@Override
	public int findUserTypeIdByTypeName(String typeName) {
		return repository.findByTypeName(typeName).getId();
	}
	
}

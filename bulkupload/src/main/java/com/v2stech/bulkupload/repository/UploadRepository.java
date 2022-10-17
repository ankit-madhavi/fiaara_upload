package com.v2stech.bulkupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v2stech.bulkupload.entity.UserTypeEntity;

@Repository
public interface UploadRepository extends JpaRepository<UserTypeEntity, Long>{

	UserTypeEntity findByTypeName(String typeName);
	
}

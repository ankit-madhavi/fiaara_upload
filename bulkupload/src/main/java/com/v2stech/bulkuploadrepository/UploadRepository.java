package com.v2stech.bulkuploadrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v2stech.bulkupload.entity.UserTypeEntity;

public interface UploadRepository extends JpaRepository<UserTypeEntity, Long>{

	UserTypeEntity findByTypeName(String typeName);
}

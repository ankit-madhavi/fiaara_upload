package com.v2stech.bulkupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v2stech.bulkupload.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByForenameAndFamilyName(String forename, String familyName);
	
}

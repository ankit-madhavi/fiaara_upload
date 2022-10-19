package com.v2stech.bulkupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v2stech.bulkupload.entity.RegionEntity;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

	RegionEntity findByRegionName(String regionName);

}

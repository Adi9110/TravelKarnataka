package com.travel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travel.entity.PackageEntity;


public interface PackageService {
	
	boolean isExist(String pname);
	
	int addPackage(PackageEntity p);
	
	List<PackageEntity> getAllPackages();
	
	PackageEntity findPackageById(Integer id);
	

}

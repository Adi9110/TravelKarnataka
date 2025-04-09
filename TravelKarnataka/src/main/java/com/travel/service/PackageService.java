package com.travel.service;

import com.travel.entity.PackageEntity;

public interface PackageService {
	
	boolean isExist(String pname);
	
	int addPackage(PackageEntity p);

}

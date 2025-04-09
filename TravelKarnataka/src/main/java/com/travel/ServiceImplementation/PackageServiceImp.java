package com.travel.ServiceImplementation;

import com.travel.entity.PackageEntity;
import com.travel.repository.PackageRepo;
import com.travel.service.PackageService;

public class PackageServiceImp implements PackageService {
	
	private PackageRepo prepo;

	@Override
	public int addPackage(PackageEntity p) {
		return prepo.save(p).getPid();
	}

	@Override
	public boolean isExist(String pname) {
		return prepo.existByPname(pname) ;
	}

}

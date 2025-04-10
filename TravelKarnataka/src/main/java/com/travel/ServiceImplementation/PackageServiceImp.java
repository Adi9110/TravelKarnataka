package com.travel.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.PackageEntity;
import com.travel.repository.PackageRepo;
import com.travel.service.PackageService;

@Service
public class PackageServiceImp implements PackageService {
	
	@Autowired
	private PackageRepo prepo;

	@Override
	public int addPackage(PackageEntity p) {
		return prepo.save(p).getPid();
	}

	@Override
	public boolean isExist(String pname) {
		return prepo.existsByPname(pname) ;
	}
	
	@Override
    public List<PackageEntity> getAllPackages() {
        return prepo.findAll();
    }

	@Override
	public PackageEntity findPackageById(Integer id) {
		// TODO Auto-generated method stub
		return prepo.findById(id).get();
	}

	@Override
	public void updatePackage(PackageEntity p) {
		// TODO Auto-generated method stub
	    prepo.save(p);
	}

	@Override
	public void deletePackage(Integer id) {
		// TODO Auto-generated method stub
		if(prepo.existsById(id)) {
			prepo.deleteById(id);
	    }
	}
	
}
	


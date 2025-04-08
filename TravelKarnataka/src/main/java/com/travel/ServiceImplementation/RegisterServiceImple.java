package com.travel.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.RegisterEntity;
import com.travel.repository.RegisterRepoistory;
import com.travel.service.RegisterService;

@Service
public class RegisterServiceImple implements RegisterService {
	
	@Autowired
	private RegisterRepoistory repo;

	@Override
	public Integer saveUser(RegisterEntity entity) {
		return repo.save(entity).getUserId();
	}

	@Override
	public boolean checkUser(String email) {
		return repo.existsByUserEmail(email);	
	}

	@Override
	public RegisterEntity getUser(String email) {
		return repo.findByEmail(email).orElse(null);
	}

}

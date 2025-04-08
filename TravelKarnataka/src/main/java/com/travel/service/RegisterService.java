package com.travel.service;

import com.travel.entity.RegisterEntity;

public interface RegisterService {
	Integer saveUser(RegisterEntity entity);
	boolean checkUser(String email);
	RegisterEntity getUser(String email);
}

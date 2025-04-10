package com.travel.service;

import com.travel.entity.RegisterEntity;

import jakarta.servlet.http.HttpSession;

public interface RegisterService {
	Integer saveUser(RegisterEntity entity);
	boolean checkUser(String email);
	RegisterEntity getUser(String email, String password, HttpSession session);
}

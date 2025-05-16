package com.travel.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.RegisterEntity;
import com.travel.repository.RegisterRepoistory;
import com.travel.service.RegisterService;

import jakarta.servlet.http.HttpSession;

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
	public RegisterEntity getUser(String email, String password, HttpSession session) {
	    RegisterEntity user = repo.findByUserEmail(email);

	    if (user != null && user.getUserPassword().equals(password)) {
	        session.setAttribute("umail", user.getUserEmail());
	        session.setAttribute("uname", user.getUserName());
	        session.setAttribute("uphone", user.getUserPhone());
	        
	        System.out.println("User logged in: " + user.getUserName() + " with email: " + user.getUserEmail());
	        
	        return user;
	    }
	    return null;
	}

	@Override
	public List<RegisterEntity> getAllUsers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public RegisterEntity getUserById(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		RegisterEntity user = repo.findById(id).get();
		
		if(user!=null) {
			repo.deleteById(id);
		}
	}

}

package com.travel.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.Contact;
import com.travel.repository.contactRepo;
import com.travel.service.ContactService;
@Service
public class contactImpl implements ContactService{

	@Autowired
	private contactRepo crepo;
	@Override
	public Integer add(Contact con) {
		// TODO Auto-generated method stub
		return crepo.save(con).getId();
	}

}

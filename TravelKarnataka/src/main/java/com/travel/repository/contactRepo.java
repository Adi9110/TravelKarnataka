package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.Contact;

public interface contactRepo extends JpaRepository<Contact, Integer>{

}

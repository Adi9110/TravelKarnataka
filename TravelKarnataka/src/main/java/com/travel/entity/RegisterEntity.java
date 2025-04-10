package com.travel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class RegisterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	@Column(unique=true, nullable=false)
	private String userEmail;
	private String userPhone;
	private String userPassword;
	private String role;
	
	@PrePersist
	public void addRole()
	{
		if(role==null)
			role="user";
	}

}

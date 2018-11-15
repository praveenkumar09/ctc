package com.censof.myfi.hidefmyfi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "emailaddress")
	private String email;
	
	
	@Column(name = "password")	
	@Transient
	private String password;
	
	
	@Column(name = "username")
	private String name;
	
	
	@Column(name = "status")
	private int status;
	
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "mycbcid")
	private String myCbcId;
	
	@Column(name = "messagetype")
	private String messageType;
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getMyCbcId() {
		return myCbcId;
	}


	public void setMyCbcId(String myCbcId) {
		this.myCbcId = myCbcId;
	}


	public String getMessageType() {
		return messageType;
	}


	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	
	
	

}

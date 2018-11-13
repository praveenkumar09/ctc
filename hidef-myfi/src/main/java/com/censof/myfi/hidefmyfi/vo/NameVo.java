package com.censof.myfi.hidefmyfi.vo;

import java.io.Serializable;

/**
 * 
 * @author CSM-Ven
 * 
 */
public class NameVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private int nameType;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getNameType() {
		return nameType;
	}
	public void setNameType(int nameType) {
		this.nameType = nameType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}

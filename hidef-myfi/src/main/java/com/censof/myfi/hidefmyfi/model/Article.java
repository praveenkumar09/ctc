package com.censof.myfi.hidefmyfi.model;

import java.io.Serializable;

public class Article implements Serializable {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String addressFree;

	public String getAddressFree() {
		return addressFree;
	}

	public void setAddressFree(String addressFree) {
		this.addressFree = addressFree;
	}
}

package com.censof.myfi.hidefmyfi.vo;

public class UserVo {
    private Long id;
    private String myCBCId;
    private String username;
    private String password;
    private String passwordConfirm;
    private String  token;    
    private int status;
    private String email;
    private String messageType;
    private String myCRSId;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    /**
	 * @return the myCBCId
	 */
	public String getMyCBCId() {
		return myCBCId;
	}

	/**
	 * @param myCBCId the myCBCId to set
	 */
	public void setMyCBCId(String myCBCId) {
		this.myCBCId = myCBCId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMyCRSId() {
		return myCRSId;
	}

	public void setMyCRSId(String myCRSId) {
		this.myCRSId = myCRSId;
	}
	
	


    
    
}

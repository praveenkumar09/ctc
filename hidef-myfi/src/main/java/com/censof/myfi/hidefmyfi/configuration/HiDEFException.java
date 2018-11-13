/*
 * Copyright (c) Axway Software, 2015. All Rights Reserved.
 * 
 * This exception is for FATCA Compression module.
 * 
 * @author	John Goh
 * @author 	Jack Phuah
 * @author 	Petter Hedlin
 */

package com.censof.myfi.hidefmyfi.configuration;

public class HiDEFException extends Exception 
{

	private static final long serialVersionUID = 2261667500362976496L;

	private String errorMessage;
	private String returncode;
	private String errorRefId;
	
	private boolean bIsFatal = false;
	
	private String errorMessages[];
	private String errorCodes[];
	private int iErrorCount = 0;

	/**
	 * This is an Exception class that used for error that is related to the
	 * custom compression with the FATCA IDES.
	 */
	public HiDEFException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * FATCA Exception to encapsulate exception during the running of the
	 * modules functionality.
	 * 
	 * @param returncode
	 * @param errorMessage
	 */
	public HiDEFException(String []errorMessages, String []errorCodes, int iErrorCount, boolean bIsFatal) 
	{
		this.errorCodes = errorCodes;
		this.errorMessages = errorMessages;
		this.iErrorCount = iErrorCount;
		this.bIsFatal = bIsFatal;
	}


	public HiDEFException(String []errorMessages, String []errorCodes, int iErrorCount) 
	{
		this.errorCodes = errorCodes;
		this.errorMessages = errorMessages;
		this.iErrorCount = iErrorCount;
	}


	public HiDEFException(String errorMessage, String returnCode) {
		this.returncode = returnCode;
		this.errorMessage = errorMessage;
	}
	
	public HiDEFException(String errorMessage, String returnCode,String errorRefId) {
		this.returncode = returnCode;
		this.errorMessage = errorMessage;
		this.errorRefId = errorRefId;
	}
	


	
	public String [] getErrorMsgs ()
	{
		return errorMessages;
	}
	
	public String [] getErrorCodes ()
	{
		return errorCodes;
	}
	
	public int getErrorCount () 
	{
		return iErrorCount;
	}
	
	public boolean isFatal ()
	{
		return bIsFatal;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getReturncode() {
		return returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getErrorRefId() {
		return errorRefId;
	}

	public void setErrorRefId(String errorRefId) {
		this.errorRefId = errorRefId;
	}

}

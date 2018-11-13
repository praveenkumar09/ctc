package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the cbcsmtrans database table.
 * 
 */
@Entity
@Table(name="cbcsmtrans")
@NamedQuery(name="Cbcsmtran.findAll", query="SELECT c FROM Cbcsmtran c")
public class Cbcsmtran implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String createDate;

	private String docRefIDInError;

	private String errorCode;

	private String errorType;

	@Lob
	private String fieldPath;

	@Lob
	private String fieldsInError;

	private String messageRefId;

	private String msgTransmittingCountry;

	private BigInteger sessionID;

	private String transmissionID;

	public Cbcsmtran() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getDocRefIDInError() {
		return this.docRefIDInError;
	}

	public void setDocRefIDInError(String docRefIDInError) {
		this.docRefIDInError = docRefIDInError;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorType() {
		return this.errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getFieldPath() {
		return this.fieldPath;
	}

	public void setFieldPath(String fieldPath) {
		this.fieldPath = fieldPath;
	}

	public String getFieldsInError() {
		return this.fieldsInError;
	}

	public void setFieldsInError(String fieldsInError) {
		this.fieldsInError = fieldsInError;
	}

	public String getMessageRefId() {
		return this.messageRefId;
	}

	public void setMessageRefId(String messageRefId) {
		this.messageRefId = messageRefId;
	}

	public String getMsgTransmittingCountry() {
		return this.msgTransmittingCountry;
	}

	public void setMsgTransmittingCountry(String msgTransmittingCountry) {
		this.msgTransmittingCountry = msgTransmittingCountry;
	}

	public BigInteger getSessionID() {
		return this.sessionID;
	}

	public void setSessionID(BigInteger sessionID) {
		this.sessionID = sessionID;
	}

	public String getTransmissionID() {
		return this.transmissionID;
	}

	public void setTransmissionID(String transmissionID) {
		this.transmissionID = transmissionID;
	}

}
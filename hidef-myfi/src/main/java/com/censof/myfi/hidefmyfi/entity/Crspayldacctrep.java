package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the crspayldacctrep database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldacctrep.findAll", query="SELECT c FROM Crspayldacctrep c")
public class Crspayldacctrep implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CrspayldacctrepPK id;

	private BigDecimal accountBalance;

	private String accountCurrCode;

	private String accountHolder;

	private String accountNumber;

	private String acctHolderType;

	private String acctNumberType;

	private String birthCity;

	private String birthCitySubent;

	private String birthCountry;

	private String birthDate;

	private String birthFormerCountry;

	private String closedAccount;

	private String corrDocRefId;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String docRefId;

	private String docTypeIndic;

	private String dormantAccount;

	private BigInteger hdrID;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private String undocumentedAccount;

	public Crspayldacctrep() {
	}

	public CrspayldacctrepPK getId() {
		return this.id;
	}

	public void setId(CrspayldacctrepPK id) {
		this.id = id;
	}

	public BigDecimal getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountCurrCode() {
		return this.accountCurrCode;
	}

	public void setAccountCurrCode(String accountCurrCode) {
		this.accountCurrCode = accountCurrCode;
	}

	public String getAccountHolder() {
		return this.accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAcctHolderType() {
		return this.acctHolderType;
	}

	public void setAcctHolderType(String acctHolderType) {
		this.acctHolderType = acctHolderType;
	}

	public String getAcctNumberType() {
		return this.acctNumberType;
	}

	public void setAcctNumberType(String acctNumberType) {
		this.acctNumberType = acctNumberType;
	}

	public String getBirthCity() {
		return this.birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public String getBirthCitySubent() {
		return this.birthCitySubent;
	}

	public void setBirthCitySubent(String birthCitySubent) {
		this.birthCitySubent = birthCitySubent;
	}

	public String getBirthCountry() {
		return this.birthCountry;
	}

	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthFormerCountry() {
		return this.birthFormerCountry;
	}

	public void setBirthFormerCountry(String birthFormerCountry) {
		this.birthFormerCountry = birthFormerCountry;
	}

	public String getClosedAccount() {
		return this.closedAccount;
	}

	public void setClosedAccount(String closedAccount) {
		this.closedAccount = closedAccount;
	}

	public String getCorrDocRefId() {
		return this.corrDocRefId;
	}

	public void setCorrDocRefId(String corrDocRefId) {
		this.corrDocRefId = corrDocRefId;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getDocRefId() {
		return this.docRefId;
	}

	public void setDocRefId(String docRefId) {
		this.docRefId = docRefId;
	}

	public String getDocTypeIndic() {
		return this.docTypeIndic;
	}

	public void setDocTypeIndic(String docTypeIndic) {
		this.docTypeIndic = docTypeIndic;
	}

	public String getDormantAccount() {
		return this.dormantAccount;
	}

	public void setDormantAccount(String dormantAccount) {
		this.dormantAccount = dormantAccount;
	}

	public BigInteger getHdrID() {
		return this.hdrID;
	}

	public void setHdrID(BigInteger hdrID) {
		this.hdrID = hdrID;
	}

	public String getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDateTime() {
		return this.modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getUndocumentedAccount() {
		return this.undocumentedAccount;
	}

	public void setUndocumentedAccount(String undocumentedAccount) {
		this.undocumentedAccount = undocumentedAccount;
	}

}
package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the crspayldpymt database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldpymt.findAll", query="SELECT c FROM Crspayldpymt c")
public class Crspayldpymt implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CrspayldpymtPK id;*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="acctrepid")
	private BigInteger acctRepID;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="currcode")
	private String currCode;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Column(name="paymentamt")
	private BigDecimal paymentAmt;

	@Column(name="paymenttype")
	private String paymentType;

	public Crspayldpymt() {
	}

	/*public CrspayldpymtPK getId() {
		return this.id;
	}

	public void setId(CrspayldpymtPK id) {
		this.id = id;
	}*/

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

	public String getCurrCode() {
		return this.currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
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

	public BigDecimal getPaymentAmt() {
		return this.paymentAmt;
	}

	public void setPaymentAmt(BigDecimal paymentAmt) {
		this.paymentAmt = paymentAmt;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getAcctRepID() {
		return acctRepID;
	}

	public void setAcctRepID(BigInteger acctRepID) {
		this.acctRepID = acctRepID;
	}
	

}
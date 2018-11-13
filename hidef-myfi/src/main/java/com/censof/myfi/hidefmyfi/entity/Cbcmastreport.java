package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcmastreport database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcmastreport.findAll", query="SELECT c FROM Cbcmastreport c")
public class Cbcmastreport implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CbcmastreportPK id;

	private int assetsAmt;

	private String assetsCurrCode;

	private int capitalAmt;

	private String capitalCurrCode;

	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	private String docRefId;

	private int earningsAmt;

	private String earningsCurrCode;

	private BigInteger hdrID;

	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	private int nbEmployees;

	private int profitOrLossAmt;

	private String profitOrLossCurrCode;

	private String resCountryCode;

	private int revenuesRelatedAmt;

	private String revenuesRelatedCurrCode;

	private int revenuesTotalAmt;

	private String revenuesTotalCurrCode;

	private int revenuesUnrelatedAmt;

	private String revenuesUnrelatedCurrCode;

	private int taxAccruedAmt;

	private String taxAccruedCurrCode;

	private int taxPaidAmt;

	private String taxPaidCurrCode;

	public Cbcmastreport() {
	}

	public CbcmastreportPK getId() {
		return this.id;
	}

	public void setId(CbcmastreportPK id) {
		this.id = id;
	}

	public int getAssetsAmt() {
		return this.assetsAmt;
	}

	public void setAssetsAmt(int assetsAmt) {
		this.assetsAmt = assetsAmt;
	}

	public String getAssetsCurrCode() {
		return this.assetsCurrCode;
	}

	public void setAssetsCurrCode(String assetsCurrCode) {
		this.assetsCurrCode = assetsCurrCode;
	}

	public int getCapitalAmt() {
		return this.capitalAmt;
	}

	public void setCapitalAmt(int capitalAmt) {
		this.capitalAmt = capitalAmt;
	}

	public String getCapitalCurrCode() {
		return this.capitalCurrCode;
	}

	public void setCapitalCurrCode(String capitalCurrCode) {
		this.capitalCurrCode = capitalCurrCode;
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

	public int getEarningsAmt() {
		return this.earningsAmt;
	}

	public void setEarningsAmt(int earningsAmt) {
		this.earningsAmt = earningsAmt;
	}

	public String getEarningsCurrCode() {
		return this.earningsCurrCode;
	}

	public void setEarningsCurrCode(String earningsCurrCode) {
		this.earningsCurrCode = earningsCurrCode;
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

	public int getNbEmployees() {
		return this.nbEmployees;
	}

	public void setNbEmployees(int nbEmployees) {
		this.nbEmployees = nbEmployees;
	}

	public int getProfitOrLossAmt() {
		return this.profitOrLossAmt;
	}

	public void setProfitOrLossAmt(int profitOrLossAmt) {
		this.profitOrLossAmt = profitOrLossAmt;
	}

	public String getProfitOrLossCurrCode() {
		return this.profitOrLossCurrCode;
	}

	public void setProfitOrLossCurrCode(String profitOrLossCurrCode) {
		this.profitOrLossCurrCode = profitOrLossCurrCode;
	}

	public String getResCountryCode() {
		return this.resCountryCode;
	}

	public void setResCountryCode(String resCountryCode) {
		this.resCountryCode = resCountryCode;
	}

	public int getRevenuesRelatedAmt() {
		return this.revenuesRelatedAmt;
	}

	public void setRevenuesRelatedAmt(int revenuesRelatedAmt) {
		this.revenuesRelatedAmt = revenuesRelatedAmt;
	}

	public String getRevenuesRelatedCurrCode() {
		return this.revenuesRelatedCurrCode;
	}

	public void setRevenuesRelatedCurrCode(String revenuesRelatedCurrCode) {
		this.revenuesRelatedCurrCode = revenuesRelatedCurrCode;
	}

	public int getRevenuesTotalAmt() {
		return this.revenuesTotalAmt;
	}

	public void setRevenuesTotalAmt(int revenuesTotalAmt) {
		this.revenuesTotalAmt = revenuesTotalAmt;
	}

	public String getRevenuesTotalCurrCode() {
		return this.revenuesTotalCurrCode;
	}

	public void setRevenuesTotalCurrCode(String revenuesTotalCurrCode) {
		this.revenuesTotalCurrCode = revenuesTotalCurrCode;
	}

	public int getRevenuesUnrelatedAmt() {
		return this.revenuesUnrelatedAmt;
	}

	public void setRevenuesUnrelatedAmt(int revenuesUnrelatedAmt) {
		this.revenuesUnrelatedAmt = revenuesUnrelatedAmt;
	}

	public String getRevenuesUnrelatedCurrCode() {
		return this.revenuesUnrelatedCurrCode;
	}

	public void setRevenuesUnrelatedCurrCode(String revenuesUnrelatedCurrCode) {
		this.revenuesUnrelatedCurrCode = revenuesUnrelatedCurrCode;
	}

	public int getTaxAccruedAmt() {
		return this.taxAccruedAmt;
	}

	public void setTaxAccruedAmt(int taxAccruedAmt) {
		this.taxAccruedAmt = taxAccruedAmt;
	}

	public String getTaxAccruedCurrCode() {
		return this.taxAccruedCurrCode;
	}

	public void setTaxAccruedCurrCode(String taxAccruedCurrCode) {
		this.taxAccruedCurrCode = taxAccruedCurrCode;
	}

	public int getTaxPaidAmt() {
		return this.taxPaidAmt;
	}

	public void setTaxPaidAmt(int taxPaidAmt) {
		this.taxPaidAmt = taxPaidAmt;
	}

	public String getTaxPaidCurrCode() {
		return this.taxPaidCurrCode;
	}

	public void setTaxPaidCurrCode(String taxPaidCurrCode) {
		this.taxPaidCurrCode = taxPaidCurrCode;
	}

}
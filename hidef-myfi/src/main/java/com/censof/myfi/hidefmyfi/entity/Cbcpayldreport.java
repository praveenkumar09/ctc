package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the cbcpayldreport database table.
 * 
 */
@Entity
@NamedQuery(name="Cbcpayldreport.findAll", query="SELECT c FROM Cbcpayldreport c")
public class Cbcpayldreport implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CbcpayldreportPK id;*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;
	
	@Column(name="bodyid")
	private BigInteger bodyID;

	@Column(name="assetsamt")
	private String assetsAmt;

	@Column(name="assetscurrcode")
	private String assetsCurrCode;

	@Column(name="capitalamt")
	private String capitalAmt;

	@Column(name="capitalcurrcode")
	private String capitalCurrCode;

	@Column(name="corrdocrefid")
	private String corrDocRefId;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="docrefid")
	private String docRefId;

	@Column(name="doctypeindic")
	private String docTypeIndic;

	@Column(name="earningsamt")
	private String earningsAmt;

	@Column(name="earningscurrcode")
	private String earningsCurrCode;

	@Column(name="hdrid")
	private BigInteger hdrID;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Column(name="nbemployees")
	private int nbEmployees;

	@Column(name="profitorlossamt")
	private String profitOrLossAmt;

	@Column(name="profitorlosscurrcode")
	private String profitOrLossCurrCode;

	@Column(name="rescountrycode")
	private String resCountryCode;

	@Column(name="revenuesrelatedamt")
	private String revenuesRelatedAmt;

	@Column(name="revenuesrelatedcurrcode")
	private String revenuesRelatedCurrCode;

	@Column(name="revenuestotalamt")
	private String revenuesTotalAmt;

	@Column(name="revenuestotalcurrcode")
	private String revenuesTotalCurrCode;

	@Column(name="revenuesunrelatedamt")
	private String revenuesUnrelatedAmt;

	@Column(name="revenuesunrelatedcurrcode")
	private String revenuesUnrelatedCurrCode;

	@Column(name="taxaccruedamt")
	private String taxAccruedAmt;

	@Column(name="taxaccruedcurrcode")
	private String taxAccruedCurrCode;

	@Column(name="taxpaidamt")
	private String taxPaidAmt;

	@Column(name="taxpaidcurrcode")
	private String taxPaidCurrCode;
	
	@Column(name="is_deleted")
	private Integer isdeleted;

	public Cbcpayldreport() {
	}

	

	

	public String getAssetsCurrCode() {
		return this.assetsCurrCode;
	}

	public void setAssetsCurrCode(String assetsCurrCode) {
		this.assetsCurrCode = assetsCurrCode;
	}

	

	public String getCapitalCurrCode() {
		return this.capitalCurrCode;
	}

	public void setCapitalCurrCode(String capitalCurrCode) {
		this.capitalCurrCode = capitalCurrCode;
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

	

	public String getRevenuesRelatedCurrCode() {
		return this.revenuesRelatedCurrCode;
	}

	public void setRevenuesRelatedCurrCode(String revenuesRelatedCurrCode) {
		this.revenuesRelatedCurrCode = revenuesRelatedCurrCode;
	}

	
	public String getRevenuesTotalCurrCode() {
		return this.revenuesTotalCurrCode;
	}

	public void setRevenuesTotalCurrCode(String revenuesTotalCurrCode) {
		this.revenuesTotalCurrCode = revenuesTotalCurrCode;
	}

	

	public String getRevenuesUnrelatedCurrCode() {
		return this.revenuesUnrelatedCurrCode;
	}

	public void setRevenuesUnrelatedCurrCode(String revenuesUnrelatedCurrCode) {
		this.revenuesUnrelatedCurrCode = revenuesUnrelatedCurrCode;
	}

	

	public String getTaxAccruedCurrCode() {
		return this.taxAccruedCurrCode;
	}

	public void setTaxAccruedCurrCode(String taxAccruedCurrCode) {
		this.taxAccruedCurrCode = taxAccruedCurrCode;
	}

	

	public String getTaxPaidCurrCode() {
		return this.taxPaidCurrCode;
	}

	public void setTaxPaidCurrCode(String taxPaidCurrCode) {
		this.taxPaidCurrCode = taxPaidCurrCode;
	}



	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public BigInteger getBodyID() {
		return bodyID;
	}



	public void setBodyID(BigInteger bodyID) {
		this.bodyID = bodyID;
	}



	public Integer getIsdeleted() {
		return isdeleted;
	}



	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}





	public String getAssetsAmt() {
		return assetsAmt;
	}





	public void setAssetsAmt(String assetsAmt) {
		this.assetsAmt = assetsAmt;
	}





	public String getCapitalAmt() {
		return capitalAmt;
	}





	public void setCapitalAmt(String capitalAmt) {
		this.capitalAmt = capitalAmt;
	}





	public String getEarningsAmt() {
		return earningsAmt;
	}





	public void setEarningsAmt(String earningsAmt) {
		this.earningsAmt = earningsAmt;
	}





	public String getProfitOrLossAmt() {
		return profitOrLossAmt;
	}





	public void setProfitOrLossAmt(String profitOrLossAmt) {
		this.profitOrLossAmt = profitOrLossAmt;
	}





	public String getRevenuesRelatedAmt() {
		return revenuesRelatedAmt;
	}





	public void setRevenuesRelatedAmt(String revenuesRelatedAmt) {
		this.revenuesRelatedAmt = revenuesRelatedAmt;
	}





	public String getRevenuesTotalAmt() {
		return revenuesTotalAmt;
	}





	public void setRevenuesTotalAmt(String revenuesTotalAmt) {
		this.revenuesTotalAmt = revenuesTotalAmt;
	}





	public String getRevenuesUnrelatedAmt() {
		return revenuesUnrelatedAmt;
	}





	public void setRevenuesUnrelatedAmt(String revenuesUnrelatedAmt) {
		this.revenuesUnrelatedAmt = revenuesUnrelatedAmt;
	}





	public String getTaxAccruedAmt() {
		return taxAccruedAmt;
	}





	public void setTaxAccruedAmt(String taxAccruedAmt) {
		this.taxAccruedAmt = taxAccruedAmt;
	}





	public String getTaxPaidAmt() {
		return taxPaidAmt;
	}





	public void setTaxPaidAmt(String taxPaidAmt) {
		this.taxPaidAmt = taxPaidAmt;
	}
	
	

}
package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the crspayldrescountry database table.
 * 
 */
@Entity
@NamedQuery(name="Crspayldrescountry.findAll", query="SELECT c FROM Crspayldrescountry c")
public class Crspayldrescountry implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	private CrspayldrescountryPK id;
*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger id;

	@Column(name="srctype")
	private String srcType;

	@Column(name="objectid")
	private BigInteger objectID;

	@Column(name="createby")
	private String createBy;

	@Column(name="createdatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	@Column(name="modifyby")
	private String modifyBy;

	@Column(name="modifydatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDateTime;

	@Column(name="rescountrycode")
	private String resCountryCode;

	public Crspayldrescountry() {
	}

	/*public CrspayldrescountryPK getId() {
		return this.id;
	}

	public void setId(CrspayldrescountryPK id) {
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

	public String getResCountryCode() {
		return this.resCountryCode;
	}

	public void setResCountryCode(String resCountryCode) {
		this.resCountryCode = resCountryCode;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getSrcType() {
		return srcType;
	}

	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}

	public BigInteger getObjectID() {
		return objectID;
	}

	public void setObjectID(BigInteger objectID) {
		this.objectID = objectID;
	}
	

}
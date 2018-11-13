package com.censof.myfi.hidefmyfi.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the userprofile database table.
 * 
 */
@Entity
@NamedQuery(name="Userprofile.findAll", query="SELECT u FROM Userprofile u")
public class Userprofile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;

	private String binaryencoding;

	private String communicationtype;

	@Column(name="create_by")
	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date_time")
	private Date createDateTime;

	private String documentreferenceid;

	private String fileformatcode;

	private String filetypeindic;

	private String messagereferenceid;

	private String messagetype;

	@Column(name="modify_by")
	private String modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modify_date_time")
	private Date modifyDateTime;

	private String mycbcid;

	private String receivingcountrycert;

	private String sendercontactemail;

	private String sendingcountry;

	private String sendingcountrycert;

	private String transmissionid;
	
	@Column(name="is_deleted")
	private Integer isdeleted;


	//bi-directional many-to-one association to Usereceivingcountry
	@OneToMany(mappedBy="userprofile")
	private List<Usereceivingcountry> usereceivingcountries;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	public Userprofile() {
	}

	

	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public String getBinaryencoding() {
		return this.binaryencoding;
	}

	public void setBinaryencoding(String binaryencoding) {
		this.binaryencoding = binaryencoding;
	}

	public String getCommunicationtype() {
		return this.communicationtype;
	}

	public void setCommunicationtype(String communicationtype) {
		this.communicationtype = communicationtype;
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

	public String getDocumentreferenceid() {
		return this.documentreferenceid;
	}

	public void setDocumentreferenceid(String documentreferenceid) {
		this.documentreferenceid = documentreferenceid;
	}

	public String getFileformatcode() {
		return this.fileformatcode;
	}

	public void setFileformatcode(String fileformatcode) {
		this.fileformatcode = fileformatcode;
	}

	public String getFiletypeindic() {
		return this.filetypeindic;
	}

	public void setFiletypeindic(String filetypeindic) {
		this.filetypeindic = filetypeindic;
	}

	public String getMessagereferenceid() {
		return this.messagereferenceid;
	}

	public void setMessagereferenceid(String messagereferenceid) {
		this.messagereferenceid = messagereferenceid;
	}

	public String getMessagetype() {
		return this.messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
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

	public String getMycbcid() {
		return this.mycbcid;
	}

	public void setMycbcid(String mycbcid) {
		this.mycbcid = mycbcid;
	}

	public String getReceivingcountrycert() {
		return this.receivingcountrycert;
	}

	public void setReceivingcountrycert(String receivingcountrycert) {
		this.receivingcountrycert = receivingcountrycert;
	}

	public String getSendercontactemail() {
		return this.sendercontactemail;
	}

	public void setSendercontactemail(String sendercontactemail) {
		this.sendercontactemail = sendercontactemail;
	}

	public String getSendingcountry() {
		return this.sendingcountry;
	}

	public void setSendingcountry(String sendingcountry) {
		this.sendingcountry = sendingcountry;
	}

	public String getSendingcountrycert() {
		return this.sendingcountrycert;
	}

	public void setSendingcountrycert(String sendingcountrycert) {
		this.sendingcountrycert = sendingcountrycert;
	}

	public String getTransmissionid() {
		return this.transmissionid;
	}

	public void setTransmissionid(String transmissionid) {
		this.transmissionid = transmissionid;
	}

	public List<Usereceivingcountry> getUsereceivingcountries() {
		return this.usereceivingcountries;
	}

	public void setUsereceivingcountries(List<Usereceivingcountry> usereceivingcountries) {
		this.usereceivingcountries = usereceivingcountries;
	}

	public Usereceivingcountry addUsereceivingcountry(Usereceivingcountry usereceivingcountry) {
		getUsereceivingcountries().add(usereceivingcountry);
		usereceivingcountry.setUserprofile(this);

		return usereceivingcountry;
	}

	public Usereceivingcountry removeUsereceivingcountry(Usereceivingcountry usereceivingcountry) {
		getUsereceivingcountries().remove(usereceivingcountry);
		usereceivingcountry.setUserprofile(null);

		return usereceivingcountry;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public Integer getIsdeleted() {
		return isdeleted;
	}



	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}
	

}
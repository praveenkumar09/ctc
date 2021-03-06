package com.censof.myfi.hidefmyfi.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.censof.myfi.hidefmyfi.entity.CbcCurrency;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldaddinfo;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldaddress;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldbizactiv;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldbody;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldconstentity;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldentity;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldhdr;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldin;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldname;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldreceivingcountry;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldreport;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldrescountry;
import com.censof.myfi.hidefmyfi.entity.Cbcpayldsumref;
import com.censof.myfi.hidefmyfi.entity.Crspayldacctrep;
import com.censof.myfi.hidefmyfi.entity.Crspayldaddress;
import com.censof.myfi.hidefmyfi.entity.Crspayldbody;
import com.censof.myfi.hidefmyfi.entity.Crspayldctrlperson;
import com.censof.myfi.hidefmyfi.entity.Crspayldfi;
import com.censof.myfi.hidefmyfi.entity.Crspayldhdr;
import com.censof.myfi.hidefmyfi.entity.Crspayldin;
import com.censof.myfi.hidefmyfi.entity.Crspayldname;
import com.censof.myfi.hidefmyfi.entity.Crspayldnamegeneration;
import com.censof.myfi.hidefmyfi.entity.Crspayldnamemiddle;
import com.censof.myfi.hidefmyfi.entity.Crspayldnamesuffix;
import com.censof.myfi.hidefmyfi.entity.Crspayldnametitle;
import com.censof.myfi.hidefmyfi.entity.Crspayldpymt;
import com.censof.myfi.hidefmyfi.entity.Crspayldrescountry;
import com.censof.myfi.hidefmyfi.entity.Docrefid;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.entity.Messagerefid;
import com.censof.myfi.hidefmyfi.entity.Senderfileid;
import com.censof.myfi.hidefmyfi.entity.User;
import com.censof.myfi.hidefmyfi.entity.Usereceivingcountry;
import com.censof.myfi.hidefmyfi.entity.Userprofile;
import com.censof.myfi.hidefmyfi.repository.CbcpayldaddinfoRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldaddressRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldbizactivRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldbodyRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldconstentityRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldentityRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldhdrRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldinRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldnameRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldreceivingcountryRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldreportRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldrescountryRepository;
import com.censof.myfi.hidefmyfi.repository.CbcpayldsumrefRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldacctrepRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldaddressRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldbodyRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldctrlpersonRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldfiRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldhdrRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldinRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldnameRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldnamegenerationRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldnamemiddleRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldnamesuffixRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldnametitleRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldpymtRepository;
import com.censof.myfi.hidefmyfi.repository.CrspayldrescountryRepository;
import com.censof.myfi.hidefmyfi.repository.DocrefidRepository;
import com.censof.myfi.hidefmyfi.repository.MessagerefidRepository;
import com.censof.myfi.hidefmyfi.repository.SenderfileidRepository;
import com.censof.myfi.hidefmyfi.repository.UserRepository;
import com.censof.myfi.hidefmyfi.repository.UsereceivingcountryRepository;
import com.censof.myfi.hidefmyfi.repository.UserprofileRepository;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.vo.AccountHolderVo;
import com.censof.myfi.hidefmyfi.vo.AddressVo;
import com.censof.myfi.hidefmyfi.vo.BizActivitiesTypeVo;
import com.censof.myfi.hidefmyfi.vo.CBCRepotsVo;
import com.censof.myfi.hidefmyfi.vo.CBCSummaryGridVo;
import com.censof.myfi.hidefmyfi.vo.CRSSummaryGridVo;
import com.censof.myfi.hidefmyfi.vo.CbcAdditionalInfo;
import com.censof.myfi.hidefmyfi.vo.CbcConstituentEntityVO;
import com.censof.myfi.hidefmyfi.vo.ControllingPersonVo;
import com.censof.myfi.hidefmyfi.vo.CrsMetadataVo;
import com.censof.myfi.hidefmyfi.vo.CrsReportingFiVo;
import com.censof.myfi.hidefmyfi.vo.GenerationIdentifierVo;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.MetaDataVo;
import com.censof.myfi.hidefmyfi.vo.MiddleNameVo;
import com.censof.myfi.hidefmyfi.vo.NameTypeVo;
import com.censof.myfi.hidefmyfi.vo.NameVo;
import com.censof.myfi.hidefmyfi.vo.OrganisationInTypeVo;
import com.censof.myfi.hidefmyfi.vo.PaymentTypeVo;
import com.censof.myfi.hidefmyfi.vo.RecievingCountryVo;
import com.censof.myfi.hidefmyfi.vo.ReportingEntityVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.censof.myfi.hidefmyfi.vo.SuffixVo;
import com.censof.myfi.hidefmyfi.vo.SummaryVo;
import com.censof.myfi.hidefmyfi.vo.TitleVo;
import com.censof.myfi.hidefmyfi.vo.UserProfileVo;

@Service("ctcDataSaveService")
public class CtcDataSaveServiceImpl implements CtcDataSaveService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CbcpayldhdrRepository cbcpayldhdrRepository;

	@Autowired
	private CbcpayldbodyRepository cbcpayldbodyRepository;

	@Autowired
	private CbcpayldreceivingcountryRepository cbcpayldreceivingcountryRepository;

	@Autowired
	private CbcpayldentityRepository cbcpayldentityRepository;

	@Autowired
	private CbcpayldnameRepository cbcpayldnameRepository;

	@Autowired
	private CbcpayldrescountryRepository cbcpayldrescountryRepository;

	@Autowired
	private CbcpayldinRepository cbcpayldinRepository;

	@Autowired
	private CbcpayldaddressRepository cbcpayldaddressRepository;

	@Autowired
	private CbcpayldreportRepository cbcpayldreportRepository;

	@Autowired
	private CbcpayldconstentityRepository cbcpayldconstentityRepository;

	@Autowired
	private CbcpayldbizactivRepository cbcpayldbizactivRepository;

	@Autowired
	private CbcpayldaddinfoRepository cbcpayldaddinfoRepository;

	@Autowired
	private CbcpayldsumrefRepository cbcpayldsumrefRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserprofileRepository userprofileRepository;

	@Autowired
	private CtccommonDropdownService commonDropDownService;

	@Autowired
	private UsereceivingcountryRepository usereceivingcountryRepository;

	@Autowired
	private DocrefidRepository docrefidRepository;

	@Autowired
	private MessagerefidRepository messagerefidRepository;

	@Autowired
	private SenderfileidRepository senderfileidRepository;

	@Autowired
	private CtccommonDropdownService ctccommonDropdownService;
	
	
	
	
	
	
	@Autowired
	private CrspayldhdrRepository crspayldhdrRepository;
	
	@Autowired
	private CrspayldbodyRepository crspayldbodyRepository;
	
	@Autowired
	private CrspayldfiRepository crspayldfiRepository;
	
	@Autowired
	private CrspayldnameRepository crspayldnameRepository;
	
	@Autowired
	private CrspayldinRepository crspayldinRepository;
	
	@Autowired
	private CrspayldrescountryRepository crspayldrescountryRepository;
	
	@Autowired
	private CrspayldaddressRepository crspayldaddressRepository;
	
	@Autowired
	private CrspayldacctrepRepository crspayldacctrepRepository;
	
	@Autowired
	private CrspayldnametitleRepository crspayldnametitleRepository;
	
	@Autowired
	private CrspayldnamemiddleRepository crspayldnamemiddleRepository;
	
	@Autowired
	private CrspayldnamegenerationRepository crspayldnamegenerationRepository;
	
	@Autowired
	private CrspayldnamesuffixRepository crspayldnamesuffixRepository;
	
	@Autowired
	private CrspayldctrlpersonRepository crspayldctrlpersonRepository;
	
	@Autowired
	private CrspayldpymtRepository crspayldpymtRepository;

	@Override
	@Transactional	
	public HidefVo saveCtcData(HidefVo hidefVo) {

		if (hidefVo != null) {
			
			
			if(hidefVo.getUserprofile() != null) {
				Userprofile userProfile = null;
				if(hidefVo.getUserprofile().getUserProfileId() != null){
					userProfile = userprofileRepository.getUserprofileDetailsById(hidefVo.getUserprofile().getUserProfileId());
					
					if(hidefVo.getUserprofile().getCtsTransId() != null && !hidefVo.getUserprofile().getCtsTransId().isEmpty()) {
					userProfile.setTransmissionid(hidefVo.getUserprofile().getCtsTransId());
					}
					
					if(hidefVo.getUserprofile().getFileTypeIndic() != null && !hidefVo.getUserprofile().getFileTypeIndic().isEmpty()) {
						userProfile.setFiletypeindic(hidefVo.getUserprofile().getFileTypeIndic());
					}
					
					userProfile = userprofileRepository.saveAndFlush(userProfile);
					
					
				}
			}
			
			
			if (hidefVo.getMetadata() != null) {
				/**
				 * Cbcpayldhdr
				 */
				logger.info("Receiving Country Code: TABLE:[Cbcpayldhdr]");
				Cbcpayldhdr payldhdr = null;
				if (hidefVo.getMetadata().getId() != null) {
					payldhdr = cbcpayldhdrRepository.getCbcDetailsById(hidefVo.getMetadata().getId());
				}
				if (payldhdr == null) {
					payldhdr = new Cbcpayldhdr();
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getSendingCountry())) {
					payldhdr.setSenderCountryCd(hidefVo.getMetadata().getSendingCountry());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getMessageTypeIndic())) {
					payldhdr.setMessageTypeIndic(hidefVo.getMetadata().getMessageTypeIndic());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getMsgType())) {
					payldhdr.setMessageType(hidefVo.getMetadata().getMsgType());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getWarning())) {
					payldhdr.setWarning(hidefVo.getMetadata().getWarning());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getContact())) {
					payldhdr.setContact(hidefVo.getMetadata().getContact());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getReportingPeriod())) {
					payldhdr.setReportingPeriod(hidefVo.getMetadata().getReportingPeriod());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getTaxYear())) {
					payldhdr.setTaxYear(Integer.parseInt(hidefVo.getMetadata().getTaxYear()));
				}

				// payldhdr.setFileCreateTs(new TimeSnew Date());
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getCommunicationType())) {
					payldhdr.setCommunicationTypeCd(hidefVo.getMetadata().getCommunicationType());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getSenderFileId())) {
					payldhdr.setSenderFileId(hidefVo.getMetadata().getSenderFileId());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getFileFormatCode())) {
					payldhdr.setFileFormatCD(hidefVo.getMetadata().getFileFormatCode());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getBinaryEncoding())) {
					payldhdr.setBinaryEncodingSchemeCd(hidefVo.getMetadata().getBinaryEncoding());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getMessageRefId())) {
					payldhdr.setMessageRefId(hidefVo.getMetadata().getMessageRefId());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getSenderContactEmailAddress())) {
					payldhdr.setSenderContactEmailAddressTxt(hidefVo.getMetadata().getSenderContactEmailAddress());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getSendingCompanyIN())) {
					payldhdr.setSendingEntityIN(hidefVo.getMetadata().getSendingCompanyIN());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getLanguage())) {
					payldhdr.setLanguage(hidefVo.getMetadata().getLanguage());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getFormCreationTimeStamp())) {
					payldhdr.setFileCreateTs(hidefVo.getMetadata().getFormCreationTimeStamp());
				}
				if (!StringUtils.isEmpty(hidefVo.getMycbcId())) {
					payldhdr.setMyCBCID(hidefVo.getMycbcId());
				}
				if(!StringUtils.isEmpty(hidefVo.getMetadata().getFileTypeIndic())){
					payldhdr.setFiletypeindic(hidefVo.getMetadata().getFileTypeIndic());
				}
				payldhdr.setIsdeleted(0);
				payldhdr.setCreateDateTime(new Date());
				payldhdr = cbcpayldhdrRepository.saveAndFlush(payldhdr);
				
				logger.info("Cbcpayldhdr ID::::::>" + payldhdr.getId());

				/**
				 * cbcpayldreceivingcountry
				 */
				logger.info("Receiving Country Code: TABLE:[cbcpayldreceivingcountry]");
				if (hidefVo.getMetadata().getRecievingCountryList() != null
						&& hidefVo.getMetadata().getRecievingCountryList().size() > 0) {
					for (RecievingCountryVo receivingCountry : hidefVo.getMetadata().getRecievingCountryList()) {
						Cbcpayldreceivingcountry payldreceivingCountry = null;
						if (receivingCountry.getId() > 0) {
							payldreceivingCountry = cbcpayldreceivingcountryRepository
									.getAllReceivingCountryByid(BigInteger.valueOf(receivingCountry.getId()));
							if(payldreceivingCountry != null){
								cbcpayldreceivingcountryRepository.delete(payldreceivingCountry);
							}
							logger.info("");
						}
						/*if (payldreceivingCountry == null) {*/
							payldreceivingCountry = new Cbcpayldreceivingcountry();
						/*}*/
						payldreceivingCountry.setCreateDateTime(new Date());
						payldreceivingCountry.setHdrID(payldhdr.getId());
						if (!StringUtils.isEmpty(receivingCountry.getRecievingCountry())) {
							payldreceivingCountry
									.setReceivingCountry(String.valueOf(receivingCountry.getRecievingCountry()));// TODO
						}
						payldreceivingCountry.setIsdeleted(0);
						payldreceivingCountry = cbcpayldreceivingcountryRepository.saveAndFlush(payldreceivingCountry);
						logger.info("cbcpayldreceivingcountry ID::::::>" + payldreceivingCountry.getId());
					}
				}

				/**
				 * cbcpayldbody
				 */
				logger.info("cbcpayldbody Saving: TABLE:[cbcpayldbody]");
				Cbcpayldbody cbcpayldbody = null;
				if (hidefVo.getMetadata().getId() != null) {
					cbcpayldbody = cbcpayldbodyRepository.getAllBodyDetailsById(hidefVo.getMetadata().getId());
				}
				if (cbcpayldbody == null) {
					cbcpayldbody = new Cbcpayldbody();
				}
				cbcpayldbody.setHdrID(payldhdr.getId());
				cbcpayldbody.setCreateDateTime(new Date());
				cbcpayldbody.setIsdeleted(0);
				cbcpayldbody = cbcpayldbodyRepository.saveAndFlush(cbcpayldbody);
				logger.info("cbcpayldbody ID::::::>" + cbcpayldbody.getId());

				/**
				 * Reporting Entity Tab Saving
				 */
				if (hidefVo.getReportingEntity() != null && payldhdr != null && payldhdr.getId() != null
						&& cbcpayldbody != null && cbcpayldbody.getId() != null) {
					logger.info("Reporting Entity Extra Fields Saving: TABLE:[cbcpayldentity]");
					Cbcpayldentity payldentity = null;
					if (hidefVo.getReportingEntity().getId() != null) {
						payldentity = cbcpayldentityRepository
								.getAllReportingEntityDetailsById(hidefVo.getReportingEntity().getId());
					}
					if (payldentity == null) {
						payldentity = new Cbcpayldentity();
					}
					payldentity.setBodyID(cbcpayldbody.getId());
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getCorDocReferenceId())) {
						payldentity.setCorrDocRefId(hidefVo.getReportingEntity().getCorDocReferenceId());
					}

					payldentity.setCreateDateTime(new Date());
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getDocumentReferenceId())) {
						payldentity.setDocRefId(hidefVo.getReportingEntity().getDocumentReferenceId());
					}
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getDocumentTypeIndicator())) {
						payldentity.setDocTypeIndic(hidefVo.getReportingEntity().getDocumentTypeIndicator());
					}
					payldentity.setHdrID(payldhdr.getId());
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getReportingRole())) {
						payldentity.setReportingRole(hidefVo.getReportingEntity().getReportingRole());
					}
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getTin())) {
						payldentity.setTin(hidefVo.getReportingEntity().getTin());
					}
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getTinType())) {
						payldentity.setIssuedBy(hidefVo.getReportingEntity().getTinType());
					}
					if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getTinIssuedBy())){
						payldentity.setTintype(hidefVo.getReportingEntity().getTinIssuedBy());
					}
					payldentity.setIsdeleted(0);
					// payldentity.
					payldentity = cbcpayldentityRepository.saveAndFlush(payldentity);
					logger.info("cbcpayldentity ID::::::>" + payldentity.getId());

					/*
					 * if() logger.info("cbcpayldentity INSERT Begin..........");
					 */

					if (payldentity != null && payldentity.getId() != null) {

						/**
						 * Reporting Entity Organisation Grid
						 */
						if (hidefVo.getReportingEntity().getNameList() != null
								&& hidefVo.getReportingEntity().getNameList().size() > 0) {
							logger.info("Reporting Entity Organisation Grid: TABLE:[cbcpayldname]");
							for (NameVo namevo : hidefVo.getReportingEntity().getNameList()) {
								Cbcpayldname payldname = null;
								if (namevo.getId() > 0) {
									payldname = cbcpayldnameRepository
											.getAllPayldNameDetailsById(BigInteger.valueOf(namevo.getId()));
									if(payldname != null){
										cbcpayldnameRepository.delete(payldname);
									}
								}
							/*	if (payldname == null) {*/
									payldname = new Cbcpayldname();
								/*}*/
								payldname.setObjectID(payldentity.getId());
								payldname.setCreateDateTime(new Date());
								if (!StringUtils.isEmpty(namevo.getFirstName())) {
									payldname.setNameOrganisation(namevo.getFirstName());
								}
								payldname.setSrcType("1");// TODO
								payldname.setIsdeleted(0);
								payldname = cbcpayldnameRepository.saveAndFlush(payldname);
								logger.info("cbcpayldname ID::::::>" + payldname.getId());
							}
						}

						/**
						 * Reporting Entity Resident Country Code Grid
						 */
						if (hidefVo.getReportingEntity().getResidentCountryList() != null
								&& hidefVo.getReportingEntity().getResidentCountryList().size() > 0) {
							logger.info("Reporting Entity Resident Country Code Grid: TABLE:[cbcpayldrescountry]");
							for (ResidentCountryVo residentCountry : hidefVo.getReportingEntity()
									.getResidentCountryList()) {
								Cbcpayldrescountry cbcpayldrescountry = null;
								if (residentCountry.getId() > 0) {
									
									cbcpayldrescountry = cbcpayldrescountryRepository.getAllPayldResidentCountryById(
											BigInteger.valueOf(residentCountry.getId()));
									if(cbcpayldrescountry != null){
										cbcpayldrescountryRepository.delete(cbcpayldrescountry);
									}
								}
							/*	if (cbcpayldrescountry == null) {*/
									cbcpayldrescountry = new Cbcpayldrescountry();
							/*	}*/
								cbcpayldrescountry.setCreateDateTime(new Date());
								cbcpayldrescountry.setObjectID(payldentity.getId());
								if (!StringUtils.isEmpty(residentCountry.getResidentCountryCode())) {
									cbcpayldrescountry.setResCountryCode(
											String.valueOf(residentCountry.getResidentCountryCode()));// TODO
								}
								cbcpayldrescountry.setSrcType("1");// TODO
								cbcpayldrescountry.setIsdeleted(0);
								cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
								logger.info("cbcpayldrescountry ID::::::>" + cbcpayldrescountry.getId());

							}
						}

						/**
						 * Reporting Entity In,InType,IssuedBy Grid
						 */
						if (hidefVo.getReportingEntity().getOrganisationInTypeList() != null
								&& hidefVo.getReportingEntity().getOrganisationInTypeList().size() > 0) {
							logger.info("cbcpayldin INSERT Begin..........");
							for (OrganisationInTypeVo organisation : hidefVo.getReportingEntity()
									.getOrganisationInTypeList()) {
								Cbcpayldin cbcpayldin = null;
								if (organisation.getId() > 0) {
									cbcpayldin = cbcpayldinRepository
											.getAllPayldInDetailsById(BigInteger.valueOf(organisation.getId()));
									if(cbcpayldin != null){
										cbcpayldinRepository.delete(cbcpayldin);
									}
								}
								/*if (cbcpayldin == null) {*/
									cbcpayldin = new Cbcpayldin();
								/*}*/
								cbcpayldin.setObjectID(payldentity.getId());
								cbcpayldin.setCreateDateTime(new Date());
								cbcpayldin.setTin(organisation.getIn());
								if (!StringUtils.isEmpty(organisation.getInType())) {
									cbcpayldin.setINType(organisation.getInType());
								}
								if (!StringUtils.isEmpty(organisation.getIssuedBy())) {
									cbcpayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));// TODO
								}
								cbcpayldin.setSrcType("1");// TODO
								cbcpayldin.setIsdeleted(0);
								cbcpayldin = cbcpayldinRepository.saveAndFlush(cbcpayldin);
								logger.info("cbcpayldin ID::::::>" + cbcpayldin.getId());
							}
						}

						/**
						 * Reporting Entity Address
						 */

						if (hidefVo.getReportingEntity().getAddressList() != null
								&& hidefVo.getReportingEntity().getAddressList().size() > 0) {
							logger.info("Reporting Entity Address: TABLE:[cbcpayldaddress]");
							for (AddressVo addressVo : hidefVo.getReportingEntity().getAddressList()) {
								Cbcpayldaddress cbcpayldaddress = null;
								if (addressVo.getId() > 0) {
									cbcpayldaddress = cbcpayldaddressRepository
											.getAllPayldAddressById(BigInteger.valueOf(addressVo.getId()));
									if(cbcpayldaddress != null){
										cbcpayldaddressRepository.delete(cbcpayldaddress);
									}
								}
								/*if (cbcpayldaddress == null) {*/
									cbcpayldaddress = new Cbcpayldaddress();
								/*}*/
								cbcpayldaddress.setObjectID(payldentity.getId());
								if (!StringUtils.isEmpty(addressVo.getAddressFree())) {
									cbcpayldaddress.setAddressFree(addressVo.getAddressFree());
								}
								if (!StringUtils.isEmpty(addressVo.getBuildingIdentifier())) {
									cbcpayldaddress.setBuildingIdentifier(addressVo.getBuildingIdentifier());
								}
								if (!StringUtils.isEmpty(addressVo.getCity())) {
									cbcpayldaddress.setCity(addressVo.getCity());
								}
								if (!StringUtils.isEmpty(addressVo.getCountryCode())) {
									cbcpayldaddress.setCountryCode(addressVo.getCountryCode());
								}
								if (!StringUtils.isEmpty(addressVo.getCountrySubentity())) {
									cbcpayldaddress.setCountrySubentity(addressVo.getCountrySubentity());
								}

								cbcpayldaddress.setCreateDateTime(new Date());
								if (!StringUtils.isEmpty(addressVo.getDistrictName())) {
									cbcpayldaddress.setDistrictName(addressVo.getDistrictName());
								}
								if (!StringUtils.isEmpty(addressVo.getFloorIdentifier())) {
									cbcpayldaddress.setFloorIdentifier(addressVo.getFloorIdentifier());
								}
								if (!StringUtils.isEmpty(addressVo.getAddressType())) {
									cbcpayldaddress.setLegalAddressType(addressVo.getAddressType());
								}
								if (!StringUtils.isEmpty(addressVo.getPob())) {
									cbcpayldaddress.setPob(addressVo.getPob());
								}
								if (!StringUtils.isEmpty(addressVo.getPostCode())) {
									cbcpayldaddress.setPostCode(addressVo.getPostCode());
								}

								cbcpayldaddress.setSrcType("1");// TODO
								if (!StringUtils.isEmpty(addressVo.getStreet())) {
									cbcpayldaddress.setStreet(addressVo.getStreet());
								}
								if (!StringUtils.isEmpty(addressVo.getSuitIdentifier())) {
									cbcpayldaddress.setSuiteIdentifier(addressVo.getSuitIdentifier());
								}
								cbcpayldaddress.setIsdeleted(0);

								cbcpayldaddress = cbcpayldaddressRepository.saveAndFlush(cbcpayldaddress);
								logger.info("cbcpayldaddress ID::::::>" + cbcpayldaddress.getId());

							}
						}
					}

				}

				/**
				 * CBC Reports
				 */
				if (hidefVo.getListCBCReports() != null && hidefVo.getListCBCReports().size() > 0 && payldhdr != null
						&& payldhdr.getId() != null && cbcpayldbody != null && cbcpayldbody.getId() != null) {
					logger.info("CBC Reports extra fileds : TABLE:[cbcpayldreport]");
					/* if(!StringUtils.isEmpty(str)) */
					for (CBCRepotsVo cbcReports : hidefVo.getListCBCReports()) {
						Cbcpayldreport cbcpayldreport = null;
						if (cbcReports.getId() > 0) {
							cbcpayldreport = cbcpayldreportRepository
									.getAllPayldCBCReportsById(BigInteger.valueOf(cbcReports.getId()));
						}
						if (cbcpayldreport == null) {
							cbcpayldreport = new Cbcpayldreport();
						}
						if (!StringUtils.isEmpty(cbcReports.getAssertAmount())) {
							if(cbcReports.getAssertAmount().contains("+") || cbcReports.getAssertAmount().contains("-")) {
							cbcpayldreport.setAssetsAmt(cbcReports.getAssertAmount());
							}else{
								cbcpayldreport.setAssetsAmt("+"+cbcReports.getAssertAmount());
							}
						}
						if (!StringUtils.isEmpty(cbcReports.getAssertCurrCode())) {
							cbcpayldreport.setAssetsCurrCode(cbcReports.getAssertCurrCode());
						}
						cbcpayldreport.setBodyID(cbcpayldbody.getId());
						if (!StringUtils.isEmpty(cbcReports.getCapitalAmount())) {
							if(cbcReports.getCapitalAmount().contains("+") || cbcReports.getCapitalAmount().contains("-")) {
							cbcpayldreport
									.setCapitalAmt(cbcReports.getCapitalAmount());
							}else{
								cbcpayldreport
								.setCapitalAmt("+"+cbcReports.getCapitalAmount());
							}
						}
						if (!StringUtils.isEmpty(hidefVo.getCbcReports().getCapitalCurrCode())) {
							cbcpayldreport.setCapitalCurrCode(cbcReports.getCapitalCurrCode());
						}
						if (!StringUtils.isEmpty(cbcReports.getCorrDocRefId())) {
							cbcpayldreport.setCorrDocRefId(cbcReports.getCorrDocRefId());
						}
						cbcpayldreport.setCreateDateTime(new Date());
						if (!StringUtils.isEmpty(cbcReports.getDocumentTypeIndicator())) {
							cbcpayldreport.setDocTypeIndic(cbcReports.getDocumentTypeIndicator());
						}
						if (!StringUtils.isEmpty(cbcReports.getEarningAmount())) {
							if(cbcReports.getEarningAmount().contains("+") || cbcReports.getEarningAmount().contains("-")) {
							cbcpayldreport
									.setEarningsAmt(cbcReports.getEarningAmount());
							}else{
								cbcpayldreport
								.setEarningsAmt("+"+cbcReports.getEarningAmount());
							}
						}
						if (!StringUtils.isEmpty(cbcReports.getEarningCurrCode())) {
							cbcpayldreport.setEarningsCurrCode(cbcReports.getEarningCurrCode());
						}
						cbcpayldreport.setHdrID(payldhdr.getId());
						if (!StringUtils.isEmpty(cbcReports.getNbEmployees())) {
							cbcpayldreport.setNbEmployees(Integer.valueOf(cbcReports.getNbEmployees()));
						}
						if (!StringUtils.isEmpty(cbcReports.getPrfitotloassAmount())) {
							if(cbcReports.getPrfitotloassAmount().contains("+") || cbcReports.getPrfitotloassAmount().contains("-")) {
							cbcpayldreport.setProfitOrLossAmt(
									cbcReports.getPrfitotloassAmount());
							}else{
								cbcpayldreport.setProfitOrLossAmt("+"+
										cbcReports.getPrfitotloassAmount());
							}
						}
						if (!StringUtils.isEmpty(cbcReports.getProfitorlossCurrCode())) {
							cbcpayldreport.setProfitOrLossCurrCode(cbcReports.getProfitorlossCurrCode());
						}
						if (!StringUtils.isEmpty(cbcReports.getResidentCountryCode())) {
							cbcpayldreport.setResCountryCode(cbcReports.getResidentCountryCode());
						}
						if (!StringUtils.isEmpty(cbcReports.getRelatedAmount())) {
							if(cbcReports.getRelatedAmount().contains("+") || cbcReports.getRelatedAmount().contains("-")) {
							cbcpayldreport.setRevenuesRelatedAmt(
									cbcReports.getRelatedAmount());
							}else{
								cbcpayldreport.setRevenuesRelatedAmt("+"+
										cbcReports.getRelatedAmount());
							}
						}
						if (!StringUtils.isEmpty(cbcReports.getRelatedCurrCode())) {
							cbcpayldreport.setRevenuesRelatedCurrCode(cbcReports.getRelatedCurrCode());
						}
						if (!StringUtils.isEmpty(cbcReports.getTotalRevenueAmount())) {
							if(cbcReports.getTotalRevenueAmount().contains("+") || cbcReports.getTotalRevenueAmount().contains("-")) {
							cbcpayldreport.setRevenuesTotalAmt(
									cbcReports.getTotalRevenueAmount());
							}else{
								cbcpayldreport.setRevenuesTotalAmt("+"+
										cbcReports.getTotalRevenueAmount());
							}
						}
						if (!StringUtils.isEmpty(cbcReports.getTotalRevenueCurrCode())) {
							cbcpayldreport.setRevenuesTotalCurrCode(cbcReports.getTotalRevenueCurrCode());
						}
						if (!StringUtils.isEmpty(cbcReports.getUnrelatedAmount())) {
							if(cbcReports.getUnrelatedAmount().contains("+") || cbcReports.getUnrelatedAmount().contains("-")) {
							cbcpayldreport.setRevenuesUnrelatedAmt(
									cbcReports.getUnrelatedAmount());
							}else{
								cbcpayldreport.setRevenuesUnrelatedAmt("+"+
										cbcReports.getUnrelatedAmount());
							}
						}
						if (!StringUtils.isEmpty(cbcReports.getUnrelateCurrCode())) {
							cbcpayldreport.setRevenuesUnrelatedCurrCode(cbcReports.getUnrelateCurrCode());
						}
						if (!StringUtils.isEmpty(cbcReports.getTaxaccruedAmount())) {
							if(cbcReports.getTaxaccruedAmount().contains("+") || cbcReports.getTaxaccruedAmount().contains("-")) {
							cbcpayldreport.setTaxAccruedAmt(
									cbcReports.getTaxaccruedAmount());
							}else{
								cbcpayldreport.setTaxAccruedAmt("+"+
										cbcReports.getTaxaccruedAmount());
							}
						}
						if (!StringUtils.isEmpty(cbcReports.getTaxaccruedCurrCode())) {
							cbcpayldreport.setTaxAccruedCurrCode(cbcReports.getTaxaccruedCurrCode());
						}
						if (!StringUtils.isEmpty(cbcReports.getTaxpaidAmount())) {
							if(cbcReports.getTaxpaidAmount().contains("+") || cbcReports.getTaxpaidAmount().contains("-")) {
							cbcpayldreport
									.setTaxPaidAmt(cbcReports.getTaxpaidAmount());
							}else{
								cbcpayldreport
								.setTaxPaidAmt("+"+cbcReports.getTaxpaidAmount());	
							}
						}
						if (!StringUtils.isEmpty(cbcReports.getTaxpiadCurrCode())) {
							cbcpayldreport.setTaxPaidCurrCode(cbcReports.getTaxpiadCurrCode());
						}

						if (!StringUtils.isEmpty(cbcReports.getDocumentRefId())) {
							cbcpayldreport.setDocRefId(cbcReports.getDocumentRefId());
						}

						cbcpayldreport.setIsdeleted(0);
						cbcpayldreport = cbcpayldreportRepository.saveAndFlush(cbcpayldreport);
						logger.info("cbcpayldreport ID::::::>" + cbcpayldreport.getId());

						/**
						 * Constituent Entities :
						 */
						if(cbcReports.getConstituentEntityList() != null && !cbcReports.getConstituentEntityList().isEmpty()) {
						for(CbcConstituentEntityVO consEntity :cbcReports.getConstituentEntityList()) {
						logger.info("CBCReports Constituent Entities : TABLE:[cbcpayldconstentity]");
						Cbcpayldconstentity cbcpayldconstentity = null;
						if (consEntity.getConsId() > 0) {
							cbcpayldconstentity = cbcpayldconstentityRepository
									.getAllconstentityById(BigInteger.valueOf(consEntity.getConsId()));
							
						}
						if (cbcpayldconstentity == null) {
							cbcpayldconstentity = new Cbcpayldconstentity();
						}
						if (!StringUtils.isEmpty(consEntity.getIncorpCountryCode())) {
							cbcpayldconstentity.setIncorpCountryCode(consEntity.getIncorpCountryCode());
						}
						if (!StringUtils.isEmpty(consEntity.getTin())) {
							cbcpayldconstentity.setTin(consEntity.getTin());
						}
						if (!StringUtils.isEmpty(consEntity.getTinType())) {
							cbcpayldconstentity.setINType(consEntity.getTinType());
						}
						if (!StringUtils.isEmpty(consEntity.getIssuedBy())) {
							cbcpayldconstentity.setIssuedBy(consEntity.getIssuedBy());
						}
						if (!StringUtils.isEmpty(consEntity.getOtherEntityInfo())) {
							cbcpayldconstentity.setOtherEntityInfo(consEntity.getOtherEntityInfo());
						}
						cbcpayldconstentity.setReportID(cbcpayldreport.getId());
						cbcpayldconstentity.setIsdeleted(0);
						cbcpayldconstentity = cbcpayldconstentityRepository.saveAndFlush(cbcpayldconstentity);
						logger.info("cbcpayldconstentity ID::::::>" + cbcpayldconstentity.getId());

						/**
						 * BiZ Activities
						 */
						if (consEntity.getBizActivitiesList() != null && consEntity.getBizActivitiesList().size() > 0) {
							logger.info("CBCReports BiZ Activities TABLE:[cbcpayldbizactiv]");
							for (BizActivitiesTypeVo bizActivitiesTypeVo : consEntity.getBizActivitiesList()) {
								Cbcpayldbizactiv cbcpayldbizactiv = null;
								if (bizActivitiesTypeVo.getId() > 0) {
									cbcpayldbizactiv = cbcpayldbizactivRepository
											.getAllBizActivitiesByID(BigInteger.valueOf(bizActivitiesTypeVo.getId()));
									if(cbcpayldbizactiv != null){
										cbcpayldbizactivRepository.delete(cbcpayldbizactiv);
									}
								}
								/*if (cbcpayldbizactiv == null) {*/
									cbcpayldbizactiv = new Cbcpayldbizactiv();
								/*}*/
								if (!StringUtils.isEmpty(bizActivitiesTypeVo.getBizType())) {
									cbcpayldbizactiv.setBizActivities(String.valueOf(bizActivitiesTypeVo.getBizType()));
								}
								cbcpayldbizactiv.setConsentID(cbcpayldconstentity.getId());
								cbcpayldbizactiv.setCreateDateTime(new Date());
								cbcpayldbizactiv.setIsdeleted(0);
								cbcpayldbizactiv = cbcpayldbizactivRepository.saveAndFlush(cbcpayldbizactiv);
								logger.info("cbcpayldbizactiv ID::::::>" + cbcpayldbizactiv.getId());
							}

						}

						/**
						 * CBCReports Organisation Grid
						 */
						if (consEntity.getNameList() != null && consEntity.getNameList().size() > 0) {
							logger.info("CBCReports Organisation TABLE:[cbcpayldname]");
							for (NameVo namevo : consEntity.getNameList()) {
								Cbcpayldname payldname = null;
								;
								if (namevo.getId() > 0) {
									payldname = cbcpayldnameRepository
											.getAllPayldNameDetailsById(BigInteger.valueOf(namevo.getId()));
									if(payldname != null){
										cbcpayldnameRepository.delete(payldname);
									}
								}
								/*if (payldname == null) {*/
									payldname = new Cbcpayldname();
								/*}*/
								payldname.setObjectID(cbcpayldconstentity.getId());
								payldname.setCreateDateTime(new Date());
								if (!StringUtils.isEmpty(namevo.getFirstName())) {
									payldname.setNameOrganisation(namevo.getFirstName());
								}
								payldname.setSrcType("1");// TODO
								payldname = cbcpayldnameRepository.saveAndFlush(payldname);
								logger.info("cbcpayldname ID::::::>" + payldname.getId());
							}
						}

						/**
						 * CBCReports Resident Country Code Grid
						 */
						if (consEntity.getResidentCountryList() != null
								&& consEntity.getResidentCountryList().size() > 0) {
							logger.info("CBCReports Resident Country Code TABLE:[cbcpayldrescountry]");
							for (ResidentCountryVo residentCountry : consEntity.getResidentCountryList()) {
								Cbcpayldrescountry cbcpayldrescountry = null;
								if (residentCountry.getId() > 0) {
									cbcpayldrescountry = cbcpayldrescountryRepository.getAllPayldResidentCountryById(
											BigInteger.valueOf(residentCountry.getId()));
									if(cbcpayldrescountry != null){
										cbcpayldrescountryRepository.delete(cbcpayldrescountry);
									}
								}
							/*	if (cbcpayldrescountry == null) {*/
									cbcpayldrescountry = new Cbcpayldrescountry();
								/*}*/
								cbcpayldrescountry.setCreateDateTime(new Date());
								cbcpayldrescountry.setObjectID(cbcpayldconstentity.getId());
								if (!StringUtils.isEmpty(residentCountry.getResidentCountryCode())) {
									cbcpayldrescountry.setResCountryCode(
											String.valueOf(residentCountry.getResidentCountryCode()));// TODO
								}
								cbcpayldrescountry.setSrcType("1");// TODO
								cbcpayldrescountry.setIsdeleted(0);
								cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
								logger.info("cbcpayldrescountry ID::::::>" + cbcpayldrescountry.getId());

							}
						}

						/**
						 * CBCReports In,InType,IssuedBy Grid
						 */
						if (consEntity.getOrganisationInTypeList() != null
								&& consEntity.getOrganisationInTypeList().size() > 0) {
							logger.info("CBCReports In,InType,IssuedBy TABLE:[cbcpayldin]");
							for (OrganisationInTypeVo organisation : consEntity.getOrganisationInTypeList()) {
								Cbcpayldin cbcpayldin = null;
								if (organisation.getId() > 0) {
									cbcpayldin = cbcpayldinRepository
											.getAllPayldInDetailsById(BigInteger.valueOf(organisation.getId()));
									if(cbcpayldin != null){
										cbcpayldinRepository.delete(cbcpayldin);
									}
								}
								/*if (cbcpayldin == null) {*/
									cbcpayldin = new Cbcpayldin();
								/*}*/
								cbcpayldin.setObjectID(cbcpayldconstentity.getId());
								cbcpayldin.setCreateDateTime(new Date());
								if (!StringUtils.isEmpty(organisation.getIn())) {
									cbcpayldin.setTin(organisation.getIn());
								}
								if (!StringUtils.isEmpty(organisation.getInType())) {
									cbcpayldin.setINType(organisation.getInType());
								}
								if (!StringUtils.isEmpty(organisation.getIssuedBy())) {
									cbcpayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));// TODO
								}
								cbcpayldin.setSrcType("1");// TODO
								cbcpayldin.setIsdeleted(0);
								cbcpayldin = cbcpayldinRepository.saveAndFlush(cbcpayldin);
								logger.info("cbcpayldin ID::::::>" + cbcpayldin.getId());
							}
						}

						/**
						 * CBCReports Address
						 */

						if (consEntity.getAddressList() != null && consEntity.getAddressList().size() > 0) {
							logger.info("CBCRepots Address  TABLE:[cbcpayldaddress]");
							for (AddressVo addressVo : consEntity.getAddressList()) {
								Cbcpayldaddress cbcpayldaddress = null;
								if (addressVo.getId() > 0) {
									cbcpayldaddress = cbcpayldaddressRepository
											.getAllPayldAddressById(BigInteger.valueOf(addressVo.getId()));
									if(cbcpayldaddress != null){
										cbcpayldaddressRepository.delete(cbcpayldaddress);
									}
								}
							/*	if (cbcpayldaddress == null) {*/
									cbcpayldaddress = new Cbcpayldaddress();
							/*	}*/
								cbcpayldaddress.setObjectID(cbcpayldconstentity.getId());
								if (!StringUtils.isEmpty(addressVo.getAddressFree())) {
									cbcpayldaddress.setAddressFree(addressVo.getAddressFree());
								}
								if (!StringUtils.isEmpty(addressVo.getBuildingIdentifier())) {
									cbcpayldaddress.setBuildingIdentifier(addressVo.getBuildingIdentifier());
								}
								if (!StringUtils.isEmpty(addressVo.getCity())) {
									cbcpayldaddress.setCity(addressVo.getCity());
								}
								if (!StringUtils.isEmpty(addressVo.getCountryCode())) {
									cbcpayldaddress.setCountryCode(addressVo.getCountryCode());
								}
								if (!StringUtils.isEmpty(addressVo.getCountrySubentity())) {
									cbcpayldaddress.setCountrySubentity(addressVo.getCountrySubentity());
								}

								cbcpayldaddress.setCreateDateTime(new Date());
								if (!StringUtils.isEmpty(addressVo.getDistrictName())) {
									cbcpayldaddress.setDistrictName(addressVo.getDistrictName());
								}
								if (!StringUtils.isEmpty(addressVo.getFloorIdentifier())) {
									cbcpayldaddress.setFloorIdentifier(addressVo.getFloorIdentifier());
								}
								if (!StringUtils.isEmpty(addressVo.getAddressType())) {
									cbcpayldaddress.setLegalAddressType(addressVo.getAddressType());
								}
								if (!StringUtils.isEmpty(addressVo.getPob())) {
									cbcpayldaddress.setPob(addressVo.getPob());
								}
								if (!StringUtils.isEmpty(addressVo.getPostCode())) {
									cbcpayldaddress.setPostCode(addressVo.getPostCode());
								}

								cbcpayldaddress.setSrcType("1");// TODO
								if (!StringUtils.isEmpty(addressVo.getStreet())) {
									cbcpayldaddress.setStreet(addressVo.getStreet());
								}
								if (!StringUtils.isEmpty(addressVo.getSuitIdentifier())) {
									cbcpayldaddress.setSuiteIdentifier(addressVo.getSuitIdentifier());
								}
								cbcpayldaddress.setIsdeleted(0);
								cbcpayldaddress = cbcpayldaddressRepository.saveAndFlush(cbcpayldaddress);
								logger.info("cbcpayldaddress ID::::::>" + cbcpayldaddress.getId());

							}
						}
					  }
					}	

					}

				}

				/**
				 * Additional Info
				 */
				if (hidefVo.getAddInfoList() != null && hidefVo.getAddInfoList().size() > 0 && payldhdr != null
						&& payldhdr.getId() != null && cbcpayldbody != null && cbcpayldbody.getId() != null) {
					logger.info("Additional Info Extra Fields TABLE:[cbcpayldaddinfo]");
					for (CbcAdditionalInfo addinfo : hidefVo.getAddInfoList()) {
						Cbcpayldaddinfo sddInfo = null;
						if (addinfo.getId() > 0) {
							sddInfo = cbcpayldaddinfoRepository
									.getAllAdditionalInfoById(BigInteger.valueOf(addinfo.getId()));
						}
						if (sddInfo == null) {
							sddInfo = new Cbcpayldaddinfo();
						}
						sddInfo.setBodyID(cbcpayldbody.getId());
						if (!StringUtils.isEmpty(addinfo.getCorDocumentRefId())) {
							sddInfo.setCorrDocRefId(addinfo.getCorDocumentRefId());
						}

						sddInfo.setCreateDateTime(new Date());
						if (!StringUtils.isEmpty(addinfo.getDocumentReferenceId())) {
							sddInfo.setDocRefId(addinfo.getDocumentReferenceId());
						}
						if (!StringUtils.isEmpty(addinfo.getDocumentTypeIndic())) {
							sddInfo.setDocTypeIndic(addinfo.getDocumentTypeIndic());
						}
						sddInfo.setHdrID(payldhdr.getId());
						if (!StringUtils.isEmpty(addinfo.getOtherInfo())) {
							sddInfo.setOtherInfo(addinfo.getOtherInfo());
						}
						sddInfo.setIsdeleted(0);
						sddInfo = cbcpayldaddinfoRepository.saveAndFlush(sddInfo);
						logger.info("cbcpayldaddinfo ID::::::>" + sddInfo.getId());

						/**
						 * AdditionalInfo Resident Country Code Grid
						 */
						if (addinfo.getResidentCountryList() != null && addinfo.getResidentCountryList().size() > 0) {
							logger.info("Additional ResidentCountry TABLE:[cbcpayldrescountry]");
							for (ResidentCountryVo residentCountry : addinfo.getResidentCountryList()) {
								Cbcpayldrescountry cbcpayldrescountry = null;
								if (residentCountry.getId() > 0) {
									cbcpayldrescountry = cbcpayldrescountryRepository.getAllPayldResidentCountryById(
											BigInteger.valueOf(residentCountry.getId()));
									if(cbcpayldrescountry != null){
										cbcpayldrescountryRepository.delete(cbcpayldrescountry);
									}
								}
								/*if (cbcpayldrescountry == null) {*/
									cbcpayldrescountry = new Cbcpayldrescountry();
								/*}*/
								cbcpayldrescountry.setCreateDateTime(new Date());
								cbcpayldrescountry.setObjectID(sddInfo.getId());
								if (!StringUtils.isEmpty(residentCountry.getResidentCountryCode())) {
									cbcpayldrescountry.setResCountryCode(
											String.valueOf(residentCountry.getResidentCountryCode()));// TODO
								}
								cbcpayldrescountry.setSrcType("1");// TODO
								cbcpayldrescountry.setIsdeleted(0);
								cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
								logger.info("cbcpayldrescountry ID::::::>" + cbcpayldrescountry.getId());

							}
						}

						/**
						 * AdditionalInfo Summary Grid
						 */
						if (addinfo.getSummaryList() != null && addinfo.getSummaryList().size() > 0) {
							logger.info("Additional info Summary Ref TABLE:[cbcpayldsumref]");
							for (SummaryVo summary : addinfo.getSummaryList()) {
								Cbcpayldsumref summaryRef = null;
								if (summary.getId() > 0) {
									summaryRef = cbcpayldsumrefRepository
											.getAllSummaryById(BigInteger.valueOf(summary.getId()));
									if(summaryRef != null){
										cbcpayldsumrefRepository.delete(summaryRef);
									}
								}
								/*if (summaryRef == null) {*/
									summaryRef = new Cbcpayldsumref();
								/*}*/
								summaryRef.setAddinfoID(sddInfo.getId());
								summaryRef.setCreateDateTime(new Date());
								if (!StringUtils.isEmpty(summary.getSummeryReference())) {
									summaryRef.setSummaryRef(String.valueOf(summary.getSummeryReference()));
								}
								summaryRef.setIsdeleted(0);
								summaryRef = cbcpayldsumrefRepository.saveAndFlush(summaryRef);
								logger.info("cbcpayldsumref ID::::::>" + summaryRef.getId());
							}

						}

					}

				}

				/**
				 * To update the DocRefId
				 */
				String pattern = "yyyyMMdd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String date = simpleDateFormat.format(new Date());
				Docrefid docrefid = ctccommonDropdownService.findDocRefIdByDate(date);
				if (docrefid != null) {
					docrefid.setDocrefid(hidefVo.getDocRefId());
					docrefid = docrefidRepository.saveAndFlush(docrefid);
				}

				/**
				 * To update the senderFileId
				 */
				Senderfileid senderFileId = ctccommonDropdownService.findSenderFileIdByDate(date);
				if (senderFileId != null) {
					String senderFieIdNew = hidefVo.getMetadata().getSenderFileId();
					senderFileId.setSenderfileid(senderFieIdNew.substring(senderFieIdNew.length() - 4));
					senderFileId = senderfileidRepository.saveAndFlush(senderFileId);
				}
				if(payldhdr != null){
				hidefVo.setPayldId(payldhdr.getId());
				}

			} // metadata

		} // hidef

		// TODO Auto-generated method stub
		return hidefVo;

	}

	@Override
	public HidefVo getAllDatabyCBCId(HidefVo hidefvo) {
		// TODO Auto-generated method stub
		logger.info("Get all the CBC Details based on CBCId:::" + hidefvo.getMycbcId());
		List<HidefVo> hidefList = new ArrayList<HidefVo>();
		List<CBCSummaryGridVo> summaryList = new ArrayList<CBCSummaryGridVo>();
		List<Cbcpayldhdr> cbcPayldhdr = cbcpayldhdrRepository.getAllCbcDetails(hidefvo.getMycbcId(), 0);// need to
																										// change Login
																										// CBCId
		for (Cbcpayldhdr payldhdr : cbcPayldhdr) {
			CBCSummaryGridVo summary = new CBCSummaryGridVo();

			summary.setMessageType(payldhdr.getMessageType());
			summary.setSendingCountry(payldhdr.getSenderCountryCd());
			summary.setId(summaryList.size() + 1);
			summary.setHrdId(payldhdr.getId());
			summary.setFilename(payldhdr.getFilename());
			summary.setCreatedDateTime(payldhdr.getCreateDateTime().toString());
			summaryList.add(summary);

		}

		hidefvo.setCbcsummary(summaryList);

		return hidefvo;
	}

	@SuppressWarnings("unused")
	@Override
	public HidefVo saveUserprofileData(HidefVo hidefvo) {
		// TODO Auto-generated method stub
		
		logger.info("User Details Saving part..........");
		String privarecert = null;
		String publiccertpath = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info(auth.getName());
		String userCBCId = auth.getName();
		List<User> user = userRepository.getUser(userCBCId);//TODO need to change login CBCId
		if(hidefvo.getUserprofile() != null){
		Userprofile userProfile = null;
		if(hidefvo.getUserprofile().getUserProfileId() != null){
			userProfile = userprofileRepository.getUserprofileDetailsById(hidefvo.getUserprofile().getUserProfileId());
			publiccertpath = userProfile.getReceivingcountrycert();
			privarecert = userProfile.getSendingcountrycert();
		}
		if(userProfile == null){
			userProfile = new Userprofile();
			privarecert = fetchProperties("privatecertpath")+"/"+hidefvo.getUserprofile().getConfigurationFileText();
			publiccertpath = fetchProperties("publiccertpath")+"/"+hidefvo.getUserprofile().getPublicCertFileName();
		}
		
		if(!StringUtils.isEmpty(hidefvo.getUserprofile().getCtsTransId())){
			userProfile.setTransmissionid(hidefvo.getUserprofile().getCtsTransId());;
			}
		
		if(!StringUtils.isEmpty(hidefvo.getUserprofile().getBinaryEncoding())){
		userProfile.setBinaryencoding(hidefvo.getUserprofile().getBinaryEncoding());
		}
		if(!StringUtils.isEmpty(hidefvo.getUserprofile().getCommunicationType())){
		userProfile.setCommunicationtype(hidefvo.getUserprofile().getCommunicationType());
		}
		
		userProfile.setCreateDateTime(new Date());
		if(!StringUtils.isEmpty(hidefvo.getUserprofile().getDocRefID())){
		userProfile.setDocumentreferenceid(hidefvo.getUserprofile().getDocRefID());
		}
		if(!StringUtils.isEmpty(hidefvo.getUserprofile().getFileformatCode())){
		userProfile.setFileformatcode(hidefvo.getUserprofile().getFileformatCode());
		}
		if(!StringUtils.isEmpty(hidefvo.getUserprofile().getFileTypeIndic())){
		userProfile.setFiletypeindic(hidefvo.getUserprofile().getFileTypeIndic());
		}
		if(!StringUtils.isEmpty(hidefvo.getUserprofile().getMessageRefID())){
		userProfile.setMessagereferenceid(hidefvo.getUserprofile().getMessageRefID());
		}
		if(!StringUtils.isEmpty(hidefvo.getUserprofile().getMsgType())){
		userProfile.setMessagetype(hidefvo.getUserprofile().getMsgType());
		}
		if(!StringUtils.isEmpty(hidefvo.getUserprofile().getSendingcountry())){
		userProfile.setSendingcountry(hidefvo.getUserprofile().getSendingcountry());
		}
		if(!StringUtils.isEmpty(hidefvo.getUserprofile().getSendContactEmailAddress())){
		userProfile.setSendercontactemail(hidefvo.getUserprofile().getSendContactEmailAddress());
		}
		userProfile.setMycbcid(user.get(0).getMyCbcId());
		userProfile.setUser(user.get(0));
		try{
			  if(hidefvo.getUserprofile().getUserProfileId() == null){///TODO
			  if(!StringUtils.isEmpty(hidefvo.getUserprofile().getConfigurationFileText()) && !StringUtils.isEmpty(fetchProperties("privatecertpath"))){ 
			  /*File privateCert = new File(privarecert);
			  hidefvo.getUserprofile().getConfigurationFile().transferTo(privateCert);*/
				  File convFile = null;
				  if(hidefvo.getUserprofile().getMsgType().equals("CRS")) {
					  convFile = new File(fetchProperties("crsprivatecertpath")+'/'+hidefvo.getUserprofile().getConfigurationFileText());

				  }else {
					  
					  convFile = new File(fetchProperties("privatecertpath")+'/'+hidefvo.getUserprofile().getConfigurationFileText());
				  }
			     convFile.createNewFile();
			     FileOutputStream fos = new FileOutputStream(convFile);
			     fos.write(hidefvo.getUserprofile().getConfigurationFile().getBytes());
			     fos.close();
			  userProfile.setSendingcountrycert(convFile.getAbsolutePath());
			  }
			  if(!StringUtils.isEmpty(hidefvo.getUserprofile().getPublicCertFileName()) && !StringUtils.isEmpty(fetchProperties("publiccertpath"))){ 
			  /*File publicCert = new File(publiccertpath);
			  hidefvo.getUserprofile().getConfigurationFile().transferTo(publicCert);*/
				  File convFile = null;
				  if(hidefvo.getUserprofile().getMsgType().equals("CRS")) {
					  
					  convFile = new File(fetchProperties("crspubliccertpath")+'/'+hidefvo.getUserprofile().getPublicCertFileName());
					  
				  }else {
					  
					  convFile = new File(fetchProperties("publiccertpath")+'/'+hidefvo.getUserprofile().getPublicCertFileName());
				  }
			     convFile.createNewFile();
			     FileOutputStream fos = new FileOutputStream(convFile);
			     fos.write(hidefvo.getUserprofile().getPublicCertPath().getBytes());
			     fos.close();
			  userProfile.setReceivingcountrycert(convFile.getAbsolutePath());
			  }
			  }else {///TODO
				  if(!StringUtils.isEmpty(hidefvo.getUserprofile().getConfigurationFileText()) && !StringUtils.isEmpty(fetchProperties("privatecertpath")) && hidefvo.getUserprofile().getConfigurationFile() != null){ 
					  /*File privateCert = new File(privarecert);
					  hidefvo.getUserprofile().getConfigurationFile().transferTo(privateCert);*/
						  File convFile = null;
						  if(hidefvo.getUserprofile().getMsgType().equals("CRS")) {
							  convFile = new File(fetchProperties("crsprivatecertpath")+'/'+hidefvo.getUserprofile().getConfigurationFileText());

						  }else {
							  
							  convFile = new File(fetchProperties("privatecertpath")+'/'+hidefvo.getUserprofile().getConfigurationFileText());
						  }
						  if(hidefvo.getUserprofile().getConfigurationFile() != null) {
					     convFile.createNewFile();
					     FileOutputStream fos = new FileOutputStream(convFile);
					     fos.write(hidefvo.getUserprofile().getConfigurationFile().getBytes());
					     fos.close();
						  }
					  userProfile.setSendingcountrycert(convFile.getAbsolutePath());
					  }
					  if(!StringUtils.isEmpty(hidefvo.getUserprofile().getPublicCertFileName()) && !StringUtils.isEmpty(fetchProperties("publiccertpath")) && hidefvo.getUserprofile().getPublicCertPath() != null){ 
					  /*File publicCert = new File(publiccertpath);
					  hidefvo.getUserprofile().getConfigurationFile().transferTo(publicCert);*/
						  File convFile = null;
						  if(hidefvo.getUserprofile().getMsgType().equals("CRS")) {
							  
							  convFile = new File(fetchProperties("crspubliccertpath")+'/'+hidefvo.getUserprofile().getPublicCertFileName());
							  
						  }else {
							  
							  convFile = new File(fetchProperties("publiccertpath")+'/'+hidefvo.getUserprofile().getPublicCertFileName());
						  }
						  if(hidefvo.getUserprofile().getPublicCertPath() != null) {
					     convFile.createNewFile();
					     FileOutputStream fos = new FileOutputStream(convFile);
					     fos.write(hidefvo.getUserprofile().getPublicCertPath().getBytes());
					     fos.close();
						  }
					  userProfile.setReceivingcountrycert(convFile.getAbsolutePath());
					  }
					  }
		
		//userProfile.setTransmissionid(hidefvo.getUserprofile().get);
		userProfile = userprofileRepository.saveAndFlush(userProfile);
		
		if(hidefvo.getUserprofile().getRecievingCountryList() != null && hidefvo.getUserprofile().getRecievingCountryList().size() >0){
			for(RecievingCountryVo receiving : hidefvo.getUserprofile().getRecievingCountryList()){
				Usereceivingcountry userReceivingCountry = null;
			if(receiving.getId() > 0){
				userReceivingCountry =  usereceivingcountryRepository.getAllUserReceivingCountryById(BigInteger.valueOf(receiving.getId()));
			}
			if(userReceivingCountry == null){
				userReceivingCountry = new Usereceivingcountry();
			}
			if(receiving.getRecievingCountry() >= 0){
			userReceivingCountry.setReceivingCountry(String.valueOf(receiving.getRecievingCountry()));
			}
			userReceivingCountry.setUserprofile(userProfile);
			userReceivingCountry = usereceivingcountryRepository.saveAndFlush(userReceivingCountry);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		}
		
		/**
		 * To update the MessageRefId
		 */
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		Messagerefid messageRefId = ctccommonDropdownService.findMessageRefIdByDate(date);
		if(messageRefId != null && hidefvo.getUserprofile().getMessageRefID() != null){
			String newMessageRefId = hidefvo.getUserprofile().getMessageRefID();
			messageRefId.setMessagerefid(newMessageRefId.substring(newMessageRefId.length() - 4));
			messageRefId = messagerefidRepository.saveAndFlush(messageRefId);
		}
		
		
		return hidefvo;
	}

	public String fetchProperties(String propertyName) {
		Properties properties = new Properties();
		String value = null;
		try {
			File file = ResourceUtils.getFile("classpath:application.properties");
			InputStream in = new FileInputStream(file);
			properties.load(in);
			value = properties.getProperty(propertyName);
		} catch (IOException e) {
		}
		return value;
	}

	@Override
	public HidefVo viewAllDatabyCBCId(HidefVo hidefvo, String id) {
		// TODO Auto-generated method stub
		Cbcpayldhdr payldhdr = cbcpayldhdrRepository.getCbcDetailsById(new BigInteger(id));
		if (payldhdr != null) {
			MetaDataVo metaData = new MetaDataVo();
			metaData.setBinaryEncoding(payldhdr.getBinaryEncodingSchemeCd());
			metaData.setCommunicationType(payldhdr.getCommunicationTypeCd());
			metaData.setContact(payldhdr.getContact());
			metaData.setFileFormatCode(payldhdr.getFileFormatCD());
			metaData.setFormCreationTimeStamp(payldhdr.getFileCreateTs());
			metaData.setId(payldhdr.getId());
			metaData.setLanguage(payldhdr.getLanguage());
			metaData.setMessageRefId(payldhdr.getMessageRefId());
			metaData.setMessageTypeIndic(payldhdr.getMessageTypeIndic());
			metaData.setMsgType(payldhdr.getMessageType());
			metaData.setReportingPeriod(payldhdr.getReportingPeriod());
			metaData.setSendContactEmailAddress(payldhdr.getSenderContactEmailAddressTxt());
			metaData.setSenderFileId(payldhdr.getSenderFileId());
			metaData.setSendingCompanyIN(payldhdr.getSendingEntityIN());
			metaData.setSendingCountry(payldhdr.getSenderCountryCd());
			metaData.setWarning(payldhdr.getWarning());
			metaData.setFileTypeIndic(payldhdr.getFiletypeindic());
			if (payldhdr.getTaxYear() > 0) {
				metaData.setTaxYear(String.valueOf(payldhdr.getTaxYear()));
			}
			List<RecievingCountryVo> recievingCountryList = new ArrayList<RecievingCountryVo>();
			List<Cbcpayldreceivingcountry> receivingCountryList = cbcpayldreceivingcountryRepository
					.getAllReceivingCountry(payldhdr.getId());
			for (Cbcpayldreceivingcountry receivingCountry : receivingCountryList) {
				RecievingCountryVo receivingCountryVo = new RecievingCountryVo();
				receivingCountryVo.setId(receivingCountry.getId().intValue());
				receivingCountryVo.setRecievingCountry(Integer.parseInt(receivingCountry.getReceivingCountry()));
				recievingCountryList.add(receivingCountryVo);
			}
			metaData.setRecievingCountryList(recievingCountryList);
			hidefvo.setMetadata(metaData);

			/**
			 * ReportingEntity
			 */
			List<Cbcpayldentity> reportingEntity = cbcpayldentityRepository
					.getAllReportingEntityDetails(payldhdr.getId());
			if (reportingEntity != null && reportingEntity.size() > 0) {
				Cbcpayldentity reportEntityDetail = reportingEntity.get(0);
				ReportingEntityVo reportingEntityVo = new ReportingEntityVo();
				reportingEntityVo.setCorDocReferenceId(reportEntityDetail.getCorrDocRefId());
				reportingEntityVo.setReportingRole(reportEntityDetail.getReportingRole());
				;
				reportingEntityVo.setDocumentTypeIndicator(reportEntityDetail.getDocTypeIndic());
				reportingEntityVo.setTin(reportEntityDetail.getTin());
				reportingEntityVo.setTinIssuedBy(reportEntityDetail.getTintype());
				reportingEntityVo.setTinType(reportEntityDetail.getIssuedBy());
				reportingEntityVo.setId(reportEntityDetail.getId());
				reportingEntityVo.setDocumentReferenceId(reportEntityDetail.getDocRefId());

				List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();

				List<Cbcpayldrescountry> residentCountry = cbcpayldrescountryRepository
						.getAllPayldResidentCountryByObjectId(reportEntityDetail.getId());
				if (residentCountry != null && residentCountry.size() > 0) {
					for (Cbcpayldrescountry resident : residentCountry) {
						ResidentCountryVo residentCountryVo = new ResidentCountryVo();
						residentCountryVo.setId(resident.getId().intValue());
						if (resident.getResCountryCode() != null) {
							residentCountryVo.setResidentCountryCode(Integer.parseInt(resident.getResCountryCode()));
						}
						residentCountryList.add(residentCountryVo);
					}
				}
				reportingEntityVo.setResidentCountryList(residentCountryList);

				List<Cbcpayldname> nameList = cbcpayldnameRepository
						.getAllPayldNameDetailsByObjectId(reportEntityDetail.getId());
				List<NameVo> nameVoList = new ArrayList<NameVo>();
				if (nameList != null && nameList.size() > 0) {
					for (Cbcpayldname name : nameList) {
						NameVo nameVO = new NameVo();
						nameVO.setFirstName(name.getNameOrganisation());
						nameVO.setId(name.getId().intValue());
						nameVoList.add(nameVO);
					}
				}
				reportingEntityVo.setNameList(nameVoList);

				List<OrganisationInTypeVo> organisationInTypeList = new ArrayList<OrganisationInTypeVo>();
				List<Cbcpayldin> cbcPayldin = cbcpayldinRepository
						.getAllPayldInDetailsByObjectId(reportEntityDetail.getId());
				if (cbcPayldin != null && cbcPayldin.size() > 0) {
					for (Cbcpayldin cbcpayld : cbcPayldin) {
						OrganisationInTypeVo orgVo = new OrganisationInTypeVo();
						orgVo.setId(cbcpayld.getId().intValue());
						orgVo.setIn(cbcpayld.getTin());
						orgVo.setInType(cbcpayld.getINType());
						if (cbcpayld.getIssuedBy() != null) {
							orgVo.setIssuedBy(Integer.parseInt(cbcpayld.getIssuedBy()));
						}
						organisationInTypeList.add(orgVo);
					}
				}
				reportingEntityVo.setOrganisationInTypeList(organisationInTypeList);

				List<Cbcpayldaddress> cbcpayldaddress = cbcpayldaddressRepository
						.getAllPayldAddressByObjectId(reportEntityDetail.getId());
				List<AddressVo> addressList = new ArrayList<AddressVo>();
				if (cbcpayldaddress != null && cbcpayldaddress.size() > 0) {
					for (Cbcpayldaddress address : cbcpayldaddress) {
						AddressVo addVo = new AddressVo();
						addVo.setAddressFree(address.getAddressFree());
						addVo.setAddressType(address.getLegalAddressType());
						addVo.setAddressTypeId(address.getLegalAddressType());
						addVo.setBuildingIdentifier(address.getBuildingIdentifier());
						addVo.setCity(address.getCity());
						addVo.setCountryCode(address.getCountryCode());
						addVo.setCountryCodeId(address.getCountryCode());
						addVo.setCountrySubentity(address.getCountrySubentity());
						addVo.setDistrictName(address.getDistrictName());
						addVo.setFloorIdentifier(address.getFloorIdentifier());
						addVo.setId(address.getId().intValue());
						addVo.setPob(address.getPob());
						addVo.setPostCode(address.getPostCode());
						addVo.setStreet(address.getStreet());
						addVo.setSuitIdentifier(address.getSuiteIdentifier());
						addressList.add(addVo);
					}
				}
				reportingEntityVo.setAddressList(addressList);
				hidefvo.setReportingEntity(reportingEntityVo);

				/**
				 * CBC Reports
				 */
				List<Cbcpayldreport> cbcReports = cbcpayldreportRepository
						.getAllPayldCBCReportsByObjectId(payldhdr.getId());
				List<CBCRepotsVo> cbcReportsList = new ArrayList<CBCRepotsVo>();
				if (cbcReports != null && cbcReports.size() > 0) {
					for (Cbcpayldreport reports : cbcReports) {
						CBCRepotsVo cbcReport = new CBCRepotsVo();
						/*DecimalFormat formatter = new DecimalFormat("###,###,###,###.00");*/
						if (reports.getAssetsAmt() != null) {

							cbcReport.setAssertAmount(
									String.valueOf(reports.getAssetsAmt()));
						}
						cbcReport.setAssertCurrCode(reports.getAssetsCurrCode());
						if (reports.getCapitalAmt() != null) {
							cbcReport.setCapitalAmount(
									String.valueOf(reports.getCapitalAmt()));
						}
						cbcReport.setCapitalCurrCode(reports.getCapitalCurrCode());
						cbcReport.setCorrDocRefId(reports.getCorrDocRefId());
						// cbcReport.setCorrMessageRefId(reports.get);
						cbcReport.setDocumentRefId(reports.getDocRefId());
						cbcReport.setDocumentTypeIndicator(reports.getDocTypeIndic());
						if (reports.getEarningsAmt() != null) {
							cbcReport.setEarningAmount(
									String.valueOf(reports.getEarningsAmt()));
						}
						cbcReport.setEarningCurrCode(reports.getEarningsCurrCode());
						cbcReport.setId(reports.getId().intValue());
						// cbcReport.setIncorpCountryCode(reports.get);
						// cbcReport.setIssuedBy(reports.get);
						if (reports.getNbEmployees() >= 0) {
							cbcReport.setNbEmployees(String.valueOf(reports.getNbEmployees()));
						}
						// cbcReport.setOtherEntityInfo(reports.get);
						if (reports.getProfitOrLossAmt() != null) {
							cbcReport.setPrfitotloassAmount(
									String.valueOf(reports.getProfitOrLossAmt()));
						}
						cbcReport.setProfitorlossCurrCode(reports.getCapitalCurrCode());
						if (reports.getRevenuesRelatedAmt() != null) {
							cbcReport.setRelatedAmount(
									String.valueOf(reports.getRevenuesRelatedAmt()));
						}
						cbcReport.setUnrelateCurrCode(reports.getRevenuesUnrelatedCurrCode());
						if (reports.getRevenuesUnrelatedAmt() != null) {
							cbcReport.setUnrelatedAmount(
									String.valueOf(reports.getRevenuesUnrelatedAmt()));
						}
						cbcReport.setRelatedCurrCode(reports.getRevenuesRelatedCurrCode());
						cbcReport.setResidentCountryCode(reports.getResCountryCode());
						if (reports.getTaxAccruedAmt() != null) {
							cbcReport.setTaxaccruedAmount(
									String.valueOf(reports.getTaxAccruedAmt()));
						}
						if (reports.getRevenuesTotalAmt() != null) {
							cbcReport.setTotalRevenueAmount(
									String.valueOf(reports.getRevenuesTotalAmt()));
						}
						cbcReport.setTotalRevenueCurrCode(reports.getRevenuesTotalCurrCode());

						cbcReport.setTaxaccruedCurrCode(reports.getTaxAccruedCurrCode());
						if (reports.getTaxPaidAmt() != null) {
							cbcReport.setTaxpaidAmount(
									String.valueOf(reports.getTaxPaidAmt()));
						}
						cbcReport.setTaxpiadCurrCode(reports.getTaxPaidCurrCode());

						List<Cbcpayldconstentity> consentityList = cbcpayldconstentityRepository
								.getAllconstentityByReportsId(reports.getId());
						cbcReport.setConstituentEntityList(new ArrayList<CbcConstituentEntityVO>());
						for(Cbcpayldconstentity consentity : consentityList) {
						CbcConstituentEntityVO cbcConsEntity = new CbcConstituentEntityVO();	
						if (consentity != null) {
							cbcConsEntity.setTin(consentity.getTin());
							cbcConsEntity.setTinType(consentity.getINType());
							cbcConsEntity.setIssuedBy(consentity.getIssuedBy());
							cbcConsEntity.setIncorpCountryCode(consentity.getIncorpCountryCode());
							cbcConsEntity.setOtherEntityInfo(consentity.getOtherEntityInfo());
							cbcConsEntity.setConsId(consentity.getId().intValue());

							List<ResidentCountryVo> reportsresidentCountryList = new ArrayList<ResidentCountryVo>();

							List<Cbcpayldrescountry> cbcReportsresidentCountry = cbcpayldrescountryRepository
									.getAllPayldResidentCountryByObjectId(consentity.getId());
							if (cbcReportsresidentCountry != null && cbcReportsresidentCountry.size() > 0) {
								for (Cbcpayldrescountry resident : cbcReportsresidentCountry) {
									ResidentCountryVo residentCountryVo = new ResidentCountryVo();
									residentCountryVo.setId(resident.getId().intValue());
									if (resident.getResCountryCode() != null) {
										residentCountryVo
												.setResidentCountryCode(Integer.parseInt(resident.getResCountryCode()));
									}
									reportsresidentCountryList.add(residentCountryVo);
								}
							}
							cbcConsEntity.setResidentCountryList(reportsresidentCountryList);

							List<Cbcpayldname> cbcReporttsnameList = cbcpayldnameRepository
									.getAllPayldNameDetailsByObjectId(consentity.getId());
							List<NameVo> cbcReportsnameVoList = new ArrayList<NameVo>();
							if (cbcReporttsnameList != null && cbcReporttsnameList.size() > 0) {
								for (Cbcpayldname name : cbcReporttsnameList) {
									NameVo nameVO = new NameVo();
									nameVO.setFirstName(name.getNameOrganisation());
									nameVO.setId(name.getId().intValue());
									cbcReportsnameVoList.add(nameVO);
								}
							}
							cbcConsEntity.setNameList(cbcReportsnameVoList);

							List<OrganisationInTypeVo> cbcReportsorganisationInTypeList = new ArrayList<OrganisationInTypeVo>();
							List<Cbcpayldin> cbcreportsPayldin = cbcpayldinRepository
									.getAllPayldInDetailsByObjectId(consentity.getId());
							if (cbcreportsPayldin != null && cbcreportsPayldin.size() > 0) {
								for (Cbcpayldin cbcpayld : cbcreportsPayldin) {
									OrganisationInTypeVo orgVo = new OrganisationInTypeVo();
									orgVo.setId(cbcpayld.getId().intValue());
									orgVo.setIn(cbcpayld.getTin());
									orgVo.setInType(cbcpayld.getINType());
									if (cbcpayld.getIssuedBy() != null) {
										orgVo.setIssuedBy(Integer.parseInt(cbcpayld.getIssuedBy()));
									}
									cbcReportsorganisationInTypeList.add(orgVo);
								}
							}
							cbcConsEntity.setOrganisationInTypeList(cbcReportsorganisationInTypeList);

							List<Cbcpayldbizactiv> bizList = cbcpayldbizactivRepository
									.getAllBizActivitiesByConsentID(consentity.getId());
							List<BizActivitiesTypeVo> bizActivitiesList = new ArrayList<BizActivitiesTypeVo>();
							if (bizList != null && bizList.size() > 0) {
								for (Cbcpayldbizactiv biz : bizList) {
									BizActivitiesTypeVo bizVo = new BizActivitiesTypeVo();
									bizVo.setId(biz.getId().intValue());
									if (biz.getBizActivities() != null) {
										bizVo.setBizType(Integer.parseInt(biz.getBizActivities()));
									}
									bizActivitiesList.add(bizVo);
								}
								cbcConsEntity.setBizActivitiesList(bizActivitiesList);
							}

							List<Cbcpayldaddress> cbcreportspayldaddress = cbcpayldaddressRepository
									.getAllPayldAddressByObjectId(consentity.getId());
							List<AddressVo> cbcReportsaddressList = new ArrayList<AddressVo>();
							if (cbcreportspayldaddress != null && cbcreportspayldaddress.size() > 0) {
								for (Cbcpayldaddress address : cbcreportspayldaddress) {
									AddressVo addVo = new AddressVo();
									addVo.setAddressFree(address.getAddressFree());
									addVo.setAddressType(address.getLegalAddressType());
									addVo.setAddressTypeId(address.getLegalAddressType());
									addVo.setBuildingIdentifier(address.getBuildingIdentifier());
									addVo.setCity(address.getCity());
									addVo.setCountryCode(address.getCountryCode());
									addVo.setCountryCodeId(address.getCountryCode());
									addVo.setCountrySubentity(address.getCountrySubentity());
									addVo.setDistrictName(address.getDistrictName());
									addVo.setFloorIdentifier(address.getFloorIdentifier());
									addVo.setId(address.getId().intValue());
									addVo.setPob(address.getPob());
									addVo.setPostCode(address.getPostCode());
									addVo.setStreet(address.getStreet());
									addVo.setSuitIdentifier(address.getSuiteIdentifier());
									cbcReportsaddressList.add(addVo);
								}
							}
							cbcConsEntity.setAddressList(cbcReportsaddressList);
							cbcReport.getConstituentEntityList().add(cbcConsEntity);
						}
					}
						cbcReportsList.add(cbcReport);

					}
					hidefvo.setListCBCReports(cbcReportsList);
				}

				/**
				 * Additional Info
				 */
				List<Cbcpayldaddinfo> addInfo = cbcpayldaddinfoRepository
						.getAllAdditionalInfoByHdrIDId(payldhdr.getId());
				List<CbcAdditionalInfo> addInfoList = new ArrayList<CbcAdditionalInfo>();
				if (addInfo != null && addInfo.size() > 0) {
					for (Cbcpayldaddinfo add : addInfo) {

						CbcAdditionalInfo sddVo = new CbcAdditionalInfo();
						sddVo.setCorDocumentRefId(add.getCorrDocRefId());
						// sddVo.setCorrMessageRefId(add.get);
						sddVo.setDocumentReferenceId(add.getDocRefId());
						sddVo.setDocumentTypeIndic(add.getDocTypeIndic());
						sddVo.setId(add.getId().intValue());
						sddVo.setOtherInfo(add.getOtherInfo());

						List<ResidentCountryVo> reportsresidentCountryList = new ArrayList<ResidentCountryVo>();

						List<Cbcpayldrescountry> cbcReportsresidentCountry = cbcpayldrescountryRepository
								.getAllPayldResidentCountryByObjectId(add.getId());
						if (cbcReportsresidentCountry != null && cbcReportsresidentCountry.size() > 0) {
							for (Cbcpayldrescountry resident : cbcReportsresidentCountry) {
								ResidentCountryVo residentCountryVo = new ResidentCountryVo();
								residentCountryVo.setId(resident.getId().intValue());
								if (resident.getResCountryCode() != null) {
									residentCountryVo
											.setResidentCountryCode(Integer.parseInt(resident.getResCountryCode()));
								}
								reportsresidentCountryList.add(residentCountryVo);
							}
						}
						sddVo.setResidentCountryList(reportsresidentCountryList);

						List<Cbcpayldsumref> summaryList = cbcpayldsumrefRepository
								.getAllSummaryByAddinfoIDId(add.getId());
						List<SummaryVo> summaryVoList = new ArrayList<SummaryVo>();
						if (summaryList != null && summaryList.size() > 0) {
							for (Cbcpayldsumref sum : summaryList) {
								SummaryVo summaryVo = new SummaryVo();
								summaryVo.setId(sum.getId().intValue());
								if (sum.getSummaryRef() != null) {
									summaryVo.setSummeryReference(Integer.parseInt(sum.getSummaryRef()));
								}
								summaryVoList.add(summaryVo);

							}

						}
						sddVo.setSummaryList(summaryVoList);

						addInfoList.add(sddVo);

					}

				}
				hidefvo.setAddInfoList(addInfoList);

			}

		}
		return hidefvo;
	}

	@SuppressWarnings("deprecation")
	@Override
	public HidefVo saveExcelFile(HidefVo hidef) throws IllegalStateException, IOException, ParseException {
		// TODO Auto-generated method stub
		HidefVo hidefVo = new HidefVo();
		String excelWorkPath = fetchProperties("excelWorkPath");
		File file = new File(excelWorkPath + "/" +hidef.getImportExcelFileName());
		hidef.getImportExcelFile().transferTo(file);
		FileInputStream excelFile = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(excelFile);

		// Metadata - data pick up code
		Sheet datatypeSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();
		hidefVo.setMetadata(new MetaDataVo());
		hidefVo.setUserprofile(new UserProfileVo());
		while (iterator.hasNext()) {

			Row currentRow = iterator.next();
			if (currentRow.getRowNum() >= 2) {
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell != null && currentCell.getCellType() != Cell.CELL_TYPE_BLANK) {
						if (currentCell.getColumnIndex() == 1) {
							if (currentRow.getRowNum() == 2) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getMetadata()
											.setSendingCountry("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setSendingCountry(currentCell.getStringCellValue());
								}
							} else if (currentRow.getRowNum() == 3) {
								List<RecievingCountryVo> receivingCountryList = new ArrayList<RecievingCountryVo>();
								String recicingCountryListFromExcel = currentCell.getStringCellValue();
								String[] rcArrayList = recicingCountryListFromExcel.split(",");
								
								for(String str: rcArrayList) {
									RecievingCountryVo coutryVo = new RecievingCountryVo();
									coutryVo.setId(Integer.parseInt(str));
									receivingCountryList.add(coutryVo);
								}
								
								/*RecievingCountryVo coutryVo = new RecievingCountryVo();
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									coutryVo.setId(Math.toIntExact(Math.round(currentCell.getNumericCellValue())));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									coutryVo.setId(Integer.parseInt(currentCell.getStringCellValue()));
								}
								receivingCountryList.add(coutryVo);*/
								hidefVo.getMetadata().setRecievingCountryList(receivingCountryList);
							} else if (currentRow.getRowNum() == 4) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getMetadata()
											.setCommunicationType("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setCommunicationType(currentCell.getStringCellValue());
								}
							} else if (currentRow.getRowNum() == 5) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getMetadata()
											.setSenderFileId("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setSenderFileId(currentCell.getStringCellValue());
								}
							} else if (currentRow.getRowNum() == 6) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getMetadata()
											.setFileFormatCode("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setFileFormatCode(currentCell.getStringCellValue());
								}
							} else if (currentRow.getRowNum() == 7) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getMetadata()
											.setBinaryEncoding("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setBinaryEncoding(currentCell.getStringCellValue());
								}
							} else if (currentRow.getRowNum() == 8) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getMetadata().setFormCreationTimeStamp(
											"" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setFormCreationTimeStamp(currentCell.getStringCellValue());
								}
							} else if (currentRow.getRowNum() == 9) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getMetadata()
											.setTaxYear("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setTaxYear(currentCell.getStringCellValue());
								}
							} else if (currentRow.getRowNum() == 10) {
								String fileTypeIndic = "";
								if (Math.round(currentCell.getNumericCellValue()) == 1L) {
									fileTypeIndic = "true";
								} else {
									fileTypeIndic = "false";
								}
								hidefVo.getMetadata().setFileTypeIndic(fileTypeIndic);
							} else if (currentRow.getRowNum() == 11) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getUserprofile()
											.setCtsTransId("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getUserprofile().setCtsTransId(currentCell.getStringCellValue());
								}
							} else if (currentRow.getRowNum() == 12) {
								hidefVo.getMetadata().setSenderContactEmailAddress(currentCell.getStringCellValue());
							} else if (currentRow.getRowNum() == 13) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getMetadata()
											.setMsgType("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setMsgType(currentCell.getStringCellValue());
								}
							} else if (currentRow.getRowNum() == 14) {
								hidefVo.getMetadata().setWarning(currentCell.getStringCellValue());
							} else if (currentRow.getRowNum() == 15) {
								hidefVo.getMetadata().setContact(currentCell.getStringCellValue());
							} else if (currentRow.getRowNum() == 16) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
																
									String fromExcelPattern = "yyyy-MM-dd";
									SimpleDateFormat sdf = new SimpleDateFormat(fromExcelPattern);
									String date = sdf.format(currentCell.getDateCellValue());
									
									/*String pattern = "yyyy-MM-dd";	
									SimpleDateFormat sdf_todb = new SimpleDateFormat(pattern);*/
									hidefVo.getMetadata().setReportingPeriod(date);
									
									
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									String fromExcelPattern = "MM/dd/yyyy";
									SimpleDateFormat sdf = new SimpleDateFormat(fromExcelPattern);
									Date date = sdf.parse(currentCell.getStringCellValue());
									
									String pattern = "yyyy-MM-dd";	
									SimpleDateFormat sdf_todb = new SimpleDateFormat(pattern);
									hidefVo.getMetadata().setReportingPeriod(sdf_todb.format(date));
								}
							} else if (currentRow.getRowNum() == 17) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getMetadata()
											.setSendingCompanyIN("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setSendingCompanyIN(currentCell.getStringCellValue());
								}
							} else if (currentRow.getRowNum() == 18) {
								hidefVo.getMetadata().setLanguage(currentCell.getStringCellValue());
							} else if (currentRow.getRowNum() == 19) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getMetadata()
											.setMessageTypeIndic("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setMessageTypeIndic(currentCell.getStringCellValue());
								}
							} else if (currentRow.getRowNum() == 20) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getMetadata()
											.setMessageRefId("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setMessageRefId(currentCell.getStringCellValue());
								}
							}

						}
					}
				}
			}
		}
		
		
		//set SENDER FILE ID, FILE CREATION TIMESTAMP, TRANSMISSION ID AND MESSAGE REF ID
		if(hidefVo!= null && hidefVo.getMetadata() != null && hidefVo.getMetadata().getTaxYear() != null && !hidefVo.getMetadata().getTaxYear().isEmpty()) {
			hidefVo.setMycbcId(hidef.getMycbcId());
			hidefVo = getSenderFileID(hidefVo,"CBC");
			
			String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String fileCreationTimeStamp = sdf.format(new Date());
			hidefVo.getMetadata().setFormCreationTimeStamp(fileCreationTimeStamp);
			
			hidefVo = getMessageRefId(hidefVo,"CBC");
			
			
			if(hidefVo != null && hidefVo.getUserprofile() != null) {
			String patternForTransmissionId = "yyyyMMdd'T'HHmmssSSS'Z'";
			  SimpleDateFormat sdfFileName = new SimpleDateFormat(patternForTransmissionId);
			  String transmissionTimeString = sdfFileName.format(new Date(System.currentTimeMillis()));
			  hidefVo.getUserprofile().setCtsTransId(hidefVo.getMycbcId()+"_"+hidefVo.getMetadata().getTaxYear()+"_CBC_"+transmissionTimeString);
			}
		}
		
		
		

		// reporting Entity Sheet - data pick up code
		Sheet reportingEntitySheet = workbook.getSheetAt(1);
		Iterator<Row> iteratorReportingEntity = reportingEntitySheet.iterator();
		hidefVo.setReportingEntity(new ReportingEntityVo());
		int tinLength = 0;
		int tinType = 0;
		int tinIssuedBy = 0;
		int reportingRole = 0;
		int docTypeIndic = 0;
		int docReferenceId = 0;
		int corMessageRefId = 0;
		int corDocumentRefId = 0;
		while (iteratorReportingEntity.hasNext()) {
			Row currentRow = iteratorReportingEntity.next();
			if (currentRow.getRowNum() >= 2) {
				Iterator<Cell> cellIterator = currentRow.iterator();
				AddressVo addressVo = null;
				ResidentCountryVo residentVo = null;
				OrganisationInTypeVo orgINVo = null;
				NameVo nameVo = null;
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell != null && currentCell.getCellType() != Cell.CELL_TYPE_BLANK) {
						if (currentCell.getColumnIndex() == 0) {
							if (tinLength == 0) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getReportingEntity()
											.setTin("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getReportingEntity().setTin(currentCell.getStringCellValue());
								}
								tinLength += 1;
							}
						}
						if (currentCell.getColumnIndex() == 1) {
							if (tinType == 0) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getReportingEntity()
											.setTinType("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getReportingEntity().setTinType(currentCell.getStringCellValue());
								}
								tinType += 1;
							}
						}
						if (currentCell.getColumnIndex() == 2) {
							if (tinIssuedBy == 0) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getReportingEntity()
											.setTinIssuedBy("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getReportingEntity().setTinIssuedBy(currentCell.getStringCellValue());
								}
								tinIssuedBy += 1;
							}
						}
						if (currentCell.getColumnIndex() == 3) {
							if (reportingRole == 0) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getReportingEntity()
											.setReportingRole("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getReportingEntity().setReportingRole(currentCell.getStringCellValue());
								}
								reportingRole += 1;
							}
						}
						if (currentCell.getColumnIndex() == 4) {
							if (docTypeIndic == 0) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getReportingEntity().setDocumentTypeIndicator(
											"" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getReportingEntity()
											.setDocumentTypeIndicator(currentCell.getStringCellValue());
								}
								docTypeIndic += 1;
							}
						}
						if (currentCell.getColumnIndex() == 5) {
							if (docReferenceId == 0) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getReportingEntity()
											.setDocumentReferenceId("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getReportingEntity()
											.setDocumentReferenceId(currentCell.getStringCellValue());
								}
								docReferenceId += 1;
							}
						}
						if (currentCell.getColumnIndex() == 6) {
							if (corMessageRefId == 0) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getReportingEntity().setCorMessageReferenceId(
											"" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getReportingEntity()
											.setCorMessageReferenceId(currentCell.getStringCellValue());
								}
								corMessageRefId += 1;
							}
						}
						if (currentCell.getColumnIndex() == 7) {
							if (corDocumentRefId == 0) {
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									hidefVo.getReportingEntity()
											.setCorDocReferenceId("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getReportingEntity().setCorDocReferenceId(currentCell.getStringCellValue());
								}
								corDocumentRefId += 1;
							}
						}
						if (currentCell.getColumnIndex() == 8) {
							if (residentVo == null) {
								residentVo = new ResidentCountryVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {

								int residentId = Math.toIntExact(Math.round(currentCell.getNumericCellValue()));
								residentVo.setResidentCountryCode(residentId);

							} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
								int residentId = Integer.parseInt(currentCell.getStringCellValue());
								residentVo.setResidentCountryCode(residentId);

							}
						}
						if (currentCell.getColumnIndex() == 9) {
							if (nameVo == null) {
								nameVo = new NameVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {

								nameVo.setFirstName(currentCell.getStringCellValue());

							}
						}

						if (currentCell.getColumnIndex() == 10) {
							if (orgINVo == null) {
								orgINVo = new OrganisationInTypeVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								orgINVo.setIn(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								orgINVo.setIn("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 11) {
							if (orgINVo == null) {
								orgINVo = new OrganisationInTypeVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								orgINVo.setInType(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								orgINVo.setInType("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 12) {
							if (orgINVo == null) {
								orgINVo = new OrganisationInTypeVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								orgINVo.setIssuedBy(Integer.parseInt(currentCell.getStringCellValue()));
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								orgINVo.setIssuedBy(
										Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
							}
						}

						if (currentCell.getColumnIndex() == 13) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setCountryCode(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 14) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setAddressType(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 15) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setAddressFree(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 16) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setStreet(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 17) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setBuildingIdentifier(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 18) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setSuitIdentifier(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 19) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setFloorIdentifier(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 20) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setDistrictName(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 21) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setPob(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 22) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setPostCode(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 23) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setCity(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 24) {
							if (addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setCountrySubentity(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setCountrySubentity("" + Math.round(currentCell.getNumericCellValue()));
							}
						}

					}else if(currentCell != null && currentCell.getCellType() == Cell.CELL_TYPE_BLANK) {
						if(currentCell.getColumnIndex() == 5) {
							if(docReferenceId == 0) {
								hidefVo = getDocRefId(hidefVo,"","CBC");
								docReferenceId += 1;
							}
							
						}
					}
				}

				if (nameVo != null) {
					if (hidefVo.getReportingEntity().getNameList() != null
							&& !hidefVo.getReportingEntity().getNameList().isEmpty()) {
						hidefVo.getReportingEntity().getNameList().add(nameVo);
					} else {
						hidefVo.getReportingEntity().setNameList(new ArrayList<NameVo>());
						hidefVo.getReportingEntity().getNameList().add(nameVo);
					}
				}

				if (orgINVo != null) {
					if (hidefVo.getReportingEntity().getOrganisationInTypeList() != null
							&& !hidefVo.getReportingEntity().getOrganisationInTypeList().isEmpty()) {
						hidefVo.getReportingEntity().getOrganisationInTypeList().add(orgINVo);
					} else {
						hidefVo.getReportingEntity().setOrganisationInTypeList(new ArrayList<OrganisationInTypeVo>());
						hidefVo.getReportingEntity().getOrganisationInTypeList().add(orgINVo);
					}
				}

				if (residentVo != null) {
					if (hidefVo.getReportingEntity().getResidentCountryList() != null
							&& !hidefVo.getReportingEntity().getResidentCountryList().isEmpty()) {
						hidefVo.getReportingEntity().getResidentCountryList().add(residentVo);
					} else {
						hidefVo.getReportingEntity().setResidentCountryList(new ArrayList<ResidentCountryVo>());
						hidefVo.getReportingEntity().getResidentCountryList().add(residentVo);
					}
				}

				if (addressVo != null) {
					if (hidefVo.getReportingEntity().getAddressList() != null
							&& !hidefVo.getReportingEntity().getAddressList().isEmpty()) {
						hidefVo.getReportingEntity().getAddressList().add(addressVo);
					} else {
						hidefVo.getReportingEntity().setAddressList(new ArrayList<AddressVo>());
						hidefVo.getReportingEntity().getAddressList().add(addressVo);
					}
				}
			}
		}

		Sheet cbcReportsSheet = workbook.getSheetAt(2);
		Iterator<Row> cbcReportsIterator = cbcReportsSheet.iterator();
		hidefVo.setListCBCReports(new ArrayList<CBCRepotsVo>());
        CBCRepotsVo reports = null;
		while (cbcReportsIterator.hasNext()) {
		Row currentRow = cbcReportsIterator.next();
		if (currentRow.getRowNum() >= 2) {
			Iterator<Cell> cellIterator = currentRow.iterator();			
			BizActivitiesTypeVo bizActivitiesVo = null;
			ResidentCountryVo residentVo = null;
			NameVo nameVo = null;
			OrganisationInTypeVo orgINVo = null;
			AddressVo addressVo = null;
						
			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				if (currentCell != null && currentCell.getCellType() != Cell.CELL_TYPE_BLANK) {

					if (currentCell.getColumnIndex() == 0) {
						
                        if(currentRow.getRowNum() != 2) {
                        	if(reports != null) {
                        	if(reports.getConstituentEntity() != null && reports.getConstituentEntityList() != null) {
                        		reports.getConstituentEntityList().add(reports.getConstituentEntity());
                        	}else {
                        		reports.setConstituentEntityList(new ArrayList<CbcConstituentEntityVO>());
                        		reports.getConstituentEntityList().add(reports.getConstituentEntity());
                        	}	
							hidefVo.getListCBCReports().add(reports);
                        	}
							reports = null;
						}
						reports = new CBCRepotsVo();
						reports.setConstituentEntityList(new ArrayList<CbcConstituentEntityVO>());
						reports.setConstituentEntity(new CbcConstituentEntityVO());
						reports.getConstituentEntity().setBizActivitiesList(new ArrayList<BizActivitiesTypeVo>());
						reports.getConstituentEntity().setResidentCountryList(new ArrayList<ResidentCountryVo>());
						reports.getConstituentEntity().setNameList(new ArrayList<NameVo>());
						reports.getConstituentEntity().setOrganisationInTypeList(new ArrayList<OrganisationInTypeVo>());
						reports.getConstituentEntity().setAddressList(new ArrayList<AddressVo>());
						
						/*if (reports == null) {
							reports = new CBCRepotsVo();
							reports.setExcelId(reports.getExcelId()+1);
							if(reports.getConstituentEntity() == null) {
								reports.setConstituentEntity(new CbcConstituentEntityVO());
								reports.setConstituentEntityList(new ArrayList<CbcConstituentEntityVO>());
								reports.getConstituentEntity().setExcelId(reports.getConstituentEntity().getExcelId()+1);
							}
						}else {
							int reExcelId = reports.getExcelId()+1;
							int ceExcelId = reports.getConstituentEntity().getExcelId()+1;
							reports = null;

							reports = new CBCRepotsVo();
							reports.setExcelId(reExcelId+1);
							if(reports.getConstituentEntity() == null) {
								reports.setConstituentEntity(new CbcConstituentEntityVO());
								reports.setConstituentEntityList(new ArrayList<CbcConstituentEntityVO>());
								reports.getConstituentEntity().setExcelId(ceExcelId+1);
							}
						
						}*/
						
						
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setDocumentTypeIndicator(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setDocumentTypeIndicator("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 1) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setDocumentRefId(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setDocumentRefId("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 2) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setCorrMessageRefId(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setCorrMessageRefId("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 3) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setCorrDocRefId(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setCorrDocRefId("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 4) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setNbEmployees(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setNbEmployees("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 5) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setResidentCountryCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setResidentCountryCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 6) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setUnrelateCurrCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setUnrelateCurrCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 7) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setUnrelatedAmount(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setUnrelatedAmount("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 8) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setRelatedCurrCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setRelatedCurrCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 9) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setRelatedAmount(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setRelatedAmount("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 10) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setTotalRevenueCurrCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setTotalRevenueCurrCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 11) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setTotalRevenueAmount(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setTotalRevenueAmount("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 12) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setProfitorlossCurrCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setProfitorlossCurrCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 13) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setPrfitotloassAmount(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setPrfitotloassAmount("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 14) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setTaxpiadCurrCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setTaxpiadCurrCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 15) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setTaxpaidAmount(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setTaxpaidAmount("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 16) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setTaxaccruedCurrCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setTaxaccruedCurrCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 17) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setTaxaccruedAmount(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setTaxaccruedAmount("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 18) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setCapitalCurrCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setCapitalCurrCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 19) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setCapitalAmount(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setCapitalAmount("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 20) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setEarningCurrCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setEarningCurrCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 21) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setEarningAmount(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setEarningAmount("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 22) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setAssertCurrCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setAssertCurrCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 23) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.setAssertAmount(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setAssertAmount("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					
					//Consttituent Entity Excel
					if(currentCell.getColumnIndex() == 24) {						
						if(currentRow.getRowNum() != 2) {
                        	if(reports != null && reports.getConstituentEntity() != null && reports.getConstituentEntity().getBizActivitiesList() != null && reports.getConstituentEntity().getBizActivitiesList().size() > 0) {
                        	if(reports.getConstituentEntityList() != null) {	
                        		reports.getConstituentEntityList().add(reports.getConstituentEntity());
                        	}else {
                        		reports.setConstituentEntityList(new ArrayList<CbcConstituentEntityVO>());
                        		reports.getConstituentEntityList().add(reports.getConstituentEntity());
                        	}
                        	}
							reports.setConstituentEntity(null);
							reports.setConstituentEntity(new CbcConstituentEntityVO());
						}else {
							reports.setConstituentEntity(null);
							reports.setConstituentEntity(new CbcConstituentEntityVO());
						}
					}
					
					
					if (currentCell.getColumnIndex() == 25) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.getConstituentEntity().setTin(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.getConstituentEntity().setTin("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 26) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.getConstituentEntity().setIssuedBy(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.getConstituentEntity().setIssuedBy("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 27) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.getConstituentEntity().setTinType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.getConstituentEntity().setTinType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 28) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.getConstituentEntity().setIncorpCountryCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.getConstituentEntity().setIncorpCountryCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 29) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.getConstituentEntity().setOtherEntityInfo(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.getConstituentEntity().setOtherEntityInfo("" + Math.round(currentCell.getNumericCellValue()));
						}
					}

					if (currentCell.getColumnIndex() == 30) {
						if (bizActivitiesVo == null) {
							bizActivitiesVo = new BizActivitiesTypeVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							bizActivitiesVo.setId(Integer.parseInt(currentCell.getStringCellValue()));

						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							bizActivitiesVo
									.setId(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));

						}
					}

					if (currentCell.getColumnIndex() == 31) {
						if (residentVo == null) {
							residentVo = new ResidentCountryVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {

							int residentId = Integer.parseInt(currentCell.getStringCellValue());
							residentVo.setResidentCountryCode(residentId);
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							int residentId = Integer.parseInt("" + Math.round(currentCell.getNumericCellValue()));
							residentVo.setResidentCountryCode(residentId);
						}
					}
					if (currentCell.getColumnIndex() == 32) {
						if (nameVo == null) {
							nameVo = new NameVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							nameVo.setFirstName(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							int residentId = Integer.parseInt("" + Math.round(currentCell.getNumericCellValue()));
							nameVo.setFirstName("" + residentId);
						}
					}

					if (currentCell.getColumnIndex() == 33) {
						if (orgINVo == null) {
							orgINVo = new OrganisationInTypeVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setIn(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setIn("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 34) {
						if (orgINVo == null) {
							orgINVo = new OrganisationInTypeVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setInType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setInType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 35) {
						if (orgINVo == null) {
							orgINVo = new OrganisationInTypeVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setIssuedBy(Integer.parseInt(currentCell.getStringCellValue()));
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setIssuedBy(
									Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
						}
					}

					if (currentCell.getColumnIndex() == 36) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCountryCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 37) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setAddressType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 38) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setAddressFree(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 39) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setStreet(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 40) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setBuildingIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 41) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setSuitIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 42) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setFloorIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 43) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setDistrictName(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 44) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setPob(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 45) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setPostCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 46) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCity(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 47) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCountrySubentity(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCountrySubentity("" + Math.round(currentCell.getNumericCellValue()));
						}
					}

				} else if(currentCell != null && currentCell.getCellType() == Cell.CELL_TYPE_BLANK){
					if(currentCell.getColumnIndex() == 1) {
						if(hidefVo.getDocRefId() != null) {
						String nextDocRef = converToString(Integer.parseInt(hidefVo.getDocRefId())+1);
						hidefVo.setDocRefId(nextDocRef);
						reports.setDocumentRefId(hidefVo.getDocRefIdStaticText()+"R"+nextDocRef);
						}
					}
				}
			}

			if (bizActivitiesVo != null) {
				if (reports.getConstituentEntity().getBizActivitiesList() != null && !reports.getConstituentEntity().getBizActivitiesList().isEmpty()) {
					reports.getConstituentEntity().getBizActivitiesList().add(bizActivitiesVo);
				} else {
					reports.getConstituentEntity().setBizActivitiesList(new ArrayList<BizActivitiesTypeVo>());
					reports.getConstituentEntity().getBizActivitiesList().add(bizActivitiesVo);
				}
			}

			if (residentVo != null) {
				if (reports.getConstituentEntity().getResidentCountryList() != null && !reports.getConstituentEntity().getResidentCountryList().isEmpty()) {
					reports.getConstituentEntity().getResidentCountryList().add(residentVo);
				} else {
					reports.getConstituentEntity().setResidentCountryList(new ArrayList<ResidentCountryVo>());
					reports.getConstituentEntity().getResidentCountryList().add(residentVo);
				}
			}

			if (nameVo != null) {
				if (reports.getConstituentEntity().getNameList() != null && !reports.getConstituentEntity().getNameList().isEmpty()) {
					reports.getConstituentEntity().getNameList().add(nameVo);
				} else {
					reports.getConstituentEntity().setNameList(new ArrayList<NameVo>());
					reports.getConstituentEntity().getNameList().add(nameVo);
				}
			}

			if (orgINVo != null) {
				if (reports.getConstituentEntity().getOrganisationInTypeList() != null && !reports.getConstituentEntity().getOrganisationInTypeList().isEmpty()) {
					reports.getConstituentEntity().getOrganisationInTypeList().add(orgINVo);
				} else {
					reports.getConstituentEntity().setOrganisationInTypeList(new ArrayList<OrganisationInTypeVo>());
					reports.getConstituentEntity().getOrganisationInTypeList().add(orgINVo);
				}
			}

			if (addressVo != null) {
				if (reports.getConstituentEntity().getAddressList() != null && !reports.getConstituentEntity().getAddressList().isEmpty()) {
					reports.getConstituentEntity().getAddressList().add(addressVo);
				} else {
					reports.getConstituentEntity().setAddressList(new ArrayList<AddressVo>());
					reports.getConstituentEntity().getAddressList().add(addressVo);
				}
			}
			
			/*if(reports != null && reports.getConstituentEntity() != null) {
				
				Iterator<CbcConstituentEntityVO> iteratorCE = reports.getConstituentEntityList().iterator();
				
				while(iteratorCE.hasNext()) {
					CbcConstituentEntityVO ceIter = iteratorCE.next();
					if(ceIter.getExcelId() == reports.getConstituentEntity().getExcelId()) {
						iteratorCE.remove();
					}
				}
				
				if(reports.getConstituentEntity().getExcelId() != 0) {
				reports.getConstituentEntityList().add(reports.getConstituentEntity());
//				}
			}*/

			/*if (reports != null) {					
					Iterator<CBCRepotsVo> reportListIterator = hidefVo.getListCBCReports().iterator();
					
					while(reportListIterator.hasNext()) {						
						CBCRepotsVo reportsIter = reportListIterator.next();
						if(reportsIter.getExcelId() == reports.getExcelId()) {
							reportListIterator.remove();
						}						
					}
					
				if(reports.getExcelId() != 0) {	
				hidefVo.getListCBCReports().add(reports);
				}
			}*/
		}
	}
		if(reports != null && reports.getConstituentEntity() != null) {
			if(reports.getConstituentEntityList() != null) {
				reports.getConstituentEntityList().add(reports.getConstituentEntity());
			}else {
				reports.setConstituentEntityList(new ArrayList<CbcConstituentEntityVO>());
				reports.getConstituentEntityList().add(reports.getConstituentEntity());
			}
			
		}
		
		if(reports != null) {
			hidefVo.getListCBCReports().add(reports);
		}

		Sheet additionalInfoSheet = workbook.getSheetAt(3);
		Iterator<Row> additionalInfo = additionalInfoSheet.iterator();
		hidefVo.setAddInfoList(new ArrayList<CbcAdditionalInfo>());
		CbcAdditionalInfo addInfo = null;
		while (additionalInfo.hasNext()) {
			Row currentRow = additionalInfo.next();
			if (currentRow.getRowNum() >= 2) {
				Iterator<Cell> cellIterator = currentRow.iterator();
				ResidentCountryVo residentVo = null;
				SummaryVo summaryVo = null;

				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell != null && currentCell.getCellType() != Cell.CELL_TYPE_BLANK) {

						if (currentCell.getColumnIndex() == 0) {
							
							   if(currentRow.getRowNum() != 2) {
								   if(addInfo!= null) {
									hidefVo.getAddInfoList().add(addInfo);
								   }
									addInfo = null;
								}
							    
							   addInfo = new CbcAdditionalInfo();
							   addInfo.setResidentCountryList(new ArrayList<ResidentCountryVo>());
							   addInfo.setSummaryList(new ArrayList<SummaryVo>());
								
							/*if (addInfo == null) {
								addInfo = new CbcAdditionalInfo();
								int excelId = addInfo.getExcelId()+1;								
								addInfo.setExcelId(excelId);
							}else {
								int excelId = addInfo.getExcelId()+1;
								addInfo = null;
								addInfo = new CbcAdditionalInfo();
								addInfo.setExcelId(excelId);
							}*/
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addInfo.setDocumentTypeIndic(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addInfo.setDocumentTypeIndic("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 1) {
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addInfo.setDocumentReferenceId(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addInfo.setDocumentReferenceId("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 2) {
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addInfo.setCorrMessageRefId(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addInfo.setCorrMessageRefId("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 3) {
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addInfo.setCorDocumentRefId(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addInfo.setCorDocumentRefId("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 4) {
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addInfo.setOtherInfo(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addInfo.setOtherInfo("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 5) {
							if (residentVo == null) {
								residentVo = new ResidentCountryVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								int residentId = Integer.parseInt(currentCell.getStringCellValue());
								residentVo.setResidentCountryCode(residentId);
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								int residentId = Integer.parseInt("" + Math.round(currentCell.getNumericCellValue()));
								residentVo.setResidentCountryCode(residentId);
							}
						}
						if (currentCell.getColumnIndex() == 6) {
							if (summaryVo == null) {
								summaryVo = new SummaryVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								int summaryId = Integer.parseInt(currentCell.getStringCellValue());
								summaryVo.setId(summaryId);
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								int summaryId = Integer.parseInt("" + Math.round(currentCell.getNumericCellValue()));
								summaryVo.setId(summaryId);
							}
						}

					}else if(currentCell != null && currentCell.getCellType() == Cell.CELL_TYPE_BLANK){
						if(currentCell.getColumnIndex() == 1) {
							if(hidefVo.getDocRefId() != null) {
							String nextDocRef = converToString(Integer.parseInt(hidefVo.getDocRefId())+1);
							hidefVo.setDocRefId(nextDocRef);
							addInfo.setDocumentReferenceId(hidefVo.getDocRefIdStaticText()+"A"+nextDocRef);
							}
						}
					}
				}

				if (residentVo != null) {
					if (addInfo.getResidentCountryList() != null && !addInfo.getResidentCountryList().isEmpty()) {
						addInfo.getResidentCountryList().add(residentVo);
					} else {
						addInfo.setResidentCountryList(new ArrayList<ResidentCountryVo>());
						addInfo.getResidentCountryList().add(residentVo);
					}
				}

				if (summaryVo != null) {
					if (addInfo.getSummaryList() != null && !addInfo.getSummaryList().isEmpty()) {
						addInfo.getSummaryList().add(summaryVo);
					} else {
						addInfo.setSummaryList(new ArrayList<SummaryVo>());
						addInfo.getSummaryList().add(summaryVo);
					}
				}
			}
		}
		
		if(addInfo != null) {
			hidefVo.getAddInfoList().add(addInfo);
		} 

		workbook.close();
		file.delete();
		logger.info("Metadata Object =====> " + hidefVo);
		return hidefVo;
	}

	@Override
	public HidefVo saveCtcExcelData(HidefVo hidefVo) {

		if (hidefVo != null) {
			if (hidefVo.getMetadata() != null) {
				/**
				 * Cbcpayldhdr
				 */
				logger.info("Receiving Country Code: TABLE:[Cbcpayldhdr]");
				Cbcpayldhdr payldhdr = null;
				if (hidefVo.getMetadata().getId() != null) {
					payldhdr = cbcpayldhdrRepository.getCbcDetailsById(hidefVo.getMetadata().getId());
				}
				if (payldhdr == null) {
					payldhdr = new Cbcpayldhdr();
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getSendingCountry())) {
					payldhdr.setSenderCountryCd(
							findCountryCodeConvertStringToBigInt(hidefVo.getMetadata().getSendingCountry()));
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getMessageTypeIndic())) {
					payldhdr.setMessageTypeIndic(hidefVo.getMetadata().getMessageTypeIndic());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getMsgType())) {
					payldhdr.setMessageType("CBC");
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getWarning())) {
					payldhdr.setWarning(hidefVo.getMetadata().getWarning());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getContact())) {
					payldhdr.setContact(hidefVo.getMetadata().getContact());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getReportingPeriod())) {
					payldhdr.setReportingPeriod(hidefVo.getMetadata().getReportingPeriod());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getTaxYear())) {
					payldhdr.setTaxYear(Integer.parseInt(hidefVo.getMetadata().getTaxYear()));
				}

				// payldhdr.setFileCreateTs(new TimeSnew Date());
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getCommunicationType())) {
					payldhdr.setCommunicationTypeCd(commonDropDownService
							.findCtscommunicationtypecdbyId(hidefVo.getMetadata().getCommunicationType())
							.getCTSCoumnicationType());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getSenderFileId())) {
					payldhdr.setSenderFileId(hidefVo.getMetadata().getSenderFileId());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getFileFormatCode())) {
					payldhdr.setFileFormatCD(commonDropDownService
							.findFileFormatCodeById(Integer.parseInt(hidefVo.getMetadata().getFileFormatCode()))
							.getCBCFileFormatType());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getBinaryEncoding())) {
					payldhdr.setBinaryEncodingSchemeCd(commonDropDownService
							.findBinaryEncodingSchemeById(Integer.parseInt(hidefVo.getMetadata().getBinaryEncoding()))
							.getType());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getMessageRefId())) {
					payldhdr.setMessageRefId(hidefVo.getMetadata().getMessageRefId());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getSenderContactEmailAddress())) {
					payldhdr.setSenderContactEmailAddressTxt(hidefVo.getMetadata().getSenderContactEmailAddress());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getSendingCompanyIN())) {
					payldhdr.setSendingEntityIN(hidefVo.getMetadata().getSendingCompanyIN());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getLanguage())) {
					payldhdr.setLanguage(hidefVo.getMetadata().getLanguage());
				}
				if (!StringUtils.isEmpty(hidefVo.getMetadata().getFormCreationTimeStamp())) {
					payldhdr.setFileCreateTs(hidefVo.getMetadata().getFormCreationTimeStamp());
				}
				if (!StringUtils.isEmpty(hidefVo.getMycbcId())) {
					payldhdr.setMyCBCID(hidefVo.getMycbcId());
				}
				if(!StringUtils.isEmpty(hidefVo.getMetadata().getFileTypeIndic())){
					payldhdr.setFiletypeindic(hidefVo.getMetadata().getFileTypeIndic());
				}
				payldhdr.setIsdeleted(0);
				payldhdr.setCreateDateTime(new Date());
				payldhdr = cbcpayldhdrRepository.saveAndFlush(payldhdr);
				logger.info("Cbcpayldhdr ID::::::>" + payldhdr.getId());

				/**
				 * cbcpayldreceivingcountry
				 */
				logger.info("Receiving Country Code: TABLE:[cbcpayldreceivingcountry]");
				if (hidefVo.getMetadata().getRecievingCountryList() != null
						&& hidefVo.getMetadata().getRecievingCountryList().size() > 0) {
					for (RecievingCountryVo receivingCountry : hidefVo.getMetadata().getRecievingCountryList()) {
						Cbcpayldreceivingcountry payldreceivingCountry = null;
						if (receivingCountry.getId() > 0) {
							payldreceivingCountry = cbcpayldreceivingcountryRepository
									.getAllReceivingCountryByid(BigInteger.valueOf(receivingCountry.getId()));
							logger.info("");
						}
						if (payldreceivingCountry == null) {
							payldreceivingCountry = new Cbcpayldreceivingcountry();
						}
						payldreceivingCountry.setCreateDateTime(new Date());
						payldreceivingCountry.setHdrID(payldhdr.getId());
						if (!StringUtils.isEmpty(receivingCountry.getId())) {
							payldreceivingCountry.setReceivingCountry(""+receivingCountry.getId());// TODO
						}
						payldreceivingCountry.setIsdeleted(0);
						payldreceivingCountry = cbcpayldreceivingcountryRepository.saveAndFlush(payldreceivingCountry);
						logger.info("cbcpayldreceivingcountry ID::::::>" + payldreceivingCountry.getId());
					}
				}

				/**
				 * cbcpayldbody
				 */
				logger.info("cbcpayldbody Saving: TABLE:[cbcpayldbody]");
				Cbcpayldbody cbcpayldbody = null;
				if (hidefVo.getMetadata().getId() != null) {
					cbcpayldbody = cbcpayldbodyRepository.getAllBodyDetailsById(hidefVo.getMetadata().getId());
				}
				if (cbcpayldbody == null) {
					cbcpayldbody = new Cbcpayldbody();
				}
				cbcpayldbody.setHdrID(payldhdr.getId());
				cbcpayldbody.setCreateDateTime(new Date());
				cbcpayldbody.setIsdeleted(0);
				cbcpayldbody = cbcpayldbodyRepository.saveAndFlush(cbcpayldbody);
				logger.info("cbcpayldbody ID::::::>" + cbcpayldbody.getId());

				/**
				 * Reporting Entity Tab Saving
				 */
				if (hidefVo.getReportingEntity() != null && payldhdr != null && payldhdr.getId() != null
						&& cbcpayldbody != null && cbcpayldbody.getId() != null) {
					logger.info("Reporting Entity Extra Fields Saving: TABLE:[cbcpayldentity]");
					Cbcpayldentity payldentity = null;
					if (hidefVo.getReportingEntity().getId() != null) {
						payldentity = cbcpayldentityRepository
								.getAllReportingEntityDetailsById(hidefVo.getReportingEntity().getId());
					}
					if (payldentity == null) {
						payldentity = new Cbcpayldentity();
					}
					payldentity.setBodyID(cbcpayldbody.getId());
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getCorDocReferenceId())) {
						payldentity.setCorrDocRefId(hidefVo.getReportingEntity().getCorDocReferenceId());
					}

					payldentity.setCreateDateTime(new Date());
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getDocumentReferenceId())) {
						payldentity.setDocRefId(hidefVo.getReportingEntity().getDocumentReferenceId());
					}
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getDocumentTypeIndicator())) {
						payldentity.setDocTypeIndic(hidefVo.getReportingEntity().getDocumentTypeIndicator());
					}
					payldentity.setHdrID(payldhdr.getId());
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getReportingRole())) {
						payldentity.setReportingRole(hidefVo.getReportingEntity().getReportingRole());
					}
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getTin())) {
						payldentity.setTin(hidefVo.getReportingEntity().getTin());
					}
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getTinType())) {
						payldentity.setIssuedBy(
								findCountryCodeConvertStringToBigInt(hidefVo.getReportingEntity().getTinIssuedBy()));
					}
					if (!StringUtils.isEmpty(hidefVo.getReportingEntity().getTinType())) {
						payldentity.setTintype(hidefVo.getReportingEntity().getTinType());
					}
					payldentity.setIsdeleted(0);
					// payldentity.
					payldentity = cbcpayldentityRepository.saveAndFlush(payldentity);
					logger.info("cbcpayldentity ID::::::>" + payldentity.getId());

					/*
					 * if() logger.info("cbcpayldentity INSERT Begin..........");
					 */

					if (payldentity != null && payldentity.getId() != null) {

						/**
						 * Reporting Entity Organisation Grid
						 */
						if (hidefVo.getReportingEntity().getNameList() != null
								&& hidefVo.getReportingEntity().getNameList().size() > 0) {
							logger.info("Reporting Entity Organisation Grid: TABLE:[cbcpayldname]");
							for (NameVo namevo : hidefVo.getReportingEntity().getNameList()) {
								Cbcpayldname payldname = null;
								if (namevo.getId() > 0) {
									payldname = cbcpayldnameRepository
											.getAllPayldNameDetailsById(BigInteger.valueOf(namevo.getId()));
								}
								if (payldname == null) {
									payldname = new Cbcpayldname();
								}
								payldname.setObjectID(payldentity.getId());
								payldname.setCreateDateTime(new Date());
								if (!StringUtils.isEmpty(namevo.getFirstName())) {
									payldname.setNameOrganisation(namevo.getFirstName());
								}
								payldname.setSrcType("1");// TODO
								payldname.setIsdeleted(0);
								payldname = cbcpayldnameRepository.saveAndFlush(payldname);
								logger.info("cbcpayldname ID::::::>" + payldname.getId());
							}
						}

						/**
						 * Reporting Entity Resident Country Code Grid
						 */
						if (hidefVo.getReportingEntity().getResidentCountryList() != null
								&& hidefVo.getReportingEntity().getResidentCountryList().size() > 0) {
							logger.info("Reporting Entity Resident Country Code Grid: TABLE:[cbcpayldrescountry]");
							for (ResidentCountryVo residentCountry : hidefVo.getReportingEntity()
									.getResidentCountryList()) {
								Cbcpayldrescountry cbcpayldrescountry = null;
								if (residentCountry.getId() > 0) {
									cbcpayldrescountry = cbcpayldrescountryRepository.getAllPayldResidentCountryById(
											BigInteger.valueOf(residentCountry.getId()));
								}
								if (cbcpayldrescountry == null) {
									cbcpayldrescountry = new Cbcpayldrescountry();
								}
								cbcpayldrescountry.setCreateDateTime(new Date());
								cbcpayldrescountry.setObjectID(payldentity.getId());
								cbcpayldrescountry.setResCountryCode(""+residentCountry.getResidentCountryCode());// TODO
								cbcpayldrescountry.setSrcType("1");// TODO
								cbcpayldrescountry.setIsdeleted(0);
								cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
								logger.info("cbcpayldrescountry ID::::::>" + cbcpayldrescountry.getId());

							}
						}

						/**
						 * Reporting Entity In,InType,IssuedBy Grid
						 */
						if (hidefVo.getReportingEntity().getOrganisationInTypeList() != null
								&& hidefVo.getReportingEntity().getOrganisationInTypeList().size() > 0) {
							logger.info("cbcpayldin INSERT Begin..........");
							for (OrganisationInTypeVo organisation : hidefVo.getReportingEntity()
									.getOrganisationInTypeList()) {
								Cbcpayldin cbcpayldin = null;
								if (organisation.getId() > 0) {
									cbcpayldin = cbcpayldinRepository
											.getAllPayldInDetailsById(BigInteger.valueOf(organisation.getId()));
								}
								if (cbcpayldin == null) {
									cbcpayldin = new Cbcpayldin();
								}
								cbcpayldin.setObjectID(payldentity.getId());
								cbcpayldin.setCreateDateTime(new Date());
								cbcpayldin.setTin(organisation.getIn());
								if (!StringUtils.isEmpty(organisation.getInType())) {
									cbcpayldin.setINType(organisation.getInType());
								}
								if (!StringUtils.isEmpty(organisation.getIssuedBy())) {
									cbcpayldin.setIssuedBy(findCountryCodeConvertStringToBigInt(
											String.valueOf(organisation.getIssuedBy())));// TODO
								}
								cbcpayldin.setSrcType("1");// TODO
								cbcpayldin.setIsdeleted(0);
								cbcpayldin = cbcpayldinRepository.saveAndFlush(cbcpayldin);
								logger.info("cbcpayldin ID::::::>" + cbcpayldin.getId());
							}
						}

						/**
						 * Reporting Entity Address
						 */

						if (hidefVo.getReportingEntity().getAddressList() != null
								&& hidefVo.getReportingEntity().getAddressList().size() > 0) {
							logger.info("Reporting Entity Address: TABLE:[cbcpayldaddress]");
							for (AddressVo addressVo : hidefVo.getReportingEntity().getAddressList()) {
								Cbcpayldaddress cbcpayldaddress = null;
								if (addressVo.getId() > 0) {
									cbcpayldaddress = cbcpayldaddressRepository
											.getAllPayldAddressById(BigInteger.valueOf(addressVo.getId()));
								}
								if (cbcpayldaddress == null) {
									cbcpayldaddress = new Cbcpayldaddress();
								}
								cbcpayldaddress.setObjectID(payldentity.getId());
								if (!StringUtils.isEmpty(addressVo.getAddressFree())) {
									cbcpayldaddress.setAddressFree(addressVo.getAddressFree());
								}
								if (!StringUtils.isEmpty(addressVo.getBuildingIdentifier())) {
									cbcpayldaddress.setBuildingIdentifier(addressVo.getBuildingIdentifier());
								}
								if (!StringUtils.isEmpty(addressVo.getCity())) {
									cbcpayldaddress.setCity(addressVo.getCity());
								}
								if (!StringUtils.isEmpty(addressVo.getCountryCode())) {
									cbcpayldaddress.setCountryCode(
											findCountryCodeConvertStringToBigInt(addressVo.getCountryCode()));
								}
								if (!StringUtils.isEmpty(addressVo.getCountrySubentity())) {
									cbcpayldaddress.setCountrySubentity(addressVo.getCountrySubentity());
								}

								cbcpayldaddress.setCreateDateTime(new Date());
								if (!StringUtils.isEmpty(addressVo.getDistrictName())) {
									cbcpayldaddress.setDistrictName(addressVo.getDistrictName());
								}
								if (!StringUtils.isEmpty(addressVo.getFloorIdentifier())) {
									cbcpayldaddress.setFloorIdentifier(addressVo.getFloorIdentifier());
								}
								if (!StringUtils.isEmpty(addressVo.getAddressType())) {
									cbcpayldaddress.setLegalAddressType(addressVo.getAddressType());
								}
								if (!StringUtils.isEmpty(addressVo.getPob())) {
									cbcpayldaddress.setPob(addressVo.getPob());
								}
								if (!StringUtils.isEmpty(addressVo.getPostCode())) {
									cbcpayldaddress.setPostCode(addressVo.getPostCode());
								}

								cbcpayldaddress.setSrcType("1");// TODO
								if (!StringUtils.isEmpty(addressVo.getStreet())) {
									cbcpayldaddress.setStreet(addressVo.getStreet());
								}
								if (!StringUtils.isEmpty(addressVo.getSuitIdentifier())) {
									cbcpayldaddress.setSuiteIdentifier(addressVo.getSuitIdentifier());
								}
								cbcpayldaddress.setIsdeleted(0);

								cbcpayldaddress = cbcpayldaddressRepository.saveAndFlush(cbcpayldaddress);
								logger.info("cbcpayldaddress ID::::::>" + cbcpayldaddress.getId());

							}
						}
					}

				}

				/**
				 * CBC Reports
				 */
				if (hidefVo.getListCBCReports() != null && hidefVo.getListCBCReports().size() > 0 && payldhdr != null
						&& payldhdr.getId() != null && cbcpayldbody != null && cbcpayldbody.getId() != null) {
					logger.info("CBC Reports extra fileds : TABLE:[cbcpayldreport]");
					/* if(!StringUtils.isEmpty(str)) */
					for (CBCRepotsVo cbcReports : hidefVo.getListCBCReports()) {
						Cbcpayldreport cbcpayldreport = null;
						if (cbcReports.getId() > 0) {
							cbcpayldreport = cbcpayldreportRepository
									.getAllPayldCBCReportsById(BigInteger.valueOf(cbcReports.getId()));
						}
						if (cbcpayldreport == null) {
							cbcpayldreport = new Cbcpayldreport();
						}
						if (!StringUtils.isEmpty(cbcReports.getAssertAmount())) {
							cbcpayldreport.setAssetsAmt(cbcReports.getAssertAmount());
						}
						if (!StringUtils.isEmpty(cbcReports.getAssertCurrCode())) {
							cbcpayldreport.setAssetsCurrCode(findCurrencyById(cbcReports.getAssertCurrCode()));
						}
						cbcpayldreport.setBodyID(cbcpayldbody.getId());
						if (!StringUtils.isEmpty(cbcReports.getCapitalAmount())) {
							cbcpayldreport
									.setCapitalAmt(cbcReports.getCapitalAmount());
						}
						if (!StringUtils.isEmpty(cbcReports.getCapitalCurrCode())) {
							cbcpayldreport.setCapitalCurrCode(findCurrencyById(cbcReports.getCapitalCurrCode()));
						}
						if (!StringUtils.isEmpty(cbcReports.getCorrDocRefId())) {
							cbcpayldreport.setCorrDocRefId(cbcReports.getCorrDocRefId());
						}
						cbcpayldreport.setCreateDateTime(new Date());
						if (!StringUtils.isEmpty(cbcReports.getDocumentTypeIndicator())) {
							cbcpayldreport.setDocTypeIndic(cbcReports.getDocumentTypeIndicator());
						}
						
						if(!StringUtils.isEmpty(cbcReports.getDocumentRefId())) {
							cbcpayldreport.setDocRefId(cbcReports.getDocumentRefId());
						}
						
						if (!StringUtils.isEmpty(cbcReports.getEarningAmount())) {
							cbcpayldreport
									.setEarningsAmt(cbcReports.getEarningAmount());
						}
						if (!StringUtils.isEmpty(cbcReports.getEarningCurrCode())) {
							cbcpayldreport.setEarningsCurrCode(findCurrencyById(cbcReports.getEarningCurrCode()));
						}
						cbcpayldreport.setHdrID(payldhdr.getId());
						if (!StringUtils.isEmpty(cbcReports.getNbEmployees())) {
							cbcpayldreport.setNbEmployees(Integer.valueOf(cbcReports.getNbEmployees()));
						}
						if (!StringUtils.isEmpty(cbcReports.getPrfitotloassAmount())) {
							cbcpayldreport.setProfitOrLossAmt(
									cbcReports.getPrfitotloassAmount());
						}
						if (!StringUtils.isEmpty(cbcReports.getProfitorlossCurrCode())) {
							cbcpayldreport
									.setProfitOrLossCurrCode(findCurrencyById(cbcReports.getProfitorlossCurrCode()));
						}
						if (!StringUtils.isEmpty(cbcReports.getResidentCountryCode())) {
							cbcpayldreport.setResCountryCode(
									findCountryCodeConvertStringToBigInt(cbcReports.getResidentCountryCode()));
						}
						if (!StringUtils.isEmpty(cbcReports.getRelatedAmount())) {
							cbcpayldreport.setRevenuesRelatedAmt(
									cbcReports.getRelatedAmount());
						}
						if (!StringUtils.isEmpty(cbcReports.getRelatedCurrCode())) {
							cbcpayldreport
									.setRevenuesRelatedCurrCode(findCurrencyById(cbcReports.getRelatedCurrCode()));
						}
						if (!StringUtils.isEmpty(cbcReports.getTotalRevenueAmount())) {
							cbcpayldreport.setRevenuesTotalAmt(
									cbcReports.getTotalRevenueAmount());
						}
						if (!StringUtils.isEmpty(cbcReports.getTotalRevenueCurrCode())) {
							cbcpayldreport
									.setRevenuesTotalCurrCode(findCurrencyById(cbcReports.getTotalRevenueCurrCode()));
						}
						if (!StringUtils.isEmpty(cbcReports.getUnrelatedAmount())) {
							cbcpayldreport.setRevenuesUnrelatedAmt(
									cbcReports.getUnrelatedAmount());
						}
						if (!StringUtils.isEmpty(cbcReports.getUnrelateCurrCode())) {
							cbcpayldreport
									.setRevenuesUnrelatedCurrCode(findCurrencyById(cbcReports.getUnrelateCurrCode()));
						}
						if (!StringUtils.isEmpty(cbcReports.getTaxaccruedAmount())) {
							cbcpayldreport.setTaxAccruedAmt(
									cbcReports.getTaxaccruedAmount());
						}
						if (!StringUtils.isEmpty(cbcReports.getTaxaccruedCurrCode())) {
							cbcpayldreport.setTaxAccruedCurrCode(
									findCurrencyById(cbcReports.getTaxaccruedCurrCode()));
						}
						if (!StringUtils.isEmpty(cbcReports.getTaxpaidAmount())) {
							cbcpayldreport
									.setTaxPaidAmt(cbcReports.getTaxpaidAmount());
						}
						if (!StringUtils.isEmpty(cbcReports.getTaxpiadCurrCode())) {
							cbcpayldreport.setTaxPaidCurrCode(findCurrencyById(cbcReports.getTaxpiadCurrCode()));
						}
						cbcpayldreport.setIsdeleted(0);
						cbcpayldreport = cbcpayldreportRepository.saveAndFlush(cbcpayldreport);
						logger.info("cbcpayldreport ID::::::>" + cbcpayldreport.getId());

						/**
						 * Constituent Entities :
						 */
						if(cbcReports.getConstituentEntityList() != null && !cbcReports.getConstituentEntityList().isEmpty()) {
							for(CbcConstituentEntityVO consEntity : cbcReports.getConstituentEntityList()) {
						logger.info("CBCReports Constituent Entities : TABLE:[cbcpayldconstentity]");
						Cbcpayldconstentity cbcpayldconstentity = null;
						if (consEntity.getConsId() > 0) {
							cbcpayldconstentity = cbcpayldconstentityRepository
									.getAllconstentityById(BigInteger.valueOf(consEntity.getConsId()));
						}
						if (cbcpayldconstentity == null) {
							cbcpayldconstentity = new Cbcpayldconstentity();
						}
						if (!StringUtils.isEmpty(consEntity.getIncorpCountryCode())) {
							cbcpayldconstentity.setIncorpCountryCode(
									findCountryCodeConvertStringToBigInt(consEntity.getIncorpCountryCode()));
						}
						if (!StringUtils.isEmpty(consEntity.getTin())) {
							cbcpayldconstentity.setTin(consEntity.getTin());
						}
						if (!StringUtils.isEmpty(consEntity.getTinType())) {
							cbcpayldconstentity.setINType(consEntity.getTinType());
						}
						if (!StringUtils.isEmpty(consEntity.getIssuedBy())) {
							cbcpayldconstentity
									.setIssuedBy(findCountryCodeConvertStringToBigInt(consEntity.getIssuedBy()));
						}
						if (!StringUtils.isEmpty(consEntity.getOtherEntityInfo())) {
							cbcpayldconstentity.setOtherEntityInfo(consEntity.getOtherEntityInfo());
						}
						cbcpayldconstentity.setReportID(cbcpayldreport.getId());
						cbcpayldconstentity.setIsdeleted(0);
						cbcpayldconstentity = cbcpayldconstentityRepository.saveAndFlush(cbcpayldconstentity);
						logger.info("cbcpayldconstentity ID::::::>" + cbcpayldconstentity.getId());

						/**
						 * BiZ Activities
						 */
						if (consEntity.getBizActivitiesList() != null && consEntity.getBizActivitiesList().size() > 0) {
							logger.info("CBCReports BiZ Activities TABLE:[cbcpayldbizactiv]");
							for (BizActivitiesTypeVo bizActivitiesTypeVo : consEntity.getBizActivitiesList()) {
								Cbcpayldbizactiv cbcpayldbizactiv = null;
								if (bizActivitiesTypeVo.getId() > 0) {
									cbcpayldbizactiv = cbcpayldbizactivRepository
											.getAllBizActivitiesByID(BigInteger.valueOf(bizActivitiesTypeVo.getId()));
								}
								if (cbcpayldbizactiv == null) {
									cbcpayldbizactiv = new Cbcpayldbizactiv();
								}
								cbcpayldbizactiv.setBizActivities(""+bizActivitiesTypeVo.getId());
								cbcpayldbizactiv.setConsentID(cbcpayldconstentity.getId());
								cbcpayldbizactiv.setCreateDateTime(new Date());
								cbcpayldbizactiv.setIsdeleted(0);
								cbcpayldbizactiv = cbcpayldbizactivRepository.saveAndFlush(cbcpayldbizactiv);
								logger.info("cbcpayldbizactiv ID::::::>" + cbcpayldbizactiv.getId());
							}

						}

						/**
						 * CBCReports Organisation Grid
						 */
						if (consEntity.getNameList() != null && consEntity.getNameList().size() > 0) {
							logger.info("CBCReports Organisation TABLE:[cbcpayldname]");
							for (NameVo namevo : consEntity.getNameList()) {
								Cbcpayldname payldname = null;
								;
								if (namevo.getId() > 0) {
									payldname = cbcpayldnameRepository
											.getAllPayldNameDetailsById(BigInteger.valueOf(namevo.getId()));
								}
								if (payldname == null) {
									payldname = new Cbcpayldname();
								}
								payldname.setObjectID(cbcpayldconstentity.getId());
								payldname.setCreateDateTime(new Date());
								if (!StringUtils.isEmpty(namevo.getFirstName())) {
									payldname.setNameOrganisation(namevo.getFirstName());
								}
								payldname.setSrcType("1");// TODO
								payldname = cbcpayldnameRepository.saveAndFlush(payldname);
								logger.info("cbcpayldname ID::::::>" + payldname.getId());
							}
						}

						/**
						 * CBCReports Resident Country Code Grid
						 */
						if (consEntity.getResidentCountryList() != null
								&& consEntity.getResidentCountryList().size() > 0) {
							logger.info("CBCReports Resident Country Code TABLE:[cbcpayldrescountry]");
							for (ResidentCountryVo residentCountry : consEntity.getResidentCountryList()) {
								Cbcpayldrescountry cbcpayldrescountry = null;
								if (residentCountry.getId() > 0) {
									cbcpayldrescountry = cbcpayldrescountryRepository.getAllPayldResidentCountryById(
											BigInteger.valueOf(residentCountry.getId()));
								}
								if (cbcpayldrescountry == null) {
									cbcpayldrescountry = new Cbcpayldrescountry();
								}
								cbcpayldrescountry.setCreateDateTime(new Date());
								cbcpayldrescountry.setObjectID(cbcpayldconstentity.getId());
								if (!StringUtils.isEmpty("" + residentCountry.getResidentCountryCode())) {
									cbcpayldrescountry.setResCountryCode(""+residentCountry.getResidentCountryCode());// TODO
								}
								cbcpayldrescountry.setSrcType("1");// TODO
								cbcpayldrescountry.setIsdeleted(0);
								cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
								logger.info("cbcpayldrescountry ID::::::>" + cbcpayldrescountry.getId());

							}
						}

						/**
						 * CBCReports In,InType,IssuedBy Grid
						 */
						if (consEntity.getOrganisationInTypeList() != null
								&& consEntity.getOrganisationInTypeList().size() > 0) {
							logger.info("CBCReports In,InType,IssuedBy TABLE:[cbcpayldin]");
							for (OrganisationInTypeVo organisation : consEntity.getOrganisationInTypeList()) {
								Cbcpayldin cbcpayldin = null;
								if (organisation.getId() > 0) {
									cbcpayldin = cbcpayldinRepository
											.getAllPayldInDetailsById(BigInteger.valueOf(organisation.getId()));
								}
								if (cbcpayldin == null) {
									cbcpayldin = new Cbcpayldin();
								}
								cbcpayldin.setObjectID(cbcpayldconstentity.getId());
								cbcpayldin.setCreateDateTime(new Date());
								if (!StringUtils.isEmpty(organisation.getIn())) {
									cbcpayldin.setTin(organisation.getIn());
								}
								if (!StringUtils.isEmpty(organisation.getInType())) {
									cbcpayldin.setINType(organisation.getInType());
								}
								if (!StringUtils.isEmpty(organisation.getIssuedBy())) {
									cbcpayldin.setIssuedBy(findCountryCodeConvertStringToBigInt(
											String.valueOf(organisation.getIssuedBy())));// TODO
								}
								cbcpayldin.setSrcType("1");// TODO
								cbcpayldin.setIsdeleted(0);
								cbcpayldin = cbcpayldinRepository.saveAndFlush(cbcpayldin);
								logger.info("cbcpayldin ID::::::>" + cbcpayldin.getId());
							}
						}

						/**
						 * CBCReports Address
						 */

						if (consEntity.getAddressList() != null && consEntity.getAddressList().size() > 0) {
							logger.info("CBCRepots Address  TABLE:[cbcpayldaddress]");
							for (AddressVo addressVo : consEntity.getAddressList()) {
								Cbcpayldaddress cbcpayldaddress = null;
								if (addressVo.getId() > 0) {
									cbcpayldaddress = cbcpayldaddressRepository
											.getAllPayldAddressById(BigInteger.valueOf(addressVo.getId()));
								}
								if (cbcpayldaddress == null) {
									cbcpayldaddress = new Cbcpayldaddress();
								}
								cbcpayldaddress.setObjectID(cbcpayldconstentity.getId());
								if (!StringUtils.isEmpty(addressVo.getAddressFree())) {
									cbcpayldaddress.setAddressFree(addressVo.getAddressFree());
								}
								if (!StringUtils.isEmpty(addressVo.getBuildingIdentifier())) {
									cbcpayldaddress.setBuildingIdentifier(addressVo.getBuildingIdentifier());
								}
								if (!StringUtils.isEmpty(addressVo.getCity())) {
									cbcpayldaddress.setCity(addressVo.getCity());
								}
								if (!StringUtils.isEmpty(addressVo.getCountryCode())) {
									cbcpayldaddress.setCountryCode(
											findCountryCodeConvertStringToBigInt(addressVo.getCountryCode()));
								}
								if (!StringUtils.isEmpty(addressVo.getCountrySubentity())) {
									cbcpayldaddress.setCountrySubentity(addressVo.getCountrySubentity());
								}

								cbcpayldaddress.setCreateDateTime(new Date());
								if (!StringUtils.isEmpty(addressVo.getDistrictName())) {
									cbcpayldaddress.setDistrictName(addressVo.getDistrictName());
								}
								if (!StringUtils.isEmpty(addressVo.getFloorIdentifier())) {
									cbcpayldaddress.setFloorIdentifier(addressVo.getFloorIdentifier());
								}
								if (!StringUtils.isEmpty(addressVo.getAddressType())) {
									cbcpayldaddress.setLegalAddressType(addressVo.getAddressType());
								}
								if (!StringUtils.isEmpty(addressVo.getPob())) {
									cbcpayldaddress.setPob(addressVo.getPob());
								}
								if (!StringUtils.isEmpty(addressVo.getPostCode())) {
									cbcpayldaddress.setPostCode(addressVo.getPostCode());
								}

								cbcpayldaddress.setSrcType("1");// TODO
								if (!StringUtils.isEmpty(addressVo.getStreet())) {
									cbcpayldaddress.setStreet(addressVo.getStreet());
								}
								if (!StringUtils.isEmpty(addressVo.getSuitIdentifier())) {
									cbcpayldaddress.setSuiteIdentifier(addressVo.getSuitIdentifier());
								}
								cbcpayldaddress.setIsdeleted(0);
								cbcpayldaddress = cbcpayldaddressRepository.saveAndFlush(cbcpayldaddress);
								logger.info("cbcpayldaddress ID::::::>" + cbcpayldaddress.getId());

							}
						}
					}
				}
					}

				}

				/**
				 * Additional Info
				 */
				if (hidefVo.getAddInfoList() != null && hidefVo.getAddInfoList().size() > 0 && payldhdr != null
						&& payldhdr.getId() != null && cbcpayldbody != null && cbcpayldbody.getId() != null) {
					logger.info("Additional Info Extra Fields TABLE:[cbcpayldaddinfo]");
					for (CbcAdditionalInfo addinfo : hidefVo.getAddInfoList()) {
						Cbcpayldaddinfo sddInfo = null;
						if (addinfo.getId() > 0) {
							sddInfo = cbcpayldaddinfoRepository
									.getAllAdditionalInfoById(BigInteger.valueOf(addinfo.getId()));
						}
						if (sddInfo == null) {
							sddInfo = new Cbcpayldaddinfo();
						}
						sddInfo.setBodyID(cbcpayldbody.getId());
						if (!StringUtils.isEmpty(addinfo.getCorDocumentRefId())) {
							sddInfo.setCorrDocRefId(addinfo.getCorDocumentRefId());
						}

						sddInfo.setCreateDateTime(new Date());
						if (!StringUtils.isEmpty(addinfo.getDocumentReferenceId())) {
							sddInfo.setDocRefId(addinfo.getDocumentReferenceId());
						}
						if (!StringUtils.isEmpty(addinfo.getDocumentTypeIndic())) {
							sddInfo.setDocTypeIndic(addinfo.getDocumentTypeIndic());
						}
						sddInfo.setHdrID(payldhdr.getId());
						if (!StringUtils.isEmpty(addinfo.getOtherInfo())) {
							sddInfo.setOtherInfo(addinfo.getOtherInfo());
						}
						sddInfo.setIsdeleted(0);
						sddInfo = cbcpayldaddinfoRepository.saveAndFlush(sddInfo);
						logger.info("cbcpayldaddinfo ID::::::>" + sddInfo.getId());

						/**
						 * AdditionalInfo Resident Country Code Grid
						 */
						if (addinfo.getResidentCountryList() != null && addinfo.getResidentCountryList().size() > 0) {
							logger.info("Additional ResidentCountry TABLE:[cbcpayldrescountry]");
							for (ResidentCountryVo residentCountry : addinfo.getResidentCountryList()) {
								Cbcpayldrescountry cbcpayldrescountry = null;
								if (residentCountry.getId() > 0) {
									cbcpayldrescountry = cbcpayldrescountryRepository.getAllPayldResidentCountryById(
											BigInteger.valueOf(residentCountry.getId()));
								}
								if (cbcpayldrescountry == null) {
									cbcpayldrescountry = new Cbcpayldrescountry();
								}
								cbcpayldrescountry.setCreateDateTime(new Date());
								cbcpayldrescountry.setObjectID(sddInfo.getId());
								cbcpayldrescountry.setResCountryCode(""+residentCountry.getResidentCountryCode());// TODO
								cbcpayldrescountry.setSrcType("1");// TODO
								cbcpayldrescountry.setIsdeleted(0);
								cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
								logger.info("cbcpayldrescountry ID::::::>" + cbcpayldrescountry.getId());

							}
						}

						/**
						 * AdditionalInfo Summary Grid
						 */
						if (addinfo.getSummaryList() != null && addinfo.getSummaryList().size() > 0) {
							logger.info("Additional info Summary Ref TABLE:[cbcpayldsumref]");
							for (SummaryVo summary : addinfo.getSummaryList()) {
								Cbcpayldsumref summaryRef = null;
								if (summary.getId() > 0) {
									summaryRef = cbcpayldsumrefRepository
											.getAllSummaryById(BigInteger.valueOf(summary.getId()));
								}
								if (summaryRef == null) {
									summaryRef = new Cbcpayldsumref();
								}
								summaryRef.setAddinfoID(sddInfo.getId());
								summaryRef.setCreateDateTime(new Date());
								summaryRef.setSummaryRef(""+summary.getId());
								summaryRef.setIsdeleted(0);
								summaryRef = cbcpayldsumrefRepository.saveAndFlush(summaryRef);
								logger.info("cbcpayldsumref ID::::::>" + summaryRef.getId());
							}

						}

					}

				}
				
				/**
				 * To update the DocRefId
				 */
				String pattern = "yyyyMMdd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String date = simpleDateFormat.format(new Date());
				Docrefid docrefid = ctccommonDropdownService.findDocRefIdByDate(date);
				if (docrefid != null) {
					docrefid.setDocrefid(hidefVo.getDocRefId());
					docrefid = docrefidRepository.saveAndFlush(docrefid);
				}

				/**
				 * To update the senderFileId
				 */
				Senderfileid senderFileId = ctccommonDropdownService.findSenderFileIdByDate(date);
				if (senderFileId != null) {
					String senderFieIdNew = hidefVo.getMetadata().getSenderFileId();
					senderFileId.setSenderfileid(senderFieIdNew.substring(senderFieIdNew.length() - 4));
					senderFileId = senderfileidRepository.saveAndFlush(senderFileId);
				}
				if(payldhdr != null){
				hidefVo.setPayldId(payldhdr.getId());
				}

			} // metadata

		} // hidef

		// TODO Auto-generated method stub
		return hidefVo;

	}

	private String findCountryCodeById(BigInteger id) {
		Hicountry findCountryById = commonDropDownService.findCountryById(id);
		return findCountryById.getCountryCode();

	}

	private String findCountryCodeConvertStringToBigInt(String id) {
		Hicountry findCountryById = commonDropDownService.findCountryById(new BigInteger(id));
		return findCountryById.getCountryCode();
	}

	private String findCurrencyById(String id) {
		CbcCurrency currency = commonDropDownService.findCurrencyById(Integer.parseInt(id));
		return currency.getCode();
	}

	@Override
	public HidefVo deleteSummaryGrid(HidefVo hidefvo, BigInteger hrdId) {
		// TODO Auto-generated method stub
		logger.info("HrdId::::::::::>" + hrdId);
		int cpayhdr = cbcpayldhdrRepository.setIsdeleted(1, hrdId);
		return hidefvo;
	}

	@Override
	public HidefVo getUserProfileByMycbcId(HidefVo hidef) {
		// TODO Auto-generated method stub
		List<Userprofile> userProfileList = userprofileRepository.getUserprofileDetailsByMycbcId(hidef.getMycbcId());
		if (userProfileList != null && userProfileList.size() > 0) {
			Userprofile userProfile = userProfileList.get(0);
			UserProfileVo userProfileVo = new UserProfileVo();
			userProfileVo.setBinaryEncoding(userProfile.getBinaryencoding());
			userProfileVo.setCommunicationType(userProfile.getCommunicationtype());
			userProfileVo.setConfigurationFileText(userProfile.getSendingcountrycert());
			userProfileVo.setDocRefID(userProfile.getDocumentreferenceid());
			userProfileVo.setFileformatCode(userProfile.getFileformatcode());
			userProfileVo.setFileTypeIndic(userProfile.getFiletypeindic());
			userProfileVo.setMessageRefID(userProfile.getMessagereferenceid());
			userProfileVo.setMsgType(userProfile.getMessagetype());
			userProfileVo.setPublicCertFileName(userProfile.getReceivingcountrycert());
			userProfileVo.setSendContactEmailAddress(userProfile.getSendercontactemail());
			userProfileVo.setUserProfileId(userProfile.getId());
			userProfileVo.setSendingcountry(userProfile.getSendingcountry());
			userProfileVo.setCtsTransId(userProfile.getTransmissionid());

			List<RecievingCountryVo> recievingCountryList = new ArrayList<RecievingCountryVo>();
			List<Usereceivingcountry> receivingCountryList = usereceivingcountryRepository
					.getAllUserReceivingCountryById(userProfile);
			for (Usereceivingcountry receiving : receivingCountryList) {
				RecievingCountryVo recevingCountryVo = new RecievingCountryVo();
				recevingCountryVo.setId(receiving.getId().intValue());
				if (receiving.getReceivingCountry() != null) {
					recevingCountryVo.setRecievingCountry(Integer.parseInt(receiving.getReceivingCountry()));
				}
				recievingCountryList.add(recevingCountryVo);
			}
			userProfileVo.setRecievingCountryList(recievingCountryList);
			logger.info("@@@@@@@@@@@@@@");

			hidef.setUserprofile(userProfileVo);

			/* cbcpayldhdrRepository.getAllCbcDetails(hidef.getMycbcId()); */
		}
		return hidef;
	}

	@Override
	public Docrefid saveDocRefIdDetails(String date, String communicationType) {
		// TODO Auto-generated method stub
		Docrefid decRef = new Docrefid();
		decRef.setCommunicationtype("CBC");
		decRef.setDate(date);
		decRef.setDocrefid("0001");
		decRef = docrefidRepository.saveAndFlush(decRef);
		return decRef;
	}

	@Override
	public Messagerefid saveMessageRefId(String date, String communicationType) {
		// TODO Auto-generated method stub
		Messagerefid messagerefid = new Messagerefid();
		messagerefid.setCommunicationtype(communicationType);
		messagerefid.setDate(date);
		messagerefid.setMessagerefid("0001");
		messagerefid = messagerefidRepository.save(messagerefid);
		return messagerefid;
	}

	@Override
	public Senderfileid saveSenderFileId(String date, String communicationType) {
		// TODO Auto-generated method stub
		Senderfileid senderfileid = new Senderfileid();
		senderfileid.setCommunicationtype(communicationType);
		senderfileid.setDate(date);
		senderfileid.setSenderfileid("0001");
		senderfileid = senderfileidRepository.save(senderfileid);
		return senderfileid;
	}

	@Override
	public void deleteUserProfileRecievingCountry(BigInteger userProfileId,int recievingCountryId) {
		usereceivingcountryRepository.deleteById(BigInteger.valueOf(recievingCountryId));
		
		/*
		// TODO Auto-generated method stub
		Userprofile userprofileDetailsById = userprofileRepository.getUserprofileDetailsById(BigInteger.valueOf(userProfileId));
		if(userprofileDetailsById != null) {
			List<Usereceivingcountry> allRecivingCountrybyUserProfleId = usereceivingcountryRepository.getAllUserReceivingCountryById(userprofileDetailsById);
		}
		
	*/}
	@Override
	public HidefVo savecrsData(HidefVo hidefVo) {
		logger.info("<<<<<<<<<<<<<<CRS Saving part begin>>>>>>>>>>>>>>>>>");
		
		if(hidefVo != null && hidefVo.getCrsmetadata() != null){
		logger.info("<<<<<<<<<<<Metadata[Crspayldhdr] begin saving>>>>>>>>>>>>>>>");
		CrsMetadataVo metadata = hidefVo.getCrsmetadata();
		Crspayldhdr crspayld = null;
		if(metadata.getId() != null){
			crspayld = crspayldhdrRepository.getAllById(metadata.getId());
		}
		if(crspayld == null){
			crspayld = new Crspayldhdr();
		}
		
		if(!StringUtils.isEmpty(metadata.getBinaryEncoding())){
		crspayld.setBinaryEncodingSchemeCd(metadata.getBinaryEncoding());
		}
		if(!StringUtils.isEmpty(metadata.getCommunicationType())){
		crspayld.setCommunicationTypeCd(metadata.getCommunicationType());
		}
		if(!StringUtils.isEmpty(metadata.getContact())){
		crspayld.setContact(metadata.getContact());
		}
		crspayld.setCreateDateTime(new Date());
		if(!StringUtils.isEmpty(hidefVo.getMycbcId())){
		crspayld.setCrsid(new BigInteger(hidefVo.getMycbcId()));
		}
		if(!StringUtils.isEmpty(metadata.getFileCreationTimestramp())){
		crspayld.setFileCreateTs(metadata.getFileCreationTimestramp());
		}
		if(!StringUtils.isEmpty(metadata.getFileFormatCode())){
		crspayld.setFileFormatCD(metadata.getFileFormatCode());
		}
		if(!StringUtils.isEmpty(metadata.getMessageTypeIndic())){
		crspayld.setFileRevisionInd(Integer.parseInt(metadata.getMessageTypeIndic()));
		}
		if(!StringUtils.isEmpty(metadata.getMessageReferenceId())){
		crspayld.setMessageRefId(metadata.getMessageReferenceId());
		}
		if(!StringUtils.isEmpty(metadata.getMessageType())){
		crspayld.setMessageType(metadata.getMessageType());
		}
		
		crspayld.setMsgTimestamp(new Date());
		if(!StringUtils.isEmpty(metadata.getReceivingCountry())){
		crspayld.setReceiverCountryCd(metadata.getReceivingCountry());
		}
		if(!StringUtils.isEmpty(metadata.getReportingPeriod())){
		crspayld.setReportingPeriod(metadata.getReportingPeriod());
		}
		if(!StringUtils.isEmpty(metadata.getSenderContactEmail())){
		crspayld.setSenderContactEmailAddressTxt(metadata.getSenderContactEmail());
		}
		if(!StringUtils.isEmpty(metadata.getSendingCountry())){
		crspayld.setSenderCountryCd(metadata.getSendingCountry());
		}
		if(!StringUtils.isEmpty(metadata.getSenderFileId())){
		crspayld.setSenderFileId(metadata.getSenderFileId());
		}
		if(!StringUtils.isEmpty(hidefVo.getMycbcId())){
		crspayld.setSendingCompanyIN(hidefVo.getMycbcId());
		}
		if(!StringUtils.isEmpty(metadata.getTaxYear())){
		crspayld.setTaxYear(Integer.parseInt(metadata.getTaxYear()));
		}
		if(!StringUtils.isEmpty(metadata.getWarning())){
		crspayld.setWarning(metadata.getWarning());
		}
		crspayld = crspayldhdrRepository.saveAndFlush(crspayld);
		logger.info("<<<<<<<<<<<Metadata[Crspayldhdr] End saving>>>>>>>>>>>>>>>"+crspayld.getId());
		
		
		if(crspayld != null){
			logger.info("<<<<<<<<<<<Body[Crspayldbody] Begin saving>>>>>>>>>>>>>>>");
			Crspayldbody crsbody = null;
			if(metadata.getId() != null){
				crsbody = crspayldbodyRepository.getAllCrsBodyDetailsByHrdid(metadata.getId());
			}else{
				crsbody = new Crspayldbody();
			}
			crsbody.setHdrID(crspayld.getId());
			crsbody.setCreateDateTime(new Date());
			
			crsbody = crspayldbodyRepository.saveAndFlush(crsbody);
			logger.info("<<<<<<<<<<<Body[Crspayldbody] End saving>>>>>>>>>>>>>>>"+crsbody.getId());
			
			if(crsbody != null && crsbody != null && crspayld != null){
				logger.info("<<<<<<<<<<<<Reposting FI Saving part begin here>>>>>>>>>>>>>>>");
				if(hidefVo.getCrsreportingfi() != null){
				logger.info("<<<<<<<<<<<ReportingFI[Crspayldfi] Begin saving>>>>>>>>>>>>>>>");
				CrsReportingFiVo reportingFi = hidefVo.getCrsreportingfi();
				Crspayldfi crspayldFi = null;
				if(reportingFi.getId() != null){
					crspayldFi = crspayldfiRepository.getAllCrspayldfiByID(reportingFi.getId());
				}else{
					crspayldFi = new Crspayldfi();
				}
				crspayldFi.setBodyID(crsbody.getId());
				crspayldFi.setCorrDocRefId(reportingFi.getCorDocRefId());
				crspayldFi.setCreateDateTime(new Date());
				crspayldFi.setDocRefId(reportingFi.getDocRefId());
				crspayldFi.setDocTypeIndic(reportingFi.getDocumentTypeIndic());
				crspayldFi.setHdrID(crspayld.getId());
				
				crspayldFi = crspayldfiRepository.saveAndFlush(crspayldFi);
				logger.info("<<<<<<<<<<<ReportingFI[Crspayldfi] End saving>>>>>>>>>>>>>>>"+crspayldFi.getId());
				
				//Organisation Grid
				if(reportingFi.getNameList() != null && reportingFi.getNameList().size() > 0){
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldname] Begin saving>>>>>>>>>>>>>>>");	
					for(NameVo nameVo : reportingFi.getNameList()){
						Crspayldname crspayldName = new Crspayldname();
						crspayldName.setNameOrganisation(nameVo.getFirstName());
						crspayldName.setNamePersonType(String.valueOf(nameVo.getNameType()));
						crspayldName.setObjectID(crspayldFi.getId());
						crspayldName.setSrcType("0");
						crspayldName = crspayldnameRepository.saveAndFlush(crspayldName);
						
					}
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldname] End saving>>>>>>>>>>>>>>>");
				}
				//Reporting FI IN
				if(reportingFi.getOrganisationInTypeList() != null && reportingFi.getOrganisationInTypeList().size() > 0){
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldin] Begin saving>>>>>>>>>>>>>>>");
					for(OrganisationInTypeVo organisation : reportingFi.getOrganisationInTypeList()){
						Crspayldin crsPayldin = new Crspayldin();
						if(!StringUtils.isEmpty(organisation.getIn())){
						crsPayldin.setTin(organisation.getIn());
						}
						if(!StringUtils.isEmpty(organisation.getInType())){
						crsPayldin.setINType(organisation.getInType());
						}
						if(organisation.getIssuedBy() >0){
						crsPayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));
						}
						crsPayldin.setObjectID(crspayldFi.getId());
						crsPayldin.setSrcType("0");
						crsPayldin = crspayldinRepository.saveAndFlush(crsPayldin);
					}
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldin] End saving>>>>>>>>>>>>>>>");
					
				}
				//Resident Country
				if(reportingFi.getResidentCountryList() != null && reportingFi.getResidentCountryList().size() >0){
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldrescountry] Begin saving>>>>>>>>>>>>>>>");
					for(ResidentCountryVo residentCountry : reportingFi.getResidentCountryList()){
						Crspayldrescountry crspayldrescountry = new Crspayldrescountry();
						if(residentCountry.getResidentCountryCode() > 0){
						crspayldrescountry.setResCountryCode(String.valueOf(residentCountry.getResidentCountryCode()));
						}
						crspayldrescountry.setObjectID(crspayldFi.getId());
						crspayldrescountry.setSrcType("0");
						crspayldrescountry = crspayldrescountryRepository.saveAndFlush(crspayldrescountry);
					}
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldrescountry] End saving>>>>>>>>>>>>>>>");
					
				}	
				
				//Address
				if(reportingFi.getAddressList() != null && reportingFi.getAddressList().size()>0){
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldaddress] Begin saving>>>>>>>>>>>>>>>");
					for(AddressVo addressVo :reportingFi.getAddressList() ){
						Crspayldaddress address = new Crspayldaddress();
						if(!StringUtils.isEmpty(addressVo.getAddressFree())){
						address.setAddressFree(addressVo.getAddressFree());
						}
						if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
						address.setBuildingIdentifier(addressVo.getBuildingIdentifier());
						}
						if(!StringUtils.isEmpty(addressVo.getCity())){
						address.setCity(addressVo.getCity());
						}
						if(!StringUtils.isEmpty(addressVo.getCountryCode())){
						address.setCountryCode(addressVo.getCountryCode());
						}
						if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
						address.setCountrySubentity(addressVo.getCountrySubentity());
						}
						address.setCreateDateTime(new Date());
						if(!StringUtils.isEmpty(addressVo.getDistrictName())){
						address.setDistrictName(addressVo.getDistrictName());
						}
						if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
						address.setFloorIdentifier(addressVo.getFloorIdentifier());
						}
						if(!StringUtils.isEmpty(addressVo.getAddressType())){
						address.setLegalAddressType(addressVo.getAddressType());
						}
						address.setObjectID(crspayldFi.getId());
						if(!StringUtils.isEmpty(addressVo.getPob())){
						address.setPob(addressVo.getPob());
						}
						if(!StringUtils.isEmpty(addressVo.getPostCode())){
						address.setPostCode(addressVo.getPostCode());
						}
						address.setSrcType("0");
						if(!StringUtils.isEmpty(addressVo.getStreet())){
						address.setStreet(addressVo.getStreet());
						}
						if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
						address.setSuiteIdentifier(addressVo.getSuitIdentifier());
						}
						address = crspayldaddressRepository.saveAndFlush(address);
					}
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldaddress] End saving>>>>>>>>>>>>>>>");
					
				}
				
				}//Reporting FI
				
				
				if(hidefVo.getAccountHolderList() != null && hidefVo.getAccountHolderList().size() >0){
				logger.info("<<<<<<<<<<<AccountHolder[Crspayldacctrep] Begin saving>>>>>>>>>>>>>>>");
				List<AccountHolderVo> accountHolderList = 	hidefVo.getAccountHolderList();
				for(AccountHolderVo accountHolderVo : accountHolderList){
					
					Crspayldacctrep crspayldacct =null;
					if(accountHolderVo.getId() > 0){
						crspayldacct = crspayldacctrepRepository.getAllCrspayldacctrepByID(BigInteger.valueOf(accountHolderVo.getId()));
					}else{
						crspayldacct = new Crspayldacctrep();
					}
					if(!StringUtils.isEmpty(accountHolderVo.getAccountBalance())){
					crspayldacct.setAccountBalance(new BigDecimal(accountHolderVo.getAccountBalance()));
					}
					crspayldacct.setAccountCurrCode(accountHolderVo.getCurrency());
					crspayldacct.setAcctHolderType(accountHolderVo.getAccountHolderType());
					/*crspayldacct.setAccountHolder(accountHolderVo.getAccountHolderType());*/
					crspayldacct.setAccountNumber(accountHolderVo.getAccountNumber());
					/*crspayldacct.setAcctNumberType(accountHolderVo.getA);*/
					
					crspayldacct.setBirthCity(accountHolderVo.getCity());
					crspayldacct.setBirthCountry(accountHolderVo.getCountryCode());
					crspayldacct.setBirthDate(accountHolderVo.getBirthDate());
					crspayldacct.setBirthFormerCountry(accountHolderVo.getCountryName());
					crspayldacct.setBirthCitySubent(accountHolderVo.getCitySubEntity());
					crspayldacct.setBodyID(crsbody.getId());
					crspayldacct.setHdrID(crspayld.getId());
					if(accountHolderVo.getAccountNumberType() != null && accountHolderVo.getAccountNumberType().size() > 0){
						for(String s : accountHolderVo.getAccountNumberType()){
							if(s.equals("Closed")){
								crspayldacct.setClosedAccount("Y");
							}else if(s.equals("Dormant")){
								crspayldacct.setDormantAccount("Y");
							}else if(s.equals("Undocumented")){
								crspayldacct.setUndocumentedAccount("Y");
							}
							
						}
					}else{
						crspayldacct.setClosedAccount("N");
						crspayldacct.setDormantAccount("N");
						crspayldacct.setUndocumentedAccount("N");
					}
					crspayldacct.setCorrDocRefId(accountHolderVo.getCorMessageDocRefId());
					crspayldacct.setCreateDateTime(new Date());
					crspayldacct.setDocRefId(accountHolderVo.getDocumentRefId());
					crspayldacct.setDocTypeIndic(accountHolderVo.getDocumentTypeIndic());
					crspayldacct = crspayldacctrepRepository.saveAndFlush(crspayldacct);
					logger.info("<<<<<<<<<<<AccountHolder[Crspayldacctrep] End saving>>>>>>>>>>>>>>>"+crspayldacct.getId());
					
					
					
					//Payment Detail
					if(accountHolderVo.getPaymentList() != null && accountHolderVo.getPaymentList().size() > 0){
						logger.info("<<<<<<<<<<<Payment Detail[Crspayldpymt] Begin saving>>>>>>>>>>>>>>>");
						for(PaymentTypeVo paymentVo : accountHolderVo.getPaymentList()){
							Crspayldpymt crspayment = new Crspayldpymt();
							crspayment.setAcctRepID(crspayldacct.getId());
							if(paymentVo.getCurrency()> 0){
							crspayment.setCurrCode(String.valueOf(paymentVo.getCurrency()));
							}
							if(paymentVo.getAmount() != null){
							crspayment.setPaymentAmt(new BigDecimal(paymentVo.getAmount()));
							}
							if(paymentVo.getPaymentType() > 0){
							crspayment.setPaymentType(String.valueOf(paymentVo.getPaymentType()));
							}
							crspayment = crspayldpymtRepository.saveAndFlush(crspayment);
						}
					}
					
					
					
					
					if(accountHolderVo.getAccountHolderType() != null){
						if(accountHolderVo.getAccountHolderType().equals("individual")){
						logger.info("Individual Section Saving part Begin Here");
						
						//Individual Address
						if(accountHolderVo.getIndividualAddressList() != null && accountHolderVo.getIndividualAddressList().size()  > 0){
						
							logger.info("<<<<<<<<<<<Individual Address[Crspayldaddress] Begin saving>>>>>>>>>>>>>>>");
							for(AddressVo addressVo :accountHolderVo.getIndividualAddressList()){
								Crspayldaddress address = new Crspayldaddress();
								if(!StringUtils.isEmpty(addressVo.getAddressFree())){
								address.setAddressFree(addressVo.getAddressFree());
								}
								if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
								address.setBuildingIdentifier(addressVo.getBuildingIdentifier());
								}
								if(!StringUtils.isEmpty(addressVo.getCity())){
								address.setCity(addressVo.getCity());
								}
								if(!StringUtils.isEmpty(addressVo.getCountryCode())){
								address.setCountryCode(addressVo.getCountryCode());
								}
								if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
								address.setCountrySubentity(addressVo.getCountrySubentity());
								}
								address.setCreateDateTime(new Date());
								if(!StringUtils.isEmpty(addressVo.getDistrictName())){
								address.setDistrictName(addressVo.getDistrictName());
								}
								if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
								address.setFloorIdentifier(addressVo.getFloorIdentifier());
								}
								if(!StringUtils.isEmpty(addressVo.getAddressType())){
								address.setLegalAddressType(addressVo.getAddressType());
								}
								address.setObjectID(crspayldacct.getId());
								if(!StringUtils.isEmpty(addressVo.getPob())){
								address.setPob(addressVo.getPob());
								}
								if(!StringUtils.isEmpty(addressVo.getPostCode())){
								address.setPostCode(addressVo.getPostCode());
								}
								address.setSrcType("0");
								if(!StringUtils.isEmpty(addressVo.getStreet())){
								address.setStreet(addressVo.getStreet());
								}
								if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
								address.setSuiteIdentifier(addressVo.getSuitIdentifier());
								}
								address = crspayldaddressRepository.saveAndFlush(address);
							}
							logger.info("<<<<<<<<<<<Individual Address[Crspayldaddress] End saving>>>>>>>>>>>>>>>");
							
						
						}
						
						//Individual Resident Country
						if(accountHolderVo.getIndividualResidentCountryList() != null && accountHolderVo.getIndividualResidentCountryList().size() >0){
							logger.info("<<<<<<<<<<<Indidivual Resident Country[Crspayldrescountry] Begin saving>>>>>>>>>>>>>>>");
							for(ResidentCountryVo residentCountry : accountHolderVo.getIndividualResidentCountryList()){
								Crspayldrescountry crspayldrescountry = new Crspayldrescountry();
								if(residentCountry.getResidentCountryCode() > 0){
								crspayldrescountry.setResCountryCode(String.valueOf(residentCountry.getResidentCountryCode()));
								}
								crspayldrescountry.setObjectID(crspayldacct.getId());
								crspayldrescountry.setSrcType("0");
								crspayldrescountry = crspayldrescountryRepository.saveAndFlush(crspayldrescountry);
							}
							logger.info("<<<<<<<<<<<Indidivual Resident Country[Crspayldrescountry] End saving>>>>>>>>>>>>>>>");
							
						}
						
						//Individaul  IN
						if(accountHolderVo.getIndividualOrganisationInTypeList() != null && accountHolderVo.getIndividualOrganisationInTypeList().size() > 0){
							logger.info("<<<<<<<<<<<Individual In type[Crspayldin] Begin saving>>>>>>>>>>>>>>>");
							for(OrganisationInTypeVo organisation : accountHolderVo.getIndividualOrganisationInTypeList()){
								Crspayldin crsPayldin = new Crspayldin();
								if(!StringUtils.isEmpty(organisation.getIn())){
								crsPayldin.setTin(organisation.getIn());
								}
								if(!StringUtils.isEmpty(organisation.getInType())){
								crsPayldin.setINType(organisation.getInType());
								}
								if(organisation.getIssuedBy() >0){
								crsPayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));
								}
								crsPayldin.setObjectID(crspayldacct.getId());
								crsPayldin.setSrcType("0");
								crsPayldin = crspayldinRepository.saveAndFlush(crsPayldin);
							}
							logger.info("<<<<<<<<<<<Individual In typ[Crspayldin] End saving>>>>>>>>>>>>>>>");
							
						}
						
						//Individual NameType Grid
						if(accountHolderVo.getIndividualNameList() != null && accountHolderVo.getIndividualNameList().size() > 0){
							logger.info("<<<<<<<<<<<Individual NameType Grid[Crspayldname] Begin saving>>>>>>>>>>>>>>>");	
							for(NameTypeVo nameVo : accountHolderVo.getIndividualNameList()){
								Crspayldname crspayldName = new Crspayldname();
								crspayldName.setCreateDateTime(new Date());
								crspayldName.setFirstName(nameVo.getFirstName());
								crspayldName.setGeneralSuffix(nameVo.getGeneralSuffix());
								crspayldName.setLastName(nameVo.getLastName());
								crspayldName.setNamePrefix(nameVo.getNamePrefix());
								crspayldName.setPrecedingTitle(nameVo.getPrecedingTitle());
								crspayldName.setObjectID(crspayldacct.getId());
								crspayldName.setSrcType("0");
								crspayldName = crspayldnameRepository.saveAndFlush(crspayldName);
								
								
								if(nameVo.getTitleList() != null &&nameVo.getTitleList().size() >0 ){
									for(TitleVo titleVo : nameVo.getTitleList()){
									Crspayldnametitle title = new Crspayldnametitle();
									title.setTitle(titleVo.getName());
									title.setNameID(crspayldName.getId());
									title.setCreateDateTime(new Date());
									title = crspayldnametitleRepository.saveAndFlush(title);
									}
								}
								
								if(nameVo.getMiddlenameList() != null && nameVo.getMiddlenameList().size() > 0){
									for(MiddleNameVo middileNamevo :nameVo.getMiddlenameList() ){
										Crspayldnamemiddle middilename = new Crspayldnamemiddle();
										middilename.setMiddleName(middileNamevo.getMiddleName());
										middilename.setCreateDateTime(new Date());
										middilename.setNameID(crspayldName.getId());
										middilename = crspayldnamemiddleRepository.saveAndFlush(middilename);
									}
								}
								
								if(nameVo.getGenerateIdentifilerList() != null &&nameVo.getGenerateIdentifilerList().size() >0 ){
									for(GenerationIdentifierVo generateId :nameVo.getGenerateIdentifilerList() ){
										Crspayldnamegeneration generationid = new Crspayldnamegeneration();
										generationid.setGenerationIdentifier(generateId.getGenerateIdentifier());
										generationid.setCreateDateTime(new Date());
										generationid.setNameID(crspayldName.getId());
										generationid = crspayldnamegenerationRepository.saveAndFlush(generationid);
									}
								}
								if(nameVo.getSuffixList() != null && nameVo.getSuffixList().size() >0){
									for(SuffixVo suffix :nameVo.getSuffixList() ){
										Crspayldnamesuffix suffi= new Crspayldnamesuffix();
										suffi.setSuffix(suffix.getSuffix());
										suffi.setNameID(crspayldName.getId());
										suffi.setCreateDateTime(new Date());
										suffi = crspayldnamesuffixRepository.saveAndFlush(suffi);
									}
								}	
								
							}
							logger.info("<<<<<<<<<<<Individual NameType Grid[Crspayldname] End saving>>>>>>>>>>>>>>>");
						}//NameList
						
						
						}
						
							
							
						}//Individual
						
						else if(accountHolderVo.getAccountHolderType().equals("organization")){
							logger.info("Organisation Section Saving part Begin Here");	
							
							if(accountHolderVo.getOrganisationAddressList() != null && accountHolderVo.getOrganisationAddressList().size()  > 0){
								logger.info("<<<<<<<<<<<Organisation Address[Crspayldaddress] Begin saving>>>>>>>>>>>>>>>");
								for(AddressVo addressVo :accountHolderVo.getOrganisationAddressList()){
									Crspayldaddress address = new Crspayldaddress();
									if(!StringUtils.isEmpty(addressVo.getAddressFree())){
									address.setAddressFree(addressVo.getAddressFree());
									}
									if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
									address.setBuildingIdentifier(addressVo.getBuildingIdentifier());
									}
									if(!StringUtils.isEmpty(addressVo.getCity())){
									address.setCity(addressVo.getCity());
									}
									if(!StringUtils.isEmpty(addressVo.getCountryCode())){
									address.setCountryCode(addressVo.getCountryCode());
									}
									if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
									address.setCountrySubentity(addressVo.getCountrySubentity());
									}
									address.setCreateDateTime(new Date());
									if(!StringUtils.isEmpty(addressVo.getDistrictName())){
									address.setDistrictName(addressVo.getDistrictName());
									}
									if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
									address.setFloorIdentifier(addressVo.getFloorIdentifier());
									}
									if(!StringUtils.isEmpty(addressVo.getAddressType())){
									address.setLegalAddressType(addressVo.getAddressType());
									}
									address.setObjectID(crspayldacct.getId());
									if(!StringUtils.isEmpty(addressVo.getPob())){
									address.setPob(addressVo.getPob());
									}
									if(!StringUtils.isEmpty(addressVo.getPostCode())){
									address.setPostCode(addressVo.getPostCode());
									}
									address.setSrcType("0");
									if(!StringUtils.isEmpty(addressVo.getStreet())){
									address.setStreet(addressVo.getStreet());
									}
									if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
									address.setSuiteIdentifier(addressVo.getSuitIdentifier());
									}
									address = crspayldaddressRepository.saveAndFlush(address);
								}
								logger.info("<<<<<<<<<<<Organisation Address[Crspayldaddress] End saving>>>>>>>>>>>>>>>");
								
							
							}
							
							//Organisation Resident Country
							if(accountHolderVo.getOrganisationResidentCountryList() != null && accountHolderVo.getOrganisationResidentCountryList().size() >0){
								logger.info("<<<<<<<<<<<Organisation Resident Country[Crspayldrescountry] Begin saving>>>>>>>>>>>>>>>");
								for(ResidentCountryVo residentCountry : accountHolderVo.getOrganisationResidentCountryList()){
									Crspayldrescountry crspayldrescountry = new Crspayldrescountry();
									if(residentCountry.getResidentCountryCode() > 0){
									crspayldrescountry.setResCountryCode(String.valueOf(residentCountry.getResidentCountryCode()));
									}
									crspayldrescountry.setObjectID(crspayldacct.getId());
									crspayldrescountry.setSrcType("0");
									crspayldrescountry = crspayldrescountryRepository.saveAndFlush(crspayldrescountry);
								}
								logger.info("<<<<<<<<<<<Organisation Resident Country[Crspayldrescountry] End saving>>>>>>>>>>>>>>>");
								
							}
							
							//Organisation  IN
							if(accountHolderVo.getOrgOrganisationInTypeList() != null && accountHolderVo.getOrgOrganisationInTypeList().size() > 0){
								logger.info("<<<<<<<<<<<Organisation In type[Crspayldin] Begin saving>>>>>>>>>>>>>>>");
								for(OrganisationInTypeVo organisation : accountHolderVo.getOrgOrganisationInTypeList()){
									Crspayldin crsPayldin = new Crspayldin();
									if(!StringUtils.isEmpty(organisation.getIn())){
									crsPayldin.setTin(organisation.getIn());
									}
									if(!StringUtils.isEmpty(organisation.getInType())){
									crsPayldin.setINType(organisation.getInType());
									}
									if(organisation.getIssuedBy() >0){
									crsPayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));
									}
									crsPayldin.setObjectID(crspayldacct.getId());
									crsPayldin.setSrcType("0");
									crsPayldin = crspayldinRepository.saveAndFlush(crsPayldin);
								}
								logger.info("<<<<<<<<<<<Organisation In typ[Crspayldin] End saving>>>>>>>>>>>>>>>");
								
							}
							
							//Organisation Grid
							if(accountHolderVo.getOrganisationList() != null && accountHolderVo.getOrganisationList().size() > 0){
								logger.info("<<<<<<<<<<<Organisation[Crspayldname] Begin saving>>>>>>>>>>>>>>>");	
								for(NameVo nameVo : accountHolderVo.getOrganisationList()){
									Crspayldname crspayldName = new Crspayldname();
									crspayldName.setNameOrganisation(nameVo.getFirstName());
									crspayldName.setNamePersonType(String.valueOf(nameVo.getNameType()));
									crspayldName.setObjectID(crspayldacct.getId());
									crspayldName.setSrcType("0");
									crspayldName = crspayldnameRepository.saveAndFlush(crspayldName);
									
								}
								logger.info("<<<<<<<<<<<Organisation[Crspayldname] End saving>>>>>>>>>>>>>>>");
							}
							
							
							
						}//Organisation
					
					
					//Controlling Person
					if(accountHolderVo.getControllingPersonList() != null && accountHolderVo.getControllingPersonList().size() > 0){
						logger.info("<<<<<<<<<<<Controlling Person[Crspayldctrlperson] Begin saving>>>>>>>>>>>>>>>");	
						for(ControllingPersonVo controlingPersonVo : accountHolderVo.getControllingPersonList()){
							Crspayldctrlperson crsctrlperson = new Crspayldctrlperson();
							crsctrlperson.setAcctRepID(crspayldacct.getId());
							crsctrlperson.setBirthCity(controlingPersonVo.getCity());
							crsctrlperson.setBirthCitySubent(controlingPersonVo.getCitySubEntity());
							crsctrlperson.setBirthCountry(controlingPersonVo.getCountryCode());
							crsctrlperson.setBirthFormerCountry(controlingPersonVo.getCountryName());
							crsctrlperson.setCreateDateTime(new Date());
							crsctrlperson.setCtrlgPersonType(controlingPersonVo.getControllingPersonType());
							crsctrlperson = crspayldctrlpersonRepository.saveAndFlush(crsctrlperson);
							logger.info("<<<<<<<<<<<Controlling Person[Crspayldctrlperson] End saving>>>>>>>>>>>>>>>"+crsctrlperson.getId());
							
							
							
							//Controlling Person Address
							if(controlingPersonVo.getControllingPersonAddressList() != null && controlingPersonVo.getControllingPersonAddressList().size()  > 0){
							
								logger.info("<<<<<<<<<<<Controlling Person Address[Crspayldaddress] Begin saving>>>>>>>>>>>>>>>");
								for(AddressVo addressVo :controlingPersonVo.getControllingPersonAddressList()){
									Crspayldaddress address = new Crspayldaddress();
									if(!StringUtils.isEmpty(addressVo.getAddressFree())){
									address.setAddressFree(addressVo.getAddressFree());
									}
									if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
									address.setBuildingIdentifier(addressVo.getBuildingIdentifier());
									}
									if(!StringUtils.isEmpty(addressVo.getCity())){
									address.setCity(addressVo.getCity());
									}
									if(!StringUtils.isEmpty(addressVo.getCountryCode())){
									address.setCountryCode(addressVo.getCountryCode());
									}
									if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
									address.setCountrySubentity(addressVo.getCountrySubentity());
									}
									address.setCreateDateTime(new Date());
									if(!StringUtils.isEmpty(addressVo.getDistrictName())){
									address.setDistrictName(addressVo.getDistrictName());
									}
									if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
									address.setFloorIdentifier(addressVo.getFloorIdentifier());
									}
									if(!StringUtils.isEmpty(addressVo.getAddressType())){
									address.setLegalAddressType(addressVo.getAddressType());
									}
									address.setObjectID(crsctrlperson.getId());
									if(!StringUtils.isEmpty(addressVo.getPob())){
									address.setPob(addressVo.getPob());
									}
									if(!StringUtils.isEmpty(addressVo.getPostCode())){
									address.setPostCode(addressVo.getPostCode());
									}
									address.setSrcType("0");
									if(!StringUtils.isEmpty(addressVo.getStreet())){
									address.setStreet(addressVo.getStreet());
									}
									if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
									address.setSuiteIdentifier(addressVo.getSuitIdentifier());
									}
									address = crspayldaddressRepository.saveAndFlush(address);
								}
								logger.info("<<<<<<<<<<<Controlling Person Address[Crspayldaddress] End saving>>>>>>>>>>>>>>>");
								
							
							}
							
							//Controlling Person Resident Country
							if(controlingPersonVo.getControllingResidentCountryList() != null && controlingPersonVo.getControllingResidentCountryList().size() >0){
								logger.info("<<<<<<<<<<<Controlling Person Resident Country[Crspayldrescountry] Begin saving>>>>>>>>>>>>>>>");
								for(ResidentCountryVo residentCountry : controlingPersonVo.getControllingResidentCountryList()){
									Crspayldrescountry crspayldrescountry = new Crspayldrescountry();
									if(residentCountry.getResidentCountryCode() > 0){
									crspayldrescountry.setResCountryCode(String.valueOf(residentCountry.getResidentCountryCode()));
									}
									crspayldrescountry.setObjectID(crsctrlperson.getId());
									crspayldrescountry.setSrcType("0");
									crspayldrescountry = crspayldrescountryRepository.saveAndFlush(crspayldrescountry);
								}
								logger.info("<<<<<<<<<<<Controlling Person Resident Country[Crspayldrescountry] End saving>>>>>>>>>>>>>>>");
								
							}
							
							//Controlling Person   IN
							if(controlingPersonVo.getControllingOrganisationInTypeList() != null && controlingPersonVo.getControllingOrganisationInTypeList().size() > 0){
								logger.info("<<<<<<<<<<<Controlling Person In type[Crspayldin] Begin saving>>>>>>>>>>>>>>>");
								for(OrganisationInTypeVo organisation : controlingPersonVo.getControllingOrganisationInTypeList()){
									Crspayldin crsPayldin = new Crspayldin();
									if(!StringUtils.isEmpty(organisation.getIn())){
									crsPayldin.setTin(organisation.getIn());
									}
									if(!StringUtils.isEmpty(organisation.getInType())){
									crsPayldin.setINType(organisation.getInType());
									}
									if(organisation.getIssuedBy() >0){
									crsPayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));
									}
									crsPayldin.setObjectID(crsctrlperson.getId());
									crsPayldin.setSrcType("0");
									crsPayldin = crspayldinRepository.saveAndFlush(crsPayldin);
								}
								logger.info("<<<<<<<<<<<Controlling Person In typ[Crspayldin] End saving>>>>>>>>>>>>>>>");
								
							}
							
							//Controlling Person NameType Grid
							if(controlingPersonVo.getNameTypeList() != null && controlingPersonVo.getNameTypeList().size() > 0){
								logger.info("<<<<<<<<<<<Controlling Person NameType Grid[Crspayldname] Begin saving>>>>>>>>>>>>>>>");	
								for(NameTypeVo nameVo : controlingPersonVo.getNameTypeList()){
									Crspayldname crspayldName = new Crspayldname();
									crspayldName.setCreateDateTime(new Date());
									crspayldName.setFirstName(nameVo.getFirstName());
									crspayldName.setGeneralSuffix(nameVo.getGeneralSuffix());
									crspayldName.setLastName(nameVo.getLastName());
									crspayldName.setNamePrefix(nameVo.getNamePrefix());
									crspayldName.setPrecedingTitle(nameVo.getPrecedingTitle());
									crspayldName.setObjectID(crsctrlperson.getId());
									crspayldName.setSrcType("0");
									crspayldName = crspayldnameRepository.saveAndFlush(crspayldName);
									
									
									if(nameVo.getTitleList() != null &&nameVo.getTitleList().size() >0 ){
										for(TitleVo titleVo : nameVo.getTitleList()){
										Crspayldnametitle title = new Crspayldnametitle();
										title.setTitle(titleVo.getName());
										title.setNameID(crspayldName.getId());
										title.setCreateDateTime(new Date());
										title = crspayldnametitleRepository.saveAndFlush(title);
										}
									}
									
									if(nameVo.getMiddlenameList() != null && nameVo.getMiddlenameList().size() > 0){
										for(MiddleNameVo middileNamevo :nameVo.getMiddlenameList() ){
											Crspayldnamemiddle middilename = new Crspayldnamemiddle();
											middilename.setMiddleName(middileNamevo.getMiddleName());
											middilename.setCreateDateTime(new Date());
											middilename.setNameID(crspayldName.getId());
											middilename = crspayldnamemiddleRepository.saveAndFlush(middilename);
										}
									}
									
									if(nameVo.getGenerateIdentifilerList() != null &&nameVo.getGenerateIdentifilerList().size() >0 ){
										for(GenerationIdentifierVo generateId :nameVo.getGenerateIdentifilerList() ){
											Crspayldnamegeneration generationid = new Crspayldnamegeneration();
											generationid.setGenerationIdentifier(generateId.getGenerateIdentifier());
											generationid.setCreateDateTime(new Date());
											generationid.setNameID(crspayldName.getId());
											generationid = crspayldnamegenerationRepository.saveAndFlush(generationid);
										}
									}
									if(nameVo.getSuffixList() != null && nameVo.getSuffixList().size() >0){
										for(SuffixVo suffix :nameVo.getSuffixList() ){
											Crspayldnamesuffix suffi= new Crspayldnamesuffix();
											suffi.setSuffix(suffix.getSuffix());
											suffi.setNameID(crspayldName.getId());
											suffi.setCreateDateTime(new Date());
											suffi = crspayldnamesuffixRepository.saveAndFlush(suffi);
										}
									}	
									
								}
								logger.info("<<<<<<<<<<<Individual NameType Grid[Crspayldname] End saving>>>>>>>>>>>>>>>");
							}//NameList
							
							
							
							
							
							
							
						}
						
						
					}//Controlling Person List End
					
					
						
						
						
					}
							
					
				}//Account holder List
					
								
					
				}//Account Holder
				
				
				
					
			
		}//CRS Payldhdr
		
		

		
		}//MetaDataVo
		
		
		return hidefVo;
	}

	@Override
	public HidefVo getAllDatabyCRSId(HidefVo hidefvo) {
		logger.info("Get all the CBC Details based on CRSID:::" + hidefvo.getMycbcId());
		List<HidefVo> hidefList = new ArrayList<HidefVo>();
		List<CRSSummaryGridVo> summaryList = new ArrayList<CRSSummaryGridVo>();
		List<Crspayldhdr> crsPayldhdr = crspayldhdrRepository.getAllCrsDetails(new BigInteger(hidefvo.getMycbcId()));
		
		if(crsPayldhdr != null && crsPayldhdr.size() >0){
			for(Crspayldhdr crspayldhdr : crsPayldhdr){
				CRSSummaryGridVo summary = new CRSSummaryGridVo();

				summary.setMessageType(crspayldhdr.getMessageType());
				summary.setSendingCountry(crspayldhdr.getSenderCountryCd());
				summary.setId(summaryList.size() + 1);
				summary.setHrdId(crspayldhdr.getId());
				summaryList.add(summary);
				
			}
			
			
		}//Payloadhdr
		
		hidefvo.setCrssummary(summaryList);
		
		return hidefvo;
	}

	@Override
	public HidefVo viewAllDatabyCRSId(HidefVo hidefvo, String id) {
		// TODO Auto-generated method stub
		
		Crspayldhdr crspayldhdr = crspayldhdrRepository.getAllById(new BigInteger(id));
		if(crspayldhdr  != null){
			CrsMetadataVo crsMeta = new CrsMetadataVo();
			crsMeta.setBinaryEncoding(crspayldhdr.getBinaryEncodingSchemeCd());
			crsMeta.setCommunicationType(crspayldhdr.getCommunicationTypeCd());
			crsMeta.setContact(crspayldhdr.getContact());
			/*crsMeta.setCorMessageReferenceId(crspayldhdr.);*/
			crsMeta.setFileCreationTimestramp(crspayldhdr.getFileCreateTs());
			crsMeta.setFileFormatCode(crspayldhdr.getFileFormatCD());
			crsMeta.setMessageReferenceId(crspayldhdr.getMessageRefId());
			crsMeta.setMessageType(crspayldhdr.getMessageType());
			crsMeta.setMessageTypeIndic(crspayldhdr.getMessageTypeIndic());
			crsMeta.setReceivingCountry(crspayldhdr.getReceivingCountry());
			crsMeta.setReportingPeriod(crspayldhdr.getReportingPeriod());
			crsMeta.setSenderContactEmail(crspayldhdr.getSenderContactEmailAddressTxt());
			crsMeta.setSenderFileId(crspayldhdr.getSenderFileId());
			crsMeta.setSendingCompanyIn(crspayldhdr.getSendingCompanyIN());
			crsMeta.setSendingCountry(crspayldhdr.getSenderCountryCd());
			if(crspayldhdr.getTaxYear() != null){
			crsMeta.setTaxYear(String.valueOf(crspayldhdr.getTaxYear()));
			}
			crsMeta.setWarning(crspayldhdr.getWarning());
			crsMeta.setId(crspayldhdr.getId());
			
			hidefvo.setCrsmetadata(crsMeta);
			
			Crspayldbody crsbody = crspayldbodyRepository.getAllCrsBodyDetailsByHrdid(crspayldhdr.getId());
			if(crsbody != null){
				
				Crspayldfi  crsReportingFI = crspayldfiRepository.getAllCrspayldfiByBodyID(crsbody.getId());
				if(crsReportingFI != null){
					CrsReportingFiVo crsReportingFIVo = new CrsReportingFiVo();
					crsReportingFIVo.setCorDocRefId(crsReportingFI.getCorrDocRefId());
					/*crsReportingFIVo.setCorMmsgRefId(crsReportingFI.get);*/
					crsReportingFIVo.setDocRefId(crsReportingFI.getDocRefId());
					crsReportingFIVo.setDocumentTypeIndic(crsReportingFI.getDocTypeIndic());
					crsReportingFIVo.setId(crsReportingFI.getId());
					
					List<Crspayldname> payldName= crspayldnameRepository.getAllCrspayldfiByObjectID(crsReportingFI.getId());
					List<NameVo> nameList = new ArrayList<NameVo>();
					if(payldName != null && payldName.size()>0){
						
						for(Crspayldname name:payldName){
							NameVo nameVo = new NameVo();
							nameVo.setFirstName(name.getNameOrganisation());
							if(name.getNamePersonType() != null){
							nameVo.setNameType(Integer.parseInt(name.getNamePersonType()));
							}
							nameVo.setId(name.getId().intValue());
							nameList.add(nameVo);
						}
					}
					crsReportingFIVo.setNameList(nameList);
					
					List<OrganisationInTypeVo> organisationInTypeList = new ArrayList<OrganisationInTypeVo>();
					List<Crspayldin>  reportingFiIn = crspayldinRepository.getAllCrspayldinByObjectID(crsReportingFI.getId());
					if(reportingFiIn != null && reportingFiIn.size() > 0){
						for(Crspayldin in : reportingFiIn){
							OrganisationInTypeVo orgIn = new OrganisationInTypeVo();
							orgIn.setIn(in.getTin());
							orgIn.setInType(in.getINType());
							if(in.getIssuedBy() != null){
							orgIn.setIssuedBy(Integer.parseInt(in.getIssuedBy()));
							}
							orgIn.setId(in.getId().intValue());
							organisationInTypeList.add(orgIn);
							
						}
					}
					crsReportingFIVo.setOrganisationInTypeList(organisationInTypeList);
					
					List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
					List<Crspayldrescountry> crspayldrec = crspayldrescountryRepository.getAllCrspayldrescountryByObjectID(crsReportingFI.getId());
					if(crspayldrec != null && crspayldrec.size()>0){
						for(Crspayldrescountry res :crspayldrec){
							ResidentCountryVo residentCountry = new ResidentCountryVo();
							residentCountry.setId(res.getId().intValue());
							if(res.getResCountryCode() != null){
							residentCountry.setResidentCountryCode(Integer.parseInt(res.getResCountryCode()));
							}
							residentCountryList.add(residentCountry);
						}
					}
					crsReportingFIVo.setResidentCountryList(residentCountryList);
					
					List<AddressVo> addressList= new ArrayList<AddressVo>();
					List<Crspayldaddress> crsReposrtingFiaddress = crspayldaddressRepository.getAllCrspayldaddressByObjectID(crsReportingFI.getId());
					if(crsReposrtingFiaddress != null && crsReposrtingFiaddress.size() >0){
						for(Crspayldaddress address : crsReposrtingFiaddress){
							AddressVo addressVo = new AddressVo();
							addressVo.setAddressFree(address.getAddressFree());
							addressVo.setAddressType(address.getLegalAddressType());
							addressVo.setAddressTypeId(address.getLegalAddressType());
							addressVo.setBuildingIdentifier(address.getBuildingIdentifier());
							addressVo.setCity(address.getCity());
							addressVo.setCountryCode(address.getCountryCode());
							addressVo.setCountryCodeId(address.getCountryCode());
							addressVo.setCountrySubentity(address.getCountrySubentity());
							addressVo.setDistrictName(address.getDistrictName());
							addressVo.setFloorIdentifier(address.getFloorIdentifier());
							addressVo.setId(address.getId().intValue());
							addressVo.setPob(address.getPob());
							addressVo.setPostCode(address.getPostCode());
							addressVo.setStreet(address.getStreet());
							addressVo.setSuitIdentifier(address.getSuiteIdentifier());
							addressList.add(addressVo);
						}
					}
					crsReportingFIVo.setAddressList(addressList);
					
					
					hidefvo.setCrsreportingfi(crsReportingFIVo);
					
					
				}
				
				
				Crspayldacctrep crspayldacctrep = crspayldacctrepRepository.getAllCrspayldacctrepByBodyID(crsbody.getId());
				
				if(crspayldacctrep != null){
					AccountHolderVo accountholder = new AccountHolderVo();
					List<AccountHolderVo> accountHolderList = new ArrayList<AccountHolderVo>();
					accountholder.setAccountHolderType(crspayldacctrep.getAcctHolderType());
					accountholder.setAccountNumber(crspayldacctrep.getAccountNumber());
					accountholder.setBirthDate(crspayldacctrep.getBirthDate());
					accountholder.setCity(crspayldacctrep.getBirthCity());
					accountholder.setCitySubEntity(crspayldacctrep.getBirthCitySubent());
					accountholder.setCurrency(crspayldacctrep.getAccountCurrCode());
					accountholder.setDocumentTypeIndic(crspayldacctrep.getDocTypeIndic());
					accountholder.setCorMessageDocRefId(crspayldacctrep.getCorrDocRefId());
					accountholder.setCorMessageRefId(crspayldacctrep.getCorrDocRefId());
					accountholder.setCountryCode(crspayldacctrep.getBirthCountry());
					accountholder.setCountryName(crspayldacctrep.getBirthFormerCountry());
					accountholder.setCurrency(crspayldacctrep.getAccountCurrCode());
					accountholder.setDocumentRefId(crspayldacctrep.getDocRefId());
					accountholder.setId(crspayldacctrep.getId().intValue());
					if(crspayldacctrep.getAccountBalance() != null){
					accountholder.setAccountBalance(String.valueOf(crspayldacctrep.getAccountBalance()));
					}
					List<String> accountType = new ArrayList<String>();
					if(crspayldacctrep.getDormantAccount() != null && crspayldacctrep.getDormantAccount().equals("Y")){
						accountType.add("Dormant");
					}if(crspayldacctrep.getClosedAccount() != null && crspayldacctrep.getClosedAccount().equals("Y")){
						accountType.add("Closed");
					}if(crspayldacctrep.getUndocumentedAccount() != null && crspayldacctrep.getUndocumentedAccount().equals("Y")){
						accountType.add("Undocumented");
					}
					if(accountType != null && accountType.size() >0){
						accountholder.setAccountNumberType(accountType);
					}
					
					//Payment Details
					List<Crspayldpymt> crspaymtList = crspayldpymtRepository.getAllCrspayldpymtByacctRepID(crspayldacctrep.getId());
					if(crspaymtList != null && crspaymtList.size() > 0){
						List<PaymentTypeVo> paymentList = new ArrayList<PaymentTypeVo>();
						for(Crspayldpymt crspaymt : crspaymtList){
							PaymentTypeVo paymentVo = new PaymentTypeVo();
							if(crspaymt.getPaymentAmt() != null){
							paymentVo.setAmount(String.valueOf(crspaymt.getPaymentAmt()));
							}
							if(crspaymt.getCurrCode() != null){
							
							paymentVo.setCurrency(Integer.parseInt(crspaymt.getCurrCode()));
							}
							if(crspaymt.getPaymentType() != null){
							paymentVo.setPaymentType(Integer.parseInt(crspaymt.getPaymentType()));
							}
							paymentVo.setId(crspaymt.getId().intValue());
							paymentList.add(paymentVo);
						}
						accountholder.setPaymentList(paymentList);
						
					}
					
					//Controlling Person List
					
					List<Crspayldctrlperson> crspayldctrlpersonList = crspayldctrlpersonRepository.getAllCrspayldctrlpersonAcctRepID(crspayldacctrep.getId());
					if(crspayldctrlpersonList != null && crspayldctrlpersonList.size() > 0){
						List<ControllingPersonVo> controllingPersonList = new ArrayList<ControllingPersonVo>();
						for(Crspayldctrlperson ctrlPerson : crspayldctrlpersonList){
						ControllingPersonVo ctrlPersonVo = new  ControllingPersonVo();
						ctrlPersonVo.setBirthDate(ctrlPerson.getBirthDate());
						ctrlPersonVo.setCity(ctrlPerson.getBirthCity());
						ctrlPersonVo.setCitySubEntity(ctrlPerson.getBirthCitySubent());
						ctrlPersonVo.setCountryCode(ctrlPerson.getBirthCountry());
						ctrlPersonVo.setCountryName(ctrlPerson.getBirthFormerCountry());
						ctrlPersonVo.setId(ctrlPerson.getId().intValue());
						
						// Controlling Person Resident Country
						List<Crspayldrescountry> residentCountryList = crspayldrescountryRepository.getAllCrspayldrescountryByObjectID(ctrlPerson.getId());
						if(residentCountryList != null &&  residentCountryList.size() >0){
							List<ResidentCountryVo> residnetList = new ArrayList<ResidentCountryVo>();
							for(Crspayldrescountry individualRes : residentCountryList){
								ResidentCountryVo residentVo = new ResidentCountryVo();
								residentVo.setId(individualRes.getId().intValue());
								if(individualRes.getResCountryCode() != null){
								residentVo.setResidentCountryCode(Integer.parseInt(individualRes.getResCountryCode()));
								}
								residnetList.add(residentVo);
							}
							ctrlPersonVo.setControllingResidentCountryList(residnetList);
						}
						
						//Controlling Person IN 
						List<Crspayldin> crsPayldInList = crspayldinRepository.getAllCrspayldinByObjectID(ctrlPerson.getId());
						if(crsPayldInList != null && crsPayldInList.size() >0){
							List<OrganisationInTypeVo> individualOrgInlist = new ArrayList<OrganisationInTypeVo>();
							for(Crspayldin crspayldin : crsPayldInList){
								OrganisationInTypeVo orgIntyleList = new OrganisationInTypeVo();
								orgIntyleList.setId(crspayldin.getId().intValue());
								orgIntyleList.setIn(crspayldin.getTin());
								orgIntyleList.setInType(crspayldin.getINType());
								if(crspayldin.getIssuedBy() != null){
								orgIntyleList.setIssuedBy(Integer.parseInt(crspayldin.getIssuedBy()));
								}
								individualOrgInlist.add(orgIntyleList);
							}
							ctrlPersonVo.setControllingOrganisationInTypeList(individualOrgInlist);
						}
						
						
						//Controlling Person Name Grid
						List<Crspayldname> CrspayldnameList =  crspayldnameRepository.getAllCrspayldfiByObjectID(ctrlPerson.getId());
						if(CrspayldnameList != null && CrspayldnameList.size() > 0){
							List<NameTypeVo> nameTypeVoList = new ArrayList<NameTypeVo>();
							for(Crspayldname name:CrspayldnameList){
								NameTypeVo nameType = new NameTypeVo();
								nameType.setFirstName(name.getFirstName());
								nameType.setGeneralSuffix(name.getGeneralSuffix());
								nameType.setId(name.getId().intValue());
								nameType.setLastName(name.getLastName());
								nameType.setNamePrefix(name.getNamePrefix());
								nameType.setNameType(name.getNamePersonType());
								nameType.setPrecedingTitle(name.getPrecedingTitle());
								
								List<TitleVo> titleList = new ArrayList<TitleVo>();
								List<Crspayldnametitle> CrspayldnametitleList = crspayldnametitleRepository.getAllCrspayldnametitleNameID(name.getId());
								if(CrspayldnametitleList != null && CrspayldnametitleList.size() > 0){
									for(Crspayldnametitle tittle: CrspayldnametitleList){
										TitleVo titleVo = new TitleVo();
										titleVo.setId(tittle.getId().intValue());
										titleList.add(titleVo);
									}
								}
								nameType.setTitleList(titleList);
								
								List<MiddleNameVo> middlevo = new ArrayList<MiddleNameVo>();
								List<Crspayldnamemiddle> middleNameList = crspayldnamemiddleRepository.getAllCrspayldnamemiddleNameID(name.getId());
								if(middleNameList != null && middleNameList.size() > 0){
									for(Crspayldnamemiddle middleNameVo : middleNameList){
										MiddleNameVo middleName = new MiddleNameVo();
										middleName.setId(middleNameVo.getId().intValue());
										middleName.setMiddleName(middleNameVo.getMiddleName());
										middlevo.add(middleName);
									}
								}
								nameType.setMiddlenameList(middlevo);
								
								List<GenerationIdentifierVo> generateList = new ArrayList<GenerationIdentifierVo>();
								List<Crspayldnamegeneration> crspayldnamegenerationList = crspayldnamegenerationRepository.getAllCrspayldnamegenerationNameID(name.getId());
								if(crspayldnamegenerationList != null && crspayldnamegenerationList.size()>0){
									for(Crspayldnamegeneration generate:crspayldnamegenerationList){
										GenerationIdentifierVo generateId = new GenerationIdentifierVo();
										generateId.setId(generate.getId().intValue());
										generateId.setGenerateIdentifier(generate.getGenerationIdentifier());
										generateList.add(generateId);
									}
								}
								nameType.setGenerateIdentifilerList(generateList);
								
								List<SuffixVo> suffixList = new ArrayList<SuffixVo>();
								List<Crspayldnamesuffix> crspayldnamesuffixList = crspayldnamesuffixRepository.getAllCrspayldnamesuffixNameID(name.getId());
								if(crspayldnamesuffixList != null && crspayldnamesuffixList.size() >0){
									for(Crspayldnamesuffix suffix : crspayldnamesuffixList){
										SuffixVo suffixVo = new SuffixVo();
										suffixVo.setId(suffix.getId().intValue());
										suffixVo.setSuffix(suffix.getSuffix());
										suffixList.add(suffixVo);
									}
								}
								nameType.setSuffixList(suffixList);
								
								nameTypeVoList.add(nameType);
								
								
							}
							ctrlPersonVo.setNameTypeList(nameTypeVoList);
							
						}
						
						//Address Grid
						List<AddressVo> addressList= new ArrayList<AddressVo>();
						List<Crspayldaddress> crsReposrtingFiaddress = crspayldaddressRepository.getAllCrspayldaddressByObjectID(ctrlPerson.getId());
						if(crsReposrtingFiaddress != null && crsReposrtingFiaddress.size() >0){
							for(Crspayldaddress address : crsReposrtingFiaddress){
								AddressVo addressVo = new AddressVo();
								addressVo.setAddressFree(address.getAddressFree());
								addressVo.setAddressType(address.getLegalAddressType());
								addressVo.setAddressTypeId(address.getLegalAddressType());
								addressVo.setBuildingIdentifier(address.getBuildingIdentifier());
								addressVo.setCity(address.getCity());
								addressVo.setCountryCode(address.getCountryCode());
								addressVo.setCountryCodeId(address.getCountryCode());
								addressVo.setCountrySubentity(address.getCountrySubentity());
								addressVo.setDistrictName(address.getDistrictName());
								addressVo.setFloorIdentifier(address.getFloorIdentifier());
								addressVo.setId(address.getId().intValue());
								addressVo.setPob(address.getPob());
								addressVo.setPostCode(address.getPostCode());
								addressVo.setStreet(address.getStreet());
								addressVo.setSuitIdentifier(address.getSuiteIdentifier());
								addressList.add(addressVo);
							}
							ctrlPersonVo.setControllingPersonAddressList(addressList);
						}		
						
						controllingPersonList.add(ctrlPersonVo);
						
						}//For end
						
						accountholder.setControllingPersonList(controllingPersonList);
					}
					
					
					
					
					
					
					
					
					
					if(accountholder.getAccountHolderType() != null && accountholder.getAccountHolderType().equals("individual")){
						
					// Individual Resident Country
						List<Crspayldrescountry> residentCountryList = crspayldrescountryRepository.getAllCrspayldrescountryByObjectID(crspayldacctrep.getId());
						if(residentCountryList != null &&  residentCountryList.size() >0){
							List<ResidentCountryVo> residnetList = new ArrayList<ResidentCountryVo>();
							for(Crspayldrescountry individualRes : residentCountryList){
								ResidentCountryVo residentVo = new ResidentCountryVo();
								residentVo.setId(individualRes.getId().intValue());
								if(individualRes.getResCountryCode() != null){
								residentVo.setResidentCountryCode(Integer.parseInt(individualRes.getResCountryCode()));
								}
								residnetList.add(residentVo);
							}
							accountholder.setIndividualResidentCountryList(residnetList);
						}
					
						//Individual IN 
						List<Crspayldin> crsPayldInList = crspayldinRepository.getAllCrspayldinByObjectID(crspayldacctrep.getId());
						if(crsPayldInList != null && crsPayldInList.size() >0){
							List<OrganisationInTypeVo> individualOrgInlist = new ArrayList<OrganisationInTypeVo>();
							for(Crspayldin crspayldin : crsPayldInList){
								OrganisationInTypeVo orgIntyleList = new OrganisationInTypeVo();
								orgIntyleList.setId(crspayldin.getId().intValue());
								orgIntyleList.setIn(crspayldin.getTin());
								orgIntyleList.setInType(crspayldin.getINType());
								if(crspayldin.getIssuedBy() != null){
								orgIntyleList.setIssuedBy(Integer.parseInt(crspayldin.getIssuedBy()));
								}
								individualOrgInlist.add(orgIntyleList);
							}
							accountholder.setIndividualOrganisationInTypeList(individualOrgInlist);
						}
						//Name Grid
						List<Crspayldname> CrspayldnameList =  crspayldnameRepository.getAllCrspayldfiByObjectID(crspayldacctrep.getId());
						if(CrspayldnameList != null && CrspayldnameList.size() > 0){
							List<NameTypeVo> nameTypeVoList = new ArrayList<NameTypeVo>();
							for(Crspayldname name:CrspayldnameList){
								NameTypeVo nameType = new NameTypeVo();
								nameType.setFirstName(name.getFirstName());
								nameType.setGeneralSuffix(name.getGeneralSuffix());
								nameType.setId(name.getId().intValue());
								nameType.setLastName(name.getLastName());
								nameType.setNamePrefix(name.getNamePrefix());
								nameType.setNameType(name.getNamePersonType());
								nameType.setPrecedingTitle(name.getPrecedingTitle());
								
								List<TitleVo> titleList = new ArrayList<TitleVo>();
								List<Crspayldnametitle> CrspayldnametitleList = crspayldnametitleRepository.getAllCrspayldnametitleNameID(name.getId());
								if(CrspayldnametitleList != null && CrspayldnametitleList.size() > 0){
									for(Crspayldnametitle tittle: CrspayldnametitleList){
										TitleVo titleVo = new TitleVo();
										titleVo.setId(tittle.getId().intValue());
										titleList.add(titleVo);
									}
								}
								nameType.setTitleList(titleList);
								
								List<MiddleNameVo> middlevo = new ArrayList<MiddleNameVo>();
								List<Crspayldnamemiddle> middleNameList = crspayldnamemiddleRepository.getAllCrspayldnamemiddleNameID(name.getId());
								if(middleNameList != null && middleNameList.size() > 0){
									for(Crspayldnamemiddle middleNameVo : middleNameList){
										MiddleNameVo middleName = new MiddleNameVo();
										middleName.setId(middleNameVo.getId().intValue());
										middleName.setMiddleName(middleNameVo.getMiddleName());
										middlevo.add(middleName);
									}
								}
								nameType.setMiddlenameList(middlevo);
								
								List<GenerationIdentifierVo> generateList = new ArrayList<GenerationIdentifierVo>();
								List<Crspayldnamegeneration> crspayldnamegenerationList = crspayldnamegenerationRepository.getAllCrspayldnamegenerationNameID(name.getId());
								if(crspayldnamegenerationList != null && crspayldnamegenerationList.size()>0){
									for(Crspayldnamegeneration generate:crspayldnamegenerationList){
										GenerationIdentifierVo generateId = new GenerationIdentifierVo();
										generateId.setId(generate.getId().intValue());
										generateId.setGenerateIdentifier(generate.getGenerationIdentifier());
										generateList.add(generateId);
									}
								}
								nameType.setGenerateIdentifilerList(generateList);
								
								List<SuffixVo> suffixList = new ArrayList<SuffixVo>();
								List<Crspayldnamesuffix> crspayldnamesuffixList = crspayldnamesuffixRepository.getAllCrspayldnamesuffixNameID(name.getId());
								if(crspayldnamesuffixList != null && crspayldnamesuffixList.size() >0){
									for(Crspayldnamesuffix suffix : crspayldnamesuffixList){
										SuffixVo suffixVo = new SuffixVo();
										suffixVo.setId(suffix.getId().intValue());
										suffixVo.setSuffix(suffix.getSuffix());
										suffixList.add(suffixVo);
									}
								}
								nameType.setSuffixList(suffixList);
								
								nameTypeVoList.add(nameType);
								
								
							}
							accountholder.setIndividualNameList(nameTypeVoList);
							
						}
						
						//Address Grid
						List<AddressVo> addressList= new ArrayList<AddressVo>();
						List<Crspayldaddress> crsReposrtingFiaddress = crspayldaddressRepository.getAllCrspayldaddressByObjectID(crspayldacctrep.getId());
						if(crsReposrtingFiaddress != null && crsReposrtingFiaddress.size() >0){
							for(Crspayldaddress address : crsReposrtingFiaddress){
								AddressVo addressVo = new AddressVo();
								addressVo.setAddressFree(address.getAddressFree());
								addressVo.setAddressType(address.getLegalAddressType());
								addressVo.setAddressTypeId(address.getLegalAddressType());
								addressVo.setBuildingIdentifier(address.getBuildingIdentifier());
								addressVo.setCity(address.getCity());
								addressVo.setCountryCode(address.getCountryCode());
								addressVo.setCountryCodeId(address.getCountryCode());
								addressVo.setCountrySubentity(address.getCountrySubentity());
								addressVo.setDistrictName(address.getDistrictName());
								addressVo.setFloorIdentifier(address.getFloorIdentifier());
								addressVo.setId(address.getId().intValue());
								addressVo.setPob(address.getPob());
								addressVo.setPostCode(address.getPostCode());
								addressVo.setStreet(address.getStreet());
								addressVo.setSuitIdentifier(address.getSuiteIdentifier());
								addressList.add(addressVo);
							}
							accountholder.setIndividualAddressList(addressList);
						}		
					
					}//Individual End
					
					//Organisation
					if(accountholder.getAccountHolderType() != null && accountholder.getAccountHolderType().equals("organization")){
						// Organisation Resident Country
						List<Crspayldrescountry> residentCountryList = crspayldrescountryRepository.getAllCrspayldrescountryByObjectID(crspayldacctrep.getId());
						if(residentCountryList != null &&  residentCountryList.size() >0){
							List<ResidentCountryVo> residnetList = new ArrayList<ResidentCountryVo>();
							for(Crspayldrescountry individualRes : residentCountryList){
								ResidentCountryVo residentVo = new ResidentCountryVo();
								residentVo.setId(individualRes.getId().intValue());
								if(individualRes.getResCountryCode() != null){
								residentVo.setResidentCountryCode(Integer.parseInt(individualRes.getResCountryCode()));
								}
								residnetList.add(residentVo);
							}
							accountholder.setOrganisationResidentCountryList(residnetList);
						}
						
						//Organisation IN 
						List<Crspayldin> crsPayldInList = crspayldinRepository.getAllCrspayldinByObjectID(crspayldacctrep.getId());
						if(crsPayldInList != null && crsPayldInList.size() >0){
							List<OrganisationInTypeVo> orgInlist = new ArrayList<OrganisationInTypeVo>();
							for(Crspayldin crspayldin : crsPayldInList){
								OrganisationInTypeVo orgIntyleList = new OrganisationInTypeVo();
								orgIntyleList.setId(crspayldin.getId().intValue());
								orgIntyleList.setIn(crspayldin.getTin());
								orgIntyleList.setInType(crspayldin.getINType());
								if(crspayldin.getIssuedBy() != null){
								orgIntyleList.setIssuedBy(Integer.parseInt(crspayldin.getIssuedBy()));
								}
								orgInlist.add(orgIntyleList);
							}
							accountholder.setOrgOrganisationInTypeList(orgInlist);
						}
						
						//Org Organisation
						List<Crspayldname> payldName= crspayldnameRepository.getAllCrspayldfiByObjectID(crspayldacctrep.getId());
						List<NameVo> nameList = new ArrayList<NameVo>();
						if(payldName != null && payldName.size()>0){
							
							for(Crspayldname name:payldName){
								NameVo nameVo = new NameVo();
								nameVo.setFirstName(name.getNameOrganisation());
								if(name.getNamePersonType() != null){
								nameVo.setNameType(Integer.parseInt(name.getNamePersonType()));
								}
								nameVo.setId(name.getId().intValue());
								nameList.add(nameVo);
							}
							accountholder.setOrganisationList(nameList);
						}
						//Address Grid
						List<AddressVo> addressList= new ArrayList<AddressVo>();
						List<Crspayldaddress> crsReposrtingFiaddress = crspayldaddressRepository.getAllCrspayldaddressByObjectID(crspayldacctrep.getId());
						if(crsReposrtingFiaddress != null && crsReposrtingFiaddress.size() >0){
							for(Crspayldaddress address : crsReposrtingFiaddress){
								AddressVo addressVo = new AddressVo();
								addressVo.setAddressFree(address.getAddressFree());
								addressVo.setAddressType(address.getLegalAddressType());
								addressVo.setAddressTypeId(address.getLegalAddressType());
								addressVo.setBuildingIdentifier(address.getBuildingIdentifier());
								addressVo.setCity(address.getCity());
								addressVo.setCountryCode(address.getCountryCode());
								addressVo.setCountryCodeId(address.getCountryCode());
								addressVo.setCountrySubentity(address.getCountrySubentity());
								addressVo.setDistrictName(address.getDistrictName());
								addressVo.setFloorIdentifier(address.getFloorIdentifier());
								addressVo.setId(address.getId().intValue());
								addressVo.setPob(address.getPob());
								addressVo.setPostCode(address.getPostCode());
								addressVo.setStreet(address.getStreet());
								addressVo.setSuitIdentifier(address.getSuiteIdentifier());
								addressList.add(addressVo);
							}
							accountholder.setOrganisationAddressList(addressList);
						}			
						
					}//Organisation End
					
	
					//hidefvo.setAccountholder(accountholder);
					
					accountHolderList.add(accountholder);
					
					hidefvo.setAccountHolderList(accountHolderList);
					
				}//Account Holder If End
			
				
			}	//CRS Body
			
		}//PayLoad Hdr If End
		return hidefvo;
	}
	
	public String converToString(int i){
		String convertedNum = "";
	    if(String.valueOf(i).length() == 1){
	    	convertedNum ="000"+i;
	    } else if (String.valueOf(i).length() == 2){
	    	convertedNum ="00"+i;
	    }
	    else if (String.valueOf(i).length() == 3){
	    	convertedNum ="0"+i;
	    } 
	    
	    else {
	    	convertedNum =String.valueOf(i);
	    }

	    return convertedNum;
	}
	
	
	public HidefVo getSenderFileID(HidefVo hidef,String communicationType){
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		Senderfileid senderFileId = ctccommonDropdownService.findSenderFileIdByDate(date);
		String senderFileIdNew = "";
		String senderFileIdStatic = communicationType+"_MY"+hidef.getMetadata().getTaxYear()+"-"+hidef.getMycbcId()+date;
		if(senderFileId != null){
			
				int sum = Integer.parseInt(senderFileId.getSenderfileid())+ 1;				
				senderFileIdNew = senderFileIdStatic+String.valueOf(converToString(sum));	
				
			
			
		}else{
			
			senderFileId = saveSenderFileId(date, communicationType);
			senderFileIdNew = senderFileIdStatic+String.valueOf(senderFileId.getSenderfileid());
		}
		
		/*if(hidef.getMetadata() != null && hidef.getMetadata().getSenderFileId() != null && !hidef.getMetadata().getSenderFileId().isEmpty()) {
			hidef.getMetadata().setSenderFileId(hidef.getMetadata().getSenderFileId());
		}else {*/
		hidef.getMetadata().setSenderFileId(senderFileIdNew);
		/*}*/
		return hidef;
	}
	
	public HidefVo getMessageRefId(HidefVo hidef,String communicationType){
		/*String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		Messagerefid messageRefId = ctccommonDropdownService.findMessageRefIdByDate(date);
		String messageRefIdNew = "";
		String messageRefIdStatic = communicationType+"_MY"+hidef.getMetadata().getTaxYear()+"-"+hidef.getMycbcId()+date;
		if(messageRefId != null){
			
				int sum = Integer.parseInt(messageRefId.getMessagerefid())+ 1;				
				messageRefIdNew = messageRefIdStatic+String.valueOf(converToString(sum));	
				
						
		}else{
			
			messageRefId = ctcDataSaveService.saveMessageRefId(date,communicationType);
			messageRefIdNew = messageRefIdStatic+String.valueOf(messageRefId.getMessagerefid());
		}*/
		
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		Senderfileid senderFileId = ctccommonDropdownService.findSenderFileIdByDate(date);
		String messageRefId = "";
		String messageRefIdStatic = communicationType+"_MY"+hidef.getMetadata().getTaxYear()+"-"+hidef.getMycbcId()+date;
		if(senderFileId != null){
			
				int sum = Integer.parseInt(senderFileId.getSenderfileid())+ 1;				
				messageRefId = messageRefIdStatic+String.valueOf(converToString(sum+1));	
				
			
			
		}else{
			
			senderFileId = saveSenderFileId(date, communicationType);
			messageRefId = messageRefIdStatic+String.valueOf(senderFileId.getSenderfileid());
		}
		
		/*if(hidef.getMetadata() != null && hidef.getMetadata().getMessageRefId() != null && !hidef.getMetadata().getMessageRefId().isEmpty()) {
			hidef.getMetadata().setMessageRefId(hidef.getMetadata().getMessageRefId());
		}else {*/
		hidef.getMetadata().setMessageRefId(messageRefId);
		/*}*/
		return hidef;
	}
	public HidefVo getDocRefId(HidefVo hidef,String Type,String communicationType){
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		Docrefid docrefid = ctccommonDropdownService.findDocRefIdByDate(date);
		String docRefId = "";
		//String docRefIdStatic = "MY"+hidef.getMycbcId()+date;
		String docRefIdStatic = "MY"+hidef.getMetadata().getTaxYear()+"-"+hidef.getMycbcId()+date;
		if(docrefid != null){
			if(docrefid.getDocrefid().equals("0001")){
				docRefId = docRefIdStatic+"E"+String.valueOf(docrefid.getDocrefid());	
			hidef.setDocRefId(String.valueOf(docrefid.getDocrefid()));
			hidef.setDocRefIdStaticText(docRefIdStatic);
			}else{
				int sum = Integer.parseInt(docrefid.getDocrefid())+ 1;
				
				docRefId = docRefIdStatic+"E"+String.valueOf(converToString(sum));
				hidef.setDocRefId(String.valueOf(converToString(sum)));
				hidef.setDocRefIdStaticText(docRefIdStatic);
			
			}
			
		}else{
			
			docrefid =saveDocRefIdDetails(date,communicationType);
			docRefId = docRefIdStatic+"E"+String.valueOf(docrefid.getDocrefid());
			hidef.setDocRefId(String.valueOf(docrefid.getDocrefid()));
			hidef.setDocRefIdStaticText(docRefIdStatic);
		}
		
		if(hidef.getReportingEntity().getDocumentReferenceId() != null && !hidef.getReportingEntity().getDocumentReferenceId().isEmpty()) {
			hidef.getReportingEntity().setDocumentReferenceId(hidef.getReportingEntity().getDocumentReferenceId());
		}else {
		hidef.getReportingEntity().setDocumentReferenceId(docRefId);
		}
		return hidef;
	}

	@Override
	public HidefVo saveCrsExcelFile(HidefVo hidef)
			throws IllegalStateException, IOException, ParseException {
		HidefVo hidefVo = new HidefVo();
		String excelWorkPath = fetchProperties("excelWorkPath");
		File file = new File(excelWorkPath + "/" + hidef.getImportExcelFileName());
		hidef.getImportExcelFile().transferTo(file);
		FileInputStream excelFile = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(excelFile);
		
		
		// Metadata - data pick up code
				Sheet datatypeSheet = workbook.getSheetAt(0);
				Iterator<Row> iterator = datatypeSheet.iterator();
				hidefVo.setCrsmetadata(new CrsMetadataVo());
				hidefVo.setUserprofile(new UserProfileVo());
				while (iterator.hasNext()) {

					Row currentRow = iterator.next();
					if (currentRow.getRowNum() >= 2) {
						Iterator<Cell> cellIterator = currentRow.iterator();

						while (cellIterator.hasNext()) {
							Cell currentCell = cellIterator.next();
							if (currentCell.getColumnIndex() == 1) {
								if (currentRow.getRowNum() == 2) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata()
												.setSendingCountry("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setSendingCountry(currentCell.getStringCellValue());
									}
								} else if (currentRow.getRowNum() == 3) {
									List<RecievingCountryVo> receivingCountryList = new ArrayList<RecievingCountryVo>();
									RecievingCountryVo coutryVo = new RecievingCountryVo();
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										coutryVo.setId(Math.toIntExact(Math.round(currentCell.getNumericCellValue())));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										coutryVo.setId(Integer.parseInt(currentCell.getStringCellValue()));
									}
									receivingCountryList.add(coutryVo);
									hidefVo.getCrsmetadata().setReceivingCountry(""+coutryVo.getId());
									hidefVo.getCrsmetadata().setRecievingCountryList(receivingCountryList);
								} else if (currentRow.getRowNum() == 4) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata()
												.setCommunicationType("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setCommunicationType(currentCell.getStringCellValue());
									}
								} else if (currentRow.getRowNum() == 5) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata()
												.setSenderFileId("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setSenderFileId(currentCell.getStringCellValue());
									}
								} else if (currentRow.getRowNum() == 6) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata()
												.setFileFormatCode("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setFileFormatCode(currentCell.getStringCellValue());
									}
								} else if (currentRow.getRowNum() == 7) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata()
												.setBinaryEncoding("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setBinaryEncoding(currentCell.getStringCellValue());
									}
								} else if (currentRow.getRowNum() == 8) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata()
												.setFileCreationTimestramp("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setFileCreationTimestramp(currentCell.getStringCellValue());
									}
								} else if (currentRow.getRowNum() == 9) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata().setTaxYear("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setTaxYear(currentCell.getStringCellValue());
									}
								} else if (currentRow.getRowNum() == 10) {
									String fileTypeIndic = "";
									if (Math.round(currentCell.getNumericCellValue()) == 1L) {
										fileTypeIndic = "true";
									} else {
										fileTypeIndic = "false";
									}
									hidefVo.getUserprofile().setFileTypeIndic(fileTypeIndic);
								} else if (currentRow.getRowNum() == 11) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getUserprofile()
												.setCtsTransId("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getUserprofile().setCtsTransId(currentCell.getStringCellValue());
									}
								} else if (currentRow.getRowNum() == 12) {
									hidefVo.getCrsmetadata().setSenderContactEmail(currentCell.getStringCellValue());
								} else if (currentRow.getRowNum() == 13) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata().setMessageType("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setMessageType(currentCell.getStringCellValue());
									}
								} else if (currentRow.getRowNum() == 14) {
									hidefVo.getCrsmetadata().setWarning(currentCell.getStringCellValue());
								} else if (currentRow.getRowNum() == 15) {
									hidefVo.getCrsmetadata().setContact(currentCell.getStringCellValue());
								} else if (currentRow.getRowNum() == 16) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata()
												.setReportingPeriod("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setReportingPeriod(currentCell.getStringCellValue());
									}
								} else if (currentRow.getRowNum() == 17) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata()
												.setSendingCompanyIn("" + Math.round(currentCell.getNumericCellValue()));
										hidefVo.setMycbcId("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setSendingCompanyIn(currentCell.getStringCellValue());
										hidefVo.setMycbcId(currentCell.getStringCellValue());
									}
								} 
								/*else if (currentRow.getRowNum() == 18) {
									hidefVo.getCrsmetadata().setLanguage(currentCell.getStringCellValue());
								} */
								else if (currentRow.getRowNum() == 18) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata()
												.setMessageTypeIndic("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setMessageTypeIndic(currentCell.getStringCellValue());
									}
								} else if (currentRow.getRowNum() == 19) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsmetadata()
												.setMessageReferenceId("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsmetadata().setMessageReferenceId(currentCell.getStringCellValue());
									}
								}

							}
						}
					}
				}
				
				
				//reporting FI Sheet - data pick up code
				Sheet reportingFISheet = workbook.getSheetAt(1);
				Iterator<Row> iteratorReportingFI = reportingFISheet.iterator();
				hidefVo.setCrsreportingfi(new CrsReportingFiVo());
				int docTypeIndic = 0;
				int docReferenceId = 0;
				int corMessageRefId = 0;
				int corDocumentRefId = 0;
				while (iteratorReportingFI.hasNext()) {
					Row currentRow = iteratorReportingFI.next();
					if (currentRow.getRowNum() >= 2) {
						NameVo nameVo = null;
						ResidentCountryVo residentVo = null;
						OrganisationInTypeVo orgINVo = null;
						AddressVo addressVo = null;
						Iterator<Cell> cellIterator = currentRow.iterator();

						while (cellIterator.hasNext()) {
							Cell currentCell = cellIterator.next();
							if (currentCell.getColumnIndex() == 0) {
								if (docTypeIndic == 0) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsreportingfi()
												.setDocumentTypeIndic("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsreportingfi().setDocumentTypeIndic(currentCell.getStringCellValue());
									}
									docTypeIndic += 1;
								}
							}
							if (currentCell.getColumnIndex() == 1) {
								if (docReferenceId == 0) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsreportingfi()
												.setDocRefId("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsreportingfi().setDocRefId(currentCell.getStringCellValue());
									}
									docReferenceId += 1;
								}
							}
							if (currentCell.getColumnIndex() == 2) {
								if (corMessageRefId == 0) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsreportingfi()
												.setCorMmsgRefId("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsreportingfi().setCorMmsgRefId(currentCell.getStringCellValue());
									}
									corMessageRefId += 1;
								}
							}
							if (currentCell.getColumnIndex() == 3) {
								if (corDocumentRefId == 0) {
									if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
										hidefVo.getCrsreportingfi()
												.setCorDocRefId("" + Math.round(currentCell.getNumericCellValue()));
									} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
										hidefVo.getCrsreportingfi().setCorDocRefId(currentCell.getStringCellValue());
									}
									corDocumentRefId += 1;
								}
							}if (currentCell.getColumnIndex() == 4) {
								if(residentVo == null){
									residentVo = new ResidentCountryVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									int residentId = Math.toIntExact(Math.round(currentCell.getNumericCellValue()));
									residentVo.setId(residentId);
									residentVo.setResidentCountryCode(residentId);
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									int residentId = Integer.parseInt(currentCell.getStringCellValue());
									residentVo.setId(residentId);
									residentVo.setResidentCountryCode(residentId);
								}
							}
							if (currentCell.getColumnIndex() == 5) {
								if(nameVo == null){
									nameVo = new NameVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									nameVo.setFirstName(currentCell.getStringCellValue());							
								}
								
							}
							if (currentCell.getColumnIndex() == 6) {
								if(nameVo == null){
									nameVo = new NameVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									nameVo.setNameType(Integer.parseInt(currentCell.getStringCellValue()));							
								}else if(currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									nameVo.setNameType( Math.toIntExact(Math.round(currentCell.getNumericCellValue())));	
								}
							}
							if (currentCell.getColumnIndex() == 7) {
								if(orgINVo == null){
									orgINVo = new OrganisationInTypeVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									orgINVo.setIn(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									orgINVo.setIn("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 8) {
								if(orgINVo == null){
									orgINVo = new OrganisationInTypeVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									orgINVo.setInType(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									orgINVo.setInType("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 9) {
								if(orgINVo == null){
									orgINVo = new OrganisationInTypeVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									orgINVo.setIssuedBy(Integer.parseInt(currentCell.getStringCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									orgINVo.setIssuedBy(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
								}
							}
							
							if (currentCell.getColumnIndex() == 10) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setCountryCode(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 11) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setAddressType(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 12) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setAddressFree(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 13) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setStreet(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 14) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setBuildingIdentifier(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 15) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setSuitIdentifier(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 16) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setFloorIdentifier(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 17) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setDistrictName(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 18) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setPob(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 19) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setPostCode(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 20) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setCity(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							if (currentCell.getColumnIndex() == 21) {
								if(addressVo == null){
									addressVo = new AddressVo();
								}
								if (currentCell.getCellTypeEnum() == CellType.STRING) {
									addressVo.setCountrySubentity(currentCell.getStringCellValue());
								} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									addressVo.setCountrySubentity("" + Math.round(currentCell.getNumericCellValue()));
								}
							}
							
						}
						if(nameVo != null){
						if (hidefVo.getCrsreportingfi().getNameList() != null
								&& !hidefVo.getCrsreportingfi().getNameList().isEmpty()) {
							hidefVo.getCrsreportingfi().getNameList().add(nameVo);
						} else {
							hidefVo.getCrsreportingfi().setNameList(new ArrayList<NameVo>());
							hidefVo.getCrsreportingfi().getNameList().add(nameVo);
						}
						}
						if(residentVo != null){
						if (hidefVo.getCrsreportingfi().getResidentCountryList() != null
								&& !hidefVo.getCrsreportingfi().getResidentCountryList().isEmpty()) {
							hidefVo.getCrsreportingfi().getResidentCountryList().add(residentVo);
						} else {
							hidefVo.getCrsreportingfi().setResidentCountryList(new ArrayList<ResidentCountryVo>());
							hidefVo.getCrsreportingfi().getResidentCountryList().add(residentVo);
						}
						}
						
						if(orgINVo != null){
						if (hidefVo.getCrsreportingfi().getOrganisationInTypeList() != null
								&& !hidefVo.getCrsreportingfi().getOrganisationInTypeList().isEmpty()) {
							hidefVo.getCrsreportingfi().getOrganisationInTypeList().add(orgINVo);
						} else {
							hidefVo.getCrsreportingfi().setOrganisationInTypeList(new ArrayList<OrganisationInTypeVo>());
							hidefVo.getCrsreportingfi().getOrganisationInTypeList().add(orgINVo);
						}
						}
						
						if(addressVo != null){
						if (hidefVo.getCrsreportingfi().getAddressList() != null
								&& !hidefVo.getCrsreportingfi().getAddressList().isEmpty()) {
							hidefVo.getCrsreportingfi().getAddressList().add(addressVo);
						} else {
							hidefVo.getCrsreportingfi().setAddressList(new ArrayList<AddressVo>());
							hidefVo.getCrsreportingfi().getAddressList().add(addressVo);
						}
						}
					
					}
				}
				
				
				
				
				//reporting FI Sheet - data pick up code
						Sheet accountHolderSheet = workbook.getSheetAt(2);
						Iterator<Row> iteratorAccountHolder = accountHolderSheet.iterator();
						hidefVo.setAccountholder(new AccountHolderVo());
						hidefVo.getAccountholder().setControllingPersonVo(new ControllingPersonVo());
						int acdocTypeIndic = 0;
						int acdocReferenceId = 0;
						int accorMessageRefId = 0;
						int accorDocumentRefId = 0;
						
						while (iteratorAccountHolder.hasNext()) {
							Row currentRow = iteratorAccountHolder.next();
							if (currentRow.getRowNum() >= 2) {
								PaymentTypeVo paymentType = null;
								ResidentCountryVo ctrlResidentVo = null;
								OrganisationInTypeVo ctrlInIntypeVo = null;
								NameTypeVo ctrlNameTypeVo = null;
								AddressVo ctrlAddressVo = null;
								TitleVo ctrltitleVo = null;
								MiddleNameVo ctrlmiddlenameVo = null;
								GenerationIdentifierVo ctrlgenerateIdentifilerVo= null;
								SuffixVo ctrlsuffixVo = null;
								
								
								ResidentCountryVo individualResidentVo = null;;
								OrganisationInTypeVo individualInIntypeVo = null;
								NameTypeVo individualNameTypeVo = null;
								AddressVo individualAddressVo = null;
								TitleVo indtitleVo = null;
								MiddleNameVo indmiddlenameVo = null;
								GenerationIdentifierVo indgenerateIdentifilerVo= null;
								SuffixVo indsuffixVo = null;
								
								ResidentCountryVo organisationResidentVo = null;
								OrganisationInTypeVo organisationInIntypeVo = null;
								AddressVo organisationAddressVo = null;
								NameVo organisationNameVo = null;
								
								Iterator<Cell> cellIterator = currentRow.iterator();

								while (cellIterator.hasNext()) {
									Cell currentCell = cellIterator.next();
									if (currentCell.getColumnIndex() == 0) {
										if (acdocTypeIndic == 0) {
											if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
												hidefVo.getAccountholder()
														.setDocumentTypeIndic("" + Math.round(currentCell.getNumericCellValue()));
											} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
												hidefVo.getAccountholder().setDocumentTypeIndic(currentCell.getStringCellValue());
											}
											acdocTypeIndic += 1;
										}
									}
									if (currentCell.getColumnIndex() == 1) {
										if (acdocReferenceId == 0) {
											if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
												hidefVo.getAccountholder()
														.setDocumentRefId("" + Math.round(currentCell.getNumericCellValue()));
											} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
												hidefVo.getAccountholder().setDocumentRefId(currentCell.getStringCellValue());
											}
											acdocReferenceId += 1;
										}
									}
									if (currentCell.getColumnIndex() == 2) {
										if (accorMessageRefId == 0) {
											if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
												hidefVo.getAccountholder()
														.setCorMessageRefId("" + Math.round(currentCell.getNumericCellValue()));
											} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
												hidefVo.getAccountholder().setCorMessageRefId(currentCell.getStringCellValue());
											}
											accorMessageRefId += 1;
										}
									}
									if (currentCell.getColumnIndex() == 3) {
										if (accorDocumentRefId == 0) {
											if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
												hidefVo.getAccountholder()
														.setCorMessageDocRefId("" + Math.round(currentCell.getNumericCellValue()));
											} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
												hidefVo.getAccountholder().setCorMessageDocRefId(currentCell.getStringCellValue());
											}
											accorDocumentRefId += 1;
										}
									}if (currentCell.getColumnIndex() == 4) {
										if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											int accountNumber = Math.toIntExact(Math.round(currentCell.getNumericCellValue()));
											hidefVo.getAccountholder().setAccountNumber(""+accountNumber);
										} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().setAccountNumber(String.valueOf(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 5) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											String array[] = currentCell.getStringCellValue().split(",");
											List<String> accountNumberType = new ArrayList<String>();
											for(int i=0;i<array.length;i++){
												accountNumberType.add(array[i]);
											}
											hidefVo.getAccountholder().setAccountNumberType(accountNumberType);							
										}else if(currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											String array[] = currentCell.getStringCellValue().split(",");
											List<String> accountNumberType = new ArrayList<String>();
											for(int i=0;i<=array.length;i++){
												accountNumberType.add(array[i]);
											}
											hidefVo.getAccountholder().setAccountNumberType(accountNumberType);	
										}
									}
									if (currentCell.getColumnIndex() == 6) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().setCurrency((currentCell.getStringCellValue()));							
										}else if(currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().setCurrency("" + Math.round(currentCell.getNumericCellValue()));	
										}
									}
									if (currentCell.getColumnIndex() == 7) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().setAccountBalance(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().setAccountBalance("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 8) {
										if(paymentType == null){
											paymentType = new PaymentTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											paymentType.setPaymentType(Integer.parseInt(currentCell.getStringCellValue()));
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											paymentType.setPaymentType(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
										}
									}
									if (currentCell.getColumnIndex() == 9) {
										if(paymentType == null){
											paymentType = new PaymentTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											paymentType.setCurrency(Integer.parseInt(currentCell.getStringCellValue()));
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											paymentType.setCurrency(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
										}
									}
									
									if (currentCell.getColumnIndex() == 10) {
										if(paymentType == null){
											paymentType = new PaymentTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											paymentType.setAmount(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											paymentType.setAmount("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 11) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().getControllingPersonVo().setControllingPersonType(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().getControllingPersonVo().setControllingPersonType("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 12) {
										if(ctrlResidentVo == null){
											ctrlResidentVo = new ResidentCountryVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlResidentVo.setId(Integer.parseInt(currentCell.getStringCellValue()));
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlResidentVo.setId(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
										}
									}
									if (currentCell.getColumnIndex() == 13) {
										if(ctrlInIntypeVo == null){
											ctrlInIntypeVo = new OrganisationInTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlInIntypeVo.setIn(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlInIntypeVo.setIn(("" + Math.round(currentCell.getNumericCellValue())));
										}
									}
									if (currentCell.getColumnIndex() == 14) {
										if(ctrlInIntypeVo == null){
											ctrlInIntypeVo = new OrganisationInTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlInIntypeVo.setInType((currentCell.getStringCellValue()));
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlInIntypeVo.setInType(("" + Math.round(currentCell.getNumericCellValue())));
										}
									}
									if (currentCell.getColumnIndex() == 15) {
										if(ctrlInIntypeVo == null){
											ctrlInIntypeVo = new OrganisationInTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlInIntypeVo.setIssuedBy(Integer.parseInt((currentCell.getStringCellValue())));
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlInIntypeVo.setIssuedBy(Integer.parseInt(("" + Math.round(currentCell.getNumericCellValue()))));
										}
									}
									if (currentCell.getColumnIndex() == 16) {
										if(ctrlNameTypeVo == null){
											ctrlNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlNameTypeVo.setName(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlNameTypeVo.setName("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 17) {
										if(ctrlNameTypeVo == null){
											ctrlNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlNameTypeVo.setNameType(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlNameTypeVo.setNameType("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 18) {
										if(ctrlNameTypeVo == null){
											ctrlNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlNameTypeVo.setPrecedingTitle(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlNameTypeVo.setPrecedingTitle("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 19) {
										if(ctrlNameTypeVo == null){
											ctrlNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlNameTypeVo.setFirstName(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlNameTypeVo.setFirstName("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 20) {
										if(ctrltitleVo == null){
											ctrltitleVo = new TitleVo();
										}
										if(ctrlNameTypeVo == null){
											ctrlNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrltitleVo.setName(currentCell.getStringCellValue());
											if(ctrlNameTypeVo.getTitleList() != null){
												ctrlNameTypeVo.getTitleList().add(ctrltitleVo);
											}else{
												ctrlNameTypeVo.setTitleList(new ArrayList<TitleVo>());
												ctrlNameTypeVo.getTitleList().add(ctrltitleVo);
											}
											
											
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrltitleVo.setName("" + Math.round(currentCell.getNumericCellValue()));
											if(ctrlNameTypeVo.getTitleList() != null){
												ctrlNameTypeVo.getTitleList().add(ctrltitleVo);
											}else{
												ctrlNameTypeVo.setTitleList(new ArrayList<TitleVo>());
												ctrlNameTypeVo.getTitleList().add(ctrltitleVo);
											}
										}
									
									}
									if (currentCell.getColumnIndex() == 21) {
										if(ctrlmiddlenameVo == null){
											ctrlmiddlenameVo = new MiddleNameVo();
										}
										if(ctrlNameTypeVo == null){
											ctrlNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlmiddlenameVo.setMiddleName(currentCell.getStringCellValue());
											if(ctrlNameTypeVo.getMiddlenameList() != null){
											ctrlNameTypeVo.getMiddlenameList().add(ctrlmiddlenameVo);
											}else{
												ctrlNameTypeVo.setMiddlenameList(new ArrayList<MiddleNameVo>());
												ctrlNameTypeVo.getMiddlenameList().add(ctrlmiddlenameVo);	
											}
											
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlmiddlenameVo.setMiddleName("" + Math.round(currentCell.getNumericCellValue()));
											if(ctrlNameTypeVo.getMiddlenameList() != null){
												ctrlNameTypeVo.getMiddlenameList().add(ctrlmiddlenameVo);
												}else{
													ctrlNameTypeVo.setMiddlenameList(new ArrayList<MiddleNameVo>());
													ctrlNameTypeVo.getMiddlenameList().add(ctrlmiddlenameVo);	
												}
										}
									
									}
									if (currentCell.getColumnIndex() == 22) {
										if(ctrlNameTypeVo == null){
											ctrlNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlNameTypeVo.setNamePrefix(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlNameTypeVo.setNamePrefix("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 23) {
										if(ctrlNameTypeVo == null){
											ctrlNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlNameTypeVo.setLastName(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlNameTypeVo.setLastName("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 24) {
										if(ctrlNameTypeVo == null){
											ctrlNameTypeVo = new NameTypeVo();
										}if(ctrlgenerateIdentifilerVo == null){
											ctrlgenerateIdentifilerVo = new GenerationIdentifierVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlgenerateIdentifilerVo.setGenerateIdentifier(currentCell.getStringCellValue());
											if(ctrlNameTypeVo.getGenerateIdentifilerList() != null){
											ctrlNameTypeVo.getGenerateIdentifilerList().add(ctrlgenerateIdentifilerVo);
											}else{
												ctrlNameTypeVo.setGenerateIdentifilerList(new ArrayList<GenerationIdentifierVo>());
												ctrlNameTypeVo.getGenerateIdentifilerList().add(ctrlgenerateIdentifilerVo);
											}
											
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlgenerateIdentifilerVo.setGenerateIdentifier("" + Math.round(currentCell.getNumericCellValue()));
											if(ctrlNameTypeVo.getGenerateIdentifilerList() != null){
												ctrlNameTypeVo.getGenerateIdentifilerList().add(ctrlgenerateIdentifilerVo);
												}else{
													ctrlNameTypeVo.setGenerateIdentifilerList(new ArrayList<GenerationIdentifierVo>());
													ctrlNameTypeVo.getGenerateIdentifilerList().add(ctrlgenerateIdentifilerVo);
												}
										}
									
									}
									if (currentCell.getColumnIndex() == 25) {
										if(ctrlsuffixVo == null){
											ctrlsuffixVo = new SuffixVo();
										}if(ctrlNameTypeVo == null){
											ctrlNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlsuffixVo.setSuffix(currentCell.getStringCellValue());
											if(ctrlNameTypeVo.getSuffixList() != null){
											ctrlNameTypeVo.getSuffixList().add(ctrlsuffixVo);
											}else{
												ctrlNameTypeVo.setSuffixList(new ArrayList<SuffixVo>());
												ctrlNameTypeVo.getSuffixList().add(ctrlsuffixVo);
											}
											
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlsuffixVo.setSuffix("" + Math.round(currentCell.getNumericCellValue()));
											if(ctrlNameTypeVo.getSuffixList() != null){
												ctrlNameTypeVo.getSuffixList().add(ctrlsuffixVo);
												}else{
													ctrlNameTypeVo.setSuffixList(new ArrayList<SuffixVo>());
													ctrlNameTypeVo.getSuffixList().add(ctrlsuffixVo);
												}
										}
									
									}
									if (currentCell.getColumnIndex() == 26) {
										if(ctrlNameTypeVo == null){
											ctrlNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlNameTypeVo.setGeneralSuffix(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlNameTypeVo.setGeneralSuffix("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									
									//Address Controlling Person
									if (currentCell.getColumnIndex() == 27) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setCountryCode(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 28) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setAddressType(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 29) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setAddressFree(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 30) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setStreet(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 31) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setBuildingIdentifier(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 32) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setSuitIdentifier(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 33) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setFloorIdentifier(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 34) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setDistrictName(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 35) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setPob(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 36) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setPostCode(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 37) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setCity(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 38) {
										if(ctrlAddressVo == null){
											ctrlAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											ctrlAddressVo.setCountrySubentity(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											ctrlAddressVo.setCountrySubentity("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									
									//End Controlling Person Address
									
									if (currentCell.getColumnIndex() == 39) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().getControllingPersonVo().setBirthDate(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().getControllingPersonVo().setBirthDate("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 40) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().getControllingPersonVo().setCity(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().getControllingPersonVo().setCity("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 41) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().getControllingPersonVo().setCitySubEntity(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().getControllingPersonVo().setCitySubEntity("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 42) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().getControllingPersonVo().setCountryCode(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().getControllingPersonVo().setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 43) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().getControllingPersonVo().setCountryName(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().getControllingPersonVo().setCountryName("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 44) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().setAccountHolderType(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().setAccountHolderType("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 45) {
										if(individualResidentVo == null){
											individualResidentVo = new ResidentCountryVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualResidentVo.setId(Integer.parseInt(currentCell.getStringCellValue()));
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualResidentVo.setId(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
										}
									}
									if (currentCell.getColumnIndex() == 46) {
										if(individualInIntypeVo == null){
											individualInIntypeVo = new OrganisationInTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualInIntypeVo.setIn(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualInIntypeVo.setIn("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 47) {
										if(individualInIntypeVo == null){
											individualInIntypeVo = new OrganisationInTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualInIntypeVo.setInType(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualInIntypeVo.setInType("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 48) {
										if(individualInIntypeVo == null){
											individualInIntypeVo = new OrganisationInTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualInIntypeVo.setIssuedBy(Integer.parseInt(currentCell.getStringCellValue()));
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualInIntypeVo.setIssuedBy(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
										}
									}
									
									//Individual Address
									//Address Controlling Person
									if (currentCell.getColumnIndex() == 49) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setCountryCode(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 50) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setAddressType(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 51) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setAddressFree(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 52) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setStreet(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 53) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setBuildingIdentifier(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 54) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setSuitIdentifier(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 55) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setFloorIdentifier(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 56) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setDistrictName(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 57) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setPob(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 58) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setPostCode(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 59) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setCity(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 60) {
										if(individualAddressVo == null){
											individualAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualAddressVo.setCountrySubentity(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualAddressVo.setCountrySubentity("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									
									//Address End
									
									//Individual Name
									if (currentCell.getColumnIndex() == 61) {
										if(individualNameTypeVo == null){
											individualNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualNameTypeVo.setName(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualNameTypeVo.setName("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 62) {
										if(individualNameTypeVo == null){
											individualNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualNameTypeVo.setNameType(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualNameTypeVo.setNameType("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 63) {
										if(individualNameTypeVo == null){
											individualNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualNameTypeVo.setPrecedingTitle(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualNameTypeVo.setPrecedingTitle("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 64) {
										if(individualNameTypeVo == null){
											individualNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualNameTypeVo.setFirstName(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualNameTypeVo.setFirstName("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 65) {
										if(individualNameTypeVo == null){
											individualNameTypeVo = new NameTypeVo();
										}
										if(indtitleVo == null){
											indtitleVo = new TitleVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											indtitleVo.setName(currentCell.getStringCellValue());
											if(individualNameTypeVo.getTitleList() != null){
												individualNameTypeVo.getTitleList().add(indtitleVo);
											}else{
												individualNameTypeVo.setTitleList(new ArrayList<TitleVo>());
												individualNameTypeVo.getTitleList().add(indtitleVo);
											}
											
											
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											indtitleVo.setName("" + Math.round(currentCell.getNumericCellValue()));
											if(individualNameTypeVo.getTitleList() != null){
												individualNameTypeVo.getTitleList().add(indtitleVo);
											}else{
												individualNameTypeVo.setTitleList(new ArrayList<TitleVo>());
												individualNameTypeVo.getTitleList().add(indtitleVo);
											}
										}
									
									}
									if (currentCell.getColumnIndex() == 66) {
										if(individualNameTypeVo == null){
											individualNameTypeVo = new NameTypeVo();
										}
										if(indmiddlenameVo == null){
											indmiddlenameVo = new MiddleNameVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											indmiddlenameVo.setMiddleName(currentCell.getStringCellValue());
											if(individualNameTypeVo.getMiddlenameList() != null){
												individualNameTypeVo.getMiddlenameList().add(indmiddlenameVo);
											}else{
												individualNameTypeVo.setMiddlenameList(new ArrayList<MiddleNameVo>());
												individualNameTypeVo.getMiddlenameList().add(indmiddlenameVo);	
											}
											
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											indmiddlenameVo.setMiddleName("" + Math.round(currentCell.getNumericCellValue()));
											if(individualNameTypeVo.getMiddlenameList() != null){
												ctrlNameTypeVo.getMiddlenameList().add(indmiddlenameVo);
												}else{
													individualNameTypeVo.setMiddlenameList(new ArrayList<MiddleNameVo>());
													individualNameTypeVo.getMiddlenameList().add(indmiddlenameVo);	
												}
										}
									
									}
									if (currentCell.getColumnIndex() == 67) {
										if(individualNameTypeVo == null){
											individualNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualNameTypeVo.setNamePrefix(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualNameTypeVo.setNamePrefix("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 68) {
										if(individualNameTypeVo == null){
											individualNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualNameTypeVo.setLastName(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualNameTypeVo.setLastName("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 69) {
										if(individualNameTypeVo == null){
											individualNameTypeVo = new NameTypeVo();
										}
										if(indgenerateIdentifilerVo == null){
											indgenerateIdentifilerVo = new GenerationIdentifierVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											indgenerateIdentifilerVo.setGenerateIdentifier(currentCell.getStringCellValue());
											if(individualNameTypeVo.getGenerateIdentifilerList() != null){
												individualNameTypeVo.getGenerateIdentifilerList().add(indgenerateIdentifilerVo);
											}else{
												individualNameTypeVo.setGenerateIdentifilerList(new ArrayList<GenerationIdentifierVo>());
												individualNameTypeVo.getGenerateIdentifilerList().add(indgenerateIdentifilerVo);
											}
											
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											indgenerateIdentifilerVo.setGenerateIdentifier("" + Math.round(currentCell.getNumericCellValue()));
											if(individualNameTypeVo.getGenerateIdentifilerList() != null){
												individualNameTypeVo.getGenerateIdentifilerList().add(indgenerateIdentifilerVo);
												}else{
													individualNameTypeVo.setGenerateIdentifilerList(new ArrayList<GenerationIdentifierVo>());
													individualNameTypeVo.getGenerateIdentifilerList().add(indgenerateIdentifilerVo);
												}
										}
									
									}
									if (currentCell.getColumnIndex() == 70) {
										if(individualNameTypeVo == null){
											individualNameTypeVo = new NameTypeVo();
										}
										if(indsuffixVo == null){
											indsuffixVo = new SuffixVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											indsuffixVo.setSuffix(currentCell.getStringCellValue());
											if(individualNameTypeVo.getSuffixList() != null){
												individualNameTypeVo.getSuffixList().add(indsuffixVo);
											}else{
												individualNameTypeVo.setSuffixList(new ArrayList<SuffixVo>());
												individualNameTypeVo.getSuffixList().add(indsuffixVo);
											}
											
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											indsuffixVo.setSuffix("" + Math.round(currentCell.getNumericCellValue()));
											if(individualNameTypeVo.getSuffixList() != null){
												individualNameTypeVo.getSuffixList().add(indsuffixVo);
												}else{
													individualNameTypeVo.setSuffixList(new ArrayList<SuffixVo>());
													individualNameTypeVo.getSuffixList().add(indsuffixVo);
												}
										}
									
									}
									if (currentCell.getColumnIndex() == 71) {
										if(individualNameTypeVo == null){
											individualNameTypeVo = new NameTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											individualNameTypeVo.setGeneralSuffix(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											individualNameTypeVo.setGeneralSuffix("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									
									//End Individual Name
									
									if (currentCell.getColumnIndex() == 72) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().setBirthDate(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().setBirthDate("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 73) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().setCity(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().setCity("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 74) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().setCitySubEntity(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().setCitySubEntity("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 75) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().setCountryCode(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 76) {
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											hidefVo.getAccountholder().setAccountHolderType(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											hidefVo.getAccountholder().setAccountHolderType("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 77) {
										if(organisationResidentVo == null){
											organisationResidentVo = new ResidentCountryVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationResidentVo.setResidentCountryCode(Integer.parseInt(currentCell.getStringCellValue()));
											/*if(hidefVo.getAccountholder().getOrganisationResidentCountryList() != null){
												hidefVo.getAccountholder().getOrganisationResidentCountryList().add(organisationResidentVo);
											}else{
												hidefVo.getAccountholder().setOrganisationResidentCountryList(new ArrayList<ResidentCountryVo>());
												hidefVo.getAccountholder().getOrganisationResidentCountryList().add(organisationResidentVo);
											}*/
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationResidentVo.setResidentCountryCode(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
											/*if(hidefVo.getAccountholder().getOrganisationResidentCountryList() != null){
												hidefVo.getAccountholder().getOrganisationResidentCountryList().add(organisationResidentVo);
											}else{
												hidefVo.getAccountholder().setOrganisationResidentCountryList(new ArrayList<ResidentCountryVo>());
												hidefVo.getAccountholder().getOrganisationResidentCountryList().add(organisationResidentVo);
											}*/
										}
									}
									if (currentCell.getColumnIndex() == 78) {
										if(organisationInIntypeVo == null){
											organisationInIntypeVo = new OrganisationInTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationInIntypeVo.setIn(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationInIntypeVo.setIn("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 79) {
										if(organisationInIntypeVo == null){
											organisationInIntypeVo = new OrganisationInTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationInIntypeVo.setInType(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationInIntypeVo.setInType("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 80) {
										if(organisationInIntypeVo == null){
											organisationInIntypeVo = new OrganisationInTypeVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationInIntypeVo.setIssuedBy(Integer.parseInt(currentCell.getStringCellValue()));
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationInIntypeVo.setIssuedBy(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
										}
									}
									if (currentCell.getColumnIndex() == 81) {
										if(organisationNameVo == null){
											organisationNameVo = new NameVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationNameVo.setFirstName(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationNameVo.setFirstName("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 82) {
										if(organisationNameVo == null){
											organisationNameVo = new NameVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationNameVo.setNameType(Integer.parseInt(currentCell.getStringCellValue()));
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationNameVo.setNameType(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
										}
									}
									
									
									
									//Address Organisation 
									if (currentCell.getColumnIndex() == 83) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setCountryCode(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 84) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setAddressType(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 85) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setAddressFree(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 86) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setStreet(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 87) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setBuildingIdentifier(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 88) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setSuitIdentifier(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 89) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setFloorIdentifier(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 90) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setDistrictName(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 91) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setPob(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 92) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setPostCode(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 93) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setCity(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									if (currentCell.getColumnIndex() == 94) {
										if(organisationAddressVo == null){
											organisationAddressVo = new AddressVo();
										}
										if (currentCell.getCellTypeEnum() == CellType.STRING) {
											organisationAddressVo.setCountrySubentity(currentCell.getStringCellValue());
										} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
											organisationAddressVo.setCountrySubentity("" + Math.round(currentCell.getNumericCellValue()));
										}
									}
									
									//Address End
									
									
								}
								if(paymentType != null){
								if (hidefVo.getAccountholder().getPaymentList() != null
										&& !hidefVo.getAccountholder().getPaymentList().isEmpty()) {
									hidefVo.getAccountholder().getPaymentList().add(paymentType);
								} else {
									hidefVo.getAccountholder().setPaymentList(new ArrayList<PaymentTypeVo>());
									hidefVo.getAccountholder().getPaymentList().add(paymentType);
								}
								}
								if(ctrlResidentVo != null){
								if (hidefVo.getAccountholder() != null && hidefVo.getAccountholder().getControllingPersonVo() != null && hidefVo.getAccountholder().getControllingPersonVo().getControllingResidentCountryList() != null
										&& !hidefVo.getAccountholder().getControllingPersonVo().getControllingResidentCountryList().isEmpty()) {
									hidefVo.getAccountholder().getControllingPersonVo().getControllingResidentCountryList().add(ctrlResidentVo);
								} else {
									hidefVo.getAccountholder().getControllingPersonVo().setControllingResidentCountryList((new ArrayList<ResidentCountryVo>()));
									hidefVo.getAccountholder().getControllingPersonVo().getControllingResidentCountryList().add(ctrlResidentVo);
								}
								}
								if(ctrlInIntypeVo != null){
								if (hidefVo.getAccountholder() != null && hidefVo.getAccountholder().getControllingPersonVo() != null && hidefVo.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList() != null
										&& !hidefVo.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList().isEmpty()) {
									hidefVo.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList().add(ctrlInIntypeVo);
								} else {
									hidefVo.getAccountholder().getControllingPersonVo().setControllingOrganisationInTypeList((new ArrayList<OrganisationInTypeVo>()));
									hidefVo.getAccountholder().getControllingPersonVo().getControllingOrganisationInTypeList().add(ctrlInIntypeVo);
								}
								}
								
								if(ctrlNameTypeVo != null){
								if (hidefVo.getAccountholder() != null && hidefVo.getAccountholder().getControllingPersonVo() != null && hidefVo.getAccountholder().getControllingPersonVo().getNameTypeList() != null
										&& !hidefVo.getAccountholder().getControllingPersonVo().getNameTypeList().isEmpty()) {
									hidefVo.getAccountholder().getControllingPersonVo().getNameTypeList().add(ctrlNameTypeVo);
								} else {
									hidefVo.getAccountholder().getControllingPersonVo().setNameTypeList((new ArrayList<NameTypeVo>()));
									hidefVo.getAccountholder().getControllingPersonVo().getNameTypeList().add(ctrlNameTypeVo);
								}
								}
								
								if(ctrlAddressVo != null){
								if (hidefVo.getAccountholder() != null && hidefVo.getAccountholder().getControllingPersonVo() != null && hidefVo.getAccountholder().getControllingPersonVo().getControllingPersonAddressList() != null
										&& !hidefVo.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().isEmpty()) {
									hidefVo.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().add(ctrlAddressVo);
								} else {
									hidefVo.getAccountholder().getControllingPersonVo().setControllingPersonAddressList((new ArrayList<AddressVo>()));
									hidefVo.getAccountholder().getControllingPersonVo().getControllingPersonAddressList().add(ctrlAddressVo);
								}
								}
								
								
								if(individualResidentVo != null){
								if(hidefVo.getAccountholder() != null && hidefVo.getAccountholder().getIndividualResidentCountryList() != null
										&& !hidefVo.getAccountholder().getIndividualResidentCountryList().isEmpty()){
									hidefVo.getAccountholder().getIndividualResidentCountryList().add(individualResidentVo);
								}else{
									hidefVo.getAccountholder().setIndividualResidentCountryList(new ArrayList<ResidentCountryVo>());
									hidefVo.getAccountholder().getIndividualResidentCountryList().add(individualResidentVo);
								}
								}
								if(individualInIntypeVo != null){
								if(hidefVo.getAccountholder() != null && hidefVo.getAccountholder().getIndividualOrganisationInTypeList() != null
										&& !hidefVo.getAccountholder().getIndividualOrganisationInTypeList().isEmpty()){
									hidefVo.getAccountholder().getIndividualOrganisationInTypeList().add(individualInIntypeVo);
								}else{
									hidefVo.getAccountholder().setIndividualOrganisationInTypeList(new ArrayList<OrganisationInTypeVo>());
									hidefVo.getAccountholder().getIndividualOrganisationInTypeList().add(individualInIntypeVo);
								}
								}
								
								if(individualAddressVo != null){
								if(hidefVo.getAccountholder() != null && hidefVo.getAccountholder().getIndividualAddressList() != null
										&& !hidefVo.getAccountholder().getIndividualAddressList().isEmpty()){
									hidefVo.getAccountholder().getIndividualAddressList().add(individualAddressVo);
								}else{
									hidefVo.getAccountholder().setIndividualAddressList(new ArrayList<AddressVo>());
									hidefVo.getAccountholder().getIndividualAddressList().add(individualAddressVo);
								}
								}
								
								if(individualNameTypeVo != null){
								if(hidefVo.getAccountholder() != null && hidefVo.getAccountholder().getIndividualNameList() != null
										&& !hidefVo.getAccountholder().getIndividualNameList().isEmpty()){
									hidefVo.getAccountholder().getIndividualNameList().add(individualNameTypeVo);
								}else{
									hidefVo.getAccountholder().setIndividualNameList(new ArrayList<NameTypeVo>());
									hidefVo.getAccountholder().getIndividualNameList().add(individualNameTypeVo);
								}
								}
								if(organisationResidentVo != null){
								if(hidefVo.getAccountholder().getOrganisationResidentCountryList() != null){
									hidefVo.getAccountholder().getOrganisationResidentCountryList().add(organisationResidentVo);
								}else{
									hidefVo.getAccountholder().setOrganisationResidentCountryList(new ArrayList<ResidentCountryVo>());
									hidefVo.getAccountholder().getOrganisationResidentCountryList().add(organisationResidentVo);
								}
								}
								if(organisationInIntypeVo != null){
								if(hidefVo.getAccountholder().getOrgOrganisationInTypeList() != null){
									hidefVo.getAccountholder().getOrgOrganisationInTypeList().add(organisationInIntypeVo);
								}else{
									hidefVo.getAccountholder().setOrgOrganisationInTypeList(new ArrayList<OrganisationInTypeVo>());
									hidefVo.getAccountholder().getOrgOrganisationInTypeList().add(organisationInIntypeVo);
								}
								}
								if(organisationNameVo != null){
								if(hidefVo.getAccountholder().getOrganisationList() != null){
									hidefVo.getAccountholder().getOrganisationList().add(organisationNameVo);
								}else{
									hidefVo.getAccountholder().setOrganisationList(new ArrayList<NameVo>());
									hidefVo.getAccountholder().getOrganisationList().add(organisationNameVo);
								}
								}
								if(organisationAddressVo != null){
								if(hidefVo.getAccountholder().getOrganisationAddressList() != null){
									hidefVo.getAccountholder().getOrganisationAddressList().add(organisationAddressVo);
								}else{
									hidefVo.getAccountholder().setOrganisationAddressList(new ArrayList<AddressVo>());
									hidefVo.getAccountholder().getOrganisationAddressList().add(organisationAddressVo);
								}
								}
								
								
							
							}
						}
						if(hidefVo.getAccountholder().getControllingPersonVo() != null){
							if(hidefVo.getAccountholder().getControllingPersonList() != null && !hidefVo.getAccountholder().getControllingPersonList().isEmpty()){
								hidefVo.getAccountholder().getControllingPersonList().add(hidefVo.getAccountholder().getControllingPersonVo());
							}else{
								hidefVo.getAccountholder().setControllingPersonList(new ArrayList<ControllingPersonVo>());
								hidefVo.getAccountholder().getControllingPersonList().add(hidefVo.getAccountholder().getControllingPersonVo());
							}
						}
						
						if(hidefVo.getAccountholder() != null){
							if(hidefVo.getAccountHolderList() != null && !hidefVo.getAccountHolderList().isEmpty()){
								hidefVo.getAccountHolderList().add(hidefVo.getAccountholder());
							}else{
								hidefVo.setAccountHolderList(new ArrayList<AccountHolderVo>());
								hidefVo.getAccountHolderList().add(hidefVo.getAccountholder());
							}
						}
				
			
		System.out.println("Metadata Object =====> " + hidefVo);
		return hidefVo;
	}

	@Override
	public HidefVo saveCrsCtcExcelData(HidefVo hidefVo) {
logger.info("<<<<<<<<<<<<<<CRS Saving part begin>>>>>>>>>>>>>>>>>");
		
		if(hidefVo != null && hidefVo.getCrsmetadata() != null){
		logger.info("<<<<<<<<<<<Metadata[Crspayldhdr] begin saving>>>>>>>>>>>>>>>");
		CrsMetadataVo metadata = hidefVo.getCrsmetadata();
		Crspayldhdr crspayld = new Crspayldhdr();
		if(!StringUtils.isEmpty(metadata.getBinaryEncoding())){
		crspayld.setBinaryEncodingSchemeCd(metadata.getBinaryEncoding());
		}
		if(!StringUtils.isEmpty(metadata.getCommunicationType())){
		crspayld.setCommunicationTypeCd("CRS");
		}
		if(!StringUtils.isEmpty(metadata.getContact())){
		crspayld.setContact(metadata.getContact());
		}
		crspayld.setCreateDateTime(new Date());
		if(!StringUtils.isEmpty(hidefVo.getMycbcId())){
		crspayld.setCrsid(new BigInteger(hidefVo.getMycbcId()));
		}
		if(!StringUtils.isEmpty(metadata.getFileCreationTimestramp())){
		crspayld.setFileCreateTs(metadata.getFileCreationTimestramp());
		}
		if(!StringUtils.isEmpty(metadata.getFileFormatCode())){
		crspayld.setFileFormatCD(metadata.getFileFormatCode());
		}
		if(!StringUtils.isEmpty(metadata.getMessageTypeIndic())){
		crspayld.setFileRevisionInd(Integer.parseInt(metadata.getMessageTypeIndic()));
		}
		if(!StringUtils.isEmpty(metadata.getMessageReferenceId())){
		crspayld.setMessageRefId(metadata.getMessageReferenceId());
		}
		if(!StringUtils.isEmpty(metadata.getMessageType())){
		crspayld.setMessageType("CRS");
		}
		
		crspayld.setMsgTimestamp(new Date());
		if(!StringUtils.isEmpty(metadata.getReceivingCountry())){
		crspayld.setReceiverCountryCd(findCountryCodeConvertStringToBigInt(metadata.getReceivingCountry()));
		}
		if(!StringUtils.isEmpty(metadata.getReportingPeriod())){
		crspayld.setReportingPeriod(metadata.getReportingPeriod());
		}
		if(!StringUtils.isEmpty(metadata.getSenderContactEmail())){
		crspayld.setSenderContactEmailAddressTxt(metadata.getSenderContactEmail());
		}
		if(!StringUtils.isEmpty(metadata.getSendingCountry())){
		crspayld.setSenderCountryCd(findCountryCodeConvertStringToBigInt(metadata.getSendingCountry()));
		}
		if(!StringUtils.isEmpty(metadata.getSenderFileId())){
		crspayld.setSenderFileId(metadata.getSenderFileId());
		}
		if(!StringUtils.isEmpty(hidefVo.getMycbcId())){
		crspayld.setSendingCompanyIN(hidefVo.getMycbcId());
		}
		if(!StringUtils.isEmpty(metadata.getTaxYear())){
		crspayld.setTaxYear(Integer.parseInt(metadata.getTaxYear()));
		}
		if(!StringUtils.isEmpty(metadata.getWarning())){
		crspayld.setWarning(metadata.getWarning());
		}
		if(!StringUtils.isEmpty(metadata.getMessageTypeIndic())){
			crspayld.setMessageTypeIndic(metadata.getMessageTypeIndic());
			}
		crspayld = crspayldhdrRepository.saveAndFlush(crspayld);
		logger.info("<<<<<<<<<<<Metadata[Crspayldhdr] End saving>>>>>>>>>>>>>>>"+crspayld.getId());
		
		
		if(crspayld != null){
			logger.info("<<<<<<<<<<<Body[Crspayldbody] Begin saving>>>>>>>>>>>>>>>");
			Crspayldbody crsbody = new Crspayldbody();
			crsbody.setHdrID(crspayld.getId());
			crsbody.setCreateDateTime(new Date());
			crsbody = crspayldbodyRepository.saveAndFlush(crsbody);
			logger.info("<<<<<<<<<<<Body[Crspayldbody] End saving>>>>>>>>>>>>>>>"+crsbody.getId());
			
			if(crsbody != null && crsbody != null && crspayld != null){
				logger.info("<<<<<<<<<<<<Reposting FI Saving part begin here>>>>>>>>>>>>>>>");
				if(hidefVo.getCrsreportingfi() != null){
				logger.info("<<<<<<<<<<<ReportingFI[Crspayldfi] Begin saving>>>>>>>>>>>>>>>");
				CrsReportingFiVo reportingFi = hidefVo.getCrsreportingfi();
				Crspayldfi crspayldFi = new Crspayldfi();
				crspayldFi.setBodyID(crsbody.getId());
				crspayldFi.setCorrDocRefId(reportingFi.getCorDocRefId());
				crspayldFi.setCreateDateTime(new Date());
				crspayldFi.setDocRefId(reportingFi.getDocRefId());
				crspayldFi.setDocTypeIndic(reportingFi.getDocumentTypeIndic());
				crspayldFi.setHdrID(crspayld.getId());
				crspayldFi = crspayldfiRepository.saveAndFlush(crspayldFi);
				logger.info("<<<<<<<<<<<ReportingFI[Crspayldfi] End saving>>>>>>>>>>>>>>>"+crspayldFi.getId());
				
				//Organisation Grid
				if(reportingFi.getNameList() != null && reportingFi.getNameList().size() > 0){
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldname] Begin saving>>>>>>>>>>>>>>>");	
					for(NameVo nameVo : reportingFi.getNameList()){
						Crspayldname crspayldName = new Crspayldname();
						crspayldName.setNameOrganisation(nameVo.getFirstName());
						crspayldName.setNamePersonType(String.valueOf(nameVo.getNameType()));
						crspayldName.setObjectID(crspayldFi.getId());
						crspayldName.setSrcType("0");
						crspayldName = crspayldnameRepository.saveAndFlush(crspayldName);
						
					}
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldname] End saving>>>>>>>>>>>>>>>");
				}
				//Reporting FI IN
				if(reportingFi.getOrganisationInTypeList() != null && reportingFi.getOrganisationInTypeList().size() > 0){
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldin] Begin saving>>>>>>>>>>>>>>>");
					for(OrganisationInTypeVo organisation : reportingFi.getOrganisationInTypeList()){
						Crspayldin crsPayldin = new Crspayldin();
						if(!StringUtils.isEmpty(organisation.getIn())){
						crsPayldin.setTin(organisation.getIn());
						}
						if(!StringUtils.isEmpty(organisation.getInType())){
						crsPayldin.setINType(organisation.getInType());
						}
						if(organisation.getIssuedBy() >0){
						crsPayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));
						}
						crsPayldin.setObjectID(crspayldFi.getId());
						crsPayldin.setSrcType("0");
						crsPayldin = crspayldinRepository.saveAndFlush(crsPayldin);
					}
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldin] End saving>>>>>>>>>>>>>>>");
					
				}
				//Resident Country
				if(reportingFi.getResidentCountryList() != null && reportingFi.getResidentCountryList().size() >0){
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldrescountry] Begin saving>>>>>>>>>>>>>>>");
					for(ResidentCountryVo residentCountry : reportingFi.getResidentCountryList()){
						Crspayldrescountry crspayldrescountry = new Crspayldrescountry();
						if(residentCountry.getResidentCountryCode() > 0){
						crspayldrescountry.setResCountryCode(String.valueOf(residentCountry.getResidentCountryCode()));
						}
						crspayldrescountry.setObjectID(crspayldFi.getId());
						crspayldrescountry.setSrcType("0");
						crspayldrescountry = crspayldrescountryRepository.saveAndFlush(crspayldrescountry);
					}
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldrescountry] End saving>>>>>>>>>>>>>>>");
					
				}	
				
				//Address
				if(reportingFi.getAddressList() != null && reportingFi.getAddressList().size()>0){
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldaddress] Begin saving>>>>>>>>>>>>>>>");
					for(AddressVo addressVo :reportingFi.getAddressList() ){
						Crspayldaddress address = new Crspayldaddress();
						if(!StringUtils.isEmpty(addressVo.getAddressFree())){
						address.setAddressFree(addressVo.getAddressFree());
						}
						if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
						address.setBuildingIdentifier(addressVo.getBuildingIdentifier());
						}
						if(!StringUtils.isEmpty(addressVo.getCity())){
						address.setCity(addressVo.getCity());
						}
						if(!StringUtils.isEmpty(addressVo.getCountryCode())){
						address.setCountryCode(findCountryCodeConvertStringToBigInt(addressVo.getCountryCode()));
						}
						if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
						address.setCountrySubentity(addressVo.getCountrySubentity());
						}
						address.setCreateDateTime(new Date());
						if(!StringUtils.isEmpty(addressVo.getDistrictName())){
						address.setDistrictName(addressVo.getDistrictName());
						}
						if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
						address.setFloorIdentifier(addressVo.getFloorIdentifier());
						}
						if(!StringUtils.isEmpty(addressVo.getAddressType())){
						address.setLegalAddressType(addressVo.getAddressType());
						}
						address.setObjectID(crspayldFi.getId());
						if(!StringUtils.isEmpty(addressVo.getPob())){
						address.setPob(addressVo.getPob());
						}
						if(!StringUtils.isEmpty(addressVo.getPostCode())){
						address.setPostCode(addressVo.getPostCode());
						}
						address.setSrcType("0");
						if(!StringUtils.isEmpty(addressVo.getStreet())){
						address.setStreet(addressVo.getStreet());
						}
						if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
						address.setSuiteIdentifier(addressVo.getSuitIdentifier());
						}
						address = crspayldaddressRepository.saveAndFlush(address);
					}
					logger.info("<<<<<<<<<<<ReportingFI[Crspayldaddress] End saving>>>>>>>>>>>>>>>");
					
				}
				
				}//Reporting FI
				
				
				if(hidefVo.getAccountHolderList() != null && hidefVo.getAccountHolderList().size() >0){
				logger.info("<<<<<<<<<<<AccountHolder[Crspayldacctrep] Begin saving>>>>>>>>>>>>>>>");
				List<AccountHolderVo> accountHolderList = 	hidefVo.getAccountHolderList();
				for(AccountHolderVo accountHolderVo : accountHolderList){
					
					Crspayldacctrep crspayldacct = new Crspayldacctrep();
					if(!StringUtils.isEmpty(accountHolderVo.getAccountBalance())){
					crspayldacct.setAccountBalance(new BigDecimal(accountHolderVo.getAccountBalance()));
					}
					crspayldacct.setAccountCurrCode(accountHolderVo.getCurrency());
					crspayldacct.setAcctHolderType(accountHolderVo.getAccountHolderType());
					/*crspayldacct.setAccountHolder(accountHolderVo.getAccountHolderType());*/
					crspayldacct.setAccountNumber(accountHolderVo.getAccountNumber());
					/*crspayldacct.setAcctNumberType(accountHolderVo.getA);*/
					
					crspayldacct.setBirthCity(accountHolderVo.getCity());
					crspayldacct.setBirthCountry(accountHolderVo.getCountryCode());
					crspayldacct.setBirthDate(accountHolderVo.getBirthDate());
					crspayldacct.setBirthFormerCountry(accountHolderVo.getCountryName());
					crspayldacct.setBirthCitySubent(accountHolderVo.getCitySubEntity());
					crspayldacct.setBodyID(crsbody.getId());
					crspayldacct.setHdrID(crspayld.getId());
					if(accountHolderVo.getAccountNumberType() != null && accountHolderVo.getAccountNumberType().size() > 0){
						for(String s : accountHolderVo.getAccountNumberType()){
							if(s.equals("Closed")){
								crspayldacct.setClosedAccount("Y");
							}else if(s.equals("Dormant")){
								crspayldacct.setDormantAccount("Y");
							}else if(s.equals("Undocumented")){
								crspayldacct.setUndocumentedAccount("Y");
							}
							
						}
					}else{
						crspayldacct.setClosedAccount("N");
						crspayldacct.setDormantAccount("N");
						crspayldacct.setUndocumentedAccount("N");
					}
					crspayldacct.setCorrDocRefId(accountHolderVo.getCorMessageDocRefId());
					crspayldacct.setCreateDateTime(new Date());
					crspayldacct.setDocRefId(accountHolderVo.getDocumentRefId());
					crspayldacct.setDocTypeIndic(accountHolderVo.getDocumentTypeIndic());
					crspayldacct = crspayldacctrepRepository.saveAndFlush(crspayldacct);
					logger.info("<<<<<<<<<<<AccountHolder[Crspayldacctrep] End saving>>>>>>>>>>>>>>>"+crspayldacct.getId());
					
					
					
					//Payment Detail
					if(accountHolderVo.getPaymentList() != null && accountHolderVo.getPaymentList().size() > 0){
						logger.info("<<<<<<<<<<<Payment Detail[Crspayldpymt] Begin saving>>>>>>>>>>>>>>>");
						for(PaymentTypeVo paymentVo : accountHolderVo.getPaymentList()){
							Crspayldpymt crspayment = new Crspayldpymt();
							crspayment.setAcctRepID(crspayldacct.getId());
							if(paymentVo.getCurrency()> 0){
							crspayment.setCurrCode(String.valueOf(paymentVo.getCurrency()));
							}
							if(paymentVo.getAmount() != null){
							crspayment.setPaymentAmt(new BigDecimal(paymentVo.getAmount()));
							}
							if(paymentVo.getPaymentType() > 0){
							crspayment.setPaymentType(String.valueOf(paymentVo.getPaymentType()));
							}
							crspayment = crspayldpymtRepository.saveAndFlush(crspayment);
						}
					}
					
					
					
					
					if(accountHolderVo.getAccountHolderType() != null){
						if(accountHolderVo.getAccountHolderType().equals("individual")){
						logger.info("Individual Section Saving part Begin Here");
						
						//Individual Address
						if(accountHolderVo.getIndividualAddressList() != null && accountHolderVo.getIndividualAddressList().size()  > 0){
						
							logger.info("<<<<<<<<<<<Individual Address[Crspayldaddress] Begin saving>>>>>>>>>>>>>>>");
							for(AddressVo addressVo :accountHolderVo.getIndividualAddressList()){
								Crspayldaddress address = new Crspayldaddress();
								if(!StringUtils.isEmpty(addressVo.getAddressFree())){
								address.setAddressFree(addressVo.getAddressFree());
								}
								if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
								address.setBuildingIdentifier(addressVo.getBuildingIdentifier());
								}
								if(!StringUtils.isEmpty(addressVo.getCity())){
								address.setCity(addressVo.getCity());
								}
								if(!StringUtils.isEmpty(addressVo.getCountryCode())){
								address.setCountryCode(addressVo.getCountryCode());
								}
								if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
								address.setCountrySubentity(addressVo.getCountrySubentity());
								}
								address.setCreateDateTime(new Date());
								if(!StringUtils.isEmpty(addressVo.getDistrictName())){
								address.setDistrictName(addressVo.getDistrictName());
								}
								if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
								address.setFloorIdentifier(addressVo.getFloorIdentifier());
								}
								if(!StringUtils.isEmpty(addressVo.getAddressType())){
								address.setLegalAddressType(addressVo.getAddressType());
								}
								address.setObjectID(crspayldacct.getId());
								if(!StringUtils.isEmpty(addressVo.getPob())){
								address.setPob(addressVo.getPob());
								}
								if(!StringUtils.isEmpty(addressVo.getPostCode())){
								address.setPostCode(addressVo.getPostCode());
								}
								address.setSrcType("0");
								if(!StringUtils.isEmpty(addressVo.getStreet())){
								address.setStreet(addressVo.getStreet());
								}
								if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
								address.setSuiteIdentifier(addressVo.getSuitIdentifier());
								}
								address = crspayldaddressRepository.saveAndFlush(address);
							}
							logger.info("<<<<<<<<<<<Individual Address[Crspayldaddress] End saving>>>>>>>>>>>>>>>");
							
						
						}
						
						//Individual Resident Country
						if(accountHolderVo.getIndividualResidentCountryList() != null && accountHolderVo.getIndividualResidentCountryList().size() >0){
							logger.info("<<<<<<<<<<<Indidivual Resident Country[Crspayldrescountry] Begin saving>>>>>>>>>>>>>>>");
							for(ResidentCountryVo residentCountry : accountHolderVo.getIndividualResidentCountryList()){
								Crspayldrescountry crspayldrescountry = new Crspayldrescountry();
								if(residentCountry.getResidentCountryCode() > 0){
								crspayldrescountry.setResCountryCode(String.valueOf(residentCountry.getResidentCountryCode()));
								}
								crspayldrescountry.setObjectID(crspayldacct.getId());
								crspayldrescountry.setSrcType("0");
								crspayldrescountry = crspayldrescountryRepository.saveAndFlush(crspayldrescountry);
							}
							logger.info("<<<<<<<<<<<Indidivual Resident Country[Crspayldrescountry] End saving>>>>>>>>>>>>>>>");
							
						}
						
						//Individaul  IN
						if(accountHolderVo.getIndividualOrganisationInTypeList() != null && accountHolderVo.getIndividualOrganisationInTypeList().size() > 0){
							logger.info("<<<<<<<<<<<Individual In type[Crspayldin] Begin saving>>>>>>>>>>>>>>>");
							for(OrganisationInTypeVo organisation : accountHolderVo.getIndividualOrganisationInTypeList()){
								Crspayldin crsPayldin = new Crspayldin();
								if(!StringUtils.isEmpty(organisation.getIn())){
								crsPayldin.setTin(organisation.getIn());
								}
								if(!StringUtils.isEmpty(organisation.getInType())){
								crsPayldin.setINType(organisation.getInType());
								}
								if(organisation.getIssuedBy() >0){
								crsPayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));
								}
								crsPayldin.setObjectID(crspayldacct.getId());
								crsPayldin.setSrcType("0");
								crsPayldin = crspayldinRepository.saveAndFlush(crsPayldin);
							}
							logger.info("<<<<<<<<<<<Individual In typ[Crspayldin] End saving>>>>>>>>>>>>>>>");
							
						}
						
						//Individual NameType Grid
						if(accountHolderVo.getIndividualNameList() != null && accountHolderVo.getIndividualNameList().size() > 0){
							logger.info("<<<<<<<<<<<Individual NameType Grid[Crspayldname] Begin saving>>>>>>>>>>>>>>>");	
							for(NameTypeVo nameVo : accountHolderVo.getIndividualNameList()){
								Crspayldname crspayldName = new Crspayldname();
								crspayldName.setCreateDateTime(new Date());
								crspayldName.setFirstName(nameVo.getFirstName());
								crspayldName.setGeneralSuffix(nameVo.getGeneralSuffix());
								crspayldName.setLastName(nameVo.getLastName());
								crspayldName.setNamePrefix(nameVo.getNamePrefix());
								crspayldName.setPrecedingTitle(nameVo.getPrecedingTitle());
								crspayldName.setObjectID(crspayldacct.getId());
								crspayldName.setSrcType("0");
								crspayldName = crspayldnameRepository.saveAndFlush(crspayldName);
								
								
								if(nameVo.getTitleList() != null &&nameVo.getTitleList().size() >0 ){
									for(TitleVo titleVo : nameVo.getTitleList()){
									Crspayldnametitle title = new Crspayldnametitle();
									title.setTitle(titleVo.getName());
									title.setNameID(crspayldName.getId());
									title.setCreateDateTime(new Date());
									title = crspayldnametitleRepository.saveAndFlush(title);
									}
								}
								
								if(nameVo.getMiddlenameList() != null && nameVo.getMiddlenameList().size() > 0){
									for(MiddleNameVo middileNamevo :nameVo.getMiddlenameList() ){
										Crspayldnamemiddle middilename = new Crspayldnamemiddle();
										middilename.setMiddleName(middileNamevo.getMiddleName());
										middilename.setCreateDateTime(new Date());
										middilename.setNameID(crspayldName.getId());
										middilename = crspayldnamemiddleRepository.saveAndFlush(middilename);
									}
								}
								
								if(nameVo.getGenerateIdentifilerList() != null &&nameVo.getGenerateIdentifilerList().size() >0 ){
									for(GenerationIdentifierVo generateId :nameVo.getGenerateIdentifilerList() ){
										Crspayldnamegeneration generationid = new Crspayldnamegeneration();
										generationid.setGenerationIdentifier(generateId.getGenerateIdentifier());
										generationid.setCreateDateTime(new Date());
										generationid.setNameID(crspayldName.getId());
										generationid = crspayldnamegenerationRepository.saveAndFlush(generationid);
									}
								}
								if(nameVo.getSuffixList() != null && nameVo.getSuffixList().size() >0){
									for(SuffixVo suffix :nameVo.getSuffixList() ){
										Crspayldnamesuffix suffi= new Crspayldnamesuffix();
										suffi.setSuffix(suffix.getSuffix());
										suffi.setNameID(crspayldName.getId());
										suffi.setCreateDateTime(new Date());
										suffi = crspayldnamesuffixRepository.saveAndFlush(suffi);
									}
								}	
								
							}
							logger.info("<<<<<<<<<<<Individual NameType Grid[Crspayldname] End saving>>>>>>>>>>>>>>>");
						}//NameList
						
						
						}
						
							
							
						}//Individual
						
						else if(accountHolderVo.getAccountHolderType().equals("organization")){
							logger.info("Organisation Section Saving part Begin Here");	
							
							if(accountHolderVo.getOrganisationAddressList() != null && accountHolderVo.getOrganisationAddressList().size()  > 0){
								logger.info("<<<<<<<<<<<Organisation Address[Crspayldaddress] Begin saving>>>>>>>>>>>>>>>");
								for(AddressVo addressVo :accountHolderVo.getOrganisationAddressList()){
									Crspayldaddress address = new Crspayldaddress();
									if(!StringUtils.isEmpty(addressVo.getAddressFree())){
									address.setAddressFree(addressVo.getAddressFree());
									}
									if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
									address.setBuildingIdentifier(addressVo.getBuildingIdentifier());
									}
									if(!StringUtils.isEmpty(addressVo.getCity())){
									address.setCity(addressVo.getCity());
									}
									if(!StringUtils.isEmpty(addressVo.getCountryCode())){
									address.setCountryCode(addressVo.getCountryCode());
									}
									if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
									address.setCountrySubentity(addressVo.getCountrySubentity());
									}
									address.setCreateDateTime(new Date());
									if(!StringUtils.isEmpty(addressVo.getDistrictName())){
									address.setDistrictName(addressVo.getDistrictName());
									}
									if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
									address.setFloorIdentifier(addressVo.getFloorIdentifier());
									}
									if(!StringUtils.isEmpty(addressVo.getAddressType())){
									address.setLegalAddressType(addressVo.getAddressType());
									}
									address.setObjectID(crspayldacct.getId());
									if(!StringUtils.isEmpty(addressVo.getPob())){
									address.setPob(addressVo.getPob());
									}
									if(!StringUtils.isEmpty(addressVo.getPostCode())){
									address.setPostCode(addressVo.getPostCode());
									}
									address.setSrcType("0");
									if(!StringUtils.isEmpty(addressVo.getStreet())){
									address.setStreet(addressVo.getStreet());
									}
									if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
									address.setSuiteIdentifier(addressVo.getSuitIdentifier());
									}
									address = crspayldaddressRepository.saveAndFlush(address);
								}
								logger.info("<<<<<<<<<<<Organisation Address[Crspayldaddress] End saving>>>>>>>>>>>>>>>");
								
							
							}
							
							//Organisation Resident Country
							if(accountHolderVo.getOrganisationResidentCountryList() != null && accountHolderVo.getOrganisationResidentCountryList().size() >0){
								logger.info("<<<<<<<<<<<Organisation Resident Country[Crspayldrescountry] Begin saving>>>>>>>>>>>>>>>");
								for(ResidentCountryVo residentCountry : accountHolderVo.getOrganisationResidentCountryList()){
									Crspayldrescountry crspayldrescountry = new Crspayldrescountry();
									if(residentCountry.getResidentCountryCode() > 0){
									crspayldrescountry.setResCountryCode(String.valueOf(residentCountry.getResidentCountryCode()));
									}
									crspayldrescountry.setObjectID(crspayldacct.getId());
									crspayldrescountry.setSrcType("0");
									crspayldrescountry = crspayldrescountryRepository.saveAndFlush(crspayldrescountry);
								}
								logger.info("<<<<<<<<<<<Organisation Resident Country[Crspayldrescountry] End saving>>>>>>>>>>>>>>>");
								
							}
							
							//Organisation  IN
							if(accountHolderVo.getOrgOrganisationInTypeList() != null && accountHolderVo.getOrgOrganisationInTypeList().size() > 0){
								logger.info("<<<<<<<<<<<Organisation In type[Crspayldin] Begin saving>>>>>>>>>>>>>>>");
								for(OrganisationInTypeVo organisation : accountHolderVo.getOrgOrganisationInTypeList()){
									Crspayldin crsPayldin = new Crspayldin();
									if(!StringUtils.isEmpty(organisation.getIn())){
									crsPayldin.setTin(organisation.getIn());
									}
									if(!StringUtils.isEmpty(organisation.getInType())){
									crsPayldin.setINType(organisation.getInType());
									}
									if(organisation.getIssuedBy() >0){
									crsPayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));
									}
									crsPayldin.setObjectID(crspayldacct.getId());
									crsPayldin.setSrcType("0");
									crsPayldin = crspayldinRepository.saveAndFlush(crsPayldin);
								}
								logger.info("<<<<<<<<<<<Organisation In typ[Crspayldin] End saving>>>>>>>>>>>>>>>");
								
							}
							
							//Organisation Grid
							if(accountHolderVo.getOrganisationList() != null && accountHolderVo.getOrganisationList().size() > 0){
								logger.info("<<<<<<<<<<<Organisation[Crspayldname] Begin saving>>>>>>>>>>>>>>>");	
								for(NameVo nameVo : accountHolderVo.getOrganisationList()){
									Crspayldname crspayldName = new Crspayldname();
									crspayldName.setNameOrganisation(nameVo.getFirstName());
									crspayldName.setNamePersonType(String.valueOf(nameVo.getNameType()));
									crspayldName.setObjectID(crspayldacct.getId());
									crspayldName.setSrcType("0");
									crspayldName = crspayldnameRepository.saveAndFlush(crspayldName);
									
								}
								logger.info("<<<<<<<<<<<Organisation[Crspayldname] End saving>>>>>>>>>>>>>>>");
							}
							
							
							
						}//Organisation
					
					
					//Controlling Person
					if(accountHolderVo.getControllingPersonList() != null && accountHolderVo.getControllingPersonList().size() > 0){
						logger.info("<<<<<<<<<<<Controlling Person[Crspayldctrlperson] Begin saving>>>>>>>>>>>>>>>");	
						for(ControllingPersonVo controlingPersonVo : accountHolderVo.getControllingPersonList()){
							Crspayldctrlperson crsctrlperson = new Crspayldctrlperson();
							crsctrlperson.setAcctRepID(crspayldacct.getId());
							crsctrlperson.setBirthCity(controlingPersonVo.getCity());
							crsctrlperson.setBirthCitySubent(controlingPersonVo.getCitySubEntity());
							crsctrlperson.setBirthCountry(controlingPersonVo.getCountryCode());
							crsctrlperson.setBirthFormerCountry(controlingPersonVo.getCountryName());
							crsctrlperson.setCreateDateTime(new Date());
							crsctrlperson.setCtrlgPersonType(controlingPersonVo.getControllingPersonType());
							crsctrlperson = crspayldctrlpersonRepository.saveAndFlush(crsctrlperson);
							logger.info("<<<<<<<<<<<Controlling Person[Crspayldctrlperson] End saving>>>>>>>>>>>>>>>"+crsctrlperson.getId());
							
							
							
							//Controlling Person Address
							if(controlingPersonVo.getControllingPersonAddressList() != null && controlingPersonVo.getControllingPersonAddressList().size()  > 0){
							
								logger.info("<<<<<<<<<<<Controlling Person Address[Crspayldaddress] Begin saving>>>>>>>>>>>>>>>");
								for(AddressVo addressVo :controlingPersonVo.getControllingPersonAddressList()){
									Crspayldaddress address = new Crspayldaddress();
									if(!StringUtils.isEmpty(addressVo.getAddressFree())){
									address.setAddressFree(addressVo.getAddressFree());
									}
									if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
									address.setBuildingIdentifier(addressVo.getBuildingIdentifier());
									}
									if(!StringUtils.isEmpty(addressVo.getCity())){
									address.setCity(addressVo.getCity());
									}
									if(!StringUtils.isEmpty(addressVo.getCountryCode())){
									address.setCountryCode(addressVo.getCountryCode());
									}
									if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
									address.setCountrySubentity(addressVo.getCountrySubentity());
									}
									address.setCreateDateTime(new Date());
									if(!StringUtils.isEmpty(addressVo.getDistrictName())){
									address.setDistrictName(addressVo.getDistrictName());
									}
									if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
									address.setFloorIdentifier(addressVo.getFloorIdentifier());
									}
									if(!StringUtils.isEmpty(addressVo.getAddressType())){
									address.setLegalAddressType(addressVo.getAddressType());
									}
									address.setObjectID(crsctrlperson.getId());
									if(!StringUtils.isEmpty(addressVo.getPob())){
									address.setPob(addressVo.getPob());
									}
									if(!StringUtils.isEmpty(addressVo.getPostCode())){
									address.setPostCode(addressVo.getPostCode());
									}
									address.setSrcType("0");
									if(!StringUtils.isEmpty(addressVo.getStreet())){
									address.setStreet(addressVo.getStreet());
									}
									if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
									address.setSuiteIdentifier(addressVo.getSuitIdentifier());
									}
									address = crspayldaddressRepository.saveAndFlush(address);
								}
								logger.info("<<<<<<<<<<<Controlling Person Address[Crspayldaddress] End saving>>>>>>>>>>>>>>>");
								
							
							}
							
							//Controlling Person Resident Country
							if(controlingPersonVo.getControllingResidentCountryList() != null && controlingPersonVo.getControllingResidentCountryList().size() >0){
								logger.info("<<<<<<<<<<<Controlling Person Resident Country[Crspayldrescountry] Begin saving>>>>>>>>>>>>>>>");
								for(ResidentCountryVo residentCountry : controlingPersonVo.getControllingResidentCountryList()){
									Crspayldrescountry crspayldrescountry = new Crspayldrescountry();
									if(residentCountry.getResidentCountryCode() > 0){
									crspayldrescountry.setResCountryCode(String.valueOf(residentCountry.getResidentCountryCode()));
									}
									crspayldrescountry.setObjectID(crsctrlperson.getId());
									crspayldrescountry.setSrcType("0");
									crspayldrescountry = crspayldrescountryRepository.saveAndFlush(crspayldrescountry);
								}
								logger.info("<<<<<<<<<<<Controlling Person Resident Country[Crspayldrescountry] End saving>>>>>>>>>>>>>>>");
								
							}
							
							//Controlling Person   IN
							if(controlingPersonVo.getControllingOrganisationInTypeList() != null && controlingPersonVo.getControllingOrganisationInTypeList().size() > 0){
								logger.info("<<<<<<<<<<<Controlling Person In type[Crspayldin] Begin saving>>>>>>>>>>>>>>>");
								for(OrganisationInTypeVo organisation : controlingPersonVo.getControllingOrganisationInTypeList()){
									Crspayldin crsPayldin = new Crspayldin();
									if(!StringUtils.isEmpty(organisation.getIn())){
									crsPayldin.setTin(organisation.getIn());
									}
									if(!StringUtils.isEmpty(organisation.getInType())){
									crsPayldin.setINType(organisation.getInType());
									}
									if(organisation.getIssuedBy() >0){
									crsPayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));
									}
									crsPayldin.setObjectID(crsctrlperson.getId());
									crsPayldin.setSrcType("0");
									crsPayldin = crspayldinRepository.saveAndFlush(crsPayldin);
								}
								logger.info("<<<<<<<<<<<Controlling Person In typ[Crspayldin] End saving>>>>>>>>>>>>>>>");
								
							}
							
							//Controlling Person NameType Grid
							if(controlingPersonVo.getNameTypeList() != null && controlingPersonVo.getNameTypeList().size() > 0){
								logger.info("<<<<<<<<<<<Controlling Person NameType Grid[Crspayldname] Begin saving>>>>>>>>>>>>>>>");	
								for(NameTypeVo nameVo : controlingPersonVo.getNameTypeList()){
									Crspayldname crspayldName = new Crspayldname();
									crspayldName.setCreateDateTime(new Date());
									crspayldName.setFirstName(nameVo.getFirstName());
									crspayldName.setGeneralSuffix(nameVo.getGeneralSuffix());
									crspayldName.setLastName(nameVo.getLastName());
									crspayldName.setNamePrefix(nameVo.getNamePrefix());
									crspayldName.setPrecedingTitle(nameVo.getPrecedingTitle());
									crspayldName.setObjectID(crsctrlperson.getId());
									crspayldName.setSrcType("0");
									crspayldName = crspayldnameRepository.saveAndFlush(crspayldName);
									
									
									if(nameVo.getTitleList() != null &&nameVo.getTitleList().size() >0 ){
										for(TitleVo titleVo : nameVo.getTitleList()){
										Crspayldnametitle title = new Crspayldnametitle();
										title.setTitle(titleVo.getName());
										title.setNameID(crspayldName.getId());
										title.setCreateDateTime(new Date());
										title = crspayldnametitleRepository.saveAndFlush(title);
										}
									}
									
									if(nameVo.getMiddlenameList() != null && nameVo.getMiddlenameList().size() > 0){
										for(MiddleNameVo middileNamevo :nameVo.getMiddlenameList() ){
											Crspayldnamemiddle middilename = new Crspayldnamemiddle();
											middilename.setMiddleName(middileNamevo.getMiddleName());
											middilename.setCreateDateTime(new Date());
											middilename.setNameID(crspayldName.getId());
											middilename = crspayldnamemiddleRepository.saveAndFlush(middilename);
										}
									}
									
									if(nameVo.getGenerateIdentifilerList() != null &&nameVo.getGenerateIdentifilerList().size() >0 ){
										for(GenerationIdentifierVo generateId :nameVo.getGenerateIdentifilerList() ){
											Crspayldnamegeneration generationid = new Crspayldnamegeneration();
											generationid.setGenerationIdentifier(generateId.getGenerateIdentifier());
											generationid.setCreateDateTime(new Date());
											generationid.setNameID(crspayldName.getId());
											generationid = crspayldnamegenerationRepository.saveAndFlush(generationid);
										}
									}
									if(nameVo.getSuffixList() != null && nameVo.getSuffixList().size() >0){
										for(SuffixVo suffix :nameVo.getSuffixList() ){
											Crspayldnamesuffix suffi= new Crspayldnamesuffix();
											suffi.setSuffix(suffix.getSuffix());
											suffi.setNameID(crspayldName.getId());
											suffi.setCreateDateTime(new Date());
											suffi = crspayldnamesuffixRepository.saveAndFlush(suffi);
										}
									}	
									
								}
								logger.info("<<<<<<<<<<<Individual NameType Grid[Crspayldname] End saving>>>>>>>>>>>>>>>");
							}//NameList
							
							
							
							
							
							
							
						}
						
						
					}//Controlling Person List End
							
						
					}
							
					
				}//Account holder List
					
								
					
				}//Account Holder
							
			
		}//CRS Payldhdr
		
		

		
		}//MetaDataVo
		
		
		return hidefVo;
	}

	
}

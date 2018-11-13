package com.censof.myfi.hidefmyfi.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DecimalFormat;
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
import com.censof.myfi.hidefmyfi.repository.DocrefidRepository;
import com.censof.myfi.hidefmyfi.repository.MessagerefidRepository;
import com.censof.myfi.hidefmyfi.repository.SenderfileidRepository;
import com.censof.myfi.hidefmyfi.repository.UserRepository;
import com.censof.myfi.hidefmyfi.repository.UsereceivingcountryRepository;
import com.censof.myfi.hidefmyfi.repository.UserprofileRepository;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.vo.AddressVo;
import com.censof.myfi.hidefmyfi.vo.BizActivitiesTypeVo;
import com.censof.myfi.hidefmyfi.vo.CBCRepotsVo;
import com.censof.myfi.hidefmyfi.vo.CBCSummaryGridVo;
import com.censof.myfi.hidefmyfi.vo.CbcAdditionalInfo;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.MetaDataVo;
import com.censof.myfi.hidefmyfi.vo.NameVo;
import com.censof.myfi.hidefmyfi.vo.OrganisationInTypeVo;
import com.censof.myfi.hidefmyfi.vo.RecievingCountryVo;
import com.censof.myfi.hidefmyfi.vo.ReportingEntityVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.censof.myfi.hidefmyfi.vo.SummaryVo;
import com.censof.myfi.hidefmyfi.vo.UserProfileVo;

@Service("ctcDataSaveService")
public class CtcDataSaveServiceImpl implements CtcDataSaveService{
	
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
	
	
	
	
	@Override
	@Transactional
	public HidefVo saveCtcData(HidefVo hidefVo) {

		
		if(hidefVo != null){
		if(hidefVo.getMetadata() != null){
		/**
		 * Cbcpayldhdr
		 */
		logger.info("Receiving Country Code: TABLE:[Cbcpayldhdr]");
		Cbcpayldhdr payldhdr = null;
		if(hidefVo.getMetadata().getId() != null){
		payldhdr = cbcpayldhdrRepository.getCbcDetailsById(hidefVo.getMetadata().getId());
		}
		if(payldhdr == null){
			payldhdr = new Cbcpayldhdr();
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getSendingCountry())){
		payldhdr.setSenderCountryCd(hidefVo.getMetadata().getSendingCountry());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getMessageTypeIndic())){
		payldhdr.setMessageTypeIndic(hidefVo.getMetadata().getMessageTypeIndic());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getMsgType())){
		payldhdr.setMessageType(hidefVo.getMetadata().getMsgType());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getWarning())){
		payldhdr.setWarning(hidefVo.getMetadata().getWarning());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getContact())){
		payldhdr.setContact(hidefVo.getMetadata().getContact());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getReportingPeriod())){
		payldhdr.setReportingPeriod(hidefVo.getMetadata().getReportingPeriod());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getTaxYear())){
			payldhdr.setTaxYear(Integer.parseInt(hidefVo.getMetadata().getTaxYear()));
		}
		
		//payldhdr.setFileCreateTs(new TimeSnew Date());
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getCommunicationType())){
		payldhdr.setCommunicationTypeCd(hidefVo.getMetadata().getCommunicationType());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getSenderFileId())){
		payldhdr.setSenderFileId(hidefVo.getMetadata().getSenderFileId());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getFileFormatCode())){
		payldhdr.setFileFormatCD(hidefVo.getMetadata().getFileFormatCode());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getBinaryEncoding())){
		payldhdr.setBinaryEncodingSchemeCd(hidefVo.getMetadata().getBinaryEncoding());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getMessageRefId())){
		payldhdr.setMessageRefId(hidefVo.getMetadata().getMessageRefId());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getSenderContactEmailAddress())){
		payldhdr.setSenderContactEmailAddressTxt(hidefVo.getMetadata().getSenderContactEmailAddress());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getSendingCompanyIN())){
		payldhdr.setSendingEntityIN(hidefVo.getMetadata().getSendingCompanyIN());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getLanguage())){
		payldhdr.setLanguage(hidefVo.getMetadata().getLanguage());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getFormCreationTimeStamp())){
			payldhdr.setFileCreateTs(hidefVo.getMetadata().getFormCreationTimeStamp());
		}
		if(!StringUtils.isEmpty(hidefVo.getMycbcId())){
			payldhdr.setMyCBCID(hidefVo.getMycbcId());
		}
		payldhdr.setIsdeleted(0);
		payldhdr.setCreateDateTime(new Date());
		payldhdr = cbcpayldhdrRepository.saveAndFlush(payldhdr);
		logger.info("Cbcpayldhdr ID::::::>"+payldhdr.getId());
		
		
		/**
		 * cbcpayldreceivingcountry
		 */
		logger.info("Receiving Country Code: TABLE:[cbcpayldreceivingcountry]");
		if(hidefVo.getMetadata().getRecievingCountryList() != null && hidefVo.getMetadata().getRecievingCountryList().size()>0){
		for(RecievingCountryVo receivingCountry :hidefVo.getMetadata().getRecievingCountryList()){
		Cbcpayldreceivingcountry payldreceivingCountry = null;
		if(receivingCountry.getId() > 0){
		payldreceivingCountry =cbcpayldreceivingcountryRepository.getAllReceivingCountryByid(BigInteger.valueOf(receivingCountry.getId()));
		logger.info("");
		}
		if(payldreceivingCountry == null){
			payldreceivingCountry = new Cbcpayldreceivingcountry();
		}
		payldreceivingCountry.setCreateDateTime(new Date());
		payldreceivingCountry.setHdrID(payldhdr.getId());
		if(!StringUtils.isEmpty(receivingCountry.getRecievingCountry())){
		payldreceivingCountry.setReceivingCountry(String.valueOf(receivingCountry.getRecievingCountry()));//TODO
		}
		payldreceivingCountry.setIsdeleted(0);
		payldreceivingCountry = cbcpayldreceivingcountryRepository.saveAndFlush(payldreceivingCountry);
		logger.info("cbcpayldreceivingcountry ID::::::>"+payldreceivingCountry.getId());
		}
		}
		
		
		
		/**
		 * cbcpayldbody
		 */
		logger.info("cbcpayldbody Saving: TABLE:[cbcpayldbody]");
		Cbcpayldbody cbcpayldbody = null;
		if(hidefVo.getMetadata().getId() != null){
			cbcpayldbody = cbcpayldbodyRepository.getAllBodyDetailsById(hidefVo.getMetadata().getId());
		}
		if(cbcpayldbody == null){
			cbcpayldbody = new Cbcpayldbody();
		}
		cbcpayldbody.setHdrID(payldhdr.getId());
		cbcpayldbody.setCreateDateTime(new Date());
		cbcpayldbody.setIsdeleted(0);
		cbcpayldbody = cbcpayldbodyRepository.saveAndFlush(cbcpayldbody);
		logger.info("cbcpayldbody ID::::::>"+cbcpayldbody.getId());
		
		
		
		
		/**
		 * Reporting Entity Tab Saving
		 */
		if(hidefVo.getReportingEntity() != null && payldhdr != null && payldhdr.getId() != null && cbcpayldbody != null && cbcpayldbody.getId() != null ){
		logger.info("Reporting Entity Extra Fields Saving: TABLE:[cbcpayldentity]");
		Cbcpayldentity payldentity = null;
		if(hidefVo.getReportingEntity().getId() != null){
		payldentity = cbcpayldentityRepository.getAllReportingEntityDetailsById(hidefVo.getReportingEntity().getId());
		}
		if(payldentity == null){
			payldentity = new Cbcpayldentity();
		}
		payldentity.setBodyID(cbcpayldbody.getId());
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getCorDocReferenceId())){
		payldentity.setCorrDocRefId(hidefVo.getReportingEntity().getCorDocReferenceId());
		}
		
		payldentity.setCreateDateTime(new Date());
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getDocumentReferenceId())){
		payldentity.setDocRefId(hidefVo.getReportingEntity().getDocumentReferenceId());
		}
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getDocumentTypeIndicator())){
		payldentity.setDocTypeIndic(hidefVo.getReportingEntity().getDocumentTypeIndicator());
		}
		payldentity.setHdrID(payldhdr.getId());
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getReportingRole())){
		payldentity.setReportingRole(hidefVo.getReportingEntity().getReportingRole());
		}
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getTin())){
		payldentity.setTin(hidefVo.getReportingEntity().getTin());
		}
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getTinType())){
		payldentity.setIssuedBy(hidefVo.getReportingEntity().getTinType());
		}
		payldentity.setIsdeleted(0);
		//payldentity.
		payldentity = cbcpayldentityRepository.saveAndFlush(payldentity);
		logger.info("cbcpayldentity ID::::::>"+payldentity.getId());
		
		/*if()
		logger.info("cbcpayldentity INSERT Begin..........");*/
		
		if(payldentity !=null && payldentity.getId() != null){
		
			/**
			 *  Reporting Entity Organisation Grid
			 */
			if(hidefVo.getReportingEntity().getNameList()  != null 
					&&	hidefVo.getReportingEntity().getNameList().size() >0){
				logger.info("Reporting Entity Organisation Grid: TABLE:[cbcpayldname]");
				for(NameVo namevo : hidefVo.getReportingEntity().getNameList()){
					Cbcpayldname payldname = null;
					if(namevo.getId() > 0){
					payldname = cbcpayldnameRepository.getAllPayldNameDetailsById(BigInteger.valueOf(namevo.getId()));
					}
					if(payldname == null){
						payldname = new Cbcpayldname();
					}
					payldname.setObjectID(payldentity.getId());
					payldname.setCreateDateTime(new Date());
					if(!StringUtils.isEmpty(namevo.getFirstName())){
					payldname.setNameOrganisation(namevo.getFirstName());
					}
					payldname.setSrcType("1");//TODO
					payldname.setIsdeleted(0);
					payldname = cbcpayldnameRepository.saveAndFlush(payldname);
					logger.info("cbcpayldname ID::::::>"+payldname.getId());
			}
			}
			
			/**
			 * Reporting Entity Resident Country Code Grid
			 */
			if(hidefVo.getReportingEntity().getResidentCountryList() != null && 
					hidefVo.getReportingEntity().getResidentCountryList().size() > 0){
				logger.info("Reporting Entity Resident Country Code Grid: TABLE:[cbcpayldrescountry]");
				for(ResidentCountryVo residentCountry:hidefVo.getReportingEntity().getResidentCountryList()){
					Cbcpayldrescountry cbcpayldrescountry = null;
					if(residentCountry.getId() > 0){
						cbcpayldrescountry =cbcpayldrescountryRepository.getAllPayldResidentCountryById(BigInteger.valueOf(residentCountry.getId()));
					}
					if(cbcpayldrescountry == null){
						cbcpayldrescountry = new Cbcpayldrescountry();
					}
					cbcpayldrescountry.setCreateDateTime(new Date());
					cbcpayldrescountry.setObjectID(payldentity.getId());
					if(!StringUtils.isEmpty(residentCountry.getResidentCountryCode())){
					cbcpayldrescountry.setResCountryCode(String.valueOf(residentCountry.getResidentCountryCode()));//TODO
					}
					cbcpayldrescountry.setSrcType("1");//TODO
					cbcpayldrescountry.setIsdeleted(0);
					cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
					logger.info("cbcpayldrescountry ID::::::>"+cbcpayldrescountry.getId());
					
				}
			}
			
			/**
			 * Reporting Entity In,InType,IssuedBy Grid
			 */
			if(hidefVo.getReportingEntity().getOrganisationInTypeList() != null 
					&& hidefVo.getReportingEntity().getOrganisationInTypeList().size() > 0){
				logger.info("cbcpayldin INSERT Begin..........");
				for(OrganisationInTypeVo organisation : hidefVo.getReportingEntity().getOrganisationInTypeList()){
					Cbcpayldin cbcpayldin = null;
					if(organisation.getId() >0){
						cbcpayldin = cbcpayldinRepository.getAllPayldInDetailsById(BigInteger.valueOf(organisation.getId()));
					}
					if(cbcpayldin == null){
						cbcpayldin = new Cbcpayldin();
					}
					cbcpayldin.setObjectID(payldentity.getId());
					cbcpayldin.setCreateDateTime(new Date());
					cbcpayldin.setTin(organisation.getIn());
					if(!StringUtils.isEmpty(organisation.getInType())){
					cbcpayldin.setINType(organisation.getInType());
					}
					if(!StringUtils.isEmpty(organisation.getIssuedBy())){
					cbcpayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));//TODO
					}
					cbcpayldin.setSrcType("1");//TODO
					cbcpayldin.setIsdeleted(0);
					cbcpayldin = cbcpayldinRepository.saveAndFlush(cbcpayldin);
					logger.info("cbcpayldin ID::::::>"+cbcpayldin.getId());
					}
				}
			
			
			/**
			 * Reporting Entity Address 
			 */
			
			if(hidefVo.getReportingEntity().getAddressList() != null
					&& hidefVo.getReportingEntity().getAddressList().size() > 0 ){
				logger.info("Reporting Entity Address: TABLE:[cbcpayldaddress]");
				for(AddressVo addressVo : hidefVo.getReportingEntity().getAddressList()){
					Cbcpayldaddress cbcpayldaddress = null;
					if(addressVo.getId() > 0){
						cbcpayldaddress = cbcpayldaddressRepository.getAllPayldAddressById(BigInteger.valueOf(addressVo.getId()));
					}
					if(cbcpayldaddress == null){
						cbcpayldaddress = new Cbcpayldaddress();
					}
					cbcpayldaddress.setObjectID(payldentity.getId());
					if(!StringUtils.isEmpty(addressVo.getAddressFree())){
						cbcpayldaddress.setAddressFree(addressVo.getAddressFree());
					}
					if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
						cbcpayldaddress.setBuildingIdentifier(addressVo.getBuildingIdentifier());
					}
					if(!StringUtils.isEmpty(addressVo.getCity())){
					cbcpayldaddress.setCity(addressVo.getCity());
					}
					if(!StringUtils.isEmpty(addressVo.getCountryCode())){
					cbcpayldaddress.setCountryCode(addressVo.getCountryCode());
					}
					if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
					cbcpayldaddress.setCountrySubentity(addressVo.getCountrySubentity());
					}
					
					cbcpayldaddress.setCreateDateTime(new Date());
					if(!StringUtils.isEmpty(addressVo.getDistrictName())){
					cbcpayldaddress.setDistrictName(addressVo.getDistrictName());
					}
					if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
					cbcpayldaddress.setFloorIdentifier(addressVo.getFloorIdentifier());
					}
					if(!StringUtils.isEmpty(addressVo.getAddressType())){
					cbcpayldaddress.setLegalAddressType(addressVo.getAddressType());
					}
					if(!StringUtils.isEmpty(addressVo.getPob())){
					cbcpayldaddress.setPob(addressVo.getPob());
					}
					if(!StringUtils.isEmpty(addressVo.getPostCode())){
					cbcpayldaddress.setPostCode(addressVo.getPostCode());
					}
					
					cbcpayldaddress.setSrcType("1");//TODO
					if(!StringUtils.isEmpty(addressVo.getStreet())){
					cbcpayldaddress.setStreet(addressVo.getStreet());
					}
					if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
					cbcpayldaddress.setSuiteIdentifier(addressVo.getSuitIdentifier());
					}
					cbcpayldaddress.setIsdeleted(0);
					
					cbcpayldaddress = cbcpayldaddressRepository.saveAndFlush(cbcpayldaddress);
					logger.info("cbcpayldaddress ID::::::>"+cbcpayldaddress.getId());
					
				}
			}		
		}	
		
		}
		
		
		/**
		 * CBC Reports
		 */
		if(hidefVo.getListCBCReports() != null && hidefVo.getListCBCReports().size()>0 && payldhdr != null && payldhdr.getId() != null
				&& cbcpayldbody != null && cbcpayldbody.getId() != null){
			logger.info("CBC Reports extra fileds : TABLE:[cbcpayldreport]");
			/*if(!StringUtils.isEmpty(str))*/
			for(CBCRepotsVo cbcReports : hidefVo.getListCBCReports()){
				Cbcpayldreport cbcpayldreport = null;
				if(cbcReports.getId() >0){
				cbcpayldreport = cbcpayldreportRepository.getAllPayldCBCReportsById(BigInteger.valueOf(cbcReports.getId()));
				}
				if(cbcpayldreport == null){
					cbcpayldreport = new Cbcpayldreport();
				}
				if(!StringUtils.isEmpty(cbcReports.getAssertAmount())){
				cbcpayldreport.setAssetsAmt(Double.valueOf(cbcReports.getAssertAmount().replace(",","")));
				}
				if(!StringUtils.isEmpty(cbcReports.getAssertCurrCode())){
				cbcpayldreport.setAssetsCurrCode(cbcReports.getAssertCurrCode());
				}
				cbcpayldreport.setBodyID(cbcpayldbody.getId());
				if(!StringUtils.isEmpty(cbcReports.getCapitalAmount())){
				cbcpayldreport.setCapitalAmt(Double.valueOf(cbcReports.getCapitalAmount().replace(",","")));
				}
				if(!StringUtils.isEmpty(hidefVo.getCbcReports().getCapitalCurrCode())){
				cbcpayldreport.setCapitalCurrCode(cbcReports.getCapitalCurrCode());
				}
				if(!StringUtils.isEmpty(cbcReports.getCorrDocRefId())){
				cbcpayldreport.setCorrDocRefId(cbcReports.getCorrDocRefId());
				}
				cbcpayldreport.setCreateDateTime(new Date());
				if(!StringUtils.isEmpty(cbcReports.getDocumentTypeIndicator())){
				cbcpayldreport.setDocTypeIndic(cbcReports.getDocumentTypeIndicator());
				}
				if(!StringUtils.isEmpty(cbcReports.getEarningAmount())){
				cbcpayldreport.setEarningsAmt(Double.valueOf(cbcReports.getEarningAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getEarningCurrCode())){
				cbcpayldreport.setEarningsCurrCode(cbcReports.getEarningCurrCode());
				}
				cbcpayldreport.setHdrID(payldhdr.getId());
				if(!StringUtils.isEmpty(cbcReports.getNbEmployees())){
				cbcpayldreport.setNbEmployees(Integer.valueOf(cbcReports.getNbEmployees()));
				}
				if(!StringUtils.isEmpty(cbcReports.getPrfitotloassAmount())){
				cbcpayldreport.setProfitOrLossAmt(Double.valueOf(cbcReports.getPrfitotloassAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getProfitorlossCurrCode())){
				cbcpayldreport.setProfitOrLossCurrCode(cbcReports.getProfitorlossCurrCode());
				}
				if(!StringUtils.isEmpty(cbcReports.getResidentCountryCode())){
				cbcpayldreport.setResCountryCode(cbcReports.getResidentCountryCode());
				}
				if(!StringUtils.isEmpty(cbcReports.getRelatedAmount())){
				cbcpayldreport.setRevenuesRelatedAmt(Double.valueOf(cbcReports.getRelatedAmount().replace(",","")));
				}
				if(!StringUtils.isEmpty(cbcReports.getRelatedCurrCode())){
				cbcpayldreport.setRevenuesRelatedCurrCode(cbcReports.getRelatedCurrCode());
				}
				if(!StringUtils.isEmpty(cbcReports.getTotalRevenueAmount())){
				cbcpayldreport.setRevenuesTotalAmt(Double.valueOf(cbcReports.getTotalRevenueAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getTotalRevenueCurrCode())){
				cbcpayldreport.setRevenuesTotalCurrCode(cbcReports.getTotalRevenueCurrCode());
				}
				if(!StringUtils.isEmpty(cbcReports.getUnrelatedAmount())){
				cbcpayldreport.setRevenuesUnrelatedAmt(Double.valueOf(cbcReports.getUnrelatedAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getUnrelateCurrCode())){
				cbcpayldreport.setRevenuesUnrelatedCurrCode(cbcReports.getUnrelateCurrCode());
				}
				if(!StringUtils.isEmpty(cbcReports.getTaxaccruedAmount())){
				cbcpayldreport.setTaxAccruedAmt(Double.valueOf(cbcReports.getTaxaccruedAmount().replace(",","")));
				}
				if(!StringUtils.isEmpty(hidefVo.getCbcReports().getTaxaccruedCurrCode())){
				cbcpayldreport.setTaxAccruedCurrCode(hidefVo.getCbcReports().getTaxaccruedCurrCode());
				}
				if(!StringUtils.isEmpty(cbcReports.getTaxpaidAmount())){
				cbcpayldreport.setTaxPaidAmt(Double.valueOf(cbcReports.getTaxpaidAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getTaxpiadCurrCode())){
				cbcpayldreport.setTaxPaidCurrCode(cbcReports.getTaxpiadCurrCode());
				}
				
				if(!StringUtils.isEmpty(cbcReports.getDocumentRefId())) {
					cbcpayldreport.setDocRefId(cbcReports.getDocumentRefId());
				}
				
				cbcpayldreport.setIsdeleted(0);
				cbcpayldreport = cbcpayldreportRepository.saveAndFlush(cbcpayldreport);
				logger.info("cbcpayldreport ID::::::>"+cbcpayldreport.getId());

				
				
				/**
				 * Constituent Entities :
				 */
				logger.info("CBCReports Constituent Entities : TABLE:[cbcpayldconstentity]");
				Cbcpayldconstentity cbcpayldconstentity = null;
				if(cbcReports.getConsId() >0){
					cbcpayldconstentity = cbcpayldconstentityRepository.getAllconstentityById(BigInteger.valueOf(cbcReports.getConsId()));
				}
				if(cbcpayldconstentity == null){
					cbcpayldconstentity = new Cbcpayldconstentity();
				}
				if(!StringUtils.isEmpty(cbcReports.getIncorpCountryCode())){
				cbcpayldconstentity.setIncorpCountryCode(cbcReports.getIncorpCountryCode());
				}
				if(!StringUtils.isEmpty(cbcReports.getTin())){
				cbcpayldconstentity.setTin(cbcReports.getTin());
				}
				if(!StringUtils.isEmpty(cbcReports.getTinType())){
				cbcpayldconstentity.setINType(cbcReports.getTinType());
				}
				if(!StringUtils.isEmpty(cbcReports.getIssuedBy())){
				cbcpayldconstentity.setIssuedBy(cbcReports.getIssuedBy());
				}
				if(!StringUtils.isEmpty(cbcReports.getOtherEntityInfo())){
				cbcpayldconstentity.setOtherEntityInfo(cbcReports.getOtherEntityInfo());
				}
				cbcpayldconstentity.setReportID(cbcpayldreport.getId());
				cbcpayldconstentity.setIsdeleted(0);
				cbcpayldconstentity = cbcpayldconstentityRepository.saveAndFlush(cbcpayldconstentity);
				logger.info("cbcpayldconstentity ID::::::>"+cbcpayldconstentity.getId());
				
				
				/**
				 * BiZ Activities
				 */
				if(cbcReports.getBizActivitiesList() != null && cbcReports.getBizActivitiesList().size() > 0){
					logger.info("CBCReports BiZ Activities TABLE:[cbcpayldbizactiv]");
					for(BizActivitiesTypeVo  bizActivitiesTypeVo : cbcReports.getBizActivitiesList()){
						Cbcpayldbizactiv cbcpayldbizactiv = null;
						if(bizActivitiesTypeVo.getId() > 0){
						cbcpayldbizactiv = cbcpayldbizactivRepository.getAllBizActivitiesByID(BigInteger.valueOf(bizActivitiesTypeVo.getId()));
						}
						if(cbcpayldbizactiv == null){
							cbcpayldbizactiv = new Cbcpayldbizactiv();
						}
						if(!StringUtils.isEmpty(bizActivitiesTypeVo.getBizType())){
						cbcpayldbizactiv.setBizActivities(String.valueOf(bizActivitiesTypeVo.getBizType()));
						}
						cbcpayldbizactiv.setConsentID(cbcpayldconstentity.getId());
						cbcpayldbizactiv.setCreateDateTime(new Date());
						cbcpayldbizactiv.setIsdeleted(0);
						cbcpayldbizactiv = cbcpayldbizactivRepository.saveAndFlush(cbcpayldbizactiv);
						logger.info("cbcpayldbizactiv ID::::::>"+cbcpayldbizactiv.getId());
					}
					
					
				}
				
				
				/**
				 * CBCReports Organisation Grid
				 */
				if(cbcReports.getNameList()  != null 
						&&	cbcReports.getNameList().size() >0){
					logger.info("CBCReports Organisation TABLE:[cbcpayldname]");
					for(NameVo namevo : cbcReports.getNameList()){
						Cbcpayldname payldname = null;;
						if(namevo.getId() > 0){
							payldname = cbcpayldnameRepository.getAllPayldNameDetailsById(BigInteger.valueOf(namevo.getId()));
						}
						if(payldname == null){
							payldname = new Cbcpayldname();
						}
						payldname.setObjectID(cbcpayldconstentity.getId());
						payldname.setCreateDateTime(new Date());
						if(!StringUtils.isEmpty(namevo.getFirstName())){
						payldname.setNameOrganisation(namevo.getFirstName());
						}
						payldname.setSrcType("1");//TODO
						payldname = cbcpayldnameRepository.saveAndFlush(payldname);
						logger.info("cbcpayldname ID::::::>"+payldname.getId());
				}
				}
				
				/**
				 * CBCReports Resident Country Code Grid
				 */
				if(cbcReports.getResidentCountryList() != null && 
						cbcReports.getResidentCountryList().size() > 0){
					logger.info("CBCReports Resident Country Code TABLE:[cbcpayldrescountry]");
					for(ResidentCountryVo residentCountry:cbcReports.getResidentCountryList()){
						Cbcpayldrescountry cbcpayldrescountry = null;
						if(residentCountry.getId() > 0){
							cbcpayldrescountry =cbcpayldrescountryRepository.getAllPayldResidentCountryById(BigInteger.valueOf(residentCountry.getId()));
						}
						if(cbcpayldrescountry == null){
							cbcpayldrescountry = new Cbcpayldrescountry();
						}
						cbcpayldrescountry.setCreateDateTime(new Date());
						cbcpayldrescountry.setObjectID(cbcpayldconstentity.getId());
						if(!StringUtils.isEmpty(residentCountry.getResidentCountryCode())){
						cbcpayldrescountry.setResCountryCode(String.valueOf(residentCountry.getResidentCountryCode()));//TODO
						}
						cbcpayldrescountry.setSrcType("1");//TODO
						cbcpayldrescountry.setIsdeleted(0);
						cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
						logger.info("cbcpayldrescountry ID::::::>"+cbcpayldrescountry.getId());
						
					}
				}
				
				/**
				 * CBCReports In,InType,IssuedBy Grid
				 */
				if(cbcReports.getOrganisationInTypeList() != null 
						&&cbcReports.getOrganisationInTypeList().size() > 0){
					logger.info("CBCReports In,InType,IssuedBy TABLE:[cbcpayldin]");
					for(OrganisationInTypeVo organisation : cbcReports.getOrganisationInTypeList()){
						Cbcpayldin cbcpayldin = null;
						if(organisation.getId() >0){
							cbcpayldin = cbcpayldinRepository.getAllPayldInDetailsById(BigInteger.valueOf(organisation.getId()));
						}
						if(cbcpayldin == null){
							cbcpayldin = new Cbcpayldin();
						}
						cbcpayldin.setObjectID(cbcpayldconstentity.getId());
						cbcpayldin.setCreateDateTime(new Date());
						if(!StringUtils.isEmpty(organisation.getIn())){
						cbcpayldin.setTin(organisation.getIn());
						}
						if(!StringUtils.isEmpty(organisation.getInType())){
						cbcpayldin.setINType(organisation.getInType());
						}
						if(!StringUtils.isEmpty(organisation.getIssuedBy())){
						cbcpayldin.setIssuedBy(String.valueOf(organisation.getIssuedBy()));//TODO
						}
						cbcpayldin.setSrcType("1");//TODO
						cbcpayldin.setIsdeleted(0);
						cbcpayldin = cbcpayldinRepository.saveAndFlush(cbcpayldin);
						logger.info("cbcpayldin ID::::::>"+cbcpayldin.getId());
						}
					}
				
				
				/**
				 * CBCReports Address 
				 */
				
				if(cbcReports.getAddressList() != null
						&& cbcReports.getAddressList().size() > 0 ){
					logger.info("CBCRepots Address  TABLE:[cbcpayldaddress]");
					for(AddressVo addressVo : cbcReports.getAddressList()){
						Cbcpayldaddress cbcpayldaddress = null;
						if(addressVo.getId() > 0){
							cbcpayldaddress = cbcpayldaddressRepository.getAllPayldAddressById(BigInteger.valueOf(addressVo.getId()));
						}
						if(cbcpayldaddress == null){
							cbcpayldaddress = new Cbcpayldaddress();
						}
						cbcpayldaddress.setObjectID(cbcpayldconstentity.getId());
						if(!StringUtils.isEmpty(addressVo.getAddressFree())){
							cbcpayldaddress.setAddressFree(addressVo.getAddressFree());
						}
						if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
							cbcpayldaddress.setBuildingIdentifier(addressVo.getBuildingIdentifier());
						}
						if(!StringUtils.isEmpty(addressVo.getCity())){
						cbcpayldaddress.setCity(addressVo.getCity());
						}
						if(!StringUtils.isEmpty(addressVo.getCountryCode())){
						cbcpayldaddress.setCountryCode(addressVo.getCountryCode());
						}
						if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
						cbcpayldaddress.setCountrySubentity(addressVo.getCountrySubentity());
						}
						
						cbcpayldaddress.setCreateDateTime(new Date());
						if(!StringUtils.isEmpty(addressVo.getDistrictName())){
						cbcpayldaddress.setDistrictName(addressVo.getDistrictName());
						}
						if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
						cbcpayldaddress.setFloorIdentifier(addressVo.getFloorIdentifier());
						}
						if(!StringUtils.isEmpty(addressVo.getAddressType())){
						cbcpayldaddress.setLegalAddressType(addressVo.getAddressType());
						}
						if(!StringUtils.isEmpty(addressVo.getPob())){
						cbcpayldaddress.setPob(addressVo.getPob());
						}
						if(!StringUtils.isEmpty(addressVo.getPostCode())){
						cbcpayldaddress.setPostCode(addressVo.getPostCode());
						}
						
						cbcpayldaddress.setSrcType("1");//TODO
						if(!StringUtils.isEmpty(addressVo.getStreet())){
						cbcpayldaddress.setStreet(addressVo.getStreet());
						}
						if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
						cbcpayldaddress.setSuiteIdentifier(addressVo.getSuitIdentifier());
						}
						cbcpayldaddress.setIsdeleted(0);
						cbcpayldaddress = cbcpayldaddressRepository.saveAndFlush(cbcpayldaddress);
						logger.info("cbcpayldaddress ID::::::>"+cbcpayldaddress.getId());
						
					}
				}	
					
				
			}
			
		}
		
		
		/**
		 * Additional Info
		 */
		if(hidefVo.getAddInfoList() != null && hidefVo.getAddInfoList().size()>0 && payldhdr != null && payldhdr.getId() != null
				&& cbcpayldbody != null && cbcpayldbody.getId() != null){
			logger.info("Additional Info Extra Fields TABLE:[cbcpayldaddinfo]");
			for(CbcAdditionalInfo addinfo : hidefVo.getAddInfoList()){
				Cbcpayldaddinfo sddInfo = null;
				if(addinfo.getId() > 0){
				sddInfo = cbcpayldaddinfoRepository.getAllAdditionalInfoById(BigInteger.valueOf(addinfo.getId()));
				}
				if(sddInfo == null){
					sddInfo = new Cbcpayldaddinfo();
				}
				sddInfo.setBodyID(cbcpayldbody.getId());
				if(!StringUtils.isEmpty(addinfo.getCorDocumentRefId())){
				sddInfo.setCorrDocRefId(addinfo.getCorDocumentRefId());
				}
				
				sddInfo.setCreateDateTime(new Date());
				if(!StringUtils.isEmpty(addinfo.getDocumentReferenceId())){
				sddInfo.setDocRefId(addinfo.getDocumentReferenceId());
				}
				if(!StringUtils.isEmpty(addinfo.getDocumentTypeIndic())){
				sddInfo.setDocTypeIndic(addinfo.getDocumentTypeIndic());
				}
				sddInfo.setHdrID(payldhdr.getId());
				if(!StringUtils.isEmpty(addinfo.getOtherInfo())){
				sddInfo.setOtherInfo(addinfo.getOtherInfo());
				}
				sddInfo.setIsdeleted(0);
				sddInfo = cbcpayldaddinfoRepository.saveAndFlush(sddInfo);
				logger.info("cbcpayldaddinfo ID::::::>"+sddInfo.getId());
				
				
				/**
				 * AdditionalInfo Resident Country Code Grid
				 */
				if(addinfo.getResidentCountryList() != null && 
						addinfo.getResidentCountryList().size() > 0){
					logger.info("Additional ResidentCountry TABLE:[cbcpayldrescountry]");
					for(ResidentCountryVo residentCountry:addinfo.getResidentCountryList()){
						Cbcpayldrescountry cbcpayldrescountry = null;
						if(residentCountry.getId() > 0){
							cbcpayldrescountry =cbcpayldrescountryRepository.getAllPayldResidentCountryById(BigInteger.valueOf(residentCountry.getId()));
						}
						if(cbcpayldrescountry == null){
							cbcpayldrescountry = new Cbcpayldrescountry();
						}
						cbcpayldrescountry.setCreateDateTime(new Date());
						cbcpayldrescountry.setObjectID(sddInfo.getId());
						if(!StringUtils.isEmpty(residentCountry.getResidentCountryCode())){
						cbcpayldrescountry.setResCountryCode(String.valueOf(residentCountry.getResidentCountryCode()));//TODO
						}
						cbcpayldrescountry.setSrcType("1");//TODO
						cbcpayldrescountry.setIsdeleted(0);
						cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
						logger.info("cbcpayldrescountry ID::::::>"+cbcpayldrescountry.getId());
						
					}
				}
				
				/**
				 * AdditionalInfo Summary Grid
				 */
				if(addinfo.getSummaryList() != null && 
						addinfo.getSummaryList().size() > 0){
					logger.info("Additional info Summary Ref TABLE:[cbcpayldsumref]");
					for(SummaryVo summary : addinfo.getSummaryList()){
						Cbcpayldsumref summaryRef = null;
						if(summary.getId() > 0){
						summaryRef = cbcpayldsumrefRepository.getAllSummaryById(BigInteger.valueOf(summary.getId()));
						}
						if(summaryRef == null){
							summaryRef = new Cbcpayldsumref();
						}
						summaryRef.setAddinfoID(sddInfo.getId());
						summaryRef.setCreateDateTime(new Date());
						if(!StringUtils.isEmpty(summary.getSummeryReference())){
						summaryRef.setSummaryRef(String.valueOf(summary.getSummeryReference()));
						}
						summaryRef.setIsdeleted(0);
						summaryRef = cbcpayldsumrefRepository.saveAndFlush(summaryRef);
						logger.info("cbcpayldsumref ID::::::>"+summaryRef.getId());
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
		if(docrefid != null){
			docrefid.setDocrefid(hidefVo.getDocRefId());
			docrefid = docrefidRepository.saveAndFlush(docrefid);
		}
		
		/**
		 * To update the senderFileId
		 */
		Senderfileid senderFileId = ctccommonDropdownService.findSenderFileIdByDate(date);
		if(senderFileId != null){
			String senderFieIdNew = hidefVo.getMetadata().getSenderFileId();
			senderFileId.setSenderfileid(senderFieIdNew.substring(senderFieIdNew.length() - 4));
			senderFileId = senderfileidRepository.saveAndFlush(senderFileId);
		}
		
	
		}//metadata
		
		
		}//hidef
		
		
		
		
		
		
		
		
		// TODO Auto-generated method stub
		return hidefVo;
	
	}

	@Override
	public HidefVo getAllDatabyCBCId(HidefVo hidefvo) {
		// TODO Auto-generated method stub
		logger.info("Get all the CBC Details based on CBCId:::"+hidefvo.getMycbcId());
		List<HidefVo> hidefList = new ArrayList<HidefVo>();
		List<CBCSummaryGridVo> summaryList = new ArrayList<CBCSummaryGridVo>();
		List<Cbcpayldhdr> cbcPayldhdr = cbcpayldhdrRepository.getAllCbcDetails(hidefvo.getMycbcId(),0);//need to change Login CBCId
		for(Cbcpayldhdr payldhdr : cbcPayldhdr){
			CBCSummaryGridVo summary = new CBCSummaryGridVo();
			
			summary.setMessageType(payldhdr.getMessageType());
			summary.setSendingCountry(payldhdr.getSenderCountryCd());
			summary.setId(summaryList.size()+1);
			summary.setHrdId(payldhdr.getId());
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
			  File convFile = new File(fetchProperties("privatecertpath")+'/'+hidefvo.getUserprofile().getConfigurationFileText());
			     convFile.createNewFile();
			     FileOutputStream fos = new FileOutputStream(convFile);
			     fos.write(hidefvo.getUserprofile().getConfigurationFile().getBytes());
			     fos.close();
			  userProfile.setSendingcountrycert(convFile.getAbsolutePath());
			  }
			  if(!StringUtils.isEmpty(hidefvo.getUserprofile().getPublicCertFileName()) && !StringUtils.isEmpty(fetchProperties("publiccertpath"))){ 
			  /*File publicCert = new File(publiccertpath);
			  hidefvo.getUserprofile().getConfigurationFile().transferTo(publicCert);*/
			  File convFile = new File(fetchProperties("publiccertpath")+'/'+hidefvo.getUserprofile().getPublicCertFileName());
			     convFile.createNewFile();
			     FileOutputStream fos = new FileOutputStream(convFile);
			     fos.write(hidefvo.getUserprofile().getPublicCertPath().getBytes());
			     fos.close();
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
		if(messageRefId != null){
			String newMessageRefId = hidefvo.getUserprofile().getMessageRefID();
			messageRefId.setMessagerefid(newMessageRefId.substring(newMessageRefId.length() - 4));
			messageRefId = messagerefidRepository.saveAndFlush(messageRefId);
		}
		
		
		return hidefvo;
	}
	
	
	public String fetchProperties(String propertyName){
        Properties properties = new Properties();
        String value= null;
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
		if(payldhdr != null){
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
			if(payldhdr.getTaxYear() > 0){
			metaData.setTaxYear(String.valueOf(payldhdr.getTaxYear()));
			}
			List<RecievingCountryVo> recievingCountryList = new ArrayList<RecievingCountryVo>();
			List<Cbcpayldreceivingcountry> receivingCountryList = cbcpayldreceivingcountryRepository.getAllReceivingCountry(payldhdr.getId());
			for(Cbcpayldreceivingcountry receivingCountry : receivingCountryList){
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
			List<Cbcpayldentity> reportingEntity = cbcpayldentityRepository.getAllReportingEntityDetails(payldhdr.getId());
			if(reportingEntity != null && reportingEntity.size() >0){
			Cbcpayldentity reportEntityDetail =  reportingEntity.get(0);
				ReportingEntityVo reportingEntityVo = new ReportingEntityVo();
				reportingEntityVo.setCorDocReferenceId(reportEntityDetail.getCorrDocRefId());
				reportingEntityVo.setReportingRole(reportEntityDetail.getReportingRole());;
				reportingEntityVo.setDocumentTypeIndicator(reportEntityDetail.getDocTypeIndic());
				reportingEntityVo.setTin(reportEntityDetail.getTin());
				reportingEntityVo.setTinIssuedBy(reportEntityDetail.getTin());
				reportingEntityVo.setTinType(reportEntityDetail.getIssuedBy());
				reportingEntityVo.setId(reportEntityDetail.getId());
				reportingEntityVo.setDocumentReferenceId(reportEntityDetail.getDocRefId());
				
				
				List<ResidentCountryVo> residentCountryList = new ArrayList<ResidentCountryVo>();
				
				List<Cbcpayldrescountry> residentCountry = cbcpayldrescountryRepository.getAllPayldResidentCountryByObjectId(reportEntityDetail.getId());
				if(residentCountry != null && residentCountry.size() >0){
				for(Cbcpayldrescountry resident:residentCountry){
					ResidentCountryVo residentCountryVo = new ResidentCountryVo();
					residentCountryVo.setId(resident.getId().intValue());
					if(resident.getResCountryCode() != null){
					residentCountryVo.setResidentCountryCode(Integer.parseInt(resident.getResCountryCode()));
					}
					residentCountryList.add(residentCountryVo);
				}
				}
				reportingEntityVo.setResidentCountryList(residentCountryList);
				
				List<Cbcpayldname> nameList = cbcpayldnameRepository.getAllPayldNameDetailsByObjectId(reportEntityDetail.getId());
				List<NameVo> nameVoList = new ArrayList<NameVo>();
				if(nameList != null && nameList.size() > 0){
				for(Cbcpayldname name:nameList){
					NameVo nameVO = new NameVo();
					nameVO.setFirstName(name.getNameOrganisation());
					nameVO.setId(name.getId().intValue());
					nameVoList.add(nameVO);
				}
				}
				reportingEntityVo.setNameList(nameVoList);
				
				List<OrganisationInTypeVo> organisationInTypeList = new ArrayList<OrganisationInTypeVo>();
				List<Cbcpayldin> cbcPayldin = cbcpayldinRepository.getAllPayldInDetailsByObjectId(reportEntityDetail.getId());
				if(cbcPayldin != null && cbcPayldin.size() > 0){
				for(Cbcpayldin cbcpayld:cbcPayldin){
					OrganisationInTypeVo orgVo = new OrganisationInTypeVo();
					orgVo.setId(cbcpayld.getId().intValue());
					orgVo.setIn(cbcpayld.getTin());
					orgVo.setInType(cbcpayld.getINType());
					if(cbcpayld.getIssuedBy() != null){
					orgVo.setIssuedBy(Integer.parseInt(cbcpayld.getIssuedBy()));
					}
					organisationInTypeList.add(orgVo);
				}
				}
				reportingEntityVo.setOrganisationInTypeList(organisationInTypeList);
				
				List<Cbcpayldaddress> cbcpayldaddress = cbcpayldaddressRepository.getAllPayldAddressByObjectId(reportEntityDetail.getId());
				List<AddressVo> addressList = new ArrayList<AddressVo>();
				if(cbcpayldaddress != null && cbcpayldaddress.size() > 0){
				for(Cbcpayldaddress address:cbcpayldaddress){
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
				List<Cbcpayldreport> cbcReports = cbcpayldreportRepository.getAllPayldCBCReportsByObjectId(payldhdr.getId());
				List<CBCRepotsVo> cbcReportsList = new ArrayList<CBCRepotsVo>();
				if(cbcReports != null && cbcReports.size()>0){
				for(Cbcpayldreport reports :cbcReports ){
					CBCRepotsVo cbcReport = new CBCRepotsVo();
					DecimalFormat formatter = new DecimalFormat("###,###,###,###.00");
					if(reports.getAssetsAmt() != null){
						
					cbcReport.setAssertAmount(String.valueOf(formatter.format(reports.getAssetsAmt().doubleValue())));
					}
					cbcReport.setAssertCurrCode(reports.getAssetsCurrCode());
					if(reports.getCapitalAmt() != null){
					cbcReport.setCapitalAmount(String.valueOf(formatter.format(reports.getCapitalAmt().doubleValue())));
					}
					cbcReport.setCapitalCurrCode(reports.getCapitalCurrCode());
					cbcReport.setCorrDocRefId(reports.getCorrDocRefId());
					//cbcReport.setCorrMessageRefId(reports.get);
					cbcReport.setDocumentRefId(reports.getDocRefId());
					cbcReport.setDocumentTypeIndicator(reports.getDocTypeIndic());
					if(reports.getEarningsAmt() != null){
					cbcReport.setEarningAmount(String.valueOf(formatter.format(reports.getEarningsAmt().doubleValue())));
					}
					cbcReport.setEarningCurrCode(reports.getEarningsCurrCode());
					cbcReport.setId(reports.getId().intValue());
					//cbcReport.setIncorpCountryCode(reports.get);
					//cbcReport.setIssuedBy(reports.get);
					if(reports.getNbEmployees() >=0){
					cbcReport.setNbEmployees(String.valueOf(reports.getNbEmployees()));
					}
					//cbcReport.setOtherEntityInfo(reports.get);
					if(reports.getProfitOrLossAmt() != null){
					cbcReport.setPrfitotloassAmount(String.valueOf(formatter.format(reports.getProfitOrLossAmt().doubleValue())));
					}
					cbcReport.setProfitorlossCurrCode(reports.getCapitalCurrCode());
					if(reports.getRevenuesRelatedAmt() != null){
					cbcReport.setRelatedAmount(String.valueOf(formatter.format(reports.getRevenuesRelatedAmt().doubleValue())));
					}
					cbcReport.setUnrelateCurrCode(reports.getRevenuesUnrelatedCurrCode());
					if(reports.getRevenuesUnrelatedAmt() != null){
					cbcReport.setUnrelatedAmount(String.valueOf(formatter.format(reports.getRevenuesUnrelatedAmt().doubleValue())));
					}
					cbcReport.setRelatedCurrCode(reports.getRevenuesRelatedCurrCode());
					cbcReport.setResidentCountryCode(reports.getResCountryCode());
					if(reports.getTaxAccruedAmt() != null){
					cbcReport.setTaxaccruedAmount(String.valueOf(formatter.format(reports.getTaxAccruedAmt().doubleValue())));
					}
					if(reports.getRevenuesTotalAmt() != null){
					cbcReport.setTotalRevenueAmount(String.valueOf(formatter.format(reports.getRevenuesTotalAmt())));
					}
					cbcReport.setTotalRevenueCurrCode(reports.getRevenuesTotalCurrCode());
					
					cbcReport.setTaxaccruedCurrCode(reports.getTaxAccruedCurrCode());
					if(reports.getTaxPaidAmt() != null){
					cbcReport.setTaxpaidAmount(String.valueOf(formatter.format(reports.getTaxPaidAmt().doubleValue())));
					}
					cbcReport.setTaxpiadCurrCode(reports.getTaxPaidCurrCode());
					
					
					Cbcpayldconstentity consentity = cbcpayldconstentityRepository.getAllconstentityByReportsId(reports.getId());
					if(consentity != null){
						cbcReport.setTin(consentity.getTin());
						cbcReport.setTinType(consentity.getINType());
						cbcReport.setIssuedBy(consentity.getIssuedBy());
						cbcReport.setIncorpCountryCode(consentity.getIncorpCountryCode());
						cbcReport.setOtherEntityInfo(consentity.getOtherEntityInfo());
						cbcReport.setConsId(consentity.getId().intValue());
						
						List<ResidentCountryVo> reportsresidentCountryList = new ArrayList<ResidentCountryVo>();
						
						List<Cbcpayldrescountry> cbcReportsresidentCountry = cbcpayldrescountryRepository.getAllPayldResidentCountryByObjectId(consentity.getId());
						if(cbcReportsresidentCountry != null && cbcReportsresidentCountry.size() >0){
						for(Cbcpayldrescountry resident:residentCountry){
							ResidentCountryVo residentCountryVo = new ResidentCountryVo();
							residentCountryVo.setId(resident.getId().intValue());
							if(resident.getResCountryCode() != null){
							residentCountryVo.setResidentCountryCode(Integer.parseInt(resident.getResCountryCode()));
							}
							reportsresidentCountryList.add(residentCountryVo);
						}
						}
						cbcReport.setResidentCountryList(residentCountryList);
						
						List<Cbcpayldname> cbcReporttsnameList = cbcpayldnameRepository.getAllPayldNameDetailsByObjectId(consentity.getId());
						List<NameVo> cbcReportsnameVoList = new ArrayList<NameVo>();
						if(cbcReporttsnameList != null && cbcReporttsnameList.size() > 0){
						for(Cbcpayldname name:nameList){
							NameVo nameVO = new NameVo();
							nameVO.setFirstName(name.getNameOrganisation());
							nameVO.setId(name.getId().intValue());
							cbcReportsnameVoList.add(nameVO);
						}
						}
						cbcReport.setNameList(cbcReportsnameVoList);
						
						List<OrganisationInTypeVo> cbcReportsorganisationInTypeList = new ArrayList<OrganisationInTypeVo>();
						List<Cbcpayldin> cbcreportsPayldin = cbcpayldinRepository.getAllPayldInDetailsByObjectId(consentity.getId());
						if(cbcreportsPayldin != null && cbcreportsPayldin.size() > 0){
						for(Cbcpayldin cbcpayld:cbcPayldin){
							OrganisationInTypeVo orgVo = new OrganisationInTypeVo();
							orgVo.setId(cbcpayld.getId().intValue());
							orgVo.setIn(cbcpayld.getTin());
							orgVo.setInType(cbcpayld.getINType());
							if(cbcpayld.getIssuedBy() != null){
							orgVo.setIssuedBy(Integer.parseInt(cbcpayld.getIssuedBy()));
							}
							cbcReportsorganisationInTypeList.add(orgVo);
						}
						}
						cbcReport.setOrganisationInTypeList(organisationInTypeList);
						
						
						List<Cbcpayldbizactiv> bizList = cbcpayldbizactivRepository.getAllBizActivitiesByConsentID(consentity.getId());
						List<BizActivitiesTypeVo> bizActivitiesList = new ArrayList<BizActivitiesTypeVo>();
						if(bizList != null && bizList.size()>0){
							for(Cbcpayldbizactiv biz:bizList){
							BizActivitiesTypeVo bizVo = new BizActivitiesTypeVo();
							bizVo.setId(biz.getId().intValue());
							if(biz.getBizActivities() !=null){
							bizVo.setBizType(Integer.parseInt(biz.getBizActivities()));
							}
							bizActivitiesList.add(bizVo);
							}
							cbcReport.setBizActivitiesList(bizActivitiesList);
						}
						
						List<Cbcpayldaddress> cbcreportspayldaddress = cbcpayldaddressRepository.getAllPayldAddressByObjectId(consentity.getId());
						List<AddressVo> cbcReportsaddressList = new ArrayList<AddressVo>();
						if(cbcreportspayldaddress != null && cbcreportspayldaddress.size() > 0){
						for(Cbcpayldaddress address:cbcpayldaddress){
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
						cbcReport.setAddressList(addressList);
						
						
						
					}
					cbcReportsList.add(cbcReport);
					 
					
				}
				hidefvo.setListCBCReports(cbcReportsList);
				}
				
				
				
				/**
				 * Additional Info
				 */
				List<Cbcpayldaddinfo> addInfo = cbcpayldaddinfoRepository.getAllAdditionalInfoByHdrIDId(payldhdr.getId());
				List<CbcAdditionalInfo> addInfoList = new ArrayList<CbcAdditionalInfo>();
				if(addInfo != null && addInfo.size() >0){
					for(Cbcpayldaddinfo add :addInfo ){
						
						CbcAdditionalInfo  sddVo = new CbcAdditionalInfo();
						sddVo.setCorDocumentRefId(add.getCorrDocRefId());
						//sddVo.setCorrMessageRefId(add.get);
						sddVo.setDocumentReferenceId(add.getDocRefId());
						sddVo.setDocumentTypeIndic(add.getDocTypeIndic());
						sddVo.setId(add.getId().intValue());
						sddVo.setOtherInfo(add.getOtherInfo());
						
						
						List<ResidentCountryVo> reportsresidentCountryList = new ArrayList<ResidentCountryVo>();
						
						List<Cbcpayldrescountry> cbcReportsresidentCountry = cbcpayldrescountryRepository.getAllPayldResidentCountryByObjectId(add.getId());
						if(cbcReportsresidentCountry != null && cbcReportsresidentCountry.size() >0){
						for(Cbcpayldrescountry resident:residentCountry){
							ResidentCountryVo residentCountryVo = new ResidentCountryVo();
							residentCountryVo.setId(resident.getId().intValue());
							if(resident.getResCountryCode() != null){
							residentCountryVo.setResidentCountryCode(Integer.parseInt(resident.getResCountryCode()));
							}
							reportsresidentCountryList.add(residentCountryVo);
						}
						}
						sddVo.setResidentCountryList(residentCountryList);
						
						
						List<Cbcpayldsumref> summaryList = cbcpayldsumrefRepository.getAllSummaryByAddinfoIDId(add.getId());
						List<SummaryVo> summaryVoList = new ArrayList<SummaryVo>();
						if(summaryList != null && summaryList.size() >0){
							for(Cbcpayldsumref sum :summaryList ){
								SummaryVo summaryVo = new SummaryVo();
								summaryVo.setId(sum.getId().intValue());
								if(sum.getSummaryRef() != null){
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
	public HidefVo saveExcelFile(HidefVo hidef) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		HidefVo hidefVo = new HidefVo();
		String excelWorkPath = fetchProperties("excelWorkPath");
		File file = new File(excelWorkPath + "/" + hidef.getImportExcelFileName());
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
								RecievingCountryVo coutryVo = new RecievingCountryVo();
								if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
									coutryVo.setId(Math.toIntExact(Math.round(currentCell.getNumericCellValue())));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									coutryVo.setId(Integer.parseInt(currentCell.getStringCellValue()));
								}
								receivingCountryList.add(coutryVo);
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
								hidefVo.getUserprofile().setFileTypeIndic(fileTypeIndic);
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
									hidefVo.getMetadata()
											.setReportingPeriod("" + Math.round(currentCell.getNumericCellValue()));
								} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
									hidefVo.getMetadata().setReportingPeriod(currentCell.getStringCellValue());
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
							if(residentVo == null) {
								residentVo = new ResidentCountryVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {

								int residentId = Math.toIntExact(Math.round(currentCell.getNumericCellValue()));
								residentVo.setId(residentId);

							} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
								int residentId = Integer.parseInt(currentCell.getStringCellValue());
								residentVo.setId(residentId);

							}
						}
						if (currentCell.getColumnIndex() == 9) {
							if(nameVo == null) {
								nameVo = new NameVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {

								nameVo.setFirstName(currentCell.getStringCellValue());

							}
						}

						if (currentCell.getColumnIndex() == 10) {
							if(orgINVo == null) {
								orgINVo = new OrganisationInTypeVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								orgINVo.setIn(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								orgINVo.setIn("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 11) {
							if(orgINVo == null) {
								orgINVo = new OrganisationInTypeVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								orgINVo.setInType(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								orgINVo.setInType("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 12) {
							if(orgINVo == null) {
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
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setCountryCode(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 14) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setAddressType(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 15) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setAddressFree(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 16) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setStreet(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 17) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setBuildingIdentifier(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 18) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setSuitIdentifier(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 19) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setFloorIdentifier(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 20) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setDistrictName(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 21) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setPob(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 22) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setPostCode(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 23) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setCity(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 24) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setCountrySubentity(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setCountrySubentity("" + Math.round(currentCell.getNumericCellValue()));
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

		while (cbcReportsIterator.hasNext()) {
			Row currentRow = cbcReportsIterator.next();
			if (currentRow.getRowNum() >= 2) {
				Iterator<Cell> cellIterator = currentRow.iterator();
				CBCRepotsVo reports = null;
				BizActivitiesTypeVo bizActivitiesVo = null;
				ResidentCountryVo residentVo = null;
				NameVo nameVo = null;
				OrganisationInTypeVo orgINVo = null;
				AddressVo addressVo = null;
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell != null && currentCell.getCellType() != Cell.CELL_TYPE_BLANK) {
						
						if(reports == null) {
							reports = new CBCRepotsVo();
						}
						
						
						if (currentCell.getColumnIndex() == 0) {
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
						if (currentCell.getColumnIndex() == 24) {
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								reports.setTin(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								reports.setTin("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 25) {
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								reports.setIssuedBy(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								reports.setIssuedBy("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 26) {
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								reports.setTinType(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								reports.setTinType("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 27) {
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								reports.setIncorpCountryCode(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								reports.setIncorpCountryCode("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 28) {
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								reports.setOtherEntityInfo(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								reports.setOtherEntityInfo("" + Math.round(currentCell.getNumericCellValue()));
							}
						}

						if (currentCell.getColumnIndex() == 29) {
							if(bizActivitiesVo == null) {
								bizActivitiesVo = new BizActivitiesTypeVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								bizActivitiesVo.setId(Integer.parseInt(currentCell.getStringCellValue()));

							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								bizActivitiesVo
										.setId(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));

							}
						}

						if (currentCell.getColumnIndex() == 30) {
							if(residentVo == null) {
								residentVo = new ResidentCountryVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {

								int residentId = Integer.parseInt(currentCell.getStringCellValue());
								residentVo.setId(residentId);
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								int residentId = Integer.parseInt("" + Math.round(currentCell.getNumericCellValue()));
								residentVo.setId(residentId);
							}
						}
						if (currentCell.getColumnIndex() == 31) {
							if(nameVo == null) {
								nameVo = new NameVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								nameVo.setFirstName(currentCell.getStringCellValue());
								reports.setNameList(new ArrayList<NameVo>());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								int residentId = Integer.parseInt("" + Math.round(currentCell.getNumericCellValue()));
								nameVo.setFirstName("" + residentId);
							}
						}

						if (currentCell.getColumnIndex() == 32) {
							if(orgINVo == null) {
								orgINVo = new OrganisationInTypeVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								orgINVo.setIn(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								orgINVo.setIn("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 33) {
							if(orgINVo == null) {
								orgINVo = new OrganisationInTypeVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								orgINVo.setInType(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								orgINVo.setInType("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 34) {
							if(orgINVo == null) {
								orgINVo = new OrganisationInTypeVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								orgINVo.setIssuedBy(Integer.parseInt(currentCell.getStringCellValue()));
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								orgINVo.setIssuedBy(
										Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
							}
						}

						if (currentCell.getColumnIndex() == 35) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setCountryCode(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 36) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setAddressType(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 37) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setAddressFree(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 38) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setStreet(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 39) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setBuildingIdentifier(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 40) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setSuitIdentifier(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 41) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setFloorIdentifier(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 42) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setDistrictName(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 43) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setPob(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 44) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setPostCode(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 45) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setCity(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 46) {
							if(addressVo == null) {
								addressVo = new AddressVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addressVo.setCountrySubentity(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addressVo.setCountrySubentity("" + Math.round(currentCell.getNumericCellValue()));
							}
						}

					}
				}

				if (bizActivitiesVo != null) {
					if (reports.getBizActivitiesList() != null && !reports.getBizActivitiesList().isEmpty()) {
						reports.getBizActivitiesList().add(bizActivitiesVo);
					} else {
						reports.setBizActivitiesList(new ArrayList<BizActivitiesTypeVo>());
						reports.getBizActivitiesList().add(bizActivitiesVo);
					}
				}

				if (residentVo != null) {
					if (reports.getResidentCountryList() != null && !reports.getResidentCountryList().isEmpty()) {
						reports.getResidentCountryList().add(residentVo);
					} else {
						reports.setResidentCountryList(new ArrayList<ResidentCountryVo>());
						reports.getResidentCountryList().add(residentVo);
					}
				}

				if (nameVo != null) {
					if (reports.getNameList() != null && !reports.getNameList().isEmpty()) {
						reports.getNameList().add(nameVo);
					} else {
						reports.setNameList(new ArrayList<NameVo>());
						reports.getNameList().add(nameVo);
					}
				}

				if (orgINVo != null) {
					if (reports.getOrganisationInTypeList() != null && !reports.getOrganisationInTypeList().isEmpty()) {
						reports.getOrganisationInTypeList().add(orgINVo);
					} else {
						reports.setOrganisationInTypeList(new ArrayList<OrganisationInTypeVo>());
						reports.getOrganisationInTypeList().add(orgINVo);
					}
				}

				if (addressVo != null) {
					if (reports.getAddressList() != null && !reports.getAddressList().isEmpty()) {
						reports.getAddressList().add(addressVo);
					} else {
						reports.setAddressList(new ArrayList<AddressVo>());
						reports.getAddressList().add(addressVo);
					}
				}

				if (reports != null) {
					hidefVo.getListCBCReports().add(reports);
				}
			}
		}

		Sheet additionalInfoSheet = workbook.getSheetAt(3);
		Iterator<Row> additionalInfo = additionalInfoSheet.iterator();
		hidefVo.setAddInfoList(new ArrayList<CbcAdditionalInfo>());

		while (additionalInfo.hasNext()) {
			Row currentRow = additionalInfo.next();
			if (currentRow.getRowNum() >= 2) {
				CbcAdditionalInfo addInfo = null;
				Iterator<Cell> cellIterator = currentRow.iterator();
				ResidentCountryVo residentVo = null;
				SummaryVo summaryVo = null;

				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell != null && currentCell.getCellType() != Cell.CELL_TYPE_BLANK) {
						
						if(addInfo == null) {
							addInfo = new CbcAdditionalInfo();
						}
						
						if (currentCell.getColumnIndex() == 0) {
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
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								addInfo.setOtherInfo(currentCell.getStringCellValue());
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								addInfo.setOtherInfo("" + Math.round(currentCell.getNumericCellValue()));
							}
						}
						if (currentCell.getColumnIndex() == 6) {
							if(residentVo == null) {
								residentVo = new ResidentCountryVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								int residentId = Integer.parseInt(currentCell.getStringCellValue());
								residentVo.setId(residentId);
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								int residentId = Integer.parseInt("" + Math.round(currentCell.getNumericCellValue()));
								residentVo.setId(residentId);
							}
						}
						if (currentCell.getColumnIndex() == 7) {
							if(summaryVo == null) {
								summaryVo = new SummaryVo();
							}
							if (currentCell.getCellTypeEnum() == CellType.STRING) {
								int summaryId = Integer.parseInt(currentCell.getStringCellValue());
								summaryVo.setSummeryReference(summaryId);
							} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								int summaryId = Integer.parseInt("" + Math.round(currentCell.getNumericCellValue()));
								summaryVo.setSummeryReference(summaryId);
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

				if (addInfo != null) {
					if (hidefVo.getAddInfoList() != null && !hidefVo.getAddInfoList().isEmpty()) {
						hidefVo.getAddInfoList().add(addInfo);
					} else {
						hidefVo.setAddInfoList(new ArrayList<CbcAdditionalInfo>());
						hidefVo.getAddInfoList().add(addInfo);
					}
				}
			}
		}

		workbook.close();
		file.delete();
		logger.info("Metadata Object =====> " + hidefVo);
		return hidefVo;
	}

	@Override
	public HidefVo saveCtcExcelData(HidefVo hidefVo) {

		
		if(hidefVo != null){
		if(hidefVo.getMetadata() != null){
		/**
		 * Cbcpayldhdr
		 */
		logger.info("Receiving Country Code: TABLE:[Cbcpayldhdr]");
		Cbcpayldhdr payldhdr = null;
		if(hidefVo.getMetadata().getId() != null){
		payldhdr = cbcpayldhdrRepository.getCbcDetailsById(hidefVo.getMetadata().getId());
		}
		if(payldhdr == null){
			payldhdr = new Cbcpayldhdr();
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getSendingCountry())){
		payldhdr.setSenderCountryCd(findCountryCodeConvertStringToBigInt(hidefVo.getMetadata().getSendingCountry()));
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getMessageTypeIndic())){
		payldhdr.setMessageTypeIndic(hidefVo.getMetadata().getMessageTypeIndic());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getMsgType())){
		payldhdr.setMessageType(hidefVo.getMetadata().getMsgType());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getWarning())){
		payldhdr.setWarning(hidefVo.getMetadata().getWarning());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getContact())){
		payldhdr.setContact(hidefVo.getMetadata().getContact());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getReportingPeriod())){
		payldhdr.setReportingPeriod(hidefVo.getMetadata().getReportingPeriod());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getTaxYear())){
			payldhdr.setTaxYear(Integer.parseInt(hidefVo.getMetadata().getTaxYear()));
		}
		
		//payldhdr.setFileCreateTs(new TimeSnew Date());
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getCommunicationType())){
		payldhdr.setCommunicationTypeCd(commonDropDownService.findCtscommunicationtypecdbyId(hidefVo.getMetadata().getCommunicationType()).getCTSCoumnicationType());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getSenderFileId())){
		payldhdr.setSenderFileId(hidefVo.getMetadata().getSenderFileId());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getFileFormatCode())){
		payldhdr.setFileFormatCD(commonDropDownService.findFileFormatCodeById(Integer.parseInt(hidefVo.getMetadata().getFileFormatCode())).getCBCFileFormatType());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getBinaryEncoding())){
		payldhdr.setBinaryEncodingSchemeCd(commonDropDownService.findBinaryEncodingSchemeById(Integer.parseInt(hidefVo.getMetadata().getBinaryEncoding())).getType());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getMessageRefId())){
		payldhdr.setMessageRefId(hidefVo.getMetadata().getMessageRefId());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getSenderContactEmailAddress())){
		payldhdr.setSenderContactEmailAddressTxt(hidefVo.getMetadata().getSenderContactEmailAddress());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getSendingCompanyIN())){
		payldhdr.setSendingEntityIN(hidefVo.getMetadata().getSendingCompanyIN());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getLanguage())){
		payldhdr.setLanguage(hidefVo.getMetadata().getLanguage());
		}
		if(!StringUtils.isEmpty(hidefVo.getMetadata().getFormCreationTimeStamp())){
			payldhdr.setFileCreateTs(hidefVo.getMetadata().getFormCreationTimeStamp());
		}
		if(!StringUtils.isEmpty(hidefVo.getMycbcId())){
			payldhdr.setMyCBCID(hidefVo.getMycbcId());
		}
		payldhdr.setIsdeleted(0);
		payldhdr.setCreateDateTime(new Date());
		payldhdr = cbcpayldhdrRepository.saveAndFlush(payldhdr);
		logger.info("Cbcpayldhdr ID::::::>"+payldhdr.getId());
		
		
		/**
		 * cbcpayldreceivingcountry
		 */
		logger.info("Receiving Country Code: TABLE:[cbcpayldreceivingcountry]");
		if(hidefVo.getMetadata().getRecievingCountryList() != null && hidefVo.getMetadata().getRecievingCountryList().size()>0){
		for(RecievingCountryVo receivingCountry :hidefVo.getMetadata().getRecievingCountryList()){
		Cbcpayldreceivingcountry payldreceivingCountry = null;
		if(receivingCountry.getId() > 0){
		payldreceivingCountry =cbcpayldreceivingcountryRepository.getAllReceivingCountryByid(BigInteger.valueOf(receivingCountry.getId()));
		logger.info("");
		}
		if(payldreceivingCountry == null){
			payldreceivingCountry = new Cbcpayldreceivingcountry();
		}
		payldreceivingCountry.setCreateDateTime(new Date());
		payldreceivingCountry.setHdrID(payldhdr.getId());
		if(!StringUtils.isEmpty(receivingCountry.getRecievingCountry())){
		payldreceivingCountry.setReceivingCountry(findCountryCodeById(BigInteger.valueOf(receivingCountry.getId())));//TODO
		}
		payldreceivingCountry.setIsdeleted(0);
		payldreceivingCountry = cbcpayldreceivingcountryRepository.saveAndFlush(payldreceivingCountry);
		logger.info("cbcpayldreceivingcountry ID::::::>"+payldreceivingCountry.getId());
		}
		}
		
		
		
		/**
		 * cbcpayldbody
		 */
		logger.info("cbcpayldbody Saving: TABLE:[cbcpayldbody]");
		Cbcpayldbody cbcpayldbody = null;
		if(hidefVo.getMetadata().getId() != null){
			cbcpayldbody = cbcpayldbodyRepository.getAllBodyDetailsById(hidefVo.getMetadata().getId());
		}
		if(cbcpayldbody == null){
			cbcpayldbody = new Cbcpayldbody();
		}
		cbcpayldbody.setHdrID(payldhdr.getId());
		cbcpayldbody.setCreateDateTime(new Date());
		cbcpayldbody.setIsdeleted(0);
		cbcpayldbody = cbcpayldbodyRepository.saveAndFlush(cbcpayldbody);
		logger.info("cbcpayldbody ID::::::>"+cbcpayldbody.getId());
		
		
		
		
		/**
		 * Reporting Entity Tab Saving
		 */
		if(hidefVo.getReportingEntity() != null && payldhdr != null && payldhdr.getId() != null && cbcpayldbody != null && cbcpayldbody.getId() != null ){
		logger.info("Reporting Entity Extra Fields Saving: TABLE:[cbcpayldentity]");
		Cbcpayldentity payldentity = null;
		if(hidefVo.getReportingEntity().getId() != null){
		payldentity = cbcpayldentityRepository.getAllReportingEntityDetailsById(hidefVo.getReportingEntity().getId());
		}
		if(payldentity == null){
			payldentity = new Cbcpayldentity();
		}
		payldentity.setBodyID(cbcpayldbody.getId());
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getCorDocReferenceId())){
		payldentity.setCorrDocRefId(hidefVo.getReportingEntity().getCorDocReferenceId());
		}
		
		payldentity.setCreateDateTime(new Date());
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getDocumentReferenceId())){
		payldentity.setDocRefId(hidefVo.getReportingEntity().getDocumentReferenceId());
		}
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getDocumentTypeIndicator())){
		payldentity.setDocTypeIndic(hidefVo.getReportingEntity().getDocumentTypeIndicator());
		}
		payldentity.setHdrID(payldhdr.getId());
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getReportingRole())){
		payldentity.setReportingRole(hidefVo.getReportingEntity().getReportingRole());
		}
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getTin())){
		payldentity.setTin(hidefVo.getReportingEntity().getTin());
		}
		if(!StringUtils.isEmpty(hidefVo.getReportingEntity().getTinType())){
		payldentity.setIssuedBy(findCountryCodeConvertStringToBigInt(hidefVo.getReportingEntity().getTinType()));
		}
		payldentity.setIsdeleted(0);
		//payldentity.
		payldentity = cbcpayldentityRepository.saveAndFlush(payldentity);
		logger.info("cbcpayldentity ID::::::>"+payldentity.getId());
		
		/*if()
		logger.info("cbcpayldentity INSERT Begin..........");*/
		
		if(payldentity !=null && payldentity.getId() != null){
		
			/**
			 *  Reporting Entity Organisation Grid
			 */
			if(hidefVo.getReportingEntity().getNameList()  != null 
					&&	hidefVo.getReportingEntity().getNameList().size() >0){
				logger.info("Reporting Entity Organisation Grid: TABLE:[cbcpayldname]");
				for(NameVo namevo : hidefVo.getReportingEntity().getNameList()){
					Cbcpayldname payldname = null;
					if(namevo.getId() > 0){
					payldname = cbcpayldnameRepository.getAllPayldNameDetailsById(BigInteger.valueOf(namevo.getId()));
					}
					if(payldname == null){
						payldname = new Cbcpayldname();
					}
					payldname.setObjectID(payldentity.getId());
					payldname.setCreateDateTime(new Date());
					if(!StringUtils.isEmpty(namevo.getFirstName())){
					payldname.setNameOrganisation(namevo.getFirstName());
					}
					payldname.setSrcType("1");//TODO
					payldname.setIsdeleted(0);
					payldname = cbcpayldnameRepository.saveAndFlush(payldname);
					logger.info("cbcpayldname ID::::::>"+payldname.getId());
			}
			}
			
			/**
			 * Reporting Entity Resident Country Code Grid
			 */
			if(hidefVo.getReportingEntity().getResidentCountryList() != null && 
					hidefVo.getReportingEntity().getResidentCountryList().size() > 0){
				logger.info("Reporting Entity Resident Country Code Grid: TABLE:[cbcpayldrescountry]");
				for(ResidentCountryVo residentCountry:hidefVo.getReportingEntity().getResidentCountryList()){
					Cbcpayldrescountry cbcpayldrescountry = null;
					if(residentCountry.getId() > 0){
						cbcpayldrescountry =cbcpayldrescountryRepository.getAllPayldResidentCountryById(BigInteger.valueOf(residentCountry.getId()));
					}
					if(cbcpayldrescountry == null){
						cbcpayldrescountry = new Cbcpayldrescountry();
					}
					cbcpayldrescountry.setCreateDateTime(new Date());
					cbcpayldrescountry.setObjectID(payldentity.getId());
					cbcpayldrescountry.setResCountryCode(findCountryCodeById(BigInteger.valueOf(residentCountry.getId())));//TODO
					cbcpayldrescountry.setSrcType("1");//TODO
					cbcpayldrescountry.setIsdeleted(0);
					cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
					logger.info("cbcpayldrescountry ID::::::>"+cbcpayldrescountry.getId());
					
				}
			}
			
			/**
			 * Reporting Entity In,InType,IssuedBy Grid
			 */
			if(hidefVo.getReportingEntity().getOrganisationInTypeList() != null 
					&& hidefVo.getReportingEntity().getOrganisationInTypeList().size() > 0){
				logger.info("cbcpayldin INSERT Begin..........");
				for(OrganisationInTypeVo organisation : hidefVo.getReportingEntity().getOrganisationInTypeList()){
					Cbcpayldin cbcpayldin = null;
					if(organisation.getId() >0){
						cbcpayldin = cbcpayldinRepository.getAllPayldInDetailsById(BigInteger.valueOf(organisation.getId()));
					}
					if(cbcpayldin == null){
						cbcpayldin = new Cbcpayldin();
					}
					cbcpayldin.setObjectID(payldentity.getId());
					cbcpayldin.setCreateDateTime(new Date());
					cbcpayldin.setTin(organisation.getIn());
					if(!StringUtils.isEmpty(organisation.getInType())){
					cbcpayldin.setINType(organisation.getInType());
					}
					if(!StringUtils.isEmpty(organisation.getIssuedBy())){
					cbcpayldin.setIssuedBy(findCountryCodeConvertStringToBigInt(String.valueOf(organisation.getIssuedBy())));//TODO
					}
					cbcpayldin.setSrcType("1");//TODO
					cbcpayldin.setIsdeleted(0);
					cbcpayldin = cbcpayldinRepository.saveAndFlush(cbcpayldin);
					logger.info("cbcpayldin ID::::::>"+cbcpayldin.getId());
					}
				}
			
			
			/**
			 * Reporting Entity Address 
			 */
			
			if(hidefVo.getReportingEntity().getAddressList() != null
					&& hidefVo.getReportingEntity().getAddressList().size() > 0 ){
				logger.info("Reporting Entity Address: TABLE:[cbcpayldaddress]");
				for(AddressVo addressVo : hidefVo.getReportingEntity().getAddressList()){
					Cbcpayldaddress cbcpayldaddress = null;
					if(addressVo.getId() > 0){
						cbcpayldaddress = cbcpayldaddressRepository.getAllPayldAddressById(BigInteger.valueOf(addressVo.getId()));
					}
					if(cbcpayldaddress == null){
						cbcpayldaddress = new Cbcpayldaddress();
					}
					cbcpayldaddress.setObjectID(payldentity.getId());
					if(!StringUtils.isEmpty(addressVo.getAddressFree())){
						cbcpayldaddress.setAddressFree(addressVo.getAddressFree());
					}
					if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
						cbcpayldaddress.setBuildingIdentifier(addressVo.getBuildingIdentifier());
					}
					if(!StringUtils.isEmpty(addressVo.getCity())){
					cbcpayldaddress.setCity(addressVo.getCity());
					}
					if(!StringUtils.isEmpty(addressVo.getCountryCode())){
					cbcpayldaddress.setCountryCode(findCountryCodeConvertStringToBigInt(addressVo.getCountryCode()));
					}
					if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
					cbcpayldaddress.setCountrySubentity(addressVo.getCountrySubentity());
					}
					
					cbcpayldaddress.setCreateDateTime(new Date());
					if(!StringUtils.isEmpty(addressVo.getDistrictName())){
					cbcpayldaddress.setDistrictName(addressVo.getDistrictName());
					}
					if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
					cbcpayldaddress.setFloorIdentifier(addressVo.getFloorIdentifier());
					}
					if(!StringUtils.isEmpty(addressVo.getAddressType())){
					cbcpayldaddress.setLegalAddressType(addressVo.getAddressType());
					}
					if(!StringUtils.isEmpty(addressVo.getPob())){
					cbcpayldaddress.setPob(addressVo.getPob());
					}
					if(!StringUtils.isEmpty(addressVo.getPostCode())){
					cbcpayldaddress.setPostCode(addressVo.getPostCode());
					}
					
					cbcpayldaddress.setSrcType("1");//TODO
					if(!StringUtils.isEmpty(addressVo.getStreet())){
					cbcpayldaddress.setStreet(addressVo.getStreet());
					}
					if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
					cbcpayldaddress.setSuiteIdentifier(addressVo.getSuitIdentifier());
					}
					cbcpayldaddress.setIsdeleted(0);
					
					cbcpayldaddress = cbcpayldaddressRepository.saveAndFlush(cbcpayldaddress);
					logger.info("cbcpayldaddress ID::::::>"+cbcpayldaddress.getId());
					
				}
			}		
		}	
		
		}
		
		
		/**
		 * CBC Reports
		 */
		if(hidefVo.getListCBCReports() != null && hidefVo.getListCBCReports().size()>0 && payldhdr != null && payldhdr.getId() != null
				&& cbcpayldbody != null && cbcpayldbody.getId() != null){
			logger.info("CBC Reports extra fileds : TABLE:[cbcpayldreport]");
			/*if(!StringUtils.isEmpty(str))*/
			for(CBCRepotsVo cbcReports : hidefVo.getListCBCReports()){
				Cbcpayldreport cbcpayldreport = null;
				if(cbcReports.getId() >0){
				cbcpayldreport = cbcpayldreportRepository.getAllPayldCBCReportsById(BigInteger.valueOf(cbcReports.getId()));
				}
				if(cbcpayldreport == null){
					cbcpayldreport = new Cbcpayldreport();
				}
				if(!StringUtils.isEmpty(cbcReports.getAssertAmount())){
				cbcpayldreport.setAssetsAmt(Double.valueOf(cbcReports.getAssertAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getAssertCurrCode())){
				cbcpayldreport.setAssetsCurrCode(findCurrencyById(cbcReports.getAssertCurrCode()));
				}
				cbcpayldreport.setBodyID(cbcpayldbody.getId());
				if(!StringUtils.isEmpty(cbcReports.getCapitalAmount())){
				cbcpayldreport.setCapitalAmt(Double.valueOf(cbcReports.getCapitalAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(hidefVo.getCbcReports().getCapitalCurrCode())){
				cbcpayldreport.setCapitalCurrCode(findCurrencyById(cbcReports.getCapitalCurrCode()));
				}
				if(!StringUtils.isEmpty(cbcReports.getCorrDocRefId())){
				cbcpayldreport.setCorrDocRefId(cbcReports.getCorrDocRefId());
				}
				cbcpayldreport.setCreateDateTime(new Date());
				if(!StringUtils.isEmpty(cbcReports.getDocumentTypeIndicator())){
				cbcpayldreport.setDocTypeIndic(cbcReports.getDocumentTypeIndicator());
				}
				if(!StringUtils.isEmpty(cbcReports.getEarningAmount())){
				cbcpayldreport.setEarningsAmt(Double.valueOf(cbcReports.getEarningAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getEarningCurrCode())){
				cbcpayldreport.setEarningsCurrCode(findCurrencyById(cbcReports.getEarningCurrCode()));
				}
				cbcpayldreport.setHdrID(payldhdr.getId());
				if(!StringUtils.isEmpty(cbcReports.getNbEmployees())){
				cbcpayldreport.setNbEmployees(Integer.valueOf(cbcReports.getNbEmployees()));
				}
				if(!StringUtils.isEmpty(cbcReports.getPrfitotloassAmount())){
				cbcpayldreport.setProfitOrLossAmt(Double.valueOf(cbcReports.getPrfitotloassAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getProfitorlossCurrCode())){
				cbcpayldreport.setProfitOrLossCurrCode(findCurrencyById(cbcReports.getProfitorlossCurrCode()));
				}
				if(!StringUtils.isEmpty(cbcReports.getResidentCountryCode())){
				cbcpayldreport.setResCountryCode(findCountryCodeConvertStringToBigInt(cbcReports.getResidentCountryCode()));
				}
				if(!StringUtils.isEmpty(cbcReports.getRelatedAmount())){
				cbcpayldreport.setRevenuesRelatedAmt(Double.valueOf(cbcReports.getRelatedAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getRelatedCurrCode())){
				cbcpayldreport.setRevenuesRelatedCurrCode(findCurrencyById(cbcReports.getRelatedCurrCode()));
				}
				if(!StringUtils.isEmpty(cbcReports.getTotalRevenueAmount())){
				cbcpayldreport.setRevenuesTotalAmt(Double.valueOf(cbcReports.getTotalRevenueAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getTotalRevenueCurrCode())){
				cbcpayldreport.setRevenuesTotalCurrCode(findCurrencyById(cbcReports.getTotalRevenueCurrCode()));
				}
				if(!StringUtils.isEmpty(cbcReports.getUnrelatedAmount())){
				cbcpayldreport.setRevenuesUnrelatedAmt(Double.valueOf(cbcReports.getUnrelatedAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getUnrelateCurrCode())){
				cbcpayldreport.setRevenuesUnrelatedCurrCode(findCurrencyById(cbcReports.getUnrelateCurrCode()));
				}
				if(!StringUtils.isEmpty(cbcReports.getTaxaccruedAmount())){
				cbcpayldreport.setTaxAccruedAmt(Double.valueOf(cbcReports.getTaxaccruedAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(hidefVo.getCbcReports().getTaxaccruedCurrCode())){
				cbcpayldreport.setTaxAccruedCurrCode(findCurrencyById(hidefVo.getCbcReports().getTaxaccruedCurrCode()));
				}
				if(!StringUtils.isEmpty(cbcReports.getTaxpaidAmount())){
				cbcpayldreport.setTaxPaidAmt(Double.valueOf(cbcReports.getTaxpaidAmount().replace(",", "")));
				}
				if(!StringUtils.isEmpty(cbcReports.getTaxpiadCurrCode())){
				cbcpayldreport.setTaxPaidCurrCode(findCurrencyById(cbcReports.getTaxpiadCurrCode()));
				}
				cbcpayldreport.setIsdeleted(0);
				cbcpayldreport = cbcpayldreportRepository.saveAndFlush(cbcpayldreport);
				logger.info("cbcpayldreport ID::::::>"+cbcpayldreport.getId());
				
				
				/**
				 * Constituent Entities :
				 */
				logger.info("CBCReports Constituent Entities : TABLE:[cbcpayldconstentity]");
				Cbcpayldconstentity cbcpayldconstentity = null;
				if(cbcReports.getConsId() >0){
					cbcpayldconstentity = cbcpayldconstentityRepository.getAllconstentityById(BigInteger.valueOf(cbcReports.getConsId()));
				}
				if(cbcpayldconstentity == null){
					cbcpayldconstentity = new Cbcpayldconstentity();
				}
				if(!StringUtils.isEmpty(cbcReports.getIncorpCountryCode())){
				cbcpayldconstentity.setIncorpCountryCode(findCountryCodeConvertStringToBigInt(cbcReports.getIncorpCountryCode()));
				}
				if(!StringUtils.isEmpty(cbcReports.getTin())){
				cbcpayldconstentity.setTin(cbcReports.getTin());
				}
				if(!StringUtils.isEmpty(cbcReports.getTinType())){
				cbcpayldconstentity.setINType(cbcReports.getTinType());
				}
				if(!StringUtils.isEmpty(cbcReports.getIssuedBy())){
				cbcpayldconstentity.setIssuedBy(findCountryCodeConvertStringToBigInt(cbcReports.getIssuedBy()));
				}
				if(!StringUtils.isEmpty(cbcReports.getOtherEntityInfo())){
				cbcpayldconstentity.setOtherEntityInfo(cbcReports.getOtherEntityInfo());
				}
				cbcpayldconstentity.setReportID(cbcpayldreport.getId());
				cbcpayldconstentity.setIsdeleted(0);
				cbcpayldconstentity = cbcpayldconstentityRepository.saveAndFlush(cbcpayldconstentity);
				logger.info("cbcpayldconstentity ID::::::>"+cbcpayldconstentity.getId());
				
				
				/**
				 * BiZ Activities
				 */
				if(cbcReports.getBizActivitiesList() != null && cbcReports.getBizActivitiesList().size() > 0){
					logger.info("CBCReports BiZ Activities TABLE:[cbcpayldbizactiv]");
					for(BizActivitiesTypeVo  bizActivitiesTypeVo : cbcReports.getBizActivitiesList()){
						Cbcpayldbizactiv cbcpayldbizactiv = null;
						if(bizActivitiesTypeVo.getId() > 0){
						cbcpayldbizactiv = cbcpayldbizactivRepository.getAllBizActivitiesByID(BigInteger.valueOf(bizActivitiesTypeVo.getId()));
						}
						if(cbcpayldbizactiv == null){
							cbcpayldbizactiv = new Cbcpayldbizactiv();
						}
						cbcpayldbizactiv.setBizActivities(commonDropDownService.findBizActivitiesById(bizActivitiesTypeVo.getId()).getBizType());
						cbcpayldbizactiv.setConsentID(cbcpayldconstentity.getId());
						cbcpayldbizactiv.setCreateDateTime(new Date());
						cbcpayldbizactiv.setIsdeleted(0);
						cbcpayldbizactiv = cbcpayldbizactivRepository.saveAndFlush(cbcpayldbizactiv);
						logger.info("cbcpayldbizactiv ID::::::>"+cbcpayldbizactiv.getId());
					}
					
					
				}
				
				
				/**
				 * CBCReports Organisation Grid
				 */
				if(cbcReports.getNameList()  != null 
						&&	cbcReports.getNameList().size() >0){
					logger.info("CBCReports Organisation TABLE:[cbcpayldname]");
					for(NameVo namevo : cbcReports.getNameList()){
						Cbcpayldname payldname = null;;
						if(namevo.getId() > 0){
							payldname = cbcpayldnameRepository.getAllPayldNameDetailsById(BigInteger.valueOf(namevo.getId()));
						}
						if(payldname == null){
							payldname = new Cbcpayldname();
						}
						payldname.setObjectID(cbcpayldconstentity.getId());
						payldname.setCreateDateTime(new Date());
						if(!StringUtils.isEmpty(namevo.getFirstName())){
						payldname.setNameOrganisation(namevo.getFirstName());
						}
						payldname.setSrcType("1");//TODO
						payldname = cbcpayldnameRepository.saveAndFlush(payldname);
						logger.info("cbcpayldname ID::::::>"+payldname.getId());
				}
				}
				
				/**
				 * CBCReports Resident Country Code Grid
				 */
				if(cbcReports.getResidentCountryList() != null && 
						cbcReports.getResidentCountryList().size() > 0){
					logger.info("CBCReports Resident Country Code TABLE:[cbcpayldrescountry]");
					for(ResidentCountryVo residentCountry:cbcReports.getResidentCountryList()){
						Cbcpayldrescountry cbcpayldrescountry = null;
						if(residentCountry.getId() > 0){
							cbcpayldrescountry =cbcpayldrescountryRepository.getAllPayldResidentCountryById(BigInteger.valueOf(residentCountry.getId()));
						}
						if(cbcpayldrescountry == null){
							cbcpayldrescountry = new Cbcpayldrescountry();
						}
						cbcpayldrescountry.setCreateDateTime(new Date());
						cbcpayldrescountry.setObjectID(cbcpayldconstentity.getId());
						if(!StringUtils.isEmpty(""+residentCountry.getId())){
						cbcpayldrescountry.setResCountryCode(findCountryCodeById(BigInteger.valueOf(residentCountry.getId())));//TODO
						}
						cbcpayldrescountry.setSrcType("1");//TODO
						cbcpayldrescountry.setIsdeleted(0);
						cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
						logger.info("cbcpayldrescountry ID::::::>"+cbcpayldrescountry.getId());
						
					}
				}
				
				/**
				 * CBCReports In,InType,IssuedBy Grid
				 */
				if(cbcReports.getOrganisationInTypeList() != null 
						&&cbcReports.getOrganisationInTypeList().size() > 0){
					logger.info("CBCReports In,InType,IssuedBy TABLE:[cbcpayldin]");
					for(OrganisationInTypeVo organisation : cbcReports.getOrganisationInTypeList()){
						Cbcpayldin cbcpayldin = null;
						if(organisation.getId() >0){
							cbcpayldin = cbcpayldinRepository.getAllPayldInDetailsById(BigInteger.valueOf(organisation.getId()));
						}
						if(cbcpayldin == null){
							cbcpayldin = new Cbcpayldin();
						}
						cbcpayldin.setObjectID(cbcpayldconstentity.getId());
						cbcpayldin.setCreateDateTime(new Date());
						if(!StringUtils.isEmpty(organisation.getIn())){
						cbcpayldin.setTin(organisation.getIn());
						}
						if(!StringUtils.isEmpty(organisation.getInType())){
						cbcpayldin.setINType(organisation.getInType());
						}
						if(!StringUtils.isEmpty(organisation.getIssuedBy())){
						cbcpayldin.setIssuedBy(findCountryCodeConvertStringToBigInt(String.valueOf(organisation.getIssuedBy())));//TODO
						}
						cbcpayldin.setSrcType("1");//TODO
						cbcpayldin.setIsdeleted(0);
						cbcpayldin = cbcpayldinRepository.saveAndFlush(cbcpayldin);
						logger.info("cbcpayldin ID::::::>"+cbcpayldin.getId());
						}
					}
				
				
				/**
				 * CBCReports Address 
				 */
				
				if(cbcReports.getAddressList() != null
						&& cbcReports.getAddressList().size() > 0 ){
					logger.info("CBCRepots Address  TABLE:[cbcpayldaddress]");
					for(AddressVo addressVo : cbcReports.getAddressList()){
						Cbcpayldaddress cbcpayldaddress = null;
						if(addressVo.getId() > 0){
							cbcpayldaddress = cbcpayldaddressRepository.getAllPayldAddressById(BigInteger.valueOf(addressVo.getId()));
						}
						if(cbcpayldaddress == null){
							cbcpayldaddress = new Cbcpayldaddress();
						}
						cbcpayldaddress.setObjectID(cbcpayldconstentity.getId());
						if(!StringUtils.isEmpty(addressVo.getAddressFree())){
							cbcpayldaddress.setAddressFree(addressVo.getAddressFree());
						}
						if(!StringUtils.isEmpty(addressVo.getBuildingIdentifier())){
							cbcpayldaddress.setBuildingIdentifier(addressVo.getBuildingIdentifier());
						}
						if(!StringUtils.isEmpty(addressVo.getCity())){
						cbcpayldaddress.setCity(addressVo.getCity());
						}
						if(!StringUtils.isEmpty(addressVo.getCountryCode())){
						cbcpayldaddress.setCountryCode(findCountryCodeConvertStringToBigInt(addressVo.getCountryCode()));
						}
						if(!StringUtils.isEmpty(addressVo.getCountrySubentity())){
						cbcpayldaddress.setCountrySubentity(addressVo.getCountrySubentity());
						}
						
						cbcpayldaddress.setCreateDateTime(new Date());
						if(!StringUtils.isEmpty(addressVo.getDistrictName())){
						cbcpayldaddress.setDistrictName(addressVo.getDistrictName());
						}
						if(!StringUtils.isEmpty(addressVo.getFloorIdentifier())){
						cbcpayldaddress.setFloorIdentifier(addressVo.getFloorIdentifier());
						}
						if(!StringUtils.isEmpty(addressVo.getAddressType())){
						cbcpayldaddress.setLegalAddressType(addressVo.getAddressType());
						}
						if(!StringUtils.isEmpty(addressVo.getPob())){
						cbcpayldaddress.setPob(addressVo.getPob());
						}
						if(!StringUtils.isEmpty(addressVo.getPostCode())){
						cbcpayldaddress.setPostCode(addressVo.getPostCode());
						}
						
						cbcpayldaddress.setSrcType("1");//TODO
						if(!StringUtils.isEmpty(addressVo.getStreet())){
						cbcpayldaddress.setStreet(addressVo.getStreet());
						}
						if(!StringUtils.isEmpty(addressVo.getSuitIdentifier())){
						cbcpayldaddress.setSuiteIdentifier(addressVo.getSuitIdentifier());
						}
						cbcpayldaddress.setIsdeleted(0);
						cbcpayldaddress = cbcpayldaddressRepository.saveAndFlush(cbcpayldaddress);
						logger.info("cbcpayldaddress ID::::::>"+cbcpayldaddress.getId());
						
					}
				}	
					
				
			}
			
		}
		
		
		/**
		 * Additional Info
		 */
		if(hidefVo.getAddInfoList() != null && hidefVo.getAddInfoList().size()>0 && payldhdr != null && payldhdr.getId() != null
				&& cbcpayldbody != null && cbcpayldbody.getId() != null){
			logger.info("Additional Info Extra Fields TABLE:[cbcpayldaddinfo]");
			for(CbcAdditionalInfo addinfo : hidefVo.getAddInfoList()){
				Cbcpayldaddinfo sddInfo = null;
				if(addinfo.getId() > 0){
				sddInfo = cbcpayldaddinfoRepository.getAllAdditionalInfoById(BigInteger.valueOf(addinfo.getId()));
				}
				if(sddInfo == null){
					sddInfo = new Cbcpayldaddinfo();
				}
				sddInfo.setBodyID(cbcpayldbody.getId());
				if(!StringUtils.isEmpty(addinfo.getCorDocumentRefId())){
				sddInfo.setCorrDocRefId(addinfo.getCorDocumentRefId());
				}
				
				sddInfo.setCreateDateTime(new Date());
				if(!StringUtils.isEmpty(addinfo.getDocumentReferenceId())){
				sddInfo.setDocRefId(addinfo.getDocumentReferenceId());
				}
				if(!StringUtils.isEmpty(addinfo.getDocumentTypeIndic())){
				sddInfo.setDocTypeIndic(addinfo.getDocumentTypeIndic());
				}
				sddInfo.setHdrID(payldhdr.getId());
				if(!StringUtils.isEmpty(addinfo.getOtherInfo())){
				sddInfo.setOtherInfo(addinfo.getOtherInfo());
				}
				sddInfo.setIsdeleted(0);
				sddInfo = cbcpayldaddinfoRepository.saveAndFlush(sddInfo);
				logger.info("cbcpayldaddinfo ID::::::>"+sddInfo.getId());
				
				
				/**
				 * AdditionalInfo Resident Country Code Grid
				 */
				if(addinfo.getResidentCountryList() != null && 
						addinfo.getResidentCountryList().size() > 0){
					logger.info("Additional ResidentCountry TABLE:[cbcpayldrescountry]");
					for(ResidentCountryVo residentCountry:addinfo.getResidentCountryList()){
						Cbcpayldrescountry cbcpayldrescountry = null;
						if(residentCountry.getId() > 0){
							cbcpayldrescountry =cbcpayldrescountryRepository.getAllPayldResidentCountryById(BigInteger.valueOf(residentCountry.getId()));
						}
						if(cbcpayldrescountry == null){
							cbcpayldrescountry = new Cbcpayldrescountry();
						}
						cbcpayldrescountry.setCreateDateTime(new Date());
						cbcpayldrescountry.setObjectID(sddInfo.getId());
						cbcpayldrescountry.setResCountryCode(findCountryCodeById(BigInteger.valueOf(residentCountry.getId())));//TODO
						cbcpayldrescountry.setSrcType("1");//TODO
						cbcpayldrescountry.setIsdeleted(0);
						cbcpayldrescountry = cbcpayldrescountryRepository.saveAndFlush(cbcpayldrescountry);
						logger.info("cbcpayldrescountry ID::::::>"+cbcpayldrescountry.getId());
						
					}
				}
				
				/**
				 * AdditionalInfo Summary Grid
				 */
				if(addinfo.getSummaryList() != null && 
						addinfo.getSummaryList().size() > 0){
					logger.info("Additional info Summary Ref TABLE:[cbcpayldsumref]");
					for(SummaryVo summary : addinfo.getSummaryList()){
						Cbcpayldsumref summaryRef = null;
						if(summary.getId() > 0){
						summaryRef = cbcpayldsumrefRepository.getAllSummaryById(BigInteger.valueOf(summary.getId()));
						}
						if(summaryRef == null){
							summaryRef = new Cbcpayldsumref();
						}
						summaryRef.setAddinfoID(sddInfo.getId());
						summaryRef.setCreateDateTime(new Date());
						summaryRef.setSummaryRef(commonDropDownService.findSummaryReferenceById(summary.getId()).getRefType());
						summaryRef.setIsdeleted(0);
						summaryRef = cbcpayldsumrefRepository.saveAndFlush(summaryRef);
						logger.info("cbcpayldsumref ID::::::>"+summaryRef.getId());
					}
					
				}
				
				
			}
			
			
			
		}
		
		
		
		
	
		}//metadata
		
		
		}//hidef
		
		
		
		
		
		
		
		
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
	public HidefVo deleteSummaryGrid(HidefVo hidefvo,BigInteger hrdId) {
		// TODO Auto-generated method stub
		logger.info("HrdId::::::::::>"+hrdId);
		int cpayhdr = cbcpayldhdrRepository.setIsdeleted(1, hrdId);
		return hidefvo;
	}

	@Override
	public HidefVo getUserProfileByMycbcId(HidefVo hidef) {
		// TODO Auto-generated method stub
		List<Userprofile> userProfileList = userprofileRepository.getUserprofileDetailsByMycbcId(hidef.getMycbcId());
		if(userProfileList != null && userProfileList.size() >0){
			Userprofile userProfile = userProfileList.get(0);
			UserProfileVo  userProfileVo = new UserProfileVo();
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
			List<Usereceivingcountry> receivingCountryList = usereceivingcountryRepository.getAllUserReceivingCountryById(userProfile);
			for(Usereceivingcountry receiving : receivingCountryList){
				RecievingCountryVo recevingCountryVo = new RecievingCountryVo();
				recevingCountryVo.setId(receiving.getId().intValue());
				if(receiving.getReceivingCountry() != null){
				recevingCountryVo.setRecievingCountry(Integer.parseInt(receiving.getReceivingCountry()));
				}
				recievingCountryList.add(recevingCountryVo);
			}
			userProfileVo.setRecievingCountryList(recievingCountryList);
			logger.info("@@@@@@@@@@@@@@");
			
			
			hidef.setUserprofile(userProfileVo);
			
			
			
			/*cbcpayldhdrRepository.getAllCbcDetails(hidef.getMycbcId());*/
		}
		return hidef;
	}

	@Override
	public Docrefid saveDocRefIdDetails(String date,String communicationType) {
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
	
	
}
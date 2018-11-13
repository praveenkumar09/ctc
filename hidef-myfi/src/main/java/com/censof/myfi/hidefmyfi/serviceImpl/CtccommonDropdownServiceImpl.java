package com.censof.myfi.hidefmyfi.serviceImpl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.censof.myfi.hidefmyfi.entity.CbcCurrency;
import com.censof.myfi.hidefmyfi.entity.Cbcaddresstype;
import com.censof.myfi.hidefmyfi.entity.Cbcbinaryencodingschemecd;
import com.censof.myfi.hidefmyfi.entity.Cbcbizactivitiesreference;
import com.censof.myfi.hidefmyfi.entity.Cbcdocumenttypeindicator;
import com.censof.myfi.hidefmyfi.entity.Cbcfileformatcd;
import com.censof.myfi.hidefmyfi.entity.Cbcfiletypeindic;
import com.censof.myfi.hidefmyfi.entity.Cbcmessagetypeindic;
import com.censof.myfi.hidefmyfi.entity.Cbcnametype;
import com.censof.myfi.hidefmyfi.entity.Cbcreportingrole;
import com.censof.myfi.hidefmyfi.entity.Cbcsummaryreference;
import com.censof.myfi.hidefmyfi.entity.Crsmessagetypeindic;
import com.censof.myfi.hidefmyfi.entity.Ctscommunicationtypecd;
import com.censof.myfi.hidefmyfi.entity.Docrefid;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.entity.Messagerefid;
import com.censof.myfi.hidefmyfi.entity.Senderfileid;
import com.censof.myfi.hidefmyfi.repository.CBCBinaryEncodingRepository;
import com.censof.myfi.hidefmyfi.repository.CbcAddressTypeRepository;
import com.censof.myfi.hidefmyfi.repository.CbcCurrencyRepository;
import com.censof.myfi.hidefmyfi.repository.CbcFileTypeIndicRepository;
import com.censof.myfi.hidefmyfi.repository.CbcbizactivitiesreferenceRepository;
import com.censof.myfi.hidefmyfi.repository.CbcmessagetypeindicRepository;
import com.censof.myfi.hidefmyfi.repository.CbcsummaryreferenceRepository;
import com.censof.myfi.hidefmyfi.repository.CommunicationTypeRepository;
import com.censof.myfi.hidefmyfi.repository.CrsmessagetypeindicRepository;
import com.censof.myfi.hidefmyfi.repository.DocrefidRepository;
import com.censof.myfi.hidefmyfi.repository.DocumentTypeIndicatorRepository;
import com.censof.myfi.hidefmyfi.repository.FileFormatCdRepository;
import com.censof.myfi.hidefmyfi.repository.HiCountryRepository;
import com.censof.myfi.hidefmyfi.repository.MessagerefidRepository;
import com.censof.myfi.hidefmyfi.repository.NameTypeRepository;
import com.censof.myfi.hidefmyfi.repository.ReportingRoleTypeRepository;
import com.censof.myfi.hidefmyfi.repository.SenderfileidRepository;
import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;

@Service("ctccommonDropdownService")
public class CtccommonDropdownServiceImpl implements CtccommonDropdownService{

	@Autowired
	private HiCountryRepository hiCountryRepository;
	
	@Autowired
	private CbcAddressTypeRepository cbcAddressTypeRepository;
	
	@Autowired
	private DocumentTypeIndicatorRepository documentTypeIndicatorRepository;
	
	@Autowired
	private ReportingRoleTypeRepository reportingRoleTypeRepository;
	
	@Autowired
	private NameTypeRepository nameTypeRepository;
	
	@Autowired
	private FileFormatCdRepository fileFormatCdRepository;
	
	@Autowired
	private CBCBinaryEncodingRepository cBCBinaryEncodingRepository;
	
	@Autowired
	private CommunicationTypeRepository communicationTypeRepository;
	
	@Autowired
	private CbcsummaryreferenceRepository cbcsummaryreferenceRepository;
	
	@Autowired
	private CbcFileTypeIndicRepository cbcFileTypeIndicRepository;
	
	@Autowired
	private CbcbizactivitiesreferenceRepository cbcbizactivitiesreferenceRepository;
	
	@Autowired
	private CbcCurrencyRepository cbcCurrencyRepository;
	
	@Autowired
	private DocrefidRepository docrefidRepository;
	
	@Autowired
	private MessagerefidRepository messageRefIdRepository;
	
	@Autowired
	private SenderfileidRepository senderFileIdRepository;
	
	@Autowired
	private CbcmessagetypeindicRepository cbcmessagetypeindicRepository;
	
	@Autowired
	private CrsmessagetypeindicRepository crsmessagetypeindicRepository;
	
	
	@Override
	public List<Hicountry> getAllCountries() {
		// TODO Auto-generated method stub
		return hiCountryRepository.findAllCountry();	}


	@Override
	public List<Cbcaddresstype> getAllAddressType() {
		// TODO Auto-generated method stub
		return cbcAddressTypeRepository.findAllAddressTypes();
	}


	@Override
	public List<Cbcdocumenttypeindicator> getAllDocumentTypeIndicator() {
		// TODO Auto-generated method stub
		return documentTypeIndicatorRepository.findAllReportingTypeIndicator();
	}


	@Override
	public List<Cbcreportingrole> getAllReportingRoleType() {
		// TODO Auto-generated method stub
		return reportingRoleTypeRepository.findAllReportingTypes();
	}


	@Override
	public List<Cbcnametype> getAllNameType() {
		// TODO Auto-generated method stub
		return nameTypeRepository.findAllNameTypes();
	}


	@Override
	public List<Cbcbinaryencodingschemecd> getAllBinaryEncodingSchemesType() {
		// TODO Auto-generated method stub
		return cBCBinaryEncodingRepository.findAllBinaryencodingSchemeTypes();
	}


	@Override
	public List<Cbcfileformatcd> getAllFileFormatCdType() {
		// TODO Auto-generated method stub
		return fileFormatCdRepository.findAllFileFormatCdTypes();
	}


	@Override
	public List<Ctscommunicationtypecd> getAllCommunicationTypeCd() {
		// TODO Auto-generated method stub
		return communicationTypeRepository.findAllCommunicationTypes();
	}


	@Override
	public List<Cbcsummaryreference> getAllCbcSummaryReference() {
		// TODO Auto-generated method stub
		return cbcsummaryreferenceRepository.findAllSummaryReference();
	}


	@Override
	public Hicountry findCountryById(BigInteger id) {
		// TODO Auto-generated method stub
		Hicountry country = null;
		Optional<Hicountry> optionalCountry = hiCountryRepository.findById(id);
		if(optionalCountry.isPresent()) {
			country = optionalCountry.get();
		}
		return country;
	}


	@Override
	public Ctscommunicationtypecd findCtscommunicationtypecdbyId(String id) {
		// TODO Auto-generated method stub
		Ctscommunicationtypecd commmunicationType = null;
		Optional<Ctscommunicationtypecd> communicationTypeOptional = communicationTypeRepository.findById(new BigInteger(id));
		if(communicationTypeOptional.isPresent()) {
			commmunicationType = communicationTypeOptional.get();
		}
		return commmunicationType;
	}


	@Override
	public List<Cbcfiletypeindic> getAllFileTypeIndic() {
		// TODO Auto-generated method stub
		return cbcFileTypeIndicRepository.findAll();
	}


	@Override
	public List<Cbcbizactivitiesreference> getAllCbcbizactivitiesreference() {
		// TODO Auto-generated method stub
		return cbcbizactivitiesreferenceRepository.findAllBizActivitiesTypes();
	}


	@Override
	public List<CbcCurrency> getAllCurrencyreference() {
		// TODO Auto-generated method stub
		return cbcCurrencyRepository.findAllCurrency();
	}
	
	@Override
	public Cbcbizactivitiesreference findBizActivitiesById(int id) {
		// TODO Auto-generated method stub
		Cbcbizactivitiesreference bizreference = null;
		Optional<Cbcbizactivitiesreference> optionalBiz = cbcbizactivitiesreferenceRepository.findById(BigInteger.valueOf(id));
		if(optionalBiz.isPresent()) {
			bizreference = optionalBiz.get();
		}
		return bizreference;
	}


	@Override
	public Cbcsummaryreference findSummaryReferenceById(int id) {
		// TODO Auto-generated method stub
		Cbcsummaryreference cbcSummaryReference = null;
		Optional<Cbcsummaryreference> optionalSummaryReference = cbcsummaryreferenceRepository.findById(BigInteger.valueOf(id));
		if(optionalSummaryReference.isPresent()) {
			cbcSummaryReference = optionalSummaryReference.get();
		}
		return cbcSummaryReference;
	}


	@Override
	public Cbcfileformatcd findFileFormatCodeById(int id) {
		// TODO Auto-generated method stub
		Cbcfileformatcd fileFormatCodeOb = null;
		Optional<Cbcfileformatcd> optionalFileFormatCode = fileFormatCdRepository.findById(BigInteger.valueOf(id));
		if(optionalFileFormatCode.isPresent()) {
			fileFormatCodeOb = optionalFileFormatCode.get();
		}
		return fileFormatCodeOb;
	}


	@Override
	public Cbcbinaryencodingschemecd findBinaryEncodingSchemeById(int id) {
		// TODO Auto-generated method stub
		Cbcbinaryencodingschemecd binaryEncodingScheemeObject = null;
		Optional<Cbcbinaryencodingschemecd> binaryOptionOb = cBCBinaryEncodingRepository.findById(BigInteger.valueOf(id));
		if(binaryOptionOb.isPresent()) {
			binaryEncodingScheemeObject = binaryOptionOb.get();
		}
		
		return binaryEncodingScheemeObject;
	}


	@Override
	public CbcCurrency findCurrencyById(int id) {
		// TODO Auto-generated method stub
		CbcCurrency cbcCurrency = null;
		Optional<CbcCurrency> optionCurrencyObject = cbcCurrencyRepository.findById(id);
		if(optionCurrencyObject.isPresent()) {
			cbcCurrency = optionCurrencyObject.get();
		}
		return cbcCurrency;
	}


	@Override
	public Docrefid findDocRefIdByDate(String date) {
		// TODO Auto-generated method stub
		Docrefid docRefId = docrefidRepository.getAllDocrefIdDetailsByDate(date);
		return docRefId;
	}


	@Override
	public Messagerefid findMessageRefIdByDate(String date) {
		// TODO Auto-generated method stub
		Messagerefid messageRefId = messageRefIdRepository.findByDate(date);
		return messageRefId;
	}


	@Override
	public Senderfileid findSenderFileIdByDate(String date) {
		// TODO Auto-generated method stub
		Senderfileid senderFileId = senderFileIdRepository.findByDate(date);
		return senderFileId;
	}


	@Override
	public List<Cbcmessagetypeindic> findAllCbcmessagetypeindic() {
		// TODO Auto-generated method stub
		List<Cbcmessagetypeindic> messageTypeIndic = cbcmessagetypeindicRepository.findAllMessageTypeIndicator();
		return messageTypeIndic;
	}


	@Override
	public List<Crsmessagetypeindic> findAllCrsMessageTypeIndic() {
		// TODO Auto-generated method stub
		List<Crsmessagetypeindic> crsMessageTypeIndic = crsmessagetypeindicRepository.findAllCrsMessageTypeIndic();
		return crsMessageTypeIndic;
	}

}

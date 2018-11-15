package com.censof.myfi.hidefmyfi.service;

import java.math.BigInteger;
import java.util.List;

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
import com.censof.myfi.hidefmyfi.entity.Messagetype;
import com.censof.myfi.hidefmyfi.entity.Senderfileid;

public interface CtccommonDropdownService {
	public List<Hicountry> getAllCountries();
	public List<Cbcaddresstype> getAllAddressType();
	public List<Cbcdocumenttypeindicator> getAllDocumentTypeIndicator();
	public List<Cbcreportingrole> getAllReportingRoleType();
	public List<Cbcnametype> getAllNameType();
	public List<Cbcfiletypeindic> getAllFileTypeIndic();
	
	public List<Cbcbinaryencodingschemecd> getAllBinaryEncodingSchemesType();
	public List<Cbcfileformatcd> getAllFileFormatCdType();
	public List<Ctscommunicationtypecd> getAllCommunicationTypeCd();
	public List<Cbcsummaryreference> getAllCbcSummaryReference();
	public List<Cbcbizactivitiesreference> getAllCbcbizactivitiesreference();
	
	public List<CbcCurrency> getAllCurrencyreference();
	
	
	public Hicountry findCountryById(BigInteger id);
	public Ctscommunicationtypecd findCtscommunicationtypecdbyId(String id);
	public Cbcbizactivitiesreference findBizActivitiesById(int id);
	public Cbcsummaryreference findSummaryReferenceById(int id);
	public Cbcfileformatcd findFileFormatCodeById(int id);
	public Cbcbinaryencodingschemecd findBinaryEncodingSchemeById(int id);
	public CbcCurrency findCurrencyById(int id);
	
	public Docrefid findDocRefIdByDate(String date);
	
	public Messagerefid findMessageRefIdByDate(String date);
	
	public Senderfileid findSenderFileIdByDate(String date);
	public List<Cbcmessagetypeindic> findAllCbcmessagetypeindic();
	public List<Crsmessagetypeindic> findAllCrsMessageTypeIndic();
	public List<Messagetype> findAllMessageTypes();
}

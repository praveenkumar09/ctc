package com.censof.myfi.hidefmyfi.service;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;

import com.censof.myfi.hidefmyfi.entity.Docrefid;
import com.censof.myfi.hidefmyfi.entity.Messagerefid;
import com.censof.myfi.hidefmyfi.entity.Senderfileid;
import com.censof.myfi.hidefmyfi.vo.HidefVo;

public interface CtcDataSaveService {
	
	public HidefVo saveCtcData(HidefVo hidefVo);
	public HidefVo getAllDatabyCBCId(HidefVo hidefvo);
	public HidefVo viewAllDatabyCBCId(HidefVo hidefvo,String id);
	public HidefVo saveUserprofileData(HidefVo hidefvo);
	public HidefVo saveExcelFile(HidefVo hidef) throws IllegalStateException, IOException, ParseException;
	public HidefVo saveCtcExcelData(HidefVo hidefVo);
	
	public HidefVo deleteSummaryGrid(HidefVo hidefvo,BigInteger hrdId);
	public HidefVo getUserProfileByMycbcId(HidefVo hidef);
	public Docrefid saveDocRefIdDetails(String date,String communicationType);
	public Messagerefid saveMessageRefId(String date,String communicationType);
	public Senderfileid saveSenderFileId(String date,String communicationType);
	
	public void deleteUserProfileRecievingCountry(BigInteger userProfileId,int receivingCountryId);
	
	public HidefVo savecrsData(HidefVo hidefVo);
	public HidefVo getAllDatabyCRSId(HidefVo hidefvo);
	public HidefVo viewAllDatabyCRSId(HidefVo hidefvo, String id);
	
	public HidefVo saveCrsExcelFile(HidefVo hidef) throws IllegalStateException, IOException, ParseException;
	public HidefVo saveCrsCtcExcelData(HidefVo hidefVo);
	
	

}

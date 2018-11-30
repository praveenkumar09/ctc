package com.censof.myfi.hidefmyfi.service;

import java.io.IOException;
import java.math.BigInteger;

import com.censof.myfi.hidefmyfi.entity.Docrefid;
import com.censof.myfi.hidefmyfi.entity.Messagerefid;
import com.censof.myfi.hidefmyfi.entity.Senderfileid;
import com.censof.myfi.hidefmyfi.vo.HidefVo;

public interface CtcDataSaveService {
	
	public HidefVo saveCtcData(HidefVo hidefVo);
	public HidefVo getAllDatabyCBCId(HidefVo hidefvo);
	public HidefVo viewAllDatabyCBCId(HidefVo hidefvo,String id);
	public HidefVo saveUserprofileData(HidefVo hidefvo);
	public HidefVo saveExcelFile(HidefVo hidef) throws IllegalStateException, IOException;
	public HidefVo saveCtcExcelData(HidefVo hidefVo);
	
	public HidefVo deleteSummaryGrid(HidefVo hidefvo,BigInteger hrdId);
	public HidefVo getUserProfileByMycbcId(HidefVo hidef);
	public Docrefid saveDocRefIdDetails(String date,String communicationType);
	public Messagerefid saveMessageRefId(String date,String communicationType);
	public Senderfileid saveSenderFileId(String date,String communicationType);
	
<<<<<<< HEAD
	public void deleteUserProfileRecievingCountry(BigInteger userProfileId,int receivingCountryId);
	
=======
>>>>>>> branch 'master' of https://github.com/praveenkumar09/ctc.git
	public HidefVo savecrsData(HidefVo hidefVo);
	

}

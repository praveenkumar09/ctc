package com.censof.myfi.hidefmyfi.service;

import java.io.IOException;

import com.censof.myfi.hidefmyfi.vo.HidefVo;

public interface PackageGenerationService {
	
	public String generateCBCXMLMetData(HidefVo hidef) throws IOException;
	public String generateCBCXMLPayload(HidefVo hidef) throws IOException;
	public String generateCBCPackage(HidefVo hidef) throws Exception;
	
	public String generateCRSXMLMetadata(HidefVo hidef)throws IOException;
	public String generateCRSXMLPayload(HidefVo hidef)throws IOException;
	public String generateCRSPackage(HidefVo hidef) throws Exception;
	
	public void writetoExcelFile(HidefVo hidef,String excelLocation) throws Exception;
	public void crsWritetoExcelFile(HidefVo hidef,String excelLocation) throws Exception;

}

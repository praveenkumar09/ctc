package com.censof.myfi.hidefmyfi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.censof.myfi.hidefmyfi.service.CtccommonDropdownService;
import com.censof.myfi.hidefmyfi.vo.AddressVo;
import com.censof.myfi.hidefmyfi.vo.BizActivitiesTypeVo;
import com.censof.myfi.hidefmyfi.vo.CBCRepotsVo;
import com.censof.myfi.hidefmyfi.vo.CbcAdditionalInfo;
import com.censof.myfi.hidefmyfi.vo.CbcConstituentEntityVO;
import com.censof.myfi.hidefmyfi.vo.CrsMetadataVo;
import com.censof.myfi.hidefmyfi.vo.CrsReportingFiVo;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.MetaDataVo;
import com.censof.myfi.hidefmyfi.vo.NameVo;
import com.censof.myfi.hidefmyfi.vo.OrganisationInTypeVo;
import com.censof.myfi.hidefmyfi.vo.RecievingCountryVo;
import com.censof.myfi.hidefmyfi.vo.ReportingEntityVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.censof.myfi.hidefmyfi.vo.SummaryVo;
import com.censof.myfi.hidefmyfi.vo.UserProfileVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { HidefMyfiApplication.class })
public class HidefMyfiApplicationTests {

	@Autowired
	CtccommonDropdownService dropDownService;

	@SuppressWarnings("deprecation")
	@Test
	public void contextLoads() throws IOException {
		HidefVo hidefVo = new HidefVo();
		File file = new File("C:\\CTCTemplate\\SampleTemplate.xlsx");
		FileInputStream excelFile = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(excelFile);
		
		
		
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
						
						if (reports == null) {
							reports = new CBCRepotsVo();
							reports.setExcelId(reports.getExcelId()+1);
							if(reports.getConstituentEntity() == null) {
								reports.setConstituentEntity(new CbcConstituentEntityVO());
								reports.setConstituentEntityList(new ArrayList<CbcConstituentEntityVO>());
								reports.getConstituentEntity().setExcelId(reports.getConstituentEntity().getExcelId()+1);
							}
						}
						
						
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
							reports.getConstituentEntity().setTin(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.getConstituentEntity().setTin("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 25) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.getConstituentEntity().setIssuedBy(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.getConstituentEntity().setIssuedBy("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 26) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.getConstituentEntity().setTinType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.getConstituentEntity().setTinType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 27) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.getConstituentEntity().setIncorpCountryCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.getConstituentEntity().setIncorpCountryCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 28) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							reports.getConstituentEntity().setOtherEntityInfo(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.getConstituentEntity().setOtherEntityInfo("" + Math.round(currentCell.getNumericCellValue()));
						}
					}

					if (currentCell.getColumnIndex() == 29) {
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

					if (currentCell.getColumnIndex() == 30) {
						if (residentVo == null) {
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

					if (currentCell.getColumnIndex() == 32) {
						if (orgINVo == null) {
							orgINVo = new OrganisationInTypeVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setIn(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setIn("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 33) {
						if (orgINVo == null) {
							orgINVo = new OrganisationInTypeVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setInType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setInType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 34) {
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

					if (currentCell.getColumnIndex() == 35) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCountryCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 36) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setAddressType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 37) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setAddressFree(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 38) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setStreet(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 39) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setBuildingIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 40) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setSuitIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 41) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setFloorIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 42) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setDistrictName(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 43) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setPob(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 44) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setPostCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 45) {
						if (addressVo == null) {
							addressVo = new AddressVo();
						}
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCity(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 46) {
						if (addressVo == null) {
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
			
			if(reports != null && reports.getConstituentEntity() != null) {
				
				Iterator<CbcConstituentEntityVO> iteratorCE = reports.getConstituentEntityList().iterator();
				
				while(iteratorCE.hasNext()) {
					CbcConstituentEntityVO ceIter = iteratorCE.next();
					if(ceIter.getExcelId() == reports.getConstituentEntity().getExcelId()) {
						iteratorCE.remove();
					}
				}
				
				if(reports.getConstituentEntity().getExcelId() != 0) {
				reports.getConstituentEntityList().add(reports.getConstituentEntity());
				}
			}

			if (reports != null) {					
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
			}
		}
	}
		
		System.out.println(hidefVo);
		
		
		/*

		String fileName = "D:\\CTC\\To Upload\\CRS\\CRS.xlsx";
		HidefVo hidefVo = new HidefVo();
		FileInputStream excelFile = new FileInputStream(new File(fileName));
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
							} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
								hidefVo.getCrsmetadata().setSendingCompanyIn(currentCell.getStringCellValue());
							}
						} else if (currentRow.getRowNum() == 18) {
							hidefVo.getCrsmetadata().setLanguage(currentCell.getStringCellValue());
						} else if (currentRow.getRowNum() == 18) {
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
				NameVo nameVo = new NameVo();
				ResidentCountryVo residentVo = new ResidentCountryVo();
				OrganisationInTypeVo orgINVo = new OrganisationInTypeVo();
				AddressVo addressVo = new AddressVo();
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
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							nameVo.setFirstName(currentCell.getStringCellValue());							
						}
					}
					if (currentCell.getColumnIndex() == 6) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							nameVo.setNameType(Integer.parseInt(currentCell.getStringCellValue()));							
						}else if(currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							nameVo.setNameType( Math.toIntExact(Math.round(currentCell.getNumericCellValue())));	
						}
					}
					if (currentCell.getColumnIndex() == 7) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setIn(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setIn("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 8) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setInType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setInType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 9) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setIssuedBy(Integer.parseInt(currentCell.getStringCellValue()));
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setIssuedBy(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
						}
					}
					
					if (currentCell.getColumnIndex() == 10) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCountryCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 11) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setAddressType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 12) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setAddressFree(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 13) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setStreet(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 14) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setBuildingIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 15) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setSuitIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 16) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setFloorIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 17) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setDistrictName(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 18) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setPob(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 19) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setPostCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 20) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCity(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 21) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCountrySubentity(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCountrySubentity("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					
				}
				if (hidefVo.getCrsreportingfi().getNameList() != null
						&& !hidefVo.getCrsreportingfi().getNameList().isEmpty()) {
					hidefVo.getCrsreportingfi().getNameList().add(nameVo);
				} else {
					hidefVo.getCrsreportingfi().setNameList(new ArrayList<NameVo>());
					hidefVo.getCrsreportingfi().getNameList().add(nameVo);
				}
				
				if (hidefVo.getCrsreportingfi().getResidentCountryList() != null
						&& !hidefVo.getCrsreportingfi().getResidentCountryList().isEmpty()) {
					hidefVo.getCrsreportingfi().getResidentCountryList().add(residentVo);
				} else {
					hidefVo.getCrsreportingfi().setResidentCountryList(new ArrayList<ResidentCountryVo>());
					hidefVo.getCrsreportingfi().getResidentCountryList().add(residentVo);
				}
				
				if (hidefVo.getCrsreportingfi().getOrganisationInTypeList() != null
						&& !hidefVo.getCrsreportingfi().getOrganisationInTypeList().isEmpty()) {
					hidefVo.getCrsreportingfi().getOrganisationInTypeList().add(orgINVo);
				} else {
					hidefVo.getCrsreportingfi().setOrganisationInTypeList(new ArrayList<OrganisationInTypeVo>());
					hidefVo.getCrsreportingfi().getOrganisationInTypeList().add(orgINVo);
				}
				
				if (hidefVo.getCrsreportingfi().getAddressList() != null
						&& !hidefVo.getCrsreportingfi().getAddressList().isEmpty()) {
					hidefVo.getCrsreportingfi().getAddressList().add(addressVo);
				} else {
					hidefVo.getCrsreportingfi().setAddressList(new ArrayList<AddressVo>());
					hidefVo.getCrsreportingfi().getAddressList().add(addressVo);
				}
			
			}
		}
		
		

		

		System.out.println("Metadata Object =====> " + hidefVo);

	*/}
	
	/*// reporting Entity Sheet - data pick up code
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

				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentCell.getColumnIndex() == 0) {
						if (tinLength == 0) {
							if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								hidefVo.getReportingEntity().setTin("" + Math.round(currentCell.getNumericCellValue()));
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
								hidefVo.getReportingEntity()
										.setDocumentTypeIndicator("" + Math.round(currentCell.getNumericCellValue()));
							} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
								hidefVo.getReportingEntity().setDocumentTypeIndicator(currentCell.getStringCellValue());
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
								hidefVo.getReportingEntity().setDocumentReferenceId(currentCell.getStringCellValue());
							}
							docReferenceId += 1;
						}
					}
					if (currentCell.getColumnIndex() == 6) {
						if (corMessageRefId == 0) {
							if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
								hidefVo.getReportingEntity()
										.setCorMessageReferenceId("" + Math.round(currentCell.getNumericCellValue()));
							} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
								hidefVo.getReportingEntity().setCorMessageReferenceId(currentCell.getStringCellValue());
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
						if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							ResidentCountryVo residentVo = new ResidentCountryVo();
							int residentId = Math.toIntExact(Math.round(currentCell.getNumericCellValue()));
							residentVo.setId(residentId);
							residentVo.setResidentCountryCode(residentId);
							if (hidefVo.getReportingEntity().getResidentCountryList() != null
									&& !hidefVo.getReportingEntity().getResidentCountryList().isEmpty()) {
								hidefVo.getReportingEntity().getResidentCountryList().add(residentVo);
							} else {
								hidefVo.getReportingEntity().setResidentCountryList(new ArrayList<ResidentCountryVo>());
								hidefVo.getReportingEntity().getResidentCountryList().add(residentVo);
							}
						} else if (currentCell.getCellTypeEnum() == CellType.STRING) {
							ResidentCountryVo residentVo = new ResidentCountryVo();
							int residentId = Integer.parseInt(currentCell.getStringCellValue());
							residentVo.setId(residentId);
							residentVo.setResidentCountryCode(residentId);
							if (hidefVo.getReportingEntity().getResidentCountryList() != null
									&& !hidefVo.getReportingEntity().getResidentCountryList().isEmpty()) {
								hidefVo.getReportingEntity().getResidentCountryList().add(residentVo);
							} else {
								hidefVo.getReportingEntity().setResidentCountryList(new ArrayList<ResidentCountryVo>());
								hidefVo.getReportingEntity().getResidentCountryList().add(residentVo);
							}
						}
					}
					if (currentCell.getColumnIndex() == 9) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							NameVo nameVo = new NameVo();
							nameVo.setFirstName(currentCell.getStringCellValue());
							if (hidefVo.getReportingEntity().getNameList() != null
									&& !hidefVo.getReportingEntity().getNameList().isEmpty()) {
								hidefVo.getReportingEntity().getNameList().add(nameVo);
							} else {
								hidefVo.getReportingEntity().setNameList(new ArrayList<NameVo>());
								hidefVo.getReportingEntity().getNameList().add(nameVo);
							}
						}
					}
					OrganisationInTypeVo orgINVo = new OrganisationInTypeVo();
					if (currentCell.getColumnIndex() == 10) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setIn(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setIn("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 11) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setInType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setInType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 12) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setIssuedBy(Integer.parseInt(currentCell.getStringCellValue()));
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setIssuedBy(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
						}
					}

					if (hidefVo.getReportingEntity().getOrganisationInTypeList() != null
							&& !hidefVo.getReportingEntity().getOrganisationInTypeList().isEmpty()) {
						hidefVo.getReportingEntity().getOrganisationInTypeList().add(orgINVo);
					} else {
						hidefVo.getReportingEntity().setOrganisationInTypeList(new ArrayList<OrganisationInTypeVo>());
						hidefVo.getReportingEntity().getOrganisationInTypeList().add(orgINVo);
					}

					AddressVo addressVo = new AddressVo();
					if (currentCell.getColumnIndex() == 13) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCountryCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 14) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setAddressType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 15) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setAddressFree(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 16) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setStreet(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 17) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setBuildingIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 18) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setSuitIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 19) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setFloorIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 20) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setDistrictName(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 21) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setPob(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 22) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setPostCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 23) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCity(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 24) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCountrySubentity(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCountrySubentity("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
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
				CBCRepotsVo reports = new CBCRepotsVo();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
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
					
					BizActivitiesTypeVo bizActivitiesVo = new BizActivitiesTypeVo();
					if (currentCell.getColumnIndex() == 29) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							bizActivitiesVo.setId(Integer.parseInt(currentCell.getStringCellValue()));
							if(reports.getBizActivitiesList() != null && !reports.getBizActivitiesList().isEmpty()) {
								reports.getBizActivitiesList().add(bizActivitiesVo);
							}else {
								reports.setBizActivitiesList(new ArrayList<BizActivitiesTypeVo>());
								reports.getBizActivitiesList().add(bizActivitiesVo);
							}
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							reports.setOtherEntityInfo("" + Math.round(currentCell.getNumericCellValue()));
							bizActivitiesVo.setId(Integer.parseInt(""+currentCell.getNumericCellValue()));
							if(reports.getBizActivitiesList() != null && !reports.getBizActivitiesList().isEmpty()) {
								reports.getBizActivitiesList().add(bizActivitiesVo);
							}else {
								reports.setBizActivitiesList(new ArrayList<BizActivitiesTypeVo>());
								reports.getBizActivitiesList().add(bizActivitiesVo);
							}
						}
					}
					
					if (currentCell.getColumnIndex() == 30) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							ResidentCountryVo residentVo = new ResidentCountryVo();
							int residentId = Integer.parseInt(currentCell.getStringCellValue());
							residentVo.setId(residentId);
							residentVo.setResidentCountryCode(residentId);
							reports.setResidentCountryList(new ArrayList<ResidentCountryVo>());
							reports.getResidentCountryList().add(residentVo);
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							ResidentCountryVo residentVo = new ResidentCountryVo();
							int residentId = Integer.parseInt(""+currentCell.getNumericCellValue());
							residentVo.setId(residentId);
							residentVo.setResidentCountryCode(residentId);
							reports.setResidentCountryList(new ArrayList<ResidentCountryVo>());
							reports.getResidentCountryList().add(residentVo);
						}
					}
					if (currentCell.getColumnIndex() == 31) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							NameVo residentVo = new NameVo();
							int residentId = Integer.parseInt(currentCell.getStringCellValue());
							residentVo.setId(residentId);
							residentVo.setFirstName(""+residentId);
							reports.setNameList(new ArrayList<NameVo>());
							reports.getNameList().add(residentVo);
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							NameVo residentVo = new NameVo();
							int residentId = Integer.parseInt(""+currentCell.getNumericCellValue());
							residentVo.setId(residentId);
							residentVo.setFirstName(""+residentId);
							reports.setNameList(new ArrayList<NameVo>());
							reports.getNameList().add(residentVo);
						}
					}
					OrganisationInTypeVo orgINVo = new OrganisationInTypeVo();
					if (currentCell.getColumnIndex() == 32) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setIn(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setIn("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 33) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setInType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setInType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 34) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							orgINVo.setIssuedBy(Integer.parseInt(currentCell.getStringCellValue()));
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							orgINVo.setIssuedBy(Integer.parseInt("" + Math.round(currentCell.getNumericCellValue())));
						}
					}

					if (hidefVo.getReportingEntity().getOrganisationInTypeList() != null
							&& !hidefVo.getReportingEntity().getOrganisationInTypeList().isEmpty()) {
						reports.getOrganisationInTypeList().add(orgINVo);
					} else {
						reports.setOrganisationInTypeList(new ArrayList<OrganisationInTypeVo>());
						reports.getOrganisationInTypeList().add(orgINVo);
					}
					
					AddressVo addressVo = new AddressVo();
					if (currentCell.getColumnIndex() == 35) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCountryCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCountryCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 36) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setAddressType(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setAddressType("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 37) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setAddressFree(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setAddressFree("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 38) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setStreet(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setStreet("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 39) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setBuildingIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setBuildingIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 40) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setSuitIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setSuitIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 41) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setFloorIdentifier(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setFloorIdentifier("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 42) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setDistrictName(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setDistrictName("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 43) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setPob(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setPob("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 44) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setPostCode(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setPostCode("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 45) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCity(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCity("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (currentCell.getColumnIndex() == 46) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							addressVo.setCountrySubentity(currentCell.getStringCellValue());
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							addressVo.setCountrySubentity("" + Math.round(currentCell.getNumericCellValue()));
						}
					}
					if (reports.getAddressList() != null
							&& !reports.getAddressList().isEmpty()) {
						reports.getAddressList().add(addressVo);
					} else {
						reports.setAddressList(new ArrayList<AddressVo>());
						reports.getAddressList().add(addressVo);
					}
					
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
				CbcAdditionalInfo addInfo = new CbcAdditionalInfo();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
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
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							ResidentCountryVo residentVo = new ResidentCountryVo();
							int residentId = Integer.parseInt(currentCell.getStringCellValue());
							residentVo.setId(residentId);
							residentVo.setResidentCountryCode(residentId);
							addInfo.setResidentCountryList(new ArrayList<ResidentCountryVo>());
							addInfo.getResidentCountryList().add(residentVo);
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							ResidentCountryVo residentVo = new ResidentCountryVo();
							int residentId = Integer.parseInt(""+currentCell.getNumericCellValue());
							residentVo.setId(residentId);
							residentVo.setResidentCountryCode(residentId);
							addInfo.setResidentCountryList(new ArrayList<ResidentCountryVo>());
							addInfo.getResidentCountryList().add(residentVo);
						}
					}
					if (currentCell.getColumnIndex() == 7) {
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							SummaryVo summaryVo = new SummaryVo();
							int summaryId = Integer.parseInt(currentCell.getStringCellValue());
							summaryVo.setId(summaryId);
							summaryVo.setSummeryReference(summaryId);
							addInfo.setSummaryList(new ArrayList<SummaryVo>());
							addInfo.getSummaryList().add(summaryVo);
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							SummaryVo summaryVo = new SummaryVo();
							int summaryId = Integer.parseInt(""+currentCell.getNumericCellValue());
							summaryVo.setId(summaryId);
							summaryVo.setSummeryReference(summaryId);
							addInfo.setSummaryList(new ArrayList<SummaryVo>());
							addInfo.getSummaryList().add(summaryVo);
						}
					}

				}
				
				if(hidefVo.getAddInfoList() != null && !hidefVo.getAddInfoList().isEmpty()) {
					hidefVo.getAddInfoList().add(addInfo);
				}else {
					hidefVo.setAddInfoList(new ArrayList<CbcAdditionalInfo>());
					hidefVo.getAddInfoList().add(addInfo);
				}
			}
		}
	  */

}

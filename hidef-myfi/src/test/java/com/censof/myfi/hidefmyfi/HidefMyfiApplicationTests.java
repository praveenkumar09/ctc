package com.censof.myfi.hidefmyfi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import com.censof.myfi.hidefmyfi.vo.AccountHolderVo;
import com.censof.myfi.hidefmyfi.vo.AddressVo;
import com.censof.myfi.hidefmyfi.vo.ControllingPersonVo;
import com.censof.myfi.hidefmyfi.vo.CrsMetadataVo;
import com.censof.myfi.hidefmyfi.vo.CrsReportingFiVo;
import com.censof.myfi.hidefmyfi.vo.GenerationIdentifierVo;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.MiddleNameVo;
import com.censof.myfi.hidefmyfi.vo.NameTypeVo;
import com.censof.myfi.hidefmyfi.vo.NameVo;
import com.censof.myfi.hidefmyfi.vo.OrganisationInTypeVo;
import com.censof.myfi.hidefmyfi.vo.PaymentTypeVo;
import com.censof.myfi.hidefmyfi.vo.RecievingCountryVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.censof.myfi.hidefmyfi.vo.SuffixVo;
import com.censof.myfi.hidefmyfi.vo.TitleVo;
import com.censof.myfi.hidefmyfi.vo.UserProfileVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { HidefMyfiApplication.class })
public class HidefMyfiApplicationTests {

	@Autowired
	CtccommonDropdownService dropDownService;

	@SuppressWarnings("deprecation")
	@Test
	public void contextLoads() throws IOException {
		String fileName = "C:\\CTC\\ctstemplate\\CRS.xlsx";
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
				AddressVo addressVo = null;;
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
		
		

		

		System.out.println("Metadata Object =====> " + hidefVo);
	
		
		
		
		/*HidefVo hidefVo = new HidefVo();
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
		
		System.out.println(hidefVo);*/
		
		
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

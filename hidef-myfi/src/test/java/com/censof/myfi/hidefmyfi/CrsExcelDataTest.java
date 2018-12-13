package com.censof.myfi.hidefmyfi;

import static org.junit.Assert.*;

import org.junit.Test;

public class CrsExcelDataTest {

	@Test
	public void test() {/*
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

}

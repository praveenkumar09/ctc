package com.censof.myfi.hidefmyfi.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.censof.myfi.hidef.packaging.FATCAPackager;
import com.censof.myfi.hidefmyfi.configuration.UtilShared;
import com.censof.myfi.hidefmyfi.entity.Cbcbizactivitiesreference;
import com.censof.myfi.hidefmyfi.entity.Cbcsummaryreference;
import com.censof.myfi.hidefmyfi.entity.Hicountry;
import com.censof.myfi.hidefmyfi.service.CtcDataSaveService;
import com.censof.myfi.hidefmyfi.service.PackageGenerationService;
import com.censof.myfi.hidefmyfi.vo.AddressVo;
import com.censof.myfi.hidefmyfi.vo.BizActivitiesTypeVo;
import com.censof.myfi.hidefmyfi.vo.CBCRepotsVo;
import com.censof.myfi.hidefmyfi.vo.CbcAdditionalInfo;
import com.censof.myfi.hidefmyfi.vo.HidefVo;
import com.censof.myfi.hidefmyfi.vo.NameVo;
import com.censof.myfi.hidefmyfi.vo.OrganisationInTypeVo;
import com.censof.myfi.hidefmyfi.vo.RecievingCountryVo;
import com.censof.myfi.hidefmyfi.vo.ResidentCountryVo;
import com.censof.myfi.hidefmyfi.vo.SummaryVo;

@Service
public class PackageGenerationServiceImpl implements PackageGenerationService {

	@Autowired
	CtccommonDropdownServiceImpl commonDropDownService;
	
	@Autowired
	CtcDataSaveService ctcDataSaveService;

	@Override
	public String generateCBCXMLMetData(HidefVo hidef) throws IOException {

		String personXMLStringValue = null;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			// Create Person root element
			Element rootElement = doc.createElement("CTSSenderFileMetadata");
			rootElement.setAttribute("xmlns", "urn:oecd:ctssenderfilemetadata");
			rootElement.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
			rootElement.setAttribute("xmlns:xmime", "http://www.w3.org/2005/05/xmlmime");
			rootElement.setAttribute("xmlns:xmime", "urn:oecd:ties:isoctstypes:v1");
			doc.appendChild(rootElement);
			// Create First Name Element

			if (hidef.getUserprofile() != null) {

				if (hidef.getUserprofile().getSendingcountry() != null
						&& !hidef.getUserprofile().getSendingcountry().isEmpty()) {
					Element senderCountryCode = doc.createElement("CTSSenderCountryCd");
					senderCountryCode.appendChild(doc.createTextNode(hidef.getUserprofile().getSendingcountry()));
					rootElement.appendChild(senderCountryCode);

				}

				
				if(hidef.getUserprofile().getRecievingCountryList() != null 
						&& !hidef.getUserprofile().getRecievingCountryList().isEmpty()) {
					for(RecievingCountryVo recievingCountry : hidef.getUserprofile().getRecievingCountryList()) {
						Element receivingCountryCode = doc.createElement("CTSReceiverCountryCd");
						receivingCountryCode.appendChild(doc.createTextNode(""+commonDropDownService.findCountryById(BigInteger.valueOf(recievingCountry.getRecievingCountry())).getCountryCode()));
						rootElement.appendChild(receivingCountryCode);
					}
				}

				if (hidef.getUserprofile().getCommunicationType() != null
						&& !hidef.getUserprofile().getCommunicationType().isEmpty()) {
					Element communicationType = doc.createElement("CTSCommunicationTypeCd");
					communicationType.appendChild(doc.createTextNode(hidef.getUserprofile().getCommunicationType()));
					rootElement.appendChild(communicationType);

				}

				if (hidef.getUserprofile().getCtsTransId() != null
						&& !hidef.getUserprofile().getCtsTransId().isEmpty()) {
					Element transmissionId = doc.createElement("OriginalCTSTransmissionId");
					transmissionId.appendChild(doc.createTextNode(hidef.getUserprofile().getCtsTransId()));
					rootElement.appendChild(transmissionId);
				}

				if (hidef.getUserprofile().getFileformatCode() != null
						&& !hidef.getUserprofile().getFileformatCode().isEmpty()) {
					Element fileFormatCode = doc.createElement("FileFormatCd");
					fileFormatCode.appendChild(doc.createTextNode(hidef.getUserprofile().getFileformatCode()));
					rootElement.appendChild(fileFormatCode);
				}

				if (hidef.getUserprofile().getBinaryEncoding() != null
						&& !hidef.getUserprofile().getBinaryEncoding().isEmpty()) {
					Element binaryEncoding = doc.createElement("BinaryEncodingSchemeCd");
					binaryEncoding.appendChild(doc.createTextNode(hidef.getUserprofile().getBinaryEncoding()));
					rootElement.appendChild(binaryEncoding);
				}

				if (hidef.getUserprofile().getSendContactEmailAddress() != null
						&& !hidef.getUserprofile().getSendContactEmailAddress().isEmpty()) {
					Element senderContactEmailAddress = doc.createElement("SenderContactEmailAddressTxt");
					senderContactEmailAddress
							.appendChild(doc.createTextNode(hidef.getUserprofile().getSendContactEmailAddress()));
					rootElement.appendChild(senderContactEmailAddress);
				}

				if (hidef.getUserprofile().getFileTypeIndic() != null
						&& !hidef.getUserprofile().getFileTypeIndic().isEmpty()) {
					Element fileTypeIndic = doc.createElement("FileRevisionInd");
					fileTypeIndic.appendChild(doc.createTextNode(hidef.getUserprofile().getFileTypeIndic()));
					rootElement.appendChild(fileTypeIndic);
				}

			}

			if (hidef.getMetadata() != null) {
				if (hidef.getMetadata().getSenderFileId() != null && !hidef.getMetadata().getSenderFileId().isEmpty()) {
					Element senderFileId = doc.createElement("SenderFileId");
					senderFileId.appendChild(doc.createTextNode(hidef.getMetadata().getSenderFileId()));
					rootElement.appendChild(senderFileId);
				}

				if (hidef.getMetadata().getFormCreationTimeStamp() != null
						&& !hidef.getMetadata().getFormCreationTimeStamp().isEmpty()) {
					Element fileCreationTS = doc.createElement("FileCreateTs");
					fileCreationTS.appendChild(doc.createTextNode(hidef.getMetadata().getFormCreationTimeStamp()));
					rootElement.appendChild(fileCreationTS);
				}

				if (hidef.getMetadata().getTaxYear() != null && !hidef.getMetadata().getTaxYear().isEmpty()) {
					Element taxYear = doc.createElement("TaxYear");
					taxYear.appendChild(doc.createTextNode(hidef.getMetadata().getTaxYear()));
					rootElement.appendChild(taxYear);
				}
			}
			// Transform Document to XML String
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			// Get the String value of final xml document
			personXMLStringValue = writer.getBuffer().toString();
			// return personXMLStringValue;
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String metaDataFilePath = fetchProperties("metadataPath");
		File file = new File(metaDataFilePath+"/"+auth.getName()+"_"+hidef.getUserprofile().getCommunicationType()+"_Metadata.xml");
		hidef.setMetaDataFileName(auth.getName()+"_"+hidef.getUserprofile().getCommunicationType()+"_Metadata.xml");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(personXMLStringValue);
		fileWriter.close();
		
		return personXMLStringValue;
	}

	@Override
	public String generateCBCXMLPayload(HidefVo hidef) throws IOException {
		// TODO Auto-generated method stub
		// cbc:CBC_OECD

		String personXMLStringValue = null;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			// Create Person root element
			Element rootElement = doc.createElement("cbc:CBC_OECD");
			rootElement.setAttribute("version", "1.0.1");
			rootElement.setAttribute("xmlns", "urn:oecd:ties:cbc:v1");
			rootElement.setAttribute("xmlns:cbc", "urn:oecd:ties:cbc:v1");
			rootElement.setAttribute("xmlns:iso", "urn:oecd:ties:isocbctypes:v1");
			rootElement.setAttribute("xmlns:stf", "urn:oecd:ties:stf:v4");
			rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			rootElement.setAttribute("xsi:schemaLocation", "urn:oecd:ties:cbc:v1 CbcXML_v1.0.1.xsd");
			doc.appendChild(rootElement);

			if (hidef.getMetadata() != null) {

				Element messageSpec = doc.createElement("cbc:MessageSpec");
				rootElement.appendChild(messageSpec);

				if (hidef.getMetadata().getSendingCompanyIN() != null
						&& !hidef.getMetadata().getSendingCompanyIN().isEmpty()) {
					Element sendingEntityIN = doc.createElement("cbc:SendingEntityIN");
					sendingEntityIN.appendChild(doc.createTextNode(hidef.getMetadata().getSendingCompanyIN()));
					messageSpec.appendChild(sendingEntityIN);
				}

				if (hidef.getMetadata().getSendingCountry() != null
						&& !hidef.getMetadata().getSendingCountry().isEmpty()) {
					Element transmittingCountry = doc.createElement("cbc:TransmittingCountry");
					transmittingCountry.appendChild(doc.createTextNode(hidef.getMetadata().getSendingCountry()));
					messageSpec.appendChild(transmittingCountry);
				}

				if (hidef.getMetadata().getRecievingCountryList() != null
						&& !hidef.getMetadata().getRecievingCountryList().isEmpty()) {
					for (RecievingCountryVo recivingCountryVO : hidef.getMetadata().getRecievingCountryList()) {
						Element receivingCountry = doc.createElement("cbc:ReceivingCountry");
						Hicountry countryObject = commonDropDownService
								.findCountryById(BigInteger.valueOf(recivingCountryVO.getRecievingCountry()));
						receivingCountry.appendChild(doc.createTextNode(countryObject.getCountryCode()));
						messageSpec.appendChild(receivingCountry);
					}
				}

				if (hidef.getMetadata().getMsgType() != null && !hidef.getMetadata().getMsgType().isEmpty()) {
					Element messageType = doc.createElement("cbc:MessageType");
					messageType.appendChild(doc.createTextNode(hidef.getMetadata().getMsgType()));
					messageSpec.appendChild(messageType);
				}

				if (hidef.getMetadata().getLanguage() != null && !hidef.getMetadata().getLanguage().isEmpty()) {
					Element language = doc.createElement("cbc:Language");
					language.appendChild(doc.createTextNode(hidef.getMetadata().getLanguage()));
					messageSpec.appendChild(language);
				}

				if (hidef.getMetadata().getWarning() != null && !hidef.getMetadata().getWarning().isEmpty()) {
					Element warning = doc.createElement("cbc:Warning");
					warning.appendChild(doc.createTextNode(hidef.getMetadata().getWarning()));
					messageSpec.appendChild(warning);
				}

				if (hidef.getMetadata().getContact() != null && !hidef.getMetadata().getContact().isEmpty()) {
					Element contact = doc.createElement("cbc:Contact");
					contact.appendChild(doc.createTextNode(hidef.getMetadata().getContact()));
					messageSpec.appendChild(contact);
				}

				if (hidef.getMetadata().getMessageRefId() != null && !hidef.getMetadata().getMessageRefId().isEmpty()) {
					Element messageRefId = doc.createElement("cbc:MessageRefId");
					messageRefId.appendChild(doc.createTextNode(hidef.getMetadata().getMessageRefId()));
					messageSpec.appendChild(messageRefId);
				}

				if (hidef.getMetadata().getMessageTypeIndic() != null
						&& !hidef.getMetadata().getMessageTypeIndic().isEmpty()) {
					Element messageTypeIndic = doc.createElement("cbc:MessageTypeIndic");
					messageTypeIndic.appendChild(doc.createTextNode(hidef.getMetadata().getMessageTypeIndic()));
					messageSpec.appendChild(messageTypeIndic);
				}

				if (hidef.getMetadata().getReportingPeriod() != null
						&& !hidef.getMetadata().getReportingPeriod().isEmpty()) {
					Element reportingPeriod = doc.createElement("cbc:ReportingPeriod");
					reportingPeriod.appendChild(doc.createTextNode(hidef.getMetadata().getReportingPeriod()));
					messageSpec.appendChild(reportingPeriod);
				}

				if (hidef.getMetadata().getFormCreationTimeStamp() != null
						&& !hidef.getMetadata().getFormCreationTimeStamp().isEmpty()) {
					Element fileCreationTimeStamp = doc.createElement("cbc:Timestamp");
					fileCreationTimeStamp
							.appendChild(doc.createTextNode(hidef.getMetadata().getFormCreationTimeStamp()));
					messageSpec.appendChild(fileCreationTimeStamp);
				}

			}

			Element cbcBodyElement = doc.createElement("cbc:CbcBody");
			rootElement.appendChild(cbcBodyElement);

			if (hidef.getReportingEntity() != null) {

				Element reportingEntity = doc.createElement("cbc:ReportingEntity");
				cbcBodyElement.appendChild(reportingEntity);

				Element cbcEntity = doc.createElement("cbc:Entity");
				reportingEntity.appendChild(cbcEntity);

				if (hidef.getReportingEntity().getResidentCountryList() != null
						&& !hidef.getReportingEntity().getResidentCountryList().isEmpty()) {
					for (ResidentCountryVo residentCountry : hidef.getReportingEntity().getResidentCountryList()) {
						Element residentCountryCode = doc.createElement("cbc:ResCountryCode");
						Hicountry countryObject = commonDropDownService
								.findCountryById(BigInteger.valueOf(residentCountry.getResidentCountryCode()));
						residentCountryCode.appendChild(doc.createTextNode(countryObject.getCountryCode()));
						cbcEntity.appendChild(residentCountryCode);
					}
				}

				if (hidef.getReportingEntity().getTin() != null && !hidef.getReportingEntity().getTin().isEmpty()) {
					Element tin = doc.createElement("cbc:TIN");
					tin.setAttribute("TINType", hidef.getReportingEntity().getTinType());
					tin.setAttribute("issuedBy", hidef.getReportingEntity().getTinIssuedBy());
					tin.appendChild(doc.createTextNode(hidef.getReportingEntity().getTin()));
					cbcEntity.appendChild(tin);
				}

				if (hidef.getReportingEntity().getOrganisationInTypeList() != null
						&& !hidef.getReportingEntity().getOrganisationInTypeList().isEmpty()) {
					for (OrganisationInTypeVo orgInType : hidef.getReportingEntity().getOrganisationInTypeList()) {
						Element in = doc.createElement("cbc:IN");
						if (orgInType.getInType() != null && !orgInType.getInType().isEmpty()) {
							in.setAttribute("INType", orgInType.getInType());
						}

						if (orgInType.getIssuedBy() != 0) {
							Hicountry countryObject = commonDropDownService
									.findCountryById(BigInteger.valueOf(orgInType.getIssuedBy()));
							in.setAttribute("issuedBy", countryObject.getCountryCode());
						}

						if (orgInType.getIn() != null && !orgInType.getIn().isEmpty()) {
							in.appendChild(doc.createTextNode(orgInType.getIn()));
						}
						cbcEntity.appendChild(in);

					}
				}

				if (hidef.getReportingEntity().getNameList() != null
						&& !hidef.getReportingEntity().getNameList().isEmpty()) {
					for (NameVo nameObject : hidef.getReportingEntity().getNameList()) {
						Element name = doc.createElement("cbc:Name");
						name.appendChild(doc.createTextNode(nameObject.getFirstName()));
						cbcEntity.appendChild(name);
					}
				}

				if (hidef.getReportingEntity().getAddressList() != null
						&& !hidef.getReportingEntity().getAddressList().isEmpty()) {
					for (AddressVo address : hidef.getReportingEntity().getAddressList()) {
						Element addressElement = doc.createElement("cbc:Address");
						cbcEntity.appendChild(addressElement);

						Element addressCountryCode = doc.createElement("cbc:CountryCode");
						addressCountryCode.appendChild(doc.createTextNode(address.getCountryCode()));
						addressElement.appendChild(addressCountryCode);

						Element addressFree = doc.createElement("cbc:AddressFree");
						addressFree.appendChild(doc.createTextNode(address.getAddressFree()));
						addressElement.appendChild(addressFree);

						if (!address.getAddressType().equals("Address Free")) {
							Element addressFix = doc.createElement("cbc:AddressFix");
							addressElement.appendChild(addressFix);

							if (address.getStreet() != null && !address.getStreet().isEmpty()) {
								Element addressFixStreet = doc.createElement("cbc:Street");
								addressFixStreet.appendChild(doc.createTextNode(address.getStreet()));
								addressFix.appendChild(addressFixStreet);
							}

							if (address.getBuildingIdentifier() != null && !address.getBuildingIdentifier().isEmpty()) {
								Element addressFixBI = doc.createElement("cbc:BuildingIdentifier");
								addressFixBI.appendChild(doc.createTextNode(address.getBuildingIdentifier()));
								addressFix.appendChild(addressFixBI);
							}

							if (address.getSuitIdentifier() != null && !address.getSuitIdentifier().isEmpty()) {
								Element addressFixSI = doc.createElement("cbc:SuiteIdentifier");
								addressFixSI.appendChild(doc.createTextNode(address.getSuitIdentifier()));
								addressFix.appendChild(addressFixSI);
							}

							if (address.getFloorIdentifier() != null && !address.getFloorIdentifier().isEmpty()) {
								Element addressFixFI = doc.createElement("cbc:FloorIdentifier");
								addressFixFI.appendChild(doc.createTextNode(address.getFloorIdentifier()));
								addressFix.appendChild(addressFixFI);
							}

							if (address.getDistrictName() != null && !address.getDistrictName().isEmpty()) {
								Element addressFixDN = doc.createElement("cbc:DistrictName");
								addressFixDN.appendChild(doc.createTextNode(address.getDistrictName()));
								addressFix.appendChild(addressFixDN);
							}

							if (address.getPob() != null && !address.getPob().isEmpty()) {
								Element addressFixPOB = doc.createElement("cbc:POB");
								addressFixPOB.appendChild(doc.createTextNode(address.getPob()));
								addressFix.appendChild(addressFixPOB);
							}

							if (address.getPostCode() != null && !address.getPostCode().isEmpty()) {
								Element addressFixPC = doc.createElement("cbc:PostCode");
								addressFixPC.appendChild(doc.createTextNode(address.getPostCode()));
								addressFix.appendChild(addressFixPC);
							}

							if (address.getCity() != null && !address.getCity().isEmpty()) {
								Element addressFixCity = doc.createElement("cbc:City");
								addressFixCity.appendChild(doc.createTextNode(address.getCity()));
								addressFix.appendChild(addressFixCity);
							}

							if (address.getCountrySubentity() != null && !address.getCountrySubentity().isEmpty()) {
								Element addressFixCSE = doc.createElement("cbc:CountrySubentity");
								addressFixCSE.appendChild(doc.createTextNode(address.getCountrySubentity()));
								addressFix.appendChild(addressFixCSE);
							}

						}
					}
				}

				if (hidef.getReportingEntity().getReportingRole() != null
						&& !hidef.getReportingEntity().getReportingRole().isEmpty()) {
					Element repotingRole = doc.createElement("cbc:ReportingRole");
					repotingRole.appendChild(doc.createTextNode(hidef.getReportingEntity().getReportingRole()));
					reportingEntity.appendChild(repotingRole);
				}

				Element docSpecRE = doc.createElement("cbc:DocSpec");
				reportingEntity.appendChild(docSpecRE);

				if (hidef.getReportingEntity().getDocumentTypeIndicator() != null
						&& !hidef.getReportingEntity().getDocumentTypeIndicator().isEmpty()) {
					Element docTypeIndicatorRE = doc.createElement("stf:DocTypeIndic");
					docTypeIndicatorRE
							.appendChild(doc.createTextNode(hidef.getReportingEntity().getDocumentTypeIndicator()));
					docSpecRE.appendChild(docTypeIndicatorRE);
				}

				if (hidef.getReportingEntity().getDocumentReferenceId() != null
						&& !hidef.getReportingEntity().getDocumentReferenceId().isEmpty()) {
					Element docRefIdRE = doc.createElement("stf:DocRefId");
					docRefIdRE.appendChild(doc.createTextNode(hidef.getReportingEntity().getDocumentReferenceId()));
					docSpecRE.appendChild(docRefIdRE);
				}

				if (hidef.getReportingEntity().getCorDocReferenceId() != null
						&& !hidef.getReportingEntity().getCorDocReferenceId().isEmpty()) {
					Element corDocRefIdRE = doc.createElement("stf:CorDocRefId");
					corDocRefIdRE.appendChild(doc.createTextNode(hidef.getReportingEntity().getCorDocReferenceId()));
					docSpecRE.appendChild(corDocRefIdRE);
				}

				if (hidef.getReportingEntity().getCorMessageReferenceId() != null
						&& !hidef.getReportingEntity().getCorMessageReferenceId().isEmpty()) {
					Element corMessageRefIdRE = doc.createElement("stf:CorMessageRefId");
					corMessageRefIdRE
							.appendChild(doc.createTextNode(hidef.getReportingEntity().getCorMessageReferenceId()));
					docSpecRE.appendChild(corMessageRefIdRE);
				}

			}

			if (hidef.getListCBCReports() != null && !hidef.getListCBCReports().isEmpty()) {
				Element cbcReportsElement = doc.createElement("cbc:CbcReports");
				cbcBodyElement.appendChild(cbcReportsElement);
				for (CBCRepotsVo reports : hidef.getListCBCReports()) {

					Element docSpecRE = doc.createElement("cbc:DocSpec");
					cbcReportsElement.appendChild(docSpecRE);

					if (reports.getDocumentTypeIndicator() != null && !reports.getDocumentTypeIndicator().isEmpty()) {
						Element docTypeIndicatorRE = doc.createElement("stf:DocTypeIndic");
						docTypeIndicatorRE.appendChild(doc.createTextNode(reports.getDocumentTypeIndicator()));
						docSpecRE.appendChild(docTypeIndicatorRE);
					}

					if (reports.getDocumentRefId() != null && !reports.getDocumentRefId().isEmpty()) {
						Element docRefIdRE = doc.createElement("stf:DocRefId");
						docRefIdRE.appendChild(doc.createTextNode(reports.getDocumentRefId()));
						docSpecRE.appendChild(docRefIdRE);
					}

					if (reports.getCorrDocRefId() != null && !reports.getCorrDocRefId().isEmpty()) {
						Element corDocRefIdRE = doc.createElement("stf:CorDocRefId");
						corDocRefIdRE.appendChild(doc.createTextNode(reports.getCorrDocRefId()));
						docSpecRE.appendChild(corDocRefIdRE);
					}

					if (reports.getCorrMessageRefId() != null && !reports.getCorrMessageRefId().isEmpty()) {
						Element corMessageRefIdRE = doc.createElement("stf:CorMessageRefId");
						corMessageRefIdRE.appendChild(doc.createTextNode(reports.getCorrMessageRefId()));
						docSpecRE.appendChild(corMessageRefIdRE);
					}

					if (reports.getResidentCountryCode() != null && !reports.getResidentCountryCode().isEmpty()) {
						Element residentCountryCode = doc.createElement("cbc:ResCountryCode");
						residentCountryCode.appendChild(doc.createTextNode(reports.getResidentCountryCode()));
						cbcReportsElement.appendChild(residentCountryCode);
					}

					Element reportsSummary = doc.createElement("cbc:Summary");
					cbcReportsElement.appendChild(reportsSummary);

					Element reportsRevenues = doc.createElement("cbc:Revenues");
					reportsSummary.appendChild(reportsRevenues);

					if (reports.getUnrelatedAmount() != null && !reports.getUnrelatedAmount().isEmpty()) {
						Element reportsUnRelatedRevenues = doc.createElement("cbc:Unrelated");
						if (reports.getUnrelateCurrCode() != null && !reports.getUnrelateCurrCode().isEmpty()) {
							reportsUnRelatedRevenues.setAttribute("currCode", reports.getUnrelateCurrCode());
						}
						reportsUnRelatedRevenues.appendChild(doc.createTextNode(reports.getUnrelatedAmount()));
						reportsRevenues.appendChild(reportsUnRelatedRevenues);
					}

					if (reports.getRelatedAmount() != null && !reports.getRelatedAmount().isEmpty()) {
						Element reportsRelatedRevenues = doc.createElement("cbc:Related");
						if (reports.getRelatedCurrCode() != null && !reports.getRelatedCurrCode().isEmpty()) {
							reportsRelatedRevenues.setAttribute("currCode", reports.getRelatedCurrCode());
						}
						reportsRelatedRevenues.appendChild(doc.createTextNode(reports.getRelatedAmount()));
						reportsRevenues.appendChild(reportsRelatedRevenues);
					}

					if (reports.getTotalRevenueAmount() != null && !reports.getTotalRevenueAmount().isEmpty()) {
						Element reportsTotalRevenues = doc.createElement("cbc:Total");
						if (reports.getTotalRevenueCurrCode() != null
								&& !reports.getTotalRevenueCurrCode().isEmpty()) {
							reportsTotalRevenues.setAttribute("currCode", reports.getTotalRevenueCurrCode());
						}
						reportsTotalRevenues.appendChild(doc.createTextNode(reports.getTotalRevenueAmount()));
						reportsRevenues.appendChild(reportsTotalRevenues);
					}

					if (reports.getPrfitotloassAmount() != null && !reports.getPrfitotloassAmount().isEmpty()) {
						Element reportsProfitOrLoss = doc.createElement("cbc:ProfitOrLoss");
						if (reports.getProfitorlossCurrCode() != null
								&& !reports.getProfitorlossCurrCode().isEmpty()) {
							reportsProfitOrLoss.setAttribute("currCode", reports.getProfitorlossCurrCode());
						}
						reportsProfitOrLoss.appendChild(doc.createTextNode(reports.getPrfitotloassAmount()));
						reportsSummary.appendChild(reportsProfitOrLoss);
					}

					if (reports.getTaxpaidAmount() != null && !reports.getTaxpaidAmount().isEmpty()) {
						Element reportsTaxPaid = doc.createElement("cbc:TaxPaid");
						if (reports.getTaxpiadCurrCode() != null && !reports.getTaxpiadCurrCode().isEmpty()) {
							reportsTaxPaid.setAttribute("currCode", reports.getTaxpiadCurrCode());
						}
						reportsTaxPaid.appendChild(doc.createTextNode(reports.getTaxpaidAmount()));
						reportsSummary.appendChild(reportsTaxPaid);
					}

					if (reports.getTaxaccruedAmount() != null && !reports.getTaxaccruedAmount().isEmpty()) {
						Element reportsTaxAccured = doc.createElement("cbc:TaxAccrued");
						if (reports.getTaxaccruedCurrCode() != null
								&& !reports.getTaxaccruedCurrCode().isEmpty()) {
							reportsTaxAccured.setAttribute("currCode", reports.getTaxaccruedCurrCode());
						}
						reportsTaxAccured.appendChild(doc.createTextNode(reports.getTaxaccruedAmount()));
						reportsSummary.appendChild(reportsTaxAccured);
					}

					if (reports.getCapitalAmount() != null && !reports.getCapitalAmount().isEmpty()) {
						Element reportsCapitalAmount = doc.createElement("cbc:Capital");
						if (reports.getCapitalCurrCode() != null && !reports.getCapitalCurrCode().isEmpty()) {
							reportsCapitalAmount.setAttribute("currCode", reports.getCapitalCurrCode());
						}
						reportsCapitalAmount.appendChild(doc.createTextNode(reports.getCapitalAmount()));
						reportsSummary.appendChild(reportsCapitalAmount);
					}

					if (reports.getEarningAmount() != null && !reports.getEarningAmount().isEmpty()) {
						Element reportsEarningAmount = doc.createElement("cbc:Earnings");
						if (reports.getEarningCurrCode() != null && !reports.getEarningCurrCode().isEmpty()) {
							reportsEarningAmount.setAttribute("currCode", reports.getEarningCurrCode());
						}
						reportsEarningAmount.appendChild(doc.createTextNode(reports.getEarningAmount()));
						reportsSummary.appendChild(reportsEarningAmount);
					}

					if (reports.getNbEmployees() != null && !reports.getNbEmployees().isEmpty()) {
						Element reportsNbEmployees = doc.createElement("cbc:NbEmployees");
						reportsNbEmployees.appendChild(doc.createTextNode(reports.getNbEmployees()));
						reportsSummary.appendChild(reportsNbEmployees);
					}

					if (reports.getAssertAmount() != null && !reports.getAssertAmount().isEmpty()) {
						Element reportsAssetAmount = doc.createElement("cbc:Assets");
						if (reports.getAssertCurrCode() != null && !reports.getAssertCurrCode().isEmpty()) {
							reportsAssetAmount.setAttribute("currCode", reports.getAssertCurrCode());
						}
						reportsAssetAmount.appendChild(doc.createTextNode(reports.getAssertAmount()));
						reportsSummary.appendChild(reportsAssetAmount);
					}

					Element reportsConstEntities = doc.createElement("cbc:ConstEntities");
					cbcReportsElement.appendChild(reportsConstEntities);

					Element reportsConstEntity = doc.createElement("cbc:ConstEntity");
					reportsConstEntities.appendChild(reportsConstEntity);

					if (reports.getResidentCountryList() != null && !reports.getResidentCountryList().isEmpty()) {
						for (ResidentCountryVo residentCountry : reports.getResidentCountryList()) {
							Element residentCountryCode = doc.createElement("cbc:ResCountryCode");
							Hicountry countryObject = commonDropDownService
									.findCountryById(BigInteger.valueOf(residentCountry.getResidentCountryCode()));
							residentCountryCode.appendChild(doc.createTextNode(countryObject.getCountryCode()));
							reportsConstEntity.appendChild(residentCountryCode);
						}
					}

					if (reports.getTin() != null && !reports.getTin().isEmpty()) {
						Element reportsTIN = doc.createElement("cbc:TIN");
						reportsTIN.setAttribute("TINType", reports.getTinType());
						reportsTIN.setAttribute("issuedBy", reports.getIssuedBy());
						reportsTIN.appendChild(doc.createTextNode(reports.getTin()));
						reportsConstEntity.appendChild(reportsTIN);
					}

					if (reports.getOrganisationInTypeList() != null && !reports.getOrganisationInTypeList().isEmpty()) {
						for (OrganisationInTypeVo orgInType : reports.getOrganisationInTypeList()) {
							Element in = doc.createElement("cbc:IN");
							if (orgInType.getInType() != null && !orgInType.getInType().isEmpty()) {
								in.setAttribute("INType", orgInType.getInType());
							}

							if (orgInType.getIssuedBy() != 0) {
								Hicountry countryObject = commonDropDownService
										.findCountryById(BigInteger.valueOf(orgInType.getIssuedBy()));
								in.setAttribute("issuedBy", countryObject.getCountryCode());
							}

							if (orgInType.getIn() != null && !orgInType.getIn().isEmpty()) {
								in.appendChild(doc.createTextNode(orgInType.getIn()));
							}
							reportsConstEntity.appendChild(in);

						}
					}

					if (reports.getNameList() != null && !reports.getNameList().isEmpty()) {
						for (NameVo nameObject : reports.getNameList()) {
							Element name = doc.createElement("cbc:Name");
							name.appendChild(doc.createTextNode(nameObject.getFirstName()));
							reportsConstEntity.appendChild(name);
						}
					}

					if (reports.getAddressList() != null && !reports.getAddressList().isEmpty()) {
						for (AddressVo address : reports.getAddressList()) {
							Element addressElement = doc.createElement("cbc:Address");
							reportsConstEntity.appendChild(addressElement);

							Element addressCountryCode = doc.createElement("cbc:CountryCode");
							addressCountryCode.appendChild(doc.createTextNode(address.getCountryCode()));
							addressElement.appendChild(addressCountryCode);

							Element addressFree = doc.createElement("cbc:AddressFree");
							addressFree.appendChild(doc.createTextNode(address.getAddressFree()));
							addressElement.appendChild(addressFree);

							if (!address.getAddressType().equals("Address Free")) {
								Element addressFix = doc.createElement("cbc:AddressFix");
								addressElement.appendChild(addressFix);

								if (address.getStreet() != null && !address.getStreet().isEmpty()) {
									Element addressFixStreet = doc.createElement("cbc:Street");
									addressFixStreet.appendChild(doc.createTextNode(address.getStreet()));
									addressFix.appendChild(addressFixStreet);
								}

								if (address.getBuildingIdentifier() != null
										&& !address.getBuildingIdentifier().isEmpty()) {
									Element addressFixBI = doc.createElement("cbc:BuildingIdentifier");
									addressFixBI.appendChild(doc.createTextNode(address.getBuildingIdentifier()));
									addressFix.appendChild(addressFixBI);
								}

								if (address.getSuitIdentifier() != null && !address.getSuitIdentifier().isEmpty()) {
									Element addressFixSI = doc.createElement("cbc:SuiteIdentifier");
									addressFixSI.appendChild(doc.createTextNode(address.getSuitIdentifier()));
									addressFix.appendChild(addressFixSI);
								}

								if (address.getFloorIdentifier() != null && !address.getFloorIdentifier().isEmpty()) {
									Element addressFixFI = doc.createElement("cbc:FloorIdentifier");
									addressFixFI.appendChild(doc.createTextNode(address.getFloorIdentifier()));
									addressFix.appendChild(addressFixFI);
								}

								if (address.getDistrictName() != null && !address.getDistrictName().isEmpty()) {
									Element addressFixDN = doc.createElement("cbc:DistrictName");
									addressFixDN.appendChild(doc.createTextNode(address.getDistrictName()));
									addressFix.appendChild(addressFixDN);
								}

								if (address.getPob() != null && !address.getPob().isEmpty()) {
									Element addressFixPOB = doc.createElement("cbc:POB");
									addressFixPOB.appendChild(doc.createTextNode(address.getPob()));
									addressFix.appendChild(addressFixPOB);
								}

								if (address.getPostCode() != null && !address.getPostCode().isEmpty()) {
									Element addressFixPC = doc.createElement("cbc:PostCode");
									addressFixPC.appendChild(doc.createTextNode(address.getPostCode()));
									addressFix.appendChild(addressFixPC);
								}

								if (address.getCity() != null && !address.getCity().isEmpty()) {
									Element addressFixCity = doc.createElement("cbc:City");
									addressFixCity.appendChild(doc.createTextNode(address.getCity()));
									addressFix.appendChild(addressFixCity);
								}

								if (address.getCountrySubentity() != null && !address.getCountrySubentity().isEmpty()) {
									Element addressFixCSE = doc.createElement("cbc:CountrySubentity");
									addressFixCSE.appendChild(doc.createTextNode(address.getCountrySubentity()));
									addressFix.appendChild(addressFixCSE);
								}

							}
						}
					}

					if (reports.getIncorpCountryCode() != null && reports.getIncorpCountryCode().isEmpty()) {
						Element reportsIncorpCountryCode = doc.createElement("cbc:IncorpCountryCode");
						reportsIncorpCountryCode.appendChild(doc.createTextNode(reports.getIncorpCountryCode()));
						reportsConstEntities.appendChild(reportsIncorpCountryCode);
					}

					if (reports.getBizActivitiesList() != null && reports.getBizActivitiesList().isEmpty()) {
						for (BizActivitiesTypeVo bizActivitiesVO : reports.getBizActivitiesList()) {
							Element bizActivities = doc.createElement("cbc:BizActivities");
							Cbcbizactivitiesreference bizActivitiesById = commonDropDownService
									.findBizActivitiesById(bizActivitiesVO.getBizType());
							bizActivities.appendChild(doc.createTextNode(bizActivitiesById.getBizType()));
							reportsConstEntities.appendChild(bizActivities);
						}
					}

					if (reports.getOtherEntityInfo() != null && !reports.getOtherEntityInfo().isEmpty()) {
						Element reportsOtherEntityInfo = doc.createElement("cbc:OtherEntityInfo");
						reportsOtherEntityInfo.appendChild(doc.createTextNode(reports.getOtherEntityInfo()));
						reportsConstEntities.appendChild(reportsOtherEntityInfo);
					}

				}
			}

			if (hidef.getAddInfoList() != null && !hidef.getAddInfoList().isEmpty()) {
				Element cbcAddInfoElement = doc.createElement("cbc:AdditionalInfo");
				cbcBodyElement.appendChild(cbcAddInfoElement);
				for (CbcAdditionalInfo addInfo : hidef.getAddInfoList()) {

					Element docSpecRE = doc.createElement("cbc:DocSpec");
					cbcAddInfoElement.appendChild(docSpecRE);

					if (addInfo.getDocumentTypeIndic() != null && !addInfo.getDocumentTypeIndic().isEmpty()) {
						Element docTypeIndicatorRE = doc.createElement("stf:DocTypeIndic");
						docTypeIndicatorRE.appendChild(doc.createTextNode(addInfo.getDocumentTypeIndic()));
						docSpecRE.appendChild(docTypeIndicatorRE);
					}

					if (addInfo.getDocumentReferenceId() != null && !addInfo.getDocumentReferenceId().isEmpty()) {
						Element docRefIdRE = doc.createElement("stf:DocRefId");
						docRefIdRE.appendChild(doc.createTextNode(addInfo.getDocumentReferenceId()));
						docSpecRE.appendChild(docRefIdRE);
					}

					if (addInfo.getCorDocumentRefId() != null && !addInfo.getCorDocumentRefId().isEmpty()) {
						Element corDocRefIdRE = doc.createElement("stf:CorDocRefId");
						corDocRefIdRE.appendChild(doc.createTextNode(addInfo.getCorDocumentRefId()));
						docSpecRE.appendChild(corDocRefIdRE);
					}

					if (addInfo.getCorrMessageRefId() != null && !addInfo.getCorrMessageRefId().isEmpty()) {
						Element corMessageRefIdRE = doc.createElement("stf:CorMessageRefId");
						corMessageRefIdRE.appendChild(doc.createTextNode(addInfo.getCorrMessageRefId()));
						docSpecRE.appendChild(corMessageRefIdRE);
					}
					
					if(addInfo.getOtherInfo() != null && !addInfo.getOtherInfo().isEmpty()) {
						Element addInfoOtherInfo = doc.createElement("cbc:OtherInfo");
						addInfoOtherInfo.appendChild(doc.createTextNode(addInfo.getOtherInfo()));
						cbcAddInfoElement.appendChild(addInfoOtherInfo);
					}
					
					if (addInfo.getResidentCountryList() != null && !addInfo.getResidentCountryList().isEmpty()) {
						for (ResidentCountryVo residentCountry : addInfo.getResidentCountryList()) {
							Element residentCountryCode = doc.createElement("cbc:ResCountryCode");
							Hicountry countryObject = commonDropDownService
									.findCountryById(BigInteger.valueOf(residentCountry.getResidentCountryCode()));
							residentCountryCode.appendChild(doc.createTextNode(countryObject.getCountryCode()));
							cbcAddInfoElement.appendChild(residentCountryCode);
						}
					}
					
					if(addInfo.getSummaryList() != null && !addInfo.getSummaryList().isEmpty()) {
						for(SummaryVo summaryVO : addInfo.getSummaryList()) {
							Element summaryReference = doc.createElement("cbc:SummaryRef");
							Cbcsummaryreference summaryReferenceObject = commonDropDownService.findSummaryReferenceById(summaryVO.getSummeryReference());
							summaryReference.appendChild(doc.createTextNode(summaryReferenceObject.getRefType()));
							cbcAddInfoElement.appendChild(summaryReference);
						}
					}
					
					
					
				}
			}

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			personXMLStringValue = writer.getBuffer().toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String payloadFilePath = fetchProperties("payloadPath");
		File file = new File(payloadFilePath+"/"+auth.getName()+"_"+hidef.getUserprofile().getCommunicationType()+"_Payload.xml");
		hidef.setPayloadFileName(auth.getName()+"_"+hidef.getUserprofile().getCommunicationType()+"_Payload.xml");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(personXMLStringValue);
		fileWriter.close();
		
		
		return personXMLStringValue;
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
	public String generateCBCPackage(HidefVo hidef) throws Exception {
		
		
		ctcDataSaveService.saveCtcData(hidef);
		
		// TODO Auto-generated method stub
		String privarecert = fetchProperties("privatecertpath");
		String publiccertpath = fetchProperties("publiccertpath");
		String metaDataPath = fetchProperties("metadataPath");
		String payloadPath = fetchProperties("payloadPath");
		String payloadxml = payloadPath+"/"+hidef.getPayloadFileName();
		String metadataxml = metaDataPath+"/"+hidef.getMetaDataFileName();
		String keystoretype = fetchProperties("confCertType");
		String keystorefile = "";
		keystorefile = hidef.getUserprofile().getConfigurationFileText();		
		String keystorepwd = fetchProperties("confCertKeyStorePwd");
		String keypwd = fetchProperties("confCertKeyPwd");
		String alias = fetchProperties("confCertAlias");
		PrivateKey myPrivateKey = null;
		X509Certificate myPublicCert = null;
		X509Certificate receiverPublicCert = null;
		String senderGiin = "";
		String receiverGiin = "";
		int taxyear = 2017;
		String targetFolderPath = fetchProperties("packageTargetFolder");
		String publicCertPath = "";
		publicCertPath = hidef.getUserprofile().getPublicCertFileName();
		receiverPublicCert = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new FileInputStream(publicCertPath));
		
		
		 
	    myPrivateKey =UtilShared.getPrivateKeyOriginal(keystoretype.trim(),keystorefile.trim(),
			  keystorepwd.trim(), keypwd.trim(), alias.trim()); 
	    System.out.println("Private Key =======>"+myPrivateKey.getEncoded());
	    myPublicCert =UtilShared.getCert(keystoretype.trim(), keystorefile.trim(),
			 keystorepwd.trim(), alias.trim());
	    System.out.println("myPublicCert =======>"+myPublicCert.getEncoded());
	    FATCAPackager fatcaPackager = new FATCAPackager(); 
		String folderPath = fatcaPackager.signAndCreatePkgStreaming(payloadxml, myPrivateKey, myPublicCert, senderGiin, receiverGiin, receiverPublicCert, taxyear, metadataxml, targetFolderPath);
	    File file = new File(folderPath);
		return file.toString();
	}

}

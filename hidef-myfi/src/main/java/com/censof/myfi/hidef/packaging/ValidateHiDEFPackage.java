package com.censof.myfi.hidef.packaging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPathExpressionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.censof.myfi.hidefmyfi.configuration.HiDEFException;

public class ValidateHiDEFPackage {
	protected static Logger logger = LoggerFactory.getLogger(ValidateHiDEFPackage.class);

	public static boolean validateSize(String filename) throws HiDEFException {
		try {
			boolean isValid = false;
			File file = new File(filename);
			long fileSizeInMB = (file.length() / 1024) / 1024;

			if (fileSizeInMB < 250) {
				isValid = true;
			} else {
				isValid = false;
				throw new HiDEFException("File Size is more than  250 MB", "RC032");
			}

			return isValid;
		} finally {

		}
	}

	public static boolean validateForeignFileName(String filename)
			throws HiDEFException, ClassNotFoundException, SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmssSSS'Z'");
		boolean isValid = false;
		Connection connection = null;
		try {
			int i = 0;
			i = filename.split(".zip")[0].split("_").length;
			if (i < 3) {
				throw new HiDEFException("Package does not contain _", "RC008");
			}
			String communicationType = null;
			String utcTimeformat = null;
			String incomingFileName = filename.split(".zip")[0];
			// TODO Country code Need to validate
			String countryCode = incomingFileName.split("_")[0];

			
			  String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; String url =
			  "jdbc:sqlserver://172.20.251.2;DatabaseName=hidefdb"; String userName
			  ="fatca"; String password ="axway";
			 

			/*String userName = TuningParameters.getInstance().getString("userName", "");
			String password = TuningParameters.getInstance().getString("password", "");
			String url = TuningParameters.getInstance().getString("url", "");
			String driver = TuningParameters.getInstance().getString("driver", "");*/

			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, password);
			if (connection != null) {
				logger.debug("Connection Success");
				Statement st = connection.createStatement();
				ResultSet rs = null;
				String sql = "select count(*) as total from hidefdb.dbo.hicountry where countrycode= '" + countryCode
						+ "'";
				rs = st.executeQuery(sql);
				while (rs.next()) {
					logger.info(rs.getString("total"));
					if (Integer.parseInt(rs.getString("total")) >= 1) {
						isValid = true;
					} else {
						throw new HiDEFException("Sender Country Code is Invalid", "RC008");
					}
				}

				rs.close();
				st.close();
				connection.close();
			}

			// TODO communicationType Need to validare
			communicationType = incomingFileName.split("_")[1];
			utcTimeformat = incomingFileName.split("_")[2];
			logger.debug("incomingFileName==>" + incomingFileName + "  countryCode==>" + countryCode
					+ "  communicationType=>" + communicationType + "utcTimeformat==>" + utcTimeformat);
			if (utcTimeformat.length() == 19) {
				format.parse(utcTimeformat);
			} else {
				throw new HiDEFException("Package is contains invalid Timestamp", "RC018");
			}
			isValid = true;
		} catch (ParseException e) {
			isValid = false;
			e.printStackTrace();
			throw new HiDEFException("InValid timestamp", "RC018 ");

		}

		return isValid;
	}

	public static boolean validateLocalFIFileName(String filename) throws HiDEFException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmssSSS'Z'");
		boolean isValid = false;
		try {
			int i = filename.split(".zip")[0].split("_").length;
			if (i < 3) {
				throw new HiDEFException("Package does not contain _", "RC008");
			}
			String communicationType = null;
			String utcTimeformat = null;
			String incomingFileName = filename.split(".zip")[0];
			// TODO CRSID Need to validate
			String crsId = incomingFileName.split("_")[0];
			// TODO communicationType Need to validate
			communicationType = incomingFileName.split("_")[2];
			utcTimeformat = incomingFileName.split("_")[3];
			logger.debug("incomingFileName==>" + incomingFileName + "  countryCode==>" + crsId + "  communicationType=>"
					+ communicationType + "utcTimeformat==>" + utcTimeformat);
			if (utcTimeformat.length() == 19) {
				format.parse(utcTimeformat);
			} else {
				throw new HiDEFException("Package is contains invalid Timestamp", "RC018");
			}
			isValid = true;
		} catch (ParseException e) {
			isValid = false;
			e.printStackTrace();
			throw new HiDEFException("InValid timestamp", "RC018 ");

		} catch (Exception e) {
			isValid = false;
			throw new HiDEFException(" Packet file naming convention doesnot  the symbol underscore _", "RC018");
		}
		return isValid;
	}

	public static double getFolderSize(File directory) {
		double length = 0;
		for (File file : directory.listFiles()) {
			if (file.isFile())
				length += file.length();
			else
				length += getFolderSize(file);
		}
		return length;
	}

	public static boolean validatePayloadXMLSchema(String outfile, String payloadXSDPath) throws HiDEFException {
		// TODO Auto-generated method stub
		logger.debug("Out File ======>" + outfile);
		logger.debug("payloadXSDPath ======>" + payloadXSDPath);
		boolean isValid = false;
		File schemaFile = new File(payloadXSDPath);
		Source xmlFile = new StreamSource(new File(outfile));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			logger.info(xmlFile.getSystemId() + " is valid");
			isValid = true;
		} catch (SAXException e) {
			isValid = false;
			logger.debug(xmlFile.getSystemId() + " is NOT valid reason:" + e);
			throw new HiDEFException("The referenced file failed validation against XML Schema", "50007");
		} catch (IOException ex) {

		}
		return isValid;

	}

	public static boolean validateMetaDataXMLSchema(String entry, String metaDataXSDPath) throws HiDEFException {
		// TODO Auto-generated method stub
		boolean isValid = false;
		File schemaFile = new File(metaDataXSDPath);
		Source xmlFile = new StreamSource(new File(entry));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			logger.info(xmlFile.getSystemId() + " is valid");
			isValid = true;
		} catch (SAXException e) {
			isValid = false;
			logger.info(xmlFile.getSystemId() + " is NOT valid reason:" + e);
			throw new HiDEFException("The referenced file failed validation against XML Schema", "50007");
		} catch (IOException ex) {

		}

		return isValid;

	}

	/***
	 * Method to test for antivirus
	 * 
	 * @param sourceFilePath
	 *            -- Orginal zip file path where the package is present
	 * @throws IOException
	 *             -- File not found exeption
	 * @author Praveen
	 */
	@SuppressWarnings("resource")
	public static void testVirusValidation(String sourceFilePath) throws IOException, HiDEFException {
		String filename = "";

		/***
		 * Getting the path from tuning properites -- folder where the virus report is
		 * stored
		 */
		// String reportFilePath = "E:\\ashwani\\Praveen_folder\\";
		String reportFilePath = "";
		/***
		 * Getting from the tuning properties -- folder where the antivirus exe
		 * installation file is stored
		 */
		// String fsecureinstallationPath = "\"C:\\Program Files
		// (x86)\\F-Secure\\Anti-Virus\\fsav.exe\"";
		// String triggerFSecureReport = "/beep /noboot /quar /spyware /ARCHIVE
		// /REPORT";
		String fsecureinstallationPath = "";
		String triggerFSecureReport = "";

		File sourcefile = new File(sourceFilePath);
		logger.debug(sourcefile.getName().replace(".zip", ".txt"));

		if (sourcefile.getName().contains(".zip")) {
			filename = sourcefile.getName().replace(".zip", ".txt");
		} else if (sourcefile.getName().contains(".pdf")) {
			filename = sourcefile.getName().replace(".pdf", ".txt");
		} else if (sourcefile.getName().contains(".exe")) {
			filename = sourcefile.getName().replace(".exe", ".txt");
		} else if (sourcefile.getName().contains(".txt")) {
			filename = sourcefile.getName().replace(".txt", "") + "_2.txt";
		} else {
			filename = "test2.txt";
		}

		String commandToExecute = "" + fsecureinstallationPath + " \"" + sourceFilePath + "\" " + triggerFSecureReport
				+ "=" + reportFilePath + filename + "";
		logger.debug(commandToExecute);

		/***
		 * running command in cmd inorder to check the number of virus
		 */
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", commandToExecute);
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line1;
		while (r.readLine() != null) {
			line1 = r.readLine();
			if (line1 == null) {
				// break;
			}
			logger.debug("To check here===>" + line1);
		}
		r.close();

		/***
		 * create the report file
		 */
		try {
			File file = new File(reportFilePath + filename);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			int i = 0;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains("Viruses:")) {
					if (i == 1) {
						logger.debug("Found===>" + line);
						int numberOfVirus = Integer.parseInt(line.split(":")[1].trim());
						logger.debug("Virus count in integer===>" + numberOfVirus);
						if (numberOfVirus >= 1) {
							logger.debug("Deleting file:::>" + file.getName());
							bufferedReader.close();
							fileReader.close();
							System.gc();
							try {
								java.lang.Thread.sleep(2000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							file.delete();
							throw new HiDEFException("File infected with virus", "RC002");
						} else {
							logger.debug("Deleting file:::>" + file.getName());
							bufferedReader.close();
							fileReader.close();
							System.gc();
							try {
								java.lang.Thread.sleep(2000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							file.delete();

						}
					}
					i++;
				}
			}
			fileReader.close();
			logger.debug(stringBuffer.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String crsCheckDuplicateDocRefId(String nameForError, String unsignPayloadFilePath, String source)
			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException, HiDEFException,
			SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String sessionId = "0";

		Connection dbConnection = null;
		String userName = "";
		String password = "";
		String url = "";
		String driver = "";

		Class.forName(driver);
		dbConnection = DriverManager.getConnection(url, userName, password);
		if (dbConnection != null) {
			logger.debug("Connection Success");
			Statement st = dbConnection.createStatement();
			ResultSet rs = null;
			String sql = "select NEXT VALUE FOR [dbo].CRS_general_seq_ids as sessionid ";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				sessionId = rs.getString("sessionid");
			}

			rs.close();
			st.close();
			dbConnection.close();
		}

		logger.info("Checking docRefId for file ======>" + unsignPayloadFilePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		Document doc = factory.newDocumentBuilder().parse(new File(unsignPayloadFilePath));
		// XPathExpression expr =
		// XPathFactory.newInstance().newXPath().compile("DocRefId");
		Set<String> ids = new HashSet<String>();

		Set<String> dupIds = new HashSet<String>();

		String docRefIdNodeName = "";

		NodeList nodeList = doc.getElementsByTagName("*");
		for (int i = 0; i < nodeList.getLength(); i++) {
			// Get element
			Element element = (Element) nodeList.item(i);
			if (element.getNodeName().contains("DocRefId")) {
				docRefIdNodeName = element.getNodeName();
				break;
			}
		}

		NodeList nodes = doc.getElementsByTagName(docRefIdNodeName);
		for (int i = 0; i < nodes.getLength(); i++) {
			String id = nodes.item(i).getTextContent();
			if (ids.contains(id)) {

				dupIds.add(id);

				logger.info("Duplicate Id is in ======>" + id);
				InsertDocRefIdErrorTransactionCode(nameForError, "80000", source, getXPath(nodes.item(i)), id,
						sessionId);
			} else {
				Connection connection = null;
				Class.forName(driver);
				connection = DriverManager.getConnection(url, userName, password);
				if (connection != null) {
					logger.debug("Connection Success");
					Statement st = connection.createStatement();
					ResultSet rs = null;
					String sql = "select " + "( select count(*) from crspayldfi  where DocRefId = '" + id + "') + "
							+ "( select count(*) from crspayldacctrep  where DocRefId = '" + id + "') as cnt";
					rs = st.executeQuery(sql);
					while (rs.next()) {
						if (Integer.parseInt(rs.getString("cnt")) >= 1) {

							dupIds.add(id);

							logger.info("Duplicate Id is in ======>" + id);

							InsertDocRefIdErrorTransactionCode(nameForError, "80000", source, getXPath(nodes.item(i)),
									id, sessionId);

						} else {
							ids.add(id);
							// sessionId="0";
						}
					}

					rs.close();
					st.close();
					connection.close();
				}

			}
		}

		if (dupIds.isEmpty()) {
			sessionId = "0";
		}

		return sessionId;

	}

	static int InsertDocRefIdErrorTransactionCode(String sTransmissionID, String sReasonCode, String source,
			String errorMessage, String errorRefId, String sessionId) {
		logger.debug("******In Inseting Database Method*******");
//		String userName = TuningParameters.getInstance().getString("userName", "");
//		String password = TuningParameters.getInstance().getString("password", "");
//		String url = TuningParameters.getInstance().getString("url", "");
//		String driver = TuningParameters.getInstance().getString("driver", "");

		
		 String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; String url =
		 "jdbc:sqlserver://172.20.251.2;DatabaseName=hidefdb"; String userName
		 ="fatca"; String password ="axway";
		

		logger.debug("driver from Tuning propertiess" + driver + "url" + url + "username" + userName + "password"
				+ password);
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userName, password);
			if (con != null) {
				logger.debug("Connection Success");
			} else {
				logger.debug("COnnection failed");
			}
			Statement stmt = con.createStatement();
			String sSQL = null;
			if (source.equalsIgnoreCase("LFI")) {
				sSQL = "INSERT INTO [dbo].[crssmtrans] (SessionId,TransmissionId, MsgTransmittingCountry, ErrorType ,ErrorCode,DocRefIDInError,FieldsInError,FieldPath)"
						+ " VALUES ( '" + sessionId + "' ,'" + sTransmissionID + "','MY','RF','" + sReasonCode + "','"
						+ errorRefId + "','DocRefId','" + errorMessage.replace("#document/", "") + "')";

			} else {
				sSQL = "INSERT INTO [dbo].[crssmtrans] (SessionId,TransmissionId, MsgTransmittingCountry, ErrorType ,ErrorCode,DocRefIDInError,FieldsInError,FieldPath)"
						+ " VALUES ( '" + sessionId + "' ,'" + sTransmissionID + "','" + sTransmissionID.split("_")[0]
						+ "','RF','" + sReasonCode + "','" + errorRefId + "','DocRefId','"
						+ errorMessage.replace("#document/", "") + "')";
			}
			stmt.execute(sSQL);

			con.close();
		} catch (ClassNotFoundException e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.debug("Exception While Inserting into Trasaction Table===>..." + errors.toString());
		} catch (SQLException e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.debug("Exception While Inserting into Trasaction Table===>..." + errors.toString());
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.debug("Exception While Inserting into Trasaction Table===>..." + errors.toString());
		}

		return 0;
	}

	private static String getXPath(Node node) {
		Node parent = node.getParentNode();
		if (parent == null) {
			return node.getNodeName();
		}
		return getXPath(parent) + "/" + node.getNodeName();
	}

	public static String crsCheckDuplicateCorDocRefId(String nameForError, String unsigedXml, String fileSource,String sessionId)
			throws SAXException, IOException, ParserConfigurationException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		Connection dbConnection = null;
		String userName = "";
		String password = "";
		String url = "";
		String driver = "";

		if(sessionId.equals("0")) {
		Class.forName(driver);
		dbConnection = DriverManager.getConnection(url, userName, password);
		if (dbConnection != null) {
			Statement st = dbConnection.createStatement();
			ResultSet rs = null;
			String sql = "select NEXT VALUE FOR [dbo].CRS_general_seq_ids as sessionid ";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				sessionId = rs.getString("sessionid");
			}

			rs.close();
			st.close();
			dbConnection.close();
		}
		}

		// TODO Auto-generated method stub
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		Document doc = factory.newDocumentBuilder().parse(new File(unsigedXml));
		Set<String> ids = new HashSet<String>();
		Set<String> dupes = new HashSet<String>();

		String docRefIdNodeName = "";

		NodeList nodeList = doc.getElementsByTagName("*");
		for (int i = 0; i < nodeList.getLength(); i++) {
			// Get element
			Element element = (Element) nodeList.item(i);
			if (element.getNodeName().contains("CorrDocRefId")) {
				docRefIdNodeName = element.getNodeName();
				break;
			}
		}

		NodeList nodes = doc.getElementsByTagName(docRefIdNodeName);
		for (int i = 0; i < nodes.getLength(); i++) {
			String id = nodes.item(i).getTextContent();
			if (ids.contains(id)) {
				dupes.add(id);
				InsertDocRefIdErrorTransactionCode(nameForError, "80011", fileSource, getXPath(nodes.item(i)), id,
						sessionId);
			} else {
				ids.add(id);
			}
		}
		logger.info("Corr_Doc_ref_id Total ids =" + ids.size() + "\nTotal Duplicates = " + dupes.size());


		//sessionId = checkReportingFICorDocRefId(nameForError, unsigedXml, fileSource, sessionId);

		if (dupes.isEmpty() && !sessionId.equals("0")) {
			sessionId = "0";
		}
		
		
		return sessionId;
	}

	public static String checkReportingFICorDocRefId(String nameForError, String unsigedXml, String fileSource,
			String sessionId)
			throws ClassNotFoundException, SQLException, SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub

		String userName = "";
		String password = "";
		String url = "";
		String driver = "";
		Set<String> dupes = new HashSet<String>();

		if (sessionId.equals("0")) {
			Connection dbConnection = null;
			Class.forName(driver);
			dbConnection = DriverManager.getConnection(url, userName, password);
			if (dbConnection != null) {
				Statement st = dbConnection.createStatement();
				ResultSet rs = null;
				String sql = "select NEXT VALUE FOR [dbo].CRS_general_seq_ids as sessionid ";
				rs = st.executeQuery(sql);
				while (rs.next()) {
					sessionId = rs.getString("sessionid");
				}

				rs.close();
				st.close();
				dbConnection.close();
			}
		}

		// TODO Auto-generated method stub
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		Document doc = factory.newDocumentBuilder().parse(new File(unsigedXml));

		NodeList nodeList = doc.getElementsByTagName("*");
		for (int i = 0; i < nodeList.getLength(); i++) {
			// Get element
			Element element = (Element) nodeList.item(i);
			// validation for reporting FI
			if (element.getNodeName().contains("ReportingFI")) {
				NodeList childNodes = element.getChildNodes();
				if (childNodes != null) {
					for (int j = 0; j < childNodes.getLength(); j++) {
						if (childNodes.item(j).getNodeName().contains("DocSpec")) {
							NodeList childOfChildNodes = childNodes.item(j).getChildNodes();
							if (childOfChildNodes != null) {
								String oecdName = "";
								String corrDocRefId = "";
								String corrPath = "";
								for (int k = 0; k < childOfChildNodes.getLength(); k++) {
									if (childOfChildNodes.item(k).getNodeName().contains("DocTypeIndic")) {

										oecdName = childOfChildNodes.item(k).getTextContent();

									} else if (childOfChildNodes.item(k).getNodeName().contains("CorrDocRefId")) {
										corrDocRefId = childOfChildNodes.item(k).getTextContent();
										logger.info("corrDocRefId =====>"+corrDocRefId);
										corrPath = getXPath(childOfChildNodes.item(k));
									}
								}
								if (oecdName.equals("OECD2") || oecdName.equals("OECD12") || oecdName.equals("OECD3")
										|| oecdName.equals("OECD13")) {

									int countforCRSMASFI = 0;
									int countforCrspayldfi = 0;
									Connection dbConnection = null;
									Class.forName(driver);
									dbConnection = DriverManager.getConnection(url, userName, password);
									if (dbConnection != null) {
										Statement st = dbConnection.createStatement();
										ResultSet rs = null;
										String sql = "select count(*) as cnt from crsmastfi where DocRefId = '"+corrDocRefId+"'";
										logger.info("To check mastfi sql here ====>"+sql);
										rs = st.executeQuery(sql);
										while (rs.next()) {
											countforCRSMASFI = Integer.parseInt(rs.getString("cnt"));
										}

										rs.close();
										st.close();
										dbConnection.close();
									}

									if (countforCRSMASFI <= 0) {
										dupes.add(corrDocRefId);
										InsertDocRefIdErrorTransactionCode(nameForError, "80002", fileSource, corrPath,
												corrDocRefId, sessionId);
									}

									Class.forName(driver);
									dbConnection = DriverManager.getConnection(url, userName, password);
									if (dbConnection != null) {
										Statement st = dbConnection.createStatement();
										ResultSet rs = null;
										String sql = "select count(*) as cnt from crspayldfi where DocRefId = '"+corrDocRefId+"'";
										logger.info("To check crspayldfi sql here ====>"+sql);
										rs = st.executeQuery(sql);
										while (rs.next()) {
											countforCrspayldfi = Integer.parseInt(rs.getString("cnt"));
										}

										rs.close();
										st.close();
										dbConnection.close();
									}

									if (countforCrspayldfi >= 1) {
										dupes.add(corrDocRefId);
										InsertDocRefIdErrorTransactionCode(nameForError, "80003", fileSource, corrPath,
												corrDocRefId, sessionId);
									}

								}
							}
						}
					}
				}
			}

			// validation for reporting group
			if (element.getNodeName().contains("ReportingGroup")) {
				NodeList firstChildNodes = element.getChildNodes();
				if (firstChildNodes != null) {
					for (int p = 0; p < firstChildNodes.getLength(); p++) {
						NodeList childNodes = firstChildNodes.item(p).getChildNodes();
						if (childNodes != null) {
							for (int j = 0; j < childNodes.getLength(); j++) {
								if (childNodes.item(j).getNodeName().contains("DocSpec")) {
									NodeList childOfChildNodes = childNodes.item(j).getChildNodes();
									if (childOfChildNodes != null) {
										String oecdName = "";
										String corrDocRefId = "";
										String corrPath = "";
										for (int k = 0; k < childOfChildNodes.getLength(); k++) {
											if (childOfChildNodes.item(k).getNodeName().contains("DocTypeIndic")) {
												oecdName = childOfChildNodes.item(k).getTextContent();

											} else if (childOfChildNodes.item(k).getNodeName()
													.contains("CorrDocRefId")) {
												corrDocRefId = childOfChildNodes.item(k).getTextContent();
												corrPath = getXPath(childOfChildNodes.item(k));
											}
										}
										if (oecdName.equals("OECD2") || oecdName.equals("OECD12")
												|| oecdName.equals("OECD3") || oecdName.equals("OECD13")) {
											int countforCrsmastacctrep = 0;
											int countforCrspayldacctrep = 0;
											Connection dbConnection = null;
											Class.forName(driver);
											dbConnection = DriverManager.getConnection(url, userName, password);
											if (dbConnection != null) {
												Statement st = dbConnection.createStatement();
												ResultSet rs = null;
												String sql = "select count(*) as cnt from crsmastacctrep where DocRefId = '"+corrDocRefId+"'";
												logger.info("To check crsmastacctrep sql here ====>"+sql);
												rs = st.executeQuery(sql);
												while (rs.next()) {
													countforCrsmastacctrep = Integer.parseInt(rs.getString("cnt"));
												}

												rs.close();
												st.close();
												dbConnection.close();
											}

											if (countforCrsmastacctrep <= 0) {
												dupes.add(corrDocRefId);
												InsertDocRefIdErrorTransactionCode(nameForError, "80002", fileSource,
														corrPath, corrDocRefId, sessionId);
											}

											Class.forName(driver);
											dbConnection = DriverManager.getConnection(url, userName, password);
											if (dbConnection != null) {
												Statement st = dbConnection.createStatement();
												ResultSet rs = null;
												String sql = "select count(*) as cnt from crspayldacctrep where DocRefId = '"+corrDocRefId+"'";
												logger.info("To check crspayldacctrep sql here ====>"+sql);
												rs = st.executeQuery(sql);
												while (rs.next()) {
													countforCrspayldacctrep = Integer.parseInt(rs.getString("cnt"));
												}

												rs.close();
												st.close();
												dbConnection.close();
											}

											if (countforCrspayldacctrep >= 1) {
												dupes.add(corrDocRefId);
												InsertDocRefIdErrorTransactionCode(nameForError, "80003", fileSource,
														corrPath, corrDocRefId, sessionId);
											}

										}

									}
								}
							}
						}
					}
				}
			}

		}

		if (dupes.isEmpty() && !sessionId.equals("0")) {
			sessionId = "0";
		}

		return sessionId;
	}
}
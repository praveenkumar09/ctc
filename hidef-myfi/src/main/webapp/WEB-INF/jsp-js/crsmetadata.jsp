<link type="text/css" href="${pageContext.request.contextPath}/css/panel-border.css" rel="stylesheet">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/crsmetadata.js"></script>
<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
		 <form:form  modelAttribute="hidef" method="POST" name="crsmetadata" id="crsmetadata">
			<div class="panel-heading">
				<strong>METADATA</strong>
			</div>
			<div class="panel-body">
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label for="" path ="crsmetadata.sendingCountry">Sending Country<font
									color='red'>*</font>:</form:label> <form:select path ="crsmetadata.sendingCountry"
							class="form-control" id="sendingCountry" disabled="true">
							<form:option value="0" >Please choose</form:option>
						<%-- 	<form:option value="1">MY</form:option>
							<form:option value="2">SG</form:option>
							<form:option value="3">CN</form:option>
							<form:option value="4">AU</form:option>
							<form:option value="5">US</form:option> --%>
							<c:forEach items="${countryList}" var="country">
							<form:option value="${country.countryCode}">
								${country.countryCode}
							</form:option>
						</c:forEach>
						</form:select>
						<font color='red'><span id="sendingCountryError"
								class="mandatory-flag"></span></font>
					</div>
					<div class="form-group col-md-6">
						<form:label for="" path ="crsmetadata.receivingCountry">Receiving Country<font
									color='red'>*</font>:</form:label> <form:select path ="crsmetadata.receivingCountry"
							class="form-control" id="receivingCountry" disabled="true">
							<form:option value="0" >Please choose</form:option>
						<%-- 	<form:option value="1">MY</form:option>
							<form:option value="2">SG</form:option>
							<form:option value="3">CN</form:option>
							<form:option value="4">AU</form:option>
							<form:option value="5">US</form:option> --%>
							<c:forEach items="${countryList}" var="country">
							<form:option value="${country.countryCode}">
								${country.countryCode}
							</form:option>
						</c:forEach>
						</form:select>
						<font color='red'><span id="receivingCountryError"
								class="mandatory-flag"></span></font>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path ="crsmetadata.messageTypeIndic">Message Type Indicator:</form:label>	
						<form:select path ="crsmetadata.messageTypeIndic"
							class="form-control" id="msgType">
							<form:option value="0" >Please choose</form:option>
						
							<c:forEach items="${messageTypeIndic}" var="msftypeindic">
							<form:option value="${msftypeindic.id}">
								${msftypeindic.indic}
							</form:option>
						</c:forEach>
						</form:select>
					</div>
					<div class="form-group col-md-6">
						<label>Message Type<font
									color='red'>*</font>:</label> <form:input type="text" path ="crsmetadata.messageType"
							class="form-control" id="messageType" value="CRS"
							placeholder="Message Type" readonly="true"></form:input>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<label>Warning:</label>
						<form:textarea class="form-control" rows="5" id="warning"
							placeholder="Warning" path ="crsmetadata.warning"></form:textarea>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<label>Contact:</label>
						<form:textarea class="form-control" rows="5" placeholder="Contact" path ="crsmetadata.contact"></form:textarea>
					</div>
				</div>
				<br />
				<div class="form-group">
					<%-- <div class="form-group col-md-6">
						<label class="">Reporting Period:</label> 
						<form:input type="text"
							class="form-control" id="reportingPeriod"
							placeholder="Reporting Period" path ="crsmetadata.reportingPeriod" maxlength="4" onkeypress='validate(event)'></form:input>
							
					<div class="input-group date" data-provide="datepicker">
								<form:input type="text" class="form-control"
									id="reportingPeriod" placeholder="Reporting Period"
									path="crsmetadata.reportingPeriod"></form:input>
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span>
								</span> <font color='red'><span id="reportingPeriodError"
									class="mandatory-flag"></span></font>
							</div>
					</div> --%>
					<div class="form-group col-md-6">
							<form:label class="" path="metadata.reportingPeriod">Reporting Period<font
									color='red'>*</font>:</form:label>
							<div class="input-group date" data-provide="datepicker">
								<form:input type="text" class="form-control"
									id="reportingPeriod" placeholder="Reporting Period"
									path="crsmetadata.reportingPeriod"></form:input>
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span>
								</span> 
							</div>
							<font color='red'><span id="reportingPeriodError"
									class="mandatory-flag"></span></font>
							
					</div>
					<font color='red'><span id="reportingPeriodError"
								class="mandatory-flag"></span></font>
					<div class="form-group col-md-6">
						<label class="">Tax Year<font
									color='red'>*</font>:</label> <form:input type="text"
							class="form-control" id="taxYear" placeholder="Tax Year" path ="crsmetadata.taxYear" maxlength="4" onkeypress='validate(event)'></form:input>
							<font color='red'><span id="taxYearError"
								class="mandatory-flag"></span></font>
					</div>
				</div>
				<div class="form-group">
					<div class="form-group col-md-6">
						<label class="">File Creation Time Stamp<font
									color='red'>*</font>:</label> <form:input
							type="text" class="form-control" id="formCreationTimeStamp"
							placeholder="File Creation Timestamp" path ="crsmetadata.fileCreationTimestramp" onclick='generateTimestramp();'></form:input>
							<font color='red'><span id="formCreationTimeStampError"
								class="mandatory-flag"></span></font>
					</div>
					<div class="form-group col-md-6">
						<label class="">Communication Type<font
									color='red'>*</font>:</label> <form:input type="text"
							class="form-control" value="CRS" id="communicationType" path ="crsmetadata.communicationType"
							placeholder="Communication Type" readonly="true"></form:input>
					</div>
				</div>
				<%-- <div class="form-group">
					<div class="form-group col-md-6">
						<form:label path="crsmetadata.corMessageReferenceId">Corr Message Reference Id:</form:label> <form:input type="text"
							class="form-control" id="corrMessageReferenceId" path="crsmetadata.corMessageReferenceId"
							placeholder="Corr Message Reference Id"></form:input>
					</div>
				</div> --%>
				<div class="form-group">
					<div class="form-group col-md-6">
						<label class="">Sender File Id<font
									color='red'>*</font>:</label> <form:input type="text"
							class="form-control" id="senderFileId" path ="crsmetadata.senderFileId"
							placeholder="Sender File Id"></form:input>
							<font color='red'><span id="senderFileIdError"
								class="mandatory-flag"></span></font>
					</div>
					<div class="form-group col-md-6">
						<label for="">File Format Code:</label> <form:select
							class="form-control" id="fileFormatCode" path ="crsmetadata.fileFormatCode" disabled="true">
							<form:option value="0">Please choose</form:option>
								<%-- 	<form:option value="1">MY</form:option>
							<form:option value="2">SG</form:option>
							<form:option value="3">CN</form:option>
							<form:option value="4">AU</form:option>
							<form:option value="5">US</form:option> --%>
								<c:forEach items="${fileformatcodeList}"
									var="fileformatcodeList">
									<form:option value="${fileformatcodeList.CBCFileFormatType}">
								${fileformatcodeList.CBCFileFormatType}
							</form:option>
								</c:forEach>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<div class="form-group col-md-6">
						<label for="">Binary Encoding:</label> <form:select
							class="form-control" id="binaryEncoding" path ="crsmetadata.binaryEncoding" disabled="true">
							<form:option value="0">Please choose</form:option>
								<%-- 	<form:option value="1">MY</form:option>
							<form:option value="2">SG</form:option>
							<form:option value="3">CN</form:option>
							<form:option value="4">AU</form:option>
							<form:option value="5">US</form:option> --%>
								<c:forEach items="${binaryencodingList}"
									var="binaryencodingList">
									<form:option value="${binaryencodingList.type}">
								${binaryencodingList.type}
							</form:option>
								</c:forEach>
						</form:select>
					</div>
					<div class="form-group col-md-6">
						<label class="">Message Reference Id<font
									color='red'>*</font>: </label> <form:input type="text"
							class="form-control" id="messageRefId"
							placeholder="Message Reference Id" path ="crsmetadata.messageReferenceId"></form:input>
							<font color='red'><span id="messageRefIdError"
								class="mandatory-flag"></span></font>
					</div>
				</div>
				<div class="form-group col-md-6">
						<label class="">Sender Contact Email Address:</label> <form:input type="text"
							class="form-control" id="senderContactEmailAddress"
							placeholder="Sender Contact Email Address" path ="crsmetadata.senderContactEmail" disabled="true"></form:input>
					</div>
					<div class="form-group col-md-6">
						<label class="">Sending Company IN:</label> <form:input type="text"
							class="form-control" id="sendingCompanyIN"
							placeholder="Sending Company IN" path ="MycbcId" disabled="true"></form:input>
					</div>
				<div class="clearfix"></div>
										<br />
				<div class="text-center">
					<button id="singlebutton" name="singlebutton"
						onClick="showReportingFI();return false;" class="btn btn-primary">Next</button>
				</div>
			</div>
			</form:form>
			<!-- <div class="form-group">
			<div class="text-center">
			<button id="singlebutton" name="singlebutton" onClick="generateCRSMetaData();return false;"
							class="btn btn-success">Generate Metadata</button>
			</div>
			</div> -->
		</div>
	</div>
</div>

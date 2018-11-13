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
						<form:label for="" path ="crsmetadata.sendingCountry">Sending Country:</form:label> <form:select path ="crsmetadata.sendingCountry"
							class="form-control" id="sendingCountry">
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
					</div>
					<div class="form-group col-md-6">
						<form:label for="" path ="crsmetadata.receivingCountry">Receiving Country:</form:label> <form:select path ="crsmetadata.receivingCountry"
							class="form-control" id="receivingCountry">
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
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path ="crsmetadata.messageTypeIndic">Message Type Indicator:</form:label> <form:input type="text" path ="crsmetadata.messageTypeIndic"
							class="form-control" id="msgType"
							placeholder="Message Type Indicator"></form:input>
					</div>
					<div class="form-group col-md-6">
						<label>Message Type:</label> <form:input type="text" path ="crsmetadata.messageType"
							class="form-control" id="sendContactEmailAddress" value="CRS"
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
					<div class="form-group col-md-6">
						<label class="">Reporting Period:</label> <form:input type="text"
							class="form-control" id="reportingPeriod"
							placeholder="Reporting Period" path ="crsmetadata.reportingPeriod"></form:input>
					</div>
					<div class="form-group col-md-6">
						<label class="">Tax Year:</label> <form:input type="text"
							class="form-control" id="taxYear" placeholder="Tax Year" path ="crsmetadata.taxYear"></form:input>
					</div>
				</div>
				<div class="form-group">
					<div class="form-group col-md-6">
						<label class="">File Creation Time Stamp:</label> <form:input
							type="text" class="form-control" id="formCreationTimeStamp"
							placeholder="File Creation Timestamp" path ="crsmetadata.fileCreationTimestramp"></form:input>
					</div>
					<div class="form-group col-md-6">
						<label class="">Communication Type:</label> <form:input type="text"
							class="form-control" value="CRS" id="communicationType" path ="crsmetadata.communicationType"
							placeholder="Communication Type" readonly="true"></form:input>
					</div>
				</div>
				<div class="form-group">
					<div class="form-group col-md-6">
						<label class="">Sender File Id:</label> <form:input type="text"
							class="form-control" id="senderFileId" path ="crsmetadata.senderFileId"
							placeholder="Sender File Id"></form:input>
					</div>
					<div class="form-group col-md-6">
						<label for="">File Format Code:</label> <form:select
							class="form-control" id="fileFormatCode" path ="crsmetadata.fileFormatCode">
							<option>JPG</option>
							<option>PDF</option>
							<option>RTF</option>
							<option>TXT</option>
							<option>XML</option>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<div class="form-group col-md-6">
						<label for="">Binary Encoding:</label> <form:select
							class="form-control" id="binaryEncoding" path ="crsmetadata.binaryEncoding">
							<option>BASE64</option>
							<option>NONE</option>
						</form:select>
					</div>
					<div class="form-group col-md-6">
						<label class="">Message Reference Id: </label> <form:input type="text"
							class="form-control" id="messageRefId"
							placeholder="Message Reference Id" path ="crsmetadata.messageReferenceId"></form:input>
					</div>
				</div>
				<div class="form-group col-md-6">
						<label class="">Sender Contact Email Address:</label> <form:input type="text"
							class="form-control" id="senderContactEmailAddress"
							placeholder="Sender Contact Email Address" path ="crsmetadata.senderContactEmail"></form:input>
					</div>
					<div class="form-group col-md-6">
						<label class="">Sending Company IN:</label> <form:input type="text"
							class="form-control" id="sendingCompanyIN"
							placeholder="Sending Company IN" path ="crsmetadata.sendingCompanyIn"></form:input>
					</div>
				<div class="clearfix"></div>
										<br />
				<div class="text-center">
					<button id="singlebutton" name="singlebutton"
						onClick="showReportingFI();return false;" class="btn btn-primary">Next</button>
				</div>
			</div>
			</form:form>
		</div>
	</div>
</div>

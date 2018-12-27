<link type="text/css"
	href="${pageContext.request.contextPath}/css/panel-border.css"
	rel="stylesheet">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/cbcmetadata.js"></script>
	
<script type="text/javascript">
/* if('${hidef.metadata.fileTypeIndic}' == 'true'){
		$('[name="fileTypeIndic"]').first().val("true");

if('${hidef.metadata.fileTypeIndic}' == 'false'){
	$('[name="fileTypeIndic"]').first().val("false");
} */
var fileTypeIndic = '${hidef.metadata.fileTypeIndic}';
if(fileTypeIndic == 'true'){
	document.getElementById("fileTypeIndic").selectedIndex = 1;
}if(fileTypeIndic == 'false'){
	document.getElementById("fileTypeIndic").selectedIndex = 2;
}
</script>
	
<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
			<form:form modelAttribute="hidef" method="POST" name="cbcmetadata"
				id="cbcmetadata">
				<div class="panel-heading">
					<strong>METADATA</strong>
				</div>
				<div class="panel-body">
					<br />
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label for="" path="metadata.sendingCountry">Sending Country<font
									color='red'>*</font>:</form:label>
							<form:select class="form-control" id="sendingCountry"
								path="metadata.sendingCountry" disabled="true">
								<form:option value="0">Please choose</form:option>
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
						<div class="clearfix"></div>
						<%-- <div class="form-group col-md-6">
						<form:label for="" path="metadata.receivingCountry">Receiving Country:</form:label>
						 <form:select
							class="form-control" id="receivingCountry" path="metadata.receivingCountry" readonly="true">
							<form:option value="0">Please choose</form:option>
							<form:option value="1">MY</form:option>
							<form:option value="2">SG</form:option>
							<form:option value="3">CN</form:option>
							<form:option value="4">AU</form:option>
							<form:option value="5">US</form:option>
							<c:forEach items="${countryList}" var="country">
							<form:option value="${country.countryCode}">
								${country.countryCode}
							</form:option>
						</c:forEach>
						</form:select>
					</div> --%>
					</div>
					<br />
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label path="metadata.messageTypeIndic">Message Type Indicator:</form:label>
							<form:select class="form-control" id="msgType"
								path="metadata.messageTypeIndic">
								<form:option value="0">Please choose</form:option>
								<c:forEach items="${cbcMessageTypeindic}" var="messageIndic">
									<form:option value="${messageIndic.indic}">
								${messageIndic.indic}
							</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="form-group col-md-6">
							<form:label path="metadata.msgType">Message Type<font
									color='red'>*</font>:</form:label>
							<form:input type="text" class="form-control"
								id="sendContactEmailAddress" value="CBC"
								placeholder="Message Type" path="metadata.msgType"
								disabled="true"></form:input>
						</div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label path="metadata.warning">Warning:</form:label>
							<form:textarea class="form-control" path="metadata.warning"
								rows="5" id="warning" placeholder="Warning"></form:textarea>
						</div>
					<!-- </div>
					<div class="form-group"> -->
						<div class="form-group col-md-6">
							<form:label path="metadata.contact">Contact:</form:label>
							<form:textarea class="form-control" path="metadata.contact"
								rows="5" placeholder="Contact"></form:textarea>
							<form:errors path="metadata.contact" cssClass="error" />
						</div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label class="" path="metadata.reportingPeriod">Reporting Period<font
									color='red'>*</font>:</form:label>
							<div class="input-group date" data-provide="datepicker">
								<form:input type="text" class="form-control"
									id="reportingPeriod" placeholder="Reporting Period"
									path="metadata.reportingPeriod"></form:input>
								<span class="input-group-addon"> <span
									class="glyphicon glyphicon-calendar"></span></span>								
							</div>
							<font color='red'><span id="reportingPeriodError"
									class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label class="" path="metadata.taxYear">Tax Year<font
									color='red'>*</font>:</form:label>
							<form:input type="text" class="form-control" id="taxYear"
								placeholder="Tax Year" path="metadata.taxYear" maxlength="4"
								onchange='generatemessageReferenceId(this.value,event)'></form:input>
								 <font color='red'><span id="taxYearError"
									class="mandatory-flag"></span></font>
						</div>
					</div>
					<div class="clearfix"></div>
					<br/>
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label class="" path="metadata.formCreationTimeStamp">File Creation Time Stamp<font
									color='red'>*</font>:</form:label>
							<form:input type="text" class="form-control"
								id="formCreationTimeStamp" path="metadata.formCreationTimeStamp"
								placeholder="File Creation Timestamp"
								onclick='generateTimestramp();'></form:input>
							<font color='red'><span id="formCreationTimeStampError"
								class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label class="" path="metadata.communicationType">Communication Type<font
									color='red'>*</font>:</form:label>
							<form:input type="text" class="form-control" value="CBC"
								id="communicationType" path="metadata.communicationType"
								placeholder="Communication Type" disabled="true"></form:input>
						</div>
					</div>
					<div class="clearfix"></div>
					<br/>
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label class="" path="metadata.senderFileId">Sender File Id<font
									color='red'>*</font>:</form:label>
							<form:input type="text" class="form-control" id="senderFileId"
								path="metadata.senderFileId" placeholder="Sender File Id"
								data-toggle="tooltip" title="Please key in Tax year for Sender File id to be auto generated"
								readonly="true"></form:input>
							<font color='red'><span id="senderFileIdError"
								class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label for="" path="metadata.fileFormatCode">File Format Code:</form:label>
							<form:select class="form-control" id="fileFormatCode"
								path="metadata.fileFormatCode" disabled="true">
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
					<div class="clearfix"></div>
					<br/>
					<div class="form-group">
						<div class="form-group col-md-6">
						<form:label path="metadata.binaryEncoding">Binary Encoding:</form:label>
						<form:input type="text" class="form-control"
							id="binaryEncoding" value="None"
							placeholder="Message Type" path="metadata.binaryEncoding"
							readonly="true"></form:input>
					   </div>
						<div class="form-group col-md-6">
							<form:label class="" path="metadata.messageRefId">Message Reference Id: </form:label>
							<form:input type="text" path="metadata.messageRefId"
								class="form-control" id="messageRefId"
								placeholder="Message Reference Id" disabled="true"></form:input>
						</div>
					</div>
					<div class="clearfix"></div>
					<br/>
					<div class="form-group col-md-6">
						<form:label class="" path="metadata.senderContactEmailAddress">Sender Contact Email Address:</form:label>
						<form:input type="text" class="form-control"
							id="senderContactEmailAddress"
							placeholder="Sender Contact Email Address"
							path="metadata.senderContactEmailAddress" disabled="true"></form:input>
					</div>
					<div class="form-group col-md-6">
						<form:label class="" path="metadata.sendingCompanyIN">Sending Company IN:</form:label>
						<form:input type="text" class="form-control" id="sendingCompanyIN"
							placeholder="Sending Company IN" path="metadata.sendingCompanyIN"
							readonly="true"></form:input>
					</div>
                    <div class="clearfix"></div>
                    <br/>
					<div class="form-group col-md-6">
						<form:label class="" path="metadata.language">Language:</form:label>
						<form:input type="text" maxlength="2" class="form-control"
							id="language" placeholder="Language" path="metadata.language"></form:input>
					</div>
					<div class="form-group col-md-6">
						<form:label for="" path="metadata.fileTypeIndic">File Type Indicator:</form:label>
						<form:select class="form-control" id="fileTypeIndic"
							path="userprofile.fileTypeIndic" name="fileTypeIndic">
							<form:option value="0">Please choose</form:option>
							<%-- 	<form:option value="1">MY</form:option>
							<form:option value="2">SG</form:option>
							<form:option value="3">CN</form:option>
							<form:option value="4">AU</form:option>
							<form:option value="5">US</form:option> --%>
							<c:forEach items="${fileTypeIndicList}" var="fileTypeIndicList">
								<form:option value="${fileTypeIndicList.indic}">
								${fileTypeIndicList.indic}
							</form:option>
							</c:forEach>
						</form:select>
					</div>
					<div class="clearfix"></div>
					<div class="form-group col-md-6">
						<form:label path="userprofile.ctsTransId">Transmission Id:</form:label>
						<form:input type="text" class="form-control" id="ctsTransId"
							placeholder="CTS Transmission Id" path="userprofile.ctsTransId"></form:input>
					</div>
					<div class="clearfix"></div>
					<div class="form-group col-md-6">
						<div id="metaDataReceivingCountryList"></div>
						<div id="pager"></div>
					</div>
					<input type="hidden" id="recievingCountry"
						value='${recievingCountry}' />
					<div class="clearfix"></div>
					<br />
					<!-- <div class="text-center">
					<button id="metaDataButton" name="singlebutton"
						onClick="showReportingEntity();return false;" class="btn btn-primary">Next</button>
				</div> -->
					<c:choose>
						<c:when test="${hidef.isSummaryView=='true'}">
							<div class="text-center">
								<button id="metaDataButton" name="singlebutton"
									onClick="viewReportingEntity();return false;"
									class="btn btn-primary">View Next Tab</button>
							</div>
						</c:when>
						<c:otherwise>
							<div class="text-center">
								<button id="metaDataButton" name="singlebutton"
									onClick="showReportingEntity();return false;"
									class="btn btn-primary">Next</button>
							</div>
						</c:otherwise>
					</c:choose>
					</div>
			</form:form>
				</div>
		</div>
	</div>
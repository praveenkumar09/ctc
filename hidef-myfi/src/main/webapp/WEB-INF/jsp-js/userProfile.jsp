<link type="text/css" href="${pageContext.request.contextPath}/css/panel-border.css" rel="stylesheet">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

$(document).ready(function() {
	 $("#summary").hide();
});
</script>
<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
			<form:form modelAttribute="hidef" method="POST" name="userProfile"
				enctype="multipart/form-data" action="profile"
				id="userProfile">
				<div class="panel-heading">
					<strong>PROFILE</strong>
				</div>
				<div class="panel-body">
					<br /> <br />
					<div class="form-group col-md-6">
						<form:label for="" path="userprofile.sendingcountry">Sending Country:</form:label>
						<form:select class="form-control" id="sendingCountry"
							path="userprofile.sendingcountry">
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
					</div>
					<%-- <div class="form-group col-md-6">
						<form:label for="" path="userprofile.receivingcountry">Receiving Country:</form:label>
						<form:select class="form-control" id="receivingCountry"
							path="userprofile.receivingcountry">
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
					<div class="form-group col-md-6">
						<form:label path="userprofile.msgType">Message Type:</form:label>
						<form:input type="text" class="form-control"
							id="sendContactEmailAddress" value="CBC"
							placeholder="Message Type" path="userprofile.msgType"
							readonly="true"></form:input>
					</div>
					<div class="form-group col-md-6">
						<form:label class="" path="userprofile.sendContactEmailAddress">Sender Contact Email Address:</form:label>
						<form:input type="text" class="form-control"
							id="senderContactEmailAddress"
							placeholder="Sender Contact Email Address"
							path="userprofile.sendContactEmailAddress"></form:input>
					</div>
					<div class="form-group col-md-6">
						<form:label class="" path="userprofile.communicationType">Communication Type:</form:label>
						<form:input type="text" class="form-control"
							name="communicationType" id="communicationType" value="CBC"
							path="userprofile.communicationType"
							placeholder="Communication Type" readonly="true"></form:input>
					</div>
					<div class="form-group col-md-6">
						<form:label for="" path="userprofile.fileformatCode">File Format Code:</form:label>
						<form:select class="form-control" id="fileFormatCode"
							path="userprofile.fileformatCode">
							<form:option value="0">Please choose</form:option>
							<%-- 	<form:option value="1">MY</form:option>
							<form:option value="2">SG</form:option>
							<form:option value="3">CN</form:option>
							<form:option value="4">AU</form:option>
							<form:option value="5">US</form:option> --%>
							<c:forEach items="${fileformatcodeList}" var="fileformatcodeList">
								<form:option value="${fileformatcodeList.CBCFileFormatType}">
								${fileformatcodeList.CBCFileFormatType}
							</form:option>
							</c:forEach>
						</form:select>
					</div>
					<div class="form-group col-md-6">
						<form:label for="" path="userprofile.binaryEncoding">Binary Encoding:</form:label>
						<form:select class="form-control" id="binaryEncoding"
							path="userprofile.binaryEncoding">
							<form:option value="0">Please choose</form:option>
							<%-- 	<form:option value="1">MY</form:option>
							<form:option value="2">SG</form:option>
							<form:option value="3">CN</form:option>
							<form:option value="4">AU</form:option>
							<form:option value="5">US</form:option> --%>
							<c:forEach items="${binaryencodingList}" var="binaryencodingList">
								<form:option value="${binaryencodingList.type}">
								${binaryencodingList.type}
							</form:option>
							</c:forEach>
						</form:select>
					</div>
					<%-- <div class="form-group col-md-6">
						<form:label class="" path="userprofile.messageRefID">Message Reference Id: </form:label>
						<form:input type="text" path="userprofile.messageRefID"
							class="form-control" id="messageRefId"
							placeholder="Message Reference Id" readonly="true"></form:input>
					</div> --%>
					<!-- <div class="form-group col-md-6">
						<label class="">Identification Number:</label> <input type="text"
							class="form-control" id="sendContactEmailAddress"
							placeholder="Identification Number">
					</div> -->
					<!-- <div class="form-group col-md-6">
						<label class="">Language:</label> <input type="text"
							class="form-control" id="sendContactEmailAddress"
							placeholder="Language">
					</div> -->
					<div class="clearfix"></div>
					<%-- <div class="form-group col-md-6">
						<form:label path="userprofile.docRefID">Document Reference Id:</form:label>
						<form:input type="text" class="form-control"
							id="documentReferenceId" placeholder="Document Reference Id"
							path="userprofile.docRefID"></form:input>
					</div> --%>
					<%-- <div class="form-group col-md-6">
						<form:label path="userprofile.ctsTransId">Transmission Id:</form:label>
						<form:input type="text" class="form-control" id="ctsTransId"
							placeholder="CTS Transmission Id" path="userprofile.ctsTransId"></form:input>
					</div> --%>
					<%-- <div class="form-group col-md-6">
						<form:label for="" path="userprofile.fileTypeIndic">File Type Indicator:</form:label>
						<form:select class="form-control" id="binaryEncoding"
							path="userprofile.fileTypeIndic">
							<form:option value="0">Please choose</form:option>
								<form:option value="1">MY</form:option>
							<form:option value="2">SG</form:option>
							<form:option value="3">CN</form:option>
							<form:option value="4">AU</form:option>
							<form:option value="5">US</form:option>
							<c:forEach items="${fileTypeIndicList}" var="fileTypeIndicList">
								<form:option value="${fileTypeIndicList.indic}">
								${fileTypeIndicList.indic}
							</form:option>
							</c:forEach>
						</form:select>
					</div> --%>
					<div class="clearfix"></div>
					<div class="form-group col-md-6">
						<div class="form-group">
							<%-- <div class="input-group input-file">
								<form:input path="userprofile.configurationFile" type="test" class="form-control"
									placeholder='Choose a P12 file...' /> <span
									class="input-group-btn">
									<form:button class="btn btn-default btn-choose"
										onclick="bs_input_file();" type="button">Choose</form:button>
								</span>
							</div> --%>
							<%-- <div class="btn btn-primary float-left" style="width:100%;">
								<form:label path="userprofile.configurationFile" for="sel1">Configuration Certificate (P12 File)</form:label>
								<form:input name="configurationFile" path="userprofile.configurationFile" type="file" />
							</div> --%>
								<div class="input-group input-file" name="Fichier1">
									<form:input path="userprofile.configurationFileText" type="text" class="form-control"
										placeholder='Choose a file...' /> 
<%-- 										<form:input name="configurationFile" path="userprofile.configurationFile" type="file"  style="display: none;"/> --%>
										<span
										class="input-group-btn">
										<button class="btn btn-default btn-choose" onclick="bs_input_file();" type="button">Choose Configuration Cert</button>
									</span>
								</div>
							<%-- <div class="file-path-wrapper">
								<form:input path="userprofile.configurationFileText" class="file-path validate" type="text"
									placeholder="Upload your file"/>
							</div>  --%>
						</div>
					</div>
					<div class="form-group col-md-6">
					<div class="input-group input-file" name="Fichier2">
									<form:input path="userprofile.publicCertFileName" type="text" class="form-control"
										placeholder='Choose a file...' /> 
<%-- 										<form:input name="publicCertPath" path="userprofile.publicCertPath" type="file"  style="display: none;"/> --%>
										<span
										class="input-group-btn">
										<button class="btn btn-default btn-choose" onclick="bs_input_file();" type="button">Choose Public Cert</button>
									</span>
								</div>
						<%-- <div class="btn btn-primary float-left" style="width: 100%;">
							<form:label path="userprofile.publicCertPath" for="sel1">Public Certificate (.crt or .cer File)</form:label>
							<form:input path="userprofile.publicCertPath"
								name="publicCertPath" type="file" />
						</div> --%>
						<%-- <form:label path="userprofile.publicCertPath" for="sel1">Public Cert</form:label>
						<div class="form-group">
							<div class="input-group input-file">
								<form:input path="userprofile.publicCertPath" type="text"
									class="form-control"
									placeholder='Choose a .crt or .cer file...' />
								<span class="input-group-btn"> <form:button
										class="btn btn-default btn-choose" onclick="bs_input_file();"
										type="button">Choose</form:button>
								</span>


							</div>
						</div> --%>
					</div>
					<div class="clearfix"></div>
					<div class="form-group col-md-6">
					<div id="userProfileReceivingCountryList"></div>
					<div id="pager"></div>
				    </div>
				    <input type="hidden" id="recievingCountry" value='${recievingCountry}'/>
					<div class="clearfix"></div>
					<!-- 	<div class="form-group col-md-6">
						<div id="jsGrid"></div>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group col-md-6">
						<div id="idGrid"></div>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div> -->
					<div class="text-center">
						<button id="singlebutton" name="singlebutton" onClick=""
							class="btn btn-success">Save</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>

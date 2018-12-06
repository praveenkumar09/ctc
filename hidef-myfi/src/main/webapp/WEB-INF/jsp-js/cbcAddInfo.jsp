<link type="text/css" href="${pageContext.request.contextPath}/css/panel-border.css" rel="stylesheet">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cbcaddInfo.js"></script>
<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
			<div class="panel-heading">
				<strong>ADDITIONAL INFORMATION</strong>
			</div>
			<div class="panel-body">
				<br />
				 <form:form  modelAttribute="hidef" method="POST" name="addtionalinfo" id="addtionalinfo">
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path="cbcAddionalInfo.documentTypeIndic">Document Type Indicator<font color='red'>*</font>:</form:label> <form:select
							class="form-control" id="docTypeIndic22" path="cbcAddionalInfo.documentTypeIndic">
							<form:option value="0">Please choose</form:option>
							<c:forEach items="${documentTypeIndicator}" var="documenttypeindic">
							<form:option value="${documenttypeindic.CBCDocumentType}">
								${documenttypeindic.CBCDocumentType}
							</form:option>
						</c:forEach>
						</form:select>
						<font color='red'><span id="documenttypeindicError22" class="mandatory-flag"></span></font>
					</div>
					<div class="form-group col-md-6">
						<form:label path="cbcAddionalInfo.documentReferenceId">Document Reference Id<font color='red'>*</font>:</form:label> <form:input type="text"
							class="form-control" id="documentReferenceId22"
							placeholder="Document Reference Id" path="cbcAddionalInfo.documentReferenceId" disabled="true"></form:input>
							<font color='red'><span id="documentReferenceIdError22" class="mandatory-flag"></span></font>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path="cbcAddionalInfo.corrMessageRefId">Corr Message Reference Id:</form:label> <form:input type="text"
							class="form-control" id="corrMessageReferenceId"
							placeholder="Corr Message Reference Id" path="cbcAddionalInfo.corrMessageRefId"></form:input>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path="cbcAddionalInfo.corDocumentRefId">Corr Document Reference Id:</form:label> <form:input type="text"
							class="form-control" id="corrDocumentReferenceId"
							placeholder="Corr Document Reference Id" path="cbcAddionalInfo.corDocumentRefId"></form:input>
					</div>
				</div>
				<div class="form-group col-md-12">
					<form:label path="cbcAddionalInfo.otherInfo">Other Info<font color='red'>*</font>:</form:label>
					<form:textarea class="form-control" rows="5" id="otherInfo22"
						placeholder="Other Info" path="cbcAddionalInfo.otherInfo"></form:textarea>
						<font color='red'><span id="otherInfoError22" class="mandatory-flag"></span></font>
				</div>
				<div class="clearfix"></div>
				<br />
				<br />
				<input type="hidden" id="residentCountry" value='${residentCountry}'/>
				<input type="hidden" id="summaryTypedropdown" value='${summeryTypeList}'/>
				<input type="hidden" id="userPropCountry" value='${userPropCountry}'/>
				<div class="form-group col-md-6" id="gridAddInfoResidentCountry">
					<div id="addInfoResidentCountryGrid"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>
				<br />
				<br />
				<div class="form-group col-md-6">
					<div id="addInfoSummaryReferenceGrid"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>
				<br />
				<div class="form-group">
				<c:choose>
				<c:when test="${hidef.isSummaryView=='true'}">
					
					</c:when>
					<c:otherwise>
					<div class="text-center">
						<br />
							<button id="saveCBCAddInfoButton" name="singlebutton"
							onClick="saveAddInfo();return false;" class="btn btn-success">Save Additional Information</button>
							<button id="viewAddInfoDone" name="singlebutton"
							onClick="doneViewAddInfo(1,0,0);return false;" class="btn btn-success">Cancel</button>
							<button id="editAddInfoDone" name="singlebutton"
							onClick="doneEditAddInfo(1,0,0);return false;" class="btn btn-success">Save Edited Changes</button>
							<button id="editCancelInfoDone" name="singlebutton"
							onClick="doneViewAddInfo(1,0,0);return false;" class="btn btn-danger">Cancel Edited Changes</button>
					</div>
					</c:otherwise>
					</c:choose>
				</div>
				<div class="clearfix"></div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<label for="language">Saved Additional Information: </label><br />
						<br />
						<div id="cbcAddInfoGrid"></div>
						<font color='red'><span id="cbcaddinfoError" class="mandatory-flag"></span></font>
						<div id="pager"></div>
					</div>
				</div>
				<div class="clearfix"></div>
				<br /> <br />
				</form:form>
				<div class="form-group">
				<c:choose>
				<c:when test="${hidef.isSummaryView=='true'}">
				<!-- <button id="addInfoViewButton" name="singlebutton"
							onClick="additionalInfoPrevious();return false;" class="btn btn-primary">Back To Summary</button> -->
							<div class="text-center">
							<a id='addInfoViewButton' href="home">Back To Summary</a>
							</div>
				</c:when>
				<c:otherwise>
					<div class="text-center">
						<br />
						<button id="singlebutton" name="singlebutton"
							onClick="additionalInfoPrevious();return false;" class="btn btn-warning">Previous</button>
					 <button id="saveCBCDataButton" name="singlebutton" onClick="saveAllCBCData();"
							class="btn btn-success">Save</button> <br/><br/>
						<!-- <button id="singlebutton" name="singlebutton" onClick="#"
							class="btn btn-danger">Reset</button> -->
							<br/><br/>
						<button id="generateCBCMetadata" name="singlebutton"
							onClick="generateMetaData();" class="btn btn-primary">Generate
							MetaData</button>
						<button id="generateCBCPayload" name="singlebutton"
							onClick="generatePayload();" class="btn btn-primary">Generate
							Payload</button>
							<br/>
							<br/>
							<button id="generateCBCPackage" name="singlebutton"
							onClick="generatePackage();" class="btn btn-primary">Generate
							Package</button>
					</div>
					</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>

<link type="text/css" href="${pageContext.request.contextPath}/css/panel-border.css" rel="stylesheet">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cbcreports.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.price_format.js"></script>
<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
		 <form:form  modelAttribute="hidef" method="POST" name="cbcreports" id="cbcreports">
			<div class="panel-heading">
				<strong>CBC REPORTS</strong>
			</div>
			<div class="panel-body">
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path="cbcReports.documentTypeIndicator">Document Type Indicator<font color='red'>*</font>:</form:label> <form:select
							class="form-control" id="docTypeIndic" path="cbcReports.documentTypeIndicator">
							<form:option value="0">Please choose</form:option>
							<c:forEach items="${documentTypeIndicator}" var="documenttypeindic">
							<form:option value="${documenttypeindic.CBCDocumentType}">
								${documenttypeindic.CBCDocumentType}
							</form:option>
						</c:forEach>
						</form:select>
						<font color='red'><span id="documentTypeIndicatorError" class="mandatory-flag"></span></font>
					</div>
					<div class="form-group col-md-6">
						<form:label path="cbcReports.documentRefId">Document Reference Id<font color='red'>*</font>:</form:label> <form:input type="text"
							class="form-control" id="documentReferenceId11"
							placeholder="Document Reference Id" path="cbcReports.documentRefId" disabled="true"></form:input>
							<font color='red'><span id="documentRefIdError11" class="mandatory-flag"></span></font>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path="cbcReports.corrMessageRefId">Corr Message Reference Id:</form:label> <form:input type="text"
							class="form-control" id="corrMessageReferenceId"
							placeholder="Corr Message Reference Id" path="cbcReports.corrMessageRefId"></form:input>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path="cbcReports.corrMessageRefId">Corr Document Reference Id:</form:label> 
						<form:input type="text"
							class="form-control" id="corrDocumentReferenceId"
							placeholder="Corr Document Reference Id" path="cbcReports.corrDocRefId"></form:input>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label for="" path="cbcReports.residentCountryCode">Resident Country Code<font color='red'>*</font>:</form:label> 
						<form:select
							class="form-control" id="residentCountryCode" path="cbcReports.residentCountryCode">
							<form:option value="0">Please choose</form:option>
							<c:forEach items="${countryList}" var="country">
							<form:option value="${country.countryCode}">
								${country.countryCode}
							</form:option>
						</c:forEach>
						</form:select>
						<font color='red'><span id="residentCountryCodeError" class="mandatory-flag"></span></font>
					</div>
				</div>
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path="cbcReports.nbEmployees">Nb Employees<font color='red'>*</font>:</form:label> 
						<form:input type="text"
							class="form-control" id="nbEmployees" placeholder="Nb Employees" path="cbcReports.nbEmployees" onkeypress='validate(event)'></form:input>
							<font color='red'><span id="nbEmployeesError" class="mandatory-flag"></span></font>
					</div>
				</div>
				<div class="clearfix"></div>
				<br />
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Unrelated Revenue:</legend>
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label for="" path="cbcReports.unrelateCurrCode">Currency Code<font color='red'>*</font>:</form:label>
							 <form:select class="form-control"
								id="unrelatedCountryCode" path="cbcReports.unrelateCurrCode">
								<form:option value="0">Please choose</form:option>
							<c:forEach items="${currencyList}" var="currency">
							<form:option value="${currency.code}">
								${currency.code}
							</form:option>
						</c:forEach>
							</form:select>
							<font color='red'><span id="unrelateCurrCodeError" class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label path="cbcReports.unrelatedAmount">Amount<font color='red'>*</font>:</form:label> <form:input type="text" class="form-control"
								id="unrelatedAmount" placeholder="Amount" path="cbcReports.unrelatedAmount" onkeypress='validate1(event)' maxlength="18"></form:input>
								<font color='red'><span id="unrelatedAmountError" class="mandatory-flag"></span></font>
						</div>
					</div>
				</fieldset>
				<div class="clearfix"></div>
				<br />
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Related Revenue :</legend>
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label  for="" path="cbcReports.relatedCurrCode">Currency Code<font color='red'>*</font>:</form:label> 
							<form:select class="form-control"
								id="relatedCurrencyCode" path="cbcReports.relatedCurrCode">
								<form:option value="0">Please choose</form:option>
							<c:forEach items="${currencyList}" var="currency">
							<form:option value="${currency.code}">
								${currency.code}
							</form:option>
						</c:forEach>
							</form:select>
							<font color='red'><span id="relatedCurrencyCodeError" class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label path="cbcReports.relatedAmount">Amount<font color='red'>*</font>:</form:label> <form:input type="text" class="form-control"
								id="relatedAmount" placeholder="Amount" path="cbcReports.relatedAmount" onkeypress='validate1(event)' maxlength="18"></form:input>
								<font color='red'><span id="relatedAmountError" class="mandatory-flag"></span></font>
						</div>
					</div>
				</fieldset>
				<div class="clearfix"></div>
				<br />
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Total Revenue :</legend>
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label for="" path="cbcReports.totalRevenueCurrCode">Currency Code<font color='red'>*</font>:</form:label> 
							<form:select class="form-control"
								id="totalCurrencyCode" path="cbcReports.totalRevenueCurrCode">
								<form:option value="0">Please choose</form:option>
							<c:forEach items="${currencyList}" var="currency">
							<form:option value="${currency.code}">
								${currency.code}
							</form:option>
						</c:forEach>
							</form:select>
							<font color='red'><span id="totalCurrencyCodeError" class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label path="cbcReports.totalRevenueAmount">Amount<font color='red'>*</font>:</form:label> <form:input type="text" class="form-control"
								id="totalAmount" placeholder="Amount" path="cbcReports.totalRevenueAmount" onkeypress='validate1(event)' maxlength="18"></form:input>
								<font color='red'><span id="totalAmountError" class="mandatory-flag"></span></font>
						</div>
					</div>
				</fieldset>
				<div class="clearfix"></div>
				<br />
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Profit Or Loss :</legend>
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label for="" path="cbcReports.profitorlossCurrCode">Currency Code<font color='red'>*</font>:</form:label> 
							<form:select class="form-control"
								id="profitOrLossCurrencyCode" path="cbcReports.profitorlossCurrCode">
								<form:option value="0">Please choose</form:option>
							<c:forEach items="${currencyList}" var="currency">
							<form:option value="${currency.code}">
								${currency.code}
							</form:option>
						</c:forEach>
							</form:select>
							<font color='red'><span id="profitOrLossCurrencyCodeError" class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label path="cbcReports.prfitotloassAmount">Amount<font color='red'>*</font>:</form:label> <form:input type="text" class="form-control"
								id="profitOrLossAmount" placeholder="Amount" path="cbcReports.prfitotloassAmount" onkeypress='validate1(event)' maxlength="18"></form:input>
								<font color='red'><span id="profitOrLossAmountError" class="mandatory-flag"></span></font>
						</div>
					</div>
				</fieldset>
				<div class="clearfix"></div>
				<br />
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Tax Paid :</legend>
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label for="" path="cbcReports.taxpiadCurrCode">Currency Code<font color='red'>*</font>:</form:label> <form:select class="form-control"
								id="taxPaidCurrencyCode" path="cbcReports.taxpiadCurrCode">
								<form:option value="0">Please choose</form:option>
							<c:forEach items="${currencyList}" var="currency">
							<form:option value="${currency.code}">
								${currency.code}
							</form:option>
						</c:forEach>
							</form:select>
							<font color='red'><span id="taxPaidCurrencyCodeError" class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label path="cbcReports.taxpaidAmount">Amount<font color='red'>*</font>:</form:label> <form:input type="text" class="form-control"
								id="taxPaidAmount" placeholder="Amount" path="cbcReports.taxpaidAmount" onkeypress='validate1(event)' maxlength="18"></form:input>
								<font color='red'><span id="taxPaidAmountError" class="mandatory-flag"></span></font>
						</div>
					</div>
				</fieldset>
				<div class="clearfix"></div>
				<br />
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Tax Accrued :</legend>
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label for="" path="cbcReports.taxaccruedCurrCode">Currency Code<font color='red'>*</font>:</form:label> 
							<form:select class="form-control"
								id="taxAccruedCurrencyCode" path="cbcReports.taxaccruedCurrCode">
								<form:option value="0">Please choose</form:option>
							<c:forEach items="${currencyList}" var="currency">
							<form:option value="${currency.code}">
								${currency.code}
							</form:option>
						</c:forEach>
							</form:select>
							<font color='red'><span id="taxAccruedCurrencyCodeError" class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label path="cbcReports.taxaccruedAmount">Amount<font color='red'>*</font>:</form:label> <form:input type="text" class="form-control"
								id="taxAccruedAmount" placeholder="Amount" path="cbcReports.taxaccruedAmount" onkeypress='validate1(event)' maxlength="18"></form:input>
								<font color='red'><span id="taxAccruedAmountError" class="mandatory-flag"></span></font>
						</div>
					</div>
				</fieldset>
				<div class="clearfix"></div>
				<br />
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Capital :</legend>
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label for="" path="cbcReports.capitalCurrCode">Currency Code<font color='red'>*</font>:</form:label> <form:select class="form-control"
								id="capitalCurrencyCode" path="cbcReports.capitalCurrCode">
								<form:option value="0">Please choose</form:option>
							<c:forEach items="${currencyList}" var="currency">
							<form:option value="${currency.code}">
								${currency.code}
							</form:option>
						</c:forEach>
							</form:select>
							<font color='red'><span id="capitalCurrencyCodeError" class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label path="cbcReports.capitalAmount">Amount<font color='red'>*</font>:</form:label> <form:input type="text" class="form-control"
								id="capitalAmount" placeholder="Amount" path="cbcReports.capitalAmount" onkeypress='validate1(event)' maxlength="18"></form:input>
								<font color='red'><span id="capitalAmountError" class="mandatory-flag"></span></font>
						</div>
					</div>
				</fieldset>
				<div class="clearfix"></div>
				<br />
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Earnings :</legend>
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label for="" path="cbcReports.earningCurrCode">Currency Code<font color='red'>*</font>:</form:label> <form:select class="form-control"
								id="earningsCurrencyCode" path="cbcReports.earningCurrCode">
								<form:option value="0">Please choose</form:option>
							<c:forEach items="${currencyList}" var="currency">
							<form:option value="${currency.code}">
								${currency.code}
							</form:option>
						</c:forEach>
							</form:select>
							<font color='red'><span id="earningsCurrencyCodeError" class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label path="cbcReports.earningAmount">Amount<font color='red'>*</font>:</form:label> <form:input type="text" class="form-control"
								id="earningsAmount" placeholder="Amount" path="cbcReports.earningAmount" onkeypress='validate1(event)' maxlength="18"></form:input>
								<font color='red'><span id="earningsAmountError" class="mandatory-flag"></span></font>
						</div>
					</div>
				</fieldset>
				<div class="clearfix"></div>
				<br />
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Assets :</legend>
					<div class="form-group">
						<div class="form-group col-md-6">
							<form:label for="" path="cbcReports.assertCurrCode">Currency Code<font color='red'>*</font>:</form:label> <form:select class="form-control"
								id="assetsCurrencyCode" path="cbcReports.assertCurrCode">
								<form:option value="0">Please choose</form:option>
							<c:forEach items="${currencyList}" var="currency">
							<form:option value="${currency.code}">
								${currency.code}
							</form:option>
						</c:forEach>
							</form:select>
							<font color='red'><span id="assetsCurrencyCodeError" class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label path="cbcReports.assertAmount">Amount<font color='red'>*</font>:</form:label> <form:input type="text" class="form-control"
								id="assetsAmount" placeholder="Amount" path="cbcReports.assertAmount" onkeypress='validate1(event)' maxlength="18"></form:input>
								<font color='red'><span id="assetsAmountError" class="mandatory-flag"></span></font>
						</div>
					</div>
				</fieldset>
				<div class="clearfix"></div>
				<div id="constituentEntities">
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Constituent Entities :</legend>
						<div class="form-group">
							<div class="form-group col-md-6">
							<form:label path="reportingEntity.tin">TIN<font color='red'>*</font></form:label> <form:input type="text" path="cbcReports.constituentEntity.tin"
								class="form-control" id="tin11"
								placeholder="TIN"></form:input>
 								<font color='red'><span id="tinError11" class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label path="reportingEntity.tinIssuedBy">TIN Type<font color='red'>*</font>:</form:label> <form:input type="text" path="cbcReports.constituentEntity.tinType"
								class="form-control" id="tintype11"
								placeholder="TIN Type"></form:input>
 								<font color='red'><span id="tintypeError11" class="mandatory-flag"></span></font>
						</div>
						
						
						<div class="form-group col-md-6">
							<form:label for="" path="cbcReports.tin">TIN Issued By<font color='red'>*</font>:</form:label> <form:select class="form-control" id="issuedBy11" path="cbcReports.constituentEntity.issuedBy">
								<form:option value="0">Please choose</form:option>
							<c:forEach items="${countryList}" var="country">
							<form:option value="${country.countryCode}">
								${country.countryCode}
							</form:option>
						</c:forEach>
							</form:select>
 							<font color='red'><span id="issuedByError11" class="mandatory-flag"></span></font>
						</div>
						<div class="form-group col-md-6">
							<form:label for="" path="cbcReports.incorpCountryCode">Incorp Country Code:</form:label> <form:select
								class="form-control" id="incorpCountryCode" path="cbcReports.constituentEntity.incorpCountryCode">
								<form:option value="0">Please choose</form:option>
							<c:forEach items="${countryList}" var="country">
							<form:option value="${country.countryCode}">
								${country.countryCode}
							</form:option>
						</c:forEach>
							</form:select>
						</div>
						<div class="form-group col-md-12">
							<form:label path="cbcReports.otherEntityInfo">Other Entity Info:</form:label>
							<form:textarea class="form-control" rows="5" id="otherEntityInfo"
								placeholder="Other Entity Info" path="cbcReports.constituentEntity.otherEntityInfo"></form:textarea>
						</div>
						<input type="hidden" id="residentCountry" value='${residentCountry}'/>
						<input type="hidden" id="nameTypedropdown" value='${nameTypeList}'/>
						<input type="hidden" id="bizTypedropdown" value='${bizTypeList}'/>
						
						<div class="form-group col-md-6">
							<div id="bizActivitiesGrid"></div>
							<div id="pager"></div>
						</div>
						<div class="clearfix"></div>
						<div class="form-group col-md-6">
							<div id="cbcReportsResidentCountryGrid"></div>
							<div id="pager"></div>
						</div>
						<div class="clearfix"></div>
						<div class="form-group col-md-6">
							<div id="cbcReportsNameGrid"></div>
							<div id="pager"></div>
						</div>
						<div class="clearfix"></div>
						<div class="form-group col-md-6">
							<div id="cbcReportsINGrid"></div>
							<div id="pager"></div>
						</div>
						<div class="clearfix"></div>
						<div class="form-group col-md-6">
							<div id="cbcReportsAddressGrid"></div>
							<div id="pager"></div>
							<div class="clearfix"></div>
						</div>
					</div>
					<div class="clearfix"></div>
					<br/><br/>
					<div class="text-center">
						<button id="saveConstEntityButton" name="saveConstEntityButton"
							onClick="saveConstituentEntity();return false;" class="btn btn-primary">Save Constituent Entity</button><br/>
							<button id="editConstEntityButton" name="saveConstEntityButton"
							onClick="editConstituentEntity();return false;" class="btn btn-primary">Edit Constituent Entity</button><br/><br/>
							<button id="viewConstEntityButton" name="viewConstEntityButton"
							onClick="returnNormalConstEntity();return false;" class="btn btn-primary">Cancel</button> <br/>
					</div>
					<br/><br/>
					<div class="clearfix"></div>
						<div class="form-group col-md-6">
							<div id="cbcReportsConstituentEntityGrid"></div>
							<div id="pager"></div>
							<div class="clearfix"></div>
						</div>
				</fieldset>
				</div>
				<br />
				<div class="clearfix"></div>
				<br />
				<div class="form-group">
				<c:choose>
						<c:when test="${hidef.isSummaryView=='true'}">
					<div class="text-center">
						
					</div>
					</c:when>
					<c:otherwise>
					<div class="text-center">
						<br />
						<button id="saveCBCReportButton" name="singlebutton"
							onClick="saveCBCReports();return false;" class="btn btn-success">Save CBC Report</button>
							<button id="viewReportsDone" name="singlebutton"
							onClick="doneViewreports(1,0,0);return false;" class="btn btn-success">Cancel</button>
							<button id="editReportsDone" name="singlebutton"
							onClick="doneEditReports(1,0,0);return false;" class="btn btn-success">Save Edited Changes</button>
							<button id="editCancelReportsDone" name="singlebutton"
							onClick="doneViewreports(1,0,0);return false;" class="btn btn-danger">Cancel</button>
					</div>
					</c:otherwise>
					</c:choose>
				</div>
				<div class="clearfix"></div>
				<br />
				<div class="form-group col-md-6">
				<label for="language">Saved CBC Reports: </label><br/><br/>
						<div id="cbcReportsGrid"></div>
						<div id="pager"></div>
						<font color='red'><span id="cbcReportsError" class="mandatory-flag"></span></font>
				</div>
				<div class="clearfix"></div>
				<br /> <br />
				<div class="form-group">
				<c:choose>
				<c:when test="${hidef.isSummaryView=='true'}">
					<div class="text-center">						
						<button id="viewNextTab" name="singlebutton"
							onClick="showCbcAddInfo(0,0,1);return false;" class="btn btn-primary">View Next Tab</button>
					</div>
					</c:when>
					<c:otherwise>
					<div class="text-center">
						<button id="singlebutton" name="singlebutton"
							onClick="showCbcReportsPrevious(1,0,0);return false;" class="btn btn-warning">Previous</button>
						&nbsp;&nbsp;
						<button id="singlebutton" name="singlebutton"
							onClick="showCbcAddInfo(1,0,0);return false;" class="btn btn-primary">Next</button>
					</div>
					</c:otherwise>
					</c:choose>
				</div>
			</div>
			</form:form>
		</div>
	</div>
</div>



<div class="modal fade" id="addNewCbcReportsAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="cbcReportsaddress" id="cbcReportsaddress">
		<div class="modal-content">
			<div class="modal-header" id="popUpModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">ADD NEW ADDRESS</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				<div class="form-group col-md-6">
					<label for="">Country Code<font color='red'>*</font>:</label> <form:select class="form-control"
						id="countryCodeReportingFI" path="cbcReports.constituentEntity.addressVo.countryCode">
						<option value="0">--Choose Country code--</option>
						<c:forEach items="${tinlist}" var="tin">
							<form:option value="${tin.countryCode}">
								${tin.countryCode}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>
				<div class="form-group col-md-6">
					<label for="">Address Type<font color='red'>*</font>:</label> <form:select class="form-control"
						id="addressTypeReportingFI" path="cbcReports.constituentEntity.addressVo.addressType">
						<option value="0">--Choose Address Type--</option>
						<c:forEach items="${addressType}" var="addressType">
							<form:option value="${addressType.id}">
								${addressType.addressType}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>
				<div class="form-group col-md-11" id="addressFreeTextField">
					<label>Address Free:</label>
					<form:textarea class="form-control" rows="5" id="addressFree"
						placeholder="Add Address Free" path="cbcReports.constituentEntity.addressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="cbcReports.constituentEntity.addressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="cbcReports.constituentEntity.addressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="cbcReports.constituentEntity.addressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="cbcReports.constituentEntity.addressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="cbcReports.constituentEntity.addressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="cbcReports.constituentEntity.addressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="cbcReports.constituentEntity.addressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="cbcReports.constituentEntity.addressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="cbcReports.constituentEntity.addressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="saveNewCbcReportsAddressClicked(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>

<div class="modal fade" id="editCbcReportsAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="editcbcreports" id="editcbcreports">
		<div class="modal-content">
			<div class="modal-header" id="popUpModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">EDIT ADDRESS</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				<div class="form-group col-md-6">
					<label for="">Country Code<font color='red'>*</font>:</label> <form:select class="form-control"
						id="countryCodeReportingFI" path="cbcReports.constituentEntity.editAddressVo.countryCode" >
						<option value="0">--Choose Country code--</option>
						<c:forEach items="${tinlist}" var="tin">
							<form:option value="${tin.countryCode}">
								${tin.countryCode}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>
				<div class="form-group col-md-6">
					<label for="">Address Type<font color='red'>*</font>:</label> <form:select class="form-control"
						id="addressTypeReportingFI" path="cbcReports.constituentEntity.editAddressVo.addressType">
						<option value="0">--Choose Address Type--</option>
						<c:forEach items="${addressType}" var="addressType">
							<form:option value="${addressType.id}">
								${addressType.addressType}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>
				<div class="form-group col-md-11" id="addressFreeTextField">
					<label>Address Free:</label>
					<form:textarea class="form-control" rows="5" id="addressFree"
						placeholder="Add Address Free" path="cbcReports.constituentEntity.editAddressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="cbcReports.constituentEntity.editAddressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="cbcReports.constituentEntity.editAddressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="cbcReports.constituentEntity.editAddressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="cbcReports.constituentEntity.editAddressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="cbcReports.constituentEntity.editAddressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="cbcReports.constituentEntity.editAddressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="cbcReports.constituentEntity.editAddressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="cbcReports.constituentEntity.editAddressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="cbcReports.constituentEntity.editAddressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="cbcReportsEditSaveAddress(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>



<div class="modal fade" id="viewCbcReportsAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="viewcbcReportsAdd" id="viewcbcReportsAdd">
		<div class="modal-content">
			<div class="modal-header" id="popUpModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">VIEW ADDRESS</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				<div class="form-group col-md-6">
					<label for="">Country Code<font color='red'>*</font>:</label> 
					<form:select class="form-control"
						id="countryCodeReportingFI" path="cbcReports.constituentEntity.viewAddressVo.countryCode" disabled="true">
						<option value="0">--Choose Country code--</option>
						<c:forEach items="${tinlist}" var="tin">
							<form:option value="${tin.countryCode}">
								${tin.countryCode}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>
				<div class="form-group col-md-6">
					<label for="">Address Type<font color='red'>*</font>:</label> <form:select class="form-control"
						id="addressTypeReportingFI" path="cbcReports.constituentEntity.viewAddressVo.addressType" disabled="true">
						<option value="0">--Choose Address Type--</option>
						<c:forEach items="${addressType}" var="addressType">
							<form:option value="${addressType.id}">
								${addressType.addressType}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>
				<div class="form-group col-md-11" id="addressFreeTextField">
					<label>Address Free:</label>
					<form:textarea class="form-control" rows="5" id="addressFree"
						placeholder="Add Address Free" path="cbcReports.constituentEntity.viewAddressVo.addressFree" readonly="true"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="cbcReports.constituentEntity.viewAddressVo.street" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="cbcReports.constituentEntity.viewAddressVo.buildingIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="cbcReports.constituentEntity.viewAddressVo.suitIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="cbcReports.constituentEntity.viewAddressVo.floorIdentifier" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="cbcReports.constituentEntity.viewAddressVo.districtName" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="cbcReports.constituentEntity.viewAddressVo.pob" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="cbcReports.constituentEntity.viewAddressVo.postCode" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="cbcReports.constituentEntity.viewAddressVo.city" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="cbcReports.constituentEntity.viewAddressVo.countrySubentity" readonly="true"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<!-- <button type="button" onClick="saveNewReportingEntityAddressClicked(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button> -->
			</div>
		</div>
		</form:form>
	</div>
</div>

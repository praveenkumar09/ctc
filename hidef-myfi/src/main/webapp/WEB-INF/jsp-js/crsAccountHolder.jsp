<link type="text/css"
	href="${pageContext.request.contextPath}/css/panel-border.css"
	rel="stylesheet">
	<link type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap-multiselect.css"
	rel="stylesheet">
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap-multiselect.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/crsAccountHolder.js"></script>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
if('${hidef.accountholder.accountHolderType}' == 'individual,0' || '${hidef.accountholder.accountHolderType}' == 'individual'){
$("#defaultUnchecked1").prop("checked", true);
accountHolderType('individual');
}
if('${hidef.accountholder.accountHolderType}' == 'organization,0' || '${hidef.accountholder.accountHolderType}' == 'organization'){
	$("#defaultUnchecked2").prop("checked", true);
	accountHolderType('organization');
}
</script>
<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
		<form:form  modelAttribute="hidef" method="POST" name="crsaccountholder" id="crsaccountholder">
			<div class="panel-heading">
				<strong>ACCOUNT HOLDER</strong>
			</div>
			<div class="panel-body">
				<br />
				<div class="form-group col-md-6">
					<label for="language">Document Type Indicator<font color='red'>*</font>: </label><form:select
						class="form-control" id="documentTypeIndicator" path="accountholder.documentTypeIndic">
						<option value="0">--Choose Document Type Indicator--</option>
						<!-- <option value="1">OECD0</option>
						<option value="2">OECD1</option>
						<option value="3">OECD2</option>
						<option value="4">OECD3</option>
						<option value="5">OECD10</option>
						<option value="5">OECD11</option>
						<option value="5">OECD12</option>
						<option value="5">OECD13</option> -->
						<c:forEach items="${documentTypeIndicator}" var="documenttypeindic">
							<form:option value="${documenttypeindic.CBCDocumentType}">
								${documenttypeindic.CBCDocumentType}
							</form:option>
						</c:forEach>
						
					</form:select>
					<font color='red'><span id="documentTypeIndicatorError"
								class="mandatory-flag"></span></font>
				</div>
				<div class="form-group col-md-6">
					<label for="documentRefId">Document Reference Id<font color='red'>*</font>:</label> <form:input
						class="form-control" id="documentRefId"
						placeholder="Enter Account Number" name="documentRefId" path="accountholder.documentRefId"></form:input>
						<font color='red'><span id="documentReferenceIdError"
								class="mandatory-flag"></span></font>
				</div>
				<div class="form-group col-md-6">
					<label for="accountNumber">Cor Message Reference Id:</label> <form:input
						class="form-control" id="cormessRefId"
						placeholder="Enter Account Number" name="accountNumber" path="accountholder.corMessageRefId"></form:input>
				</div>
				<div class="form-group col-md-6">
					<label for="accountNumber">Cor Document Reference Id:</label> <form:input
						class="form-control" id="corMessDocRefId"
						placeholder="Enter Account Number" name="accountNumber" path="accountholder.corMessageDocRefId"></form:input>
				</div>
				<div class="form-group col-md-6">
					<label for="accountNumber">Account Number<font color='red'>*</font>:</label> <form:input
						class="form-control" id="accountNumber"
						placeholder="Enter Account Number" name="accountNumber" path="accountholder.accountNumber"></form:input>
						<font color='red'><span id="accountNumberError"
								class="mandatory-flag"></span></font>
				</div>
				<div class="form-group col-md-6">
					<label for="sel1">Account Number Type<font color='red'>*</font>:</label> <br />
				    <form:select id="multi-select-account-type" multiple="multiple" path="accountholder.accountNumberType">
						<option value="Undocumented">Undocumented Account</option>
						<option value="Closed">Closed Account</option>
						<option value="Dormant">Dormant Account</option>
					</form:select>
					<font color='red'><span id="multi-select-account-typeError"
								class="mandatory-flag"></span></font>

				</div>
				<div class="clearfix"></div>
				<div class="form-group col-md-6">
					<label for="language">Currency<font color='red'>*</font>: </label><form:select
						class="form-control" id="currencyV" path="accountholder.currency">
						<option value="0">--Choose Currency--</option>
						<!-- <option value="1">AED</option>
						<option value="2">AFN</option>
						<option value="3">ALL</option>
						<option value="4">MYR</option>
						<option value="5">INR</option> -->
						<c:forEach items="${currencyList}" var="currency">
							<form:option value="${currency.code}">
								${currency.code}
							</form:option>
						</c:forEach>
					</form:select>
					<font color='red'><span id="currencyError"
								class="mandatory-flag"></span></font>
				</div>
				<div class="form-group col-md-6">
					<label for="accoutBalance">Account Balance<font color='red'>*</font>:</label> <form:input
						class="form-control" id="accoutBalance"
						placeholder="Enter Account Balance" name="accoutBalance" path="accountholder.accountBalance"></form:input>
						<font color='red'><span id="accoutBalanceError"
								class="mandatory-flag"></span></font>
				</div>
				<div class="clearfix"></div>
				<input type="hidden" id="paymentType" value='${paymentType}'/>
				<input type="hidden" id="currency" value='${currency}'/>
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Payment Details:</legend>
					<div class="form-group">
						<div class="form-group col-md-6">
							<div id="accountHolderControlingPersonPaymentGrid"></div>
							<font color='red'><span id="paymentGridError"
							class="mandatory-flag"></span></font>
							<div id="pager"></div>
						</div>
						
					</div>
				
				</fieldset>
				<div class="clearfix"></div>
				<br />
				<!-- <div class="form-group col-md-6">
					<div id="accountHolderControllingPersonGrid"></div>
					<div id="pager"></div>
				</div> -->
				<fieldset class="scheduler-border">
				<div id="controllingPerson">
					<legend class="scheduler-border">Controlling Person:</legend>
					<div class="form-group">
						<div class="clearfix"></div>
						<br />
						<div class="form-group col-md-6">
							<label for="language">Controlling Person Type: </label><form:select
								class="form-control" id="controllingPersonType" path="accountholder.controllingPersonVo.controllingPersonType">
								<form:option value="0">Choose Controlling Person Type</form:option>
								<!-- <option value="1">CRS801</option>
								<option value="2">CRS802</option>
								<option value="3">CRS803</option>
								<option value="4">CRS804</option>
								<option value="5">CRS805</option>
								<option value="6">CRS806</option>
								<option value="7">CRS807</option>
								<option value="8">CRS808</option>
								<option value="9">CRS809</option>
								<option value="10">CRS810</option>
								<option value="11">CRS811</option>
								<option value="12">CRS812</option>
								<option value="13">CRS813</option> -->
								<c:forEach items="${controllingPersonType}" var="ctrlperson">
							<form:option value="${ctrlperson.ctrlpersontype}">
								${ctrlperson.ctrlpersontype}
							</form:option>
						</c:forEach>
							</form:select>
						</div>
						<div class="clearfix"></div>
						<br />
						<div class="form-group col-md-6">
							<div id="accountHolderControlingPersonResidentCountryGrid"></div>
							<font color='red'><span id="accountHolderControlingPersonResidentCountryGridError"
							class="mandatory-flag"></span></font>
							<div id="pager"></div>
						</div>
						<div class="clearfix"></div>
						<br />
						<div class="form-group col-md-6">
							<div id="accountHolderControllingPersonTNGrid"></div>
							<div id="pager"></div>
						</div>
						<div class="clearfix"></div>
						<br />
						<div class="form-group col-md-6">
							<div id="accountHolderControllingPersonNameGrid"></div>
								<font color='red'><span id="accountHolderControllingPersonNameGridError"
							class="mandatory-flag"></span></font>
							<div id="pager"></div>
						</div>
						<div class="clearfix"></div>
						<br />
						<div class="form-group col-md-6">
							<div id="accountHolderControllingPersonAddressGrid"></div>
							<font color='red'><span id="accountHolderControllingPersonAddressGridError"
							class="mandatory-flag"></span></font>
							<div id="pager"></div>
						</div>
						<div class="clearfix"></div>
						<br />
						<!-- <div class="form-group col-md-6">
							<div id="accountHolderControllingPersonNationalityGrid"></div>
							<div id="pager"></div>
						</div> -->
						<!-- <div class="form-group col-md-6">
							<label for="language">Nationality: </label><select
								class="form-control" id="cpNationality">
								<option value="0">--Choose Nationality--</option>
								<option value="1">AD</option>
								<option value="2">AF</option>
								<option value="3">US</option>
								<option value="4">MY</option>
								<option value="5">AU</option>
							</select>
						</div> -->
						<div class="clearfix"></div>
						<br />
						<div class="form-group col-md-6">
							<label for="language">Birth Date:</label> <form:input
								class="form-control" id="birthDate" placeholder="Birth Date"
								name="birthDate" path="accountholder.controllingPersonVo.birthDate"></form:input>
						</div>
						<div class="form-group col-md-6">
							<label for="language">City:</label> <form:input class="form-control"
								id="city" placeholder="City" name="city" path="accountholder.controllingPersonVo.city"></form:input>
						</div>
						<div class="form-group col-md-6">
							<label for="language">City Sub Entity:</label> <form:input
								class="form-control" id="citySubentity"
								placeholder="City Subentity" name="citySubentity" path="accountholder.controllingPersonVo.citySubEntity"></form:input>
						</div>
						<div class="form-group col-md-6">
							<label for="language">Country Code: </label><form:select
								class="form-control" id="birthCountryCode" path="accountholder.controllingPersonVo.countryCode">
								<option value="0">--Choose Country Code--</option>
								<!-- <option value="1">AD</option>
								<option value="2">AF</option>
								<option value="3">US</option>
								<option value="4">MY</option>
								<option value="5">AU</option> -->
								<c:forEach items="${countryList}" var="tin">
							<form:option value="${tin.countryCode}">
								${tin.countryCode}
							</form:option>
						</c:forEach>
							</form:select>
						</div>
						<div class="form-group col-md-6">
							<label for="language">Country Name:</label> <form:input
								class="form-control" id="countryName" placeholder="Country Name" path="accountholder.controllingPersonVo.countryName"
								name="countryName"></form:input>
						</div>
						<!-- <div class="form-group col-md-6">
							<div id="accountHolderControllingPersonBirthInfoGrid"></div>
							<div id="pager"></div>
						</div> -->
						<div class="clearfix"></div>
						<div class="text-center">
							<br />
							<button id="saveControllingPersonButton" name="singlebutton"
								onClick="saveCtrlPersonMain();return false;"
								class="btn btn-success">Save Controlling Person</button>
							<button id="viewControllingDone" name="singlebutton"
							onClick="doneViewControllingPerson();return false;" class="btn btn-success">Cancel</button>
							<button id="editviewControllingDoneDone" name="singlebutton"
							onClick="doneEditSaveControllingPerson();return false;" class="btn btn-success">Save Edited Changes</button>
							<button id="editCancelviewControllingDoneDone" name="singlebutton"
							onClick="doneViewControllingPerson();return false;" class="btn btn-danger">Cancel</button>
								
						</div>
						<div class="clearfix"></div>
						<br />
						<div class="form-group col-md-6">
							<label for="language">Controlling Person Details: </label><br />
							<br />
							<div id="accountHolderControllingPersonGrid"></div>
							<div id="pager"></div>
						</div>
						<br />
					</div>
					</div>
				</fieldset>
				<div class="clearfix"></div>
				<br />
				<div class="form-group col-md-6">
					<label for="sel1">Account Holder Type :</label> <br />
					<div id="accountHolderType" class="custom-control custom-radio">
						<form:radiobutton class="custom-control-input"
							id="defaultUnchecked1" name="accountHolderTypeRadio"
							value="individual" path="accountholder.accountHolderType" onclick="accountHolderType(this.value)"></form:radiobutton> 
							<label class="custom-control-label"
							for="defaultUnchecked">Individual</label> <form:radiobutton
							class="custom-control-input" id="defaultUnchecked2"
							name="accountHolderTypeRadio" value="organization" path="accountholder.accountHolderType" onclick="accountHolderType(this.value)"></form:radiobutton> <label
							class="custom-control-label" for="defaultUnchecked">Organization</label>
					</div>
				</div>
				<div class="clearfix"></div>
				<div id="Individual">
				<input type="hidden" id="residentcountry" value='${country}'/>
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderResidentCountryGrid"></div>
						<font color='red'><span id="indresidentGridError"
							class="mandatory-flag"></span></font>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderTNGrid"></div>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderNameGrid"></div>
						<font color='red'><span id="accountHolderNameGridError"
							class="mandatory-flag"></span></font>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderAddressGrid"></div>
						<font color='red'><span id="accountHolderaddressGridError"
							class="mandatory-flag"></span></font>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<!-- <div class="form-group col-md-6">
						<div id="accountHolderNationalityGrid"></div>
						<div id="pager"></div>
					</div> -->
					<div class="clearfix"></div>
					<br />
					<!-- <div class="form-group col-md-6">
						<div id="accountHolderBirthInfoGrid"></div>
						<div id="pager"></div>
					</div> -->
					<div class="form-group col-md-6">
						<label for="language">Birth Date:</label> <form:input
							class="form-control" id="birthDate" placeholder="Birth Date"
							name="birthDate" path="accountholder.birthDate"></form:input>
					</div>
					<div class="form-group col-md-6">
						<label for="language">City:</label> <form:input class="form-control"
							id="city" placeholder="City" name="city" path="accountholder.city"></form:input>
					</div>
					<div class="form-group col-md-6">
						<label for="language">City Sub Entity:</label> <form:input
							class="form-control" id="citySubentity"
							placeholder="City Subentity" name="citySubentity" path="accountholder.citySubEntity"></form:input>
					</div>
					<div class="form-group col-md-6">
						<label for="language">Country Code: </label><form:select
							class="form-control" id="birthCountryCode" path="accountholder.countryCode">
							<option value="0">--Choose Country Code--</option>
							<!-- <option value="1">AD</option>
							<option value="2">AF</option>
							<option value="3">US</option>
							<option value="4">MY</option>
							<option value="5">AU</option> -->
							<c:forEach items="${countryList}" var="tin">
							<form:option value="${tin.countryCode}">
								${tin.countryCode}
							</form:option>
						</c:forEach>
						</form:select>
					</div>
					<div class="form-group col-md-6">
						<label for="language">Country Name:</label> <form:input 
							class="form-control" id="countryName" placeholder="Country Name" path="accountholder.countryName"
							name="countryName"></form:input>
					</div>
					<div class="clearfix"></div>
					<br />
				</div>
				<div class="clearfix"></div>
				<div id="Organization">
					<br />
					<div class="form-group col-md-6">
						<label for="language">Account Holder Type<font color='red'>*</font>: </label><form:select path="accountholder.individualaccountHolderType"
							class="form-control" id="accountHoldertype1">
							<option value="0">--Choose Account Holder Type--</option>
							<!-- <option value="1">CRS101</option>
							<option value="2">CRS102</option>
							<option value="3">CRS103</option> -->
							<c:forEach items="${accountHolderType}" var="accountholder">
							<form:option value="${accountholder.accholdertype}">
								${accountholder.accholdertype}
							</form:option>
						</c:forEach>
						</form:select>
						<font color='red'><span id="accountHoldertypeError"
							class="mandatory-flag"></span></font>
					</div>
					<div class="clearfix"></div>
					<input type="hidden" id="nameTypedropdown" value='${nameTypeList}'/>
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderOrganisationResidentCountryGrid"></div>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderOrganisationInTypeGrid"></div>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderOrganisationNameTypeGrid"></div>
						<font color='red'><span id="accountHolderOrganisationNameTypeGridError"
							class="mandatory-flag"></span></font>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderOrganisationAddressGrid"></div>
						<font color='red'><span id="accountHolderOrganisationAddressGridError"
							class="mandatory-flag"></span></font>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
				</div>
				<div class="clearfix"></div>
				<br />

				<div class="form-group">
					<div class="text-center">
						<br />
						<button id="saveaccountholder" name="singlebutton"
							onClick="saveAccountHolderMain();return false;" class="btn btn-primary">Save</button>
							<button id="viewAccountDone" name="singlebutton"
							onClick="doneViewAccounts();return false;" class="btn btn-success">Cancel</button>
							<button id="editAccountDone" name="singlebutton"
							onClick="doneEditAccounts();return false;" class="btn btn-success">Save Edited Changes</button>
							<button id="editCancelAccountDone" name="singlebutton"
							onClick="doneViewAccounts(1,0,0);return false;" class="btn btn-danger">Cancel</button>
					</div>
				</div>
				<div class="clearfix"></div>
				<br />
				<div class="form-group col-md-6">
					<label for="language">Account Holder Details: </label><br /> <br />
					<div id="accountHolderGrid"></div>
					<font color='red'><span id="accountHolderGridError"
							class="mandatory-flag"></span></font>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>
				<br />
				<div class="form-group">
					<div class="text-center">
						<br />
						<button id="singlebutton" name="singlebutton"
							onClick="accountHolderPrevious();return false;" class="btn btn-warning">Previous</button>	
						 <button id="saveCRSDataButton" name="singlebutton" onClick="saveAllCRSData();return false;"
							class="btn btn-success">Save</button> <br/><br/>
						<!-- <button id="singlebutton" name="singlebutton" onClick="#"
							class="btn btn-danger">Reset</button> -->
							<br/><br/>
						<button id="generateCRSMetadata" name="singlebutton"
							onClick="generateCRSMetaData();return false;" class="btn btn-primary">Generate
							MetaData</button>
							<button id="generateCRSPayload" name="singlebutton"
							onClick="generateCRSPayload1();return false;" class="btn btn-primary">Generate
							Payload</button>
						<!-- <button id="generateCRSPayload" name="singlebutton"
							onClick="generateCRSPayload();return false;" class="btn btn-primary">Generate
							Payload</button> -->
							<br/>
							<br/>
							<button id="generateCRSPackage" name="singlebutton"
							onClick="generateCRSPackage();return false;" class="btn btn-primary">Generate
							Package</button>
					</div>
				</div>

			</div>
			</form:form>
		</div>
	</div>
</div>


<div class="modal fade" id="addNewIndividualAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="addindividual" id="addindividual">
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
						id="countryCodeReportingFI" path="accountholder.individualaddressVo.countryCode">
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
						id="addressTypeReportingFI" path="accountholder.individualaddressVo.addressType">
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
						placeholder="Add Address Free" path="accountholder.individualaddressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="accountholder.individualaddressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="accountholder.individualaddressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="accountholder.individualaddressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="accountholder.individualaddressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="accountholder.individualaddressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="accountholder.individualaddressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="accountholder.individualaddressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="accountholder.individualaddressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="accountholder.individualaddressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="saveNewIndividualAddressClicked(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>

<div class="modal fade" id="editNewIndividualAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="editNewIndividual" id="editNewIndividual">
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
						id="countryCodeReportingFI" path="accountholder.individualeditAddressVo.countryCode" >
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
						id="addressTypeReportingFI" path="accountholder.individualeditAddressVo.addressType">
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
						placeholder="Add Address Free" path="accountholder.individualeditAddressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="accountholder.individualeditAddressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="accountholder.individualeditAddressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="accountholder.individualeditAddressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="accountholder.individualeditAddressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="accountholder.individualeditAddressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="accountholder.individualeditAddressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="accountholder.individualeditAddressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="accountholder.individualeditAddressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="accountholder.individualeditAddressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="individualEditSaveAddress(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>



<div class="modal fade" id="individualAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="viewIndividualAdd" id="viewIndividualAdd">
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
						id="countryCodeReportingFI" path="accountholder.individualviewAddressVo.countryCode" disabled="true">
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
						id="addressTypeReportingFI" path="accountholder.individualviewAddressVo.addressType" disabled="true">
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
						placeholder="Add Address Free" path="accountholder.individualviewAddressVo.addressFree" readonly="true"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="accountholder.individualviewAddressVo.street" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="accountholder.individualviewAddressVo.buildingIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="accountholder.individualviewAddressVo.suitIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="accountholder.individualviewAddressVo.floorIdentifier" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="accountholder.individualviewAddressVo.districtName" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="accountholder.individualviewAddressVo.pob" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="accountholder.individualviewAddressVo.postCode" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="accountholder.individualviewAddressVo.city" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="accountholder.individualviewAddressVo.countrySubentity" readonly="true"></form:input>
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


<div class="modal fade" id="addAccountHolderIndividualName" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="viewAccountHolderIndividualName" id="viewAccountHolderIndividualName">
		<div class="modal-content">
			<div class="modal-header" id="popUpModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">ADD NEW NAME</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				<br/>
				<div class="form-group col-md-6">
					<label for="language">Name:</label> <form:input
						class="form-control" id="name" placeholder="Name" path="accountholder.individualName.name"
						name="Name"></form:input>
				 </div>
				 <div class="form-group col-md-6">
					<label for="">Name Type:</label> <form:select class="form-control"
						id="countryCodeReportingFI" path="accountholder.individualName.nameType">
						<!-- <option value="0">--Choose Name Type--</option>
						<option value="1">OECD201</option>
						<option value="2">OECD202</option>
						<option value="3">OECD203</option>
						<option value="4">OECD204</option>
						<option value="5">OECD205</option>
						<option value="6">OECD206</option>
						<option value="7">OECD207</option>
						<option value="8">OECD208</option> -->
						<c:forEach items="${nameType}" var="nameType">
							<form:option value="${nameType.nameType}">
								${nameType.nameType}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>		
				<div class="form-group col-md-6">
					<label for="language">Preceding Title:</label> <form:input
						class="form-control" id="precedingTitle" placeholder="Preceding Title"
						name="precedingTitle" path="accountholder.individualName.precedingTitle"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">First Name:</label> <form:input
						class="form-control" id="firstName" placeholder="First Name"
						name="firstName" path="accountholder.individualName.firstName"/> 
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				<div class="form-group col-md-6">
					<div id="accountHolderNameTitleGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<div id="accountHolderNameMiddleNameGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<label for="language">Name Prefix:</label> <form:input
						class="form-control" id="namePrefix" placeholder="Name Prefix"
						name="namePrefix" path="accountholder.individualName.namePrefix"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">Last Name:</label> <form:input
						class="form-control" id="lastName" placeholder="Last Name"
						name="lastName" path="accountholder.individualName.lastName"></form:input>
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="accountHolderNameGenerationIdentifierGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="accountHolderNameSuffixGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<label for="language">General Suffix:</label> <form:input
						class="form-control" id="generalSuffix" placeholder="General Suffix"
						name="generalSuffix" path="accountholder.individualName.generalSuffix"></form:input>
				 </div>	
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="saveNewIndividualNameClicked();" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>

<div class="modal fade" id="editAccountHolderIndividualName" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="editAccountHolderIndividualName" id="editAccountHolderIndividualName">
		<div class="modal-content">
			<div class="modal-header" id="popUpModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">EDIT NEW NAME</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				<br/>
				<div class="form-group col-md-6">
					<label for="language">Name:</label> <form:input
						class="form-control" id="name" placeholder="Name" path="accountholder.individualName.name"
						name="Name"></form:input>
				 </div>
				 <div class="form-group col-md-6">
					<label for="">Name Type:</label> <form:select class="form-control"
						id="countryCodeReportingFI" path="accountholder.individualName.nameType">
						<!-- <option value="0">--Choose Name Type--</option>
						<option value="1">OECD201</option>
						<option value="2">OECD202</option>
						<option value="3">OECD203</option>
						<option value="4">OECD204</option>
						<option value="5">OECD205</option>
						<option value="6">OECD206</option>
						<option value="7">OECD207</option>
						<option value="8">OECD208</option> -->
						<c:forEach items="${nameType}" var="nameType">
							<form:option value="${nameType.nameType}">
								${nameType.nameType}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>		
				<div class="form-group col-md-6">
					<label for="language">Preceding Title:</label> <form:input
						class="form-control" id="precedingTitle" placeholder="Preceding Title"
						name="precedingTitle" path="accountholder.individualName.precedingTitle"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">First Name:</label> <form:input
						class="form-control" id="firstName" placeholder="First Name"
						name="firstName" path="accountholder.individualName.firstName"/> 
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				<div class="form-group col-md-6">
					<div id="editaccountHolderNameTitleGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<div id="editaccountHolderNameMiddleNameGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<label for="language">Name Prefix:</label> <form:input
						class="form-control" id="namePrefix" placeholder="Name Prefix"
						name="namePrefix" path="accountholder.individualName.namePrefix"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">Last Name:</label> <form:input
						class="form-control" id="lastName" placeholder="Last Name"
						name="lastName" path="accountholder.individualName.lastName"></form:input>
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="editaccountHolderNameGenerationIdentifierGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="editaccountHolderNameSuffixGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<label for="language">General Suffix:</label> <form:input
						class="form-control" id="generalSuffix" placeholder="General Suffix"
						name="generalSuffix" path="accountholder.individualName.generalSuffix"></form:input>
				 </div>	
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="saveEditedNewIndividualNameClicked();" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>

<div class="modal fade" id="viewAccountHolderIndividualName1" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="viewAccountHolderIndividualName1" id="viewAccountHolderIndividualName1">
		<div class="modal-content">
			<div class="modal-header" id="popUpModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">VIEW NAME</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				<br/>
				<div class="form-group col-md-6">
					<label for="language">Name:</label> <form:input
						class="form-control" id="name" placeholder="Name" path="accountholder.individualName.name"
						name="Name"></form:input>
				 </div>
				 <div class="form-group col-md-6">
					<label for="">Name Type:</label> <form:select class="form-control"
						id="countryCodeReportingFI" path="accountholder.individualName.nameType">
						<!-- <option value="0">--Choose Name Type--</option>
						<option value="1">OECD201</option>
						<option value="2">OECD202</option>
						<option value="3">OECD203</option>
						<option value="4">OECD204</option>
						<option value="5">OECD205</option>
						<option value="6">OECD206</option>
						<option value="7">OECD207</option>
						<option value="8">OECD208</option> -->
						<c:forEach items="${nameType}" var="nameType">
							<form:option value="${nameType.nameType}">
								${nameType.nameType}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>		
				<div class="form-group col-md-6">
					<label for="language">Preceding Title:</label> <form:input
						class="form-control" id="precedingTitle" placeholder="Preceding Title"
						name="precedingTitle" path="accountholder.individualName.precedingTitle"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">First Name:</label> <form:input
						class="form-control" id="firstName" placeholder="First Name"
						name="firstName" path="accountholder.individualName.firstName"/> 
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				<div class="form-group col-md-6">
					<div id="viewaccountHolderNameTitleGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<div id="viewaccountHolderNameMiddleNameGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<label for="language">Name Prefix:</label> <form:input
						class="form-control" id="namePrefix" placeholder="Name Prefix"
						name="namePrefix" path="accountholder.individualName.namePrefix"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">Last Name:</label> <form:input
						class="form-control" id="lastName" placeholder="Last Name"
						name="lastName" path="accountholder.individualName.lastName"></form:input>
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="viewaccountHolderNameGenerationIdentifierGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="viewaccountHolderNameSuffixGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<label for="language">General Suffix:</label> <form:input
						class="form-control" id="generalSuffix" placeholder="General Suffix"
						name="generalSuffix" path="accountholder.individualName.generalSuffix"></form:input>
				 </div>	
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<!-- <button type="button" onClick="saveNewIndividualNameClicked();" class="btn btn-primary" data-dismiss="modal">Save</button> -->
			</div>
		</div>
		</form:form>
	</div>
</div>


<!-- Org Address -->

<div class="modal fade" id="addNewOrgAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="addorganisation" id="addorganisation">
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
						id="countryCodeReportingFI" path="accountholder.organisationaddressVo.countryCode">
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
						id="addressTypeReportingFI" path="accountholder.organisationaddressVo.addressType">
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
						placeholder="Add Address Free" path="accountholder.organisationaddressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="accountholder.organisationaddressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="accountholder.organisationaddressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="accountholder.organisationaddressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="accountholder.organisationaddressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="accountholder.organisationaddressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="accountholder.organisationaddressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="accountholder.organisationaddressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="accountholder.organisationaddressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="accountholder.organisationaddressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="saveNewOrgAddressClicked(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>

<div class="modal fade" id="editNewOrgAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="editNewOrg" id="editNewOrg">
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
						id="countryCodeReportingFI" path="accountholder.organisationeditAddressVo.countryCode" >
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
						id="addressTypeReportingFI" path="accountholder.organisationeditAddressVo.addressType">
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
						placeholder="Add Address Free" path="accountholder.organisationeditAddressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="accountholder.organisationeditAddressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="accountholder.organisationeditAddressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="accountholder.organisationeditAddressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="accountholder.organisationeditAddressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="accountholder.organisationeditAddressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="accountholder.organisationeditAddressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="accountholder.organisationeditAddressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="accountholder.organisationeditAddressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="accountholder.organisationeditAddressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="orgEditSaveAddress(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>



<div class="modal fade" id="orgAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="viewOrganisationalAdd" id="viewOrganisationalAdd">
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
						id="countryCodeReportingFI" path="accountholder.organisationviewAddressVo.countryCode" disabled="true">
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
						id="addressTypeReportingFI" path="accountholder.organisationviewAddressVo.addressType" disabled="true">
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
						placeholder="Add Address Free" path="accountholder.organisationviewAddressVo.addressFree" readonly="true"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="accountholder.organisationviewAddressVo.street" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="accountholder.organisationviewAddressVo.buildingIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="accountholder.organisationviewAddressVo.suitIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="accountholder.organisationviewAddressVo.floorIdentifier" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="accountholder.organisationviewAddressVo.districtName" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="accountholder.organisationviewAddressVo.pob" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="accountholder.organisationviewAddressVo.postCode" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="accountholder.organisationviewAddressVo.city" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="accountholder.organisationviewAddressVo.countrySubentity" readonly="true"></form:input>
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
<!-- End Org Address
 -->
 
 
 <!-- Controlling Person Address -->
 <div class="modal fade" id="addNewCtrlPersonAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="addctrlperson" id="addctrlperson">
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
						id="countryCodeReportingFI" path="accountholder.controllingPersonaddressVo.countryCode">
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
						id="addressTypeReportingFI" path="accountholder.controllingPersonaddressVo.addressType">
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
						placeholder="Add Address Free" path="accountholder.controllingPersonaddressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="accountholder.controllingPersonaddressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="accountholder.controllingPersonaddressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="accountholder.controllingPersonaddressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="accountholder.controllingPersonaddressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="accountholder.controllingPersonaddressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="accountholder.controllingPersonaddressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="accountholder.controllingPersonaddressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="accountholder.controllingPersonaddressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="accountholder.controllingPersonaddressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="saveNewCtrlPersonAddressClicked(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>

<div class="modal fade" id="editNewCtrlPersonAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="editNewCtrlPerson" id="editNewCtrlPerson">
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
						id="countryCodeReportingFI" path="accountholder.controllingPersoneditAddressVo.countryCode" >
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
						id="addressTypeReportingFI" path="accountholder.controllingPersoneditAddressVo.addressType">
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
						placeholder="Add Address Free" path="accountholder.controllingPersoneditAddressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="accountholder.controllingPersoneditAddressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="accountholder.controllingPersoneditAddressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="accountholder.controllingPersoneditAddressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="accountholder.controllingPersoneditAddressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="accountholder.controllingPersoneditAddressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="accountholder.controllingPersoneditAddressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="accountholder.controllingPersoneditAddressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="accountholder.controllingPersoneditAddressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="accountholder.controllingPersoneditAddressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="controllingPersonEditSaveAddress(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>



<div class="modal fade" id="ctrlPersonAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="viewCtrlPersonAdd" id="viewCtrlPersonAdd">
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
						id="countryCodeReportingFI" path="accountholder.controllingPersonviewAddressVo.countryCode" disabled="true">
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
						id="addressTypeReportingFI" path="accountholder.controllingPersonviewAddressVo.addressType" disabled="true">
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
						placeholder="Add Address Free" path="accountholder.controllingPersonviewAddressVo.addressFree" readonly="true"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="accountholder.controllingPersonviewAddressVo.street" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="accountholder.controllingPersonviewAddressVo.buildingIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="accountholder.controllingPersonviewAddressVo.suitIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="accountholder.controllingPersonviewAddressVo.floorIdentifier" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="accountholder.controllingPersonviewAddressVo.districtName" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="accountholder.controllingPersonviewAddressVo.pob" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="accountholder.controllingPersonviewAddressVo.postCode" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="accountholder.controllingPersonviewAddressVo.city" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="accountholder.controllingPersonviewAddressVo.countrySubentity" readonly="true"></form:input>
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
 
 
 <!-- Controlling Person Address -->

<!-- Controlling Person Name -->
<div class="modal fade" id="addAccountHolderCtrlPersonName" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="viewAccountHolderCtrlPersonName" id="viewAccountHolderCtrlPersonName">
		<div class="modal-content">
			<div class="modal-header" id="popUpModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">ADD NEW NAME</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				<br/>
				<div class="form-group col-md-6">
					<label for="language">Name:</label> <form:input
						class="form-control" id="name" placeholder="Name" path="accountholder.individualName.name"
						name="Name"></form:input>
				 </div>
				 <div class="form-group col-md-6">
					<label for="">Name Type:</label> <form:select class="form-control"
						id="countryCodeReportingFI" path="accountholder.individualName.nameType">
						<!-- <option value="0">--Choose Name Type--</option>
						<option value="1">OECD201</option>
						<option value="2">OECD202</option>
						<option value="3">OECD203</option>
						<option value="4">OECD204</option>
						<option value="5">OECD205</option>
						<option value="6">OECD206</option>
						<option value="7">OECD207</option>
						<option value="8">OECD208</option> -->
						<c:forEach items="${nameType}" var="nameType">
							<form:option value="${nameType.nameType}">
								${nameType.nameType}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>		
				<div class="form-group col-md-6">
					<label for="language">Preceding Title:</label> <form:input
						class="form-control" id="precedingTitle" placeholder="Preceding Title"
						name="precedingTitle" path="accountholder.individualName.precedingTitle"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">First Name:</label> <form:input
						class="form-control" id="firstName" placeholder="First Name"
						name="firstName" path="accountholder.individualName.firstName"/> 
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				<div class="form-group col-md-6">
					<div id="accountHolderNameTitleGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<div id="accountHolderNameMiddleNameGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<label for="language">Name Prefix:</label> <form:input
						class="form-control" id="namePrefix" placeholder="Name Prefix"
						name="namePrefix" path="accountholder.individualName.namePrefix"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">Last Name:</label> <form:input
						class="form-control" id="lastName" placeholder="Last Name"
						name="lastName" path="accountholder.individualName.lastName"></form:input>
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="accountHolderNameGenerationIdentifierGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="accountHolderNameSuffixGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<label for="language">General Suffix:</label> <form:input
						class="form-control" id="generalSuffix" placeholder="General Suffix"
						name="generalSuffix" path="accountholder.individualName.generalSuffix"></form:input>
				 </div>	
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button class="btn btn-danger" data-dismiss="modal">Close</button>
				<button  id ="saveaccountholder" onClick="saveNewIndividualNameClicked();" class="btn btn-primary" data-dismiss="modal">Save</button>
				
			</div>
		</div>
		</form:form>
	</div>
</div>



<!-- Controlling Person Name -->

<div class="modal fade" id="deleteConfirmation" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header modal-header-danger" id="alertModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">Delete Confirmation</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				<br/>
				     Are you sure you want to Delete?
				     <div id="idToDelete" style="display: none;"></div>
				     <div id="formId" style="display: none;"></div>
				<br/>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				<button type="button" onClick="proceedAccDelete();" class="btn btn-danger" data-dismiss="modal">Delete</button>
			</div>
		</div>
	</div>
</div>



<div class="modal fade" id="addAccountHolderCtrlNameName" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="accountHolderCtrlPersonName" id="accountHolderCtrlPersonName">
		<div class="modal-content">
			<div class="modal-header" id="popUpModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">ADD NEW NAME</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				<br/>
				<div class="form-group col-md-6">
					<label for="language">Name:</label> <form:input
						class="form-control" id="name" placeholder="Name" path="accountholder.controllingPersonVo.ctrlPersonName.name"
						name="Name"></form:input>
				 </div>
				 <div class="form-group col-md-6">
					<label for="">Name Type:</label> <form:select class="form-control"
						id="countryCodeReportingFI" path="accountholder.controllingPersonVo.ctrlPersonName.nameType">
						<!-- <option value="0">--Choose Name Type--</option>
						<option value="1">OECD201</option>
						<option value="2">OECD202</option>
						<option value="3">OECD203</option>
						<option value="4">OECD204</option>
						<option value="5">OECD205</option>
						<option value="6">OECD206</option>
						<option value="7">OECD207</option>
						<option value="8">OECD208</option> -->
						<c:forEach items="${nameType}" var="nameType">
							<form:option value="${nameType.nameType}">
								${nameType.nameType}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>		
				<div class="form-group col-md-6">
					<label for="language">Preceding Title:</label> <form:input
						class="form-control" id="precedingTitle" placeholder="Preceding Title"
						name="precedingTitle" path="accountholder.controllingPersonVo.ctrlPersonName.precedingTitle"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">First Name:</label> <form:input
						class="form-control" id="firstName" placeholder="First Name"
						name="firstName" path="accountholder.controllingPersonVo.ctrlPersonName.firstName"/> 
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				<div class="form-group col-md-6">
					<div id="accountHolderCtrlNameTitleGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<div id="accountHolderCtrlNameMiddleNameGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<label for="language">Name Prefix:</label> <form:input
						class="form-control" id="namePrefix" placeholder="Name Prefix"
						name="namePrefix" path="accountholder.controllingPersonVo.ctrlPersonName.namePrefix"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">Last Name:</label> <form:input
						class="form-control" id="lastName" placeholder="Last Name"
						name="lastName" path="accountholder.controllingPersonVo.ctrlPersonName.lastName"></form:input>
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="accountHolderCtrlNameGenerationIdentifierGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="accountHolderCtrlNameSuffixGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<label for="language">General Suffix:</label> <form:input
						class="form-control" id="generalSuffix" placeholder="General Suffix"
						name="generalSuffix" path="accountholder.controllingPersonVo.ctrlPersonName.generalSuffix"></form:input>
				 </div>	
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="saveNewCtrlPersonNameClicked();" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>
<div class="modal fade" id="viewAccountHolderCtrlName1" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="viewAccountHolderCtrlName1" id="viewAccountHolderCtrlName1">
		<div class="modal-content">
			<div class="modal-header" id="popUpModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">VIEW NAME</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				<br/>
				<div class="form-group col-md-6">
					<label for="language">Name:</label> <form:input
						class="form-control" id="name" placeholder="Name" path="accountholder.ctrlPersonName.name"
						name="Name"></form:input>
				 </div>
				 <div class="form-group col-md-6">
					<label for="">Name Type:</label> <form:select class="form-control"
						id="countryCodeReportingFI" path="accountholder.individualName.nameType">
						<!-- <option value="0">--Choose Name Type--</option>
						<option value="1">OECD201</option>
						<option value="2">OECD202</option>
						<option value="3">OECD203</option>
						<option value="4">OECD204</option>
						<option value="5">OECD205</option>
						<option value="6">OECD206</option>
						<option value="7">OECD207</option>
						<option value="8">OECD208</option> -->
						<c:forEach items="${nameType}" var="nameType">
							<form:option value="${nameType.nameType}">
								${nameType.nameType}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>		
				<div class="form-group col-md-6">
					<label for="language">Preceding Title:</label> <form:input
						class="form-control" id="precedingTitle" placeholder="Preceding Title"
						name="precedingTitle" path="accountholder.individualName.precedingTitle"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">First Name:</label> <form:input
						class="form-control" id="firstName" placeholder="First Name"
						name="firstName" path="accountholder.ctrlPersonName.firstName"/> 
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				<div class="form-group col-md-6">
					<div id="viewCtrlNameTitleGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<div id="viewCtrlNameMiddleNameGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<label for="language">Name Prefix:</label> <form:input
						class="form-control" id="namePrefix" placeholder="Name Prefix"
						name="namePrefix" path="accountholder.ctrlPersonName.namePrefix"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">Last Name:</label> <form:input
						class="form-control" id="lastName" placeholder="Last Name"
						name="lastName" path="accountholder.ctrlPersonName.lastName"></form:input>
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="viewCtrlNameGenerationIdentifierGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="viewCtrlNameSuffixGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<label for="language">General Suffix:</label> <form:input
						class="form-control" id="generalSuffix" placeholder="General Suffix"
						name="generalSuffix" path="accountholder.ctrlPersonName.generalSuffix"></form:input>
				 </div>	
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<!-- <button type="button" onClick="saveNewIndividualNameClicked();" class="btn btn-primary" data-dismiss="modal">Save</button> -->
			</div>
		</div>
		</form:form>
	</div>
</div>


<div class="modal fade" id="editAccountHolderCtrlNameName" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="editaccountHolderCtrlPersonName" id="editaccountHolderCtrlPersonName">
		<div class="modal-content">
			<div class="modal-header" id="popUpModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">ADD NEW NAME</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				<br/>
				<div class="form-group col-md-6">
					<label for="language">Name:</label> <form:input
						class="form-control" id="name" placeholder="Name" path="accountholder.controllingPersonVo.ctrlPersonName.name"
						name="Name"></form:input>
				 </div>
				 <div class="form-group col-md-6">
					<label for="">Name Type:</label> <form:select class="form-control"
						id="countryCodeReportingFI" path="accountholder.controllingPersonVo.ctrlPersonName.nameType">
						<!-- <option value="0">--Choose Name Type--</option>
						<option value="1">OECD201</option>
						<option value="2">OECD202</option>
						<option value="3">OECD203</option>
						<option value="4">OECD204</option>
						<option value="5">OECD205</option>
						<option value="6">OECD206</option>
						<option value="7">OECD207</option>
						<option value="8">OECD208</option> -->
						<c:forEach items="${nameType}" var="nameType">
							<form:option value="${nameType.nameType}">
								${nameType.nameType}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="clearfix"></div>		
				<div class="form-group col-md-6">
					<label for="language">Preceding Title:</label> <form:input
						class="form-control" id="precedingTitle" placeholder="Preceding Title"
						name="precedingTitle" path="accountholder.controllingPersonVo.ctrlPersonName.precedingTitle"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">First Name:</label> <form:input
						class="form-control" id="firstName" placeholder="First Name"
						name="firstName" path="accountholder.controllingPersonVo.ctrlPersonName.firstName"/> 
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				<div class="form-group col-md-6">
					<div id="accountHoldereditCtrlNameTitleGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<div id="accountHoldereditCtrlNameMiddleNameGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				 <div class="form-group col-md-6">
					<label for="language">Name Prefix:</label> <form:input
						class="form-control" id="namePrefix" placeholder="Name Prefix"
						name="namePrefix" path="accountholder.controllingPersonVo.ctrlPersonName.namePrefix"></form:input>
				 </div>	
				 <div class="form-group col-md-6">
					<label for="language">Last Name:</label> <form:input
						class="form-control" id="lastName" placeholder="Last Name"
						name="lastName" path="accountholder.controllingPersonVo.ctrlPersonName.lastName"></form:input>
				 </div>		
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="accountHoldereditCtrlNameGenerationIdentifierGridPU"></div>
					<div id="pager"></div>
				</div>
				 <div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<div id="accountHoldereditCtrlNameSuffixGridPU"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>		
				 <br/>
				  <div class="form-group col-md-6">
					<label for="language">General Suffix:</label> <form:input
						class="form-control" id="generalSuffix" placeholder="General Suffix"
						name="generalSuffix" path="accountholder.controllingPersonVo.ctrlPersonName.generalSuffix"></form:input>
				 </div>	
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="controllingPersonEditSaveName();" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>











<%-- <%@ include file="common/addNewAccountHolderAddress.jspf"%> --%>
<%@ include file="common/addNewAccountHolderBirthInfo.jspf"%>
<%@ include file="common/addNewAccountHolderOrganisationAddress.jspf"%>
<%-- <%@ include file="common/addNewAccountHolderControllingPerson.jspf"%> --%>
<%@ include file="common/addAccountHolderControllingPersonName.jspf"%>
<%-- <%@ include file="common/addAccountHolderName.jspf"%> --%>
<%@ include
	file="common/addNewAddressAccountHolderControllingPerson.jspf"%>
<%-- <%@ include file="common/addNewAccountHolderCPBirthInfo.jspf"%> --%>

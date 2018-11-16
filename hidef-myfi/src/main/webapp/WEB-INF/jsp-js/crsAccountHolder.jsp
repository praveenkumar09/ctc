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
<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
			<div class="panel-heading">
				<strong>ACCOUNT HOLDER</strong>
			</div>
			<div class="panel-body">
				<br />
				<div class="form-group col-md-6">
					<label for="language">Document Type Indicator: </label><select
						class="form-control" id="documentTypeIndicator">
						<option value="0">--Choose Document Type Indicator--</option>
						<option value="1">OECD0</option>
						<option value="2">OECD1</option>
						<option value="3">OECD2</option>
						<option value="4">OECD3</option>
						<option value="5">OECD10</option>
						<option value="5">OECD11</option>
						<option value="5">OECD12</option>
						<option value="5">OECD13</option>
					</select>
				</div>
				<div class="form-group col-md-6">
					<label for="accountNumber">Document Reference Id:</label> <input
						class="form-control" id="accountNumber"
						placeholder="Enter Account Number" name="accountNumber">
				</div>
				<div class="form-group col-md-6">
					<label for="accountNumber">Cor Message Reference Id:</label> <input
						class="form-control" id="accountNumber"
						placeholder="Enter Account Number" name="accountNumber">
				</div>
				<div class="form-group col-md-6">
					<label for="accountNumber">Cor Document Reference Id:</label> <input
						class="form-control" id="accountNumber"
						placeholder="Enter Account Number" name="accountNumber">
				</div>
				<div class="form-group col-md-6">
					<label for="accountNumber">Account Number:</label> <input
						class="form-control" id="accountNumber"
						placeholder="Enter Account Number" name="accountNumber">
				</div>
				<div class="form-group col-md-6">
					<label for="sel1">Account Number Type:</label> <br />
					<!-- <div id="numberType" class="custom-control custom-radio">
						<input type="radio" class="custom-control-input"
							id="defaultUnchecked" name="numberTypeRadios"> <label
							class="custom-control-label" for="defaultUnchecked">Undocumented
							Account</label> <input type="radio" class="custom-control-input"
							id="defaultUnchecked" name="numberTypeRadios"> <label
							class="custom-control-label" for="defaultUnchecked">Closed
							Account</label> <input type="radio" class="custom-control-input"
							id="defaultUnchecked" name="numberTypeRadios"> <label
							class="custom-control-label" for="defaultUnchecked">Dormant
							Account</label>
					</div> -->
				    <select id="multi-select-account-type" multiple="multiple">
						<option value="Undocumented">Undocumented Account</option>
						<option value="Closed">Closed Account</option>
						<option value="Dormant">Dormant Account</option>
					</select>

				</div>
				<div class="clearfix"></div>
				<div class="form-group col-md-6">
					<label for="language">Account Balance Type: </label><select
						class="form-control" id="docTypeIndicatorReportingFI">
						<option value="0">--Choose Account Balance Type--</option>
						<option value="1">AED</option>
						<option value="2">AFN</option>
						<option value="3">ALL</option>
						<option value="4">MYR</option>
						<option value="5">INR</option>
					</select>
				</div>
				<!-- <div class="form-group col-md-6">
					<label for="language">Payment Type: </label><select
						class="form-control" id="docTypeIndicatorReportingFI">
						<option value="0">--Choose Payment Type--</option>
						<option value="1">CRS501</option>
						<option value="2">CRS502</option>
						<option value="3">CRS503</option>
						<option value="4">CRS504</option>
					</select>
				</div>
				<div class="form-group col-md-6">
					<label for="language">Payment Amount Type: </label><select
						class="form-control" id="docTypeIndicatorReportingFI">
						<option value="0">--Choose Payment Amount Type--</option>
						<option value="1">AED</option>
						<option value="2">AFN</option>
						<option value="3">ALL</option>
						<option value="4">MYR</option>
						<option value="5">INR</option>
					</select>
				</div> -->
				<div class="clearfix"></div>
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Payment Details:</legend>
					<div class="form-group">
						<div class="form-group col-md-6">
							<div id="accountHolderControlingPersonPaymentGrid"></div>
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
					<legend class="scheduler-border">Controlling Person:</legend>
					<div class="form-group">
						<div class="clearfix"></div>
						<br />
						<div class="form-group col-md-6">
							<label for="language">Controlling Person Type: </label><select
								class="form-control" id="controllingPersonType">
								<option value="0">Choose Controlling Person Type</option>
								<option value="1">CRS801</option>
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
								<option value="13">CRS813</option>
							</select>
						</div>
						<div class="clearfix"></div>
						<br />
						<div class="form-group col-md-6">
							<div id="accountHolderControlingPersonResidentCountryGrid"></div>
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
							<div id="pager"></div>
						</div>
						<div class="clearfix"></div>
						<br />
						<div class="form-group col-md-6">
							<div id="accountHolderControllingPersonAddressGrid"></div>
							<div id="pager"></div>
						</div>
						<div class="clearfix"></div>
						<br />
						<div class="form-group col-md-6">
							<div id="accountHolderControllingPersonNationalityGrid"></div>
							<div id="pager"></div>
						</div>
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
							<label for="language">Birth Date:</label> <input
								class="form-control" id="birthDate" placeholder="Birth Date"
								name="birthDate">
						</div>
						<div class="form-group col-md-6">
							<label for="language">City:</label> <input class="form-control"
								id="city" placeholder="City" name="city">
						</div>
						<div class="form-group col-md-6">
							<label for="language">City Sub Entity:</label> <input
								class="form-control" id="citySubentity"
								placeholder="City Subentity" name="citySubentity">
						</div>
						<div class="form-group col-md-6">
							<label for="language">Country Code: </label><select
								class="form-control" id="birthCountryCode">
								<option value="0">--Choose Country Code--</option>
								<option value="1">AD</option>
								<option value="2">AF</option>
								<option value="3">US</option>
								<option value="4">MY</option>
								<option value="5">AU</option>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="language">Country Name:</label> <input
								class="form-control" id="countryName" placeholder="Country Name"
								name="countryName">
						</div>
						<!-- <div class="form-group col-md-6">
							<div id="accountHolderControllingPersonBirthInfoGrid"></div>
							<div id="pager"></div>
						</div> -->
						<div class="clearfix"></div>
						<div class="text-center">
							<br />
							<button id="saveControllingPersonButton" name="singlebutton"
								onClick="saveControllingPerson();return false;"
								class="btn btn-success">Save Controlling Person</button>
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
				</fieldset>
				<div class="clearfix"></div>
				<br />
				<div class="form-group col-md-6">
					<label for="sel1">Account Holder Type :</label> <br />
					<div id="accountHolderType" class="custom-control custom-radio">
						<input type="radio" class="custom-control-input"
							id="defaultUnchecked" name="accountHolderTypeRadio"
							value="individual"> <label class="custom-control-label"
							for="defaultUnchecked">Individual</label> <input type="radio"
							class="custom-control-input" id="defaultUnchecked"
							name="accountHolderTypeRadio" value="organization"> <label
							class="custom-control-label" for="defaultUnchecked">Organization</label>
					</div>
				</div>
				<div class="clearfix"></div>
				<div id="Individual">
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderResidentCountryGrid"></div>
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
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderAddressGrid"></div>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderNationalityGrid"></div>
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<!-- <div class="form-group col-md-6">
						<div id="accountHolderBirthInfoGrid"></div>
						<div id="pager"></div>
					</div> -->
					<div class="form-group col-md-6">
						<label for="language">Birth Date:</label> <input
							class="form-control" id="birthDate" placeholder="Birth Date"
							name="birthDate">
					</div>
					<div class="form-group col-md-6">
						<label for="language">City:</label> <input class="form-control"
							id="city" placeholder="City" name="city">
					</div>
					<div class="form-group col-md-6">
						<label for="language">City Sub Entity:</label> <input
							class="form-control" id="citySubentity"
							placeholder="City Subentity" name="citySubentity">
					</div>
					<div class="form-group col-md-6">
						<label for="language">Country Code: </label><select
							class="form-control" id="birthCountryCode">
							<option value="0">--Choose Country Code--</option>
							<option value="1">AD</option>
							<option value="2">AF</option>
							<option value="3">US</option>
							<option value="4">MY</option>
							<option value="5">AU</option>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label for="language">Country Name:</label> <input
							class="form-control" id="countryName" placeholder="Country Name"
							name="countryName">
					</div>
					<div class="clearfix"></div>
					<br />
				</div>
				<div class="clearfix"></div>
				<div id="Organization">
					<br />
					<div class="form-group col-md-6">
						<label for="language">Account Holder Type: </label><select
							class="form-control" id="docTypeIndicatorReportingFI">
							<option value="0">--Choose Account Holder Type--</option>
							<option value="1">CRS101</option>
							<option value="2">CRS102</option>
							<option value="3">CRS103</option>
						</select>
					</div>
					<div class="clearfix"></div>
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
						<div id="pager"></div>
					</div>
					<div class="clearfix"></div>
					<br />
					<div class="form-group col-md-6">
						<div id="accountHolderOrganisationAddressGrid"></div>
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
						<button id="singlebutton" name="singlebutton"
							onClick="saveAccountHolderInfo();" class="btn btn-primary">Save</button>
					</div>
				</div>
				<div class="clearfix"></div>
				<br />
				<div class="form-group col-md-6">
					<label for="language">Account Holder Details: </label><br /> <br />
					<div id="accountHolderGrid"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>
				<br />
				<div class="form-group">
					<div class="text-center">
						<br />
						<button id="singlebutton" name="singlebutton"
							onClick="showReportingFI();" class="btn btn-warning">Previous</button>
						<button id="singlebutton" name="singlebutton" onClick="#"
							class="btn btn-danger">Validate</button>
						<!-- <button id="singlebutton" name="singlebutton" onClick="#"
							class="btn btn-danger">Reset</button> -->
						<button id="singlebutton" name="singlebutton" onClick="#"
							class="btn btn-success">Generate Package</button>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>
<%@ include file="common/addNewAccountHolderAddress.jspf"%>
<%@ include file="common/addNewAccountHolderBirthInfo.jspf"%>
<%@ include file="common/addNewAccountHolderOrganisationAddress.jspf"%>
<%-- <%@ include file="common/addNewAccountHolderControllingPerson.jspf"%> --%>
<%@ include file="common/addAccountHolderControllingPersonName.jspf"%>
<%@ include
	file="common/addNewAddressAccountHolderControllingPerson.jspf"%>
<%-- <%@ include file="common/addNewAccountHolderCPBirthInfo.jspf"%> --%>

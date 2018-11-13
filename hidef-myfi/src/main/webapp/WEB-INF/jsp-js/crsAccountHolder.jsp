<link type="text/css" href="/css/panel-border.css" rel="stylesheet">
<script type="text/javascript" src="/js/crsAccountHolder.js"></script>
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
					<label for="accountNumber">Account Number:</label> <input
						class="form-control" id="accountNumber"
						placeholder="Enter Account Number" name="accountNumber">
				</div>
				<div class="form-group col-md-6">
					<label for="sel1">Number Type:</label> <br />
					<div id="numberType" class="custom-control custom-radio">
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
					</div>

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
				<div class="form-group col-md-6">
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
				</div>
				<div class="clearfix"></div>
				<br />
				<div class="form-group col-md-6">
					<div id="accountHolderControllingPersonGrid"></div>
					<div id="pager"></div>
				</div>
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
					<div class="form-group col-md-6">
						<div id="accountHolderBirthInfoGrid"></div>
						<div id="pager"></div>
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
				<label for="language">Account Holder Details: </label><br/>
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
<%@ include file="common/addNewAccountHolderControllingPerson.jspf"%>
<%@ include file="common/addAccountHolderControllingPersonName.jspf"%>
<%@ include file="common/addNewAddressAccountHolderControllingPerson.jspf"%>
<%@ include file="common/addNewAccountHolderCPBirthInfo.jspf"%>

<link type="text/css" href="${pageContext.request.contextPath}/css/panel-border.css" rel="stylesheet">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cbcreportingEntity.js"></script>
<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
		 <form:form  modelAttribute="hidef" method="POST" name="reportingentity" id="reportingentity">
			<div class="panel-heading">
				<strong>REPORTING ENTITY</strong>
			</div>
			<div class="panel-body">
				<br />
				<div class="form-group">
				<div class="form-group col-md-6">
						<form:label path="reportingEntity.tin">TIN<font color='red'>*</font>:</form:label> <form:input type="text" path="reportingEntity.tin"
							class="form-control" id="tin"
							placeholder="TIN"></form:input>
							<font color='red'><span id="tinError" class="mandatory-flag"></span></font>
					</div>
					<div class="form-group col-md-6">
						<form:label path="reportingEntity.tinIssuedBy">TIN Type<font color='red'>*</font>:</form:label> <form:input type="text" path="reportingEntity.tinIssuedBy"
							class="form-control" id="tintype"
							placeholder="TIN Type"></form:input>
							<font color='red'><span id="tintypeError" class="mandatory-flag"></span></font>
					</div>
					<div class="form-group col-md-6">
						<form:label for="" path="reportingEntity.tinType">TIN Issued By<font color='red'>*</font>:</form:label> 
						<form:select class="form-control" id="issuedBy" path="reportingEntity.tinType">
							<form:option value="0">Please choose</form:option>
						<%-- 	<form:option value="1">MY</form:option>
							<form:option value="2">SG</form:option>
							<form:option value="3">CN</form:option>
							<form:option value="4">AU</form:option>
							<form:option value="5">US</form:option> --%>
							<c:forEach items="${tinlist}" var="tin">
							<form:option value="${tin.countryCode}">
								${tin.countryCode}
							</form:option>
						</c:forEach>
						</form:select>
						<font color='red'><span id="issuedByError" class="mandatory-flag"></span></font>
					</div>
					<div class="form-group col-md-6">
						<form:label for="" path="reportingEntity.reportingRole">Reporting Role<font color='red'>*</font>:</form:label> <form:select class="form-control" path="reportingEntity.reportingRole"
							id="reportingRole">
							<form:option value="0">Please choose</form:option>
							<c:forEach items="${reportingRole}" var="reportingrole">
							<form:option value="${reportingrole.CBCRepRole}">
								${reportingrole.CBCRepRole}
							</form:option>
						</c:forEach>
						</form:select>
						<font color='red'><span id="reportingRoleError" class="mandatory-flag"></span></font>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path="reportingEntity.documentTypeIndicator">Document Type Indicator:</form:label>
						 <form:select class="form-control" id="docTypeIndic" path="reportingEntity.documentTypeIndicator">
							<form:option value="0">Please choose</form:option>
							<c:forEach items="${documentTypeIndicator}" var="documenttypeindic">
							<form:option value="${documenttypeindic.CBCDocumentType}">
								${documenttypeindic.CBCDocumentType}
							</form:option>
						</c:forEach>
						</form:select>
					</div>
					<div class="form-group col-md-6">
						<form:label path="reportingEntity.documentReferenceId">Document Reference Id:</form:label> <form:input type="text" path="reportingEntity.documentReferenceId"
							class="form-control" id="documentReferenceId"
							placeholder="Document Reference Id" disabled="true"></form:input>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path="reportingEntity.corMessageReferenceId">Corr Message Reference Id:</form:label> <form:input type="text"
							class="form-control" id="corrMessageReferenceId" path="reportingEntity.corMessageReferenceId"
							placeholder="Corr Message Reference Id"></form:input>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<form:label path="reportingEntity.corDocReferenceId">Corr Document Reference Id:</form:label> <form:input type="text" path="reportingEntity.corDocReferenceId"
							class="form-control" id="corrDocumentReferenceId"
							placeholder="Corr Document Reference Id"></form:input>
					</div>
				</div>
				<input type="hidden" id="residentCountry" value='${residentCountry}'/>
				<input type="hidden" id="nameTypedropdown" value='${nameTypeList}'/>
				<div class="clearfix"></div>
				<br /> <br />
				<div class="form-group col-md-6">
					<div id="reportingEntityResidentCountryGrid"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>
				<br /> <br />
				<div class="form-group col-md-6">
					<div id="reportingEntityNameGrid"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>
				<br /> <br />
				<div class="form-group col-md-6">
					<div id="reportingEntityOrgINGrid"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>
				<br /> <br />
				<div class="form-group col-md-6">
					<div id="reportingEntityAddressGrid"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>
				<br /> <br />
				<div class="form-group">
				<c:choose>
						<c:when test="${hidef.isSummaryView=='true'}">
					<div class="text-center">
						<button id="reportingEntityViewNext" name="singlebutton"
							onClick="showCbcReports(0,0,1);return false;" class="btn btn-primary">View Next Tab</button>
					</div>
					</c:when>
						<c:otherwise>
						<div class="text-center">
						<button id="singlebutton" name="singlebutton"
							onClick="cbcReportingEntityPrevious(1,0,0);return false;" class="btn btn-warning">Previous</button>
						&nbsp;&nbsp;
						<button id="singlebutton" name="singlebutton"
							onClick="showCbcReports(1,0,0);return false;" class="btn btn-primary">Next</button>
					</div>
						</c:otherwise>
						</c:choose>
				</div>
			</div>
			</form:form>
		</div>
	</div>
</div>



<div class="modal fade" id="addNewReportingEntityAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="reportingfiaddress" id="reportingfiaddress">
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
						id="countryCodeReportingFI" path="reportingEntity.addressVo.countryCode">
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
						id="addressTypeReportingFI" path="reportingEntity.addressVo.addressType">
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
						placeholder="Add Address Free" path="reportingEntity.addressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="reportingEntity.addressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="reportingEntity.addressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="reportingEntity.addressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="reportingEntity.addressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="reportingEntity.addressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="reportingEntity.addressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="reportingEntity.addressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="reportingEntity.addressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="reportingEntity.addressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="saveNewReportingEntityAddressClicked(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>

<div class="modal fade" id="editNewReportingEntityAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="editNewReportingEntity" id="editNewReportingEntity">
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
						id="countryCodeReportingFI" path="reportingEntity.editAddressVo.countryCode" >
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
						id="addressTypeReportingFI" path="reportingEntity.editAddressVo.addressType">
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
						placeholder="Add Address Free" path="reportingEntity.editAddressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="reportingEntity.editAddressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="reportingEntity.editAddressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="reportingEntity.editAddressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="reportingEntity.editAddressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="reportingEntity.editAddressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="reportingEntity.editAddressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="reportingEntity.editAddressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="reportingEntity.editAddressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="reportingEntity.editAddressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="reportEntityEditSaveAddress(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>



<div class="modal fade" id="viewReportingFiAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="viewReportingFiAdd" id="viewReportingFiAdd">
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
						id="countryCodeReportingFI" path="reportingEntity.viewAddressVo.countryCode" disabled="true">
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
						id="addressTypeReportingFI" path="reportingEntity.viewAddressVo.addressType" disabled="true">
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
						placeholder="Add Address Free" path="reportingEntity.viewAddressVo.addressFree" readonly="true"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="reportingEntity.viewAddressVo.street" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="reportingEntity.viewAddressVo.buildingIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="reportingEntity.viewAddressVo.suitIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="reportingEntity.viewAddressVo.floorIdentifier" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="reportingEntity.viewAddressVo.districtName" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="reportingEntity.viewAddressVo.pob" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="reportingEntity.viewAddressVo.postCode" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="reportingEntity.viewAddressVo.city" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="reportingEntity.viewAddressVo.countrySubentity" readonly="true"></form:input>
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


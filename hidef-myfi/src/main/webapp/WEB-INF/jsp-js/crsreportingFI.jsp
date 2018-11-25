<link type="text/css" href="${pageContext.request.contextPath}/css/panel-border.css" rel="stylesheet">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/crsreportingfi.js"></script>
<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
		 <form:form  modelAttribute="hidef" method="POST" name="crsreportingfi" id="crsreportingfi">
			<div class="panel-heading">
				<strong>REPORTING FI</strong>
			</div>
			<div class="panel-body">
				<div class="clearfix"></div>
				<br /> <br />
				<div class="form-group col-md-6">
					<div id="reportingFINameGrid"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>
								<br /> <br />
				<div class="form-group col-md-6">
					<div id="reportingFIIDGrid"></div>
					<div id="pager"></div>
				</div>

					<div class="clearfix"></div>
									<br /> <br />
				<div class="form-group col-md-6">
					<label for="language">Doc Type Indicator<font color='red'>*</font>:</label><form:select
							class="form-control" id="docTypeIndicatorReportingFI" path="crsreportingfi.documentTypeIndic">
							<form:option value="0">Please choose</form:option>
							<c:forEach items="${documentTypeIndicator}" var="documenttypeindic">
							<form:option value="${documenttypeindic.CBCDocumentType}">
								${documenttypeindic.CBCDocumentType}
							</form:option>
						</c:forEach>
						</form:select>
				</div>

				<div class="form-group col-md-6">
					<label for="language">Doc Ref Id<font color='red'>*</font>:</label> <form:input
						class="form-control" id="docRefId" placeholder="Doc Ref Id" path="crsreportingfi.docRefId"
						name="language"></form:input>
				</div>
				<div class="form-group col-md-6">
					<label for="language">Cor Message Ref Id:</label> <form:input
						class="form-control" id="corMessageRefId" placeholder="Cor Message Ref Id" path="crsreportingfi.corMmsgRefId"
						name="language"></form:input>
				</div>
				<div class="form-group col-md-6">
					<label for="language">Cor Doc Ref Id:</label> <form:input
						class="form-control" id="corDocRefId" placeholder="Cor Doc Ref Id" path="crsreportingfi.corDocRefId"
						name="language"></form:input>
				</div>
				<div class="clearfix"></div>
				<input type="hidden" id="residentCountry" value='${residentCountry}'/>
				<input type="hidden" id="nameTypedropdown" value='${nameTypeList}'/>
				<br /> <br />
				<div class="form-group col-md-6">
					<div id="reportingFIResidentCountryGrid"></div>
					<div id="pager"></div>
				</div>
				<div class="clearfix"></div>
				<br /> <br/>
				 <div class="form-group col-md-6">
					<label for="">Address<font color='red'>*</font>:</label> 
					</div>			
				<div class="clearfix"></div>
				<div class="form-group col-md-6">
					<div id="reportingFIAddressGrid"></div>
					<div id="pager"></div>
				</div>
				
				<div class="clearfix"></div>
										<br />						<br />
				<div class="form-group">
					<div class="text-center">
						<button id="singlebutton" name="singlebutton"
							onClick="ReportingFiPrevious();return false;" class="btn btn-warning">Previous</button>
							&nbsp;&nbsp;
						<button id="singlebutton" name="singlebutton"
							onClick="ReportingFiNext(1,0,0);return false;" class="btn btn-primary">Next</button>
					</div>
				</div>
			</div>
			</form:form>
		</div>
	</div>
</div>


<div class="modal fade" id="addNewReportingFiAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="addNewReportingFi" id="addNewReportingFi">
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
						id="countryCodeReportingFI" path="crsreportingfi.addressVo.countryCode">
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
						id="addressTypeReportingFI" path="crsreportingfi.addressVo.addressType">
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
						placeholder="Add Address Free" path="crsreportingfi.addressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="crsreportingfi.addressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="crsreportingfi.addressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="crsreportingfi.addressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="crsreportingfi.addressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="crsreportingfi.addressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="crsreportingfi.addressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="crsreportingfi.addressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="crsreportingfi.addressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="crsreportingfi.addressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="saveNewReportingFiAddressClicked(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>

<div class="modal fade" id="editNewReportingfiAddress" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" name="editNewReportingfi" id="editNewReportingfi">
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
						id="countryCodeReportingFI" path="crsreportingfi.editAddressVo.countryCode" >
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
						id="addressTypeReportingFI" path="crsreportingfi.editAddressVo.addressType">
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
						placeholder="Add Address Free" path="crsreportingfi.editAddressVo.addressFree"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="crsreportingfi.editAddressVo.street"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="crsreportingfi.editAddressVo.buildingIdentifier"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="crsreportingfi.editAddressVo.suitIdentifier" ></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="crsreportingfi.editAddressVo.floorIdentifier"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="crsreportingfi.editAddressVo.districtName"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="crsreportingfi.editAddressVo.pob"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="crsreportingfi.editAddressVo.postCode"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="crsreportingfi.editAddressVo.city"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="crsreportingfi.editAddressVo.countrySubentity"></form:input>
				    </div>				
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="reportingFiEditSaveAddress(); return false;" class="btn btn-primary" data-dismiss="modal">Save</button>
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
						id="countryCodeReportingFI" path="crsreportingfi.viewAddressVo.countryCode" disabled="true">
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
						id="addressTypeReportingFI" path="crsreportingfi.viewAddressVo.addressType" disabled="true">
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
						placeholder="Add Address Free" path="crsreportingfi.viewAddressVo.addressFree" readonly="true"></form:textarea>
				</div>
				<div class="clearfix"></div>
				<div id="addressFixContent">
				    <div class="form-group col-md-6">
					<label for="language">Street:</label> <form:input
						class="form-control" id="Street" placeholder="Street"
						name="language" path="crsreportingfi.viewAddressVo.street" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Building Identifier:</label> <form:input
						class="form-control" id="buildingIdentifier" placeholder="Building Identifier"
						name="language" path="crsreportingfi.viewAddressVo.buildingIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Suite Identifier:</label> <form:input
						class="form-control" id="suiteIdentifier" placeholder="Suite Identifier"
						name="language" path="crsreportingfi.viewAddressVo.suitIdentifier" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Floor Identifier:</label> <form:input
						class="form-control" id="floorIdentifier" placeholder="Floor Identifier"
						name="language" path="crsreportingfi.viewAddressVo.floorIdentifier" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">District Name:</label> <form:input
						class="form-control" id="districtName" placeholder="District Name"
						name="language" path="crsreportingfi.viewAddressVo.districtName" readonly="true"></form:input>
				    </div>
				     <div class="form-group col-md-6">
					<label for="language">POB:</label> <form:input
						class="form-control" id="pob" placeholder="POB"
						name="language" path="crsreportingfi.viewAddressVo.pob" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Post Code:</label> <form:input
						class="form-control" id="postCode" placeholder="Post Code"
						name="language" path="crsreportingfi.viewAddressVo.postCode" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">City<font color='red'>*</font>:</label> <form:input
						class="form-control" id="city" placeholder="City"
						name="language" path="crsreportingfi.viewAddressVo.city" readonly="true"></form:input>
				    </div>
				    <div class="form-group col-md-6">
					<label for="language">Country Sub Entity:</label> <form:input
						class="form-control" id="countrySubEntity" placeholder="Country Sub Entity"
						name="language" path="crsreportingfi.viewAddressVo.countrySubentity" readonly="true"></form:input>
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

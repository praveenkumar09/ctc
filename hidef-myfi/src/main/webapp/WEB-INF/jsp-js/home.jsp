<%@ include file="common/header.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link type="text/css" href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/css/fieldset.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jsgrid.min.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jsgrid-theme.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jsgrid.min.js"></script>

<script src="${pageContext.request.contextPath}/js/home.js"></script>
<script src="${pageContext.request.contextPath}/js/summary.js"></script>
<body>
	<%@ include file="common/navigation.jspf"%>
	<div id="crsbreadcrumb" class="crsbreadcrumb">
		<ul class="nav nav-tabs">
			<!-- <li id="userProfile" class="active"><a href="#" onclick="userProfileOnClick();">USER PROFILE</a></li> -->
			<!-- <li id="metadataBtn" class="active"><a href="#"
				onclick="metaDataOnClick();">METADATA</a></li>
			<li id="reportingFI"><a href="#" onclick="showReportingFI();">REPORTING
					FI</a></li>
			<li id="accountHolder"><a href="#"
				onclick="showAccountHolder();">ACCOUNT HOLDER</a></li> -->
				<li id="metadataBtn" class="active"><a href="#"
				onclick="return false;">METADATA</a></li>
			<li id="reportingFI"><a href="#" onclick="return false;">REPORTING
					FI</a></li>
			<li id="accountHolder"><a href="#"
				onclick="return false;">ACCOUNT HOLDER</a></li>
			<!-- <li id="config"><a href="#" onclick="showConfig();">CONFIGURATION</a></li> -->
		</ul>
		<div id="tabinfo"></div>
	</div>
	<div id="cbcbreadcrumb" class="cbcbreadcrumb">
		<ul class="nav nav-tabs">
			<!-- <li id="userProfile" class="active"><a href="#" onclick="userProfileOnClick();">USER PROFILE</a></li> -->
			<!-- <li id="cbcMetaDataBtn" class="active"><a href="#"
				onclick="cbcMetaDataOnClick();">METADATA</a></li>
			<li id="reportingEntity"><a href="#" onclick="showReportingEntity();">REPORTING
					ENTITY</a></li>
			<li id="cbcReports"><a href="#"
				onclick="showCbcReports(1,0,0);">CBC REPORTS</a></li>
				<li id="cbcAddInfo"><a href="#"
				onclick="showCbcAddInfo(1,0,0);">ADDITIONAL INFO</a></li> -->
				
				<li id="cbcMetaDataBtn" class="active"><a href="#"
				onclick="return false;">METADATA</a></li>
			<li id="reportingEntity"><a href="#" onclick="return false;">REPORTING
					ENTITY</a></li>
			<li id="cbcReports"><a href="#"
				onclick="return false;">CBC REPORTS</a></li>
				<li id="cbcAddInfo"><a href="#"
				onclick="return false;">ADDITIONAL INFO</a></li>
			<!-- <li id="config"><a href="#" onclick="showConfig();">CONFIGURATION</a></li> -->
		</ul>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" id="toProfilePage" value='${toProfilePage}'/>
		<div id="tabinfo"></div>
	</div>
	 <!-- <div class="form-group col-md-6" >
	<button id="singlebutton" name="singlebutton" onClick="doenloadExcel()"
							class="btn btn-primary">Download Excel</button>
							</div><div class="clearfix"></div>
							<br/>  -->
	<div id="summary" class="summary">
	<div class="form-group col-md-6" >
	<button id="singlebutton" name="singlebutton" onClick="doenloadExcel()"
							class="btn btn-primary">Download Excel</button>
							</div><div class="clearfix"></div>
							<br/> 
		<div class="form-group col-md-6">
							<div id="summaryGrid"></div>
							<div id="pager"></div>
							<div class="clearfix"></div>
		</div>
	</div>
	<script type="text/javascript">
$(function() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
 </script>
	
	
	<div class="modal fade" id="importpopup" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
	 <form:form  modelAttribute="hidef" enctype="multipart/form-data" action="import/excel" id="importExcel" method="POST" >
		<div class="modal-content">
			<div class="modal-header" id="popUpModelHeader">
				<h5 class="modal-title" id="exampleModalLabel">
					<strong><font color="white">IMPORT XML</font></strong>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="clearfix"></div>
				
				<div class="input-group input-file" name="Fichier1">
									<form:input type="text" path="importExcelFileName" class="form-control"
										placeholder='Choose a file...' /> 
<%-- 										<form:input name="configurationFile" path="userprofile.configurationFile" type="file"  style="display: none;"/> --%>
										<span
										class="input-group-btn">
										<button class="btn btn-default btn-choose" onclick="bs_input_file();" type="button">Choose XML</button>
									</span>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				<button type="button" onClick="onClickImportExcelCalled();" class="btn btn-success" data-dismiss="modal">Save</button>
			</div>
		</div>
		</form:form>
	</div>
</div>
	
	
	
	<div id="userProfile"></div>
	<div id="metaData"></div>
<%@ include file="common/crsname.jspf"%>
<%@ include file="common/addNewReportingFIAddress.jspf"%>
<%-- <%@ include file="common/addNewReportingEntityAddress.jspf"%> --%>
<%@ include file="common/addAccountHolderName.jspf"%>
<%-- <%@ include file="common/addNewReportsAddress.jspf"%> --%>
<%@ include file="common/viewModelDialog.jsp"%>
<%@ include file="common/deleteConfirmationBox.jspf"%>
</body>
<%@ include file="common/footer.jspf"%>
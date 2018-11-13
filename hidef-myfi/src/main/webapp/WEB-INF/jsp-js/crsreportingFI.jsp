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
							onClick="ReportingFiNext();return false;" class="btn btn-primary">Next</button>
					</div>
				</div>
			</div>
			</form:form>
		</div>
	</div>
</div>

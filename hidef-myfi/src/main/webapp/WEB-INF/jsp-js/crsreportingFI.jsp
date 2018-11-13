<link type="text/css" href="${pageContext.request.contextPath}/css/panel-border.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/crsreportingfi.js"></script>
<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
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
					<label for="language">Doc Type Indicator<font color='red'>*</font>:</label><select
							class="form-control" id="docTypeIndicatorReportingFI">
							<option value="0">--Choose Doc Type Indicator--</option>
							<option value="1">OECD0</option>
							<option value="2">OECD1</option>
							<option value="3">OECD2</option>
							<option value="4">OECD3</option>
							<option value="5">OECD10</option>
							<option value="6">OECD11</option>
							<option value="7">OECD12</option>
							<option value="8">OECD13</option>
						</select>
				</div>

				<div class="form-group col-md-6">
					<label for="language">Doc Ref Id<font color='red'>*</font>:</label> <input
						class="form-control" id="docRefId" placeholder="Doc Ref Id"
						name="language">
				</div>
				<div class="form-group col-md-6">
					<label for="language">Cor Message Ref Id:</label> <input
						class="form-control" id="corMessageRefId" placeholder="Cor Message Ref Id"
						name="language">
				</div>
				<div class="form-group col-md-6">
					<label for="language">Cor Doc Ref Id:</label> <input
						class="form-control" id="corDocRefId" placeholder="Cor Doc Ref Id"
						name="language">
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
		</div>
	</div>
</div>

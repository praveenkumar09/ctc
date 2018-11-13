<link type="text/css" href="/css/panel-border.css" rel="stylesheet">
<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
			<div class="panel-heading">
				<strong>METADATA</strong>
			</div>
			<div class="panel-body">
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<label for="">Sending Country:</label> <select
							class="form-control" id="sendingCountry">
							<option>MY</option>
							<option>SG</option>
							<option>CN</option>
							<option>AU</option>
							<option>US</option>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label for="">Receiving Country:</label> <select
							class="form-control" id="receivingCountry">
							<option>MY</option>
							<option>SG</option>
							<option>CN</option>
							<option>AU</option>
							<option>US</option>
						</select>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<label>Message Type Indicator:</label> <input type="text"
							class="form-control" id="msgType"
							placeholder="Message Type Indicator">
					</div>
					<div class="form-group col-md-6">
						<label>Message Type:</label> <input type="text"
							class="form-control" id="sendContactEmailAddress" value="CRS"
							placeholder="Message Type" readonly>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<label>Warning:</label>
						<textarea class="form-control" rows="5" id="warning"
							placeholder="Warning"></textarea>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<label>Contact:</label>
						<textarea class="form-control" rows="5" placeholder="Contact"></textarea>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="form-group col-md-6">
						<label class="">Reporting Period:</label> <input type="text"
							class="form-control" id="reportingPeriod"
							placeholder="Reporting Period">
					</div>
					<div class="form-group col-md-6">
						<label class="">Tax Year:</label> <input type="text"
							class="form-control" id="taxYear" placeholder="Tax Year">
					</div>
				</div>
				<div class="form-group">
					<div class="form-group col-md-6">
						<label class="">File Creation Time Stamp:</label> <input
							type="text" class="form-control" id="formCreationTimeStamp"
							placeholder="File Creation Timestamp">
					</div>
					<div class="form-group col-md-6">
						<label class="">Communication Type:</label> <input type="text"
							class="form-control" value="CRS" id="communicationType"
							placeholder="Communication Type" readonly>
					</div>
				</div>
				<div class="form-group">
					<div class="form-group col-md-6">
						<label class="">Sender File Id:</label> <input type="text"
							class="form-control" id="senderFileId"
							placeholder="Sender File Id">
					</div>
					<div class="form-group col-md-6">
						<label for="">File Format Code:</label> <select
							class="form-control" id="fileFormatCode">
							<option>JPG</option>
							<option>PDF</option>
							<option>RTF</option>
							<option>TXT</option>
							<option>XML</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="form-group col-md-6">
						<label for="">Binary Encoding:</label> <select
							class="form-control" id="binaryEncoding">
							<option>BASE64</option>
							<option>NONE</option>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label class="">Message Reference Id: </label> <input type="text"
							class="form-control" id="messageRefId"
							placeholder="Message Reference Id">
					</div>
				</div>
				<div class="form-group col-md-6">
						<label class="">Sender Contact Email Address:</label> <input type="text"
							class="form-control" id="senderContactEmailAddress"
							placeholder="Sender Contact Email Address">
					</div>
					<div class="form-group col-md-6">
						<label class="">Sending Company IN:</label> <input type="text"
							class="form-control" id="sendingCompanyIN"
							placeholder="Sending Company IN">
					</div>
				<div class="clearfix"></div>
										<br />
				<div class="text-center">
					<button id="singlebutton" name="singlebutton"
						onClick="showReportingFI();" class="btn btn-primary">Next</button>
				</div>
			</div>
		</div>
	</div>
</div>

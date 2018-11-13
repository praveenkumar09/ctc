<div class="container">
	<div class="row">
		<br /> <br />
		<div class="panel panel-default">
			<div class="panel-heading">
				<strong>CONFIGURATION</strong>
			</div>
			<div class="panel-body">
				<br />
				<div class="form-group col-md-6">
					<label for="sel1">Country Validator:</label> <select
						class="form-control" id="countryvalidator">
						<option>MALAYSIA</option>
						<option>SYRIAN ARAB REPUBLIC</option>
						<option>TAIWAN</option>
						<option>TAJIKISTAN</option>
						<option>TANZANIA, UNITED REPUBLIC OF</option>
						<option>TONGA</option>
						<option>TRINIDAD AND TOBAGO</option>
						<option>TURKMENISTAN</option>
					</select>
				</div>
				<div class="form-group col-md-6">
					<label for="sel1">TIN-Regx:</label> <select class="form-control"
						id="countryvalidator">
						<option>FACTA-GIIN</option>
					</select>

				</div>
				<div class="form-group col-md-6">
					<label for="sel1">Doc Ref ID Rule:</label> <select
						class="form-control" id="countryvalidator">
						<option>CRS</option>
					</select>

				</div>

				<div class="form-group col-md-6">
					<label for="sel1">Message Ref ID Rule:</label> <select
						class="form-control" id="countryvalidator">
						<option>CRS</option>
					</select>

				</div>

				<div class="form-group col-md-6">
					<label for="sel1">File Naming:</label> <select class="form-control"
						id="countryvalidator">
						<option>DEFAULT</option>
					</select>

				</div>

				<div class="form-group col-md-6">
					<label for="sel1">Default Currency:</label> <select
						class="form-control" id="countryvalidator">
						<option>CGD</option>
					</select>

				</div>

				<div class="form-group col-md-6">
					<label for="sel1">Default Currency Code:</label> <select
						class="form-control" id="countryvalidator">
						<option>MYR</option>
					</select>
				</div>

				<div class="form-group col-md-6">
					<label for="sel1">Configuration File</label>
				 <!-- <input type="file"
						class="form-control" data-icon="false"> -->
					<div class="form-group">
						<div class="input-group input-file">
							<input type="text" class="form-control"
								placeholder='Choose a file...' /> <span class="input-group-btn">
								<button class="btn btn-default btn-choose" onclick="bs_input_file();" type="button">Choose</button>
							</span>


						</div>
					</div>
				</div>

				<div class="form-group col-md-6">
					<label for="sel1">Configuration Sheet:</label> <select
						class="form-control" id="configurationsheet">
						<option>1706 Multiline</option>
						<option>1712 Basic Light Free Address</option>
						<option>1706 Basic Light</option>
					</select>

				</div>

				<div class="form-group col-md-6">
					<label for="defaultin">Default IN:</label> <input
						class="form-control" id="defaultin" placeholder="Enter Default IN"
						name="defaultin">
				</div>

				<div class="clearfix"></div>
				<div class="form-group">
					<div class="text-center">
						<br />
						<button id="singlebutton" name="singlebutton"
							onClick="showAccountHolder();" class="btn btn-warning">Previous</button>
						<button id="singlebutton" name="singlebutton" onClick="#"
							class="btn btn-info">Validate</button>
						<button id="singlebutton" name="singlebutton" onClick="#"
							class="btn btn-danger">Reset</button>
						<button id="singlebutton" name="singlebutton" onClick="#"
							class="btn btn-primary">Save</button>
						<button id="singlebutton" name="singlebutton" onClick="#"
							class="btn btn-success">Generate Package</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

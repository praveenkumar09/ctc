$(document).ready(function() {
		
	$('#addressTypeReportingFI').change(function() 
			{ 
			   var val = $(this).val();
			   if(val == 1){
				   console.log("Value is 1");
				   $("#addressFreeTextField").show();
			   }else if(val == 2){
				   console.log("Value is 2");
				   $("#addressFreeTextField").show();
				   $("#addressFixContent").show();
			   }
			});	
	
	$("#addressFreeTextField").hide();
	$("#addressFixContent").hide();
	
	
	var clients = [];
	var nameType = [ {
		"id" : "1",
		"name" : "OECD 201"
	}, {
		"id" : "2",
		"name" : "OECD 202"
	}, {
		"id" : "3",
		"name" : "OECD 203"
	}, {
		"id" : "4",
		"name" : "OECD 204"
	}, {
		"id" : "5",
		"name" : "OECD 205"
	}, {
		"id" : "6",
		"name" : "OECD 206"
	}, {
		"id" : "7",
		"name" : "OECD 207"
	}, {
		"id" : "8",
		"name" : "OECD 208"
	} ];

	var issuedBy = [ {
		"id" : "1",
		"name" : "MALAYSIA"
	}, {
		"id" : "2",
		"name" : "AUSTRALIA"
	}, {
		"id" : "3",
		"name" : "ANGOLA"
	}, {
		"id" : "4",
		"name" : "ALBANIA"
	} ]

	var valLog = "";

	$("#reportingFINameGrid")
			.jsGrid(
					{
						width : "205%",
						inserting : true,
						editing : true,
						sorting : true,
						paging : true,
						pageSize : 6,
						pageButtonCount : 5,
						data : clients,
						invalidNotify : function(args) {
							console.log("vallog last value"
									+ valLog);
							if (valLog == "firstNameCharectersLengthWrong") {
								$("#validateTextHere").text("");
								$("#validateTextHere").text(
										"Name Length is wrong");
								$('#crsNameModal')
										.modal('show');
							} else if (valLog == "nameIsEmpty") {
								$("#validateTextHere").text("");
								$("#validateTextHere")
										.text(
												"Please fill in all the mandatory fields");
								$('#crsNameModal')
										.modal('show');
							} else {
								$("#validateTextHere").text("");
								$("#validateTextHere")
										.text(
												"Please fill in all the mandatory fields");
								$('#crsNameModal')
										.modal('show');
							}
						},
						fields : [
								{
									title : "First Name<font color='red'>*</font>",
									name : "firstName",
									type : "text",
									width : 150,
									validate : function(value,
											item) {
										if (value.length == 0) {
											valLog = "nameIsEmpty";
										} else if (value.length >= 3) {
											console
													.log("Val log fist value"
															+ valLog);
											valLog = "firstNameCharectersLengthWrong";
											console
													.log("Val log second value"
															+ valLog);
										} else {
											valLog = "";
											return true;
										}
									}

								},
								{
									title : "Last Name<font color='red'>*</font>",
									name : "lastName",
									type : "text",
									width : 150,
									validate : "required"
								},
								{
									title : "Name Type<font color='red'>*</font>",
									name : "nameType",
									type : "select",
									width : 150,
									items : nameType,
									valueField : "id",
									textField : "name",
									validate : "required"
								}, {
									type : "control"
								} ]
					});

	$("#reportingFIIDGrid")
			.jsGrid(
					{
						width : "205%",
						inserting : true,
						editing : true,
						sorting : true,
						paging : true,
						pageSize : 6,
						pageButtonCount : 5,
						data : clients,
						invalidNotify : function(args) {
							console.log("vallog last value"
									+ valLog);
							if (valLog == "firstNameCharectersLengthWrong") {
								$("#validateTextHere").text("");
								$("#validateTextHere").text(
										"Name Length is wrong");
								$('#crsNameModal')
										.modal('show');
							} else if (valLog == "nameIsEmpty") {
								$("#validateTextHere").text("");
								$("#validateTextHere")
										.text(
												"Please fill in all the mandatory fields");
								$('#crsNameModal')
										.modal('show');
							} else {
								$("#validateTextHere").text("");
								$("#validateTextHere")
										.text(
												"Please fill in all the mandatory fields");
								$('#crsNameModal')
										.modal('show');
							}
						},
						fields : [
								{
									title : "Identification Number<font color='red'>*</font>",
									name : "idNumber",
									type : "text",
									width : 150,
									validate : function(value,
											item) {
										if (value.length == 0) {
											valLog = "nameIsEmpty";
										} else {
											valLog = "";
											return true;
										}
									}

								},
								{
									title : "In Type<font color='red'>*</font>",
									name : "inType",
									type : "text",
									width : 150,
									validate : "required"
								},
								{
									title : "Issued By<font color='red'>*</font>",
									name : "issuedBy",
									type : "select",
									width : 150,
									items : issuedBy,
									valueField : "id",
									textField : "name",
									validate : "required"
								}, {
									type : "control"
								} ]
					});

	var residentCountryCode = [ {
		"id" : "1",
		"name" : "MY"
	}, {
		"id" : "2",
		"name" : "AR"
	}, {
		"id" : "3",
		"name" : "AU"
	}, {
		"id" : "4",
		"name" : "US"
	} ]

	$("#reportingFIResidentCountryGrid")
			.jsGrid(
					{
						width : "205%",
						inserting : true,
						editing : true,
						sorting : true,
						paging : true,
						pageSize : 6,
						pageButtonCount : 5,
						data : clients,
						invalidNotify : function(args) {
							$("#validateTextHere").text("");
							$("#validateTextHere")
									.text(
											"Please fill in all the mandatory fields");
							$('#crsNameModal').modal('show');
						},
						fields : [
								{
									title : "Resident Country Code",
									name : "residentCountryCode",
									type : "select",
									width : 150,
									items : residentCountryCode,
									valueField : "id",
									textField : "name",
								},
								{
									title : "Issued By",
									name : "lastName",
									type : "select",
									width : 150,
									items : issuedBy,
									valueField : "id",
									textField : "name",
								},
								{
									title : "Name Type<font color='red'>*</font>",
									name : "nameType",
									type : "select",
									width : 150,
									items : nameType,
									valueField : "id",
									textField : "name",
									validate : "required"
								}, {
									type : "control"
								} ]
					});

	/*
	 * var somAddressType = [{"id" : "1","name" :
	 * "AddressFree"},{"id" : "2","name" : "AddressFix"}]; var
	 * somCountryCode = [{"id" : "1", "name" : "MY"},{"id" :
	 * "2", "name" : "AU"}];
	 */
	var object = {};

	$("#reportingFIAddressGrid")
			.jsGrid(
					{
						width : "205%",
						inserting : false,
						editing : false,
						sorting : false,
						paging : true,
						pageSize : 6,
						pageButtonCount : 5,
						controller : object,
						datatype : 'json',
						invalidNotify : function(args) {
							$("#validateTextHere").text("");
							$("#validateTextHere")
									.text(
											"Please fill in all the mandatory fields");
							$('#crsNameModal').modal('show');
						},
						fields : [
								{
									name : "id",
									title : "id",
									type : "text",
									visible : false,
									width : 10,
									items : object.id
								},
								{
									title : "Address Type",
									name : "addressType",
									type : "text",
									width : 150,
									items : object.addressType,
									visible : true
								},
								{
									title : "Country Code",
									name : "countryCode",
									type : "text",
									width : 150,
									items : object.countryCode,
									visible : true
								},
								{
									name : "button",
									headerTemplate : function() {
										return $("<button>")
												.attr("type",
														"button")
												.attr("class",
														"btn btn-success btn-sm")
												.text(
														"Click here to Add New Address")
												.on(
														"click",
														function() {
															addNewAddressReportingFIClicked();
														});
									},
									itemTemplate : function(
											value, item) {
										var $result = jsGrid.fields.control.prototype.itemTemplate
												.apply(this,
														arguments);

										var $customViewButton = $(
												"<button>")
												.attr("class",
														"btn btn-info btn-sm")
												.text("View")
												.click(
														function(
																e) {
															/*alert("ID: " +
															    item.id);*/
															$
																	.ajax({

																		url : '/admin/view/Address',
																		type : 'GET',
																		success : function(
																				data) {
																			$(
																					'#viewModelDialog')
																					.modal(
																							'show');
																			$(
																					'#modalBody')
																					.html(
																							data);
																		},
																		error : function(
																				request,
																				error) {
																			alert("Request: "
																					+ JSON
																							.stringify(request));
																		}
																	// e.stopPropagation();
																	});
														});

										var $customEditButton = $(
												"<button>")
												.attr("class",
														"btn btn-warning btn-sm")
												.text("Edit")
												.click(
														function(
																e) {
															// alert("ID: " +
															//item.id);
															e
																	.stopPropagation();
														});

										var $customDeleteButton = $(
												"<button>")
												.attr("class",
														"btn btn-danger btn-sm")
												.text("Delete")
												.click(
														function(
																e) {
															//alert("ID: " +
															//item.id);
															e
																	.stopPropagation();
														});

										return $("<div>")
												.append(
														$customViewButton)
												.append(
														"&nbsp;")
												.append(
														"&nbsp;")
												.append(
														$customEditButton)
												.append(
														"&nbsp;")
												.append(
														"&nbsp;")
												.append(
														$customDeleteButton);
										// return
										// $result.add($customButton);
									}
								} ]
					});
	
	
});

function addNewAddressReportingFIClicked(){
	$("#addNewReportingFiAddress").modal('show');
	$("#addressFreeTextField").hide();
	$("#addressFixContent").hide();
}

function saveNewReportingFIAddressClicked(){
	$.ajax({
		url : '/admin/tabs/accountHolder/saveAddress',
		type : 'GET',
		success : function(data) {
		   var object =  {"id":"1","addressType":"Address Free","countryCode":"MY"};
			console.log(object.addressType);
			console.log(object.id);
			console.log(object.countryCode);
			$("#reportingFIAddressGrid").jsGrid("insertItem", object).done(function() {
			    console.log("insertion completed");
			});
			
			
		},error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
	
}

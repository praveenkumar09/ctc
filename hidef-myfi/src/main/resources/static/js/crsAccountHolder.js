$(document)
		.ready(
				function() {

					$('.modal').on(
							"hidden.bs.modal",
							function(e) {
								if ($('.modal:visible').length) {
									$('.modal-backdrop').first().css(
											'z-index',
											parseInt($('.modal:visible').last()
													.css('z-index')) - 10);
									$('body').addClass('modal-open');
								}
							});

					$('#addressTypeAccountHolder').change(function() {
						var val = $(this).val();
						if (val == 1) {
							console.log("Value is 1");
							$("#addressFreeTextField").show();
						} else if (val == 2) {
							console.log("Value is 2");
							$("#addressFreeTextField").show();
							$("#addressFixContent").show();
						}
					});

					var object = {};

					$("#accountHolderGrid")
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
										fields : [ {
											name : "id",
											title : "id",
											type : "text",
											visible : false,
											width : 10,
											items : object.id
										}, {
											title : "Account Number",
											name : "accountNumber",
											type : "text",
											width : 150,
											items : object.accountNumber,
											visible : true
										}, {
											title : "Number Type",
											name : "numberType",
											type : "text",
											width : 150,
											items : object.numberType,
											visible : true
										}, {
											title : "Account Holder Type",
											name : "accoutHolderType",
											type : "text",
											width : 150,
											items : object.accoutHolderType,
											visible : true
										} ]
									});

					$('#addressTypeAccountOrgHolder').change(function() {
						var val = $(this).val();
						if (val == 1) {
							console.log("Value is 1");
							$("#addressFreeTextOrgField").show();
						} else if (val == 2) {
							console.log("Value is 2");
							$("#addressFreeTextOrgField").show();
							$("#addressFixOrgContent").show();
						}
					});

					$('#addressTypeAccountCPHolder').change(function() {
						var val = $(this).val();
						if (val == 1) {
							console.log("Value is 1");
							$("#addressFreeTextCPField").show();
						} else if (val == 2) {
							console.log("Value is 2");
							$("#addressFreeTextCPField").show();
							$("#addressFixCPContent").show();
						}
					});

					$("#addressFreeTextField").hide();
					$("#addressFixContent").hide();

					$('input:radio[name="accountHolderTypeRadio"]')
							.change(
									function() {
										if ($(this).is(':checked')
												&& $(this).val() == "organization") {
											$("#Organization").show();
											$("#Individual").hide();

											var residentCountryCode = [ {
												"id" : "1",
												"name" : "MY"
											}, {
												"id" : "2",
												"name" : "SG"
											}, {
												"id" : "3",
												"name" : "AU"
											}, {
												"id" : "4",
												"name" : "US"
											} ];

											$(
													"#accountHolderOrganisationResidentCountryGrid")
													.jsGrid(
															{
																width : "205%",
																inserting : true,
																editing : true,
																sorting : true,
																paging : true,
																pageSize : 6,
																pageButtonCount : 5,
																invalidNotify : function(
																		args) {
																	$(
																			"#validateTextHere")
																			.text(
																					"");
																	$(
																			"#validateTextHere")
																			.text(
																					"Please fill in all the mandatory fields");
																	$(
																			'#crsNameModal')
																			.modal(
																					'show');
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
																			type : "control"
																		} ]
															});

											var inType = [];
											var issuedBy = [ {
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
											} ];

											$(
													"#accountHolderOrganisationInTypeGrid")
													.jsGrid(
															{
																width : "205%",
																inserting : true,
																editing : true,
																sorting : true,
																paging : true,
																pageSize : 6,
																pageButtonCount : 5,
																invalidNotify : function(
																		args) {
																	$(
																			"#validateTextHere")
																			.text(
																					"");
																	$(
																			"#validateTextHere")
																			.text(
																					"Please fill in all the mandatory fields");
																	$(
																			'#crsNameModal')
																			.modal(
																					'show');
																},
																fields : [
																		{
																			title : "IN",
																			name : "inType",
																			type : "text",
																			width : 150,
																			items : inType,
																		},
																		{
																			title : "Issued By",
																			name : "issuedBy",
																			type : "select",
																			width : 150,
																			items : issuedBy,
																			valueField : "id",
																			textField : "name",
																		},
																		{
																			type : "control"
																		} ]
															});

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

											$(
													"#accountHolderOrganisationNameTypeGrid")
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
																invalidNotify : function(
																		args) {
																	console
																			.log("vallog last value"
																					+ valLog);
																	if (valLog == "firstNameCharectersLengthWrong") {
																		$(
																				"#validateTextHere")
																				.text(
																						"");
																		$(
																				"#validateTextHere")
																				.text(
																						"Name Length is wrong");
																		$(
																				'#crsNameModal')
																				.modal(
																						'show');
																	} else if (valLog == "nameIsEmpty") {
																		$(
																				"#validateTextHere")
																				.text(
																						"");
																		$(
																				"#validateTextHere")
																				.text(
																						"Please fill in all the mandatory fields");
																		$(
																				'#crsNameModal')
																				.modal(
																						'show');
																	} else {
																		$(
																				"#validateTextHere")
																				.text(
																						"");
																		$(
																				"#validateTextHere")
																				.text(
																						"Please fill in all the mandatory fields");
																		$(
																				'#crsNameModal')
																				.modal(
																						'show');
																	}
																},
																fields : [
																		{
																			title : "First Name<font color='red'>*</font>",
																			name : "firstName",
																			type : "text",
																			width : 150,
																			validate : function(
																					value,
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
																		},
																		{
																			type : "control"
																		} ]
															});

											var object = {};

											$(
													"#accountHolderOrganisationAddressGrid")
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
																invalidNotify : function(
																		args) {
																	$(
																			"#validateTextHere")
																			.text(
																					"");
																	$(
																			"#validateTextHere")
																			.text(
																					"Please fill in all the mandatory fields");
																	$(
																			'#crsNameModal')
																			.modal(
																					'show');
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
																				return $(
																						"<button>")
																						.attr(
																								"type",
																								"button")
																						.attr(
																								"class",
																								"btn btn-success btn-sm")
																						.text(
																								"Click here to Add New Address")
																						.on(
																								"click",
																								function() {
																									addNewAddressAccountHolderOrganisationClicked();
																								});
																			},
																			itemTemplate : function(
																					value,
																					item) {
																				var $result = jsGrid.fields.control.prototype.itemTemplate
																						.apply(
																								this,
																								arguments);

																				var $customViewButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-info btn-sm")
																						.text(
																								"View")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				var $customEditButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-warning btn-sm")
																						.text(
																								"Edit")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				var $customDeleteButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-danger btn-sm")
																						.text(
																								"Delete")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				return $(
																						"<div>")
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
										} else {
											$("#Individual").show();
											$("#Organization").hide();

											var residentCountryCode = [ {
												"id" : "1",
												"name" : "MY"
											}, {
												"id" : "2",
												"name" : "SG"
											}, {
												"id" : "3",
												"name" : "AU"
											}, {
												"id" : "4",
												"name" : "US"
											} ];

											$(
													"#accountHolderResidentCountryGrid")
													.jsGrid(
															{
																width : "205%",
																inserting : true,
																editing : true,
																sorting : true,
																paging : true,
																pageSize : 6,
																pageButtonCount : 5,
																invalidNotify : function(
																		args) {
																	$(
																			"#validateTextHere")
																			.text(
																					"");
																	$(
																			"#validateTextHere")
																			.text(
																					"Please fill in all the mandatory fields");
																	$(
																			'#crsNameModal')
																			.modal(
																					'show');
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
																			type : "control"
																		} ]
															});

											var issuedBy = [ {
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
											} ];

											var tin = [];

											$("#accountHolderTNGrid")
													.jsGrid(
															{
																width : "205%",
																inserting : true,
																editing : true,
																sorting : true,
																paging : true,
																pageSize : 6,
																pageButtonCount : 5,
																invalidNotify : function(
																		args) {
																	$(
																			"#validateTextHere")
																			.text(
																					"");
																	$(
																			"#validateTextHere")
																			.text(
																					"Please fill in all the mandatory fields");
																	$(
																			'#crsNameModal')
																			.modal(
																					'show');
																},
																fields : [
																		{
																			title : "TIN",
																			name : "tin",
																			type : "text",
																			width : 150,
																			items : tin,
																		},
																		{
																			title : "Issued By",
																			name : "issuedBy",
																			type : "select",
																			width : 150,
																			items : issuedBy,
																			valueField : "id",
																			textField : "name",
																		},
																		{
																			type : "control"
																		} ]
															});

											var object = {};

											$("#accountHolderNameGrid")
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
																invalidNotify : function(
																		args) {
																	$(
																			"#validateTextHere")
																			.text(
																					"");
																	$(
																			"#validateTextHere")
																			.text(
																					"Please fill in all the mandatory fields");
																	$(
																			'#crsNameModal')
																			.modal(
																					'show');
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
																			title : "Name",
																			name : "name",
																			type : "text",
																			width : 150,
																			items : object.name,
																			visible : true
																		},
																		{
																			title : "Name Type",
																			name : "nameType",
																			type : "text",
																			width : 150,
																			items : object.nameType,
																			visible : true
																		},
																		{
																			name : "button",
																			headerTemplate : function() {
																				return $(
																						"<button>")
																						.attr(
																								"type",
																								"button")
																						.attr(
																								"class",
																								"btn btn-success btn-sm")
																						.text(
																								"Click here to Add New Name")
																						.on(
																								"click",
																								function() {
																									addAccountHolderNameClicked();
																								});
																			},
																			itemTemplate : function(
																					value,
																					item) {
																				var $result = jsGrid.fields.control.prototype.itemTemplate
																						.apply(
																								this,
																								arguments);

																				var $customViewButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-info btn-sm")
																						.text(
																								"View")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				var $customEditButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-warning btn-sm")
																						.text(
																								"Edit")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				var $customDeleteButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-danger btn-sm")
																						.text(
																								"Delete")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				return $(
																						"<div>")
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

											var object = {};

											$("#accountHolderAddressGrid")
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
																invalidNotify : function(
																		args) {
																	$(
																			"#validateTextHere")
																			.text(
																					"");
																	$(
																			"#validateTextHere")
																			.text(
																					"Please fill in all the mandatory fields");
																	$(
																			'#crsNameModal')
																			.modal(
																					'show');
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
																				return $(
																						"<button>")
																						.attr(
																								"type",
																								"button")
																						.attr(
																								"class",
																								"btn btn-success btn-sm")
																						.text(
																								"Click here to Add New Address")
																						.on(
																								"click",
																								function() {
																									addNewAddressAccountHolderClicked();
																								});
																			},
																			itemTemplate : function(
																					value,
																					item) {
																				var $result = jsGrid.fields.control.prototype.itemTemplate
																						.apply(
																								this,
																								arguments);

																				var $customViewButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-info btn-sm")
																						.text(
																								"View")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				var $customEditButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-warning btn-sm")
																						.text(
																								"Edit")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				var $customDeleteButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-danger btn-sm")
																						.text(
																								"Delete")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				return $(
																						"<div>")
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

											var nationality = [ {
												"id" : "1",
												"name" : "MY"
											}, {
												"id" : "2",
												"name" : "SG"
											}, {
												"id" : "3",
												"name" : "AU"
											}, {
												"id" : "4",
												"name" : "US"
											} ];

											$("#accountHolderNationalityGrid")
													.jsGrid(
															{
																width : "205%",
																inserting : true,
																editing : true,
																sorting : true,
																paging : true,
																pageSize : 6,
																pageButtonCount : 5,
																invalidNotify : function(
																		args) {
																	$(
																			"#validateTextHere")
																			.text(
																					"");
																	$(
																			"#validateTextHere")
																			.text(
																					"Please fill in all the mandatory fields");
																	$(
																			'#crsNameModal')
																			.modal(
																					'show');
																},
																fields : [
																		{
																			title : "Nationality",
																			name : "nationality",
																			type : "select",
																			width : 150,
																			items : nationality,
																			valueField : "id",
																			textField : "name",
																		},
																		{
																			type : "control"
																		} ]
															});

											var object = {};

											$("#accountHolderBirthInfoGrid")
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
																invalidNotify : function(
																		args) {
																	$(
																			"#validateTextHere")
																			.text(
																					"");
																	$(
																			"#validateTextHere")
																			.text(
																					"Please fill in all the mandatory fields");
																	$(
																			'#crsNameModal')
																			.modal(
																					'show');
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
																			title : "Birth Date",
																			name : "birthDate",
																			type : "text",
																			width : 150,
																			items : object.birthDate,
																			visible : true
																		},
																		{
																			title : "City",
																			name : "city",
																			type : "text",
																			width : 150,
																			items : object.city,
																			visible : true
																		},
																		{
																			name : "button",
																			headerTemplate : function() {
																				return $(
																						"<button>")
																						.attr(
																								"type",
																								"button")
																						.attr(
																								"class",
																								"btn btn-success btn-sm")
																						.text(
																								"Click here to Add New Birth Info")
																						.on(
																								"click",
																								function() {
																									addAccountBirthInfoClicked();
																								});
																			},
																			itemTemplate : function(
																					value,
																					item) {
																				var $result = jsGrid.fields.control.prototype.itemTemplate
																						.apply(
																								this,
																								arguments);

																				var $customViewButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-info btn-sm")
																						.text(
																								"View")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				var $customEditButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-warning btn-sm")
																						.text(
																								"Edit")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				var $customDeleteButton = $(
																						"<button>")
																						.attr(
																								"class",
																								"btn btn-danger btn-sm")
																						.text(
																								"Delete")
																						.click(
																								function(
																										e) {
																									alert("ID: "
																											+ item.id);
																									e
																											.stopPropagation();
																								});

																				return $(
																						"<div>")
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
										}
									});

					var object = {};

					$("#accountHolderControllingPersonGrid")
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
													title : "Controlling Person Type",
													name : "personType",
													type : "text",
													width : 150,
													items : object.personType,
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
																		"Click here to Add New Controlling Person")
																.on(
																		"click",
																		function() {
																			addNewAccountHolderControllingPersonClicked();
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
																			alert("ID: "
																					+ item.id);
																			e
																					.stopPropagation();
																		});

														var $customEditButton = $(
																"<button>")
																.attr("class",
																		"btn btn-warning btn-sm")
																.text("Edit")
																.click(
																		function(
																				e) {
																			alert("ID: "
																					+ item.id);
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
																			alert("ID: "
																					+ item.id);
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

					$("#Individual").hide();
					$("#Organization").hide();

				});

function addAccountBirthInfoClicked() {

	$("#addAccountBirthInfo").modal('show');
	var countryCode = [ {
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
	} ];

	var tin = [];

	$("#accountHolderBirthCountryInfo").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "Former Country Code",
					name : "formerCountryCode",
					type : "text",
					width : 150,
					items : tin,
				}, {
					title : "Country Code",
					name : "countryCode",
					type : "select",
					width : 150,
					items : countryCode,
					valueField : "id",
					textField : "name",
				}, {
					type : "control"
				} ]
			});
}

function addAccountCPBirthInfoClicked() {

	$("#addAccountCPBirthInfo").modal('show');
	var countryCode = [ {
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
	} ];

	var tin = [];

	$("#accountHolderBirthCountryInfo").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "Former Country Code",
					name : "formerCountryCode",
					type : "text",
					width : 150,
					items : tin,
				}, {
					title : "Country Code",
					name : "countryCode",
					type : "select",
					width : 150,
					items : countryCode,
					valueField : "id",
					textField : "name",
				}, {
					type : "control"
				} ]
			});
}

function addAccountHolderNameClicked() {
	$("#addAccountHolderName").modal('show');

	var title = [];

	$("#accountHolderNameTitleGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "Title",
					name : "title",
					type : "text",
					width : 150,
					items : title,
				}, {
					type : "control"
				} ]
			});

	var middleName = [];

	$("#accountHolderNameMiddleNameGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "Middle Name",
					name : "middleName",
					type : "text",
					width : 150,
					items : middleName,
				}, {
					type : "control"
				} ]
			});

	var generationIdentifier = [];

	$("#accountHolderNameGenerationIdentifierGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "Generation Identifier",
					name : "generationIdentifier",
					type : "text",
					width : 150,
					items : generationIdentifier,
				}, {
					type : "control"
				} ]
			});

	var suffix = [];

	$("#accountHolderNameSuffixGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "Suffix",
					name : "suffix",
					type : "text",
					width : 150,
					items : suffix,
				}, {
					type : "control"
				} ]
			});

}

function saveNewAccountHolderName() {
	$("#addAccountHolderName").modal('hide');
	var object = {
		"id" : "1",
		"name" : "Test Name",
		"nameType" : "OECD208"
	};
	console.log(object.addressType);
	console.log(object.id);
	console.log(object.countryCode);
	$("#accountHolderNameGrid").jsGrid("insertItem", object).done(function() {
		console.log("insertion completed");
	});
}

function saveNewAccountHolderBirthInfo() {
	$("#addAccountBirthInfo").modal('hide');
	var object = {
		"id" : "1",
		"birthDate" : "12/12/1980",
		"city" : "Cheras"
	};
	console.log(object.addressType);
	console.log(object.id);
	console.log(object.countryCode);
	$("#accountHolderBirthInfoGrid").jsGrid("insertItem", object).done(
			function() {
				console.log("insertion completed");
			});

}

function saveNewAccountHolderCPBirthInfo() {
	$("#addAccountCPBirthInfo").modal('hide');
	var object = {
		"id" : "1",
		"birthDate" : "12/12/1980",
		"city" : "Cheras"
	};
	console.log(object.addressType);
	console.log(object.id);
	console.log(object.countryCode);
	$("#accountHolderControllingPersonBirthInfoGrid").jsGrid("insertItem",
			object).done(function() {
		console.log("insertion completed");
	});

}

function addNewAddressAccountHolderClicked() {
	$("#addNewAccountHolderAddress").modal('show');
	$("#addressFreeTextField").hide();
	$("#addressFixContent").hide();
}

function saveNewAccountHolderAddressClicked() {
	var object = {
		"id" : "1",
		"addressType" : "Address Free",
		"countryCode" : "MY"
	};
	console.log(object.addressType);
	console.log(object.id);
	console.log(object.countryCode);
	$("#accountHolderAddressGrid").jsGrid("insertItem", object).done(
			function() {
				console.log("insertion completed");
			});
}

function addNewAddressAccountHolderOrganisationClicked() {
	$("#addNewAccountHolderOrganisationAddress").modal('show');
	$("#addressFreeTextOrgField").hide();
	$("#addressFixOrgContent").hide();
}

function saveNewAccountHolderOrganisationAddressClicked() {
	var object = {
		"id" : "1",
		"addressType" : "Address Free",
		"countryCode" : "MY"
	};
	console.log(object.addressType);
	console.log(object.id);
	console.log(object.countryCode);
	$("#accountHolderOrganisationAddressGrid").jsGrid("insertItem", object)
			.done(function() {
				console.log("insertion completed");
			});
}

function saveNewAccountHolderControllingPersonClicked() {
	var object = {
		"id" : "1",
		"personType" : "CRS801"
	};
	$("#accountHolderControllingPersonGrid").jsGrid("insertItem", object).done(
			function() {
				console.log("insertion completed");
			});

}

function addNewAddressAccountHolderCPClicked() {
	$("#addNewAddressAccountHolderControllingPerson").modal('show');
	$("#addressFreeTextCPField").hide();
	$("#addressFixCPContent").hide();
}

function saveNewAccountHolderCPAddressClicked() {
	var object = {
		"id" : "1",
		"addressType" : "Address Free",
		"countryCode" : "MY"
	};
	console.log(object.addressType);
	console.log(object.id);
	console.log(object.countryCode);
	$("#accountHolderControllingPersonAddressGrid")
			.jsGrid("insertItem", object).done(function() {
				console.log("insertion completed");
			});
}

function addNewAccountHolderControllingPersonClicked() {
	$("#addNewAccountHolderControllingPerson").modal('show');

	var residentCountryCode = [ {
		"id" : "1",
		"name" : "MY"
	}, {
		"id" : "2",
		"name" : "SG"
	}, {
		"id" : "3",
		"name" : "AU"
	}, {
		"id" : "4",
		"name" : "US"
	} ];

	$("#accountHolderControlingPersonResidentCountryGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "Resident Country Code",
					name : "residentCountryCode",
					type : "select",
					width : 150,
					items : residentCountryCode,
					valueField : "id",
					textField : "name",
				}, {
					type : "control"
				} ]
			});

	var issuedBy = [ {
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
	} ];

	var tin = [];

	$("#accountHolderControllingPersonTNGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "TIN",
					name : "tin",
					type : "text",
					width : 150,
					items : tin,
				}, {
					title : "Issued By",
					name : "issuedBy",
					type : "select",
					width : 150,
					items : issuedBy,
					valueField : "id",
					textField : "name",
				}, {
					type : "control"
				} ]
			});

	var object = {};

	$("#accountHolderControllingPersonNameGrid")
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
							$("#validateTextHere").text(
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
									title : "Name",
									name : "name",
									type : "text",
									width : 150,
									items : object.name,
									visible : true
								},
								{
									title : "Name Type",
									name : "nameType",
									type : "text",
									width : 150,
									items : object.nameType,
									visible : true
								},
								{
									name : "button",
									headerTemplate : function() {
										return $("<button>")
												.attr("type", "button")
												.attr("class",
														"btn btn-success btn-sm")
												.text("Add Name")
												.on(
														"click",
														function() {
															addAccountHolderControllingPersonNameClicked();
														});
									},
									itemTemplate : function(value, item) {
										var $result = jsGrid.fields.control.prototype.itemTemplate
												.apply(this, arguments);

										var $customViewButton = $("<button>")
												.attr("class",
														"btn btn-info btn-sm")
												.text("View")
												.click(function(e) {
													alert("ID: " + item.id);
													e.stopPropagation();
												});

										var $customEditButton = $("<button>")
												.attr("class",
														"btn btn-warning btn-sm")
												.text("Edit")
												.click(function(e) {
													alert("ID: " + item.id);
													e.stopPropagation();
												});

										var $customDeleteButton = $("<button>")
												.attr("class",
														"btn btn-danger btn-sm")
												.text("Delete")
												.click(function(e) {
													alert("ID: " + item.id);
													e.stopPropagation();
												});

										return $("<div>").append(
												$customViewButton).append(
												"&nbsp;").append("&nbsp;")
												.append($customEditButton)
												.append("&nbsp;").append(
														"&nbsp;").append(
														$customDeleteButton);
										// return
										// $result.add($customButton);
									}
								} ]
					});

	var object = {};

	$("#accountHolderControllingPersonAddressGrid")
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
							$("#validateTextHere").text(
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
												.attr("type", "button")
												.attr("class",
														"btn btn-success btn-sm")
												.text(
														"Click here to Add New Address")
												.on(
														"click",
														function() {
															addNewAddressAccountHolderCPClicked();
														});
									},
									itemTemplate : function(value, item) {
										var $result = jsGrid.fields.control.prototype.itemTemplate
												.apply(this, arguments);

										var $customViewButton = $("<button>")
												.attr("class",
														"btn btn-info btn-sm")
												.text("View")
												.click(function(e) {
													alert("ID: " + item.id);
													e.stopPropagation();
												});

										var $customEditButton = $("<button>")
												.attr("class",
														"btn btn-warning btn-sm")
												.text("Edit")
												.click(function(e) {
													alert("ID: " + item.id);
													e.stopPropagation();
												});

										var $customDeleteButton = $("<button>")
												.attr("class",
														"btn btn-danger btn-sm")
												.text("Delete")
												.click(function(e) {
													alert("ID: " + item.id);
													e.stopPropagation();
												});

										return $("<div>").append(
												$customViewButton).append(
												"&nbsp;").append("&nbsp;")
												.append($customEditButton)
												.append("&nbsp;").append(
														"&nbsp;").append(
														$customDeleteButton);
										// return
										// $result.add($customButton);
									}
								} ]
					});

	var object = {};

	$("#accountHolderControllingPersonBirthInfoGrid")
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
							$("#validateTextHere").text(
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
									title : "Birth Date",
									name : "birthDate",
									type : "text",
									width : 150,
									items : object.birthDate,
									visible : true
								},
								{
									title : "City",
									name : "city",
									type : "text",
									width : 150,
									items : object.city,
									visible : true
								},
								{
									name : "button",
									headerTemplate : function() {
										return $("<button>")
												.attr("type", "button")
												.attr("class",
														"btn btn-success btn-sm")
												.text(
														"Click here to Add New Birth Info")
												.on(
														"click",
														function() {
															addAccountCPBirthInfoClicked();
														});
									},
									itemTemplate : function(value, item) {
										var $result = jsGrid.fields.control.prototype.itemTemplate
												.apply(this, arguments);

										var $customViewButton = $("<button>")
												.attr("class",
														"btn btn-info btn-sm")
												.text("View")
												.click(function(e) {
													alert("ID: " + item.id);
													e.stopPropagation();
												});

										var $customEditButton = $("<button>")
												.attr("class",
														"btn btn-warning btn-sm")
												.text("Edit")
												.click(function(e) {
													alert("ID: " + item.id);
													e.stopPropagation();
												});

										var $customDeleteButton = $("<button>")
												.attr("class",
														"btn btn-danger btn-sm")
												.text("Delete")
												.click(function(e) {
													alert("ID: " + item.id);
													e.stopPropagation();
												});

										return $("<div>").append(
												$customViewButton).append(
												"&nbsp;").append("&nbsp;")
												.append($customEditButton)
												.append("&nbsp;").append(
														"&nbsp;").append(
														$customDeleteButton);
										// return
										// $result.add($customButton);
									}
								} ]
					});

}

function addAccountHolderControllingPersonNameClicked() {

	$("#addAccountHolderControllingPersonName").modal('show');

	var title = [];

	$("#accountHolderNameTitleGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "Title",
					name : "title",
					type : "text",
					width : 150,
					items : title,
				}, {
					type : "control"
				} ]
			});

	var middleName = [];

	$("#accountHolderNameMiddleNameGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "Middle Name",
					name : "middleName",
					type : "text",
					width : 150,
					items : middleName,
				}, {
					type : "control"
				} ]
			});

	var generationIdentifier = [];

	$("#accountHolderNameGenerationIdentifierGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "Generation Identifier",
					name : "generationIdentifier",
					type : "text",
					width : 150,
					items : generationIdentifier,
				}, {
					type : "control"
				} ]
			});

	var suffix = [];

	$("#accountHolderNameSuffixGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ {
					title : "Suffix",
					name : "suffix",
					type : "text",
					width : 150,
					items : suffix,
				}, {
					type : "control"
				} ]
			});

}

function saveNewAccountHolderControllingPersonName() {
	$("#addAccountHolderControllingPersonName").modal('hide');
	var object = {
		"id" : "1",
		"name" : "Test Name",
		"nameType" : "OECD208"
	};
	console.log(object.addressType);
	console.log(object.id);
	console.log(object.countryCode);
	$("#accountHolderControllingPersonNameGrid").jsGrid("insertItem", object)
			.done(function() {
				console.log("insertion completed");
			});
}

function saveAccountHolderInfo(){
	var object = {
			"id" : "1",
			"accountNumber" : "123456",
			"numberType" : "MYR",
			"accoutHolderType":"Individual"
		};
		console.log(object.addressType);
		console.log(object.id);
		console.log(object.countryCode);
		$("#accountHolderGrid").jsGrid("insertItem", object).done(function() {
			console.log("insertion completed");
		});
	
	
}
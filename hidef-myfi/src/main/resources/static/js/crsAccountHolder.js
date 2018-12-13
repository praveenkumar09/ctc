$(document)
		.ready(
				function() {
					
					$('#multi-select-account-type').multiselect();

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
										autoload: true,
										controller: {

						                    loadData: function(filter) {
						                    	
//						                    	return $.ajax({url:  "/admin/cbc/loadResidentCountryGrid",data:filter
//						                        });
						                    	
						                    	
						                        var d = $.Deferred();
						                        $
						                            .ajax({
						                                type: 'GET',
						                                url: 'crs/loadAccountHolderMain',
						                                mimeType: 'application/json',
						                                contentType: 'application/json',
						                                success: function(
						                                    data) {
						                                	d.resolve(data);
						                                },
						                                error: function(e) {
						                                    alert("error: " +
						                                        e.responseText);
						                                }
						                            });

						                        return d.promise();
						                    }
						                },
										/*controller : object,*/
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
											name : "accountNumberType",
											type : "text",
											width : 150,
											items : object.accountNumberType,
											visible : true
										}
										, {
											title : "Document Type Indicator",
											name : "documentTypeIndic",
											type : "text",
											width : 150,
											items : object.documentTypeIndic,
											visible : true
										}, {
											name: "",
											itemTemplate : function(value,item){
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
							                        /*	e
							                            .stopPropagation();*/
							                           /* alert(JSON
							                                    .stringify(item));*/
							                          e
							                                .stopPropagation();
							                        	console.log(item);
							                          /* viewCBCReports(item);*/
							                        	viewAccountHolderMain(item);
							                           return false;
							                        });

							                var $customEditButton = $(
							                        "<button>")
							                    .attr("class",
							                        "btn btn-warning btn-sm")
							                    .text("Edit")
							                    .click(
							                        function(
							                            e) {
							                        	 e
							                             .stopPropagation();
							                        	 //alert("in here")
							                        	 console.log("inside item edit");
							                        /*editCBCReports(item);*/
							                       editAccountHolderMain(item);
							                        console.log("completed item edit");
							                        return false;
							                        	
							                        });

							                var $customDeleteButton = $(
							                        "<button>")
							                    .attr("class",
							                        "btn btn-danger btn-sm")
							                    .text("Delete")
							                    .click(
							                        function(
							                            e) {
							                        	e
							                            .stopPropagation();
							                        	/*deleteNewAddressReportsClicked(item);*/
							                        	deleteNewAccountHolderClicked(item);
							                        	return false;                                
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
											} 
										
										]
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

											var IN = [];
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
																		args) {},
																fields : [{
																	title : "IN",
																	name : "IN",
																	type : "text",
																	width : 150,
																	items : IN
																},
																		{
																			title : "IN Type",
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
																		args) {},
																fields : [
																		{
																			title : "Organisation Name<font color='red'>*</font>",
																			name : "firstName",
																			type : "text",
																			width : 150,
																			validate : "required"

																		},
																		{
																			title : "Last Name<font color='red'>*</font>",
																			name : "lastName",
																			type : "text",
																			width : 150,
																			visible :false
																		},
																		{
																			title : "Organisation Name Type<font color='red'>*</font>",
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
											var tinType = [];

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
																		},{
																			title : "TIN Type",
																			name : "tinType",
																			type : "text",
																			width : 150,
																			items : tinType,
																		},{
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
								autoload: true,
								controller: {

				                    loadData: function(filter) {
				                    	
//				                    	return $.ajax({url:  "/admin/cbc/loadResidentCountryGrid",data:filter
//				                        });
				                    	
				                    	
				                        var d = $.Deferred();
				                        $
				                            .ajax({
				                                type: 'GET',
				                                url: 'crs/loadCtrlPersonMain',
				                                mimeType: 'application/json',
				                                contentType: 'application/json',
				                                success: function(
				                                    data) {
				                                	d.resolve(data);
				                                },
				                                error: function(e) {
				                                    alert("error: " +
				                                        e.responseText);
				                                }
				                            });

				                        return d.promise();
				                    }
				                },
								/*controller : object,*/
								datatype : 'json',
								invalidNotify : function(args) {
									
								},
								fields : [ {
									name : "id",
									title : "id",
									type : "text",
									visible : false,
									width : 10,
									items : object.id
								}, {
									title : "Controlling Person Type",
									name : "controllingPersonType",
									type : "text",
									width : 150,
									items : object.controllingPersonType,
									visible : true
								},{

									name : "",
									/*headerTemplate : function() {
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
									},*/
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
															/*alert("ID: "
																	+ item.id);*/
															e
																	.stopPropagation();
															viewControllingPersonMain(item.id);
															return false;
														});

										var $customEditButton = $(
												"<button>")
												.attr("class",
														"btn btn-warning btn-sm")
												.text("Edit")
												.click(
														function(
																e) {
															/*alert("ID: "
																	+ item.id);*/
															e
																	.stopPropagation();
															editControllingPersonMain(item.id);
															return false;
														});

										var $customDeleteButton = $(
												"<button>")
												.attr("class",
														"btn btn-danger btn-sm")
												.text("Delete")
												.click(
														function(
																e) {
															/*alert("ID: "
																	+ item.id);*/
															e
																	.stopPropagation();
															deleteControllingPersonMain(item.id);
															$(
															"#accountHolderControllingPersonGrid")
															.jsGrid(
																	"loadData");
															return false;
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
					
					var residentCountryCode = $("#residentcountry").val();
					residentCountryCode = $.parseJSON(residentCountryCode);

					/*var residentCountryCode = [ {
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
					} ];*/
				
					$("#accountHolderControlingPersonResidentCountryGrid").jsGrid(
							{
								width : "205%",
								inserting : true,
								editing : true,
								sorting : true,
								paging : true,
								pageSize : 6,
								pageButtonCount : 5,
								autoload : true,
								controller : {

									loadData : function() {
										var d = $.Deferred();
										$
												.ajax({
													type : 'GET',
													url : 'crs/loadctrlResidentCountryGrid',
													mimeType : 'application/json',
													contentType : 'application/json',
													success : function(
															data) {
														d
																.resolve(data);
													},
													error : function(
															e) {
														alert("error: "
																+ e.responseText);
													}
												});

										return d.promise();
										/*return
										[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
									},

									insertItem : function(item) {
										var d = $.Deferred();

										$
												.ajax(
														{
															type : "POST",
															url : "crs/insertctrlResidentCountryGrid",
															data : JSON
																	.stringify(item),
															mimeType : 'application/json',
															contentType : 'application/json',
														})
												.done(
														function(
																response) {
															console
																	.log("done: "
																			+ JSON
																					.stringify(response));
															d
																	.resolve(response);
															// showReportingEntity();
															$(
																	"#accountHolderControlingPersonResidentCountryGrid")
																	.jsGrid(
																			"loadData");
														})
												.fail(
														function(
																msg) {
															console
																	.log("fail"
																			+ msg);
															d
																	.reject();
														});
									},

									updateItem : function(item) {
										var d = $.Deferred();
										$
												.ajax(
														{
															type : "POST",
															url : "crs/updatectrlResidentCountryGrid?id="
																	+ item.id,
															data : JSON
																	.stringify(item),
															mimeType : 'application/json',
															contentType : 'application/json',
														})
												.done(
														function(
																response) {
															console
																	.log("done: "
																			+ JSON
																					.stringify(response));
															d
																	.resolve(response);
														})
												.fail(
														function(
																msg) {
															console
																	.log("fail"
																			+ msg);
															d
																	.reject();
														});
									},

									deleteItem : function(item) {
										var d = $.Deferred();
										// alert('@@@@@@@@@@@@@' + item.id)

										$
												.ajax(
														{
															type : "POST",
															url : "crs/deletectrlResidentCountryGrid?id="
																	+ item.id,
															data : JSON
																	.stringify(item),
															mimeType : 'application/json',
															contentType : 'application/json',
														})
												.done(
														function(
																response) {
															console
																	.log("done: "
																			+ JSON
																					.stringify(response));
															d
																	.resolve(response);
														})
												.fail(
														function(
																msg) {
															console
																	.log("fail"
																			+ msg);
															d
																	.reject();
														});
									},

								},
								invalidNotify : function(args) {
									$("#validateTextHere").text("");
									$("#validateTextHere").text(
											"Please fill in all the mandatory fields");
									$('#crsNameModal').modal('show');
								},
								fields : [
								{
									title : "No.<font color='red'>*</font>",
									name : "id",
									type : "text",
									width : 150,
									validate : "required",
									visible : false
								},
								  {
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

					/*var issuedBy = [ {
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
					} ];*/
					var issuedBy =residentCountryCode;

					var tin = [];
                    var type = [];
					$("#accountHolderControllingPersonTNGrid").jsGrid(
							{
								width : "205%",
								inserting : true,
								editing : true,
								sorting : true,
								paging : true,
								pageSize : 6,
								pageButtonCount : 5,
								autoload : true,
								controller : {

									loadData : function() {
										var d = $.Deferred();
										$
												.ajax({
													type : 'GET',
													url : 'crs/loadctrlOrganisationGrid',
													mimeType : 'application/json',
													contentType : 'application/json',
													success : function(
															data) {
														d
																.resolve(data);
													},
													error : function(
															e) {
														alert("error: "
																+ e.responseText);
													}
												});

										return d.promise();
										/*return
										[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
									},

									insertItem : function(item) {
										var d = $.Deferred();

										$
												.ajax(
														{
															type : "POST",
															url : "crs/insertctrlOrganisationGrid",
															data : JSON
																	.stringify(item),
															mimeType : 'application/json',
															contentType : 'application/json',
														})
												.done(
														function(
																response) {
															console
																	.log("done: "
																			+ JSON
																					.stringify(response));
															d
																	.resolve(response);
															// showReportingEntity();
															$(
																	"#accountHolderControllingPersonTNGrid")
																	.jsGrid(
																			"loadData");
														})
												.fail(
														function(
																msg) {
															console
																	.log("fail"
																			+ msg);
															d
																	.reject();
														});
									},

									updateItem : function(item) {
										var d = $.Deferred();
										$
												.ajax(
														{
															type : "POST",
															url : "crs/updatectrlOrganisationGrid?id="
																	+ item.id,
															data : JSON
																	.stringify(item),
															mimeType : 'application/json',
															contentType : 'application/json',
														})
												.done(
														function(
																response) {
															console
																	.log("done: "
																			+ JSON
																					.stringify(response));
															d
																	.resolve(response);
														})
												.fail(
														function(
																msg) {
															console
																	.log("fail"
																			+ msg);
															d
																	.reject();
														});
									},

									deleteItem : function(item) {
										var d = $.Deferred();
										// alert('@@@@@@@@@@@@@' + item.id)

										$
												.ajax(
														{
															type : "POST",
															url : "crs/deletectrlOrganisationGrid?id="
																	+ item.id,
															data : JSON
																	.stringify(item),
															mimeType : 'application/json',
															contentType : 'application/json',
														})
												.done(
														function(
																response) {
															console
																	.log("done: "
																			+ JSON
																					.stringify(response));
															d
																	.resolve(response);
														})
												.fail(
														function(
																msg) {
															console
																	.log("fail"
																			+ msg);
															d
																	.reject();
														});
									},

								},
								
								invalidNotify : function(args) {
									$("#validateTextHere").text("");
									$("#validateTextHere").text(
											"Please fill in all the mandatory fields");
									$('#crsNameModal').modal('show');
								},
								fields : [ 
								{
									title : "No.<font color='red'>*</font>",
									name : "id",
									type : "text",
									width : 150,
									validate : "required",
									visible : false
								},
								{
									title : "TIN",
									name : "in",
									type : "text",
									width : 150,
									items : tin,
								}, {
									title : "TIN Type",
									name : "inType",
									type : "text",
									width : 150,
									items : type,
								},{
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
					
					var nationality = [ {
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
					
					
					$("#accountHolderControllingPersonNationalityGrid").jsGrid({
						width : "205%",
						inserting : true,
						editing : true,
						sorting : true,
						paging : true,
						pageSize : 5,
						pageButtonCount : 5,
						invalidNotify : function(args) {},
						fields : [ {
							title : "Nationality",
							name : "nationality",
							type : "select",
							width : 150,
							items : nationality,
							valueField : "id",
							textField : "name",
						}, {
							type : "control"
						} ]
					});	
					
					var paymentType = $("#paymentType").val();
					paymentType = $.parseJSON(paymentType);
					
					var currencyCode = $("#currency").val();
					currencyCode = $.parseJSON(currencyCode);
					
					/*var paymentType = [ {
						"id" : "1",
						"name" : "CRS501"
					}, {
						"id" : "2",
						"name" : "CRS502"
					}, {
						"id" : "3",
						"name" : "CRS503"
					}, {
						"id" : "4",
						"name" : "CRS504"
					} ];
					
					var currencyCode = [ {
						"id" : "1",
						"name" : "MYR"
					}, {
						"id" : "2",
						"name" : "USD"
					}, {
						"id" : "3",
						"name" : "AED"
					}, {
						"id" : "4",
						"name" : "INR"
					} ];*/
					
					var amount =[];
					
					$("#accountHolderControlingPersonPaymentGrid").jsGrid({
						width : "205%",
						inserting : true,
						editing : true,
						sorting : true,
						paging : true,
						pageSize : 5,
						pageButtonCount : 5,
						autoload : true,
						controller : {
							loadData : function() {
								var d = $.Deferred();

								$
										.ajax({
											type : 'GET',
											url : 'crs/loadPaymentGrid',
											mimeType : 'application/json',
											contentType : "application/json; charset=utf-8",
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
							},
							/*
							 * loadData: function() { return
							 * [{"firstName":"MyAccount","lastName":"111-111-7890","nameType":"123456789"},{"firstName":"venki","lastName":"111-111-7890","nameType":"123456789"}]; },
							 */
							/* }, */

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertPaymentGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													//showReportingEntity();
													$(
															"#accountHolderControlingPersonPaymentGrid")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/updatePaymentGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								//alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deletePaymentGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
						invalidNotify : function(args) {},
						fields : [
							{
								title : "No.<font color='red'>*</font>",
								name : "id",
								type : "text",
								width : 150,
								validate : "required",
								visible : false
							},
						  
						  {
							title : "Payment Type",
							name : "paymentType",
							type : "select",
							width : 150,
							items : paymentType,
							valueField : "id",
							textField : "name",
						},{
							title : "Currrency Code",
							name : "currency",
							type : "select",
							width : 150,
							items : currencyCode,
							valueField : "id",
							textField : "name",
						},{
							title : "Amount",
							name : "amount",
							type : "text",
							width : 150,
							
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
										autoload : true,
										controller : {
											loadData : function() {
												var d = $.Deferred();

												$
														.ajax({
															type : 'GET',
															url : 'crs/loadctrlNameTypeMainGrid',
															mimeType : 'application/json',
															contentType : "application/json; charset=utf-8",
															success : function(
																	data) {
																d
																		.resolve(data);
															},
															error : function(
																	e) {
																alert("error: "
																		+ e.responseText);
															}
														});

												return d.promise();
											}
										},
										/*controller : object,*/
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
																			addAccountHolderCtrlPersonNameClicked();
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
																	/*alert("ID: " + item.id);*/
																	e.stopPropagation();
																	viewAccountHolderCtrlPersonNameClicked(item.id);
																	return false;
																});

														var $customEditButton = $("<button>")
																.attr("class",
																		"btn btn-warning btn-sm")
																.text("Edit")
																.click(function(e) {
																	/*alert("ID: " + item.id);*/
																	e.stopPropagation();
																	editAccountHolderCtrlPersonNameClicked(item.id);
																	return false;
																});

														var $customDeleteButton = $("<button>")
																.attr("class",
																		"btn btn-danger btn-sm")
																.text("Delete")
																.click(function(e) {
																	/*alert("ID: " + item.id);*/
																	e.stopPropagation();
																	deleteNewCtrlNameClicked(item.id);
																	$(
																	"#accountHolderControllingPersonNameGrid")
																	.jsGrid(
																			"loadData");
																	return false;
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
										autoload : true,
										controller : {
											loadData : function() {
												var d = $.Deferred();

												$
														.ajax({
															type : 'GET',
															url : 'crs/loadCtrlPersonAddress',
															mimeType : 'application/json',
															contentType : "application/json; charset=utf-8",
															success : function(
																	data) {
																d
																		.resolve(data);
															},
															error : function(
																	e) {
																alert("error: "
																		+ e.responseText);
															}
														});

												return d.promise();
											}
										},
										/*controller : object,*/
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
													/*items : object.id*/
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
																			addNewAddressControllingPersonClicked();
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
																	/*alert("ID: " + item.id);*/
																	controllingPersonViewAddress(item.id);
																	e.stopPropagation();
																	return false;
																});

														var $customEditButton = $("<button>")
																.attr("class",
																		"btn btn-warning btn-sm")
																.text("Edit")
																.click(function(e) {
																	/*alert("ID: " + item.id);*/
																	controllingPersonEditAddress(item.id);
																	e.stopPropagation();
																	return false;
																});

														var $customDeleteButton = $("<button>")
																.attr("class",
																		"btn btn-danger btn-sm")
																.text("Delete")
																.click(function(e) {
																	/*alert("ID: " + item.id);*/
																	controllingPersonDeleteAddress(item.id);
																	$(
																	"#accountHolderControllingPersonAddressGrid")
																	.jsGrid(
																			"loadData");
																	e.stopPropagation();
																	return false;
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

					/*$("#accountHolderControllingPersonBirthInfoGrid")
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
									});*/



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
	
	
	
	
	
	$("#addAccountHolderIndividualName").modal('show');


	
	
	$.ajax({
        url: 'crs/resetIndividualNameGrid',
        type: 'GET',
        async: false,
        data : $('#viewAccountHolderIndividualName').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#addAccountHolderIndividualName").html();
            $('#addAccountHolderIndividualName').html('');
            $('#addAccountHolderIndividualName').html(htmlFiltered);
            
        	var title = [];

        	var title = [];

        	$("#accountHolderNameTitleGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualNameTitleGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualNameTitleGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHolderNameTitleGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Title",
        					name : "name",
        					type : "text",
        					width : 150,
        					items : title,
        				}, {
        					type : "control"
        				} ]
        			});

        	var middleName = [];

        	$("#accountHolderNameMiddleNameGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualMiddileNameGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualMiddileNameGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHolderNameMiddleNameGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
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

        	$("#accountHolderNameGenerationIdentifierGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualGenIdGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualGenIdGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHolderNameGenerationIdentifierGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Generation Identifier",
        					name : "generateIdentifier",
        					type : "text",
        					width : 150,
        					items : generationIdentifier,
        				}, {
        					type : "control"
        				} ]
        			});

        	var suffix = [];

        	$("#accountHolderNameSuffixGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualSuffixGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualSuffixGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHolderNameSuffixGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
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
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });

}
function editAccountHolderNameClicked(id) {
	
	
	
	
	
	$("#editAccountHolderIndividualName").modal('show');


	
	
	$.ajax({
        url: 'crs/editIndividualNameGrid?id='+id,
        type: 'GET',
        async: false,
        data : $('#editAccountHolderIndividualName').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#editAccountHolderIndividualName").html();
            $('#editAccountHolderIndividualName').html('');
            $('#editAccountHolderIndividualName').html(htmlFiltered);
            
        	var title = [];

        	var title = [];

        	$("#editaccountHolderNameTitleGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualNameTitleGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualNameTitleGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#editaccountHolderNameTitleGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Title",
        					name : "name",
        					type : "text",
        					width : 150,
        					items : title,
        				}, {
        					type : "control"
        				} ]
        			});

        	var middleName = [];

        	$("#editaccountHolderNameMiddleNameGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualMiddileNameGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualMiddileNameGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#editaccountHolderNameMiddleNameGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
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

        	$("#editaccountHolderNameGenerationIdentifierGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualGenIdGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualGenIdGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#editaccountHolderNameGenerationIdentifierGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Generation Identifier",
        					name : "generateIdentifier",
        					type : "text",
        					width : 150,
        					items : generationIdentifier,
        				}, {
        					type : "control"
        				} ]
        			});

        	var suffix = [];

        	$("#editaccountHolderNameSuffixGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualSuffixGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualSuffixGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#editaccountHolderNameSuffixGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
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
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });

}
function viewAccountHolderNameClicked(id) {

	$("#viewAccountHolderIndividualName1").modal('show');
	
	
	$.ajax({
        url: 'crs/viewIndividualNameGrid?id='+id,
        type: 'GET',
        async: false,
        data : $('#viewAccountHolderIndividualName1').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#viewAccountHolderIndividualName1").html();
            $('#viewAccountHolderIndividualName1').html('');
            $('#viewAccountHolderIndividualName1').html(htmlFiltered);
            
            /*$('#viewAccountHolderIndividualName1 *').prop('readOnly', true);
			$('#viewAccountHolderIndividualName1 *').prop('disabled', true);*/
            $("#viewaccountHolderNameTitleGridPU").prop('disabled', true);
        	$("#viewaccountHolderNameMiddleNameGridPU").prop('disabled', true);
        	$("#viewaccountHolderNameGenerationIdentifierGridPU").prop('disabled', true);
        	$("#viewaccountHolderNameSuffixGridPU").prop('disabled', true);
        	
            
        	var title = [];

        	var title = [];

        	$("#viewaccountHolderNameTitleGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : false,
        				editing : false,
        				deleteButton: false,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualNameTitleGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							/*insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualNameTitleGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#viewaccountHolderNameTitleGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},*/

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Title",
        					name : "name",
        					type : "text",
        					width : 150,
        					items : title,
        				}, {
        					type : "control"
        				} ]
        			});

        	var middleName = [];

        	$("#viewaccountHolderNameMiddleNameGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualMiddileNameGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							/*insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualMiddileNameGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#viewaccountHolderNameMiddleNameGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},
*/
						},
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

        	$("#viewaccountHolderNameGenerationIdentifierGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualGenIdGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							/*insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualGenIdGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#viewaccountHolderNameGenerationIdentifierGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},*/

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Generation Identifier",
        					name : "generateIdentifier",
        					type : "text",
        					width : 150,
        					items : generationIdentifier,
        				}, {
        					type : "control"
        				} ]
        			});

        	var suffix = [];

        	$("#viewaccountHolderNameSuffixGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadIndividualSuffixGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							/*insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertIndividualSuffixGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#viewaccountHolderNameSuffixGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateIndividualSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteIndividualSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},
*/
						},
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
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });

}



function addAccountHolderCtrlPersonNameClicked() {
	
	
	$("#addAccountHolderCtrlNameName").modal('show');
	$.ajax({
        url: 'crs/resetCtrlNameGrid',
        type: 'GET',
        async: false,
        data : $('#accountHolderCtrlPersonName').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#addAccountHolderCtrlNameName").html();
            $('#addAccountHolderCtrlNameName').html('');
            $('#addAccountHolderCtrlNameName').html(htmlFiltered);
            
        	var title = [];

        	var title = [];

        	$("#accountHolderCtrlNameTitleGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonNameTitleGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonNameTitleGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHolderCtrlNameTitleGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Title",
        					name : "name",
        					type : "text",
        					width : 150,
        					items : title,
        				}, {
        					type : "control"
        				} ]
        			});

        	var middleName = [];

        	$("#accountHolderCtrlNameMiddleNameGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonMiddileNameGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonMiddileNameGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHolderCtrlNameMiddleNameGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
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

        	$("#accountHolderCtrlNameGenerationIdentifierGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonGenIdGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonGenIdGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHolderCtrlNameGenerationIdentifierGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Generation Identifier",
        					name : "generateIdentifier",
        					type : "text",
        					width : 150,
        					items : generationIdentifier,
        				}, {
        					type : "control"
        				} ]
        			});

        	var suffix = [];

        	$("#accountHolderCtrlNameSuffixGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonSuffixGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonSuffixGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHolderCtrlNameSuffixGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
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
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });

}
function viewAccountHolderCtrlPersonNameClicked(id) {
	
	
	$("#viewAccountHolderCtrlName1").modal('show');
	$.ajax({
        url: 'crs/viewctrlPersonInsertNameGrid?id='+id,
        type: 'GET',
        async: false,
        data : $('#viewAccountHolderCtrlName1').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#viewAccountHolderCtrlName1").html();
            $('#viewAccountHolderCtrlName1').html('');
            $('#viewAccountHolderCtrlName1').html(htmlFiltered);
            
        	var title = [];

        	var title = [];

        	$("#viewCtrlNameTitleGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonNameTitleGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonNameTitleGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#viewCtrlNameTitleGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Title",
        					name : "name",
        					type : "text",
        					width : 150,
        					items : title,
        				}, {
        					type : "control"
        				} ]
        			});

        	var middleName = [];

        	$("#viewCtrlNameMiddleNameGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonMiddileNameGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonMiddileNameGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#viewCtrlNameMiddleNameGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
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

        	$("#viewCtrlNameGenerationIdentifierGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonGenIdGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonGenIdGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#viewaccountHolderCtrlNameGenerationIdentifierGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Generation Identifier",
        					name : "generateIdentifier",
        					type : "text",
        					width : 150,
        					items : generationIdentifier,
        				}, {
        					type : "control"
        				} ]
        			});

        	var suffix = [];

        	$("#viewCtrlNameSuffixGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonSuffixGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonSuffixGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#viewCtrlNameSuffixGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
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
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });

}


function editAccountHolderCtrlPersonNameClicked(id) {
	
	
	$("#editAccountHolderCtrlNameName").modal('show');
	$.ajax({
        url: 'crs/editctrlPersonInsertNameGrid?id='+id,
        type: 'GET',
        async: false,
        data : $('#editaccountHolderCtrlPersonName').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#editAccountHolderCtrlNameName").html();
            $('#editAccountHolderCtrlNameName').html('');
            $('#editAccountHolderCtrlNameName').html(htmlFiltered);
            
        	var title = [];

        	var title = [];

        	$("#accountHoldereditCtrlNameTitleGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonNameTitleGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonNameTitleGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHoldereditCtrlNameTitleGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonNameTitleGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Title",
        					name : "name",
        					type : "text",
        					width : 150,
        					items : title,
        				}, {
        					type : "control"
        				} ]
        			});

        	var middleName = [];

        	$("#accountHoldereditCtrlNameMiddleNameGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonMiddileNameGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonMiddileNameGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHoldereditCtrlNameMiddleNameGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonMiddileNameGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
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

        	$("#accountHoldereditCtrlNameGenerationIdentifierGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonGenIdGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonGenIdGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHoldereditCtrlNameGenerationIdentifierGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonGenIdGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
        				invalidNotify : function(args) {
        					$("#validateTextHere").text("");
        					$("#validateTextHere").text(
        							"Please fill in all the mandatory fields");
        					$('#crsNameModal').modal('show');
        				},
        				fields : [ {
        					title : "Generation Identifier",
        					name : "generateIdentifier",
        					type : "text",
        					width : 150,
        					items : generationIdentifier,
        				}, {
        					type : "control"
        				} ]
        			});

        	var suffix = [];

        	$("#accountHoldereditCtrlNameSuffixGridPU").jsGrid(
        			{
        				width : "205%",
        				inserting : true,
        				editing : true,
        				sorting : true,
        				paging : true,
        				pageSize : 6,
        				pageButtonCount : 5,
        				autoload : true,
						controller : {

							loadData : function() {
								var d = $.Deferred();
								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonSuffixGrid',
											mimeType : 'application/json',
											contentType : 'application/json',
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
								/*return
								[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
							},

							insertItem : function(item) {
								var d = $.Deferred();

								$
										.ajax(
												{
													type : "POST",
													url : "crs/insertCtrlPersonSuffixGrid",
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
													// showReportingEntity();
													$(
															"#accountHoldereditCtrlNameSuffixGridPU")
															.jsGrid(
																	"loadData");
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							updateItem : function(item) {
								var d = $.Deferred();
								$
										.ajax(
												{
													type : "POST",
													url : "crs/updateCtrlPersonSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

							deleteItem : function(item) {
								var d = $.Deferred();
								// alert('@@@@@@@@@@@@@' + item.id)

								$
										.ajax(
												{
													type : "POST",
													url : "crs/deleteCtrlPersonSuffixGrid?id="
															+ item.id,
													data : JSON
															.stringify(item),
													mimeType : 'application/json',
													contentType : 'application/json',
												})
										.done(
												function(
														response) {
													console
															.log("done: "
																	+ JSON
																			.stringify(response));
													d
															.resolve(response);
												})
										.fail(
												function(
														msg) {
													console
															.log("fail"
																	+ msg);
													d
															.reject();
												});
							},

						},
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
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
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

//function addNewAccountHolderControllingPersonClicked() {}

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

function saveControllingPerson(){
	var object = {
			"id" : "1",
			"controllingPersonType" : "CRS802"
		};
	$("#accountHolderControllingPersonGrid").jsGrid("insertItem", object).done(function() {
		console.log("insertion completed");
	});
}
function accountHolderType(id){
	if(id == 'individual'){
		$("#Individual").show();
		$("#Organization").hide();
		debugger;
		var residentCountryCode = $("#residentcountry").val();
		residentCountryCode = $.parseJSON(residentCountryCode);
		
		var issuedBy = residentCountryCode;

		/*var residentCountryCode = [ {
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
		} ];*/

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
							autoload : true,
							controller : {

								loadData : function() {
									var d = $.Deferred();
									$
											.ajax({
												type : 'GET',
												url : 'crs/loadIndividualResidentCountryGrid',
												mimeType : 'application/json',
												contentType : 'application/json',
												success : function(
														data) {
													d
															.resolve(data);
												},
												error : function(
														e) {
													alert("error: "
															+ e.responseText);
												}
											});

									return d.promise();
									/*return
									[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
								},

								insertItem : function(item) {
									var d = $.Deferred();

									$
											.ajax(
													{
														type : "POST",
														url : "crs/insertIndividualResidentCountryGrid",
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
														// showReportingEntity();
														$(
																"#accountHolderResidentCountryGrid")
																.jsGrid(
																		"loadData");
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

								updateItem : function(item) {
									var d = $.Deferred();
									$
											.ajax(
													{
														type : "POST",
														url : "crs/updateIndividualResidentCountryGrid?id="
																+ item.id,
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

								deleteItem : function(item) {
									var d = $.Deferred();
									// alert('@@@@@@@@@@@@@' + item.id)

									$
											.ajax(
													{
														type : "POST",
														url : "crs/deleteIndividualResidentCountryGrid?id="
																+ item.id,
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

							},
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
										title : "No.<font color='red'>*</font>",
										name : "id",
										type : "text",
										width : 150,
										validate : "required",
										visible : false
									},
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

		/*var issuedBy = [ {
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
		} ];*/

		var tin = [];
		var tinType = [];

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
							autoload : true,
							controller : {

								loadData : function() {
									var d = $.Deferred();
									$
											.ajax({
												type : 'GET',
												url : 'crs/loadIndividualOrganisationGrid',
												mimeType : 'application/json',
												contentType : 'application/json',
												success : function(
														data) {
													d
															.resolve(data);
												},
												error : function(
														e) {
													alert("error: "
															+ e.responseText);
												}
											});

									return d.promise();
									/*return
									[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
								},

								insertItem : function(item) {
									var d = $.Deferred();

									$
											.ajax(
													{
														type : "POST",
														url : "crs/insertIndividualOrganisationGrid",
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
														// showReportingEntity();
														$(
																"#accountHolderTNGrid")
																.jsGrid(
																		"loadData");
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

								updateItem : function(item) {
									var d = $.Deferred();
									$
											.ajax(
													{
														type : "POST",
														url : "crs/updateIndividualOrganisationGrid?id="
																+ item.id,
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

								deleteItem : function(item) {
									var d = $.Deferred();
									// alert('@@@@@@@@@@@@@' + item.id)

									$
											.ajax(
													{
														type : "POST",
														url : "crs/deleteIndividualOrganisationGrid?id="
																+ item.id,
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

							},
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
										title : "No.<font color='red'>*</font>",
										name : "id",
										type : "text",
										width : 150,
										validate : "required",
										visible : false
									},
									
									
									{
										title : "TIN",
										name : "in",
										type : "text",
										width : 150,
										/*items : tin,*/
									},{
										title : "TIN Type",
										name : "inType",
										type : "text",
										width : 150,
										/*items : tinType,*/
									},{
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
							autoload : true,
							controller : {
								loadData : function() {
									var d = $.Deferred();

									$
											.ajax({
												type : 'GET',
												url : 'crs/loadNameTypeMainGrid',
												mimeType : 'application/json',
												contentType : "application/json; charset=utf-8",
												success : function(
														data) {
													d
															.resolve(data);
												},
												error : function(
														e) {
													alert("error: "
															+ e.responseText);
												}
											});

									return d.promise();
								}
							},
							/*controller : object,*/
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
																/*alert("ID: "
																		+ item.id);*/
																e
																		.stopPropagation();
																viewAccountHolderNameClicked(item.id);
																return false;
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
																/*alert("ID: "
																		+ item.id);*/
																editAccountHolderNameClicked(item.id);
																e
																		.stopPropagation();
																return false;
																
																
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
																/*alert("ID: "
																		+ item.id);*/
																e
																		.stopPropagation();
																deleteNewIndividualNameClicked(item.id);
																$(
																"#accountHolderNameGrid")
																.jsGrid(
																		"loadData");
																return false;
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
							autoload : true,
							controller : {
								loadData : function() {
									var d = $.Deferred();

									$
											.ajax({
												type : 'GET',
												url : 'crs/loadIndividualAddress',
												mimeType : 'application/json',
												contentType : "application/json; charset=utf-8",
												success : function(
														data) {
													d
															.resolve(data);
												},
												error : function(
														e) {
													alert("error: "
															+ e.responseText);
												}
											});

									return d.promise();
								}
							},
							/*controller : object,*/
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
											width : 10
										/*,
										items: object.id*/
									
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
																addNewAddressIndividualClicked();
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
																/*alert("ID: "
																		+ item.id);*/
																individualViewAddress(item.id);
																e
																		.stopPropagation();
																return false;
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
																/*alert("ID: "
																		+ item.id);*/
																individualEditAddress(item.id);
																e
																		.stopPropagation();
																return false;
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
															/*	alert("ID: "
																		+ item.id);*/
																individualDeleteAddress(item.id);
																e
																		.stopPropagation();
																return false;
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
	
		
	}else if(id == 'organization'){
		
		var residentCountryCode = $("#residentcountry").val();
		residentCountryCode = $.parseJSON(residentCountryCode);
		
		var issuedBy = residentCountryCode;

		$("#Organization").show();
		$("#Individual").hide();

		/*var residentCountryCode = [ {
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
		} ];*/

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
							autoload : true,
							controller : {

								loadData : function() {
									var d = $.Deferred();
									$
											.ajax({
												type : 'GET',
												url : 'crs/loadOrganisationResidentCountryGrid',
												mimeType : 'application/json',
												contentType : 'application/json',
												success : function(
														data) {
													d
															.resolve(data);
												},
												error : function(
														e) {
													alert("error: "
															+ e.responseText);
												}
											});

									return d.promise();
									/*return
									[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
								},

								insertItem : function(item) {
									var d = $.Deferred();

									$
											.ajax(
													{
														type : "POST",
														url : "crs/insertOrganisationResidentCountryGrid",
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
														// showReportingEntity();
														$(
																"#accountHolderOrganisationResidentCountryGrid")
																.jsGrid(
																		"loadData");
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

								updateItem : function(item) {
									var d = $.Deferred();
									$
											.ajax(
													{
														type : "POST",
														url : "crs/updateOrganisationResidentCountryGrid?id="
																+ item.id,
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

								deleteItem : function(item) {
									var d = $.Deferred();
									// alert('@@@@@@@@@@@@@' + item.id)

									$
											.ajax(
													{
														type : "POST",
														url : "crs/deleteOrganisationResidentCountryGrid?id="
																+ item.id,
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

							},
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
										width : 10
									/*,
									items: object.id*/
									
									},
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

		var IN = [];
		var inType = [];
		/*var issuedBy = [ {
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
		} ];*/

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
							autoload : true,
							controller : {

								loadData : function() {
									var d = $.Deferred();
									$
											.ajax({
												type : 'GET',
												url : 'crs/loadOrgOrganisationGrid',
												mimeType : 'application/json',
												contentType : 'application/json',
												success : function(
														data) {
													d
															.resolve(data);
												},
												error : function(
														e) {
													alert("error: "
															+ e.responseText);
												}
											});

									return d.promise();
									/*return
									[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
								},

								insertItem : function(item) {
									var d = $.Deferred();

									$
											.ajax(
													{
														type : "POST",
														url : "crs/insertOrgOrganisationGrid",
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
														// showReportingEntity();
														$(
																"#accountHolderOrganisationInTypeGrid")
																.jsGrid(
																		"loadData");
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

								updateItem : function(item) {
									var d = $.Deferred();
									$
											.ajax(
													{
														type : "POST",
														url : "crs/updateOrgOrganisationGrid?id="
																+ item.id,
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

								deleteItem : function(item) {
									var d = $.Deferred();
									// alert('@@@@@@@@@@@@@' + item.id)

									$
											.ajax(
													{
														type : "POST",
														url : "crs/deleteOrgOrganisationGrid?id="
																+ item.id,
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

							},
							invalidNotify : function(
									args) {},
							fields : [
							 
							{
								
								name : "id",
								title : "id",
								type : "text",
								visible : false,
								width : 10
							/*,
							items: object.id*/
							
							},
							 
							 {
								title : "IN",
								name : "in",
								type : "text",
								width : 150,
								items : IN
							},
									{
										title : "IN Type",
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
		var nameType = $("#nameTypedropdown").val();
		nameType = $.parseJSON(nameType);

		/*var nameType = [ {
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
		} ];*/

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
							autoload : true,
							controller : {

								loadData : function() {
									var d = $.Deferred();
									$
											.ajax({
												type : 'GET',
												url : 'crs/loadOrgNameGrid',
												mimeType : 'application/json',
												contentType : 'application/json',
												success : function(
														data) {
													d
															.resolve(data);
												},
												error : function(
														e) {
													alert("error: "
															+ e.responseText);
												}
											});

									return d.promise();
									/*return
									[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
								},

								insertItem : function(item) {
									var d = $.Deferred();

									$
											.ajax(
													{
														type : "POST",
														url : "crs/insertOrgNameGrid",
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
														// showReportingEntity();
														$(
																"#accountHolderOrganisationNameTypeGrid")
																.jsGrid(
																		"loadData");
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

								updateItem : function(item) {
									var d = $.Deferred();
									$
											.ajax(
													{
														type : "POST",
														url : "crs/updateOrgNameGrid?id="
																+ item.id,
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

								deleteItem : function(item) {
									var d = $.Deferred();
									// alert('@@@@@@@@@@@@@' + item.id)

									$
											.ajax(
													{
														type : "POST",
														url : "crs/deleteOrgNameGrid?id="
																+ item.id,
														data : JSON
																.stringify(item),
														mimeType : 'application/json',
														contentType : 'application/json',
													})
											.done(
													function(
															response) {
														console
																.log("done: "
																		+ JSON
																				.stringify(response));
														d
																.resolve(response);
													})
											.fail(
													function(
															msg) {
														console
																.log("fail"
																		+ msg);
														d
																.reject();
													});
								},

							},
						/*	data : clients,*/
							invalidNotify : function(
									args) {},
							fields : [
									
									{
										
										name : "id",
										title : "id",
										type : "text",
										visible : false,
										width : 10
									/*,
									items: object.id*/
									
									},
									
									{
										title : "Organisation Name<font color='red'>*</font>",
										name : "firstName",
										type : "text",
										width : 150,
										validate : "required"

									},
									{
										title : "Last Name<font color='red'>*</font>",
										name : "lastName",
										type : "text",
										width : 150,
										visible :false
									},
									{
										title : "Organisation Name Type<font color='red'>*</font>",
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
							autoload : true,
							controller : {
								loadData : function() {
									var d = $.Deferred();

									$
											.ajax({
												type : 'GET',
												url : 'crs/loadOrganisationAddress',
												mimeType : 'application/json',
												contentType : "application/json; charset=utf-8",
												success : function(
														data) {
													d
															.resolve(data);
												},
												error : function(
														e) {
													alert("error: "
															+ e.responseText);
												}
											});

									return d.promise();
								}
							},
							/*controller : object,*/
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
																addNewOrgAddressIndividualClicked();
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
															/*	alert("ID: "
																		+ item.id);*/
																orgViewAddress(item.id);
																e
																		.stopPropagation();
																return false;
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
																/*alert("ID: "
																		+ item.id);*/
																organisationalEditAddress(item.id);
																
																e
																		.stopPropagation();
																return false;
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
																/*alert("ID: "
																		+ item.id);*/
																orgDeleteAddress(item.id);
																e
																		.stopPropagation();
																return false;
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
}

function addNewAddressIndividualClicked(){
	$("#addNewIndividualAddress").modal('show');
	$("#addressFreeEntityTextField").hide();
	$("#addressFixEntityContent").hide();
	
	$.ajax({
        url: 'crs/resetIndividualAddAddress',
        type: 'GET',
        async: false,
        data : $('#addindividual').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#addNewIndividualAddress").html();
            $('#addNewIndividualAddress').html('');
            $('#addNewIndividualAddress').html(htmlFiltered);
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });
	
}
function individualViewAddress(id) {
	$.ajax({
		url : 'crs/individualViewAddress?id=' + id,
		type : 'GET',
		async : false,
		cache : false,
		data : $('#viewIndividualAdd').serialize(),
		success : function(data) {
			var htmlFiltered = $("<div>").html(data).find(
					"#individualAddress").html();
			$('#individualAddress').html('');
			$('#individualAddress').html(htmlFiltered);
			$("#individualAddress").modal('show');
			return false;

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function individualEditAddress(id) {
	
	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'crs/individualEditAddress?id=' + id,
			async : false,
			cache : false,
			data : $('#editNewIndividual').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewIndividualAddress").html();
				$('#editNewIndividualAddress').html('');
				$('#editNewIndividualAddress').html(htmlFiltered);
				$("#editNewIndividualAddress").modal('show');
				return false;
			}
		});

	});
}
function individualEditSaveAddress() {

	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'crs/individualEditSaveAddress',
			async : false,
			cache : false,
			data : $('#editNewIndividual').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewIndividualAddress").html();
				$('#editNewIndividualAddress').html('');
				$('#editNewIndividualAddress').html(htmlFiltered);
				/*$("#editNewReportingEntityAddress").modal('close');*/
				$('#editNewIndividualAddress').modal('toggle');
				return false;
			}
		});

	});
}

function individualDeleteAddress(id) {
	$.ajax({
		url : 'crs/individualDeleteAddress?id=' + id,
		type : 'GET',
		async : false,
		success : function(response) {
			return false;

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}
function saveNewIndividualAddressClicked(){
	//alert('saveAddress clicked......');
	$.ajax({
		url : 'crs/individualInsertAddress',
		type : 'GET',
		data: $('#addindividual').serialize(),
		success : function(data) {
		   /*var object =  {"id":"1","addressType":"Address Free","countryCode":"MY"};
			console.log(object.addressType);
			console.log(object.id);
			console.log(object.countryCode);*/
			console.log(data.addressType);
			console.log(data.id);
			console.log(data.countryCode);
			$("#accountHolderAddressGrid").jsGrid("insertItem", data).done(function() {
			    console.log("insertion completed");
			});
			
			
		},error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
	
}
function reportingFiEditSaveAddress() {

	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'crs/reportingFieditSaveAddress',
			async : false,
			cache : false,
			data : $('#editNewReportingfi').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewReportingfiAddress").html();
				$('#editNewReportingfiAddress').html('');
				$('#editNewReportingfiAddress').html(htmlFiltered);
				/*$("#editNewReportingEntityAddress").modal('close');*/
				$('#editNewReportingfiAddress').modal('toggle');
				return false;
			}
		});

	});
}

function saveNewIndividualNameClicked(){
	//alert('saveAddress clicked......');
	$.ajax({
		url : 'crs/individualInsertNameGrid',
		type : 'GET',
		data: $('#viewAccountHolderIndividualName').serialize(),
		success : function(data) {
		   /*var object =  {"id":"1","addressType":"Address Free","countryCode":"MY"};
			console.log(object.addressType);
			console.log(object.id);
			console.log(object.countryCode);*/
			console.log(data.addressType);
			console.log(data.id);
			console.log(data.countryCode);
			$("#accountHolderNameGrid").jsGrid("insertItem", data).done(function() {
			    console.log("insertion completed");
			});
			
			
		},error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
	
}

function saveNewCtrlPersonNameClicked(){
	//alert('saveAddress clicked......');
	$.ajax({
		url : 'crs/ctrlPersonInsertNameGrid',
		type : 'GET',
		data: $('#accountHolderCtrlPersonName').serialize(),
		success : function(data) {
		   /*var object =  {"id":"1","addressType":"Address Free","countryCode":"MY"};
			console.log(object.addressType);
			console.log(object.id);
			console.log(object.countryCode);*/
			console.log(data.addressType);
			console.log(data.id);
			console.log(data.countryCode);
			$("#accountHolderControllingPersonNameGrid").jsGrid("insertItem", data).done(function() {
			    console.log("insertion completed");
			});
			
			
		},error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
	
}



function addNewOrgAddressIndividualClicked(){
	$("#addNewOrgAddress").modal('show');
	$("#addressFreeEntityTextField").hide();
	$("#addressFixEntityContent").hide();
	
	$.ajax({
        url: 'crs/resetOrgAddAddress',
        type: 'GET',
        async: false,
        data : $('#addorganisation').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#addNewOrgAddress").html();
            $('#addNewOrgAddress').html('');
            $('#addNewOrgAddress').html(htmlFiltered);
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });
	
}
function orgViewAddress(id) {
	$.ajax({
		url : 'crs/organisationViewAddress?id=' + id,
		type : 'GET',
		async : false,
		cache : false,
		data : $('#viewOrganisationalAdd').serialize(),
		success : function(data) {
			var htmlFiltered = $("<div>").html(data).find(
					"#orgAddress").html();
			$('#orgAddress').html('');
			$('#orgAddress').html(htmlFiltered);
			$("#orgAddress").modal('show');
			return false;

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}
function organisationalEditAddress(id) {
	
	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'crs/orgEditAddress?id=' + id,
			async : false,
			cache : false,
			data : $('#editNewOrg').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewOrgAddress").html();
				$('#editNewOrgAddress').html('');
				$('#editNewOrgAddress').html(htmlFiltered);
				$("#editNewOrgAddress").modal('show');
				return false;
			}
		});

	});
}
function orgDeleteAddress(id) {
	$.ajax({
		url : 'crs/orgDeleteAddress?id=' + id,
		type : 'GET',
		async : false,
		success : function(response) {
			return false;

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}
function saveNewOrgAddressClicked(){
	//alert('saveAddress clicked......');
	$.ajax({
		url : 'crs/orgInsertAddress',
		type : 'GET',
		data: $('#addorganisation').serialize(),
		success : function(data) {
		   /*var object =  {"id":"1","addressType":"Address Free","countryCode":"MY"};
			console.log(object.addressType);
			console.log(object.id);
			console.log(object.countryCode);*/
			console.log(data.addressType);
			console.log(data.id);
			console.log(data.countryCode);
			$("#accountHolderOrganisationAddressGrid").jsGrid("insertItem", data).done(function() {
			    console.log("insertion completed");
			});
			
			
		},error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
	
}
function orgEditSaveAddress() {

	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'crs/orgEditSaveAddress',
			async : false,
			cache : false,
			data : $('#editNewOrg').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewOrgAddress").html();
				$('#editNewOrgAddress').html('');
				$('#editNewOrgAddress').html(htmlFiltered);
				/*$("#editNewReportingEntityAddress").modal('close');*/
				$('#editNewOrgAddress').modal('toggle');
				return false;
			}
		});

	});
}



function addNewAddressControllingPersonClicked(){
	$("#addNewCtrlPersonAddress").modal('show');
	$("#addressFreeEntityTextField").hide();
	$("#addressFixEntityContent").hide();
	
	$.ajax({
        url: 'crs/resetCtrlPersonAddAddress',
        type: 'GET',
        async: false,
        data : $('#addctrlperson').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#addNewCtrlPersonAddress").html();
            $('#addNewCtrlPersonAddress').html('');
            $('#addNewCtrlPersonAddress').html(htmlFiltered);
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });
	
}
function controllingPersonViewAddress(id) {
	$.ajax({
		url : 'crs/ctrlPersonViewAddress?id=' + id,
		type : 'GET',
		async : false,
		cache : false,
		data : $('#viewCtrlPersonAdd').serialize(),
		success : function(data) {
			var htmlFiltered = $("<div>").html(data).find(
					"#ctrlPersonAddress").html();
			$('#ctrlPersonAddress').html('');
			$('#ctrlPersonAddress').html(htmlFiltered);
			$("#ctrlPersonAddress").modal('show');
			return false;

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function controllingPersonEditAddress(id) {
	
	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'crs/ctrlPersonEditAddress?id=' + id,
			async : false,
			cache : false,
			data : $('#editNewCtrlPerson').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewCtrlPersonAddress").html();
				$('#editNewCtrlPersonAddress').html('');
				$('#editNewCtrlPersonAddress').html(htmlFiltered);
				$("#editNewCtrlPersonAddress").modal('show');
				return false;
			}
		});

	});
}
function individualEditSaveAddress() {

	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'crs/individualEditSaveAddress',
			async : false,
			cache : false,
			data : $('#editNewIndividual').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewIndividualAddress").html();
				$('#editNewIndividualAddress').html('');
				$('#editNewIndividualAddress').html(htmlFiltered);
				/*$("#editNewReportingEntityAddress").modal('close');*/
				$('#editNewIndividualAddress').modal('toggle');
				return false;
			}
		});

	});
}

function controllingPersonDeleteAddress(id) {
	$.ajax({
		url : 'crs/ctrlPersonDeleteAddress?id=' + id,
		type : 'GET',
		async : false,
		success : function(response) {
			return false;

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}
function saveNewCtrlPersonAddressClicked(){
	//alert('saveAddress clicked......');
	$.ajax({
		url : 'crs/ctrlPersonInsertAddress',
		type : 'GET',
		data: $('#addctrlperson').serialize(),
		success : function(data) {
		   /*var object =  {"id":"1","addressType":"Address Free","countryCode":"MY"};
			console.log(object.addressType);
			console.log(object.id);
			console.log(object.countryCode);*/
			console.log(data.addressType);
			console.log(data.id);
			console.log(data.countryCode);
			$("#accountHolderControllingPersonAddressGrid").jsGrid("insertItem", data).done(function() {
			    console.log("insertion completed");
			});
			
			
		},error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
	
}
function controllingPersonEditSaveAddress() {

	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'crs/ctrlPersoneditSaveAddress',
			async : false,
			cache : false,
			data : $('#editNewCtrlPerson').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewCtrlPersonAddress").html();
				$('#editNewCtrlPersonAddress').html('');
				$('#editNewCtrlPersonAddress').html(htmlFiltered);
				/*$("#editNewReportingEntityAddress").modal('close');*/
				$('#editNewCtrlPersonAddress').modal('toggle');
				return false;
			}
		});

	});
}

function validateAccountholder(){
	var errorFlag = false;
	var sendingCountryId = $('#documentTypeIndicator').val();
	if (sendingCountryId == '0') {
		$("#documentTypeIndicatorError").empty().append("Document Type Indicator not empty!");
		errorFlag = true;
	} else {
		$("#documentTypeIndicatorError").empty();
	}
	if ($("#documentReferenceId").val() == '') {
		$("#documentReferenceIdError").empty()
				.append("Document Reference Id not empty!");
		errorFlag = true;
	} else {
		$("#documentReferenceIdError").empty();
	}
	if ($("#accountNumber").val() == '') {
		$("#accountNumberError").empty()
				.append("Account Number not empty!");
		errorFlag = true;
	} else {
		$("#accountNumberError").empty();
	}
	var accountNumberType = $('#multi-select-account-type').val();
	if (accountNumberType == null) {
		$("#multi-select-account-typeError").empty().append("Account Number Type not empty!");
		errorFlag = true;
	} else {
		$("#multi-select-account-typeError").empty();
	}
	var currency = $('#currencyV').val();
	if (currency == '0') {
		$("#currencyError").empty().append("Currency not empty!");
		errorFlag = true;
	} else {
		$("#currencyError").empty();
	}
	if ($("#accoutBalance").val() == '') {
		$("#accoutBalanceError").empty()
				.append("Account Balance not empty!");
		errorFlag = true;
	} else {
		$("#accoutBalanceError").empty();
	}
	var items = $("#accountHolderControlingPersonPaymentGrid").jsGrid("option", "data");
	var arrayLength = items.length;
	if(arrayLength == 0){
		$("#paymentGridError").empty().append("Payment Details not empty!");
		errorFlag = true;
	}else{
		$("#paymentGridError").empty();
	}
	/*var radio1 = $("#defaultUnchecked1").val();
	alert('@@@@@@@@@@'+radio1)
	var radio2 = $("#defaultUnchecked2").val();
	alert('@@@@@@@@@@'+radio2)*/
	
	var individual = document.getElementById("defaultUnchecked1").checked;
	var organisation= document.getElementById("defaultUnchecked2").checked;
	if(individual){
		var reident = $("#accountHolderResidentCountryGrid").jsGrid("option", "data");
		var reidentarrayLength = reident.length;
		if(reidentarrayLength == 0){
			$("#indresidentGridError").empty().append("Resident Country Code not empty!");
			errorFlag = true;
		}else{
			$("#indresidentGridError").empty();
		}
		
		var nameGrid = $("#accountHolderNameGrid").jsGrid("option", "data");
		var nameGridarrayLength = nameGrid.length;
		if(nameGridarrayLength == 0){
			$("#accountHolderNameGridError").empty().append("Name and NameType not empty!");
			errorFlag = true;
		}else{
			$("#accountHolderNameGridError").empty();
		}
		var addressGrid = $("#accountHolderAddressGrid").jsGrid("option", "data");
		var addressGridarrayLength = addressGrid.length;
		if(addressGridarrayLength == 0){
			$("#accountHolderaddressGridError").empty().append("Address not empty!");
			errorFlag = true;
		}else{
			$("#accountHolderaddressGridError").empty();
		}
		
		
		
		
		
		
		
	}
	if(organisation){
		
		var nameGrid = $("#accountHolderOrganisationNameTypeGrid").jsGrid("option", "data");
		var nameGridarrayLength = nameGrid.length;
		if(nameGridarrayLength == 0){
			$("#accountHolderOrganisationNameTypeGridError").empty().append("Name and NameType not empty!");
			errorFlag = true;
		}else{
			$("#accountHolderOrganisationNameTypeGridError").empty();
		}
		var addressGrid = $("#accountHolderOrganisationAddressGrid").jsGrid("option", "data");
		var addressGridarrayLength = addressGrid.length;
		if(addressGridarrayLength == 0){
			$("#accountHolderOrganisationAddressGridError").empty().append("Address not empty!");
			errorFlag = true;
		}else{
			$("#accountHolderOrganisationAddressGridError").empty();
		}
		var accountHolderType = $('#accountHoldertype1').val();
		if (accountHolderType == '0') {
			$("#accountHoldertypeError").empty().append("Account Holder Type not empty!");
			errorFlag = true;
		} else {
			$("#accountHoldertypeError").empty();
		}
		
	}
	

	return errorFlag;
	
	
}


function saveAccountHolderMain(){

	$("#accountHolderControlingPersonResidentCountryGridError").empty();
	$("#accountHolderControllingPersonNameGridError").empty();
	$("#accountHolderControllingPersonAddressGridError").empty();
	
	var errorFlag = validateAccountholder();
	if(!errorFlag){
	 var form_data = $('#crsaccountholder').serialize();
		$.ajax({
			url : 'crs/insertAcountHolderMain',
			type : 'GET',
			data : form_data,
			success : function(data) {
				console.log(data);
				$("#accountHolderGrid").jsGrid("insertItem", data).done(function() {
					console.log("insertion completed");
					/*showCbcReports(1,0,0);*/
					ReportingFiNext(1,0,0);
				});
			},error : function(request, error) {
				alert("Request: " + JSON.stringify(request));
			}
		});	
	}
}

function viewAccountHolderMain(item){
	
	$
   .ajax({

       url: 'crs/viewAcountHolderMain?viewId='+item.id,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           viewAccountHolder(0,0,1);
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}

function doneViewAccounts(newForm,editForm,viewForm){
	$
   .ajax({

       url: 'crs/viewAccountDone',
       type: 'GET',
       async: false,
       success: function(data) {
    	   viewAccountHolder(1,0,0);       	
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });
}
function doneEditAccounts(){
	
	var errorFlag = validateAccountholder();
	if(!errorFlag){
	 var form_data = $('#crsaccountholder').serialize();
		$.ajax({
			url : 'crs/saveEditedData',
			type : 'GET',
			data : form_data,
			success : function(data) {
				console.log(data);
				/*$("#cbcReportsGrid").jsGrid("insertItem", data).done(function() {
					console.log("insertion completed");*/
				viewAccountHolder(1,0,0);
			},error : function(request, error) {
				alert("Request: " + JSON.stringify(request));
			}
		});	
	}
} 

function editAccountHolderMain(item){
	
	$
   .ajax({

       url: 'crs/editAcountHolderMain?editId='+item.id,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           viewAccountHolder(0,1,0);
           return false;
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}
function deleteNewAccountHolderClicked(item){
	$("#deleteConfirmation").modal('show');
	$("#idToDelete").html(item.id);
	$("#formId").html(7);
}

function proceedAccDelete(){
	var idToDelete = $("#idToDelete").text();
	var formToDelete = $("#formId").text();
	/*alert("idToDelete =====>"+formToDelete);*/
	
	if(formToDelete == 7){
	$
   .ajax({

       url: 'crs/populateDeletedAccountHolder?deleteId='+idToDelete,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           ReportingFiNext(1,0,0);
           return false;
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });
	}
}
function validateControllingPerson(){
	
	/*var errorFlag = false;
	var sendingCountryId = $('#documentTypeIndicator').val();
	if (sendingCountryId == '0') {
		$("#documentTypeIndicatorError").empty().append("Document Type Indicator not empty!");
		errorFlag = true;
	} else {
		$("#documentTypeIndicatorError").empty();
	}
	if ($("#documentReferenceId").val() == '') {
		$("#documentReferenceIdError").empty()
				.append("Document Reference Id not empty!");
		errorFlag = true;
	} else {
		$("#documentReferenceIdError").empty();
	}*/
	var errorFlag = false;
	var resident = $("#accountHolderControlingPersonResidentCountryGrid").jsGrid("option", "data");
	var residentarrayLength = resident.length;
	if(residentarrayLength == 0){
		$("#accountHolderControlingPersonResidentCountryGridError").empty().append("Resident Country not empty!");
		errorFlag = true;
	}else{
		$("#accountHolderControlingPersonResidentCountryGridError").empty();
	}
	var name = $("#accountHolderControllingPersonNameGrid").jsGrid("option", "data");
	var namearrayLength = name.length;
	if(namearrayLength == 0){
		$("#accountHolderControllingPersonNameGridError").empty().append("Name and NameType not empty!");
		errorFlag = true;
	}else{
		$("#accountHolderControllingPersonNameGridError").empty();
	}
	
	var name = $("#accountHolderControllingPersonAddressGrid").jsGrid("option", "data");
	var namearrayLength = name.length;
	if(namearrayLength == 0){
		$("#accountHolderControllingPersonAddressGridError").empty().append("Address not empty!");
		errorFlag = true;
	}else{
		$("#accountHolderControllingPersonAddressGridError").empty();
	}
	
	return errorFlag;
	
}
function saveCtrlPersonMain(){
	$("#documentTypeIndicatorError").empty();
	$("#documentReferenceIdError").empty();
	$("#accountNumberError").empty();
	$("#multi-select-account-typeError").empty();
	$("#currencyError").empty();
	$("#accoutBalanceError").empty();
	$("#paymentGridError").empty();
	$("#indresidentGridError").empty();
	$("#accountHolderNameGridError").empty();
	$("#accountHolderaddressGridError").empty();
	$("#accountHolderOrganisationNameTypeGridError").empty();
	$("#accountHolderOrganisationAddressGridError").empty();
	$("#accountHoldertypeError").empty();
		
	var	errorFlag = validateControllingPerson();
		if(!errorFlag){
		 var form_data = $('#crsaccountholder').serialize();
			$.ajax({
				url : 'crs/insertCtrlPersonMain',
				type : 'GET',
				data : form_data,
				success : function(data) {
					console.log(data);
					$("#accountHolderControllingPersonGrid").jsGrid("insertItem", data).done(function() {
						console.log("insertion completed");
						ReportingFiNext(0,0,0);
					});
				},error : function(request, error) {
					alert("Request: " + JSON.stringify(request));
				}
			});	
		}
	
	}

function controllingPersonEditSaveName() {

	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'crs/editSavectrlPersonInsertNameGrid',
			async : false,
			cache : false,
			data : $('#editaccountHolderCtrlPersonName').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editAccountHolderCtrlNameName").html();
				$('#editAccountHolderCtrlNameName').html('');
				$('#editAccountHolderCtrlNameName').html(htmlFiltered);
				/*$("#editNewReportingEntityAddress").modal('close');*/
				$('#editAccountHolderCtrlNameName').modal('toggle');
				return false;
			}
		});

	});
}

function saveEditedNewIndividualNameClicked() {

	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'crs/editSaveIndividualNameGrid',
			async : false,
			cache : false,
			data : $('#editAccountHolderIndividualName').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editAccountHolderIndividualName").html();
				$('#editAccountHolderIndividualName').html('');
				$('#editAccountHolderIndividualName').html(htmlFiltered);
				/*$("#editNewReportingEntityAddress").modal('close');*/
				$('#editAccountHolderIndividualName').modal('toggle');
				return false;
			}
		});

	});
}

function deleteNewIndividualNameClicked(id){
	/*var idToDelete = $("#idToDelete").text();
	var formToDelete = $("#formId").text();
	alert("idToDelete =====>"+formToDelete);
	
	if(formToDelete == 7){*/
	$
   .ajax({

       url: 'crs/deleteIndividualNameGrid?id='+id,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           /*ReportingFiNext(1,0,0);*/
           return false;
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });
/*	}*/
}

function deleteNewCtrlNameClicked(id){
	/*var idToDelete = $("#idToDelete").text();
	var formToDelete = $("#formId").text();
	alert("idToDelete =====>"+formToDelete);
	
	if(formToDelete == 7){*/
	$
   .ajax({

       url: 'crs/deleteCtrlNameGrid?id='+id,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           /*ReportingFiNext(1,0,0);*/
           return false;
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });
/*	}*/
}

function viewControllingPersonMain(id){
	
	$
   .ajax({

       url: 'crs/viewControllingPersonMain?viewId='+id,
       type: 'GET',
       async: false,
       success: function(data) {
    	   var htmlFiltered = $("<div>").html(data).find("#controllingPerson").html();
       		console.log(htmlFiltered);
           $('#controllingPerson').html('');
           $('#controllingPerson').html(htmlFiltered);	            
           $('#controllingPerson').find('input, textarea,select').attr('disabled','disabled');
           $('#saveControllingPersonButton').hide();
           $('#viewControllingDone').show();
           $('#editviewControllingDoneDone').hide();
           $('#editCancelviewControllingDoneDone').hide();
          /* $('#editCancelviewControllingDoneDone').prop('disabled',false);*/
          /* showAllViewReportsGrid();
           debugger;*/
           showAllControllingPersonGrid();
           return false;	  
          /* ReportingFiNext(0,0,1);*/
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}

function editControllingPersonMain(id){
	
	$
   .ajax({

       url: 'crs/editControllingPersonMain?editId='+id,
       type: 'GET',
       async: false,
       success: function(data) {
    	   var htmlFiltered = $("<div>").html(data).find("#controllingPerson").html();
       		console.log(htmlFiltered);
           $('#controllingPerson').html('');
           $('#controllingPerson').html(htmlFiltered);	            
          /* $('#controllingPerson').find('input, textarea,select').attr('disabled','disabled');*/
           $('#saveControllingPersonButton').hide();
           $('#viewControllingDone').hide();
           $('#editviewControllingDoneDone').show();
           $('#editCancelviewControllingDoneDone').show();
          /* $('#editCancelviewControllingDoneDone').prop('disabled',false);*/
          /* showAllViewReportsGrid();
           debugger;*/
           showAllControllingPersonGrid();
           return false;	  
          /* ReportingFiNext(0,0,1);*/
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });
}

function saveAllCRSData(){
	/*alert('@@@@@@@@@@@@@@');*/
	var errorFlag = false;
	var accountHolderMain = $("#accountHolderGrid").jsGrid("option", "data");
	var accountHolderMainarrayLength = accountHolderMain.length;
	if(accountHolderMainarrayLength == 0){
		$("#accountHolderGridError").empty().append("Account Holder Details not empty!");
		errorFlag = true;
	}else{
		$("#accountHolderGridError").empty();
	}
	if(!errorFlag){
	$
   .ajax({

       url: 'crs/save',
       type: 'POST',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           return false;
         /*  $("#saveCBCDataButton").hide();
           $("#generateCBCMetadata").show();*/
           //showCbcAddInfo(0,0,1);
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });
	}

}

function showAllControllingPersonGrid(){
	
	var residentCountryCode = $("#residentcountry").val();
	residentCountryCode = $.parseJSON(residentCountryCode);
	
	$("#accountHolderControlingPersonResidentCountryGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				autoload : true,
				controller : {

					loadData : function() {
						var d = $.Deferred();
						$
								.ajax({
									type : 'GET',
									url : 'crs/loadctrlResidentCountryGrid',
									mimeType : 'application/json',
									contentType : 'application/json',
									success : function(
											data) {
										d
												.resolve(data);
									},
									error : function(
											e) {
										alert("error: "
												+ e.responseText);
									}
								});

						return d.promise();
						/*return
						[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
					},

					insertItem : function(item) {
						var d = $.Deferred();

						$
								.ajax(
										{
											type : "POST",
											url : "crs/insertctrlResidentCountryGrid",
											data : JSON
													.stringify(item),
											mimeType : 'application/json',
											contentType : 'application/json',
										})
								.done(
										function(
												response) {
											console
													.log("done: "
															+ JSON
																	.stringify(response));
											d
													.resolve(response);
											// showReportingEntity();
											$(
													"#accountHolderControlingPersonResidentCountryGrid")
													.jsGrid(
															"loadData");
										})
								.fail(
										function(
												msg) {
											console
													.log("fail"
															+ msg);
											d
													.reject();
										});
					},

					updateItem : function(item) {
						var d = $.Deferred();
						$
								.ajax(
										{
											type : "POST",
											url : "crs/updatectrlResidentCountryGrid?id="
													+ item.id,
											data : JSON
													.stringify(item),
											mimeType : 'application/json',
											contentType : 'application/json',
										})
								.done(
										function(
												response) {
											console
													.log("done: "
															+ JSON
																	.stringify(response));
											d
													.resolve(response);
										})
								.fail(
										function(
												msg) {
											console
													.log("fail"
															+ msg);
											d
													.reject();
										});
					},

					deleteItem : function(item) {
						var d = $.Deferred();
						// alert('@@@@@@@@@@@@@' + item.id)

						$
								.ajax(
										{
											type : "POST",
											url : "crs/deletectrlResidentCountryGrid?id="
													+ item.id,
											data : JSON
													.stringify(item),
											mimeType : 'application/json',
											contentType : 'application/json',
										})
								.done(
										function(
												response) {
											console
													.log("done: "
															+ JSON
																	.stringify(response));
											d
													.resolve(response);
										})
								.fail(
										function(
												msg) {
											console
													.log("fail"
															+ msg);
											d
													.reject();
										});
					},

				},
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [
				{
					title : "No.<font color='red'>*</font>",
					name : "id",
					type : "text",
					width : 150,
					validate : "required",
					visible : false
				},
				  {
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
	
	var issuedBy =residentCountryCode;

	var tin = [];
    var type = [];
	$("#accountHolderControllingPersonTNGrid").jsGrid(
			{
				width : "205%",
				inserting : true,
				editing : true,
				sorting : true,
				paging : true,
				pageSize : 6,
				pageButtonCount : 5,
				autoload : true,
				controller : {

					loadData : function() {
						var d = $.Deferred();
						$
								.ajax({
									type : 'GET',
									url : 'crs/loadctrlOrganisationGrid',
									mimeType : 'application/json',
									contentType : 'application/json',
									success : function(
											data) {
										d
												.resolve(data);
									},
									error : function(
											e) {
										alert("error: "
												+ e.responseText);
									}
								});

						return d.promise();
						/*return
						[{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
					},

					insertItem : function(item) {
						var d = $.Deferred();

						$
								.ajax(
										{
											type : "POST",
											url : "crs/insertctrlOrganisationGrid",
											data : JSON
													.stringify(item),
											mimeType : 'application/json',
											contentType : 'application/json',
										})
								.done(
										function(
												response) {
											console
													.log("done: "
															+ JSON
																	.stringify(response));
											d
													.resolve(response);
											// showReportingEntity();
											$(
													"#accountHolderControllingPersonTNGrid")
													.jsGrid(
															"loadData");
										})
								.fail(
										function(
												msg) {
											console
													.log("fail"
															+ msg);
											d
													.reject();
										});
					},

					updateItem : function(item) {
						var d = $.Deferred();
						$
								.ajax(
										{
											type : "POST",
											url : "crs/updatectrlOrganisationGrid?id="
													+ item.id,
											data : JSON
													.stringify(item),
											mimeType : 'application/json',
											contentType : 'application/json',
										})
								.done(
										function(
												response) {
											console
													.log("done: "
															+ JSON
																	.stringify(response));
											d
													.resolve(response);
										})
								.fail(
										function(
												msg) {
											console
													.log("fail"
															+ msg);
											d
													.reject();
										});
					},

					deleteItem : function(item) {
						var d = $.Deferred();
						// alert('@@@@@@@@@@@@@' + item.id)

						$
								.ajax(
										{
											type : "POST",
											url : "crs/deletectrlOrganisationGrid?id="
													+ item.id,
											data : JSON
													.stringify(item),
											mimeType : 'application/json',
											contentType : 'application/json',
										})
								.done(
										function(
												response) {
											console
													.log("done: "
															+ JSON
																	.stringify(response));
											d
													.resolve(response);
										})
								.fail(
										function(
												msg) {
											console
													.log("fail"
															+ msg);
											d
													.reject();
										});
					},

				},
				
				invalidNotify : function(args) {
					$("#validateTextHere").text("");
					$("#validateTextHere").text(
							"Please fill in all the mandatory fields");
					$('#crsNameModal').modal('show');
				},
				fields : [ 
				{
					title : "No.<font color='red'>*</font>",
					name : "id",
					type : "text",
					width : 150,
					validate : "required",
					visible : false
				},
				{
					title : "TIN",
					name : "in",
					type : "text",
					width : 150,
					items : tin,
				}, {
					title : "TIN Type",
					name : "inType",
					type : "text",
					width : 150,
					items : type,
				},{
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
						autoload : true,
						controller : {
							loadData : function() {
								var d = $.Deferred();

								$
										.ajax({
											type : 'GET',
											url : 'crs/loadctrlNameTypeMainGrid',
											mimeType : 'application/json',
											contentType : "application/json; charset=utf-8",
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
							}
						},
						/*controller : object,*/
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
															addAccountHolderCtrlPersonNameClicked();
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
													/*alert("ID: " + item.id);*/
													e.stopPropagation();
													viewAccountHolderCtrlPersonNameClicked(item.id);
													return false;
												});

										var $customEditButton = $("<button>")
												.attr("class",
														"btn btn-warning btn-sm")
												.text("Edit")
												.click(function(e) {
													/*alert("ID: " + item.id);*/
													e.stopPropagation();
													editAccountHolderCtrlPersonNameClicked(item.id);
													return false;
												});

										var $customDeleteButton = $("<button>")
												.attr("class",
														"btn btn-danger btn-sm")
												.text("Delete")
												.click(function(e) {
													/*alert("ID: " + item.id);*/
													e.stopPropagation();
													deleteNewCtrlNameClicked(item.id);
													$(
													"#accountHolderControllingPersonNameGrid")
													.jsGrid(
															"loadData");
													return false;
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
						autoload : true,
						controller : {
							loadData : function() {
								var d = $.Deferred();

								$
										.ajax({
											type : 'GET',
											url : 'crs/loadCtrlPersonAddress',
											mimeType : 'application/json',
											contentType : "application/json; charset=utf-8",
											success : function(
													data) {
												d
														.resolve(data);
											},
											error : function(
													e) {
												alert("error: "
														+ e.responseText);
											}
										});

								return d.promise();
							}
						},
						/*controller : object,*/
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
									/*items : object.id*/
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
															addNewAddressControllingPersonClicked();
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
													/*alert("ID: " + item.id);*/
													controllingPersonViewAddress(item.id);
													e.stopPropagation();
													return false;
												});

										var $customEditButton = $("<button>")
												.attr("class",
														"btn btn-warning btn-sm")
												.text("Edit")
												.click(function(e) {
													/*alert("ID: " + item.id);*/
													controllingPersonEditAddress(item.id);
													e.stopPropagation();
													return false;
												});

										var $customDeleteButton = $("<button>")
												.attr("class",
														"btn btn-danger btn-sm")
												.text("Delete")
												.click(function(e) {
													/*alert("ID: " + item.id);*/
													controllingPersonDeleteAddress(item.id);
													$(
													"#accountHolderControllingPersonAddressGrid")
													.jsGrid(
															"loadData");
													e.stopPropagation();
													return false;
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
				autoload: true,
				controller: {

                    loadData: function(filter) {
                    	
//                    	return $.ajax({url:  "/admin/cbc/loadResidentCountryGrid",data:filter
//                        });
                    	
                    	
                        var d = $.Deferred();
                        $
                            .ajax({
                                type: 'GET',
                                url: 'crs/loadCtrlPersonMain',
                                mimeType: 'application/json',
                                contentType: 'application/json',
                                success: function(
                                    data) {
                                	d.resolve(data);
                                },
                                error: function(e) {
                                    alert("error: " +
                                        e.responseText);
                                }
                            });

                        return d.promise();
                    }
                },
				/*controller : object,*/
				datatype : 'json',
				invalidNotify : function(args) {
					
				},
				fields : [ {
					name : "id",
					title : "id",
					type : "text",
					visible : false,
					width : 10,
					items : object.id
				}, {
					title : "Controlling Person Type",
					name : "controllingPersonType",
					type : "text",
					width : 150,
					items : object.controllingPersonType,
					visible : true
				},{

					name : "",
					/*headerTemplate : function() {
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
					},*/
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
											/*alert("ID: "
													+ item.id);*/
											e
													.stopPropagation();
											viewControllingPersonMain(item.id);
											return false;
										});

						var $customEditButton = $(
								"<button>")
								.attr("class",
										"btn btn-warning btn-sm")
								.text("Edit")
								.click(
										function(
												e) {
										/*	alert("ID: "
													+ item.id);*/
											e
													.stopPropagation();
											editControllingPersonMain(item.id);
											return false;
										});

						var $customDeleteButton = $(
								"<button>")
								.attr("class",
										"btn btn-danger btn-sm")
								.text("Delete")
								.click(
										function(
												e) {
											/*alert("ID: "
													+ item.id);*/
											e
													.stopPropagation();
											deleteControllingPersonMain(item.id);
											$(
											"#accountHolderControllingPersonGrid")
											.jsGrid(
													"loadData");
											return false;
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
	
}

function doneViewControllingPerson(){
	$
	   .ajax({

	       url: 'crs/viewControllingPersonDone',
	       type: 'GET',
	       async: false,
	       success: function(data) {	        	
	        	var htmlFiltered = $("<div>").html(data).find("#controllingPerson").html();
	        	console.log(htmlFiltered);
	            $('#controllingPerson').html('');
	            $('#controllingPerson').html(htmlFiltered);	            
	            $('#saveControllingPersonButton').show();
	            $('#viewControllingDone').hide();
	            $('#editviewControllingDoneDone').hide();
	            $('#editCancelviewControllingDoneDone').hide();
	            
	            showAllControllingPersonGrid();
	            debugger;
	            return false;	              	
	       },
	       error: function(
	           request,
	           error) {
	           console.log(error);
	       }
	   });	
}

function doneEditSaveControllingPerson(){
	var	errorFlag = validateControllingPerson();
	if(!errorFlag){
	$
	   .ajax({

	       url: 'crs/editsaveControllingPersonDone',
	       type: 'GET',
	       async: false,
	       success: function(data) {	        	
	        	var htmlFiltered = $("<div>").html(data).find("#controllingPerson").html();
	        	console.log(htmlFiltered);
	            $('#controllingPerson').html('');
	            $('#controllingPerson').html(htmlFiltered);	            
	            $('#saveControllingPersonButton').show();
	            $('#viewControllingDone').hide();
	            $('#editviewControllingDoneDone').hide();
	            $('#editCancelviewControllingDoneDone').hide();
	            
	            showAllControllingPersonGrid();
	            debugger;
	            return false;	              	
	       },
	       error: function(
	           request,
	           error) {
	           console.log(error);
	       }
	   });	
	}
}

function deleteControllingPersonMain(item){
	
	$
	   .ajax({

	       url: 'cbc/deleteControllingPersonMain?viewId='+item,
	       type: 'GET',
	       async: false,
	       success: function(data) {	        	
	        	var htmlFiltered = $("<div>").html(data).find("#controllingPerson").html();
	        	console.log(htmlFiltered);
	            $('#controllingPerson').html('');
	            $('#controllingPerson').html(htmlFiltered);	            
	            $('#saveControllingPersonButton').show();
	            $('#viewControllingDone').hide();
	            $('#editviewControllingDoneDone').hide();
	            $('#editCancelviewControllingDoneDone').hide();
	            showAllControllingPersonGrid();
	            debugger;
	            return false;	              	
	       },
	       error: function(
	           request,
	           error) {
	           console.log(error);
	       }
	   });
}

function deleteControllingPersonMain(item){
	alert('@@@@@@@@@@@@@@@@@')
	$
	   .ajax({

	       url: 'crs/deleteControllingPersonMain?viewId='+item,
	       type: 'GET',
	       async: false,
	       success: function(data) {	        	
	        	var htmlFiltered = $("<div>").html(data).find("#controllingPerson").html();
	        	console.log(htmlFiltered);
	            $('#controllingPerson').html('');
	            $('#controllingPerson').html(htmlFiltered);	            
	            $('#saveControllingPersonButton').show();
	            $('#viewControllingDone').hide();
	            $('#editviewControllingDoneDone').hide();
	            $('#editCancelviewControllingDoneDone').hide();
	            showAllControllingPersonGrid();
	            debugger;
	            return false;	              	
	       },
	       error: function(
	           request,
	           error) {
	           console.log(error);
	       }
	   });
}












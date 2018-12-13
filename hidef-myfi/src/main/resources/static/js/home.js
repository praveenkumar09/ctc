$(document).ready(function() {
	$("#crsbreadcrumb").hide();
	$("#cbcbreadcrumb").hide();
	$('.dropdown').on('show.bs.dropdown', function(e) {
		$(this).find('.dropdown-menu').first().stop(true, true).slideDown(300);
	});

	$('.dropdown').on('hide.bs.dropdown', function(e) {
		$(this).find('.dropdown-menu').first().stop(true, true).slideUp(200);
	});

	var pageValue = $("#toProfilePage").val();
	if (pageValue == "true") {
		userProfileOnClick();
	}else if (pageValue == "cbctrue"){
		crsUserProfileOnClick();
	}

});

function bs_input_file() {
	$(".input-file")
			.before(
					function() {
						if (!$(this).prev().hasClass('input-ghost')) {
							var element = $("<input type='file' class='input-ghost' style='visibility:hidden; height:0'>");
							element.attr("name", $(this).attr("name"));
							element.change(function() {
								element.next(element).find('input').val(
										(element.val()).split('\\').pop());
							});
							$(this).find("button.btn-choose").click(function() {
								element.click();
							});
							$(this).find("button.btn-reset").click(
									function() {
										element.val(null);
										$(this).parents(".input-file").find(
												'input').val('');
									});
							$(this).find('input').css("cursor", "pointer");
							$(this).find('input').mousedown(function() {
								$(this).parents('.input-file').prev().click();
								return false;
							});
							return element;
						}
					});
}

function newCRSOnClick() {
	$("#crsbreadcrumb").show();
	$("#cbcbreadcrumb").hide();
	$("#userProfile").hide();
	$("#summary").hide();

	metaDataOnClick();
}

function newCBCOnClick() {
	$("#cbcbreadcrumb").show();
	$("#crsbreadcrumb").hide();
	$("#userProfile").hide();
	$("#summary").hide();
	cbcMetaDataOnClick();
}

function viewCBCOnClick(){
	$("#cbcbreadcrumb").show();
	$("#crsbreadcrumb").hide();
	$("#userProfile").hide();
	$("#summary").hide();
	viewCBCMetaDataOnClick();

}

function newNewCBCOnClick() {
	$("#cbcbreadcrumb").show();
	$("#crsbreadcrumb").hide();
	$("#userProfile").hide();
	$("#summary").hide();
	newCbcMetaDataOnClick();
}

function newCbcMetaDataOnClick() {
	$("#userProfile").hide();
	$("#reportingEntity").removeClass("active");
	$("#cbcMetaDataBtn").addClass("active");
	$("#cbcReports").removeClass("active");
	$("#cbcAddInfo").removeClass("active");
	$.ajax({

		url : 'cbc/new/metadata',
		type : 'GET',
		success : function(data) {
			$("#metaData").show();
			$("#metaData").html(data);

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function cbcOnClick() {
	$('#cbc').addClass('active');
	$('#crs').removeClass('active');
	$('#fatca').removeClass('active');
	$("#crsbreadcrumb").hide();
	$("#cbcbreadcrumb").hide();
}

function fatcaOnClick() {
	$('#fatca').addClass('active');
	$('#cbc').removeClass('active');
	$('#crs').removeClass('active');
	$("#crsbreadcrumb").hide();
	$("#cbcbreadcrumb").hide();

}

function userProfileOnClick() {
	$("#crsbreadcrumb").hide();
	$("#cbcbreadcrumb").hide();
	$("#metaData").hide();
	$
			.ajax({

				url : 'profile',
				type : 'GET',
				success : function(data) {
					$("#userProfile").show();
					$("#userProfile").html(data);

					var recievingCountry = $("#recievingCountry").val();
					recievingCountry = $.parseJSON(recievingCountry);

					$("#userProfileReceivingCountryList")
							.jsGrid(
									{
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
															url : 'loadReceivingCountryList',
															mimeType : 'application/json',
															contentType : 'application/json',
															success : function(
																	data) {
																d.resolve(data);
															},
															error : function(e) {
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
																	url : "insertReceivingCountryGrid",
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
																function(msg) {
																	console
																			.log("fail"
																					+ msg);
																	d.reject();
																});
											},

											updateItem : function(item) {
												var d = $.Deferred();
												$
														.ajax(
																{
																	type : "POST",
																	url : "updateReceivingCountryGrid?id="
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
																function(msg) {
																	console
																			.log("fail"
																					+ msg);
																	d.reject();
																});
											},

											deleteItem : function(item) {
												var d = $.Deferred();
												//alert('@@@@@@@@@@@@@' + item.id)

												$
														.ajax(
																{
																	type : "POST",
																	url : "cbc/deleteReceivingCountryGrid?id="
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
																function(msg) {
																	console
																			.log("fail"
																					+ msg);
																	d.reject();
																});
											},

										},
										invalidNotify : function(args) {/*
										                        $("#validateTextHere").text("");
										                        $("#validateTextHere")
										                            .text(
										                                "Please fill in all the mandatory fields");
										                        $('#crsNameModal').modal('show');
										 */
										},
										fields : [ {
											title : "No",
											name : "id",
											type : "text",
											width : 150,
											validate : "required",
											visible : false
										}, {
											title : "Receiving Country",
											name : "recievingCountry",
											type : "select",
											items : recievingCountry,
											valueField : "id",
											textField : "name",
										}, {
											type : "control"
										} ]
									});

				},
				error : function(request, error) {
					alert("Request: " + JSON.stringify(request));
				}
			});
}

function crsUserProfileOnClick() {
	$("#crsbreadcrumb").hide();
	$("#cbcbreadcrumb").hide();
	$("#metaData").hide();
	$
			.ajax({

				url : 'crs/crsuserprofile',
				type : 'GET',
				success : function(data) {
					$("#userProfile").show();
					$("#userProfile").html(data);

					/*var recievingCountry = $("#recievingCountry").val();
					recievingCountry = $.parseJSON(recievingCountry);*/

					/*$("#userProfileReceivingCountryList")
							.jsGrid(
									{});*/

				},
				error : function(request, error) {
					alert("Request: " + JSON.stringify(request));
				}
			});
}

function viewCBCMetaDataOnClick(){
	$("#userProfile").hide();
	$("#reportingEntity").removeClass("active");
	$("#cbcMetaDataBtn").addClass("active");
	$("#cbcReports").removeClass("active");
	$("#cbcAddInfo").removeClass("active");
	$.ajax({

		url : 'cbc/main',
		type : 'GET',
		success : function(data) {
			$("#metaData").show();
			$("#metaData").html(data);
			$("#cbcmetadata *").prop('disabled',true);
			$("#metaDataButton").prop('disabled', false);

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function cbcMetaDataOnClick() {
	$("#userProfile").hide();
	$("#reportingEntity").removeClass("active");
	$("#cbcMetaDataBtn").addClass("active");
	$("#cbcReports").removeClass("active");
	$("#cbcAddInfo").removeClass("active");
	$.ajax({

		url : 'cbc/main',
		type : 'GET',
		success : function(data) {
			$("#metaData").show();
			$("#metaData").html(data);

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function cbcReportingEntityPrevious(newForm, editForm, viewForm) {
	var errorFlag = false;
	errorFlag = validateReportingEntity();
	if (!errorFlag) {
		$("#userProfile").hide();
		$("#reportingEntity").removeClass("active");
		$("#cbcMetaDataBtn").addClass("active");
		$("#cbcReports").removeClass("active");
		$("#cbcAddInfo").removeClass("active");
		var form_data = $('#reportingentity').serialize();
		$.ajax({

			url : 'cbc/reportingEntityNext?previous1=previous&newForm='
					+ newForm,
			type : 'POST',
			data : form_data,
			success : function(response) {
				$("#metaData").show();
				$("#metaData").html(response);
			},
			error : function(request, error) {
				alert("Request: " + JSON.stringify(request));
			}
		});
	}
}

function metaDataOnClick() {
	$("#userProfile").hide();
	$("#reportingFI").removeClass("active");
	$("#metadataBtn").addClass("active");
	$("#accountHolder").removeClass("active");
	$("#config").removeClass("active");
	$.ajax({

		url : 'crs/main',
		type : 'GET',
		success : function(data) {
			$("#metaData").show();
			$("#metaData").html(data);
		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}
function viewSummaryGrid() {
	$("#userProfile").hide();
	$("#reportingFI").removeClass("active");
	$("#metadataBtn").addClass("active");
	$("#accountHolder").removeClass("active");
	$("#config").removeClass("active");
	$.ajax({

		url : '/admin/cbc/main',
		type : 'GET',
		success : function(data) {
			$("#metaData").show();
			$("#metaData").html(data);
		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function viewReportingEntity() {

	var clients = [];
	var errorFlag = false;

	if (!errorFlag) {
		$("#metaData").hide();
		$("#reportingEntity").addClass("active");
		$("#cbcMetaDataBtn").removeClass("active");
		$("#cbcReports").removeClass("active");
		$("#cbcAddInfo").removeClass("active");
		var form_data = $('#cbcmetadata').serialize();

		$
				.ajax({

					url : 'cbc/metadataNext?next1=next',
					type : 'POST',
					data : form_data,
					success : function(response) {
						$("#metaData").show();
						$("#metaData").html(response);

						var residentCountryCode = $("#residentCountry").val();
						residentCountryCode = $.parseJSON(residentCountryCode);
						var issuedBy = residentCountryCode;
						var nameType = $("#nameTypedropdown").val();
						nameType = $.parseJSON(nameType);

						$("#reportingentity *").prop('disabled',true);
						$("#reportingEntityViewNext").prop('disabled',false);

						$("#reportingEntityResidentCountryGrid")
								.jsGrid(
										{
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
																url : 'cbc/loadResidentCountryGrid',
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
												}
											},
											invalidNotify : function(args) {
												$("#validateTextHere").text("");
												$("#validateTextHere")
														.text(
																"Please fill in all the mandatory fields");
												$('#crsNameModal')
														.modal('show');
											},
											fields : [
													{
														title : "No.<font color='red'>*</font>",
														name : "id",
														type : "number",
														width : 150,
														validate : "required",
														visible : false
													},
													{
														title : "Resident Country Code",
														name : "residentCountryCode",
														type : "select",
														items : residentCountryCode,
														valueField : "id",
														textField : "name",
													} ]
										});

						$("#reportingEntityNameGrid")
								.jsGrid(
										{
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
																url : 'cbc/loadNameGrid',
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
											data : clients,
											invalidNotify : function(args) {

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
														title : "Organisation Name<font color='red'>*</font>",
														name : "firstName",
														type : "text",
														validate : "required",
														align : "center"
													},
													{
														title : "Last Name",
														name : "lastName",
														type : "text",
														width : 150,
														visible : false
													},
													{
														title : "Organisation Type<font color='red'>*</font>",
														name : "nameType",
														type : "select",
														width : 150,
														items : nameType,
														valueField : "id",
														textField : "name",
														visible : false
													} ]
										});

						$("#reportingEntityOrgINGrid")
								.jsGrid(
										{
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
																url : 'cbc/loadOrganisationGrid',
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
											data : clients,
											invalidNotify : function(args) {

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
														title : "IN",
														name : "in",
														width : 150,
														type : "text",
														align : "center",
														validate : "required"
													},
													{
														title : "IN Type<font color='red'>*</font>",
														name : "inType",
														type : "text",
														width : 150
													},
													{
														title : "IN Issued By",
														name : "issuedBy",
														type : "select",
														items : residentCountryCode,
														valueField : "id",
														textField : "name",
													} ]
										});

						var object = {};

						$("#reportingEntityAddressGrid")
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
																url : 'cbc/loadReportingEntityAddress',
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
											/* controller: object,*/
											datatype : 'json',
											invalidNotify : function(args) {

											},
											fields : [
													{
														name : "id",
														title : "id",
														type : "text",
														visible : false,
														width : 10
													/* items: object.id*/
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
													}
												 ]
										});

					},
					error : function(request, error) {
						alert("Request: " + JSON.stringify(request));
					}
				});

	}//end if

}

function showReportingEntity() {
	var clients = [];
	var errorFlag = false;
	if ($("#reportingPeriod").val() == '') {
		$("#reportingPeriodError").empty()
				.append("Reporting Period not empty!");
		errorFlag = true;
	} else {
		$("#reportingPeriodError").empty();
	}
	if ($("#formCreationTimeStamp").val() == '') {
		$("#formCreationTimeStampError").empty().append(
				"File Creation Time Stamp not empty!");
		errorFlag = true;
	} else {
		$("#formCreationTimeStampError").empty();
	}
	if ($("#senderFileId").val() == '') {
		$("#senderFileIdError").empty().append("Sender File Id not empty!");
		errorFlag = true;
	} else {
		$("#senderFileIdError").empty();
	}
	var sendingCountryId = $('#sendingCountry').val();
	if (sendingCountryId == '0') {
		$("#sendingCountryError").empty().append("Sending Country not empty!");
		errorFlag = true;
	} else {
		$("#sendingCountryError").empty();
	}
    
    var year = $('#taxYear').val();
	if(year == null || year ==''){
		$("#taxYearError").empty().append("Year not empty!");
		errorFlag = true;
	}else{
		$("#taxYearError").empty();
	}
    
    
	var year = $('#taxYear').val();
	if(year == null || year ==''){
		$("#taxYearError").empty().append("Year not empty!");
		errorFlag = true;
	}else{
		$("#taxYearError").empty();
	}

	if (!errorFlag) {
		$("#metaData").hide();
		$("#reportingEntity").addClass("active");
		$("#cbcMetaDataBtn").removeClass("active");
		$("#cbcReports").removeClass("active");
		$("#cbcAddInfo").removeClass("active");
		var form_data = $('#cbcmetadata').serialize();

		/*
		 * var e = document.getElementById("TIN"); var residentCountryCode =
		 * jQuery.parseJSON(e);
		 */
		/*
		 * var residentCountryCode = $("#residentCountry").val();
		 * residentCountryCode = $.parseJSON(residentCountryCode); var issuedBy =
		 * residentCountryCode;
		 */

		$
				.ajax({

					url : 'cbc/metadataNext?next1=next',
					type : 'POST',
					data : form_data,
					success : function(response) {
						$("#metaData").show();
						$("#metaData").html(response);

						var residentCountryCode = $("#residentCountry").val();
						residentCountryCode = $.parseJSON(residentCountryCode);
						var issuedBy = residentCountryCode;
						var nameType = $("#nameTypedropdown").val();
						nameType = $.parseJSON(nameType);

						/*alert('@@@@@@@@@@@@'+JSON.stringify(residentCountryCode))*/

						/*
						 * var residentCountryCode = [ { "id" : "1", "name" : "MY" }, {
						 * "id" : "2", "name" : "AR" }, { "id" : "3", "name" : "AU" }, {
						 * "id" : "4", "name" : "US" } ];
						 * 
						 * var issuedBy = [ { "id" : "1", "name" : "MY" }, { "id" :
						 * "2", "name" : "AR" }, { "id" : "3", "name" : "AU" }, {
						 * "id" : "4", "name" : "US" } ];
						 */
						/*
						 * var nameType = [ { "id" : "1", "name" : "OECD 201" }, {
						 * "id" : "2", "name" : "OECD 202" }, { "id" : "3", "name" :
						 * "OECD 203" }, { "id" : "4", "name" : "OECD 204" }, { "id" :
						 * "5", "name" : "OECD 205" }, { "id" : "6", "name" : "OECD
						 * 206" }, { "id" : "7", "name" : "OECD 207" }, { "id" :
						 * "8", "name" : "OECD 208" } ];
						 */
						/* var residentCountryCode = [ { "id" : "1", "name" : "MY" }, {
						     "id" : "2", "name" : "AR" }, { "id" : "3", "name" : "AU" }, {
						     "id" : "4", "name" : "US" } ];
						 
						 alert('After@@@@@@@@@@@@'+JSON.stringify(residentCountryCode))*/

						$("#reportingEntityResidentCountryGrid")
								.jsGrid(
										{
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
																url : 'cbc/loadResidentCountryGrid',
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
																		url : "cbc/insertResidentCountryGrid",
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
																				"#reportingEntityResidentCountryGrid")
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
																		url : "cbc/updateResidentCountryGrid?id="
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
																		url : "cbc/deleteResidentCountryGrid?id="
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
												$("#validateTextHere")
														.text(
																"Please fill in all the mandatory fields");
												$('#crsNameModal')
														.modal('show');
											},
											fields : [
													{
														title : "No.<font color='red'>*</font>",
														name : "id",
														type : "number",
														width : 150,
														validate : "required",
														visible : false
													},
													{
														title : "Resident Country Code",
														name : "residentCountryCode",
														type : "select",
														items : residentCountryCode,
														valueField : "id",
														textField : "name",
													}, {
														type : "control"
													} ]
										});

						$("#reportingEntityNameGrid")
								.jsGrid(
										{
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
																url : 'cbc/loadNameGrid',
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
																		url : "cbc/insertNameGrid",
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
																				"#reportingEntityNameGrid")
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
																		url : "cbc/updateNameGrid?id="
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
																		url : "cbc/deleteNameGrid?id="
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
											data : clients,
											invalidNotify : function(args) {

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
														title : "Organisation Name<font color='red'>*</font>",
														name : "firstName",
														type : "text",
														validate : "required",
														align : "center"
													},
													{
														title : "Last Name",
														name : "lastName",
														type : "text",
														width : 150,
														visible : false
													},
													{
														title : "Organisation Type<font color='red'>*</font>",
														name : "nameType",
														type : "select",
														width : 150,
														items : nameType,
														valueField : "id",
														textField : "name",
														visible : false
													}, {
														type : "control"
													} ]
										});

						$("#reportingEntityOrgINGrid")
								.jsGrid(
										{
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
																url : 'cbc/loadOrganisationGrid',
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
																		url : "cbc/insertOrganisationGrid",
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
																				"#reportingEntityOrgINGrid")
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
																		url : "cbc/updateOrganisationGrid?id="
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
																		url : "cbc/deleteOrganisationGrid?id="
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
											data : clients,
											invalidNotify : function(args) {

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
														title : "IN",
														name : "in",
														width : 150,
														type : "text",
														align : "center",
														validate : "required"
													},
													{
														title : "IN Type<font color='red'>*</font>",
														name : "inType",
														type : "text",
														width : 150
													},
													{
														title : "IN Issued By",
														name : "issuedBy",
														type : "select",
														items : residentCountryCode,
														valueField : "id",
														textField : "name",
													}, {
														type : "control"
													} ]
										});

						var object = {};

						$("#reportingEntityAddressGrid")
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
																url : 'cbc/loadReportingEntityAddress',
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
											/* controller: object,*/
											datatype : 'json',
											invalidNotify : function(args) {

											},
											fields : [
													{
														name : "id",
														title : "id",
														type : "text",
														visible : false,
														width : 10
													/* items: object.id*/
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
																				addNewAddressReportingEntityClicked();
																			});
														},
														itemTemplate : function(
																value, item) {
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
																				/*  alert("ID: " +
																				      item.id);*/
																				e
																						.stopPropagation();
																				reportEntityViewAddress(item.id);
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
																				/* alert("ID: " +
																				     item.id);*/
																				e
																						.stopPropagation();
																				reportEntityEditAddress(item.id);
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
																				// alert("ID: " +
																				//item.id);
																				e
																						.stopPropagation();
																				reportEntityDeleteAddress(item.id);
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

					},
					error : function(request, error) {
						alert("Request: " + JSON.stringify(request));
					}
				});

	}//end if
}

function showCbcReportsPrevious(newForm, editForm, viewForm) {
	var items = $("#cbcReportsGrid").jsGrid("option", "data");
	var arrayLength = items.length;
	var errorFlag = false;
	if (arrayLength <= 0) {
		$("#documentRefIdError11").empty();
		$("#documentTypeIndicatorError").empty();
		$("#residentCountryCodeError").empty();
		$("#nbEmployeesError").empty();
		$("#unrelateCurrCodeError").empty();
		$("#unrelatedAmountError").empty();
		$("#relatedCurrencyCodeError").empty();
		$("#relatedAmountError").empty();
		$("#totalCurrencyCodeError").empty();
		$("#totalAmountError").empty();
		$("#profitOrLossCurrencyCodeError").empty();
		$("#profitOrLossAmountError").empty();
		$("#taxPaidCurrencyCodeError").empty();
		$("#taxPaidAmountError").empty();
		$("#taxAccruedCurrencyCodeError").empty();
		$("#taxAccruedAmountError").empty();
		$("#capitalCurrencyCodeError").empty();
		$("#capitalAmountError").empty();
		$("#earningsCurrencyCodeError").empty();
		$("#earningsAmountError").empty();
		$("#assetsCurrencyCodeError").empty();
		$("#assetsAmountError").empty();
		$("#tintypeError11").empty();
		$("#tinError11").empty();
		$("#issuedByError11").empty();
		$("#cbcReportsError").empty().append("CBC Reports not empty!");
		errorFlag = true;
	} else {

	}

	if (!errorFlag) {
		var clients = [];
		$("#metaData").hide();
		$("#reportingEntity").addClass("active");
		$("#cbcMetaDataBtn").removeClass("active");
		$("#cbcReports").removeClass("active");
		$("#cbcAddInfo").removeClass("active");
		var form_data = $('#cbcreports').serialize();

		$
				.ajax({

					url : 'cbc/cbcReportsNext?previous2=previous&newForm='
							+ newForm,
					type : 'POST',
					data : form_data,
					success : function(response) {
						$("#metaData").show();
						$("#metaData").html(response);
						var residentCountryCode = $("#residentCountry").val();
						residentCountryCode = $.parseJSON(residentCountryCode);
						var issuedBy = residentCountryCode;
						var nameType = $("#nameTypedropdown").val();
						nameType = $.parseJSON(nameType);
						/*
						 * var residentCountryCode = [ { "id" : "1", "name" : "MY" }, {
						 * "id" : "2", "name" : "AR" }, { "id" : "3", "name" : "AU" }, {
						 * "id" : "4", "name" : "US" } ];
						 * 
						 * var issuedBy = [ { "id" : "1", "name" : "MY" }, { "id" :
						 * "2", "name" : "AR" }, { "id" : "3", "name" : "AU" }, {
						 * "id" : "4", "name" : "US" } ];
						 */

						/*
						 * var nameType = [ { "id" : "1", "name" : "OECD 201" }, {
						 * "id" : "2", "name" : "OECD 202" }, { "id" : "3", "name" :
						 * "OECD 203" }, { "id" : "4", "name" : "OECD 204" }, { "id" :
						 * "5", "name" : "OECD 205" }, { "id" : "6", "name" : "OECD
						 * 206" }, { "id" : "7", "name" : "OECD 207" }, { "id" :
						 * "8", "name" : "OECD 208" } ];
						 */
						/* var residentCountryCode = [ { "id" : "1", "name" : "MY" }, {
						      "id" : "2", "name" : "AR" }, { "id" : "3", "name" : "AU" }, {
						      "id" : "4", "name" : "US" } ];*/

						$("#reportingEntityResidentCountryGrid")
								.jsGrid(
										{
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
																url : 'cbc/loadResidentCountryGrid',
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
																		url : "cbc/insertResidentCountryGrid",
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
																				"#reportingEntityResidentCountryGrid")
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
																		url : "cbc/updateResidentCountryGrid?id="
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
																		url : "cbc/deleteResidentCountryGrid?id="
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
												$("#validateTextHere")
														.text(
																"Please fill in all the mandatory fields");
												$('#crsNameModal')
														.modal('show');
											},
											fields : [
													{
														title : "No.<font color='red'>*</font>",
														name : "id",
														type : "number",
														width : 150,
														validate : "required",
														visible : false
													},

													{
														title : "Resident Country Code",
														name : "residentCountryCode",
														type : "select",
														items : residentCountryCode,
														valueField : "id",
														textField : "name",
													}, {
														type : "control"
													} ]
										});

						$("#reportingEntityNameGrid")
								.jsGrid(
										{
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
																url : 'cbc/loadNameGrid',
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
																		url : "cbc/insertNameGrid",
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
																				"#reportingEntityNameGrid")
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
																		url : "cbc/updateNameGrid?id="
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
																		url : "cbc/deleteNameGrid?id="
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
											data : clients,
											invalidNotify : function(args) {

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
														title : "Organisation Name<font color='red'>*</font>",
														name : "firstName",
														type : "text",
														align : "center",
														validate : "required"
													},
													{
														title : "Last Name<font color='red'>*</font>",
														name : "lastName",
														type : "text",
														width : 150,
														//validate: "required"
														visible : false

													},
													{
														title : "Organisation Type<font color='red'>*</font>",
														name : "nameType",
														type : "select",
														width : 150,
														items : nameType,
														valueField : "id",
														textField : "name",
														visible : false
													}, {
														type : "control"
													} ]
										});

						$("#reportingEntityOrgINGrid")
								.jsGrid(
										{
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
																url : 'cbc/loadOrganisationGrid',
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
													/*alert('@@@@@@@@@@@@@' +
													    item.issuedBy)*/

													$
															.ajax(
																	{
																		type : "POST",
																		url : "cbc/insertOrganisationGrid",
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
																				"#reportingEntityOrgINGrid")
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
													/*alert('@@@@@@@@@@@@@' +
													    item.issuedBy)*/

													$
															.ajax(
																	{
																		type : "POST",
																		url : "cbc/updateOrganisationGrid?id="
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
																		url : "cbc/deleteOrganisationGrid?id="
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
											data : clients,
											invalidNotify : function(args) {

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
														title : "IN",
														name : "in",
														width : 150,
														type : "text",
														align : "center",
														validate : "required"
													},
													{
														title : "IN Type<font color='red'>*</font>",
														name : "inType",
														type : "text",
														width : 150
													},
													{
														title : "IN Issued By",
														name : "issuedBy",
														type : "select",
														items : residentCountryCode,
														valueField : "id",
														textField : "name",
													}, {
														type : "control"
													} ]
										});

						var object = {};

						$("#reportingEntityAddressGrid")
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
																url : 'cbc/loadReportingEntityAddress',
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
											/*controller: object,*/
											datatype : 'json',
											invalidNotify : function(args) {

											},
											fields : [
													{
														name : "id",
														title : "id",
														type : "text",
														visible : false,
														width : 10
													/*items: object.id*/
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
																				addNewAddressReportingEntityClicked();
																			});
														},
														itemTemplate : function(
																value, item) {
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
																				/* alert("ID: " +
																				     item.id);*/
																				e
																						.stopPropagation();
																				reportEntityViewAddress(item.id);

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
																				/*alert("ID: " +
																				    item.id);*/
																				e
																						.stopPropagation();
																				reportEntityEditAddress(item.id);
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
																				//alert("ID: " +
																				//item.id);
																				e
																						.stopPropagation();
																				reportEntityDeleteAddress(item.id);
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

					},
					error : function(request, error) {
						alert("Request: " + JSON.stringify(request));
					}
				});
	}//end if
}

function showCbcReports(newForm, editForm, viewForm) {

	var errorFlag = false;
	errorFlag = validateReportingEntity();
	if (!errorFlag) {

		var clients = [];
		var clients1 = [];
		var clients2 = [];
		var clients3 = [];
		$("#metaData").hide();
		$("#reportingEntity").removeClass("active");
		$("#cbcMetaDataBtn").removeClass("active");
		$("#cbcReports").addClass("active");
		$("#cbcAddInfo").removeClass("active");
		var form_data = $('#reportingentity').serialize();

		$
				.ajax({

					url : 'cbc/reportingEntityNext?next2=next&newForm='
							+ newForm,
					type : 'POST',
					data : form_data,
					success : function(response) {
						$("#metaData").show();
						$("#metaData").html(response);
						
						showAllReportsGrid();
						
						if (newForm == 1 && editForm == 0 && viewForm == 0) {
							$("#viewReportsDone").hide();
							$("#saveCBCReportButton").show();
							$("#editReportsDone").hide();
							$("#editCancelReportsDone").hide();
							$("#viewNextTab").hide();
						} else if (newForm == 0 && editForm == 0
								&& viewForm == 1) {
							$("#saveCBCReportButton").hide();
							$("#viewReportsDone").show();
							$("#editReportsDone").hide();
							$("#editCancelReportsDone").hide();
							$('#cbcreports *').prop('readOnly', true);
							$('#cbcreports *').prop('disabled', true);
							$("#viewReportsDone").prop('disabled', false);
							$("#gridCbcReportsList").prop('disabled', false);
							$("#viewNextTab").prop('disabled',false);
							$("#constituentEntityViewBtn").prop('disabled',false);
							$("#constituentEntityViewBtn").prop('readOnly',false);
						} else if (newForm == 0 && editForm == 1
								&& viewForm == 0) {
							$("#viewReportsDone").hide();
							$("#saveCBCReportButton").hide();
							$("#editReportsDone").show();
							$("#editCancelReportsDone").show();
							$("#viewNextTab").hide();
						}					
					},
					error : function(request, error) {
						alert("Request: " + JSON.stringify(request));
					}
				});

	}//end if

}

function additionalInfoPrevious() {
	var items = $("#cbcAddInfoGrid").jsGrid("option", "data");
	var arrayLength = items.length;
	var errorFlag = false;
	if (arrayLength <= 0) {
		$("#documentReferenceIdError").empty();
		$("#documenttypeindicError").empty();
		$("#otherInfoError").empty();
		$("#cbcaddinfoError").empty().append("CBC Additional Info not empty!");
		errorFlag = true;
	}

	if (!errorFlag) {
		var clients = [];
		var clients1 = [];
		var clients2 = [];
		var clients3 = [];
		$("#metaData").hide();
		$("#reportingEntity").removeClass("active");
		$("#cbcMetaDataBtn").removeClass("active");
		$("#cbcReports").addClass("active");
		$("#cbcAddInfo").removeClass("active");
		var form_data = $('#addtionalinfo').serialize();

		$
				.ajax({

					url : 'cbc/additionalInfoPrevious?previous3=previous',
					type : 'POST',
					data : form_data,
					success : function(response) {
						$("#metaData").show();
						$("#metaData").html(response);

						$("#viewReportsDone").hide();
						$("#editReportsDone").hide();
						$("#editCancelReportsDone").hide();

						showAllReportsGrid();
					},
					error : function(request, error) {
						alert("Request: " + JSON.stringify(request));
					}
				});

	}

}
function validateCrsMetadata(){
	var errorFlag = false;
	var sendingCountryId = $('#sendingCountry').val();
	if (sendingCountryId == '0') {
		$("#sendingCountryError").empty().append("Sending Country not empty!");
		errorFlag = true;
	} else {
		$("#sendingCountryError").empty();
	}
	var sendingCountryId = $('#receivingCountry').val();
	if (sendingCountryId == '0') {
		$("#receivingCountryError").empty().append("Receiving Country not empty!");
		errorFlag = true;
	} else {
		$("#receivingCountryError").empty();
	}
	if ($("#reportingPeriod").val() == '') {
		$("#reportingPeriodError").empty()
				.append("Reporting Period not empty!");
		errorFlag = true;
	} else {
		$("#reportingPeriodError").empty();
	}
	var year = $('#taxYear').val();
	if(year == null || year ==''){
		$("#taxYearError").empty().append("Year not empty!");
		errorFlag = true;
	}else{
		$("#taxYearError").empty();
	}
	if ($("#formCreationTimeStamp").val() == '') {
		$("#formCreationTimeStampError").empty().append(
				"File Creation Time Stamp not empty!");
		errorFlag = true;
	} else {
		$("#formCreationTimeStampError").empty();
	}
	if ($("#senderFileId").val() == '') {
		$("#senderFileIdError").empty().append("Sender File Id not empty!");
		errorFlag = true;
	} else {
		$("#senderFileIdError").empty();
	}
	if ($("#messageRefId").val() == '') {
		$("#messageRefIdError").empty().append("Sender File Id not empty!");
		errorFlag = true;
	} else {
		$("#messageRefIdError").empty();
	}
	return errorFlag;
	
}
function showReportingFI() {
	
	var errorFlag = validateCrsMetadata();
	if(!errorFlag){
	$("#metaData").hide();
	$("#reportingFI").addClass("active");
	$("#metadataBtn").removeClass("active");
	$("#accountHolder").removeClass("active");
	var form_data = $('#crsmetadata').serialize();

	$.ajax({
				url : 'crs/metadataNext?next1=next',
				type : 'POST',
				data : form_data,
				success : function(data) {
					$("#metaData").show();
					$("#metaData").html(data);
			
				},
				error : function(request, error) {
					alert("Request: " + JSON.stringify(request));
				}
			});
	}
}

function accountHolderPrevious() {
	$("#metaData").hide();
	$("#reportingFI").addClass("active");
	$("#metadataBtn").removeClass("active");
	$("#accountHolder").removeClass("active");
	var form_data = $('#crsaccountholder').serialize();

	$.ajax({
				url : 'crs/accountHolderPrevious?previous2=previous',
				type : 'POST',
				data : form_data,
				success : function(data) {
					$("#metaData").show();
					$("#metaData").html(data);
			
				},
				error : function(request, error) {
					alert("Request: " + JSON.stringify(request));
				}
			});
}

//venki
function showCbcAddInfo1(newForm, editForm, viewForm) {

	var clients = [];
	$("#metaData").hide();
	$("#reportingEntity").removeClass("active");
	$("#cbcMetaDataBtn").removeClass("active");
	$("#cbcReports").removeClass("active");
	$("#cbcAddInfo").addClass("active");
	var form_data = $('#cbcreports').serialize();

	$
			.ajax({

				url : 'cbc/cbcReportsNext?next3=next&newForm=' + newForm,
				type : 'POST',
				data : form_data,
				success : function(response) {
					$("#metaData").show();
					$("#metaData").html(response);

					/*var residentCountryCode = $("#residentCountry").val();
					residentCountryCode = $.parseJSON(residentCountryCode);*/
					var residentCountry = $("#userPropCountry").val();
				    residentCountry = $.parseJSON(residentCountry);
					/*var issuedBy = residentCountryCode;*/
					var summeryReference = $("#summaryTypedropdown").val();
					summeryReference = $.parseJSON(summeryReference);

					$("#addInfoResidentCountryGrid")
							.jsGrid(
									{
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
															url : 'cbc/addinfoloadResidentCountryGrid',
															mimeType : 'application/json',
															contentType : 'application/json',
															success : function(
																	data) {
																d.resolve(data);
															},
															error : function(e) {
																alert("error: "
																		+ e.responseText);
															}
														});

												return d.promise();
											},

											insertItem : function(item) {
												var d = $.Deferred();
												$
														.ajax(
																{
																	type : "POST",
																	url : "cbc/addinfoinsertResidentCountryGrid",
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
																	$(
																			"#addInfoResidentCountryGrid")
																			.jsGrid(
																					"loadData");
																	//showCbcAddInfo(1,0,0);
																})
														.fail(
																function(msg) {
																	console
																			.log("fail"
																					+ msg);
																	d.reject();
																});
											},

											updateItem : function(item) {
												var d = $.Deferred();
												$
														.ajax(
																{
																	type : "POST",
																	url : "cbc/addinfoupdateResidentCountryGrid?id="
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
																	//showCbcAddInfo(1,0,0);
																})
														.fail(
																function(msg) {
																	console
																			.log("fail"
																					+ msg);
																	d.reject();
																});
											},

											deleteItem : function(item) {
												var d = $.Deferred();
												//alert('@@@@@@@@'+item.id)
												$
														.ajax(
																{
																	type : "POST",
																	url : "cbc/addinfodeleteResidentCountryGrid?id="
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
																	// showCbcAddInfo(1,0,0);
																})
														.fail(
																function(msg) {
																	console
																			.log("fail"
																					+ msg);
																	d.reject();
																});
											},

										},
										data : clients,
										invalidNotify : function(args) {

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
													items : residentCountry,
													valueField : "id",
													textField : "name",
												}, {
													type : "control"
												} ]
									});

					$("#addInfoSummaryReferenceGrid")
							.jsGrid(
									{
										width : "205%",
										inserting : true,
										editing : true,
										sorting : true,
										paging : true,
										pageSize : 5,
										pageButtonCount : 5,
										data : clients,
										autoload : true,
										controller : {

											loadData : function() {
												var d = $.Deferred();
												$
														.ajax({
															type : 'GET',
															url : 'cbc/loadSummaryReferenceGrid',
															mimeType : 'application/json',
															contentType : 'application/json',
															success : function(
																	data) {
																d.resolve(data);
															},
															error : function(e) {
																alert("error: "
																		+ e.responseText);
															}
														});

												return d.promise();
											},

											insertItem : function(item) {
												var d = $.Deferred();
												$
														.ajax(
																{
																	type : "POST",
																	url : "cbc/addSummaryReferenceGrid",
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
																	$(
																			"#addInfoSummaryReferenceGrid")
																			.jsGrid(
																					"loadData");
																	//showCbcAddInfo(1,0,0);
																})
														.fail(
																function(msg) {
																	console
																			.log("fail"
																					+ msg);
																	d.reject();
																});
											},

											updateItem : function(item) {
												var d = $.Deferred();
												$
														.ajax(
																{
																	type : "POST",
																	url : "cbc/updateSummaryReferenceGrid?id="
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
																	//showCbcAddInfo(1,0,0);
																})
														.fail(
																function(msg) {
																	console
																			.log("fail"
																					+ msg);
																	d.reject();
																});
											},

											deleteItem : function(item) {
												var d = $.Deferred();
												//alert('@@@@@@@@'+item.id)
												$
														.ajax(
																{
																	type : "POST",
																	url : "cbc/deleteSummaryReferenceGrid?id="
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
																	// showCbcAddInfo(1,0,0);
																})
														.fail(
																function(msg) {
																	console
																			.log("fail"
																					+ msg);
																	d.reject();
																});
											},

										},
										invalidNotify : function(args) {

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
													title : "Summary Reference",
													name : "summeryReference",
													type : "select",
													items : summeryReference,
													valueField : "id",
													textField : "name",
												}, {
													type : "control"
												} ]
									});

					if (newForm == 1 && editForm == 0 && viewForm == 0) {
						$("#viewAddInfoDone").hide();
						$("#saveCBCAddInfoButton").show();
						$("#editAddInfoDone").hide();
						$("#editCancelInfoDone").hide();
						$('#addInfoSummaryReferenceGrid').prop('disabled',
								false);
						$("#addInfoResidentCountryGrid")
								.prop('disabled', false);
					} else if (newForm == 0 && editForm == 0 && viewForm == 1) {
						$("#form :input").prop("disabled", true);
						$("#saveCBCAddInfoButton").hide();
						$("#viewAddInfoDone").show();
						$("#editAddInfoDone").hide();
						$("#editCancelInfoDone").hide();
						$('#addtionalinfo *').prop('readOnly', true);
						$('#addtionalinfo *').prop('disabled', true);
						$("#viewAddInfoDone").prop('disabled', false);
						$("#cbcAddInfoGrid").prop('disabled', false);
						$('#addInfoSummaryReferenceGrid')
								.prop('disabled', true);
						$("#addInfoResidentCountryGrid").prop('disabled', true);
					} else if (newForm == 0 && editForm == 1 && viewForm == 0) {
						$("#viewAddInfoDone").hide();
						$("#saveCBCAddInfoButton").hide();
						$("#editAddInfoDone").show();
						$("#editCancelInfoDone").show();
						$('#addInfoSummaryReferenceGrid').prop('disabled',
								false);
						$("#addInfoResidentCountryGrid")
								.prop('disabled', false);
					}
				},
				error : function(request, error) {
					alert("Request: " + JSON.stringify(request));
				}
			});

}

//venki

function showCbcAddInfo(newForm, editForm, viewForm) {

	debugger;
	var items = $("#cbcReportsGrid").jsGrid("option", "data");
	var arrayLength = items.length;
	var errorFlag = false;
	if (arrayLength <= 0) {
		$("#documentRefIdError11").empty();
		$("#documentTypeIndicatorError").empty();
		$("#residentCountryCodeError").empty();
		$("#nbEmployeesError").empty();
		$("#unrelateCurrCodeError").empty();
		$("#unrelatedAmountError").empty();
		$("#relatedCurrencyCodeError").empty();
		$("#relatedAmountError").empty();
		$("#totalCurrencyCodeError").empty();
		$("#totalAmountError").empty();
		$("#profitOrLossCurrencyCodeError").empty();
		$("#profitOrLossAmountError").empty();
		$("#taxPaidCurrencyCodeError").empty();
		$("#taxPaidAmountError").empty();
		$("#taxAccruedCurrencyCodeError").empty();
		$("#taxAccruedAmountError").empty();
		$("#capitalCurrencyCodeError").empty();
		$("#capitalAmountError").empty();
		$("#earningsCurrencyCodeError").empty();
		$("#earningsAmountError").empty();
		$("#assetsCurrencyCodeError").empty();
		$("#assetsAmountError").empty();
		$("#tintypeError11").empty();
		$("#tinError11").empty();
		$("#issuedByError11").empty();
		$("#cbcReportsError").empty().append("CBC Reports not empty!");
		errorFlag = true;
	} else {

	}

	if (!errorFlag) {
		var clients = [];
		$("#metaData").hide();
		$("#reportingEntity").removeClass("active");
		$("#cbcMetaDataBtn").removeClass("active");
		$("#cbcReports").removeClass("active");
		$("#cbcAddInfo").addClass("active");
		var form_data = $('#cbcreports').serialize();

		$
				.ajax({

					url : 'cbc/cbcReportsNext?next3=next&newForm=' + newForm,
					type : 'POST',
					data : form_data,
					success : function(response) {
						$("#metaData").show();
						$("#metaData").html(response);

						/*var residentCountryCode = $("#residentCountry").val();
						residentCountryCode = $.parseJSON(residentCountryCode);*/
						var residentCountry = $("#userPropCountry").val();
					    residentCountry = $.parseJSON(residentCountry);
						/*var issuedBy = residentCountryCode;*/
						var summeryReference = $("#summaryTypedropdown").val();
						summeryReference = $.parseJSON(summeryReference);

						$("#addInfoResidentCountryGrid")
								.jsGrid(
										{
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
																url : 'cbc/addinfoloadResidentCountryGrid',
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
												},

												insertItem : function(item) {
													var d = $.Deferred();
													$
															.ajax(
																	{
																		type : "POST",
																		url : "cbc/addinfoinsertResidentCountryGrid",
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
																		$(
																				"#addInfoResidentCountryGrid")
																				.jsGrid(
																						"loadData");
																		//showCbcAddInfo(1,0,0);
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
																		url : "cbc/addinfoupdateResidentCountryGrid?id="
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
																		//showCbcAddInfo(1,0,0);
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
													//alert('@@@@@@@@'+item.id)
													$
															.ajax(
																	{
																		type : "POST",
																		url : "cbc/addinfodeleteResidentCountryGrid?id="
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
																		// showCbcAddInfo(1,0,0);
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
											data : clients,
											invalidNotify : function(args) {

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
														items : residentCountry,
														valueField : "id",
														textField : "name",
													}, {
														type : "control"
													} ]
										});

						$("#addInfoSummaryReferenceGrid")
								.jsGrid(
										{
											width : "205%",
											inserting : true,
											editing : true,
											sorting : true,
											paging : true,
											pageSize : 5,
											pageButtonCount : 5,
											data : clients,
											autoload : true,
											controller : {

												loadData : function() {
													var d = $.Deferred();
													$
															.ajax({
																type : 'GET',
																url : 'cbc/loadSummaryReferenceGrid',
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
												},

												insertItem : function(item) {
													var d = $.Deferred();
													$
															.ajax(
																	{
																		type : "POST",
																		url : "cbc/addSummaryReferenceGrid",
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
																		$(
																				"#addInfoSummaryReferenceGrid")
																				.jsGrid(
																						"loadData");
																		//showCbcAddInfo(1,0,0);
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
																		url : "cbc/updateSummaryReferenceGrid?id="
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
																		//showCbcAddInfo(1,0,0);
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
													//alert('@@@@@@@@'+item.id)
													$
															.ajax(
																	{
																		type : "POST",
																		url : "cbc/deleteSummaryReferenceGrid?id="
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
																		// showCbcAddInfo(1,0,0);
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
														title : "Summary Reference",
														name : "summeryReference",
														type : "select",
														items : summeryReference,
														valueField : "id",
														textField : "name",
													}, {
														type : "control"
													} ]
										});

						if (newForm == 1 && editForm == 0 && viewForm == 0) {
							$("#viewAddInfoDone").hide();
							$("#saveCBCAddInfoButton").show();
							$("#editAddInfoDone").hide();
							$("#editCancelInfoDone").hide();
							$('#addInfoSummaryReferenceGrid').prop('disabled',
									false);
							$("#addInfoResidentCountryGrid").prop('disabled',
									false);
							$("#addInfoViewButton").hide();
						} else if (newForm == 0 && editForm == 0
								&& viewForm == 1) {
							$("#form :input").prop("disabled", true);
							$("#saveCBCAddInfoButton").hide();
							$("#viewAddInfoDone").show();
							$("#editAddInfoDone").hide();
							$("#editCancelInfoDone").hide();
							$('#addtionalinfo *').prop('readOnly', true);
							$('#addtionalinfo *').prop('disabled', true);
							$("#viewAddInfoDone").prop('disabled', false);
							$("#cbcAddInfoGrid").prop('disabled', false);
							$('#addInfoSummaryReferenceGrid').prop('disabled',
									true);
							$("#addInfoResidentCountryGrid").prop('disabled',
									true);
							$("#addInfoViewButton").prop('disabled',false);
						} else if (newForm == 0 && editForm == 1
								&& viewForm == 0) {
							$("#viewAddInfoDone").hide();
							$("#saveCBCAddInfoButton").hide();
							$("#editAddInfoDone").show();
							$("#editCancelInfoDone").show();
							$('#addInfoSummaryReferenceGrid').prop('disabled',
									false);
							$("#addInfoResidentCountryGrid").prop('disabled',
									false);
							$("#addInfoViewButton").hide();
						}
					},
					error : function(request, error) {
						alert("Request: " + JSON.stringify(request));
					}
				});
	}//end if

}

function showAccountHolder() {
	$("#metaData").hide();
	$("#reportingFI").removeClass("active");
	$("#metadataBtn").removeClass("active");
	$("#accountHolder").addClass("active");
	$("#config").removeClass("active");

	$.ajax({

		url : '/admin/tabs/accountHolder',
		type : 'GET',
		success : function(data) {
			$("#metaData").show();
			$("#metaData").html(data);

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function showConfig() {
	$("#metaData").hide();
	$("#reportingFI").removeClass("active");
	$("#metadataBtn").removeClass("active");
	$("#accountHolder").removeClass("active");
	$("#config").addClass("active");

	$.ajax({

		url : '/admin/tabs/config',
		type : 'GET',
		success : function(data) {
			$("#metaData").show();
			$("#metaData").html(data);
		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function addNameDetailsClicked() {
	$('#crsNameModal').modal('show');
}

function addNewName() {
	$('#crsNameModal').modal('hide');
}

function saveUserProfile() {
	/* var data = $("#jsGrid").jsGrid("option", "data");
	 console.log(data);*/
	$.ajax({
		url : '/admin/profile',
		type : 'POST',
		async : false,
		cache : false,
		enctype : 'multipart/form-data',
		processData : false,
		contentType : false,
		cache : false,
		data : $('#userProfile').serialize(),
		success : function(data) {
			alert("saved in session");
			console.log(data);
			return false;

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});

}

function reportEntityViewAddress(id) {
	$.ajax({
		url : 'cbc/reportEntityviewAddress?id=' + id,
		type : 'GET',
		async : false,
		cache : false,
		data : $('#viewReportingFiAdd').serialize(),
		success : function(data) {
			// alert('view Callled')
			var htmlFiltered = $("<div>").html(data).find(
					"#viewReportingFiAddress").html();
			$('#viewReportingFiAddress').html('');
			$('#viewReportingFiAddress').html(htmlFiltered);
			$("#viewReportingFiAddress").modal('show');
			return false;

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function reportEntityEditAddress(id) {
	/* $.ajax({
	     url: '/admin/cbc/reportEntityeditAddress?id='+id,
	     type: 'GET',
	     async: false,
	     success: function(response) {
	         alert('@@@@@@@@@@@@@@@' + response);
	         return false;
	        


	     },
	     error: function(request, error) {
	         alert("Request: " + JSON.stringify(request));
	     }
	 });*/
	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'cbc/reportEntityeditAddress?id=' + id,
			async : false,
			cache : false,
			data : $('#editNewReportingEntity').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewReportingEntityAddress").html();
				$('#editNewReportingEntityAddress').html('');
				$('#editNewReportingEntityAddress').html(htmlFiltered);
				$("#editNewReportingEntityAddress").modal('show');
				return false;
			}
		});

	});
}
function reportEntityEditSaveAddress() {

	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'cbc/reportEntityeditSaveAddress',
			async : false,
			cache : false,
			data : $('#editNewReportingEntity').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewReportingEntityAddress").html();
				$('#editNewReportingEntityAddress').html('');
				$('#editNewReportingEntityAddress').html(htmlFiltered);
				/*$("#editNewReportingEntityAddress").modal('close');*/
				$('#editNewReportingEntityAddress').modal('toggle');
				$(
				"#reportingEntityAddressGrid")
				.jsGrid(
						"loadData");
				return false;
			}
		});

	});
}

function reportEntityDeleteAddress(id) {
	$.ajax({
		url : 'cbc/reportEntitydeleteAddress?id=' + id,
		type : 'GET',
		async : false,
		success : function(response) {
			/*var obj = jQuery.parseJSON(response);*/
			//$("#addNewReportingFiAddress").modal('show');
			/*$("#reportingFIAddressGrid").jsGrid("insertItem", obj).done(function() {
			    console.log("insertion completed");
			});*/

			// alert('@@@@@@@@@@@@@@@' + response);
			$(
			"#reportingEntityAddressGrid")
			.jsGrid(
					"loadData");
			return false;
			//addNewAddressReportingFIClicked();

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function cbcReportsViewAddress(id) {
	
	$.ajax({
		url : 'cbc/cbcReportsviewAddress?id=' + id,
		type : 'GET',
		async : false,
		cache : false,
		data : $('#viewcbcReportsAdd').serialize(),
		success : function(data) {
			//alert('view Callled')
			var htmlFiltered = $("<div>").html(data).find(
					"#viewCbcReportsAddress").html();
			$('#viewCbcReportsAddress').html('');
			$('#viewCbcReportsAddress').html(htmlFiltered);
			$("#viewCbcReportsAddress").modal('show');
			return false;

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}

function cbcReportsEditAddress(id) {
	/* $.ajax({
	     url: '/admin/cbc/reportEntityeditAddress?id='+id,
	     type: 'GET',
	     async: false,
	     success: function(response) {
	         alert('@@@@@@@@@@@@@@@' + response);
	         return false;
	        


	     },
	     error: function(request, error) {
	         alert("Request: " + JSON.stringify(request));
	     }
	 });*/
	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'cbc/cbcReportseditAddress?id=' + id,
			async : false,
			cache : false,
			data : $('#editcbcreports').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editCbcReportsAddress").html();
				$('#editCbcReportsAddress').html('');
				$('#editCbcReportsAddress').html(htmlFiltered);
				$("#editCbcReportsAddress").modal('show');
				return false;
			}
		});

	});
}
function cbcReportsEditSaveAddress() {

	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'cbc/cbcReportseditSaveAddress',
			async : false,
			cache : false,
			data : $('#editNewReportingEntity').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewReportingEntityAddress").html();
				$('#editNewReportingEntityAddress').html('');
				$('#editNewReportingEntityAddress').html(htmlFiltered);
				/*$("#editNewReportingEntityAddress").modal('close');*/
				$('#editNewReportingEntityAddress').modal('toggle');
				$(
				"#cbcReportsAddressGrid")
				.jsGrid(
						"loadData");
				return false;
			}
		});

	});
}

function cbcReportsEditSaveAddress() {
	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'cbc/cbcReportseditSaveAddress',
			async : false,
			cache : false,
			data : $('#editcbcreports').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editCbcReportsAddress").html();
				$('#editCbcReportsAddress').html('');
				$('#editCbcReportsAddress').html(htmlFiltered);
				/*$("#editNewReportingEntityAddress").modal('close');*/
				$('#editCbcReportsAddress').modal('toggle');
				$(
				"#cbcReportsAddressGrid")
				.jsGrid(
						"loadData");
				return false;
			}
		});

	});
}

function cbcReportsDeleteAddress(id) {
	$.ajax({
		url : 'cbc/cbcReportsdeleteAddress?id=' + id,
		type : 'GET',
		async : false,
		success : function(response) {
			$(
			"#cbcReportsAddressGrid")
			.jsGrid(
					"loadData");
			return false;

		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
}


function generateCRSMetaData() {
	/*var items = $("#cbcAddInfoGrid").jsGrid("option", "data");
	var arrayLength = items.length;
	var errorFlag = false;
	if (arrayLength <= 0) {
		$("#documentReferenceIdError").empty();
		$("#documenttypeindicError").empty();
		$("#otherInfoError").empty();
		$("#cbcaddinfoError").empty().append("CBC Additional Info not empty!");
		errorFlag = true;
	}*/

	/*if (!errorFlag) {*/
		var downloadUrl = "crs/generateMetaData";
		window.location.href = downloadUrl;
	/*}*/
}




function onClickImportExcelCalled() {
	$("#importExcel").submit();
}

function doenloadExcel() {
	var downloadUrl = "cbc/downloadTemplate";
	window.location.href = downloadUrl;
}
function ReportingFiPrevious() {
	var errorFlag = validateReportingFi();
	if(!errorFlag){
	$("#userProfile").hide();
	$("#reportingFI").removeClass("active");
	$("#metadataBtn").addClass("active");
	$("#accountHolder").removeClass("active");
	$("#config").removeClass("active");
	
	$.ajax({

		url : 'crs/reportingFiNextOrPrevious?previous1=previous&newForm=3',
		type : 'POST',
		data : $('#crsreportingfi').serialize(),
		success : function(data) {
			$("#metaData").show();
			$("#metaData").html(data);
		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
	}
}

function validateReportingFi(){
	
	var items = $("#reportingFINameGrid").jsGrid("option", "data");
	var arrayLength = items.length;
	var errorFlag = false;
	if(arrayLength == 0){
		$("#crsNameGridError").empty().append("Organisation Grid not empty!");
		errorFlag = true;
	}else{
		$("#crsNameGridError").empty();
	}
	var sendingCountryId = $('#docTypeIndicatorReportingFI').val();
	if (sendingCountryId == '0') {
		$("#docTypeIndicatorReportingFIError").empty().append("Document Indicator not empty!");
		errorFlag = true;
	} else {
		$("#docTypeIndicatorReportingFIError").empty();
	}
	if ($("#docRefId").val() == '') {
		$("#docRefIdFIError").empty()
				.append("DocRefId not empty!");
		errorFlag = true;
	} else {
		$("#docRefIdFIError").empty();
	}
	return errorFlag;
	
	
}

function ReportingFiNext(newForm, editForm, viewForm) {
	var errorFlag = validateReportingFi();
	if(!errorFlag){
	$("#userProfile").hide();
	$("#reportingFI").removeClass("active");
	$("#metadataBtn").removeClass("active");
	$("#accountHolder").addClass("active");
	/*$("#config").removeClass("active");*/
	$.ajax({

		url : 'crs/reportingFiNextOrPrevious?next1=next&newForm='
			+ newForm,
		type : 'POST',
		success : function(data) {
			$("#metaData").show();
			$("#metaData").html(data);
			if (newForm == 1 && editForm == 0 && viewForm == 0) {
				$("#viewAccountDone").hide();
				$("#saveaccountholder").show();
				$("#editAccountDone").hide();
				$("#editCancelAccountDone").hide();
				
				$('#saveControllingPersonButton').show();
		        $('#viewControllingDone').hide();
		        $('#editviewControllingDoneDone').hide();
		        $('#editCancelviewControllingDoneDone').hide();
				/*$("#viewNextTab").hide();*/
			} else if (newForm == 0 && editForm == 0
					&& viewForm == 1) {
				debugger;
				$("#saveaccountholder").hide();
				$("#viewAccountDone").show();
				$("#editAccountDone").hide();
				$("#editCancelAccountDone").hide();
				$('#crsaccountholder *').prop('readOnly', true);
				$('#crsaccountholder *').prop('disabled', true);
				$("#viewAccountDone").prop('disabled', false);
				/*$("#gridCbcReportsList").prop('disabled', false);
				$("#viewNextTab").prop('disabled',false);*/
			} else if (newForm == 0 && editForm == 1
					&& viewForm == 0) {
				$("#viewAccountDone").hide();
				$("#saveaccountholder").hide();
				$("#editAccountDone").show();
				$("#editCancelAccountDone").show();
				/*$("#viewNextTab").hide();*/
			}else{
				$("#viewAccountDone").hide();
				$("#saveaccountholder").show();
				$("#editAccountDone").hide();
				$("#editCancelAccountDone").hide();
				
				$('#saveControllingPersonButton').show();
		        $('#viewControllingDone').hide();
		        $('#editviewControllingDoneDone').hide();
		        $('#editCancelviewControllingDoneDone').hide();
			}
		},
		error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
	}
}



function showAllReportsGrid(){
	 var residentCountryCode = $("#residentCountry").val();
		residentCountryCode = $.parseJSON(residentCountryCode);
		var issuedBy = residentCountryCode;
		var nameType = $("#nameTypedropdown").val();
		nameType = $.parseJSON(nameType);

		var bizType = $("#bizTypedropdown").val();
		bizType = $.parseJSON(bizType);
		
		var clients = [];
		var clients1 = [];
		var clients2 = [];
		var clients3 = [];
     
     $("#cbcReportsConstituentEntityGrid")
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
 											url : 'cbc/loadCbcConstituentEntityGrid',
 											mimeType : 'application/json',
 											contentType : "application/json; charset=utf-8",
 											success : function(
 													data) {
 												d.resolve(data);
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
 						/*controller: object,*/
 						datatype : 'json',
 						invalidNotify : function(args) {

 						},
 						fields : [
 								{
 									name : "consId",
 									title : "ID",
 									type : "text",
 									visible : false,
 									width : 15
 								/*,
 								items: object.id*/
 								},
 								{
 									title : "TIN",
 									name : "tin",
 									type : "text",
 									width : 150,
 									/*items: object.addressType,*/
 									visible : true
 								},
 								{
 									title : "TIN Type",
 									name : "tinType",
 									type : "text",
 									width : 150,
 									/*,
 									items: object.countryCode,*/
 									visible : true
 								},
 								{
 									name : "",
 									itemTemplate : function(
 											value, item) {
 										var $result = jsGrid.fields.control.prototype.itemTemplate
 												.apply(
 														this,
 														arguments);

 										var $customViewButton = $(
 												"<button>")
 												.attr(
 														"class",
 														"btn btn-info btn-sm")
 												.attr("id","constituentEntityViewBtn")		
 												.text(
 														"View")
 												.click(
 														function(
 																e) {
 															/*alert("ID: " +
 															    item.id);*/
 															e.stopPropagation();
 															cbcViewConstituentEntity(item.consId);
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
 															/* alert("ID: " +
 															     item.id);*/
 															e
 																	.stopPropagation();
 															cbcEditConstituentEntity(item.consId);
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
 															//alert("ID: " +
 															//item.id);
 															e
 																	.stopPropagation();
 															cbcDeleteConstituentEntity(item.consId);
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
     
     $("#bizActivitiesGrid")
		.jsGrid(
				{
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
										url : 'cbc/reportsBizloadGrid',
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
												url : "cbc/reportsinsertBizGrid",
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
												$(
														"#bizActivitiesGrid")
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
												url : "cbc/reportsupdateBizGrid?id="
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
												url : "cbc/reportsdeleteBizGrid?id="
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

					data : clients1,
					invalidNotify : function(args) {

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
								title : "Biz Activities Type<font color='red'>*</font>",
								name : "bizType",
								type : "select",
								width : 150,
								items : bizType,
								valueField : "id",
								textField : "name",
								validate : "required"
							}, {
								type : "control"
							} ]
				});

     var residentCountry = $("#userPropCountry").val();
     residentCountry = $.parseJSON(residentCountry);
     var issuedCountry = residentCountry;

$("#cbcReportsResidentCountryGrid")
		.jsGrid(
				{
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
										url : 'cbc/reportsloadResidentCountryGrid',
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
						},

						insertItem : function(item) {
							var d = $.Deferred();

							$
									.ajax(
											{
												type : "POST",
												url : "cbc/reportsinsertResidentCountryGrid",
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
												$(
														"#cbcReportsResidentCountryGrid")
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
												url : "cbc/reportsupdateResidentCountryGrid?id="
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
												url : "cbc/reportsdeleteResidentCountryGrid?id="
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
					data : clients,
					invalidNotify : function(args) {
						$("#validateTextHere").text("");
						$("#validateTextHere")
								.text(
										"Please fill in all the mandatory fields");
						$('#crsNameModal')
								.modal('show');
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
								items : residentCountry,
								valueField : "id",
								textField : "name",
							}, {
								type : "control"
							} ]
				});

$("#cbcReportsNameGrid")
		.jsGrid(
				{
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
										url : 'cbc/reportsloadNameGrid',
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
												url : "cbc/reportsinsertNameGrid",
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
												$(
														"#cbcReportsNameGrid")
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
												url : "cbc/reportsupdateNameGrid?id="
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
												url : "cbc/reportsdeleteNameGrid?id="
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

					data : clients1,
					invalidNotify : function(args) {

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
								title : "Organisation Name<font color='red'>*</font>",
								name : "firstName",
								type : "text",
								validate : "required",
								align : "center"
							},
							{
								title : "Last Name<font color='red'>*</font>",
								name : "lastName",
								type : "text",
								width : 150,
								validate : "required",
								visible : false
							},
							{
								title : "Name Type<font color='red'>*</font>",
								name : "nameType",
								type : "select",
								width : 150,
								items : nameType,
								valueField : "id",
								textField : "name",
								validate : "required",
								visible : false
							}, {
								type : "control"
							} ]
				});

$("#cbcReportsINGrid")
		.jsGrid(
				{
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
										url : 'cbc/reportsloadOrganisationGrid',
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
												url : "cbc/reportsinsertOrganisationGrid",
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
												$(
														"#cbcReportsNameGrid")
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
												url : "cbc/reportsupdateOrganisationGrid?id="
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
							/*alert('@@@@@@@@@@@@@' + item.issuedBy)*/

							$
									.ajax(
											{
												type : "POST",
												url : "cbc/reportsdeleteOrganisationGrid?id="
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
					data : clients2,
					invalidNotify : function(args) {

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
							/*{
							    title: "Organisation IN Type Issued By",
							    name: "issuedBy",
							    type: "select",
							    items: residentCountryCode,
							    valueField: "id",
							    textField: "name",
							}, */
							{
								title : "IN",
								name : "in",
								width : 150,
								type : "text",
								align : "center",
								validate : "required"
							},
							{
								title : "IN Type",
								name : "inType",
								type : "text",
								width : 150
							},
							{
								title : "IN Issued By",
								name : "issuedBy",
								type : "select",
								items : issuedCountry,
								valueField : "id",
								textField : "name",
							}, {
								type : "control"
							} ]
				});

var object = {};
$("#cbcReportsAddressGrid")
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
										url : 'cbc/loadCbcReportsAddress',
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
					/*controller: object,*/
					datatype : 'json',
					invalidNotify : function(args) {

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
								/*items: object.addressType,*/
								visible : true
							},
							{
								title : "Country Code",
								name : "countryCode",
								type : "text",
								width : 150,
								/*,
								items: object.countryCode,*/
								visible : true
							},
							{
								name : "",
								headerTemplate : function() {
									return $("<button>")
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
														addNewAddressCbcReportsClicked();
													});
								},
								itemTemplate : function(
										value, item) {
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
														/*alert("ID: " +
														    item.id);*/
														e
																.stopPropagation();
														cbcReportsViewAddress(item.id);

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
														/* alert("ID: " +
														     item.id);*/
														e
																.stopPropagation();
														cbcReportsEditAddress(item.id);
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
														//alert("ID: " +
														//item.id);
														e
																.stopPropagation();
														cbcReportsDeleteAddress(item.id);
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


function cbcViewConstituentEntity(item){
	$
	   .ajax({

	       url: 'cbc/viewConsEntity?viewId='+item,
	       type: 'GET',
	       async: false,
	       success: function(data) {	        	
	        	var htmlFiltered = $("<div>").html(data).find("#constituentEntities").html();
	        	console.log(htmlFiltered);
	            $('#constituentEntities').html('');
	            $('#constituentEntities').html(htmlFiltered);	            
	            $('#constituentEntities').find('input, textarea, button, select').attr('disabled','disabled');
	            $('#saveConstEntityButton').hide();
	            $('#viewConstEntityButton').show();
	            $('#editConstEntityButton').hide();
	            $('#viewConstEntityButton').prop('disabled',false);
	            showAllViewReportsGrid();
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


function cbcEditConstituentEntity(item){
	$
	   .ajax({

	       url: 'cbc/viewConsEntity?viewId='+item,
	       type: 'GET',
	       async: false,
	       success: function(data) {	        	
	        	var htmlFiltered = $("<div>").html(data).find("#constituentEntities").html();
	        	console.log(htmlFiltered);
	            $('#constituentEntities').html('');
	            $('#constituentEntities').html(htmlFiltered);	            
	            $('#saveConstEntityButton').hide();
	            $('#viewConstEntityButton').show();
	            $('#editConstEntityButton').show();
	            showAllReportsGrid();
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

function cbcDeleteConstituentEntity(item){
	
	$
	   .ajax({

	       url: 'cbc/deleteConsEntity?viewId='+item,
	       type: 'GET',
	       async: false,
	       success: function(data) {	        	
	        	var htmlFiltered = $("<div>").html(data).find("#constituentEntities").html();
	        	console.log(htmlFiltered);
	            $('#constituentEntities').html('');
	            $('#constituentEntities').html(htmlFiltered);	            
	            $('#saveConstEntityButton').show();
	            $('#viewConstEntityButton').hide();
	            $('#editConstEntityButton').hide();
	            showAllReportsGrid();
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



function returnNormalConstEntity(){
	$
	   .ajax({

	       url: 'cbc/viewConstituentEntityDone',
	       type: 'GET',
	       async: false,
	       success: function(data) {	        	
	        	var htmlFiltered = $("<div>").html(data).find("#constituentEntities").html();
	        	console.log(htmlFiltered);
	            $('#constituentEntities').html('');
	            $('#constituentEntities').html(htmlFiltered);	            
	            $('#saveConstEntityButton').show();
	            $('#viewConstEntityButton').hide();
	            $('#editConstEntityButton').hide();
	            showAllReportsGrid();
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


function showAllViewReportsGrid(){

	 var residentCountryCode = $("#residentCountry").val();
		residentCountryCode = $.parseJSON(residentCountryCode);
		var issuedBy = residentCountryCode;
		var nameType = $("#nameTypedropdown").val();
		nameType = $.parseJSON(nameType);

		var bizType = $("#bizTypedropdown").val();
		bizType = $.parseJSON(bizType);
		
		var clients = [];
		var clients1 = [];
		var clients2 = [];
		var clients3 = [];
    
    $("#cbcReportsConstituentEntityGrid")
	.jsGrid(
					{
						width : "205%",
						inserting : false,
						editing : false,
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
											url : 'cbc/loadCbcConstituentEntityGrid',
											mimeType : 'application/json',
											contentType : "application/json; charset=utf-8",
											success : function(
													data) {
												d.resolve(data);
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
						/*controller: object,*/
						datatype : 'json',
						invalidNotify : function(args) {

						},
						fields : [
								{
									name : "consId",
									title : "ID",
									type : "text",
									visible : false,
									width : 15
								/*,
								items: object.id*/
								},
								{
									title : "TIN",
									name : "tin",
									type : "text",
									width : 150,
									/*items: object.addressType,*/
									visible : true
								},
								{
									title : "TIN Type",
									name : "tinType",
									type : "text",
									width : 150,
									/*,
									items: object.countryCode,*/
									visible : true
								},
								{
									name : "",
									itemTemplate : function(
											value, item) {}
								} ]
					});
    
    $("#bizActivitiesGrid")
		.jsGrid(
				{
					width : "205%",
					inserting : false,
					editing : false,
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
										url : 'cbc/reportsBizloadGrid',
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
					},

					data : clients1,
					invalidNotify : function(args) {

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
								title : "Biz Activities Type<font color='red'>*</font>",
								name : "bizType",
								type : "select",
								width : 150,
								items : bizType,
								valueField : "id",
								textField : "name",
								validate : "required"
							}]
				});

var residentCountry = $("#userPropCountry").val();
residentCountry = $.parseJSON(residentCountry);
var issuedCountry = residentCountry;
$("#cbcReportsResidentCountryGrid")
		.jsGrid(
				{
					width : "205%",
					inserting : false,
					editing : false,
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
										url : 'cbc/reportsloadResidentCountryGrid',
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
						},
					},
					data : clients,
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
								title : "Resident Country Code",
								name : "residentCountryCode",
								type : "select",
								items : residentCountry,
								valueField : "id",
								textField : "name",
							}]
				});

$("#cbcReportsNameGrid")
		.jsGrid(
				{
					width : "205%",
					inserting : false,
					editing : false,
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
										url : 'cbc/reportsloadNameGrid',
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
					},

					data : clients1,
					invalidNotify : function(args) {

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
								title : "Organisation Name<font color='red'>*</font>",
								name : "firstName",
								type : "text",
								validate : "required",
								align : "center"
							},
							{
								title : "Last Name<font color='red'>*</font>",
								name : "lastName",
								type : "text",
								width : 150,
								validate : "required",
								visible : false
							},
							{
								title : "Name Type<font color='red'>*</font>",
								name : "nameType",
								type : "select",
								width : 150,
								items : nameType,
								valueField : "id",
								textField : "name",
								validate : "required",
								visible : false
							}]
				});

$("#cbcReportsINGrid")
		.jsGrid(
				{
					width : "205%",
					inserting : false,
					editing : false,
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
										url : 'cbc/reportsloadOrganisationGrid',
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
					},
					data : clients2,
					invalidNotify : function(args) {

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
							/*{
							    title: "Organisation IN Type Issued By",
							    name: "issuedBy",
							    type: "select",
							    items: residentCountryCode,
							    valueField: "id",
							    textField: "name",
							}, */
							{
								title : "IN",
								name : "in",
								width : 150,
								type : "text",
								align : "center",
								validate : "required"
							},
							{
								title : "IN Type<font color='red'>*</font>",
								name : "inType",
								type : "text",
								width : 150
							},
							{
								title : "IN Issued By",
								name : "issuedBy",
								type : "select",
								items : issuedCountry,
								valueField : "id",
								textField : "name",
							} ]
				});

var object = {};
$("#cbcReportsAddressGrid")
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
										url : 'cbc/loadCbcReportsAddress',
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
					/*controller: object,*/
					datatype : 'json',
					invalidNotify : function(args) {

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
								/*items: object.addressType,*/
								visible : true
							},
							{
								title : "Country Code",
								name : "countryCode",
								type : "text",
								width : 150,
								/*,
								items: object.countryCode,*/
								visible : true
							},
							{
								name : "button",
								itemTemplate : function(
										value, item) {
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
														/*alert("ID: " +
														    item.id);*/
														e
																.stopPropagation();
														cbcReportsViewAddress(item.id);

														return false;

													});

								


									return $("<div>")
											.append(
													$customViewButton);
											
									// return
									// $result.add($customButton);
								}
							} ]
				});
	

}

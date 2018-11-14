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
	} ]*/
	
	var residentCountryCode = $("#residentCountry").val();
	residentCountryCode = $.parseJSON(residentCountryCode);
	var issuedBy = residentCountryCode;
	var nameType = $("#nameTypedropdown").val();
	nameType = $.parseJSON(nameType);

	var valLog = "";

	$("#reportingFIResidentCountryGrid")
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
									url : 'crs/loadResidentCountryGrid',
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
											url : "crs/insertResidentCountryGrid",
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
													"#reportingFIResidentCountryGrid")
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
											url : "crs/updateResidentCountryGrid?id="
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
											url : "crs/deleteResidentCountryGrid?id="
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

$("#reportingFINameGrid")
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
									url : 'crs/loadNameGrid',
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
											url : "crs/insertNameGrid",
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
													"#reportingFINameGrid")
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
											url : "crs/updateNameGrid?id="
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
											url : "crs/deleteNameGrid?id="
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

$("#reportingFIIDGrid")
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
									url : 'crs/loadOrganisationGrid',
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
											url : "crs/insertOrganisationGrid",
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
													"#reportingFIIDGrid")
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
											url : "crs/updateOrganisationGrid?id="
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
											url : "crs/deleteOrganisationGrid?id="
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
							items : residentCountryCode ,
							valueField : "id",
							textField : "name",
						}, {
							type : "control"
						} ]
			});

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
				autoload : true,
				controller : {
					loadData : function() {
						var d = $.Deferred();

						$
								.ajax({
									type : 'GET',
									url : 'crs/loadReportingFiAddress',
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
													addNewAddressReportingFiClicked();
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
													reportingFiViewAddress(item.id);
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
													reportingFiEditAddress(item.id);
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
													reportingFiDeleteAddress(item.id);
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
function addNewAddressReportingFiClicked(){
	$("#addNewReportingFiAddress").modal('show');
	$("#addressFreeEntityTextField").hide();
	$("#addressFixEntityContent").hide();
	
	$.ajax({
        url: 'crs/resetReportingFiAddAddress',
        type: 'GET',
        async: false,
        data : $('#addNewReportingFi').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#addNewReportingFi").html();
            $('#addNewReportingFi').html('');
            $('#addNewReportingFi').html(htmlFiltered);
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });
	
}
function reportingFiViewAddress(id) {
	$.ajax({
		url : 'crs/reportingFiviewAddress?id=' + id,
		type : 'GET',
		async : false,
		cache : false,
		data : $('#viewReportingFiAdd').serialize(),
		success : function(data) {
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

function reportingFiEditAddress(id) {
	
	$(function() {
		var ctx = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			url : 'crs/reportingFieditAddress?id=' + id,
			async : false,
			cache : false,
			data : $('#editNewReportingfi').serialize(),
			success : function(data) {
				var htmlFiltered = $("<div>").html(data).find(
						"#editNewReportingfiAddress").html();
				$('#editNewReportingfiAddress').html('');
				$('#editNewReportingfiAddress').html(htmlFiltered);
				$("#editNewReportingfiAddress").modal('show');
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

function reportingFiDeleteAddress(id) {
	$.ajax({
		url : 'crs/reportingFideleteAddress?id=' + id,
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
function saveNewReportingFiAddressClicked(){
	//alert('saveAddress clicked......');
	$.ajax({
		url : 'crs/reportingFiinsertAddress',
		type : 'GET',
		data: $('#addNewReportingFi').serialize(),
		success : function(data) {
		   /*var object =  {"id":"1","addressType":"Address Free","countryCode":"MY"};
			console.log(object.addressType);
			console.log(object.id);
			console.log(object.countryCode);*/
			console.log(data.addressType);
			console.log(data.id);
			console.log(data.countryCode);
			$("#reportingFIAddressGrid").jsGrid("insertItem", data).done(function() {
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

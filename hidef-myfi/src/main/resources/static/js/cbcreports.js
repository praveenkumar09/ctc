$(document).ready(function() {
	
	 $('#viewConstEntityButton').hide();
	 $('#editConstEntityButton').hide();
		
	$('#addressTypeReports').change(function() 
			{ 
			   var val = $(this).val();
			   if(val == 1){
				   console.log("Value is 1");
				   $("#addressFreeReportsTextField").show();
				   $("#addressFixReportsContent").hide();
			   }else if(val == 2){
				   console.log("Value is 2");
				   $("#addressFreeReportsTextField").show();
				   $("#addressFixReportsContent").show();
			   }
			});	
	
	$("#addressFreeReportsTextField").hide();
	$("#addressFixReportsContent").hide();
	
	var object = {};
	$("#cbcReportsGrid")
	.jsGrid(
			{
				width : "205%",
				inserting : false,
				editing : false,
				sorting : false,
				paging : true,
				pageSize : 5,
				pageButtonCount : 5,
				//controller : object,
				data : cbcReports,
				datatype : 'json',
				autoload: true,
				invalidNotify : function(args) {
					
				}, controller: {

                    loadData: function(filter) {
                    	
//                    	return $.ajax({url:  "/admin/cbc/loadResidentCountryGrid",data:filter
//                        });
                    	
                    	
                        var d = $.Deferred();
                        $
                            .ajax({
                                type: 'GET',
                                url: 'cbc/loadCBCReports',
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
				fields : [ /*{
					name : "id",
					title : "id",
					type : "number",
					visible : false,
					width : 10,
					items : object.id
				}, */{
					title : "Document Reference Id",
					name : "documentRefId",
					type : "number",
					width : 150,
					items : cbcReports.documentRefId,
					visible : true
				}, {
					title : "Corr Message Reference Id",
					name : "corrMessageRefId",
					type : "number",
					width : 150,
					items : cbcReports.corrMessageRefId,
					visible : true
				}, {
					title : "Corr Document Reference Id",
					name : "corrDocRefId",
					type : "number",
					width : 150,
					items : cbcReports.corrDocRefId,
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
                           viewCBCReports(item);
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
                        editCBCReports(item);
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
                        	deleteNewAddressReportsClicked(item);
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
});

function addNewAddressCbcReportsClicked(){
	$("#addNewCbcReportsAddress").modal('show');
	$("#addressFreeReportsTextField").hide();
	$("#addressFixReportsContent").hide();
}

function saveNewReportsAddressClicked(){
	$.ajax({
		url : '/cbc/tabs/cbcReports/saveAddress',
		type : 'GET',
		success : function(data) {
		   var object =  {"id":"1","addressType":"Address Free","countryCode":"MY"};
			console.log(object.addressType);
			console.log(object.id);
			console.log(object.countryCode);
			$("#cbcReportsAddressGrid").jsGrid("insertItem", object).done(function() {
			    console.log("insertion completed");
			});
			
			
		},error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
	
}

function saveCBCReports(){
	
	var errorFlag = false; 
	errorFlag = validateCbcReports();
	 $("#cbcReportsError").empty();
	if(!errorFlag){
	
	 var form_data = $('#cbcreports').serialize();
		$.ajax({
			url : 'cbc/cbcReports/populateCBCReports',
			type : 'GET',
			data : form_data,
			success : function(data) {
				console.log(data);
				$("#cbcReportsGrid").jsGrid("insertItem", data).done(function() {
					console.log("insertion completed");
					showCbcReports(1,0,0);
				});
			},error : function(request, error) {
				alert("Request: " + JSON.stringify(request));
			}
		});	
	}
}

function editConstituentEntity(){
	var errorFlag = false; 
	var form_data = $('#cbcreports').serialize();
	errorFlag = validateCbcConstituentEntity(form_data);
	 $("#cbcReportsError").empty();
	if(!errorFlag){	
		 $("#issuedByError11").empty()
		$.ajax({
			url : 'cbc/cbcReports/populateEditedConsitituentEntityGrid',
			type : 'GET',
			data : form_data,
			 async: false,
			success : function(data) {
				console.log(data);
			},error : function(request, error) {
				alert("Request: " + JSON.stringify(request));
			}
		});	
		
		$.ajax({
	        url: 'cbc/reloadConstEntityAlone',
	        type: 'GET',
	        async: false,
	        data : $('#constituentEntities').serialize(),
	        success: function(response) {	        	
	        	var htmlFiltered = $("<div>").html(response).find("#constituentEntities").html();
	        	console.log(htmlFiltered);
	            $('#constituentEntities').html('');
	            $('#constituentEntities').html(htmlFiltered);
	            $('#viewConstEntityButton').hide();    
	            $('#editConstEntityButton').hide();
	            showAllReportsGrid();
	            
	        },
	        error: function(request, error) {
	            alert("Request: " + JSON.stringify(request));
	        }
	    });
	}
	
}


function saveConstituentEntity(){
	var errorFlag = false; 
	 var form_data = $('#cbcreports').serialize();
	errorFlag = validateCbcConstituentEntity(form_data);
	 $("#cbcReportsError").empty();
	if(!errorFlag){	
		 $("#issuedByError11").empty()
		$.ajax({
			url : 'cbc/cbcReports/populateCBCConsitituentEntityGrid',
			type : 'GET',
			data : form_data,
			 async: false,
			success : function(data) {
				console.log(data);
				$("#cbcReportsConstituentEntityGrid").jsGrid("insertItem", data).done(function() {
					console.log("insertion completed");
					//showCbcReports(1,0,0);		
				});
			},error : function(request, error) {
				alert("Request: " + JSON.stringify(request));
			}
		});	
		
		$.ajax({
	        url: 'cbc/reloadConstEntityAlone',
	        type: 'GET',
	        async: false,
	        data : $('#constituentEntities').serialize(),
	        success: function(response) {	        	
	        	var htmlFiltered = $("<div>").html(response).find("#constituentEntities").html();
	        	console.log(htmlFiltered);
	            $('#constituentEntities').html('');
	            $('#constituentEntities').html(htmlFiltered);
	            $('#viewConstEntityButton').hide(); 
	            $("#editConstEntityButton").hide();
	            showAllReportsGrid();
	            
	        },
	        error: function(request, error) {
	            alert("Request: " + JSON.stringify(request));
	        }
	    });
	}
}

function viewCBCReports(item){
	
	$
   .ajax({

       url: 'cbc/view/cbcReports?viewId='+item.id,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           showCbcReports(0,0,1);
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}


function doneViewreports(newForm,editForm,viewForm){
	$
   .ajax({

       url: 'cbc/viewReportsDone',
       type: 'GET',
       async: false,
       success: function(data) {
       	showCbcReports(1,0,0);       	
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });
}

function editCBCReports(item){
	
	$
   .ajax({

       url: 'cbc/edit/cbcReports?editId='+item.id,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           showCbcReports(0,1,0);
           return false;
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}


function doneEditReports(newForm,editForm,viewForm){
	
	var errorFlag = false; 
	errorFlag = validateCbcReports();
	
	
	if(!errorFlag){
	 var form_data = $('#cbcreports').serialize();
		$.ajax({
			url : 'cbc/cbcReports/populateEditedCBCReports',
			type : 'GET',
			data : form_data,
			success : function(data) {
				console.log(data);
				/*$("#cbcReportsGrid").jsGrid("insertItem", data).done(function() {
					console.log("insertion completed");*/
					showCbcReports(1,0,0);
			},error : function(request, error) {
				alert("Request: " + JSON.stringify(request));
			}
		});	
	}
} 
function addNewAddressCbcReportsClicked(){
	$("#addNewCbcReportsAddress").modal('show');
	$("#addressFreeEntityTextField").hide();
	$("#addressFixEntityContent").hide();
	
	$.ajax({
        url: 'cbc/resetCbcReportdAddAddress',
        type: 'GET',
        async: false,
        data : $('#cbcReportsaddress').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#addNewCbcReportsAddress").html();
            $('#addNewCbcReportsAddress').html('');
            $('#addNewCbcReportsAddress').html(htmlFiltered);
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });
	
}

function deleteNewAddressReportsClicked(item){
	$("#deleteConfirmationBox").modal('show');
	$("#idToDelete").html(item.id);
	$("#formId").html(7);
}

function proceedDelete(){
	var idToDelete = $("#idToDelete").text();
	var formToDelete = $("#formId").text();
	//alert("idToDelete =====>"+idToDelete);
	
	if(formToDelete == 7){
	$
   .ajax({

       url: 'cbc/cbcReports/populateDeletedCBCReports?deleteId='+idToDelete,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           showCbcReports(1,0,0);
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
function saveNewCbcReportsAddressClicked(){
	//alert('saveAddress clicked......');
	$.ajax({
		url : 'cbc/cbcReporstinsertAddress',
		type : 'GET',
		data: $('#cbcReportsaddress').serialize(),
		success : function(data) {
		   /*var object =  {"id":"1","addressType":"Address Free","countryCode":"MY"};
			console.log(object.addressType);
			console.log(object.id);
			console.log(object.countryCode);*/
			console.log(data.addressType);
			console.log(data.id);
			console.log(data.countryCode);
			$("#cbcReportsAddressGrid").jsGrid("insertItem", data).done(function() {
			    console.log("insertion completed");
			});
			
			
		},error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
	
}

function validate1(evt) {
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode( key );
	var regex = /[0-9]|\./;
	if( !regex.test(key) && key !='-' && key !='+')  {
	theEvent.returnValue = false;
	if(theEvent.preventDefault) theEvent.preventDefault();
	}
}
function validate(evt) {
	
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	key = String.fromCharCode( key );
	var regex = /[0-9]|\./;
	if( !regex.test(key) ) {
	
	theEvent.returnValue = false;
	if(theEvent.preventDefault) theEvent.preventDefault();
	}
}


function validateCbcConstituentEntity(form_data){
	var errorFlag=false;
/*	if($("#tin11").val() == ''){
	   	 $("#tinError11").empty().append("TIN not empty!");
	   	 errorFlag = true;	
 }else{
 	$("#tinError11").empty();
}
 
 if($("#tintype11").val() == ''){
	   	 $("#tintypeError11").empty().append("TIN Type not empty!");
	   	 errorFlag = true;	
 }else{
 	$("#tintypeError11").empty();
 }*/
 
/* var issuedBy = $('#issuedBy11').val();
 if(issuedBy =='0'){
 	 $("#issuedByError11").empty().append("TIN Issued By not empty!");
	   	 errorFlag = true;
 }else{
 	$("#issuedByError11").empty();
 }*/
 
 $.ajax({
		url : 'cbc/cbcReports/validateConstiituentEntityGrids',
		type : 'GET',
		data : form_data,
		 async: false,
		success : function(data) {
			if(data == "true"){
				 $("#issuedByError11").empty().append("Please fill up all the mandatory Grids!.");
				 errorFlag = true;
			}else{
				$("#issuedByError11").empty();
			}
		},error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});	
 
 return errorFlag;
}

function validateCbcReports(){
	var errorFlag=false;
	if($("#documentReferenceId11").val() == ''){
	   	 $("#documentRefIdError11").empty().append("Document Reference Id not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#documentRefIdError11").empty();
   }
   var docTypeIndic = $('#docTypeIndic').val();
   if(docTypeIndic =='0'){
   	 $("#documentTypeIndicatorError").empty().append("Document Type Indicator not empty!");
	   	 errorFlag = true;
   }else{
   	$("#documentTypeIndicatorError").empty();
   } 
   
   var residentCountry = $('#residentCountryCode').val();
   if(residentCountry =='0'){
   	 $("#residentCountryCodeError").empty().append("Resident Country Code not empty!");
	   	 errorFlag = true;
   }else{
   	$("#residentCountryCodeError").empty();
   }
   
   if($("#nbEmployees").val() == ''){
	   	 $("#nbEmployeesError").empty().append("Nb Employees not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#nbEmployeesError").empty();
   }
   var unrelatedCur = $('#unrelatedCountryCode').val();
   if(unrelatedCur =='0'){
   	 $("#unrelateCurrCodeError").empty().append("Currency Code not empty!");
	   	 errorFlag = true;
   }else{
   	$("#unrelateCurrCodeError").empty();
   }
   
   if($("#unrelatedAmount").val() == ''){
	   	 $("#unrelatedAmountError").empty().append("Amount not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#unrelatedAmountError").empty();
   }
   
   var relatedCur = $('#relatedCurrencyCode').val();
   if(relatedCur =='0'){
   	 $("#relatedCurrencyCodeError").empty().append("Currency Code not empty!");
	   	 errorFlag = true;
   }else{
   	$("#relatedCurrencyCodeError").empty();
   }
   
   if($("#relatedAmount").val() == ''){
	   	 $("#relatedAmountError").empty().append("Amount not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#relatedAmountError").empty();
   }
   
   var totalCur = $('#totalCurrencyCode').val();
   if(totalCur =='0'){
   	 $("#totalCurrencyCodeError").empty().append("Currency Code not empty!");
	   	 errorFlag = true;
   }else{
   	$("#totalCurrencyCodeError").empty();
   }
   
   if($("#totalAmount").val() == ''){
	   	 $("#totalAmountError").empty().append("Amount not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#totalAmountError").empty();
   }
   
   var profitorloss = $('#profitOrLossCurrencyCode').val();
   if(profitorloss =='0'){
   	 $("#profitOrLossCurrencyCodeError").empty().append("Currency Code not empty!");
	   	 errorFlag = true;
   }else{
   	$("#profitOrLossCurrencyCodeError").empty();
   }
   
   if($("#profitOrLossAmount").val() == ''){
	   	 $("#profitOrLossAmountError").empty().append("Amount not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#profitOrLossAmountError").empty();
   }
   
   
   var taxpaid = $('#taxPaidCurrencyCode').val();
   if(taxpaid =='0'){
   	 $("#taxPaidCurrencyCodeError").empty().append("Currency Code not empty!");
	   	 errorFlag = true;
   }else{
   	$("#taxPaidCurrencyCodeError").empty();
   }
   
   if($("#taxPaidAmount").val() == ''){
	   	 $("#taxPaidAmountError").empty().append("Amount not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#taxPaidAmountError").empty();
   }
   
   var taxaccured = $('#taxAccruedCurrencyCode').val();
   if(taxaccured =='0'){
   	 $("#taxAccruedCurrencyCodeError").empty().append("Currency Code not empty!");
	   	 errorFlag = true;
   }else{
   	$("#taxAccruedCurrencyCodeError").empty();
   }
   
   if($("#taxAccruedAmount").val() == ''){
	   	 $("#taxAccruedAmountError").empty().append("Amount not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#taxAccruedAmountError").empty();
   }
   
   var capital = $('#capitalCurrencyCode').val();
   if(capital =='0'){
   	 $("#capitalCurrencyCodeError").empty().append("Currency Code not empty!");
	   	 errorFlag = true;
   }else{
   	$("#capitalCurrencyCodeError").empty();
   }
   
   if($("#capitalAmount").val() == ''){
	   	 $("#capitalAmountError").empty().append("Amount not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#capitalAmountError").empty();
   }
   
   var earnings = $('#earningsCurrencyCode').val();
   if(capital =='0'){
   	 $("#earningsCurrencyCodeError").empty().append("Currency Code not empty!");
	   	 errorFlag = true;
   }else{
   	$("#earningsCurrencyCodeError").empty();
   }
   
   if($("#earningsAmount").val() == ''){
	   	 $("#earningsAmountError").empty().append("Amount not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#earningsAmountError").empty();
   }
   
   var asset = $('#assetsCurrencyCode').val();
   if(asset =='0'){
   	 $("#assetsCurrencyCodeError").empty().append("Currency Code not empty!");
	   	 errorFlag = true;
   }else{
   	$("#assetsCurrencyCodeError").empty();
   }
   
   if($("#assetsAmount").val() == ''){
	   	 $("#assetsAmountError").empty().append("Amount not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#assetsAmountError").empty();
   }
   /*if($("#tin11").val() == ''){
	   	 $("#tinError11").empty().append("TIN not empty!");
	   	 errorFlag = true;	
  }else{
  	$("#tinError11").empty();
 }
  
  if($("#tintype11").val() == ''){
	   	 $("#tintypeError11").empty().append("TIN Type not empty!");
	   	 errorFlag = true;	
  }else{
  	$("#tintypeError11").empty();
  }
  
  var issuedBy = $('#issuedBy11').val();
  if(issuedBy =='0'){
  	 $("#issuedByError11").empty().append("TIN Issued By not empty!");
	   	 errorFlag = true;
  }else{
  	$("#issuedByError11").empty();
  }*/
  return errorFlag;
}

function currencyValidation(obj){
	/*alert('@@@@@@@@@@@@@@@');*/
	//validate(evt);
    $(obj).priceFormat({
    	prefix: '',
    	centsSeparator: '',
        thousandsSeparator: '',
        limit: 18,
        allowNegative: true,
		insertPlusSign: true
    }); 	
} 


$(document).ready(function() {
	
	 /*$.ajax({
	        url: 'cbc/getdocrefid?type=E',
	        type: 'GET',
	        async: false,
	        success: function(response) {
	        	alert('@@@@@@@@@@@@'+response)
	        	$("#documentReferenceId").val(response);
	            return false;


	        },
	        error: function(request, error) {
	            alert("Request: " + JSON.stringify(request));
	        }
	    });*/

		
	$('#addressTypeReportingEntity').change(function() 
			{ 
			   var val = $(this).val();
			   if(val == 1){
				   console.log("Value is 1");
				   $("#addressFreeEntityTextField").show();
				   $("#addressFixEntityContent").hide();
			   }else if(val == 2){
				   console.log("Value is 2");
				   $("#addressFreeEntityTextField").show();
				   $("#addressFixEntityContent").show();
			   }
			});	
	
	$("#addressFreeEntityTextField").hide();
	$("#addressFixEntityContent").hide();
});

function addNewAddressReportingEntityClicked(){
	$("#addNewReportingEntityAddress").modal('show');
	$("#addressFreeEntityTextField").hide();
	$("#addressFixEntityContent").hide();
	
	$.ajax({
        url: 'cbc/resetReportingEntityAddAddress',
        type: 'GET',
        async: false,
        data : $('#reportingfiaddress').serialize(),
        success: function(response) {
        	var htmlFiltered = $("<div>").html(response).find("#reportingfiaddress").html();
            $('#reportingfiaddress').html('');
            $('#reportingfiaddress').html(htmlFiltered);
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });
	
}


function saveNewReportingEntityAddressClicked(){
	//alert('saveAddress clicked......');
	$.ajax({
		url : 'cbc/reportEntityinsertAddress',
		type : 'GET',
		data: $('#reportingfiaddress').serialize(),
		success : function(data) {
		   /*var object =  {"id":"1","addressType":"Address Free","countryCode":"MY"};
			console.log(object.addressType);
			console.log(object.id);
			console.log(object.countryCode);*/
			console.log(data.addressType);
			console.log(data.id);
			console.log(data.countryCode);
			$("#reportingEntityAddressGrid").jsGrid("insertItem", data).done(function() {
			    console.log("insertion completed");
			});
			
			
		},error : function(request, error) {
			alert("Request: " + JSON.stringify(request));
		}
	});
	
}

function validateReportingEntity(){
	
	var errorFlag = false;
	
	if($("#tin").val() == ''){
	   	 $("#tinError").empty().append("TIN not empty!");
	   	 errorFlag = true;	
	 }else{
	 	$("#tinError").empty();
	}
 
	if($("#tintype").val() == ''){
	   	 $("#tintypeError").empty().append("TIN Type not empty!");
	   	 errorFlag = true;	
	}else{
		$("#tintypeError").empty();
	}
 
	var issuedBy = $('#issuedBy').val();
	if(issuedBy =='0'){
		$("#issuedByError").empty().append("TIN Issued By not empty!");
	   	 errorFlag = true;
	}else{
		$("#issuedByError").empty();
	}
	
	var reportingRole = $('#reportingRole').val();
	if(reportingRole =='0'){
		$("#reportingRoleError").empty().append("Reporting Role not empty!");
	   	 errorFlag = true;
	}else{
		$("#reportingRoleError").empty();
	}
	
	
	return errorFlag;
}


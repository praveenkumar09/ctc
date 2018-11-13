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

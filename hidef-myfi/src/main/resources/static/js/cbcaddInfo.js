$(document).ready(function() {
	console.log("Inside here");
	var cbcAddionalInfo = {};
	
	$("#cbcAddInfoGrid")
	.jsGrid(
			{
				width : "205%",
				inserting : false,
				editing : false,
				sorting : false,
				paging : true,
				pageSize : 5,
				pageButtonCount : 5,
				controller : cbcAddionalInfo,
				datatype : 'json',
				autoload: true,
				invalidNotify : function(args) {
					
				}, controller: {

                    loadData: function(filter) {
                      var d = $.Deferred();
                        $
                            .ajax({
                                type: 'GET',
                                url: 'cbc/loadCBCAddInfo',
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
				fields : [{
					title : "Document Reference Id",
					name : "documentReferenceId",
					type : "number",
					width : 150,
					items : cbcAddionalInfo.documentReferenceId,
					visible : true
				}, {
					title : "Corr Message Reference Id",
					name : "corrMessageRefId",
					type : "number",
					width : 150,
					items : cbcAddionalInfo.corrMessageRefId,
					visible : true
				}, {
					title : "Corr Document Reference Id",
					name : "corDocumentRefId",
					type : "number",
					width : 150,
					items : cbcAddionalInfo.corDocumentRefId,
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
	                          e
	                                .stopPropagation();
	                        	console.log(item);
	                           viewCBCAddInfo(item);
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
	                        	 editCBCAddInfo(item);
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
	                        	deleteNewAddInfoClicked(item);
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


function saveAddInfo(){
	var errorFlag = false; 
	errorFlag = validateAdditionalInfo();
	 $("#cbcReportsError").empty();
	if(!errorFlag){
	
	 var form_data = $('#addtionalinfo').serialize();
		$.ajax({
			url : 'cbc/populateAddInfoGrid',
			type : 'GET',
			data : form_data,
			success : function(data) {
				console.log(data);
				$("#cbcAddInfoGrid").jsGrid("insertItem", data).done(function() {
					console.log("insertion completed");
					showCbcAddInfo(1,0,0);
				});
			},error : function(request, error) {
				alert("Request: " + JSON.stringify(request));
			}
		});	
	}
}

function viewCBCAddInfo(item){
	$
   .ajax({

       url: 'cbc/view/cbcAddInfo?viewId='+item.id,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           showCbcAddInfo1(0,0,1);
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}

function doneViewAddInfo(newForm,editForm,viewForm){
	$
   .ajax({

       url: 'cbc/viewAddInfoDone',
       type: 'GET',
       async: false,
       success: function(data) {
    	   showCbcAddInfo1(1,0,0);       	
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });
}

function editCBCAddInfo(item){
	$
   .ajax({

       url: 'cbc/edit/cbcAddInfo?editId='+item.id,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           showCbcAddInfo1(0,1,0);
           return false;
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}



function doneEditAddInfo(newForm,editForm,viewForm){
	 var form_data = $('#addtionalinfo').serialize();
		$.ajax({
			url : 'cbc/populateEditedCBCAddInfo',
			type : 'GET',
			data : form_data,
			success : function(data) {
				console.log(data);
				/*$("#cbcReportsGrid").jsGrid("insertItem", data).done(function() {
					console.log("insertion completed");*/
				showCbcAddInfo1(1,0,0);
			},error : function(request, error) {
				alert("Request: " + JSON.stringify(request));
			}
		});	
} 

function deleteNewAddInfoClicked(item){
	$("#deleteConfirmationBox").modal('show');
	$("#idToDelete").html(item.id);
	$("#formId").html(8);
}

function proceedDelete(){
	var idToDelete = $("#idToDelete").text();
	var formToDelete = $("#formId").text();
	//alert("idToDelete =====>"+idToDelete);
	
	if(formToDelete == 8){
	$
   .ajax({

       url: 'cbc/populateDeletedCBCAddInfo?deleteId='+idToDelete,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           showCbcAddInfo(1,0,0);
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

function validateAdditionalInfo(){
	var errorFlag=false;
	if($("#documentReferenceId22").val() == ''){
	   	 $("#documentReferenceIdError22").empty().append("Document Reference Id not empty!");
	   	 errorFlag = true;	
   }else{
   	$("#documentReferenceIdError22").empty();
   }
    var documentTypeIndic = $('#docTypeIndic22').val();
	if(documentTypeIndic =='0'){
		$("#documenttypeindicError22").empty().append("Document Type Indicator not empty!");
	   	 errorFlag = true;
	}else{
		$("#documenttypeindicError22").empty();
	}
	
	if($("#otherInfo22").val() == ''){
	   	 $("#otherInfoError22").empty().append("Other Info not empty!");
	   	 errorFlag = true;	
	}else{
		$("#otherInfoError22").empty();
	}
	return errorFlag;
	
}

function saveAllCBCData(){
	//alert('@@@@@@@@@@@@@@');
	$
   .ajax({

       url: 'cbc/save',
       type: 'POST',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           //showCbcAddInfo(0,0,1);
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}





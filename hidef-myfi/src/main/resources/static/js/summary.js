$(document).ready(function() {
	  var object = {};
	 /* var ctx = "${pageContext.request.contextPath}";
	  alert('@@@@@@@@@@@@'+ctx)*/

      $("#summaryGrid")
          .jsGrid({
              width: "205%",
              inserting: false,
              editing: false,
              sorting: false,
              paging: true,
              pageSize: 6,
              pageButtonCount: 5,
              autoload: true,
              controller: {
                  loadData: function() {
                      var d = $.Deferred();

                      $
                          .ajax({
                              type: 'GET',
                              url: 'cbc/summary',
                              mimeType: 'application/json',
                              contentType: "application/json; charset=utf-8",
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
            /*  controller: object,*/
              datatype: 'json',
              invalidNotify: function(args) {

              },
              fields: [{
                      name: "id",
                      title: "id",
                      type: "text",
                      visible: false,
                      width: 10
                      /*,
                      items: object.id*/
                  },
                  {
                      title: "Sending Country",
                      name: "sendingCountry",
                      type: "text",
                      width: 10
                      ,
                      /*items: object.addressType,*/
                      visible: true
                  },
                  {
                      title: "Message Type",
                      name: "messageType",
                      type: "text",
                      width: 10,
                      /*,
                      items: object.countryCode,*/
                      visible: true
                  },
                  {
                      title: "PackageName",
                      name: "filename",
                      type: "text",
                      width: 50,
                      /*,
                      items: object.countryCode,*/
                      visible: true
                  },
                  {
                      title: "Created Date Time",
                      name: "createdDateTime",
                      type: "text",
                      width: 30,
                      /*,
                      items: object.countryCode,*/
                      visible: true
                  },
                     {
                      name: "button",
                      width: 40,
                      align:"center",
                      headerTemplate: function() {
                          return $("<button>")
                              .attr("type",
                                  "button")
                              .attr("class",
                                  "btn btn-success btn-sm")
                              .text(
                                  "Import Excel here!")
                              .on(
                                  "click",
                                  function() {
                                	  clickImport();
                                  }) 
                      },
                      itemTemplate: function(
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
                                      e.stopPropagation();
                                      viewCBCSummaryGrid(item);
                                      
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
                                     /* alert("ID: " +
                                          item.id);*/
                                      e.stopPropagation();
                                      editCBCSummaryGrid(item);
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
                                      //alert("ID: " +
                                          //item.id);
                                      e.stopPropagation();
                                      deleteCBCSummaryClicked(item);
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
      
      
      
      $("#crssummaryGrid")
      .jsGrid({
          width: "205%",
          inserting: false,
          editing: false,
          sorting: false,
          paging: true,
          pageSize: 6,
          pageButtonCount: 5,
          autoload: true,
          controller: {
              loadData: function() {
                  var d = $.Deferred();

                  $
                      .ajax({
                          type: 'GET',
                          url: 'crs/summary',
                          mimeType: 'application/json',
                          contentType: "application/json; charset=utf-8",
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
        /*  controller: object,*/
          datatype: 'json',
          invalidNotify: function(args) {

          },
          fields: [{
                  name: "id",
                  title: "id",
                  type: "text",
                  visible: false,
                  width: 10
                  /*,
                  items: object.id*/
              },
              {
                  title: "Sending Country",
                  name: "sendingCountry",
                  type: "text",
                  width: 10
                  ,
                  /*items: object.addressType,*/
                  visible: true
              },
              {
                  title: "Message Type",
                  name: "messageType",
                  type: "text",
                  width: 10,
                  /*,
                  items: object.countryCode,*/
                  visible: true
              },
              {
                  title: "PackageName",
                  name: "filename",
                  type: "text",
                  width: 50,
                  /*,
                  items: object.countryCode,*/
                  visible: true
              },
              {
                  title: "Created Date Time",
                  name: "createdDateTime",
                  type: "text",
                  width: 30,
                  /*,
                  items: object.countryCode,*/
                  visible: true
              },
                 {
                  name: "button",
                  width: 40,
                  align:"center",
                  headerTemplate: function() {
                      return $("<button>")
                          .attr("type",
                              "button")
                          .attr("class",
                              "btn btn-success btn-sm")
                          .text(
                              "Import Excel here!")
                          .on(
                              "click",
                              function() {
                            	  clickImport();
                              }) 
                  },
                  itemTemplate: function(
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
                                  e.stopPropagation();
                                  viewCRSSummaryGrid(item);
                                  
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
                                 /* alert("ID: " +
                                      item.id);*/
                                  e.stopPropagation();
                                  editCBCSummaryGrid(item);
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
                                  //alert("ID: " +
                                      //item.id);
                                  e.stopPropagation();
                                  deleteCBCSummaryClicked(item);
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


function clickImport(){
	$
	   .ajax({

	       url: 'import/excel',
	       type: 'GET',
	       async: false,
	       success: function(response) {
	    	   $("#importpopup").modal('show');	           
	       },
	       error: function(
	           request,
	           error) {
	           console.log(error);
	       }
	   });
	
	
}
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
function viewCBCSummaryGrid(item){
	debugger;
	$
   .ajax({

       url: 'cbc/viewSummaryGrid?id='+item.hrdId,
       type: 'GET',
       async: false,
       success: function(response) {
           console
               .log("data ====>"+response);
           //newCBCOnClick();
           viewCBCOnClick();
          // $('#cbcmetadata *').prop('disabled',true);
           
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}

function editCBCSummaryGrid(item){
	$
   .ajax({

       url: 'cbc/editSummaryGrid?id='+item.hrdId,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           newCBCOnClick();
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}

function deleteCBCSummaryGrid(item){
	$
   .ajax({

       url: 'cbc/deleteSummaryGrid?id='+item.hrdId,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
          // newCBCOnClick();
           $("#summaryGrid").jsGrid("loadData");
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}
function proceedDelete(){
	var idToDelete = $("#idToDelete").text();
	var formToDelete = $("#formId").text();
	//alert("idToDelete =====>"+idToDelete);
	/*if(formToDelete == 7){*/
	$
   .ajax({

       url: 'cbc/deleteSummaryGrid?id='+idToDelete,
       type: 'GET',
       async: false,
       success: function(data) {
           console
               .log("data ====>"+data);
           //showCbcReports(1,0,0);
           $("#summaryGrid").jsGrid("loadData");
           return false;
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });
	/*}*/
}

function deleteCBCSummaryClicked(item){
	$("#deleteConfirmationBox").modal('show');
	$("#idToDelete").html(item.hrdId);
	$("#formId").html(7);
}

function viewCRSSummaryGrid(item){
	debugger;
	$
   .ajax({

       url: 'crs/viewSummaryGrid?id='+item.hrdId,
       type: 'GET',
       async: false,
       success: function(response) {
           console
               .log("data ====>"+response);
           //newCBCOnClick();
           newCRSOnClick();
          // $('#cbcmetadata *').prop('disabled',true);
           
       },
       error: function(
           request,
           error) {
           console.log(error);
       }
   });

}


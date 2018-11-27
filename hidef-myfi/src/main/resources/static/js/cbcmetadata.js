$(document).ready(function() {

	
    var recievingCountry = $("#recievingCountry").val();
    recievingCountry = $.parseJSON(recievingCountry);
    $.fn.datepicker.defaults.format = "yyyy-mm-dd";
    $.fn.datepicker.defaults.autoclose = true;
    var datepicker = $.fn.datepicker.noConflict(); // return $.fn.datepicker to previously assigned value
    $.fn.bootstrapDP = datepicker; 
   /* $('#reportingPeriod').datepicker({
    	 autoclose: true,
    	 todayHighlight: true
    });*/

     
     
     $("#metaDataReceivingCountryList")
     .jsGrid({
         width: "205%",
         sorting: true,
         paging: true,
         pageSize: 5,
         pageButtonCount: 5,
         controller: recievingCountry,
         autoload: true,
         controller: {

             loadData: function() {
                 var d = $.Deferred();
                 $
                     .ajax({
                         type: 'GET',
                         url: 'metadata/loadReceivingCountryList',
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
                 /*return
                 [{"id":"1","residentCountryCode":6},{"id":"1","residentCountryCode":6}];*/
             },
         },
         invalidNotify: function(args) {/*
             $("#validateTextHere").text("");
             $("#validateTextHere")
                 .text(
                     "Please fill in all the mandatory fields");
             $('#crsNameModal').modal('show');
         */},
         fields: [
             {
                 title: "Receiving Country",
                 name: "recievingCountry",
                 type: "select",
                 items: recievingCountry,
                 valueField: "id",
                 textField: "name",
             },
         ]
     });
	
	
});


function generate(n) {
    var add = 1, max = 12 - add;   // 12 is the min safe number Math.random() can generate without it starting to pad the end with zeros.   

    if ( n > max ) {
            return generate(max) + generate(n - max);
    }

    max        = Math.pow(10, n+add);
    var min    = max/10; // Math.pow(10, n) basically
    var number = Math.floor( Math.random() * (max - min + 1) ) + min;

    return ("" + number).substring(add); 
}

function generateTimestramp(){
	var d = new Date();
	var n = d.toISOString();
	$("#formCreationTimeStamp").val(n);
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
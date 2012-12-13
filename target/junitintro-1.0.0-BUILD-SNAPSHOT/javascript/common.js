$(document).ready(function(){
	
	refreshResults();
	
	$("#addUserForm").submit(function(ev) {
		
		// prevent default posting of form
	    ev.preventDefault();
		
		var formData = $(this).serialize();
		
		$.ajax({
	        url: "remote/addUser",
	        type: "post",
	        data: formData,
	        // callback handler that will be called on success
	        success: function(response, textStatus, jqXHR){
	            if(response == true) {
	            	refreshResults();
	            }else {
	            	alert("Error occured");
	            }
	        },
	        // callback handler that will be called on error
	        error: function(jqXHR, textStatus, errorThrown){
	            // log the error to the console
	            console.log(
	                "The following error occured: "+
	                textStatus, errorThrown
	            );
	        }
	    });
		
	});
	
	function refreshResults() {
		$.ajax({
	        url: "remote/getAllUsers",
	        type: "get",
	        // callback handler that will be called on success
	        success: showData,
	        // callback handler that will be called on error
	        error: function(jqXHR, textStatus, errorThrown){
	            // log the error to the console
	            console.log(
	                "The following error occured: "+
	                textStatus, errorThrown
	            );
	        }
	    });
	}
	
	function showData(data) {
		$('#contactsTable > tbody').html("");
		$.each(data, function(i, item) {
			$('#contactsTable > tbody:last').append("<tr><td>"+item.FIRST_NAME+"</td><td>"+item.LAST_NAME+"</td><td>"+item.EMAIL+"</td></tr>");;
		}); 
	}
	
	$('#searchString').keyup(function() {
		  var val = {"searchString":$(this).val()};
		  
		  $.ajax({
		        url: "remote/getUser",
		        type: "post",
		        data: val,
		        // callback handler that will be called on success
		        success: showData,
		        // callback handler that will be called on error
		        error: function(jqXHR, textStatus, errorThrown){
		            // log the error to the console
		            console.log(
		                "The following error occured: "+
		                textStatus, errorThrown
		            );
		        }
		    });
		  
	});
	
});
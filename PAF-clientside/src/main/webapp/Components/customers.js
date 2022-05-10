$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 


///SAVE Function ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateCustomerForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If it is valid------------------------  
	var t = ($("#hidcustomerIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "CustomerAPI",
		type : t,
		data : $("#formCustomer").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onCustomerSaveComplete(response.responseText, status);
			
			
		}
	});
}); 

function onCustomerSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divCustomersGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidcustomerIDSave").val("");
	$("#formCustomer")[0].reset();
}



//UPDATE Function========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidcustomerIDSave").val($(this).closest("tr").find('#hidcustomerIDUpdate').val());     
	$("#username").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#useraddress").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#userphonenumber").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#userusername").val($(this).closest("tr").find('td:eq(3)').text());
	$("#userpassword").val($(this).closest("tr").find('td:eq(4)').text());  
	

});

//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "CustomerAPI",
		type : "DELETE",
		data : "userid=" + $(this).data("userid"),
		dataType : "text",
		complete : function(response, status)
		{
			onCustomerDeletedComplete(response.responseText, status);
		}
	});
});

function onCustomerDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divCustomersGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateCustomerForm() {  
	// Name 
	if ($("#username").val().trim() == "")  {   
		return "Please insert your Name.";  
		
	} 
	
	 // Address 
	if ($("#useraddress").val().trim() == "")  {   
		return "Please insert your Address.";  
		
	} 
	 
	
	// Phonenumber 
	if ($("#userphonenumber").val().trim() == "")  {   
		return "Please insert your Phone number.";  
		
	}  
	var phone = $("#userphonenumber").val().trim(); 
	if (!$.isNumeric(phone)) { 
 		return "Insert a valid phone number"; 
 	}
	
	// Date  
	if ($("#userusername").val().trim() == "")  {   
		return "Please insert a Username.";  
		
	} 
	
	// Date  
	if ($("#userpassword").val().trim() == "")  {   
		return "Please insert a Password";  
		
	}
		 
	 return true; 
	 
}
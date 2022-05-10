<%@page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Profile</title>
<link rel="stylesheet" href="views/bootstrap.min.css">

<script src="components/jquery-3.6.0.min.js"></script>
<script src="components/customers.js"></script>

</head>
<body>
<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Customer Profile</h1>        
				
				<form id="formCustomer" name="formCustomer" method="post" action="researchProject.jsp">  
					Customer Name:  
					<input id="customerName" name="customerName" type="text" class="form-control form-control-sm">  
					
					<br> 
					Customer Address:  
					<input id="customerAddress" name="customerAddress" type="text" class="form-control form-control-sm">  
					
					<br>
					Customer Phone number:  
					 <input id="customerPhone" name="customerPhone" type="text" class="form-control form-control-sm">  
					 
					<br> 
					Customer User name:  
					<input id="customerUname" name="customerUname" type="text" class="form-control form-control-sm">  
					 
					<br> 
					Customer password:  
					<input id="customerPwd" name="customerPwd" type="password" class="form-control form-control-sm">
				
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidcustomerIDSave" name="hidcustomerIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="#divCustomersGrid">   
					<%    
						Customer cObj = new Customer();
						out.print(cObj.readCustomer());   
					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 
</body>
</html>
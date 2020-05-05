<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="model.Doctor"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
<meta charset="ISO-8859-1">


</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-6">

				<h1>Doctor Management</h1>

				<form id="formDoc" name="formDoc">
							
  				Doctor Id :
					<input id="id" name="id" type="text" class="form-control form-control-sm" readonly> 
					
					<br> Doctor Name :
					<input id="name" name="name" type="text" class="form-control form-control-sm">
					
					<br> Doctor Address : 
					<input id="address" name="address" type="text" class="form-control form-control-sm"> 
					
					<br> Location : 
					<input id="location" name="location" type="text" class="form-control form-control-sm"> 
					
					<br> Doctor Mobile : 
					<input id="mobile" name="mobile" type="text" class="form-control form-control-sm"> 
					
					<br> 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> <input type="hidden"id="hidItemIDSave" name="hidItemIDSave" value="">
					
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>

				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divItemsGrid">
					<%
						Doctor itemobj = new Doctor();
						out.print(itemobj.readDoctor());
					%>
				</div>

			</div>
		</div>
	</div>


</body>
</html>
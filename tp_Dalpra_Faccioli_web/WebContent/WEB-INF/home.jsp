<%@page import="java.util.ArrayList"%>
<%@page import="entity.Persona"%>
<%@page import="entity.Reserva"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestión de Reservas</title>
</head>
<link href="style/mdb.min.css" rel="stylesheet">

<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/home.css" rel="stylesheet">
<body>

<div class="container">
	<div class="row">
		<div class="col-md-12 text-center">
			<h3>Usuario Actual: <%=((Persona)session.getAttribute("user")).getApellido() %>, <%=((Persona)session.getAttribute("user")).getNombre() %>. </h3>
		</div>
	</div>
	<div class="row">
			
			<div class="col-md-2 col-sm-1"></div>
			<form  name="personas" action="personas">			
		        <div class="col-md-4 col-sm-5" >
		        <button class="btn btn-info btn-lg btn-block"><h4 style="padding:30px; margin:0px;">Personas</h4></button> 
		        </div>         
	        </form>
	        
	        <form  name="reservas" action="reservas">			
		        <div class="col-md-4 col-sm-5" >
		        <button class="btn btn-warning btn-lg btn-block"><h4 style="padding:30px; margin:0px;">Reservas</h4></button> 
		        </div>         
	        </form>
	    </div>
	    <div class="row"> 
	   		<div class="col-md-2 col-sm-1"></div>  
	        <form  name="elementos" action="buscarelementos" >			
		        <div class="col-md-4 col-sm-5" >
		        <button class="btn btn-success btn-lg btn-block"><h4 style="padding:30px; margin:0px;">Elementos</h4></button> 
		        </div>         
	        </form>
	         <form  name="tipoElementos" action="tipoElementos" >			
		        <div class="col-md-4 col-sm-5" >
		        <button class="btn btn-danger btn-lg btn-block"><h4 style="padding:30px; margin:0px;">Tipo de Elementos</h4></button> 
		        </div>         
	        </form>
	       
    	</div>
</div>
	

</body>
<script src="style/jquery.min.js"></script>
	<script src="style/mdb.min.js"></script>
	<script src="style/bootstrap.min.js"></script>
</html>
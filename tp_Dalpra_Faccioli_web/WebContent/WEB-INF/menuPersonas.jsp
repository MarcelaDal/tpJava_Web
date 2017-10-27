<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="entity.Persona"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<link href="style/mdb.min.css" rel="stylesheet">

<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/home.css" rel="stylesheet">
<body>
	<div class="row">
	<div class="col-md-2"></div>
    <form class="col-md-6" name="personas" method="post">
      <div class="row">
        <div class="input-field col-md-12">
          <input disabled id="id" type="text" >
          <label for="id">Id</label>
        </div>
       
      </div>
      <div class="row">
        <div class="input-field col-md-12">
          <input id="dniInput" type="number" class="validate">
          <label for="dniInput">DNI</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col-md-12">
          <input id="nameInput" type="text" class="validate">
          <label for="nameInput">Nombre</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col-md-12">
          <input id="lastnameInput" type="text" class="validate">
          <label for="lastnameInput">Apellido</label>
        </div>
        </div>
    <button type=submit></button>
    </form>
  </div>
	

</body>
<script src="style/jquery.min.js"></script>
	<script src="style/mdb.min.js"></script>
	<script src="style/bootstrap.min.js"></script>
</html>
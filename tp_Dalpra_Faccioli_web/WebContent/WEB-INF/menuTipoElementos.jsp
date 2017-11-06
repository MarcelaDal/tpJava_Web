<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="entity.TipoElementos"%>
    <%@page import="controlers.CtrlABMCTipoElementos"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <script type="text/javascript">
    	function submitForm(met) {
    		document.myForm.action=met;
        }
    </script>
<title>Gestión Tipos de Elementos</title>
</head>
<link href="style/mdb.min.css" rel="stylesheet">
<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/home.css" rel="stylesheet">

<body>
<form class="col-md-6" name="myForm" action="" method="post">
      <div class="row">
        <div class="input-field col-md-6">
          <input disabled id="idInput" name="idInput" type="text" >
          <label for="id">Id</label>
        </div>       
      </div>      
      <div class="row">
        <div class="input-field col-md-6">
          <input name="nameInput" id="nameInput" type="text" class="validate">
          <label for="nameInput">Nombre</label>
        </div>
      </div>
       <div class="row">
        <div class="input-field col-md-6">
          <input name="cantRes" id="cantRes" type="number" class="validate">
          <label for="cantRes">Cantidad máxima de reservas pendientes</label>
        </div>
      </div>
      <div class="checkbox">
	    <label>
	      <input type="checkbox" name="habilitado">Habilitado</label>
	  </div>
     
      
    <div class="row margin"></div>
    <button class="btn btn-success waves-effect waves-light " onclick="javascript: submitForm('tipoElemento/consulta')">Buscar</button>
        <button class="btn btn-primary waves-effect waves-light " onclick="javascript: submitForm('tipoElemento/alta')">Agregar</button>
        <button class="btn btn-warning waves-effect waves-light " onclick="javascript: submitForm('tipoElemento/modificacion')">Editar</button>
        <button class="btn btn-danger waves-effect waves-light " onclick="javascript: submitForm('tipoElemento/baja')">Eliminar</button>
    </form>
    
    
 
</body>
 <script src="style/ie10-viewport-bug-workaround.js"></script>
<script src="style/jquery.min.js"></script>
	<script src="style/mdb.min.js"></script>
	<script src="style/bootstrap.min.js"></script>

</html>
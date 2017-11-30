<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="entity.Persona"%>
<%@page import="entity.Categoria"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Personas</title>
<script type="text/javascript">
    	function submitForm(met) {
    		document.myForm.action=met;
    		//document.getElementById("myFrom").submit();
        }
    </script>
<style type="text/css">
 #buscar {margin-left: 0cm; }
 #agregar {margin-left: 0.5cm;}	
 #eliminar {margin-left: 0.5cm;}
 #modificar {margin-left: 0.6cm;}	
	</style>
</head>
<link href="style/mdb.min.css" rel="stylesheet">

<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/home.css" rel="stylesheet">
<body>
	<div class="row">
	<div class="col-md-2"></div>
    <form class="col-md-6" name="myForm" action="" method="post">
      <div class="row">
        <div class="input-field col-md-12">
          <input disabled id="id" type="text" >
          <label for="id">Id</label>
        </div>
       
      </div>
      <div class="row">
        <div class="input-field col-md-12">
          <input id="dniInput" name="dniInput" value="<%= session.getAttribute("dniPersona") %>" type="number" class="validate">
          <label for="dniInput">DNI</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col-md-12">
          <input id="nameInput"  name="nameInput" value="<%= session.getAttribute("nombrePersona") %>" type="text" class="validate">
          <label for="nameInput">Nombre</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col-md-12">
          <input id="lastnameInput" name="lastnameInput" value="<%= session.getAttribute("apellidoPersona") %>"  type="text" class="validate">
          <label for="lastnameInput">Apellido</label>
        </div>
        </div>
        
        <div class="row">
        <div class="col-md-6">        
       	<select class="browser-default" name="categoria">
       	<option value="" disabled selected>Seleccione una Categor�a</option>
       	<%
			ArrayList<Categoria> listaCategorias= (ArrayList<Categoria>)request.getAttribute("listaCategorias");
			for(Categoria c: listaCategorias){
		%>
        	<option value="<%=c.getId()%>" >
        		<%=c.getDescripcion()%>
        	</option>
        	<%} %>
        </select>
        </div>
       </div>
        <div class="checkbox">
	    <label>
	      <input type="checkbox" name="habilitado">Habilitado</label>
	  </div>
   <button class="btn btn-success waves-effect waves-light " onclick="javascript: submitForm('persona/consulta')">Buscar</button>
        <button class="btn btn-primary waves-effect waves-light " onclick="javascript: submitForm('persona/alta')">Agregar</button>
        <button class="btn btn-warning waves-effect waves-light " onclick="javascript: submitForm('persona/modificacion')">Editar</button>
        <button class="btn btn-danger waves-effect waves-light " onclick="javascript: submitForm('persona/baja')">Eliminar</button>
    </form>
  </div>
	

</body>
<script src="style/jquery.min.js"></script>
	<script src="style/mdb.min.js"></script>
	<script src="style/bootstrap.min.js"></script>
</html>
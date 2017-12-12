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

<body style="margin-left: 25px;">
<form class="col-md-6" name="myForm" action="" method="post">
      <div class="row">
        <div class="input-field col-md-6">
        <% if(((TipoElementos)session.getAttribute("tipoElemento"))!=null){%>
        	   <input disabled name="idInput" id="idInput" type="text" class="validate" value="<%=((TipoElementos)session.getAttribute("tipoElemento")).getId()%>" >
        	<%} else { %>
        	   <input disabled id="idInput" name="idInput" type="text" >
        	<%} %>
          <label for="idInput">Id</label>
        </div>       
      </div>      
      <div class="row">
        <div class="input-field col-md-6">
         <% if(((TipoElementos)session.getAttribute("tipoElemento"))!=null){%>
          <input name="nameInput" id="nameInput" type="text" class="validate" value="<%= ((TipoElementos)session.getAttribute("tipoElemento")).getNombre() %>">
        	<%} else { %>
        	    <input name="nameInput" id="nameInput" type="text" class="validate">
        	<%} %>
          <label for="nameInput">Nombre</label>
        </div>
      </div>
       <div class="row">
         <% if(((TipoElementos)session.getAttribute("tipoElemento"))!=null){%>
          <input name="cantRes" id="cantRes" type="number" class="validate" value="<%=((TipoElementos)session.getAttribute("tipoElemento")).getCanMaxResPend() %>">
        	<%} else { %>
        	    <input name="cantRes" id="cantRes" type="number" class="validate" ">
        	<%} %>
          <label for="cantRes">Cantidad máxima de reservas pendientes</label>
        </div>
      </div>
       <div class="checkbox">
	    <label>
	      <input type="checkbox" name="habilitado" <%if(((TipoElementos)session.getAttribute("tipoElemento"))!=null && ((TipoElementos)session.getAttribute("tipoElemento")).isHabilitado()==true){%> 
	      checked="true" <% } %>>Habilitado</label>
	      
	  </div> 
     
      
    <div class="row margin"></div>
    <button class="btn btn-success waves-effect waves-light " onclick="javascript: submitForm('tipoElemento/consulta')">Buscar</button>
        <button class="btn btn-primary waves-effect waves-light " onclick="javascript: submitForm('tipoElemento/alta')">Agregar</button>
        <button class="btn btn-warning waves-effect waves-light " onclick="javascript: submitForm('tipoElemento/modificacion')">Editar</button>
        <button class="btn btn-danger waves-effect waves-light " onclick="javascript: submitForm('tipoElemento/baja')">Eliminar</button>
    </form>
     <% String error= (String)session.getAttribute("error");
            if(error=="updateTipoElemento"){ %> 
        <script>alert("No se ha podido modificar el tipo de elemento.");</script> 
        <%} if(error=="deleteTipoElemento") {%>  
        <script>alert("No se ha podido eliminar el tipo de elemento.");</script> 
        <%} if(error=="addTipoElemento") {%>       
         <script>alert("No se ha podido agregar el tipo de elemento.");</script> 
        <%}if(error=="consultaTipoElemento") {%>       
         <script>alert("No se ha encontrado un tipo de elemento con ese nombre.");</script> 
         <%}%>
         
    <% String success= (String)session.getAttribute("success");
            if(success=="updateTipoElemento"){%> 
      <script>alert("Tipo de elemento modificado con éxito.");</script> 
      <%} if(success=="deleteTipoElemento") {%>  
        <script>alert("Tipo de elemento eliminado con éxito.");</script> 
        <%} if(success=="addTipoElemento") {%>       
         <script>alert("Tipo de elemento agregado con éxito.");</script> 
         <%}%>           
                 
        
    
 
</body>
 <script src="style/ie10-viewport-bug-workaround.js"></script>
<script src="style/jquery.min.js"></script>
	<script src="style/mdb.min.js"></script>
	<script src="style/bootstrap.min.js"></script>

</html>
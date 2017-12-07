<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="entity.Elemento"%>
    <%@page import="entity.TipoElementos"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <script type="text/javascript">
    	function submitForm(met) {
    		document.myForm.action=met;
    		//document.getElementById("myFrom").submit();
        }
    </script>
<title>Elementos</title>
</head>
<link href="style/mdb.min.css" rel="stylesheet">
<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/home.css" rel="stylesheet">

<body style="margin-left: 25px;">
<div class="row">
<div class="col-md-2"></div>
<form class="col-md-6" name="myForm" action="" method="post">

      <div class="row">
        <div class="input-field col-md-6">
          <% if(session.getAttribute("idElemento")!=null){%>
          <input disabled id="idInput" name="idInput" type="text" value=" <%=session.getAttribute("idElemento")%> ">
          <%} else { %>
           <input disabled id="idInput" name="idInput" type="text">
        	<%} %>
          <label for="id">Id</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col-md-6">
        	<% if(session.getAttribute("nombreElemento")!=null){%>
        	   <input name="nameInput" id="nameInput" type="text" class="validate" value="<%=session.getAttribute("nombreElemento")%>" >
        	
        	<%} else { %>
        	    <input name="nameInput" id="nameInput" type="text" class="validate">
        	
        	<%} %>
          
          <label for="nameInput">Nombre</label>
        </div>
      </div>
     
       <div class="row">
        <div class="col-md-6">        
       	<select class="browser-default" name="tipoElemento">
       	<% if(session.getAttribute("tipoElemento")==null){%>
       		<option value="" disabled selected>Seleccione un Tipo de Elemento</option>
       	<%} else { %>
       		<option value="<%=session.getAttribute("tipoElemento")%>" disabled selected>
       		<%=session.getAttribute("tipoElemento")%>
       		</option>
       	<%}%>
       
       	<% 
       		ArrayList<TipoElementos> listaTipoElementos= (ArrayList<TipoElementos>)request.getAttribute("listaTipoElementos");
			for(TipoElementos te: listaTipoElementos){       	
		%>
        	<option value="<%=te.getNombre()%>">
        		<%=te.getNombre()%>
        	</option>
        	<%}%>
        </select>
        </div>
       </div>
       <div class="checkbox">
	    <label>
	      <input type="checkbox" name="habilitado" <%if(session.getAttribute("habilitadoElemento")!=null && Boolean.parseBoolean(request.getAttribute("habilitadoElemento").toString())==true){%> 
	      checked="true" <% } %>>Habilitado</label>
	      
	  </div> 
    <div class="row margin"></div>
    <button class="btn btn-success waves-effect waves-light " onclick="javascript: submitForm('elemento/consulta')">Buscar</button>
        <button class="btn btn-primary waves-effect waves-light " onclick="javascript: submitForm('elemento/alta')">Agregar</button>
        <button class="btn btn-warning waves-effect waves-light " onclick="javascript: submitForm('elemento/modificacion')">Editar</button>
        <button class="btn btn-danger waves-effect waves-light " onclick="javascript: submitForm('elemento/baja')">Eliminar</button>
    </form>
 </div>   
    <% String error= (String)session.getAttribute("error");
            if(error=="updateElemento"){ 
                %> 
                <script>alert("No se ha podido modificar el elemento");</script> 
               <%} %>         
        
 
</body>
 <script src="style/ie10-viewport-bug-workaround.js"></script>
<script src="style/jquery.min.js"></script>
	<script src="style/mdb.min.js"></script>
	<script src="style/bootstrap.min.js"></script>

</html>
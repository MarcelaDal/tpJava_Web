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
<body style="margin-left: 25px;">
	<div class="row">
	<div class="col-md-2"></div>
    <form class="col-md-6" name="myForm" action="" method="post">
      <div class="row">
        <div class="input-field col-md-12">
        <% if(((Persona)session.getAttribute("persona"))!=null){%>
          <input disabled id="idInput" name="idInput" type="text" value="<%=((Persona)session.getAttribute("persona")).getId()%> ">
          <%} else { %>
           <input disabled id="idInput" name="idInput" type="text" >
        	<%} %>
          <label for="id">Id</label>
        </div>
       
      </div>
      <div class="row">
        <div class="input-field col-md-12">
        <% if(((Persona)session.getAttribute("persona"))!=null){%>
          <input id="dniInput" name="dniInput" value="<%=((Persona)session.getAttribute("persona")).getDni() %>" type="number" class="validate">
        	<%} else { %>
        	   <input id="dniInput" name="dniInput" type="number" class="validate">        	
        	<%} %>
          <label for="dniInput">DNI</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col-md-12">
        <% if(((Persona)session.getAttribute("persona"))!=null){%>
          <input id="nameInput"  name="nameInput" value="<%= ((Persona)session.getAttribute("persona")).getNombre() %>" type="text" class="validate">
        	<%} else { %>
        	    <input name="nameInput" id="nameInput" type="text" class="validate">
        	<%} %>
          <label for="nameInput">Nombre</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col-md-12">
        <% if(((Persona)session.getAttribute("persona"))!=null){%>
          <input id="lastnameInput" name="lastnameInput" value="<%= ((Persona)session.getAttribute("persona")).getApellido() %>"  type="text" class="validate">
        	<%} else { %>
        	    <input name="lastnameInput" id="lastnameInput" type="text" class="validate">
        	<%} %>
          <label for="lastnameInput">Apellido</label>
        </div>
        </div>
        <div class="row">
        <div class="col-md-6">            	
       	<select class="browser-default" name="categoria">
       	<% if(((Persona)session.getAttribute("persona"))==null){%>
       		<option value="" disabled selected>Seleccione una Categoría</option>
       	<%} else { %>
       		<option  selected>
       		<%=((Persona)session.getAttribute("persona")).getCategoria()%>
       		</option>
       	<%}%>
       
       	<% 
       	ArrayList<Categoria> listaCategorias= (ArrayList<Categoria>)request.getAttribute("listaCategorias");
		for(Categoria c: listaCategorias){
		%>
        	<option value="<%=c %>" >
        	    <%=c.getDescripcion()%>
        	</option>
        	<%} %>
        </select>
      </div>
       </div>
     <div class="checkbox">
	    <label>
	      <input type="checkbox" name="habilitado" <%if(((Persona)session.getAttribute("persona"))!=null && ((Persona)session.getAttribute("persona")).isHabilitado()==true){%> 
	      checked="true" <% } %>>Habilitado</label>
	      
	  </div> 
     
   <button class="btn btn-success waves-effect waves-light " onclick="javascript: submitForm('persona/consulta')">Buscar</button>
        <button class="btn btn-primary waves-effect waves-light " onclick="javascript: submitForm('persona/alta')">Agregar</button>
        <button class="btn btn-warning waves-effect waves-light " onclick="javascript: submitForm('persona/modificacion')">Editar</button>
        <button class="btn btn-danger waves-effect waves-light " onclick="javascript: submitForm('persona/baja')">Eliminar</button>
    </form>
  </div>
	
	 <% String error= (String)session.getAttribute("error");
            if(error=="updatePersona"){ %> 
        <script>alert("No se ha podido modificar la persona.");</script> 
        <%} if(error=="deletePersona") {%>  
        <script>alert("No se ha podido dar de baja a la persona.");</script> 
        <%} if(error=="addPersona") {%>       
         <script>alert("No se ha podido agregar a la persona.");</script> 
        <%}if(error=="consultaPersona") {%>       
         <script>alert("No se ha encontrado una persona con ese dni.");</script> 
         <%}%>
         
    <% String success= (String)session.getAttribute("success");
            if(success=="updatePersona"){%> 
      <script>alert("Datos de la persona modificados con éxito.");</script> 
      <%} if(success=="deletePersona") {%>  
        <script>alert("Persona dada de baja con éxito.");</script> 
        <%} if(success=="addPersona") {%>       
         <script>alert("Persona agregada con éxito.");</script> 
         <%}%>           

</body>
<script src="style/jquery.min.js"></script>
	<script src="style/mdb.min.js"></script>
	<script src="style/bootstrap.min.js"></script>
</html>
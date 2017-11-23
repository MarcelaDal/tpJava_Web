<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="entity.Elemento"%>
    <%@page import="entity.TipoElementos"%>
    <%@page import="controlers.CtrlABMCElementos"%>
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

<body>
<div class="row">
<div class="col-md-2"></div>
<form class="col-md-6" name="myForm" action="" method="post">
      <div class="row">
        <div class="input-field col-md-6">
          <input disabled id="idInput" name="idInput" type="text" <%=request.getAttribute("idInput")  %>
           >
          <label for="id">Id</label>
        </div>       
      </div>      
      <div class="row">
        <div class="input-field col-md-6">
          <input name="nameInput" id="nameInput" type="text" class="validate" <%=request.getAttribute("nameInput")  %>>
          <label for="nameInput">Nombre</label>
        </div>
      </div>
     
       <div class="row">
        <div class="col-md-6">        
       	<select class="browser-default" name="tipoElemento">
       	<option value="" disabled selected>Seleccione un Tipo de Elemento</option>
       	<%
			ArrayList<TipoElementos> listaTipoElementos= (ArrayList<TipoElementos>)request.getAttribute("listaTipoElementos");
			for(TipoElementos te: listaTipoElementos){
		%>
        	<option value="<%=te.getNombre()%>">
        		<%=te.getNombre()%>
        	</option>
        	<%} %>
        </select>
        </div>
       </div>
    <div class="row margin"></div>
    <button class="btn btn-success waves-effect waves-light " onclick="javascript: submitForm('elemento/consulta')">Buscar</button>
        <button class="btn btn-primary waves-effect waves-light " onclick="javascript: submitForm('elemento/alta')">Agregar</button>
        <button class="btn btn-warning waves-effect waves-light " onclick="javascript: submitForm('elemento/modificacion')">Editar</button>
        <button class="btn btn-danger waves-effect waves-light " onclick="javascript: submitForm('elemento/baja')">Eliminar</button>
    </form>
 </div>   
    
 
</body>
 <script src="style/ie10-viewport-bug-workaround.js"></script>
<script src="style/jquery.min.js"></script>
	<script src="style/mdb.min.js"></script>
	<script src="style/bootstrap.min.js"></script>

</html>
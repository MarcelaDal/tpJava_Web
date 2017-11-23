<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="entity.Reserva"%>
<%@page import="entity.TipoElementos"%>
<%@page import="entity.Elemento"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Reservas</title>
<script type="text/javascript">
    	function submitForm(met) {
    		document.myForm.action=met;
    		//document.getElementById("myFrom").submit();
        }
    </script>
</head>
<link href="style/mdb.min.css" rel="stylesheet">
<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/home.css" rel="stylesheet">

<body>
	<div class="row">
	<div class="col-md-2"></div>
    <form class="col-md-6" name="myForm" action="" method="post">
     
 		<div class="row">
	        <div class="col-md-6">        
		       	<select class="browser-default" name="tipoElemento">
			       	<option value="" disabled selected>Seleccione un tipo de elemento</option>
			       	<%
						ArrayList<TipoElementos> tipoElementos= (ArrayList<TipoElementos>)request.getAttribute("tipoElementos");
						for(TipoElementos te: tipoElementos){
					%>
			        	<option value="<%=te.getId()%>" >
			        		<%=te.getNombre()%>
			        	</option>
			        	<%} %>
		        </select>
	        </div>
       </div>
       
       <div class="row" style="margin-top:16px;">
	        <div class="col-md-6">        
		       	<select class="browser-default" name="elemento">
			       	<option value="" disabled selected>Seleccione un elemento</option>
			       	<%
						ArrayList<Elemento> elementos= (ArrayList<Elemento>)request.getAttribute("elementos");
						for(Elemento e: elementos){
					%>
			        	<option value="<%=e.getId()%>" >
			        		<%=e.getNombre()%>
			        	</option>
			        	<%} %>
		        </select>
	        </div>
       </div>
       
       <div class="container">
    <div class="row">
        <div class='col-sm-6'>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker1'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker();
            });
        </script>
    </div>
</div>
       
       
      <div class="row">
        <div class="input-field col-md-12">
         <textarea id="detail" name="detail" class="materialize-textarea"></textarea>
          <label for="detail">Detalles</label>
        </div>
      </div>
            
   <button class="btn btn-success waves-effect waves-light " onclick="javascript: submitForm('reserva/consulta')">Buscar</button>
        <button class="btn btn-primary waves-effect waves-light " onclick="javascript: submitForm('reserva/alta')">Agregar</button>
        <button class="btn btn-warning waves-effect waves-light " onclick="javascript: submitForm('reserva/modificacion')">Editar</button>
        <button class="btn btn-danger waves-effect waves-light " onclick="javascript: submitForm('reserva/baja')">Eliminar</button>
    </form>
  </div>
	

</body>
<script src="style/jquery.min.js"></script>
	<script src="style/mdb.min.js"></script>
	<script src="style/bootstrap.min.js"></script>

<script type="text/javascript" src="/path/to/bootstrap/js/transition.js"></script>
<script type="text/javascript" src="/path/to/bootstrap/js/collapse.js"></script>
<script type="text/javascript" src="/path/to/bootstrap/dist/bootstrap.min.js"></script>
<script type="text/javascript" src="/path/to/bootstrap-datetimepicker.min.js"></script>
<script src="style/moment.min.js"></script>
</html>
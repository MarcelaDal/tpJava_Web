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
<link href="style/mdb.min.css" rel="stylesheet">
<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/home.css" rel="stylesheet">
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">

	<script src="style/mdb.min.js"></script>
	<script src="style/bootstrap.min.js"></script>
<script src="style/moment.min.js"></script>
<script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous">
 </script>
 <script
  src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
  integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
  crossorigin="anonymous"></script>
 
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({
    	beforeShowDay: function(date) {
            var day = date.getDay();
            return [(day != 6 && day != 0)];
        }
    });
  } );
  </script>
     <script type="text/javascript">
       $(function() {
               $("#datepicker").datepicker({ dateFormat: "dd-mm-yyyy" }).val()
       });
   </script>
<script type="text/javascript">
    	function submitForm(met) {
    		document.myForm.action=met;
    		//document.getElementById("myFrom").submit();
        }
    </script>

 
 <script>
 $(document).ready(function(){
	 $("#tipoElemento").change(function(){
		 var tipo=$("#tipoElemento").val();
		 $.ajax({
	           url: 'ComboElementos',
	           data: { tipoElemento: tipo },
	           type: "post",
	           dataType: "json",
	           cache: false,
	           success: function (dato) {
					$("#elemento").empty();
					$.each(dato,function(index,item){
						$("#elemento").append('<option value="'+item.id+'">'+item.nombre+'</option>');
					});
			
	           },
	           error: function (dato) {

	           }
	       });
	 })
 })
 </script>  
</head>
<body style="margin-left: 25px;">
	<div class="row">
	<div class="col-md-2"></div>
    <form class="col-md-6" name="myForm" action="" method="post">
      <div class="row">
        <div class="input-field col-md-12">
        <% if(session.getAttribute("Reserva")!=null){%>
          <input disabled id="id" name="idInput" type="text" value=" <%=((Reserva)session.getAttribute("reserva")).getId() %> ">
          <%} else { %>
           <input  id="idInput" name="idInput" type="text" >
        	<%} %>
          <label for="id">Id</label>
        </div>
 		<div class="row">
	        <div class="col-md-6">        
		       	<select class="browser-default" name="tipoElemento"  id="tipoElemento">
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
		       	<select class="browser-default" name="elemento" id="elemento"   >
			       	<option value="" disabled selected>Seleccione un elemento</option>		
		        </select>
	        </div>
      <div class="row">
        <div class="input-field col-md-12">
         <textarea id="detail" name="detail" class="materialize-textarea"></textarea>
          <label for="detail">Detalles</label>
        </div>
      </div>
  <p>Date: <input type="text" id="datepicker" name="datepicker"></p>
  
  <div class="col-md-6">        
		       	<select class="browser-default" id="hora" name="hora">
			       	<option value="" disabled selected>Seleccione un horario</option>
			        	<option value= "008:00:00"> 08:00:00</option >
			        	<option value= "009:00:00"> 09:00:00</option >
			        	<option value= "010:00:00"> 10:00:00</option >
			        	<option value= "011:00:00"> 11:00:00</option >
			        	<option value= "012:00:00"> 12:00:00</option >
			        	<option value= "013:00:00"> 13:00:00</option >
			        	<option value= "014:00:00"> 14:00:00</option >
			        	<option value= "015:00:00"> 15:00:00</option >
			        	<option value= "016:00:00"> 16:00:00</option > 
			        	<option value= "017:00:00"> 17:00:00</option >
			        	<option value= "018:00:00"> 18:00:00</option >
			        	<option value= "019:00:00"> 19:00:00</option >
			        	<option value= "020:00:00"> 20:00:00</option >
			        	<option value= "021:00:00"> 21:00:00</option >
			        	<option value= "022:00:00"> 22:00:00</option >
			       </select>
	        </div>
        <button class="btn btn-primary waves-effect waves-light " onclick="javascript: submitForm('reserva/alta')">Agregar</button>
        <button class="btn btn-danger waves-effect waves-light " onclick="javascript: submitForm('reserva/baja')">Eliminar</button>
    </form>
      
       <% String error= (String)session.getAttribute("error");
       if(error=="deleteReserva") {%>  
        <script>alert("No se ha podido eliminar la reserva.");</script> 
        <%} if(error=="addReserva") {%>       
         <script>alert("No se ha podido agregar la reserva.");</script> 
       <% } if(error=="reserva Superpuesta"){ %>
          <script>alert("Reserva Superpuesta, cambie la fecha y hora");</script> 
          <% } %>
    <% String success= (String)session.getAttribute("success");
       if(success=="deleteReserva") {%>  
        <script>alert("Reserva eliminada con éxito.");</script> 
        <%} if(success=="addReserva") {%>       
         <script>alert("Reserva agregada con éxito.");</script> 
          <% } %>
    
  </div>
	
</body>

</html>
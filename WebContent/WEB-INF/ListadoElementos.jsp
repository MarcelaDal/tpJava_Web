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
    		document.getElementById("myForm").submit();
        }
    </script>
<title>Elementos</title>
</head>
<link href="style/mdb.min.css" rel="stylesheet">
<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/listados.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<body style="margin-left: 25px;">

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">Resultados de la búsqueda
                  <!-- <div class="pull-right action-buttons">
                        <div class="btn-group pull-right">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                <span class="glyphicon glyphicon-cog" style="margin-right: 0px;"></span>
                            </button>
                            <ul class="dropdown-menu slidedown">
                                <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-pencil"></span>Edit</a></li>
                                <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-trash"></span>Delete</a></li>
                                <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-flag"></span>Flag</a></li>
                            </ul>
                        </div>
                    </div> -->  
                </div>
                <form name="myForm" action="" method="post">
                 
                <div class="panel-body">
                
                    <ul class="list-group">
                   <% 
       		ArrayList<Elemento> itemsBusqueda= (ArrayList<Elemento>)request.getAttribute("resultadosBusqueda");
			for(Elemento e: itemsBusqueda){       	
			%>
                        <li class="list-group-item">
                            <div class="checkbox" style="margin-left: 3px;">
                                <input type="checkbox" id="checkbox" />
                                <label for="checkbox">
                                 <%=e.getNombre() %>
                                </label>
                            </div>
                            <div class="pull-right action-buttons">
                                <button id="delete" class="option edit" value="<%e.getId();%>" onclick="javascript: submitForm('elemento/modificacion')"><i class="material-icons prefix">create</i></button>
                                <button class="option trash"  value="<%e.getId();%>" onclick="javascript: submitForm('elemento/baja')"><i class="material-icons prefix">delete</i></button>
                                <button class="option" data-toggle="modal" data-target="#verDetalle"><i class="material-icons prefix">remove_red_eye</i></button>
                                
                            </div>
                        </li>
                          <%}%>
                   </ul>
                    
                </div>
                 </form>
            </div>
        </div>
    </div>
</div>


 <!-- Modal Detalles -->
  <div class="modal fade" id="verDetalle" >
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
          <p>Some text in the modal.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

</body>
 <script src="style/ie10-viewport-bug-workaround.js"></script>
<script src="style/jquery.min.js"></script>
	<script src="style/mdb.min.js"></script>
	<script src="style/bootstrap.min.js"></script>

</html>
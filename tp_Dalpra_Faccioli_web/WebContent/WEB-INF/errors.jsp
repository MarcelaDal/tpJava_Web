<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>

</head>
<link href="style/mdb.min.css" rel="stylesheet">

<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/home.css" rel="stylesheet">
<body>
<div class="container">
	<div class="row">
		<%
		String error = (String) session.getAttribute("errorLogin");
		if (error.equals("Error")){   %>
		<h2>Credenciales incorrectas.</h2>
		<% } %>
	</div>
	<div class="row">
		<div class="col-md-4">
			<a class="btn btn-primary btn-lg btn-block" href="index.html">OK</a>
		</div>
	</div>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unu.poo2.beans.Editorial"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<title>Lista de Editorial</title>
</head>
<body>
	<%
	String url = "http://localhost:8080/bibliotecaUnuPoo2/";
	%>
	<div class="container">
		<div class="row col-md-6 offset-md-3">
			<div class="col-auto me-auto">
				<a type="button" href="<%=url%>EditorialController?op=nuevo"
					class="btn btn-info">Nuevo Editorial</a>
			</div>
			<div class="col-auto">
				<a type="button" href="<%=url%>" class="btn btn-danger">Volver</a>
			</div>
		</div>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous"><title>Bienvenido</title>
</head>
<body>
	<%
	String url = "http://localhost:8080/bibliotecaUnuPoo2/";
	%>
	<div class="container col-md-6 offset-md-3 list-group" >
		<br><h5>Inicio Biblioteca Unu Poo 2</h5>
		<a href= "<%=url%>AutoresController?op=listar" class="list-group-item list-group-item-action list-group-item-success">Autores</a>
		<a href= "<%=url%>EditorialController?op=listar" class="list-group-item list-group-item-action list-group-item-danger">Editorial</a>
		<a href= "<%=url%>GeneroController?op=listar" class="list-group-item list-group-item-action list-group-item-dark">Genero</a>
		<a href= "<%=url%>AutoresController?op=listar" class="list-group-item list-group-item-action list-group-item-info">Libro</a>
	</div>
</body>
</html>
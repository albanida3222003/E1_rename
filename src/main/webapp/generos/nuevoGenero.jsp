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
	crossorigin="anonymous">
<title>Nuevo Genero</title>
</head>
<body>
	<%
	String url = "http://localhost:8080/bibliotecaUnuPoo2/";
	%>
	<div class="container col-md-6 offset-md-3">
		<br><h5>Nuevo Genero</h5>
		<form action="<%=url%>GeneroController" method="Post">
		<input type="hidden" name = "op" value= "insertar">
		Nombre del genero: <input type="text" name ="nombre" id = "nombre" class="form-control"><br>
		Descripcion del genero: <input type="text" name ="descripcion" id="descripcion" class="form-control"><br>
		<input type ="submit" value="Guardar" name="Guardar" class="btn btn-info">
		<a href= "<%=url%>GeneroController?op=listar" class="btn btn-danger">Volver</a>
		</form>
	</div>
</body>
</html>
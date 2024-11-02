<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bienvenido</title>
</head>
<body>
	<%
	String url = "http://localhost:8080/bibliotecaUnuPoo2/";
	%>
	goooo al rojo, apuesta todo al rojoooooo
	<br>
	<a href= "<%=url%>AutoresController?op=listar">Autores</a>
</body>
</html>
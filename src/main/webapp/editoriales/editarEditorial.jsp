<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.unu.poo2.beans.Editorial"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<title>Editar Editorial</title>
</head>
<body>
	<%
	String url = "http://localhost:8080/bibliotecaUnuPoo2/";
	Editorial editor;
	HttpSession sesion = request.getSession();

	if (request.getAttribute("editorial") == null) {
		editor = new Editorial();
	} else {
		editor = (Editorial) request.getAttribute("editor");
		System.out.println(editor.getIdeditorial());
		System.out.println(editor.getNombre());
	}
	%>
</body>
</html>
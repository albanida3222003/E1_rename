<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.unu.poo2.beans.Genero"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<title>Edicion de Genero</title>
</head>
<body>
	<%
	String url = "http://localhost:8080/bibliotecaUnuPoo2/";
	Genero genero;
	HttpSession sesion = request.getSession();

	if (request.getAttribute("genero") == null) {
		genero = new Genero();
	} else {
		genero = (Genero) request.getAttribute("genero");
		System.out.println(genero.getIdgenero());
		System.out.println(genero.getNombre());
	}
	%>
	<div class="container col-md-6 offset-md-3">
		<form role="form" action="<%=url%>GeneroController" method="POST">
			<div >
				<input type="hidden" name="op" value="modificar" /> 
				<input type="hidden" name="idgenero" class="form-control" value="<%=genero.getIdgenero()%>" />
			</div>
			<br><h5>REGISTRO DE GENERO</h5>
			<div>
				Codigo: <input type="text" name="codigo" class="form-control" value="<%=genero.getIdgenero()%>"> <br> 
			</div>
			<div>
				Nombre: <input type="text" name="nombre"  placeholder="Nombre" aria-label="Nombre" 
				class="form-control" value="<%=genero.getNombre()%>"> <br>
			</div>
			<div>
				Descripcion: <input type="text" name="descripcion" placeholder="descripcion" aria-label="descripcion" 
				class="form-control" value="<%=genero.getDescripcion()%>"> <br> 
			</div>
			<div>
				<input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
				<a class="btn btn-danger" href="<%=url%>GeneroController?op=listar">Cancelar</a>
			</div>
		</form>
	</div>
</body>
</html>
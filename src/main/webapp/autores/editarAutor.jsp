<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.unu.poo2.beans.Autor"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<title>Edicion de Autor</title>
</head>
<body>
	<%
	String url = "http://localhost:8080/bibliotecaUnuPoo2/";
	Autor autor;
	HttpSession sesion = request.getSession();

	if (request.getAttribute("autor") == null) {
		autor = new Autor();
	} else {
		autor = (Autor) request.getAttribute("autor");
		System.out.println(autor.getIdAutor());
		System.out.println(autor.getNombre());
	}
	%>
	<div class="container col-md-6 offset-md-3">
		<form role="form" action="<%=url%>AutoresController" method="POST">
			<div >
				<input type="hidden" name="op" value="modificar" /> 
				<input type="hidden" name="idautor" class="form-control" value="<%=autor.getIdAutor()%>" />
			</div>
			<br><h5>REGISTRO DE AUTOR</h5>
			<div>
				Codigo: <input type="text" name="codigo" class="form-control" value="<%=autor.getIdAutor()%>"> <br> 
			</div>
			<div>
				Nombre: <input type="text" name="nombre"  placeholder="Nombre" aria-label="Nombre" 
				class="form-control" value="<%=autor.getNombre()%>"> <br>
			</div>
			<div>
				Nacionalidad: <input type="text" name="nacionalidad" placeholder="Nacionalidad" aria-label="Nacionalidad" 
				class="form-control" value="<%=autor.getNacionalidad()%>"> <br> 
			</div>
			<div>
				<input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
				<a class="btn btn-danger" href="<%=url%>AutoresController?op=listar">Cancelar</a>
			</div>
		</form>
	</div>

</body>
</html>
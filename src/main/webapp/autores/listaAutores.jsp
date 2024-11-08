<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unu.poo2.beans.Autor"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<title>Lista de Autores</title>
</head>
<body>
	<%
	String url = "http://localhost:8080/bibliotecaUnuPoo2/";
	%>
	<div class="container">
		<div class="col-md-6 offset-md-3">
			<br>
			<table class="table table-striped table-bordered">
				<thead>
					<tr class="table table-striped">
						<th>Codigo del autor</th>
						<th>Nacionalidad</th>
						<th>Nombre del autor</th>
						<th>Operaciones</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Autor> listaAutores = (List<Autor>) request.getAttribute("listaAutores");
					// Verificar si la lista no es nula
					if (listaAutores != null) {
						// Iterar sobre la lista de autores
						for (Autor autor : listaAutores) {
					%>
					<tr class="table table-striped">
						<td><%=autor.getIdAutor()%></td>
						<td><%=autor.getNombre()%></td>
						<td><%=autor.getNacionalidad()%></td>
						<td><a
							href="<%=url%>AutoresController?op=obtener&id=<%=autor.getIdAutor()%>"
							class="btn btn-success">Editar</a> <a
							href="<%=url%>AutoresController?op=eliminar&id=<%=autor.getIdAutor()%>"
							class="btn btn-danger">Eliminar</a></td>
					</tr>
					<%
					}
					} else {
					%>
					<tr class="table table-striped">
						<td>No hay datos</td>
						<td>No hay datos</td>
						<td>No hay datos</td>
						<td></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<div class="row col-md-6 offset-md-3">
			<div class="col-auto me-auto">
				<a type="button" href="<%=url%>AutoresController?op=nuevo"
					class="btn btn-info">Nuevo Autor</a>
			</div>
			<div class="col-auto">
				<a type="button" href="<%=url%>"
					class="btn btn-danger">Volver</a>
			</div>
		</div>
	</div>
</body>
</html>
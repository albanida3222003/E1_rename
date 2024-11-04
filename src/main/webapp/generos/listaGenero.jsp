<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.unu.poo2.beans.Genero"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<title>Lista de Genero</title>
</head>
<body>
	<%
	String url = "http://localhost:8080/bibliotecaUnuPoo2/";
	%>
	<div class="col-md-6 offset-md-3">
			<br>
			<table class="table table-striped table-bordered">
				<thead>
					<tr class="table table-striped">
						<th>Codigo del Genero</th>
						<th>Nombre del Genero</th>
						<th>Descripcion</th>
						<th>Operaciones</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Genero> listaGenero = (List<Genero>) request.getAttribute("listaGenero");
					// Verificar si la lista no es nula
					if (listaGenero != null) {
						// Iterar sobre la lista de autores
						for (Genero genero : listaGenero) {
					%>
					<tr class="table table-striped">
						<td><%=genero.getIdgenero()%></td>
						<td><%=genero.getNombre()%></td>
						<td><%=genero.getDescripcion()%></td>
						<td><a
							href="<%=url%>GeneroController?op=obtener&id=<%=genero.getIdgenero()%>"
							class="btn btn-success">Editar</a> <a
							href="<%=url%>GeneroController?op=eliminar&id=<%=genero.getIdgenero()%>"
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
	<div class="container">
		<div class="row col-md-6 offset-md-3">
			<div class="col-auto me-auto">
				<a type="button" href="<%=url%>GeneroController?op=nuevo"
					class="btn btn-info">Nuevo Genero</a>
			</div>
			<div class="col-auto">
				<a type="button" href="<%=url%>"
					class="btn btn-danger">Volver</a>
			</div>
		</div>
	</div>
</body>
</html>
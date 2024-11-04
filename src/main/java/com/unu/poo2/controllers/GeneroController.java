package com.unu.poo2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.unu.poo2.beans.Genero;
import com.unu.poo2.model.GeneroModel;

public class GeneroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GeneroModel modelo = new GeneroModel();
	
	public GeneroController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		if (request.getParameter("op") == null) {
			listar(request, response);
			return;
		}

		String operacion = request.getParameter("op");
		switch (operacion) {
		case "listar": {
			listar(request, response);
			System.out.println("Entro a listar.");
			break;
		}
		case "nuevo": {
			request.getRequestDispatcher("/generos/nuevoGenero.jsp").forward(request, response);
			System.out.println("Entro a nuevo.");
			break;
		}
		case "insertar": {
			insertar(request, response);
			System.out.println("Entro a insertar.");
			break;
		}
		case "obtener": {
			obtener(request, response);
			System.out.println("Entro a obtener.");
			break;
		}
		case "modificar": {
			modificar(request, response);
			System.out.println("Entro a modificar.");
			break;
		}
		case "eliminar": {
			eliminar(request, response);
			System.out.println("Entro a eliminar.");
			break;
		}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException, SQLException{
		try {
			request.setAttribute("listaGenero", modelo.listarGenero());
			request.getRequestDispatcher("/generos/listaGenero.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en Listar: " + e.getMessage());
		}
	}
	
	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {

			Genero miGenero = new Genero();
			miGenero.setNombre(request.getParameter("nombre"));
			miGenero.setDescripcion(request.getParameter("descripcion"));

			if (modelo.insertarGenero(miGenero) > 0) {
				request.getSession().setAttribute("exito", "genero registrado exitosamente");
				response.sendRedirect(request.getContextPath() + "/GeneroController?op=listar");
			} else {
				request.getSession().setAttribute("fracaso",
						"El genero no ha sido ingresado" + "ya hay un genero con este codigo");
				response.sendRedirect(request.getContextPath() + "/GeneroController?op=listar");
			}

		} catch (IOException | SQLException ex) {
			System.out.println("Error en Insertar." + ex.getMessage());
		}
	}
	
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			Genero miGenero = modelo.obtenerGenero(Integer.parseInt(codigo));

			if (miGenero != null) {
				request.setAttribute("genero", miGenero);
				request.getRequestDispatcher("/generos/editarGenero.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (IOException | ServletException ex) {
			ex.getStackTrace();
		}
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {

			Genero miGenero = new Genero();
			miGenero.setIdgenero(Integer.parseInt(request.getParameter("idgenero")));
			miGenero.setNombre(request.getParameter("nombre"));
			miGenero.setDescripcion(request.getParameter("descripcion"));
			request.setAttribute("genero", miGenero);

			if (modelo.modificarGenero(miGenero) > 0) {
				request.getSession().setAttribute("exito", "genero modificado exitosamente");
				response.sendRedirect(request.getContextPath() + "/GeneroController?op=listar");
			} else {
				request.getSession().setAttribute("fracaso",
						"El genero no ha sido modificado" + "ya hay un genero con este codigo");
				response.sendRedirect(request.getContextPath() + "/GeneroController?op=listar");
			}

		} catch (IOException | SQLException ex) {
			System.out.println("Error en modificar." + ex.getMessage());
		}
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			int idgenero = Integer.parseInt(request.getParameter("id"));
			if (modelo.eliminarGenero(idgenero) > 0) {
				request.setAttribute("exito", "Genero eliminado exitosamente");

			} else {
				request.setAttribute("fracaso", "No se puede eliminar este genero");
			}
			request.getRequestDispatcher("/GeneroController?op=listar").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			System.out.println("Error en eliminar." + ex.getMessage());
		}
	}
}

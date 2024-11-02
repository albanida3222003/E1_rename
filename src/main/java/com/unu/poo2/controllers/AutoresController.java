package com.unu.poo2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.unu.poo2.beans.Autor;
import com.unu.poo2.model.AutoresModel;

public class AutoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AutoresModel modelo = new AutoresModel();

	public AutoresController() {
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
			request.getRequestDispatcher("/autores/nuevoAutor.jsp").forward(request, response);
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
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try {
			request.setAttribute("listaAutores", modelo.listarAutores());
			request.getRequestDispatcher("/autores/listaAutores.jsp").forward(request, response);
		} catch (ServletException | IOException ex) {
			System.out.println("Error en Listar." + ex.getMessage());
		}

	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {

			Autor miAutor = new Autor();
			miAutor.setNombre(request.getParameter("nombre"));
			miAutor.setNacionalidad(request.getParameter("nacionalidad"));

			if (modelo.insertarAutor(miAutor) > 0) {
				request.getSession().setAttribute("exito", "autor registrado exitosamente");
				response.sendRedirect(request.getContextPath() + "/AutoresController?op=listar");
			} else {
				request.getSession().setAttribute("fracaso",
						"El autor no ha sido ingresado" + "ya hay un autor con este codigo");
				response.sendRedirect(request.getContextPath() + "/AutoresController?op=listar");
			}

		} catch (IOException | SQLException ex) {
			System.out.println("Error en Insertar." + ex.getMessage());
		}
	}
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			Autor miAutor = modelo.obtenerAutor(Integer.parseInt(codigo));

			if (miAutor != null) {
				request.setAttribute("autor", miAutor);
				request.getRequestDispatcher("/autores/editarAutor.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (IOException | ServletException ex) {
			ex.getStackTrace();
		}
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {

			Autor miAutor = new Autor();
			miAutor.setIdAutor(Integer.parseInt(request.getParameter("idautor")));
			miAutor.setNombre(request.getParameter("nombre"));
			miAutor.setNacionalidad(request.getParameter("nacionalidad"));
			request.setAttribute("autor", miAutor);

			if (modelo.modificarAutor(miAutor) > 0) {
				request.getSession().setAttribute("exito", "autor modificado exitosamente");
				response.sendRedirect(request.getContextPath() + "/AutoresController?op=listar");
			} else {
				request.getSession().setAttribute("fracaso",
						"El autor no ha sido modificado" + "ya hay un autor con este codigo");
				response.sendRedirect(request.getContextPath() + "/AutoresController?op=listar");
			}

		} catch (IOException | SQLException ex) {
			System.out.println("Error en modificar." + ex.getMessage());
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			int idautor = Integer.parseInt(request.getParameter("id"));
			if (modelo.eliminarAutor(idautor) > 0) {
				request.setAttribute("exito", "Autor eliminado exitosamente");

			} else {
				request.setAttribute("fracaso", "No se puede eliminar este autor");
			}
			request.getRequestDispatcher("/AutoresController?op=listar").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			System.out.println("Error en eliminar." + ex.getMessage());
		}
	}

}

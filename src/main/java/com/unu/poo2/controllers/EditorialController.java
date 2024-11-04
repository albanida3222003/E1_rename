package com.unu.poo2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.unu.poo2.beans.Editorial;
import com.unu.poo2.model.EditorialModel;

public class EditorialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EditorialModel modelo = new EditorialModel();

	public EditorialController() {
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
			request.getRequestDispatcher("/editoriales/nuevoEditorial.jsp").forward(request, response);
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
			request.setAttribute("listaEditorial", modelo.listarEditorial());
			request.getRequestDispatcher("/editoriales/listaEditorial.jsp").forward(request, response);
		} catch (ServletException | IOException ex) {
			System.out.println("Error en Listar." + ex.getMessage());
		}

	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {

			Editorial miEditorial = new Editorial();
			miEditorial.setNombre(request.getParameter("nombre"));
			miEditorial.setContacto(request.getParameter("contacto"));
			miEditorial.setTelefono(request.getParameter("telefono"));

			if (modelo.insertarEditorial(miEditorial) > 0) {
				request.getSession().setAttribute("exito", "editorial registrado exitosamente");
				response.sendRedirect(request.getContextPath() + "/EditorialController?op=listar");
			} else {
				request.getSession().setAttribute("fracaso",
						"El editorial no ha sido ingresado" + "ya hay un editorial con este codigo");
				response.sendRedirect(request.getContextPath() + "/EditorialController?op=listar");
			}

		} catch (IOException | SQLException ex) {
			System.out.println("Error en Insertar." + ex.getMessage());
		}
	}
	
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			Editorial miEditorial = modelo.obtenerEditorial(Integer.parseInt(codigo));

			if (miEditorial != null) {
				request.setAttribute("editorial", miEditorial);
				request.getRequestDispatcher("/editoriales/editarEditorial.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (IOException | ServletException ex) {
			ex.getStackTrace();
		}
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {

			Editorial miEditorial = new Editorial();
			miEditorial.setIdeditorial(Integer.parseInt(request.getParameter("ideditorial")));
			miEditorial.setNombre(request.getParameter("nombre"));
			miEditorial.setContacto(request.getParameter("contacto"));
			miEditorial.setTelefono(request.getParameter("telefono"));
			request.setAttribute("editorial", miEditorial);

			if (modelo.modificarEditorial(miEditorial) > 0) {
				request.getSession().setAttribute("exito", "editorial modificado exitosamente");
				response.sendRedirect(request.getContextPath() + "/EditorialController?op=listar");
			} else {
				request.getSession().setAttribute("fracaso",
						"El editorial no ha sido modificado" + "ya hay un editorial con este codigo");
				response.sendRedirect(request.getContextPath() + "/EditorialController?op=listar");
			}

		} catch (IOException | SQLException ex) {
			System.out.println("Error en modificar." + ex.getMessage());
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			int ideditorial = Integer.parseInt(request.getParameter("id"));
			if (modelo.eliminarEditorial(ideditorial) > 0) {
				request.setAttribute("exito", "Editorial eliminado exitosamente");

			} else {
				request.setAttribute("fracaso", "No se puede eliminar este editorial");
			}
			request.getRequestDispatcher("/EditorialController?op=listar").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			System.out.println("Error en eliminar." + ex.getMessage());
		}
	}

}

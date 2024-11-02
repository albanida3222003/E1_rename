package com.unu.poo2.beans;

public class Autor {
	private int idAutor;
	private String nombre;
	private String nacionalidad;

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idaautor) {
		this.idAutor = idaautor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Autor(int idAutor, String nombre, String nacionalidad) {
		super();
		this.idAutor = idAutor;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	public Autor() {
		this(0, "", "");
	}
	
}

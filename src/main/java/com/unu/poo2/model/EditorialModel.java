package com.unu.poo2.model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unu.poo2.beans.Editorial;

public class EditorialModel extends Conexion{
	CallableStatement cs;
	ResultSet rs;
	
	public List<Editorial> listarEditorial(){
		try {
			List<Editorial> lista = new ArrayList<>();
			String sql = "CALL sp_listarEditorial()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Editorial edi =new Editorial();
				edi.setIdeditorial(rs.getInt("ideditorial"));
				edi.setNombre(rs.getString("nombre"));
				edi.setContacto(rs.getString("contacto"));
				edi.setTelefono(rs.getString("telefono"));
				lista.add(edi);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public int insertarEditorial(Editorial edi) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_insertarEditorial(?,?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setString(1, edi.getNombre());
			cs.setString(2, edi.getContacto());
			cs.setString(3, edi.getTelefono());
			this.cerrarConexion();
			return filasAfectadas; 
		} catch (Exception e) {
			// TODO: handle exception
			this.cerrarConexion();
			return 0;
		}
	}
	
	public Editorial obtenerEditorial(int ideditorial) {
		Editorial edi = new Editorial();
		try {
			String sql = "CALL sp_obtenerEditorial(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, ideditorial);
			rs = cs.executeQuery();
			if (rs.next()) {
				edi.setIdeditorial(rs.getInt("ideditorial"));
				edi.setNombre(rs.getString("nombre"));
				edi.setContacto(rs.getString("contacto"));
				edi.setTelefono(rs.getString("telefono"));
				this.cerrarConexion();
			}
		} catch (Exception e) {
			// TODO: handle exception
			this.cerrarConexion();
			return null;
		}
		return edi;
	}
	
	public int modificarEditorial(Editorial edi) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_modificarEditorial(?,?,?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, edi.getIdeditorial());
			cs.setString(2, edi.getNombre());
			cs.setString(3, edi.getContacto());
			cs.setString(4, edi.getTelefono());
			filasAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;
		} catch (Exception e) {
			// TODO: handle exception
			this.cerrarConexion();
			return 0;
		}
	}
	
	public int eliminarEditorial(int ideditorial) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_eliminarEditorial(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, ideditorial);
			filasAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;
		} catch (Exception e) {
			// TODO: handle exception
			this.cerrarConexion();
			return 0;
		}
	}
}

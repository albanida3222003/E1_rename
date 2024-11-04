package com.unu.poo2.model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unu.poo2.beans.Genero;

public class GeneroModel extends Conexion{
	CallableStatement cs;
	ResultSet rs;
	
	public List<Genero> listarGenero(){
		try {
			List<Genero> lista = new ArrayList<>();
			String sql = "CALL sp_listarGenero()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Genero genero =new Genero();
				genero.setIdgenero(rs.getInt("idgenero"));
				genero.setNombre(rs.getString("nombre"));
				genero.setDescripcion(rs.getString("descripcion"));
				lista.add(genero);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public int insertarGenero(Genero genero) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_insertarGenero(?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setString(1, genero.getNombre());
			cs.setString(2, genero.getDescripcion());
			filasAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas; 
		} catch (Exception e) {
			// TODO: handle exception
			this.cerrarConexion();
			return 0;
		}
	}
	
	public Genero obtenerGenero(int idgenero) {
		Genero genero = new Genero();
		try {
			String sql = "CALL sp_obtenerGenero(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idgenero);
			rs = cs.executeQuery();
			if (rs.next()) {
				genero.setIdgenero(rs.getInt("idgenero"));
				genero.setNombre(rs.getString("nombre"));
				genero.setDescripcion(rs.getString("descripcion"));
				this.cerrarConexion();
			}
		} catch (Exception e) {
			// TODO: handle exception
			this.cerrarConexion();
			return null;
		}
		return genero;
	}
	
	public int modificarGenero(Genero genero) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_modificarGenero(?,?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, genero.getIdgenero());
			cs.setString(2, genero.getNombre());
			cs.setString(3, genero.getDescripcion());
			filasAfectadas = cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;
		} catch (Exception e) {
			// TODO: handle exception
			this.cerrarConexion();
			return 0;
		}
	}
	
	public int eliminarGenero(int idgenero) throws SQLException{
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_eliminarGenero(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idgenero);
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

package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import com.example.demo.dto.Usuario;

public class UsuarioDao {

	private Connection conexion;

	/**
	 * permite loguear a un usuario en el sistema
	 * 
	 * @return boolean
	 */
	public boolean login(String name, String password) {
		boolean result = false;
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				String sql = "SELECT * FROM usuario WHERE name = ? AND password = ?";
				PreparedStatement consulta = conexion.prepareStatement(sql);
				consulta.setString(1, name);
				consulta.setString(2, password);
				ResultSet res = consulta.executeQuery();
				if (res.next()) {
					result = true;
				}
				res.close();
				consulta.close();
				Conexion.commit(conexion);
				return result;
			} catch (Exception e) {
				System.out.print(e.getMessage());
				Conexion.rollback(conexion);
			} finally {
				Conexion.cerrar(conexion);
			}
		}
		return result;
	}
	/**
	 * permite crear usuarios
	 * 
	 * @return String
	 */
	public Boolean crearUsuario(String name, String lastName, String accountName, String password) {
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				String sql = "INSERT INTO usuario (name, lastName, accountName,  password) VALUES (?,?,?,?)";
				PreparedStatement consulta = conexion.prepareStatement(sql);
				consulta.setString(1, name);
				consulta.setString(2, lastName);
				consulta.setString(3, accountName);
				consulta.setString(4, password);
				consulta.executeUpdate();
				consulta.close();
				Conexion.commit(conexion);
				return true;
			} catch (Exception e) {
				System.out.print(e.getMessage());
				Conexion.rollback(conexion);
			} finally {
				Conexion.cerrar(conexion);
			}
		}
		return false;

	}
	
	/**
	 * permite comprobar si existe un usuario ya creado, buscando por accountName
	 */
	public Boolean buscarUsuarioPorAccountName(String accountName) {
		boolean result = false;
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM usuario WHERE accountName = ?");
				consulta.setString(1, accountName);
				ResultSet res = consulta.executeQuery();
				if(res.next()) {
					result = true;
				}
				res.close();
				consulta.close();
				Conexion.commit(conexion);
			} catch (Exception e) {
				System.out.print(e.getMessage());
				Conexion.rollback(conexion);
			} finally {
				Conexion.cerrar(conexion);
			}
		}
		return result;
	}
	
	/**
	 * retorna el usuario buscado por id
	 */
	public ArrayList<Usuario> buscarUsuarioPorId(Integer id) {
		ArrayList<Usuario> usuario = new ArrayList<Usuario>();
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM usuario WHERE id = ?");
				consulta.setInt(1, id);
				ResultSet res = consulta.executeQuery();
				while (res.next()) {
					Usuario persona = new Usuario();
					persona.setName(res.getString("name"));
					persona.setLastName(res.getString("lastName"));
					persona.setAccount(res.getString("accountName"));
					usuario.add(persona);
				}
				res.close();
				consulta.close();
				Conexion.commit(conexion);
			} catch (Exception e) {
				System.out.print(e.getMessage());
				Conexion.rollback(conexion);
			} finally {
				Conexion.cerrar(conexion);
			}
		}
		return usuario;
	}
	
	/**
	 * 	permite listar usuarios
	 */
	public ArrayList<Usuario> listarUsuarios() {
		ArrayList<Usuario> usuario = new ArrayList<Usuario>();
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM usuario");
				ResultSet res = consulta.executeQuery();
				while (res.next()) {
					Usuario persona = new Usuario();
					persona.setId(Integer.parseInt(res.getString("id")));
					persona.setName(res.getString("name"));
					persona.setLastName(res.getString("lastName"));

					usuario.add(persona);
				}
				res.close();
				consulta.close();
				Conexion.commit(conexion);
			} catch (Exception e) {
				System.out.print(e.getMessage());
				Conexion.rollback(conexion);
			} finally {
				Conexion.cerrar(conexion);
			}
		}
		return usuario;
	}
	
	/**
	 * Permite actualizar un usuario
	 */
	public Boolean actualizarUsuario(String name, String lastName, String accountName, String password) {
		Boolean result = false;
		String sql = "";
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				if (password.isEmpty()) {
					sql = "UPDATE usuario SET name = ?, lastName = ?, accountName = ?";
					PreparedStatement consulta = conexion.prepareStatement(sql);
					consulta.setString(1, name);
					consulta.setString(2, lastName);
					consulta.setString(3, accountName);
					consulta.executeUpdate();
					consulta.close();
				} else {
					sql = "UPDATE usuario SET name = ?, lastName = ?, accountName = ?, password = ?";
					PreparedStatement consulta = conexion.prepareStatement(sql);
					consulta.setString(1, name);
					consulta.setString(2, lastName);
					consulta.setString(3, accountName);
					consulta.setString(4, password);
					consulta.executeUpdate();
					consulta.close();
				}
				result = true;
				Conexion.commit(conexion);
			} catch (Exception e) {
				System.out.print(e.getMessage());
				Conexion.rollback(conexion);
			} finally {
				Conexion.cerrar(conexion);
			}
		}
		return result;
	}
	
	/**
	 * Permite eliminar un usuario por id
	 */
	public Boolean eliminarUsuario(Integer id) {
		Boolean result = false;
		String sql = "";
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				sql = "DELETE FROM usuario WHERE id = ?";
				PreparedStatement consulta = conexion.prepareStatement(sql);
				consulta.setInt(1, id);
				consulta.executeUpdate();
				consulta.close();
				result = true;
				Conexion.commit(conexion);
			} catch (Exception e) {
				System.out.print(e.getMessage());
				Conexion.rollback(conexion);
			} finally {
				Conexion.cerrar(conexion);
			}
		}
		return result; 

	}
}

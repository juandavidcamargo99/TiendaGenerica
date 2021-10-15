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
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				String sql = "SELECT * FROM usuario WHERE name = ? AND password = ?";
				PreparedStatement consulta = conexion.prepareStatement(sql);
				consulta.setString(1, name);
				consulta.setString(2, password);
				ResultSet res = consulta.executeQuery();
				if (res.next()) {
					return true;
				}
				res.close();
				consulta.close();

				Conexion.commit(conexion);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n" + e);
				Conexion.rollback(conexion);
			} finally {
				Conexion.cerrar(conexion);
			}
		}
		return false;
	}

}

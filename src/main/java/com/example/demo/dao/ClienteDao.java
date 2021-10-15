package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import com.example.demo.dao.Conexion;

import javax.swing.JOptionPane;

import com.example.demo.dto.Usuario;

public class ClienteDao {

	private Connection conexion;

	/**
	 * permite consultar la lista de Clientes
	 * 
	 * @return ArrayList<Usuario>
	 */
	public ArrayList<Usuario> listaDeClientes() {
		ArrayList<Usuario> usuario = new ArrayList<Usuario>();
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM cliente");
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
				JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n" + e);
				Conexion.rollback(conexion);
			} finally {
				Conexion.cerrar(conexion);
			}
		}
		return usuario;
	}

}

package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import com.example.demo.dao.Conexion;

import javax.swing.JOptionPane;

import com.example.demo.dto.Cliente;
import com.example.demo.dto.Usuario;

public class ClienteDao {

	private Connection conexion;

	/**
	 * permite consultar la lista de Clientes
	 * 
	 * @return ArrayList<Cliente>
	 */
	public ArrayList<Cliente> listaDeClientes() {
		ArrayList<Cliente> cliente = new ArrayList<Cliente>();
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM cliente");
				ResultSet res = consulta.executeQuery();
				while (res.next()) {
					Cliente persona = new Cliente();
					persona.setId(Integer.parseInt(res.getString("id")));
					persona.setName(res.getString("name"));
					persona.setLastName(res.getString("lastName"));

					cliente.add(persona);
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
		return cliente;
	}
	
	/**
	 * Retorna el clientes por cedula
	 */
	public ArrayList<Usuario> buscarPorCedula(String cardId) {
		ArrayList<Usuario> usuario = new ArrayList<Usuario>();
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM cliente WHERE cardId = ?");
				consulta.setString(1, cardId);
				ResultSet res = consulta.executeQuery();
				while (res.next()) {
					Usuario persona = new Usuario();
					persona.setId(Integer.parseInt(res.getString("id")));
					persona.setName(res.getString("name"));
					persona.setLastName(res.getString("lastName"));
					persona.setCardId(res.getString("cardId"));
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
	 * permite crear clientes
	 * 
	 * @return Boolean
	 */
	public Boolean crearCliente(String name, String lastName, String cardId) {
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				String sql = "INSERT INTO cliente (name, lastName, cardId) VALUES (?,?,?)";
				PreparedStatement consulta = conexion.prepareStatement(sql);
				consulta.setString(1, name);
				consulta.setString(2, lastName);
				consulta.setString(3, cardId);
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

}

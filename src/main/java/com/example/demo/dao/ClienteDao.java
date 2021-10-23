package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import com.example.demo.dao.Conexion;

import javax.swing.JOptionPane;

import com.example.demo.dto.Cliente;

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
	 * retorna el cliente buscado por id
	 */
	public ArrayList<Cliente> buscarClientePorId(Integer id) {
		ArrayList<Cliente> cliente = new ArrayList<Cliente>();
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM cliente WHERE id = ?");
				consulta.setInt(1, id);
				ResultSet res = consulta.executeQuery();
				while (res.next()) {
					Cliente persona = new Cliente();
					persona.setId(res.getInt("id"));
					persona.setName(res.getString("name"));
					persona.setLastName(res.getString("lastName"));
					persona.setCardId(res.getString("cardId"));
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
	public ArrayList<Cliente> buscarPorCedula(String cardId) {
		ArrayList<Cliente> cliente = new ArrayList<Cliente>();
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM cliente WHERE cardId = ?");
				consulta.setString(1, cardId);
				ResultSet res = consulta.executeQuery();
				while (res.next()) {
					Cliente persona = new Cliente();
					persona.setId(Integer.parseInt(res.getString("id")));
					persona.setName(res.getString("name"));
					persona.setLastName(res.getString("lastName"));
					persona.setCardId(res.getString("cardId"));
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
	
	
	/**
	 * Permite actualizar un cliente
	 */
	public Boolean actualizarCliente(Integer id, String name, String lastName) {
		Boolean result = false;
		String sql = "";
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				sql = "UPDATE cliente SET name = ?, lastName = ? WHERE id = ?";
				PreparedStatement consulta = conexion.prepareStatement(sql);
				consulta.setString(1, name);
				consulta.setString(2, lastName);
				consulta.setInt(3, id);
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

	/**
	 * Permite eliminar un usuario por id
	 */
	public Boolean eliminarCliente(Integer id) {
		Boolean result = false;
		String sql = "";
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				sql = "DELETE FROM cliente WHERE id = ?";
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

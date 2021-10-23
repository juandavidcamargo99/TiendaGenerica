package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import com.example.demo.dao.Conexion;

import com.example.demo.dto.Cliente;
import com.example.demo.dto.Proveedor;
import com.example.demo.dto.Usuario;

public class ProveedorDao {
	private Connection conexion;

	/**
	 * permite consultar la lista de Proveedores
	 * 
	 * @return ArrayList<Proveedor>
	 */
	public ArrayList<Proveedor> listaDeProveedores() {
		ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM proveedor");
				ResultSet res = consulta.executeQuery();
				while (res.next()) {
					Proveedor persona = new Proveedor();
					persona.setId(Integer.parseInt(res.getString("id")));
					persona.setName(res.getString("name"));
					persona.setAddress(res.getString("address"));

					proveedor.add(persona);
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
		return proveedor;
	}
	
	/**
	 * retorna el proveedor buscado por id
	 */
	public ArrayList<Proveedor> buscarProvedorPorId(Integer id) {
		ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM proveedor WHERE id = ?");
				consulta.setInt(1, id);
				ResultSet res = consulta.executeQuery();
				while (res.next()) {
					Proveedor persona = new Proveedor();
					persona.setId(res.getInt("id"));
					persona.setName(res.getString("name"));
					persona.setAddress(res.getString("address"));
					persona.setCardId(res.getString("cardId"));
					proveedor.add(persona);
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
		return proveedor;
	}
	
	/**
	 * Retorna el proveedor por NIT
	 */
	public ArrayList<Proveedor> buscarPorNIT(String cardId) {
		ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM proveedor WHERE cardId = ?");
				consulta.setString(1, cardId);
				ResultSet res = consulta.executeQuery();
				while (res.next()) {
					Proveedor persona = new Proveedor();
					persona.setId(Integer.parseInt(res.getString("id")));
					persona.setName(res.getString("name"));
					persona.setAddress(res.getString("address"));
					persona.setCardId(res.getString("cardId"));
					proveedor.add(persona);
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
		return proveedor;
	}
	
	/**
	 * permite crear proveedor
	 * 
	 * @return Boolean
	 */
	public Boolean crearProveedor(String name, String address, String cardId) {
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				String sql = "INSERT INTO proveedor (name, address, cardId) VALUES (?,?,?)";
				PreparedStatement consulta = conexion.prepareStatement(sql);
				consulta.setString(1, name);
				consulta.setString(2, address);
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
	 * Permite actualizar un proveedor
	 */
	public Boolean actualizarProveedor(Integer id, String name, String address, String cardId) {
		Boolean result = false;
		String sql = "";
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				if (name.isEmpty()) {
					sql = "UPDATE proveedor SET name = ?, address = ?, cardId = ? WHERE id = ?";
					PreparedStatement consulta = conexion.prepareStatement(sql);
					consulta.setString(1, name);
					consulta.setString(2, address);
					consulta.setString(3, cardId);
					consulta.setInt(4, id);
					consulta.executeUpdate();
					consulta.close();
				} else {
					sql = "UPDATE proveedor SET name = ?, address = ?, cardId = ? WHERE id = ?";
					PreparedStatement consulta = conexion.prepareStatement(sql);
					consulta.setString(1, name);
					consulta.setString(2, address);
					consulta.setString(3, cardId);
					consulta.setInt(4, id);
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
	 * Permite eliminar un proveedor por id
	 */
	public Boolean eliminarProveedor(Integer id) {
		Boolean result = false;
		String sql = "";
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				sql = "DELETE FROM proveedor WHERE id = ?";
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

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

}

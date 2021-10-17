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

}

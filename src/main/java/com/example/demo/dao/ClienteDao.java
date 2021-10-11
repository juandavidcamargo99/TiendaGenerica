package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import com.example.demo.dto.Cliente;

public class ClienteDao {
	
	/**
	 * permite consultar la lista de Clientes
	 * 
	 * @return
	 */
	public ArrayList<Cliente> listaDeClientes() {
		ArrayList<Cliente> cliente = new ArrayList<Cliente>();
		Conexion conex = new Conexion();
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM cliente");
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				Cliente persona = new Cliente();
				persona.setId(Integer.parseInt(res.getString("id")));
				persona.setNombre(res.getString("nombre"));
				persona.setApellido(res.getString("apellido"));

				cliente.add(persona);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n" + e);
		}
		return cliente;
	}
	
}

package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.example.demo.dto.Producto;

public class ProductoDao {
	
	private Connection conexion;
	
	/**
	 * permite consultar la lista de Producto
	 * 
	 * 
	 * @return ArrayList<>
	 */
	public ArrayList<Producto> listaDeProductos() {
		ArrayList<Producto> Producto = new ArrayList<Producto>();
		conexion = Conexion.conectar();
		if (Conexion.AutoCommit(conexion)) {
			try {
				PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM producto");
				ResultSet res = consulta.executeQuery();
				while (res.next()) {
					Producto alimento = new Producto();
					alimento.setId(res.getInt("id"));
					alimento.setCode(res.getString("code"));
					alimento.setName(res.getString("name"));
					alimento.setNit(res.getInt("nit"));
					alimento.setPrecioCompra(res.getDouble("precioCompra"));
					alimento.setIva(res.getInt("iva"));
					alimento.setPrecioVenta(res.getDouble("precioVenta"));
					Producto.add(alimento);
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
		return Producto;
	}
}

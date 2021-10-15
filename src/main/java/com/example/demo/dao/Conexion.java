package com.example.demo.dao;

import java.sql.*;

/**
 * Clase que permite conectar con la base de datos
 * 
 *
 */
public class Conexion {

	/** Parametros de conexion */
	static String bd = "reto4";
	static String login = "mintictest";
	static String password = "mintictest123";
	static String url = "jdbc:mysql://localhost/" + bd;

	public static Connection conectar() {
		
		Connection connection = null;
		try {
			// obtenemos el driver de para mysql
			Class.forName("com.mysql.cj.jdbc.Driver");
			// obtenemos la conexión
			connection = (Connection) DriverManager.getConnection(url, login, password);

			if (connection != null) {
				System.out.println("Conexión a base de datos " + bd + " OK\n");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return connection;
		}
	}
	
	public static boolean AutoCommit(Connection conexion){
        try{ 
            conexion.setAutoCommit(false);
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public static void commit(Connection conexion){
        try{
            conexion.commit();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
        
        
    public static void rollback(Connection conexion){
        try{
            conexion.rollback();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
        
        
    public static void cerrar(Connection conexion){
        try{
            conexion.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
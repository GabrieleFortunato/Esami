package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Classe InizializzaDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Inizializzazione {
	
	/**
	 * Localhost
	 */
	final static String URL= "jdbc:mysql://localhost:3306/";
	
	/**
	 * Nome  del database
	 */
	final static String DBNAME = "esamiprogrammazione"+"?autoReconnect=true&useSSL=false";
	
	/**
	 * Driver
	 */
	final static String DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * Metodo costruttore
	 */
	private Inizializzazione(){
		
	}
	
	/**
	 * Inizializza il database
	 * @throws NamingException 
	 * @throws SQLException
	 */
	public static void inizializzaDatabase() throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		conn = DriverManager.getConnection(
				URL+DBNAME,Utility.user(),Utility.pass()
		);
		st = (PreparedStatement) conn.prepareStatement(
				"create database if not exists esamiprogrammazione"
		);
		int res = st.executeUpdate();
		PreparedStatement st1 = (PreparedStatement) conn.prepareStatement(
				"use esamiprogrammazione"
		);
		Logger.getLogger(Integer.toString(res));
		int res1 = st1.executeUpdate();
		Logger.getLogger(Integer.toString(res1));
		PreparedStatement st2 = (PreparedStatement) conn.prepareStatement(
				"create table if not exists candidato("+
				"id int auto_increment primary key,"+
				"nome varchar(60),"+
				"cognome varchar(60),"+
				"unique (nome,cognome)"+
				");"
		);
		int res2 = st2.executeUpdate();
		Logger.getLogger(Integer.toString(res2));
		PreparedStatement st3 = (PreparedStatement) conn.prepareStatement(
				"create table if not exists teoria("+
				"candidato int primary key,"+
				"esito varchar(60),"+
				"foreign key constaint (candidato) references candidato(id) on delete cascade"+
				");"
		);
		int res3 = st3.executeUpdate();
		Logger.getLogger(Integer.toString(res3));
		PreparedStatement st4 = (PreparedStatement) conn.prepareStatement(
				"create table if not exists progetto("+
				"candidato int primary key,"+
				"libreria int,"+
				"test int,"+
				"main int,"+
				"foreign key constaint (candidato) references candidato(id) on delete cascade"+
				");"
		);
		int res4 = st4.executeUpdate();
		Logger.getLogger(Integer.toString(res4)); 
		st.close();
		conn.close();
	}

}
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
	final static String url = "jdbc:mysql://localhost:3306/";
	
	/**
	 * Nome  del database
	 */
	final static String dbName = "esamiprogrammazione"+"?autoReconnect=true&useSSL=false";
	final static String driver = "com.mysql.jdbc.Driver";
	
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
	public static void inizializzaDatabase() {
		try {
			Connection conn = DriverManager.getConnection(
					url+dbName,Utility.user(),Utility.pass()
			);
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(
					"create database if not exists esamiprogrammazione"
			);
			@SuppressWarnings("unused")
			int res = st.executeUpdate();
			PreparedStatement st1 = (PreparedStatement) conn.prepareStatement(
					"use esamiprogrammazione"
			);
			@SuppressWarnings("unused")
			int res1 = st1.executeUpdate();
			PreparedStatement st2 = (PreparedStatement) conn.prepareStatement(
					"create table if not exists candidato("+
					"id int auto_increment primary key,"+
					"nome varchar(60),"+
					"cognome varchar(60),"+
					"unique (nome,cognome)"+
					");"
			);
			@SuppressWarnings("unused")
			int res2 = st2.executeUpdate();
			PreparedStatement st3 = (PreparedStatement) conn.prepareStatement(
					"create table if not exists teoria("+
					"candidato int primary key,"+
					"esito varchar(60),"+
					"foreign key constaint (candidato) references candidato(id) on delete cascade"+
					");"
			);
			@SuppressWarnings("unused")
			int res3 = st3.executeUpdate();
			PreparedStatement st4 = (PreparedStatement) conn.prepareStatement(
					"create table if not exists progetto("+
					"candidato int primary key,"+
					"libreria int,"+
					"test int,"+
					"main int,"+
					"foreign key constaint (candidato) references candidato(id) on delete cascade"+
					");"
			);
			@SuppressWarnings("unused")
			int res4 = st4.executeUpdate();
			st.close();
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog (
					null , "Problemi di connessione con il database"
			);
		}
	}

}
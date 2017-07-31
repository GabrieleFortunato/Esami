package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe InizializzaDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Inizializzazione {
	
	private Inizializzazione(){
		
	}
	
	private final static String url = "jdbc:mysql://localhost:3306/";
	private final static String dbName = "esamiprogrammazione";
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String userName = dbUtility.stringaUser(); 
	private final static String password = dbUtility.stringaPass();
	
	/**
	 * Inizializza il database
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void inizializzaDatabase() 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			Class.forName(driver).newInstance();
			String str = "?autoReconnect=true&useSSL=false";
			Connection conn = DriverManager.getConnection(
					url+dbName+str,userName,password
			);
			Statement st = conn.createStatement();
			@SuppressWarnings("unused")
			int res = st.executeUpdate(
					"create database if not exists esamiprogrammazione"		
			);
			@SuppressWarnings("unused")
			int res1 = st.executeUpdate(
					"use esamiprogrammazione"
			);
			@SuppressWarnings("unused")
			int res2 = st.executeUpdate(
					"create table if not exists candidato("+
					"id int auto_increment primary key,"+
					"nome varchar(60),"+
					"cognome varchar(60),"+
					"unique (nome,cognome)"+
					");"
			);
			@SuppressWarnings("unused")
			int res3 = st.executeUpdate("create table if not exists teoria("+
					"candidato int primary key,"+
					"esito varchar(60),"+
					"foreign key constaint (candidato) references candidato(id) on delete cascade"+
					");"
			);
			@SuppressWarnings("unused")
			int res4 = st.executeUpdate(
					"create table if not exists progetto("+
					"candidato int primary key,"+
					"libreria int,"+
					"test int,"+
					"main int,"+
					"foreign key constaint (candidato) references candidato(id) on delete cascade"+
					");"
			);
	}

}
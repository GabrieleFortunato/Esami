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
public class InizializzaDatabase {
	
	public InizializzaDatabase(){
		
	}
	
	private final static String url = "jdbc:mysql://localhost:3306/";
	private final static String dbName = "esamiprogrammazione";
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String userName = "root"; 
	private final static String password = "qrnq946";
	
	public static void inizializzaDatabase() 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(
					url+dbName+"?autoReconnect=true&useSSL=false",userName,password
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
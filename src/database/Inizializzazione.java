package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classe InizializzaDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Inizializzazione {
	
	private final static String url = "jdbc:mysql://localhost:3306/";
	private final static InitialContext context = context();
	private final static DataSource ds = ds(context);
	
	private Inizializzazione(){
		
	}
	
	private static InitialContext context(){
		try {
			return new InitialContext();
		} catch (NamingException e) {
			
		} 
		return null;
	}
	
	private static DataSource ds(InitialContext context){
		try {
			return (DataSource) context.lookup(url);
		} catch (NamingException e) {
		
		}
		return null;
	}
	
	/**
	 * Inizializza il database
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static void inizializzaDatabase() throws SQLException {
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		int res = st.executeUpdate(
				"create database if not exists esamiprogrammazione"		
		);	
		Logger.getLogger(Integer.toString(res));
		int res1 = st.executeUpdate(
				"use esamiprogrammazione"
		);
		Logger.getLogger(Integer.toString(res1));
		int res2 = st.executeUpdate(
				"create table if not exists candidato("+
				"id int auto_increment primary key,"+
				"nome varchar(60),"+
				"cognome varchar(60),"+
				"unique (nome,cognome)"+
				");"
		);
		Logger.getLogger(Integer.toString(res2));
		int res3 = st.executeUpdate("create table if not exists teoria("+
				"candidato int primary key,"+
				"esito varchar(60),"+
				"foreign key constaint (candidato) references candidato(id) on delete cascade"+
				");"
		);
		Logger.getLogger(Integer.toString(res3));
		int res4 = st.executeUpdate(
				"create table if not exists progetto("+
				"candidato int primary key,"+
				"libreria int,"+
				"test int,"+
				"main int,"+
				"foreign key constaint (candidato) references candidato(id) on delete cascade"+
				");"
		);
		Logger.getLogger(Integer.toString(res4));
		st.close();
		conn.close();
	}

}
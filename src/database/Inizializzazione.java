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
	
	private Inizializzazione(){
		
	}
	
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static InitialContext context = getContext();
	private final static DataSource ds = getDs(context);
	
	private final static InitialContext getContext(){
		InitialContext context1 = null;
		try {
			context1 = new InitialContext();
		} catch (NamingException e) {
			Logger.getLogger("Eccezione sollevata");
		}
		return context1;
	}
	
	private final static DataSource getDs(InitialContext context){
		try {
			DataSource ds1 = (DataSource) context.lookup(driver);
			return ds1;
		} catch (NamingException e) {
			Logger.getLogger("Eccezione sollevata");
		}
		return null;
	}
	
	/**
	 * Inizializza il database
	 * @throws NamingException 
	 * @throws SQLException
	 */
	public static void inizializzaDatabase() throws NamingException, SQLException {
		Connection conn = ds.getConnection();
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
		st.close();
		conn.close();
	}

}
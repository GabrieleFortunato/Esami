package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Classe CancellazioneDaDatabase
 * @author Gabriele Fortunato
 */
public class Cancellazione {
	
	/**
	 * Localhost
	 */
	final static String URL = "jdbc:mysql://localhost:3306/";
	
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
	private Cancellazione(){
		
	}
	
	/**
	 * Cancella un candidato dal database
	 * @param nome
	 * @param cognome
	 * @throws SQLException 
	 */
	public static void cancellaCandidato(String nome, String cognome) throws SQLException{
		Connection conn = null;
		PreparedStatement st = null;
		String sql = "delete from candidato where (nome=? and cognome=?)";
		conn = DriverManager.getConnection(URL+DBNAME,Utility.user(),Utility.pass());
		st = (PreparedStatement) conn.prepareStatement(sql);
		st.setString(1, nome);
		st.setString(2, cognome);
		int res = st.executeUpdate();
		Logger.getLogger(Integer.toString(res));
		st.close();
		conn.close();
	} 

	/**
	 * Cancella un candidato dal database
	 * @param nome
	 * @param cognome
	 * @throws SQLException 
	 */
	public static void cancellaDatabase() throws SQLException{
		Connection conn;
		PreparedStatement st;
		String sql = "drop database if exists esamiprogrammazione";
		conn = DriverManager.getConnection(URL,Utility.user(),Utility.pass());
		st = (PreparedStatement) conn.prepareStatement(sql);
		int res = st.executeUpdate(sql);
		Logger.getLogger(Integer.toString(res));
		st.close();
		conn.close();
	}

}



package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 * Classe InserimentoNelDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Inserimento {
	
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
	private Inserimento(){
		
	}
	
	/**
	 * Inserisci la prenotazione
	 * @param nome
	 * @param cognome
<<<<<<< HEAD
	 * @throws SQLException 
	 */
	public static void inserisciPrenotazione(String nome, String cognome) 
			throws SQLException{
		Connection conn = null;
		PreparedStatement st = null;
		conn = DriverManager.getConnection(
				URL+DBNAME,Utility.user(),Utility.pass()
		);
		st = (PreparedStatement) conn.prepareStatement(
				"insert into candidato (nome,cognome) values(?,?)"
		);
		st.setString(1, nome);
		st.setString(2, cognome);
		int res = st.executeUpdate();
		Logger.getLogger(Integer.toString(res));
		st.close();
		conn.close();
	}	
	
	/**
	 * Inserimento nel database del voto della prova teorica
	 * @param nome
	 * @param cognome
	 * @param teoria
	 * @throws SQLException 
	 * @throws NamingException 
	 */
	public static void inserisciEsitoTeoria(String nome, String cognome, String teoria) 
			throws SQLException, NamingException{
		Connection conn = null;
		PreparedStatement st = null;
		String id = Integer.toString(Lettura.id(nome, cognome));
		conn = DriverManager.getConnection(
				URL+DBNAME,Utility.user(),Utility.pass()
		);
		st = (PreparedStatement) conn.prepareStatement(
						"insert ignore into teoria values (?,?)"
		);
		st.setString(1, id);
		st.setString(2, teoria);
		int res = st.executeUpdate();
		Logger.getLogger(Integer.toString(res));
		System.out.println("Esito teoria inserito");
		if (st!=null){
			st.close();
		}
		if (conn!=null){
			conn.close();
		}	
	}
		
	/**
	 * Inserisci nel database l'esito del progetto 
	 * svolto da un candidato
	 * @param c
	 * @param p
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public static void inserisciEsitoProgetto(
			String nome, String cognome, String libr, String test, String main
			) throws SQLException, NamingException{
		Connection conn = null;
		PreparedStatement st = null;
		String id = Integer.toString(Lettura.id(nome,cognome));
		conn = DriverManager.getConnection(
				URL+DBNAME,Utility.user(),Utility.pass()
		);
		st = (PreparedStatement) conn.prepareStatement(
				"insert ignore into progetto values(?,?,?,?)"
		);
		st.setString(1, id);
		st.setString(2, libr);
		st.setString(3, test);
		st.setString(4, main);
		int res = st.executeUpdate();
		Logger.getLogger(Integer.toString(res));
		st.close();
		conn.close();
		System.out.println("Esito progetto inserito"); 
		if (st!=null&&conn!=null){
			st.close();
			conn.close();		
		}
	}

}
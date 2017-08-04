package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
	 */
	public static void inserisciPrenotazione(String nome, String cognome){
		Connection conn = null;
		PreparedStatement st = null;
		String a = Utility.stringForQuery(nome);
		String b = Utility.stringForQuery(cognome);
		try {
			conn = DriverManager.getConnection(
					URL+DBNAME,Utility.user(),Utility.pass()
			);
			st = (PreparedStatement) conn.prepareStatement(
					"insert into candidato (nome,cognome) values(?,?)"
			);
			st.setString(1, a);
			st.setString(2, b);
			int res = st.executeUpdate();
			System.out.println("Candidato inserito");
			Logger.getLogger(Integer.toString(res));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog (
					null , "Problemi di connessione con il database"
			);
		} finally {
			if (st!=null&&conn!=null){
				try {
					st.close();
					conn.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog (
							null , "Problemi di connessione con il database"
					);
				}
			}
		}
	}	
	
	/**
	 * Inserimento nel database del voto della prova teorica
	 * @param nome
	 * @param cognome
	 * @param teoria
	 */
	public static void inserisciEsitoTeoria(String nome, String cognome, String teoria){
		Connection conn = null;
		PreparedStatement st = null;
		String a = Utility.stringForQuery(nome);
		String b = Utility.stringForQuery(cognome);
		String id = Integer.toString(Lettura.id(a, b));
		try {
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
		} catch (SQLException e) {
			JOptionPane.showMessageDialog (
					null , "Problemi di connessione con il database"
			);
		} finally{
			try {
				if (st!=null){
					st.close();
				}
				if (conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog (
						null , "Problemi di connessione con il database"
				);
			}
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
	public static void inserisciEsitoProgetto(String nome, String cognome, String libr, String test, String main){
		Connection conn = null;
		PreparedStatement st = null;
		String a = Utility.stringForQuery(nome);
		String b = Utility.stringForQuery(cognome);
		try {
			String id = Integer.toString(Lettura.id(a, b));
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
		} catch (SQLException e) {
			JOptionPane.showMessageDialog (
					null , "Problemi di connessione con il database"
			);
		} finally {
			if (st!=null&&conn!=null){
				try {
					st.close();
					conn.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog (
							null , "Problemi di connessione con il database"
					);
				}
			}
		}
		
	}

	
}

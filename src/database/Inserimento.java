package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
	final static String url = "jdbc:mysql://localhost:3306/";
	final static String dbName = "esamiprogrammazione"+"?autoReconnect=true&useSSL=false";
	final static String driver = "com.mysql.jdbc.Driver";
	
	/**
	 * Metodo costruttore
	 */
	private Inserimento(){
		
	}
	
	public static void inserisciPrenotazione(String nome, String cognome){
		nome = Utility.stringForQuery(nome);
		cognome = Utility.stringForQuery(cognome);
		try {
			Connection conn = DriverManager.getConnection(
					url+dbName,Utility.user(),Utility.pass()
			);
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(
					"insert into candidato (nome,cognome) values(?,?)"
			);
			st.setString(1, nome);
			st.setString(2, cognome);
			int res = st.executeUpdate();
			Logger.getLogger(Integer.toString(res));
			st.close();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog (
					null , "Problemi di lettura da file"
			);
		}
	}	
	
	public static void inserisciEsitoTeoria(String nome, String cognome,String teoria){
		nome = Utility.stringForQuery(nome);
		cognome = Utility.stringForQuery(cognome);
		teoria = Utility.stringForQuery(teoria);
		String id = Integer.toString(Lettura.id(nome, cognome));
		try {
			Connection conn = DriverManager.getConnection(
					url+dbName,Utility.user(),Utility.pass()
			);
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(
							"insert ignore into teoria values (?,?)"
			);
			st.setString(1, id);
			st.setString(2, teoria);
			int res = st.executeUpdate();
			Logger.getLogger(Integer.toString(res));
			st.close();
			conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog (
					null , "Problemi di lettura da file"
			);
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
		try {
			nome = Utility.stringForQuery(nome);
			cognome = Utility.stringForQuery(cognome);
			String id = Integer.toString(Lettura.id(nome, cognome));
			Connection conn = DriverManager.getConnection(
					url+dbName,Utility.user(),Utility.pass()
			);
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(
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
		}
		
	}

	
}

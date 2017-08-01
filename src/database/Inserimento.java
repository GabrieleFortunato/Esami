package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import utility.Utility;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

/**
 * Classe InserimentoNelDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Inserimento {

	/**
	 * Metodo costruttore
	 */
	private Inserimento(){
		
	}
	
	private final static String driver = "com.mysql.jdbc.Driver";
	private static DataSource ds = null;
	
	/**
	 * Inserisci nel database l'esito del progetto 
	 * svolto da un candidato
	 * @param c
	 * @param p
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void inserisciEsitoProgetto(String nome, String cognome, 
			String lib, String test, String main) {
		Connection conn = null;	
		Statement st = null;
		try {
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup(driver);
			conn = ds.getConnection();	
			st = conn.createStatement();
			int res = st.executeUpdate(
					"insert ignore into progetto values"
					+ "((select id from candidato where nome='"+Utility.stringForQuery(nome)
					+"' and cognome='"+Utility.stringForQuery(cognome)+"'),"+lib+","+test+","+main+")"
			);
			Logger.getLogger(Integer.toString(res));
		} catch (NamingException | SQLException e) {
			JOptionPane.showMessageDialog (
					null , "Impossibile inserire nel database l'esito della teoria"
			);
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog (
						null , "Problemi di connesion con il database"
				);
			}
		}
		
	}

	/**
	 * Inserimento prenotazione
	 * @param nome
	 * @param cognome
	 */
	public static void inserisciPrenotazione(String nome, String cognome) {
		try {
			Context context = new InitialContext();
			javax.sql.DataSource ds =(javax.sql.DataSource)context.lookup(driver);
			Connection conn = ds.getConnection();
			Statement st = conn.createStatement();
			int res = st.executeUpdate(
					"insert ignore into candidato (nome,cognome) values ('"+nome+"','"+cognome+"')"
			);
			Logger.getLogger(Integer.toString(res));
		} catch (NamingException e) {
			JOptionPane.showMessageDialog (
				null , "Naming exception"
			);
		}
			 catch (SQLException e) {
				 JOptionPane.showMessageDialog (
				null , "SQL exception"
			);	
		} 
	}
	
	public static void inserisciEsitoTeoria(String nome, String cognome, String teoria) {
		Connection conn = null;	
		Statement st = null;
		try {
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup(driver);
			conn = ds.getConnection();	
			st = conn.createStatement();
			int res = st.executeUpdate(
					"insert ignore into teoria values ((select id from candidato where nome='"+
					Utility.stringForQuery(nome)+"' and cognome='"+
					Utility.stringForQuery(cognome)+"'),'"+
					Utility.stringForQuery(teoria)+"')"
			);
			Logger.getLogger(Integer.toString(res));
		} catch (NamingException | SQLException e) {
			JOptionPane.showMessageDialog (
					null , "Impossibile inserire nel database l'esito della teoria"
			);
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog (
						null , "Problemi di collegamento con il database"
				);
			}
		}
	}
	
}

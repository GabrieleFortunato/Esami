package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import utility.Utility;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
	public static void inserisciEsitoProgetto(String nome, String cognome, String lib, String test, String main) 
			throws NamingException, SQLException, InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(driver);
		Connection conn = ds.getConnection();	
		Statement st = conn.createStatement();
		int res = st.executeUpdate(
				"insert ignore into progetto values"
				+ "((select id from candidato where nome='"+Utility.stringForQuery(nome)
				+"' and cognome='"+Utility.stringForQuery(cognome)+"'),"+lib+","+test+","+main+")"
		);
		Logger.getLogger(Integer.toString(res));
		st.close();
		conn.close();
		
	}

	/**
	 * Inserimento prenotazione
	 * @param nome
	 * @param cognome
	 */
	public static void inserisciPrenotazione(String nome, String cognome) {
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup(driver);
			Connection conn = ds.getConnection();	
			Statement st = conn.createStatement();
			int res = st.executeUpdate(
					"insert ignore into candidato (nome,cognome) values ('"+nome+"','"+cognome+"')"
			);
			Logger.getLogger(Integer.toString(res));
			st.close();
			conn.close();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void inserisciEsitoTeoria(String nome, String cognome, String teoria) 
			throws NamingException, SQLException {
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(driver);
		Connection conn = ds.getConnection();	
		Statement st = conn.createStatement();
		int res = st.executeUpdate(
				"insert ignore into teoria values ((select id from candidato where nome='"+
				Utility.stringForQuery(nome)+"' and cognome='"+
				Utility.stringForQuery(cognome)+"'),'"+
				Utility.stringForQuery(teoria)+"')"
		);
		Logger.getLogger(Integer.toString(res));
		st.close();
		conn.close();
	}
	
}

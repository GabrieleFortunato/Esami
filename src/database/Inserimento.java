package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import candidati.Candidato;
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

	private Inserimento(){
		
	}
	
	private final static String driver = "com.mysql.jdbc.Driver";
	private static InitialContext context = context();
	private static DataSource ds = ds(context);
	
	private static InitialContext context(){
		InitialContext context = null;
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			
		}
		return context;
	}
	
	private static DataSource ds(InitialContext context){
		try {
			ds = (DataSource) context.lookup(driver);
		} catch (NamingException e) {
		
		}
		return ds;
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
			) throws NamingException, SQLException  {
		ds = (DataSource) context.lookup(driver);
		Connection conn = ds.getConnection();	
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"insert ignore into progetto values"
				+ "((select id from candidato where nome='"+Utility.stringForQuery(nome)
				+"' and cognome='"+Utility.stringForQuery(cognome)
				+"'),"+libr+","+test+","+main+")"
		);
		st.close();
		conn.close();
		
	}

	public static void inserisciPrenotazione(Candidato c) throws SQLException, NamingException {
		ds = (DataSource) context.lookup(driver);
		Connection conn = ds.getConnection();	
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"insert ignore into candidato (nome,cognome) values ('"+
				c.getNome()+"','"+c.getCognome()+"')"
		);
		st.close();
		conn.close();
			
	}
	
	public static void inserisciEsitoTeoria(Candidato c) throws NamingException, SQLException {
		ds = (DataSource) context.lookup(driver);
		Connection conn = ds.getConnection();	
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"insert ignore into teoria values ((select id from candidato where nome='"+
				Utility.stringForQuery(c.getNome())+"' and cognome='"+
				Utility.stringForQuery(c.getCognome())+"'),'"+
				Utility.stringForQuery(c.getEsitoTeoria())+"')"
		);
		st.close();
		conn.close();
	}
	
}

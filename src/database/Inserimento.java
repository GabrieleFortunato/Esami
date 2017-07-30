package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

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

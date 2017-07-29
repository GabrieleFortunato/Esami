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

	private final static String url = "jdbc:mysql://localhost:3306/";
	private static InitialContext context;
	private static DataSource ds;
	
	private Inserimento(){
		try {
			context = new InitialContext();ds = 
			(DataSource) context.lookup(url);
		} catch (NamingException e) {
			Logger.getLogger("Eccezione");
		}
	}
	
	/**
	 * Inserimento nel database dell'esito di un progetto
	 * @param c
	 * @param p
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static void inserisciEsitoProgetto(String nome, String cognome, String libreria, 
			String test, String main) throws SQLException {
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"insert ignore into progetto values"
				+ "((select id from candidato where nome='"+Utility.stringForQuery(nome)
				+"' and cognome='"+Utility.stringForQuery(cognome)
				+"'),"+libreria+","+test+","+main+")"
		);
	}

	public static void inserisciPrenotazione(Candidato c) throws SQLException {
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"insert ignore into candidato (nome,cognome) values ('"+
				c.getNome()+"','"+c.getCognome()+"')"
		);
	}
	
	public static void inserisciEsitoTeoria(Candidato c) throws SQLException{
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"insert ignore into teoria values ((select id from candidato where nome='"
				+Utility.stringForQuery(c.getNome())
				+"' and cognome='"+Utility.stringForQuery(c.getCognome())
				+"'),'"+Utility.stringForQuery(c.getEsitoTeoria())
				+"')"
		);
	}
	
}
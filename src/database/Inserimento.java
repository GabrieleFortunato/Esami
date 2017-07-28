package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import candidati.Candidato;
import candidati.Progetto;
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
	
	/**
	 * Inserisci nel database l'esito del progetto 
	 * svolto da un candidato
	 * @param c
	 * @param p
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void inserisciEsitoProgetto(Candidato c, Progetto p) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup(driver);
			Connection conn = ds.getConnection();	
			Statement st = conn.createStatement();
			@SuppressWarnings("unused")
			int res = st.executeUpdate(
					"insert ignore into progetto values"
					+ "((select id from candidato where nome='"+Utility.stringForQuery(c.getNome())
					+"' and cognome='"+Utility.stringForQuery(c.getCognome())
					+"'),"+p.getLibreria()+","+p.getTest()+","+p.getMain()+")"
			);
			st.close();
			conn.close();
		} catch (NamingException e) {
		}
		
	}

	public static void inserisciPrenotazione(Candidato c) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			try {
				InitialContext context = new InitialContext();
				DataSource ds = (DataSource) context.lookup(driver);
				Connection conn = ds.getConnection();	
				Statement st = conn.createStatement();
				@SuppressWarnings("unused")
				int res = st.executeUpdate(
						"insert ignore into candidato (nome,cognome) values ('"
						+c.getNome()+"','"+c.getCognome()+"')"
				);
				st.close();
				conn.close();
			} catch (NamingException e) {
				
			}
	}
	
	public static void inserisciEsitoTeoria(Candidato c) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup(driver);
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
		} catch (NamingException e) {
			
		}
	}
	
}

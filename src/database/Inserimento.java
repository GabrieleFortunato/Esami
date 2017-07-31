package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import candidati.Candidato;
import candidati.Progetto;
import utility.Utility;

import javax.naming.Context;
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
	private static DataSource ds;
	
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
	public static void inserisciEsitoProgetto(Candidato c, Progetto p) 
			throws NamingException, SQLException, InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Class.forName(driver).newInstance();
		InitialContext context = new InitialContext();
		ds = (DataSource) context.lookup(driver);
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
		
	}

	@SuppressWarnings("unchecked")
	public static void inserisciPrenotazione(Candidato c) {
		try {
			@SuppressWarnings("rawtypes")
			Hashtable env = new Hashtable(11);
			env.put(Context.INITIAL_CONTEXT_FACTORY, 
			    "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "jdbc:mysql://localhost:3306/");

			// Enable connection pooling
			env.put("com.sun.jndi.ldap.connect.pool", "true");
			InitialContext context = new InitialContext();
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
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void inserisciEsitoTeoria(Candidato c) throws NamingException, SQLException {
		InitialContext context = new InitialContext();
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

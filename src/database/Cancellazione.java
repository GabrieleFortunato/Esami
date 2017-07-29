package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import candidati.Candidato;

/**
 * Classe CancellazioneDaDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Cancellazione {

	private final static String url = "jdbc:mysql://localhost:3306/";
	private static InitialContext context;
	private static DataSource ds;
	
	private Cancellazione(){
		try {
			context = new InitialContext();ds = 
			(DataSource) context.lookup(url);
		} catch (NamingException e) {
			Logger.getLogger("Eccezione");
		}
	}
	
	/**
	 * Pulisce tutto il database
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static void puliziaDatabase() throws SQLException {
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"delete from candidato"
		);
	}

	/**
	 * Cancella un solo database
	 * @param c
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static void cancellaCandidato(Candidato c) throws SQLException{
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"delete from candidato where (nome='"+c.getNome()+"' and cognome='"+c.getCognome()+"')"
		);
	} 
}



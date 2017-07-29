package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import candidati.Candidato;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classe CancellazioneDaDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Cancellazione {
	
	private Cancellazione(){
	
	}
	
	private final static String driver = "com.mysql.jdbc.Driver";
	private static DataSource ds;
	
	/**
	 * Cancella un candidato dal database
	 * @param c
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static void cancellaCandidato(Candidato c) throws NamingException, SQLException {
		InitialContext context = new InitialContext();
		ds = (DataSource) context.lookup(driver);
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"delete from candidato where (nome='"+c.getNome()+
				"' and cognome='"+c.getCognome()+"')"
		);
		st.close();
		conn.close();
	} 
}



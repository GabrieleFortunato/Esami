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
	 * Cancella un candidato dal database
	 * @param c
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static void cancellaCandidato(Candidato c) throws SQLException {
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



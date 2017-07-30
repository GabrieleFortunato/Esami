package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

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



package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	private Cancellazione(){
	
	}
	
	private final static String url = "jdbc:mysql://localhost:3306/";
	
	public static void puliziaDatabase() throws NamingException, SQLException {
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(url);
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"delete from candidato"
		);
	}

	public static void cancellaCandidato(Candidato c) throws NamingException, SQLException{
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(url);
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"delete from candidato where (nome='"+c.getNome()+"' and cognome='"+c.getCognome()+"')"
		);
	} 
}



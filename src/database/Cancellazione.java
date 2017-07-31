package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
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

	/**
	 * Metodo costruttore
	 */
	private Cancellazione(){
	
	}
	
	private final static String driver = "com.mysql.jdbc.Driver";
	private static DataSource ds;
	
	/**
	 * Cancella un candidato dal database
	 * @param c
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void cancellaCandidato(String nome, String cognome) throws NamingException, SQLException, 
	InstantiationException, IllegalAccessException, ClassNotFoundException {
		InitialContext context = new InitialContext();
		ds = (DataSource) context.lookup(driver);
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		String sql = 
				"delete from candidato where (nome='"+nome+"' and cognome='"+cognome+"')";
		int res = st.executeUpdate(sql);
		Logger.getLogger(Integer.toString(res));
		st.close();
		conn.close();
	} 
}



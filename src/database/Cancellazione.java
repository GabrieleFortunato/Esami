package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

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
	private static DataSource ds = null;
	
	/**
	 * Cancella un candidato dal database
	 * @param c
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void cancellaCandidato(String nome, String cognome) {
		Connection conn = null;
		Statement st = null;
			try {
				InitialContext context = new InitialContext();
				ds = (DataSource)context.lookup(driver);
				conn = ds.getConnection();
				st = conn.createStatement();
				int res = st.executeUpdate(
						"delete from candidato where (nome='"+nome+"' and cognome='"+cognome+"')"
				);
				Logger.getLogger(Integer.toString(res));
			} catch (NamingException | SQLException e) {
				JOptionPane.showMessageDialog (
						null , "Impossibile inizializzare una variabile"
				);
			} finally {
				try {
					st.close();
					conn.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog (
							null , "Impossibile inizializzare una variabile"
					);
				}
			} 
		} 
}
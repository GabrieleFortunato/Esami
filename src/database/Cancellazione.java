package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe CancellazioneDaDatabase
 * @author Gabriele Fortunato
 */
public class Cancellazione {
	
	/**
	 * Localhost
	 */
	final static String URL = "jdbc:mysql://localhost:3306/";
	
	/**
	 * Nome  del database
	 */
	final static String DBNAME = "esamiprogrammazione"+"?autoReconnect=true&useSSL=false";
	
	/**
	 * Driver
	 */
	final static String DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * Metodo costruttore
	 */
	private Cancellazione(){
		
	}
	
	/**
	 * Cancella un candidato dal database
	 * @param nome
	 * @param cognome
	 */
	public static void cancellaCandidato(String nome, String cognome){
		String a = Utility.stringForQuery(nome);
		String b = Utility.stringForQuery(cognome);
		String sql = "delete from candidato where (nome=? and cognome=?)";
		try {
			Connection conn = DriverManager.getConnection(URL+DBNAME,Utility.user(),Utility.pass());
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1, a);
			st.setString(2, b);
			int res = st.executeUpdate();
			Logger.getLogger(Integer.toString(res));
			st.close();
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog (
					null , "Problemi di connessione con il database"
			);
		} 
		
	} 
}



package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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

	private static InitialContext context ;
	private static DataSource ds;
	
	/**
	 * Metodo costruttore
	 */
	private Cancellazione(){
		
	}
	
	/**
	 * Cancella un candidato dal database
	 * @param nome
	 * @param cognome
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public static void cancellaCandidato(String nome, String cognome) throws NamingException {
		Connection conn = null;
		PreparedStatement st = null;
		context = new InitialContext();
		ds = (DataSource) context.lookup(URL);
		String sql = "delete from candidato where (nome=? and cognome=?)";
		try {
			conn = ds.getConnection(Utility.user(),Utility.pass());
			st = (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1, nome);
			st.setString(2, cognome);
			int res = st.executeUpdate();
			Logger.getLogger(Integer.toString(res));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Impossibile cancellare la prenotazione");
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Impossibile cancellare la prenotazione");
			}
		}
	} 

	/**
	 * Cancella un candidato dal database
	 * @param nome
	 * @param cognome
	 * @throws SQLException 
	 */
	public static void cancellaDatabase() {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			String sql = "drop database if exists esamiprogrammazione";
			conn = DriverManager.getConnection(URL,Utility.user(),Utility.pass());
			st = (PreparedStatement) conn.prepareStatement(sql);
			int res = st.executeUpdate(sql);
			Logger.getLogger(Integer.toString(res));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Impossibile cancellare la prenotazione");
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Impossibile cancellare la prenotazione");
			}
		}
	}

}



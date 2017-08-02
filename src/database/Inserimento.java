package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;
import com.mysql.jdbc.PreparedStatement;

/**
 * Inserimento nel database
 * @author gabriele
 *
 */
public class Inserimento {
	
	/**
	 * Localhost
	 */
	static String url = "jdbc:mysql://localhost:3306/";
	static String dbName = "esamiprogrammazione"+"?autoReconnect=true&useSSL=false";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = Utility.user();
	static String password = Utility.pass();
	
	/**
	 * Metodo costruttore
	 */
	private Inserimento(){
		
	}
	
	public static void inserisciPrenotazione(String nome, String cognome){
		nome = Utility.stringForQuery(nome);
		cognome = Utility.stringForQuery(cognome);
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(
					url+dbName,Utility.user(),Utility.pass()
			);
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(
					"insert into candidato (nome,cognome) values(?,?)"
			);
			st.setString(1, nome);
			st.setString(2, cognome);
			int res = st.executeUpdate();
			Logger.getLogger(Integer.toString(res));
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}


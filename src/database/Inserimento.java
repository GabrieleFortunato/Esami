package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

/**
 * Inserimento nel database
 * @author gabriele
 *
 */
public class Inserimento {
	
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
					url+dbName,userName,password
			);
			Statement st = (Statement) conn.createStatement();
			int res = st.executeUpdate(
					"insert into candidato (nome,cognome) values('"+nome+"','"+cognome+"')"
			);
			Logger.getLogger(Integer.toString(res));
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}


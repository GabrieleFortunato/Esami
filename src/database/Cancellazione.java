package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
	private final static String dbName = "esamiprogrammazione";
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String userName = dbUtility.stringaUser(); 
	private final static String password = dbUtility.stringaPass();
	
	public static void puliziaDatabase() 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(
				url+dbName+"?autoReconnect=true&useSSL=false",userName,password
		);
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"delete from candidato"
		);
	}

	public static void cancellaCandidato(Candidato c)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(
				url+dbName+"?autoReconnect=true&useSSL=false",userName,password
		);
		Statement st = conn.createStatement();
		@SuppressWarnings("unused")
		int res = st.executeUpdate(
				"delete from candidato where (nome='"+c.getNome()+"' and cognome='"+c.getCognome()+"')"
		);
	} 
}



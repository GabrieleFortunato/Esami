package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import candidati.Candidato;

/**
 * Classe CancellazioneDaDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class CancellazioneDaDatabase {
	
	private final static String url = "jdbc:mysql://localhost:3306/";
	private final static String dbName = "esamiprogrammazione";
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String userName = "root"; 
	private final static String password = "qrnq946";
	
	public static void puliziaDatabase(){
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(
					url+dbName+"?autoReconnect=true&useSSL=false",userName,password
			);
			Statement st = conn.createStatement();
			@SuppressWarnings("unused")
			int res = st.executeUpdate(
					"delete from candidato"
			);
		} catch (Exception e) {
			
		} 
	}

	public static void cancellaCandidato(Candidato c){
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(
					url+dbName+"?autoReconnect=true&useSSL=false",userName,password
			);
			Statement st = conn.createStatement();
			@SuppressWarnings("unused")
			int res = st.executeUpdate(
					"delete from candidato where (nome='"+c.getNome()+"' and cognome='"+c.getCognome()+"')"
			);
			file.PrintOnFile.cancellaFile(c);
		} catch (Exception e) {
			
		} 
	}

}

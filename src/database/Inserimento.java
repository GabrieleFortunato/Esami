package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import utility.Utility;

/**
 * Classe InserimentoNelDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Inserimento {

	private Inserimento(){
		
	}
	
	private final static String url = "jdbc:mysql://localhost:3306/";
	private final static String dbName = "esamiprogrammazione";
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String userName = "root"; 
	private final static String password = "qrnq946";
	
	public static void inserisciEsitoProgetto(
			String nome, String cognome, String libr, String test, String main
			) throws InstantiationException, IllegalAccessException, ClassNotFoundException, 
					 SQLException{
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(
					url+dbName+"?autoReconnect=true&useSSL=false",userName,password
			);
			Statement st = conn.createStatement();			int res = st.executeUpdate(
					"insert ignore into progetto values"
					+ "((select id from candidato where nome='"+Utility.stringForQuery(nome)
					+"' and cognome='"+Utility.stringForQuery(cognome)
					+"'),"+libr+","+test+","+main+")"
			);
			Logger.getLogger(Integer.toString(res));
			st.close();
			conn.close();
	}

	public static void inserisciPrenotazione(String nome, String cognome) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(
					url+dbName+"?autoReconnect=true&useSSL=false",userName,password
			);
			Statement st = conn.createStatement();
			int res = st.executeUpdate(
					"insert ignore into candidato (nome,cognome) values ('"
					+nome+"','"+cognome+"')"
			);
			Logger.getLogger(Integer.toString(res));
			st.close();
			conn.close();
	}
	
	public static void inserisciEsitoTeoria(String nome, String cognome, String teoria) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(
					url+dbName+"?autoReconnect=true&useSSL=false",userName,password
			);
			Statement st = conn.createStatement();
			int res = st.executeUpdate(
					"insert ignore into teoria values ((select id from candidato where nome='"
					+Utility.stringForQuery(nome)
					+"' and cognome='"+Utility.stringForQuery(cognome)
					+"'),'"+Utility.stringForQuery(teoria)
					+"')"
			);
			Logger.getLogger(Integer.toString(res));
			st.close();
			conn.close();
	}
	
}

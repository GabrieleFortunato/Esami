package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import candidati.Candidato;
import candidati.Progetto;
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
	
	public static void inserisciEsitoProgetto(Candidato c, Progetto p) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(
					url+dbName+"?autoReconnect=true&useSSL=false",userName,password
			);
			Statement st = conn.createStatement();
			@SuppressWarnings("unused")
			int res = st.executeUpdate(
					"insert ignore into progetto values"
					+ "((select id from candidato where nome='"+Utility.stringForQuery(c.getNome())
					+"' and cognome='"+Utility.stringForQuery(c.getCognome())
					+"'),"+p.getLibreria()+","+p.getTest()+","+p.getMain()+")"
			);
			st.close();
			conn.close();
		
	}

	public static void inserisciPrenotazione(Candidato c) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(
					url+dbName+"?autoReconnect=true&useSSL=false",userName,password
			);
			Statement st = conn.createStatement();
			@SuppressWarnings("unused")
			int res = st.executeUpdate(
					"insert ignore into candidato (nome,cognome) values ('"
					+c.getNome()+"','"+c.getCognome()+"')"
			);
			st.close();
			conn.close();
	}
	
	public static void inserisciEsitoTeoria(Candidato c) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(
					url+dbName+"?autoReconnect=true&useSSL=false",userName,password
			);
			Statement st = conn.createStatement();
			@SuppressWarnings("unused")
			int res = st.executeUpdate(
					"insert ignore into teoria values ((select id from candidato where nome='"
					+Utility.stringForQuery(c.getNome())
					+"' and cognome='"+Utility.stringForQuery(c.getCognome())
					+"'),'"+Utility.stringForQuery(c.getEsitoTeoria())
					+"')"
			);
			st.close();
			conn.close();
	}
	
}

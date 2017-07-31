package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import candidati.Candidato;
import candidati.Progetto;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;

/**
 * Classe LetturaDaDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Lettura {

	private Lettura(){
		
	}
	
	private final static String url = "jdbc:mysql://localhost:3306/";
	private final static String dbName = "esamiprogrammazione";
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String userName = "root"; 
	private final static String password = "qrnq946";
	
	public static HashSet<Candidato> interrogati() 
			throws SQLException,	EsitoTeoriaException, InstantiationException, IllegalAccessException, 
			ClassNotFoundException, VotoException{
		HashSet<Candidato> list = new HashSet<>();
		Class.forName(driver).newInstance();
		String stringa = "?autoReconnect=true&useSSL=false";
		Connection conn = DriverManager.getConnection(
				url+dbName+stringa,userName,password
		);
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(
				"select nome,cognome,esito,libreria,test,main from candidato "
				+ "inner join teoria on candidato.id=teoria.candidato "
				+ "inner join progetto on candidato.id=progetto.candidato"
		);
		Progetto progetto;
		while (res.next()) {
			String colonna = "nome";
			String nome = res.getString(colonna);
			colonna = "cognome";
			String cognome = res.getString(colonna);
			String teoria = res.getString("esito");
			int libreria = res.getInt("libreria");
			int test = res.getInt("test");
			int main = res.getInt("main");
			progetto = new Progetto(libreria,test,main);
			Candidato candidato = new Candidato(nome,cognome,teoria,progetto);
			list.add(candidato);
		}
		conn.close();
		return list;
	}
	
	public static HashSet<Candidato> perTeoria() 
			throws SQLException, InstantiationException, 
	IllegalAccessException, ClassNotFoundException{
		Set<Candidato> list = new HashSet<>();
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(
				url+dbName+"?autoReconnect=true&useSSL=false",userName,password
		);
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(
				"select nome,cognome from candidato where id not in (select candidato from teoria)"
		);
		while (res.next()) {
			String nome = res.getString("nome");
			String cognome = res.getString("cognome");
			Candidato candidato = new Candidato(nome,cognome);
			list.add(candidato);
		}
		conn.close(); 
		return (HashSet<Candidato>) list;
	}
	
	public static HashSet<Candidato> daInterrogare() 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Set<Candidato> list = new HashSet<>();
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(
				url+dbName+"?autoReconnect=true&useSSL=false",userName,password
		);
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(
				"select nome,cognome from candidato where id not in (select candidato from progetto)"
		);
		while (res.next()) {
			String nome = res.getString("nome");
			String cognome = res.getString("cognome");
			Candidato candidato = new Candidato(nome,cognome);
			list.add(candidato);
		}
		conn.close();
		return (HashSet<Candidato>) list;
	}
	
	public static HashSet<Candidato> candidati() 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Set<Candidato> list = new HashSet<>();
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(
				url+dbName+"?autoReconnect=true&useSSL=false",userName,password
		);
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(
				"select nome,cognome from candidato"
		);
		while (res.next()) {
			String nome = res.getString("nome");
			String cognome = res.getString("cognome");
			Candidato candidato = new Candidato(nome,cognome);
			list.add(candidato);
		}
		conn.close();
		return (HashSet<Candidato>) list;
	}
}

package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import candidati.Candidato;
import candidati.Progetto;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classe LetturaDaDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Lettura {

	private Lettura(){
		
	}
	
	private final static String driver = "com.mysql.jdbc.Driver";
	private static InitialContext context = context();
	private static DataSource ds = ds(context);
	
	private static InitialContext context(){
		InitialContext context = null;
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			
		}
		return context;
	}
	
	private static DataSource ds(InitialContext context){
		DataSource ds = null;
		try {
			ds = (DataSource) context.lookup(driver);
		} catch (NamingException e) {
		
		}
		return ds;
	}
	
	/**
	 * Legge dal database i candidati che hanno sostenuto e superato 
	 * tutte le prove
	 * @return
	 * @throws NamingException 
	 * @throws SQLException
	 * @throws VotoException
	 * @throws EsitoTeoriaException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static HashSet<Candidato> interrogati() 
			throws NamingException, SQLException, VotoException, EsitoTeoriaException {
		Set<Candidato> list = new HashSet<>();
		ds = (DataSource) context.lookup(driver);
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(
				"select nome,cognome,esito,libreria,test,main from candidato "
				+ "inner join teoria on candidato.id=teoria.candidato "
				+ "inner join progetto on candidato.id=progetto.candidato"
		);
		Progetto progetto;
		while (res.next()) {
			String nome = res.getString("nome");
			String cognome = res.getString("cognome");
			String teoria = res.getString("esito");
			int libreria = res.getInt("libreria");
			int test = res.getInt("test");
			int main = res.getInt("main");
			progetto = new Progetto(libreria,test,main);
			Candidato candidato = new Candidato(nome,cognome,teoria,progetto);
			list.add(candidato);
		}
		st.close();
		res.close();
		conn.close();
		return (HashSet<Candidato>) list;
	}
	

	
	public static HashSet<Candidato> daInterrogare() 
			throws NamingException, SQLException{
		Set<Candidato> list = new HashSet<>();
		ds = (DataSource) context.lookup(driver);
		Connection conn = ds.getConnection();
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
		st.close();
		res.close();
		conn.close();
		return (HashSet<Candidato>) list;
	}
		
}

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
	
	private final static String url = "jdbc:mysql://localhost:3306/";
	
	/**
	 * Cerca nel database i candidati interrogati
	 * @return
	 */
	public static HashSet<Candidato> interrogati() {
		Set<Candidato> list = new HashSet<>();
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup(url);
			Connection conn = ds.getConnection();
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery(
					"select nome,cognome,esito,libreria,test,main from candidato "
					+ "inner join teoria on candidato.id=teoria.candidato "
					+ "inner join progetto on candidato.id=progetto.candidato"
			);
			while (res.next()) {
				String nomeColonna = "nome";
				String cognomeColonna = "nome";
				String nome = res.getString(nomeColonna);
				String cognome = res.getString(cognomeColonna);
				String teoria = res.getString("esito");
				int libreria = res.getInt("libreria");
				int test = res.getInt("test");
				int main = res.getInt("main");
				Progetto progetto = new Progetto(libreria,test,main);
				Candidato candidato = new Candidato(nome,cognome,teoria,progetto);
				list.add(candidato);
			}
			conn.close();
		} catch (NamingException | SQLException | VotoException | EsitoTeoriaException e) {
				
		}
		return (HashSet<Candidato>) list;
	}
	
	public static HashSet<Candidato> perTeoria() {
		Set<Candidato> list = new HashSet<>();
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup(url);
			Connection conn = ds.getConnection();
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
		} catch (NamingException | SQLException e) {
			
		}
		return (HashSet<Candidato>) list;
	}
	
	public static HashSet<Candidato> daInterrogare() {
		Set<Candidato> list = new HashSet<>();
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup(url);
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
			conn.close();
		} catch (NamingException | SQLException e) {
			
		}
		return (HashSet<Candidato>) list;
	}
	
	public static HashSet<Candidato> candidati() throws NamingException, SQLException {
		Set<Candidato> list = new HashSet<>();
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(url);
		Connection conn = ds.getConnection();
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

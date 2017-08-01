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
import javax.swing.JOptionPane;

/**
 * Classe LetturaDaDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Lettura {

	/**
	 * Metodo costruttore
	 */
	private Lettura(){
		
	}
	
	private final static String driver = "com.mysql.jdbc.Driver";
	private static DataSource ds = null;
	
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
	public static HashSet<Candidato> proveCompletate() {
		Set<Candidato> list = null;
		Connection conn = null;
		Statement st = null;
		ResultSet res = null;
		try {
			list = new HashSet<>();
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup(driver);
			conn = ds.getConnection();
			st = conn.createStatement();
			res = st.executeQuery(
					"select nome,cognome,esito,libreria,test,main from candidato "
					+ "inner join teoria on candidato.id=teoria.candidato "
					+ "inner join progetto on candidato.id=progetto.candidato"
			);
			Progetto progetto = null;
			boolean flag = res.next();
			while (flag) {
				String nome = res.getString("nome");
				String cognome = res.getString("cognome");
				String teoria = res.getString("esito");
				int libreria = res.getInt("libreria");
				int test = res.getInt("test");
				int main = res.getInt("main");
				progetto = new Progetto(libreria,test,main);
				Candidato candidato = new Candidato(nome,cognome,teoria,progetto);
				list.add(candidato);
				flag = res.next();
			}
		} catch (NamingException | SQLException | VotoException | EsitoTeoriaException e) {
			JOptionPane.showMessageDialog (
					null , "Impossibile inserire nel database l'esito della teoria"
			);
		} finally {
			try {
				st.close();
				res.close();
				conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog (
						null , "Problemi di collegamento con il database"
				);
			}
		}
		return (HashSet<Candidato>) list;
	}
	
	/**
	 * Cerca nel database i candidati da interrogare
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static HashSet<Candidato> daInterrogare() {
		Set<Candidato> list = null;
		Connection conn = null;
		Statement st = null;
		ResultSet res = null;
		try {
			list = new HashSet<>();
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup(driver);
			conn = ds.getConnection();
			st = conn.createStatement();
			res = st.executeQuery(
					"select nome,cognome from candidato where id not in (select candidato from progetto)"
			);
			while (res.next()) {
				String nome = res.getString("nome");
				String cognome = res.getString("cognome");
				Candidato candidato = new Candidato(nome,cognome);
				list.add(candidato);
			}
		} catch (NamingException | SQLException e) {
			JOptionPane.showMessageDialog (
					null , "Impossibile inserire nel database l'esito della teoria"
			);
		} finally {
			try {
				st.close();
				res.close();
				conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog (
						null , "Problemi di collegamento con il database"
				);
			}
		}
		return (HashSet<Candidato>) list;
	}
		
}

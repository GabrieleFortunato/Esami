package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import candidati.Candidato;
import candidati.Progetto;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;
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
	

	/**
	 * Localhost
	 */
	final static String url = "jdbc:mysql://localhost:3306/";

	/**
	 * Nome  del database
	 */
	final static String dbName = "esamiprogrammazione"+"?autoReconnect=true&useSSL=false";

	/**
	 * Driver
	 */
	final static String driver = "com.mysql.jdbc.Driver";
	
	/**
	 * Legge dal database i candidati che hanno sostenuto e superato 
	 * tutte le prove
	 * @return
	 */
	public static HashSet<Candidato> proveCompletate() {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet res = null;
		Set<Candidato> list = new HashSet<>();
		try {
			conn = DriverManager.getConnection(
					url+dbName,Utility.user(),Utility.pass()
			);
			st = (PreparedStatement) conn.prepareStatement(
					"select nome,cognome,esito,libreria,test,main from candidato "
							+ "inner join teoria on candidato.id=teoria.candidato "
							+ "inner join progetto on candidato.id=progetto.candidato"
			);
			res = st.executeQuery();
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
		} catch (SQLException e) {
			JOptionPane.showMessageDialog (
					null , "Problemi di connessione con il database"
			);
		} catch (VotoException e) {
			e.stampaMessaggio("Voto non valido");
		} catch (EsitoTeoriaException e) {
			e.stampaMessaggio("Esito della teoria non valido");
		} finally {
			if (st!=null&&res!=null&&conn!=null){
				try {
					st.close();
					res.close();
					conn.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog (
							null , "Problemi di connessione con il database"
					);
				}
			}
		}
		return (HashSet<Candidato>) list;
	}
	

	
	/**
	 * Legge dal database i candidati che hanno sostenuto e superato 
	 * tutte le prove
	 * @return
	 */
	public static int id(String nome, String cognome) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet res = null;
		nome = Utility.stringForQuery(nome);
		cognome = Utility.stringForQuery(cognome);
		int ris = 0;
		try {
			conn = DriverManager.getConnection(
					url+dbName,Utility.user(),Utility.pass()
			);
			st = (PreparedStatement) conn.prepareStatement(
					"select id from candidato where (nome='"+nome+"' and cognome='"+cognome+"')"
			);
			res = st.executeQuery();
			boolean flag = res.next();
			while (flag) {
				ris = res.getInt("id");
				flag = res.next();
			}
			st.close();
			res.close();
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog (
					null , "Problemi di connessione con il database"
			);
		} finally {
			if (st!=null&&res!=null&&conn!=null){
				try {
					st.close();
					res.close();
					conn.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog (
							null , "Problemi di connessione con il database"
					);
				}
			}
		}
		return ris;
	}
	

}

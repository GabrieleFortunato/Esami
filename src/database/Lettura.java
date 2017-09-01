package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

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

	/**
	 * Metodo costruttore
	 */
	private Lettura(){
		
	}
	
	/**
	 * Localhost
	 */
	final static String URL = "jdbc:mysql://localhost:3306/";

	/**
	 * Nome  del database
	 */
	final static String DBNAME = "esamiprogrammazione"+"?autoReconnect=true&useSSL=false";

	/**
	 * Driver
	 */
	final static String DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * Legge dal database i candidati che hanno sostenuto e superato 
	 * tutte le prove
	 * @return
	 * @throws SQLException 
	 * @throws VotoException 
	 * @throws EsitoTeoriaException 
	 */
	public static HashSet<Candidato> proveCompletate() 
			throws VotoException, EsitoTeoriaException {
		Set<Candidato> list = new HashSet<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet res = null;
		try {
			conn = DriverManager.getConnection(
					URL+DBNAME,Utility.user(),Utility.pass()
			);
			st = (PreparedStatement) conn.prepareStatement(
					"select nome,cognome,esito,libreria,test,main from candidato "
					+ "inner join teoria on candidato.id=teoria.candidato "
					+ "inner join progetto on candidato.id=progetto.candidato"
			);
			res = st.executeQuery();
			Progetto progetto = null;
			Candidato candidato = null;
			boolean flag = res.next();
			while (flag) {
				String nome = res.getString("nome");
				String cognome = res.getString("cognome");
				String teoria = res.getString("esito");
				int libreria = res.getInt("libreria");
				int test = res.getInt("test");
				int fmain = res.getInt("main");
				progetto = new Progetto(libreria,test,fmain);
				candidato = new Candidato(nome,cognome,teoria,progetto);
				list.add(candidato);
				flag = res.next();
			};
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Impossibile cancellare la prenotazione");
		} finally {
			try {
				res.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Impossibile cancellare la prenotazione");
			}
		}
		return (HashSet<Candidato>) list;
	}

	/**
	 * Legge dal database l'id del candidato 
	 * tutte le prove
	 * @return
	 * @throws SQLException 
	 */
	public static int id(String nome, String cognome)  {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet res = null;
		String a = Utility.stringForQuery(nome);
		String b = Utility.stringForQuery(cognome);
		int ris = 0;
		try {
			conn = DriverManager.getConnection(
					URL+DBNAME,Utility.user(),Utility.pass()
			);
			st = (PreparedStatement) conn.prepareStatement(
					"select id from candidato where (nome='"+a+"' and cognome='"+b+"')"
			);
			res = st.executeQuery();
			boolean flag = res.next();
			while (flag) {
				ris = res.getInt("id");
				flag = res.next();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Impossibile cancellare la prenotazione");
		} finally {
			try {
				res.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Impossibile cancellare la prenotazione");
			}
		}
		return ris;
	}
	
	/**
	 * Legge da database i candidati di cui inserire l'esito della teoria
	 * @return
	 * @throws SQLException
	 * @throws VotoException
	 * @throws EsitoTeoriaException
	 */
	public static HashSet<Candidato> candidatoInserireTeoria() 
			throws VotoException, EsitoTeoriaException {
		Set<Candidato> list = new HashSet<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet res = null;
		try {
			conn = DriverManager.getConnection(
					URL+DBNAME,Utility.user(),Utility.pass()
			);
			st = (PreparedStatement) conn.prepareStatement(
					"select nome,cognome from candidato "
					+ "where id not in (select candidato from teoria)"
			);
			res = st.executeQuery();
			Progetto progetto = null;
			Candidato candidato = null;
			boolean flag = res.next();
			while (flag) {
				String nome = res.getString("nome");
				String cognome = res.getString("cognome");
				String teoria = res.getString("esito");
				int libreria = res.getInt("libreria");
				int test = res.getInt("test");
				int fmain = res.getInt("main");
				progetto = new Progetto(libreria,test,fmain);
				candidato = new Candidato(nome,cognome,teoria,progetto);
				list.add(candidato);
				flag = res.next();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Impossibile cancellare la prenotazione");
		} finally {
			try {
				res.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Impossibile cancellare la prenotazione");
			}
		}
		return (HashSet<Candidato>) list;
	}

}


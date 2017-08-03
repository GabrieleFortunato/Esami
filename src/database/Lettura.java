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
import javax.naming.NamingException;
import javax.swing.JOptionPane;

/**
 * Classe LetturaDaDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Lettura {

	private Lettura(){
		
	}
	

	/**
	 * Localhost
	 */
	final static String url = "jdbc:mysql://localhost:3306/";
	final static String dbName = "esamiprogrammazione"+"?autoReconnect=true&useSSL=false";
	final static String driver = "com.mysql.jdbc.Driver";
	
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
		Set<Candidato> list = new HashSet<>();
		try {
			Connection conn = DriverManager.getConnection(
					url+dbName,Utility.user(),Utility.pass()
			);
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(
					"select nome,cognome,esito,libreria,test,main from candidato "
							+ "inner join teoria on candidato.id=teoria.candidato "
							+ "inner join progetto on candidato.id=progetto.candidato"
			);
			ResultSet res = st.executeQuery();
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
		} catch (SQLException e) {
			JOptionPane.showMessageDialog (
					null , "Problemi di connessione con il database"
			);
		} catch (VotoException e) {
			JOptionPane.showMessageDialog (
					null , "Voto non valido"
			);
		} catch (EsitoTeoriaException e) {
			JOptionPane.showMessageDialog (
					null , "Esito della teoria non valido"
			);
		}
		return (HashSet<Candidato>) list;
	}
	

		
}

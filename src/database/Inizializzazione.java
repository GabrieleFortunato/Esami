package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

/**
 * Classe InizializzaDatabase
 * 
 * @author Gabriele Fortunato
 *
 */
public class Inizializzazione {

	/**
	 * Metodo costruttore
	 */
	private Inizializzazione(){
		
	}
	
	private final static String driver = "com.mysql.jdbc.Driver";
	private static DataSource ds;
	
	/**
	 * Inizializza il database
	 * @throws NamingException 
	 * @throws SQLException
	 */
	public static void inizializzaDatabase() {
		Connection conn = null;
		Statement st = null;
			try {
				InitialContext context = new InitialContext();
				ds = (DataSource) context.lookup(driver);
				conn = ds.getConnection();
				st = conn.createStatement();
				int res = st.executeUpdate(
						"create database if not exists esamiprogrammazione"		
						);
				Logger.getLogger(Integer.toString(res));
				int res1 = st.executeUpdate(
						"use esamiprogrammazione"
						);
				Logger.getLogger(Integer.toString(res1));
				int res2 = st.executeUpdate(
						"create table if not exists candidato("+
						"id int auto_increment primary key,"+
						"nome varchar(60),"+
						"cognome varchar(60),"+
						"unique (nome,cognome)"+
						");"
						);
				Logger.getLogger(Integer.toString(res2));
				int res3 = st.executeUpdate("create table if not exists teoria("+
						"candidato int primary key,"+
						"esito varchar(60),"+
						"foreign key constaint (candidato) references candidato(id) on delete cascade"+
						");"
						);
				Logger.getLogger(Integer.toString(res3));
				int res4 = st.executeUpdate(
						"create table if not exists progetto("+
						"candidato int primary key,"+
						"libreria int,"+
						"test int,"+
						"main int,"+
						"foreign key constaint (candidato) references candidato(id) on delete cascade"+
						");"
						);
				Logger.getLogger(Integer.toString(res4));
			} catch (NamingException | SQLException e) {
				JOptionPane.showMessageDialog (
						null , "Impossibile inizializzare una variabile"
				);
			} finally {
				try {
					st.close();
					conn.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog (
							null , "Impossibile inizializzare una variabile"
					);
				}
			}
		} 
	
}


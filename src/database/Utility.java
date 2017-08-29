package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JOptionPane;

/**
 * Classe di utilità per il database
 * @author gabriele
 *
 */
public class Utility {
	
	/**
	 * Metodo costruttore
	 */
	private Utility(){
		
	}
	
	/**
	 * Restituisce una stringa per la query SQL
	 * @param s
	 * @return
	 */
	public static String stringForQuery(String s){
		int length = s.length();
		String result = s;
		for (int i=0;i<length;i++){
			if (s.charAt(i)=='\''){
				result=s.substring(0,i+1).concat(s.substring(i));
			}
		}
		return result;
	}
	
	/**
	 * Legge da file il nome utente di MySQL
	 * @return
	 */
	public static String user() {
		String s = null;
		try {
			@SuppressWarnings("resource")
			BufferedReader buffer = new BufferedReader(new FileReader("user.txt"));
			s = buffer.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog (
				null , "Problemi di lettura da file user"
			);
		}
		return s;
	}
	
	public static String pass() {
		String s = null;
		try {
			@SuppressWarnings("resource")
			BufferedReader buffer = new BufferedReader(new FileReader("pass.txt"));
			s = buffer.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog (
				null , "Problemi di lettura da file pass"
			);
		}
		return s;
	}
}

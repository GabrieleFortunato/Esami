package esami;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import candidati.Candidato;
import database.Lettura;

/**
 * Classe Interrogazione
 * 
 * @author Gabriele Fortunato
 *
 */
public class Interrogazione implements Iterable<Candidato>{
	
	private Set<Candidato> daInterrogare;

	/**
	 * Imposta i candidati da interrogare
	 * @param daInterrogare
	 */
	private void setDaInterrogare(HashSet<Candidato> daInterrogare) {
		this.daInterrogare = daInterrogare;
	}

	/**
	 * Metodo costruttore
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Interrogazione() 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		this.setDaInterrogare(Lettura.daInterrogare());
	}

	/**
	 * Itera sui candidati da interrogare
	 */
	@Override
	public Iterator<Candidato> iterator() {
		return daInterrogare.iterator();
	}
	
}

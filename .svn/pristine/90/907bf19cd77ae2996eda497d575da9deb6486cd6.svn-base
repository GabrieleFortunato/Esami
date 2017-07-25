package esami;

import java.util.HashSet;
import java.util.Iterator;
import candidati.Candidato;
import database.LetturaDaDatabase;

/**
 * Classe Interrogazione
 * 
 * @author Gabriele Fortunato
 *
 */
public class Interrogazione implements Iterable<Candidato>{
	
	private HashSet<Candidato> daInterrogare;

	/**
	 * Imposta i candidati da interrogare
	 * @param daInterrogare
	 */
	private void setDaInterrogare(HashSet<Candidato> daInterrogare) {
		this.daInterrogare = daInterrogare;
	}

	/**
	 * Metodo costruttore
	 */
	public Interrogazione(){
		this.setDaInterrogare(LetturaDaDatabase.daInterrogare());
	}

	@Override
	public Iterator<Candidato> iterator() {
		return daInterrogare.iterator();
	}
	
}

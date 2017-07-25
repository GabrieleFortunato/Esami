package esami;

import java.util.HashSet;
import java.util.Iterator;

import candidati.Candidato;
import database.LetturaDaDatabase;

/**
 * Classe EsitoEsame
 * 
 * @author Gabriele Fortunato
 *
 */
public class EsitoEsame implements Iterable<Candidato> {

	private HashSet<Candidato> interrogati;
	
	/**
	 * Metodo costruttore
	 */
	public EsitoEsame(){
		this.setInterrogati(LetturaDaDatabase.interrogati());
	}
	
	/**
	 * Imposta i candidati interrogati
	 * @param interrogati
	 */
	private void setInterrogati(HashSet<Candidato> interrogati) {
		this.interrogati = interrogati;
	}

	@Override
	/**
	 * Itera sugli interrogati
	 */
	public Iterator<Candidato> iterator() {
		return interrogati.iterator();
	}

	
}

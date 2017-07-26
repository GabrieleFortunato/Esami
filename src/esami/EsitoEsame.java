package esami;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

import candidati.Candidato;
import database.Lettura;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoNonValidoException;

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
	 * @throws EsitoTeoriaException 
	 * @throws VotoNonValidoException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public EsitoEsame() 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, 
			VotoNonValidoException, EsitoTeoriaException{
		this.setInterrogati(Lettura.interrogati());
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

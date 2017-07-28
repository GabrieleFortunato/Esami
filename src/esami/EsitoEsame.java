package esami;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import candidati.Candidato;
import database.Lettura;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;

/**
 * Classe EsitoEsame
 * 
 * @author Gabriele Fortunato
 *
 */
public class EsitoEsame implements Iterable<Candidato> {

	private Set<Candidato> interrogati;
	
	/**
	 * Metodo costruttore
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws VotoException
	 * @throws EsitoTeoriaException
	 */
	public EsitoEsame() 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, 
			VotoException, EsitoTeoriaException{
		this.setInterrogati(Lettura.proveCompletate());
	}
	
	/**
	 * Imposta i candidati interrogati
	 * @param interrogati
	 */
	private void setInterrogati(HashSet<Candidato> interrogati) {
		this.interrogati = interrogati;
	}

	/**
	 * Itera sugli interrogati
	 */
	@Override
	public Iterator<Candidato> iterator() {
		return interrogati.iterator();
	}

	
}

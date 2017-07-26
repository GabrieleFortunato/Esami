package esami;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

import candidati.Candidato;
import database.Lettura;
import eccezioni.EsitoTeoriaEccezione;
import eccezioni.VotoNonValidoEccezione;

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
	 * @throws EsitoTeoriaEccezione 
	 * @throws VotoNonValidoEccezione 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public EsitoEsame() 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, 
			VotoNonValidoEccezione, EsitoTeoriaEccezione{
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

package esami;

import java.util.ArrayList;

import candidati.Candidato;

/**
 * Calcola gli esiti degli esami
 * @author gabriele
 *
 */
public class Esito {
	
	private ArrayList<Candidato> candidati;

	/**
	 * Imposta i candidati
	 * @param candidati
	 */
	private void setCandidati(ArrayList<Candidato> candidati) {
		this.candidati = candidati;
	}
		
	/**
	 * Metodo costruttore	
	 */
	public Esito(){	
		this.setCandidati(candidati);
	}
	
	
}

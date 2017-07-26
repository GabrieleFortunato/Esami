package candidati;

import eccezioni.EsitoTeoriaEccezione;

/**
 * Classe Candidato
 * 
 * @author Gabriele Fortunato
 *
 */
public class Candidato {
	

	private int idCandidato;
	private static int idProgressivo=0;
	private String nome;
	private String cognome;
	private String esitoTeoria;
	private Progetto progetto;
	
	/**
	 * Verifica che l'esito della teoria è corretto
	 * @param s
	 * @return
	 */
	private static boolean esitoTeoriaCorretto(String s){
		return (
				s.equals("quasi sufficiente")||s.equals("sufficiente")||
				s.equals("più che sufficiente")||s.equals("quasi buono")||
				s.equals("buono")||s.equals("più che buono")||
				s.equals("quasi ottimo")||s.equals("ottimo")
		);
	}

	/**
	 * Metodo costruttore
	 * @param nome
	 * @param cognome
	 * @param esitoTeoria
	 * @param progetto
	 */
	public Candidato(String nome, String cognome) {
		this.setId(++idProgressivo);
		this.setNome(nome);
		this.setCognome(cognome);
	}

	/**
	 * Metodo costruttore
	 * @param nome
	 * @param cognome
	 * @param esitoTeoria
	 * @param progetto
	 * @throws EsitoTeoriaEccezione 
	 */
	public Candidato(String nome, String cognome, String esitoTeoria) 
			throws EsitoTeoriaEccezione {
		this(nome, cognome);
		this.setEsitoTeoria(esitoTeoria);
	}

	/**
	 * Metodo costruttore
	 * @param nome
	 * @param cognome
	 * @param esitoTeoria
	 * @param progetto
	 * @throws EsitoTeoriaEccezione 
	 */
	public Candidato(String nome, String cognome, String esitoTeoria, Progetto progetto) 
			throws EsitoTeoriaEccezione {
		this(nome, cognome,esitoTeoria);
		this.setProgetto(progetto);
	}

	/**
	 * Imposta l'id del candidato
	 * @param id
	 */
	private void setId(int id){
		this.idCandidato=id;
	}

	/**
	 * Imposta il nome del candidato
	 * @param nome
	 */
	private void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Restituisce il nome del candidato
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce il cognome del candidato
	 * @return
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Imposta il cognome del candidato
	 * @param cognome
	 */
	private void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Imposta l'esito della teoria
	 * @param esitoTeoria
	 * @throws EsitoTeoriaEccezione 
	 */
	private void setEsitoTeoria(String esitoTeoria) throws EsitoTeoriaEccezione {
		if (esitoTeoriaCorretto(esitoTeoria)){
			this.esitoTeoria = esitoTeoria;
		} else {
			throw new EsitoTeoriaEccezione();
		}
	}

	/**
	 * Restituisce l'esito della teoria
	 * @return
	 */
	public String getEsitoTeoria() {
		return esitoTeoria;
	}

	/**
	 * Imposta il progetto svolto dal candidato
	 * @param progetto
	 */
	private void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}
	
	/**
	 * Restituisce il progetto svolto dal candidato
	 * @return
	 */
	public Progetto getProgetto() {
		return progetto;
	}

	/**
	 * Restituisce il voto in numero della prova teorica
	 * @return
	 */
	private double teoria(){
		if (esitoTeoria.equals("quasi sufficiente")){
			return 16;
		} else if (esitoTeoria.equals("sufficiente")){
			return 18;
		} else if (esitoTeoria.equals("più che sufficiente")){
			return 20;
		} else if (esitoTeoria.equals("quasi buono")){
			return 22;
		} else if (esitoTeoria.equals("buono")){
			return 24;
		} else if (esitoTeoria.equals("più che buono")){
			return 26;
		} else if (esitoTeoria.equals("quasi ottimo")){
			return 28;
		} else {
			return 30;
		}
	}
	
	/**
	 * Restituisce l'esito finale del candidato
	 * @return
	 */
	public double esito(){
		double esito = ((teoria()+(7/6.0)*progetto.esito())/2.0);
		return esito;
	}

	@Override
	/**
	 * Genera l'hashCode in base all'id
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCandidato;
		return result;
	}

}
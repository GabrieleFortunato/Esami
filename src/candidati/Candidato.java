package candidati;

import eccezioni.EsitoTeoriaException;
import utility.Utility;

/**
 * Classe Candidato
 * @author Gabriele Fortunato
 */
public class Candidato implements Comparable<Candidato>{
	
	private int idCandidato;
	private static int idProgressivo=0;
	private String nome;
	private String cognome;
	private String esitoTeoria;
	private Progetto progetto;
	
	/**
	 * Verifica che l'esito della teoria � corretto
	 * @param s
	 * @return
	 */
	private static boolean esitoTeoriaCorretto(String s){
		return (
				s.equals("quasi sufficiente")||s.equals("sufficiente")||
				s.equals("pi� che sufficiente")||s.equals("quasi buono")||
				s.equals("buono")||s.equals("pi� che buono")||
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
	 * @throws EsitoTeoriaException 
	 */
	public Candidato(String nome, String cognome, String esitoTeoria) 
			throws EsitoTeoriaException {
		this(nome, cognome);
		this.setEsitoTeoria(esitoTeoria);
	}

	/**
	 * Metodo costruttore
	 * @param nome
	 * @param cognome
	 * @param esitoTeoria
	 * @param progetto
	 * @throws EsitoTeoriaException 
	 */
	public Candidato(String nome, String cognome, String esitoTeoria, Progetto progetto) 
			throws EsitoTeoriaException {
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
	 * Imposta l'id del candidato
	 * @param id
	 */
	public int getId(){
		return idCandidato;
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
	 * @throws EsitoTeoriaException 
	 */
	private void setEsitoTeoria(String esitoTeoria) throws EsitoTeoriaException {
		if (esitoTeoriaCorretto(esitoTeoria)){
			this.esitoTeoria = esitoTeoria;
		} else {
			throw new EsitoTeoriaException();
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
	 * Imposta il primo progetto svolto dal candidato
	 * @param progetto
	 */
	private void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}
	
	/**
	 * Restituisce il primo progetto svolto dal candidato
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
		int esito = 0;
		if (esitoTeoria.equals("quasi sufficiente")){
			esito = 16;
		} else if (esitoTeoria.equals("sufficiente")){
			esito = 18;
		} else if (esitoTeoria.equals("pi� che sufficiente")){
			esito = 20;
		} else if (esitoTeoria.equals("quasi buono")){
			esito = 22;
		} else if (esitoTeoria.equals("buono")){
			esito = 24;
		} else if (esitoTeoria.equals("pi� che buono")){
			esito = 26;
		} else if (esitoTeoria.equals("quasi ottimo")){
			esito = 28;
		} else {
			esito = 30;
		}
		return esito;
	}

	@Override
	/**
	 * Genera l'hashCode
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCandidato;
		return result;
	}	
	
	@Override
	/**
	 * Verifica l'uguaglianza con un altro candidato in base all'id
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null||getClass() != obj.getClass())
			return false;
		Candidato other = (Candidato) obj;
		if (idCandidato != other.idCandidato)
			return false;
		return true;
	}

	/**
	 * Restituisce l'esito finale del candidato
	 * @return
	 */
	public int esito(){
		double esito = (teoria()+progetto.esito()*7/6.0)/2.0;
		return (int) Utility.arrotonda(esito,0);
	}

	@Override
	/**
	 * Confronta con un altro candidato in base all'esito finale
	 */
	public int compareTo(Candidato c) {
		if (this.esito()<c.esito()){
			return -1;
		} else if (this.esito()>c.esito()){
			return 1;
		} else {
			if (this.cognome!=c.getCognome()) {
				return this.cognome.compareTo(c.getCognome());
			} else {
				return this.nome.compareTo(c.getNome());
			}
		}
 	}
	
	/**
	 * Verifica che il candidato � stato promosso
	 * @return
	 */
	public boolean promosso(){
		return (esito()>=18&&progetto.esito()>=16&&teoria()>=16);
	}

}
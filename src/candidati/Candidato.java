package candidati;

public class Candidato {
	
	@SuppressWarnings("unused")
	private String nome;
	@SuppressWarnings("unused")
	private String cognome;
	@SuppressWarnings("unused")
	private String teoria;
	@SuppressWarnings("unused")
	private Progetto progetto;
	
	/**
	 * Metodo costruttore
	 * @param nome
	 * @param cognome
	 * @param teoria
	 * @param progetto
	 */
	public Candidato(String nome, String cognome, String teoria, Progetto progetto) {
		this.setNome(nome);
		this.setCognome(cognome);
		this.setTeoria(teoria);
		this.setProgetto(progetto);
	}
	
	/**
	 * Imposta il nome del candidato
	 * @param nome
	 */
	private void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Imposta il cognome del candidato
	 * @param cognome
	 */
	private void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	/**
	 * Imposta l'esito della parte teorica 
	 * @param teoria
	 */
	private void setTeoria(String teoria) {
		this.teoria = teoria;
	}
	
	/**
	 * Imposta il progetto svolto dal candidato
	 * @param progetto
	 */
	private void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}
	
}

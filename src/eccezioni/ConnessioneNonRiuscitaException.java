package eccezioni;

/**
 * Eccezione che viene lanciata se la connessione al database non è riuscita
 * 
 * @author Gabriele Fortunato
 *
 */
public class ConnessioneNonRiuscitaException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void stampaMessaggio(String messaggio){
		System.out.println(messaggio);
	}

}

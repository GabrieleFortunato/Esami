package eccezioni;

/**
 * Classe Eccezione
 * 
 * @author Gabriele Fortunato
 *
 */
public class NoInterrogazioniException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void stampaMessaggio(String messaggio){
		System.out.println(messaggio);
	}

}

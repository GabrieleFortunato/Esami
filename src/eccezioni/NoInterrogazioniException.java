package eccezioni;

/**
 * Classe Eccezione
 * 
 * @author Gabriele Fortunato
 *
 */
public class NoInterrogazioniException extends Exception {
	public void stampaMessaggio(String messaggio){
		System.out.println(messaggio);
	}

}

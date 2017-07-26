package eccezioni;

/**
 * Classe Eccezione
 * 
 * @author Gabriele Fortunato
 *
 */
public class VotoNonValidoException extends Exception {
	public void stampaMessaggio(String messaggio){
		System.out.println(messaggio);
	}

}

package eccezioni;

import java.util.logging.Logger;

/**
 * Classe Eccezione
 * 
 * @author Gabriele Fortunato
 *
 */
@SuppressWarnings("serial")
public class VotoException extends Exception {

	public VotoException(){
		Logger.getLogger("Voto non valido");
	}
}

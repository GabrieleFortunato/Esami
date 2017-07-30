package eccezioni;

import java.util.logging.Logger;

/**
 * Classe Eccezione
 * 
 * @author Gabriele Fortunato
 *
 */
@SuppressWarnings("serial")
public class EsitoTeoriaException extends Exception {

	public EsitoTeoriaException(){
		Logger.getLogger("Esito teoria non valido");
	}
}

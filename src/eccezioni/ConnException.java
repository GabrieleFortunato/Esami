package eccezioni;

import java.util.logging.Logger;

/**
 * Eccezione che viene lanciata se la connessione al database non è riuscita
 * 
 * @author Gabriele Fortunato
 *
 */
@SuppressWarnings("serial")
public class ConnException extends Exception {

	public ConnException(){
		Logger.getLogger("Connessione non riuscita");
	}
}

package eccezioni;

import javax.swing.JOptionPane;

/**
 * Classe Eccezione
 * 
 * @author Gabriele Fortunato
 *
 */
@SuppressWarnings("serial")
final public class NoInterrException extends Exception {
	
	/**
	 * Stampa un messaggio quando viene lanciata l'eccezione
	 * @param message
	 */
	public void stampaMessaggio(String message){
		JOptionPane.showMessageDialog (
				null , message
			);
	}
	
}

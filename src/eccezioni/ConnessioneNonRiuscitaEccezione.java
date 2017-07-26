package eccezioni;

public class ConnessioneNonRiuscitaEccezione extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void stampaMessaggio(String messaggio){
		System.out.println(messaggio);
	}

}

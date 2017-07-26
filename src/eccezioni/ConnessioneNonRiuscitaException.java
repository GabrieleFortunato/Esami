package eccezioni;

public class ConnessioneNonRiuscitaException extends Exception {
	
	public void stampaMessaggio(String messaggio){
		System.out.println(messaggio);
	}

}

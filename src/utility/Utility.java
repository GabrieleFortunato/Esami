package utility;

/**
 * Classe Utility
 * 
 * @author Gabriele Fortunato
 *
 */
public class Utility {
	
	private final static int dieci = 10;
	
	/**
	 * Metodo costruttore
	 */
	private Utility(){
		
	}
	
	/**
	 * Verifica se un voto inserito � compreso tra 0 e 30
	 * @param voto
	 * @return
	 */
	public static boolean votoCorretto(int voto){
		return (0<=voto&&voto<=30);
	}
	
	/**
	 * Arrotonda un numero decimale
	 * @param a
	 * @param b
	 * @return
	 */
	public static double arrotonda(double a, double b){
		return Math.ceil(a*Math.pow(dieci, b))/Math.pow(dieci, b);
	}
	
}

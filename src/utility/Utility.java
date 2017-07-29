package utility;

/**
 * Classe Utility
 * 
 * @author Gabriele Fortunato
 *
 */
public class Utility {
	
	private final static int dieci = 10;
	
	private Utility(){
		
	}
	
	/**
	 * Restituisce una stringa per la query SQL
	 * @param s
	 * @return
	 */
	public static String stringForQuery(String s){
		int length = s.length();
		String result = s;
		for (int i=0;i<length;i++){
			int index = i;
			if (s.charAt(i)=='\''){
				result=s.substring(0,index+1).concat(s.substring(index));
				index = i+1;
			}
			index++;
		}
		return result;
	}
	
	/**
	 * Verifica se un voto inserito è compreso tra 0 e 30
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

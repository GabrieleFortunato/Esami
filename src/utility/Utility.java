package utility;

/**
 * Classe Utility
 * 
 * @author Gabriele Fortunato
 *
 */
public class Utility {
	
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
			if (s.charAt(i)=='\''){
				result=s.substring(0,i+1).concat(s.substring(i));
				i++;
			}
		}
		return result;
	}
	
	public static boolean votoCorretto(int voto){
		return (0<=voto&&voto<=30);
	}
	
	public static double arrotonda(double a, double b){
		return Math.ceil(a*Math.pow(10, b))/Math.pow(10, b);
	}
	
}

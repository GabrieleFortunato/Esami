package candidati;

import eccezioni.VotoException;
import utility.Utility;

/**
 * Classe Progetto
 * @author Gabriele Fortunato
 *
 */
public class Progetto {

	private int libreria;
	private int test;
	private int main;
	
	/**
	 * Metodo costruttore
	 * @param libreria
	 * @param test
	 * @param main
	 * @throws VotoException 
	 */
	public Progetto(int libreria, int test, int main) throws VotoException {
		this.setLibreria(libreria);
		this.setTest(test);
		this.setMain(main);
	}

	/**
	 * Imposta il voto della libreria
	 * @param libreria
	 * @throws VotoException 
	 */
	private void setLibreria(int libreria) throws VotoException {
		if (Utility.votoCorretto(libreria)){
			this.libreria = libreria;
		} else {
			throw new VotoException();
		}
		
	}

	/**
	 * Imposta il voto dei test
	 * @param test
	 * @throws VotoException 
	 */
	private void setTest(int test) throws VotoException {
		if (Utility.votoCorretto(libreria)){
			this.test = test;
		} else {
			throw new VotoException();
		}
	}

	/**
	 * Imposta il voto del main
	 * @param main
	 * @throws VotoException 
	 */
	private void setMain(int main) throws VotoException {
		if (Utility.votoCorretto(libreria)){
			this.main=main;
		} else {
			throw new VotoException();
		}
	}
	
	/**
	 * Restituisce il voto della libreria
	 * @return
	 */
	public int getLibreria() {
		return libreria;
	}

	/**
	 * Restituisce il voto dei test
	 * @return
	 */
	public int getTest() {
		return test;
	}

	/**
	 * Imposta il voto del main
	 * @return
	 */
	public int getMain() {
		return main;
	}

	/**
	 * Restituisce l'esito del progetto
	 * @return
	 */
	public double esito(){
		double five = 5.0;
		return (libreria+libreria+libreria+test+main)/five;
	}
	
}
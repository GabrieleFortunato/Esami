package candidati;

import eccezioni.VotoNonValidoEccezione;
import utility.Utility;

/**
 * Classe Progetto
 * 
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
	 * @throws VotoNonValidoEccezione 
	 */
	public Progetto(int libreria, int test, int main) throws VotoNonValidoEccezione {
		this.setLibreria(libreria);
		this.setTest(test);
		this.setMain(main);
	}

	/**
	 * Imposta il voto della libreria
	 * @param libreria
	 * @throws VotoNonValidoEccezione 
	 */
	private void setLibreria(int libreria) throws VotoNonValidoEccezione {
		if (Utility.voto(libreria)){
			this.libreria = libreria;
		} else {
			throw new VotoNonValidoEccezione();
		}
		
	}

	/**
	 * Imposta il voto dei test
	 * @param test
	 * @throws VotoNonValidoEccezione 
	 */
	private void setTest(int test) throws VotoNonValidoEccezione {
		if (Utility.voto(libreria)){
			this.test = test;
		} else {
			throw new VotoNonValidoEccezione();
		}
	}

	/**
	 * Imposta il voto del main
	 * @param main
	 * @throws VotoNonValidoEccezione 
	 */
	private void setMain(int main) throws VotoNonValidoEccezione {
		if (Utility.voto(libreria)){
			this.main=main;
		} else {
			throw new VotoNonValidoEccezione();
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
		return (libreria+libreria+test+main)/4.0;
	}
	
}
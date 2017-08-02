package candidati;

public class Progetto {

	private int libreria;
	private int test;
	private int main;
	
	/**
	 * Metodo costruttore
	 * @param libreria
	 * @param test
	 * @param main
	 */
	public Progetto(int libreria, int test, int main) {
		this.setLibreria(libreria);
		this.setTest(test);
		this.setMain(main);
	}

	/**
	 * Imposta il voto della libreria
	 * @param libreria
	 */
	private void setLibreria(int libreria) {
		this.libreria = libreria;
	}

	/**
	 * Imposta il voto dei casi di test
	 * @param test
	 */
	private void setTest(int test) {
		this.test = test;
	}

	/**
	 * Imposta il voto del main
	 * @param main
	 */
	private void setMain(int main) {
		this.main = main;
	}
	
	/**
	 * Restituisce l'esito del progetto
	 * @return
	 */
	public double esito(){
		return (libreria+libreria+test+main)/4.0;
	}
	
}

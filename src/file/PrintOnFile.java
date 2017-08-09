package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import javax.swing.JOptionPane;
import candidati.Candidato;
import utility.Utility;

/**
 * Classe PrintOnFile
 * 
 * @author Gabriele Fortunato
 *
 */
public class PrintOnFile {

	/**
	 * Metodo costruttore
	 */
	private PrintOnFile(){
		
	}
	
	/**
	 * Stampa su files gli esiti finali dei candidati
	 * @param iterator
	 */
	public static void printOnFile(HashSet<Candidato> candidati){
		File dir = new File("Esiti");
		if (!dir.exists()){
			dir.mkdir();
		}
		PrintStream output = null;
		try {
			for (Candidato c: candidati){
					int esito = (int)c.getProgetto().esito();
					output = new PrintStream(
							new File("Esiti\\"+c.getCognome()+" "+c.getNome()+".txt")
					);
					if (c.esito()>30){
						output.println(
								"Esito teoria: "+c.getEsitoTeoria()
					);
						output.println(
								"Esito progetto: "+esito
					);
						output.println(
								"Esame superato con 30 e lode"
								);
					} else if (c.esito()<18){
						output.println(
								"Esito teoria: "+c.getEsitoTeoria()		
								);
						output.println(
								"Esito progetto: "+esito
								);
						output.println(
								"Esame non superato"
								);
						} else{
							output.println(
									"Esito teoria: "+c.getEsitoTeoria()
							);
							output.println(
									"Esito progetto: "+esito
							);
							output.println(
									"Esame superato con "+(int)Utility.arrotonda(c.esito(),0)
							);
									
						}
						output.flush();
						output.close();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog (
					null , "Impossibile aprire il file"
			);
		}
	}
}

package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.logging.Logger;
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
		PrintStream output = null;
		try {
			for (Candidato c: candidati){
				int esito = (int)c.getProgetto().esito();
				if (esito>=18){
					output = new PrintStream(
							new File(c.getCognome()+" "+c.getNome()+".txt"));
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
					} else {
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
			}
		} catch (FileNotFoundException e) {
			Logger.getLogger("Il file non può essere aperto");
		}
	}	
	
	public static void daInserireTeoria(HashSet<Candidato> candidati){
		PrintStream output = null;
		if (candidati.size()>0){
			try {
				output=new PrintStream(new File("Inserire teoria.txt"));
				output.println("\nCANDIDATI DI CUI INSERIRE L'ESITO DELLA TEORIA\n");
				for (Candidato c: candidati){
					output.println(c.getCognome()+" "+c.getNome());
				}
			} catch (FileNotFoundException e) {
				Logger.getLogger("Il file non può essere aperto");
			} 
		} else {
			try {
				output=new PrintStream(new File("Inserire teoria.txt"));
				output.println("Non ci sono candidati di cui inserire l'esito della prate teorica");
			} catch (FileNotFoundException e) {
				Logger.getLogger("Il file non può essere aperto");
			}
		}
		if (output!=null){
			output.flush();
			output.close();
		}
	}
	
}

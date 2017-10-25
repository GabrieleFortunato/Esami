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
		PrintStream output;
		PrintStream output1;
		try {
			output = new PrintStream(
					new File("Candidati promossi.txt"));
			output1 = new PrintStream(
					new File("Candidati non promossi.txt"));
			for (Candidato c: candidati){
				int esito = (int)Utility.arrotonda(c.esito(), 2.0);
				int progetto = (int) Utility.arrotonda(c.getProgetto().esito(),2.0);
				if (c.promosso()){
					output.println(" ");
					String nominativo = "\n"+c.getCognome().toUpperCase()+" "+c.getNome();
					output.println(nominativo);
					if (esito>=31){
						output.println("Esito teoria: "+c.getEsitoTeoria());
						output.println("Esito progetto: "+progetto);
						output.println("Esame superato con 30 e lode");
					} else if (esito>=18) {
						output.println("Esito teoria: "+c.getEsitoTeoria());
						output.println("Esito progetto: "+progetto);
						output.println("Esame superato con "+esito);
					}
				}
				else {
					output1.println(" ");
					String nominativo = "\n"+c.getCognome().toUpperCase()+" "+c.getNome();
					output1.println(nominativo);
					output1.println("Esito teoria: "+c.getEsitoTeoria());
					output1.println("Esito progetto: "+progetto);
					output1.println("Esame non superato. ");
				}
			}
			output.flush();
			output.close();
			output1.flush();
			output1.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
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

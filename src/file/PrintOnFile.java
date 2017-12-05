package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
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
	
	private static void stampaPromossi(TreeSet<Candidato> candidati) {
		try {
			PrintStream output = new PrintStream(
					new File("Candidati promossi.txt"));
			for (Candidato c:candidati) {
				int esito = c.esito();
				int primo = (int) Utility.arrotonda(c.getProgetto().esito(),2.0);
				String nominativo = "\n"+c.getCognome().toUpperCase()+" "+c.getNome();
				output.println("");
				output.println(nominativo);
				if (esito>=31){
					output.println("Esito teoria: "+c.getEsitoTeoria());
					output.println("Esito progetto: "+primo);
					output.println("Esame superato con 30 e lode");
				} else {
					output.println("Esito teoria: "+c.getEsitoTeoria());
					output.println("Esito primo progetto: "+primo);					
					output.println("Esame superato con "+esito);
				}
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void stampaNonPromossi(ArrayList<Candidato> candidati) {
		try {
			PrintStream  output = new PrintStream(
					new File("Candidati non promossi.txt"));
			for (Candidato c:candidati) {
				int primo = (int) Utility.arrotonda(c.getProgetto().esito(),2.0);
				String nominativo = "\n"+c.getCognome().toUpperCase()+" "+c.getNome();
				output.println("");
				output.println(nominativo);
				output.println("Esito teoria: "+c.getEsitoTeoria());
				output.println("Esito primo progetto: "+primo);
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Stampa su files gli esiti finali dei candidati
	 * @param iterator
	 */
	public static void printOnFile(HashSet<Candidato> candidati){
		ArrayList<Candidato> nonPromossi = new ArrayList<>();
		TreeSet<Candidato> promossi = new TreeSet<>();
		for (Candidato c:candidati) {
			try {
				Thread.sleep(100);
				System.out.print(c.getCognome().toUpperCase()+" "+c.getNome());
				if (c.promosso()) {
					System.out.println("--->Esame superato");
					promossi.add(c);
				} else {
					System.out.println("--->Esame non superato");
					nonPromossi.add(c);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (!promossi.isEmpty()) {
			stampaPromossi(promossi);
		}
		if (!nonPromossi.isEmpty()) {
			stampaNonPromossi(nonPromossi);
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
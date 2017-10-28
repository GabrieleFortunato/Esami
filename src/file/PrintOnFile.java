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
				int esito = (int)Utility.arrotonda(c.esito(), 2.0);
				int primo = (int) Utility.arrotonda(c.getPrimo().esito(),2.0);
				int secondo = (int) Utility.arrotonda(c.getSecondo().esito(),2.0);
				output.println(" ");
				String nominativo = "\n"+c.getCognome().toUpperCase()+" "+c.getNome();
				output.println(nominativo);
				if (esito>=31){
					output.println("Esito teoria: "+c.getEsitoTeoria());
					output.println("Esito primo progetto: "+primo);
					output.println("Esito secondo progetto: "+secondo);
					output.println("Esame superato con 30 e lode");
				} else {
					output.println("Esito teoria: "+c.getEsitoTeoria());
					output.println("Esito primo progetto: "+primo);
					output.println("Esito secondo progetto: "+secondo);
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
				int primo = (int) Utility.arrotonda(c.getPrimo().esito(),2.0);
				int secondo = (int) Utility.arrotonda(c.getSecondo().esito(),2.0);
				output.println(" ");
				String nominativo = "\n"+c.getCognome().toUpperCase()+" "+c.getNome();
				output.println(nominativo);
				output.println("Esito teoria: "+c.getEsitoTeoria());
				output.println("Esito primo progetto: "+primo);
				output.println("Esito secondo progetto: "+secondo);
				output.println("Esame non superato. ");
				
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
			if (c.promosso()) {
				promossi.add(c);
			} else {
				nonPromossi.add(c);
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

package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
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
	
	private static void stampaCandidatoPromosso(Candidato c) {
		PrintStream output;
		try {
			String nominativo = c.getCognome().toUpperCase()+" "+c.getNome();
			output = new PrintStream(new File("Candidati promossi\\"+nominativo+".txt"));
			int esito = c.esito();
			int primo = (int) Utility.arrotonda(c.getPrimoProgetto().esito(),2.0);
			int secondo = (int) Utility.arrotonda(c.getSecondo().esito(),2.0);
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
		output.flush();
		output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void stampaPromossi(TreeSet<Candidato> candidati) {
			for (Candidato c:candidati) {
				stampaCandidatoPromosso(c);
		}
	}

	private static void stampaCandidatoNonPromosso(Candidato c) {
		int primo = (int) Utility.arrotonda(c.getPrimoProgetto().esito(),2.0);
		int secondo = (int) Utility.arrotonda(c.getSecondo().esito(),2.0);
		PrintStream output;
		try {
			String nominativo = c.getCognome().toUpperCase()+" "+c.getNome();
			output = new PrintStream(new File("Candidati non promossi\\"+nominativo+".txt"));
			output.println("Esito teoria: "+c.getEsitoTeoria());
			output.println("Esito primo progetto: "+primo);
			output.println("Esito secondo progetto: "+secondo);
			output.flush();
			output.close();		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	private static void stampaNonPromossi(ArrayList<Candidato> candidati) {
			for (Candidato c:candidati) {
				stampaCandidatoNonPromosso(c);		
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
				System.out.print(c.getCognome().toUpperCase()+" "+c.getNome());
				if (c.promosso()) {
					System.out.println("--->Esame superato");
					promossi.add(c);
				} else {
					System.out.println("--->Esame non superato");
					nonPromossi.add(c);
				}
		}
		if (!promossi.isEmpty()) {
			File pr = new File("Candidati promossi");
			pr.mkdir();
			stampaPromossi(promossi);
		}
		if (!nonPromossi.isEmpty()) {
			File nopr = new File("Candidati non promossi");
			nopr.mkdir();
			stampaNonPromossi(nonPromossi);
		} 
	} 
	
}
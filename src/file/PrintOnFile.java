package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import candidati.Candidato;
import utility.Utility;

/**
 * Classe PrintOnFile
 * 
 * @author Gabriele Fortunato
 *
 */
public class PrintOnFile {

	private PrintOnFile(){
		
	}
	
	/**
	 * Stampa su files gli esiti finali dei candidati
	 * @param iterator
	 */
	public static void printOnFile(Iterator<Candidato> iterator){
		File dir = new File("Esiti");
		if (!dir.exists()){
			dir.mkdir();
		}
		try {
			while (iterator.hasNext()){
				Candidato candidato = iterator.next();
				int esito = (int)candidato.getProgetto().esito();
				PrintStream output = new PrintStream(
						new File("Esiti\\"+candidato.getCognome()+" "+candidato.getNome()+".txt")
				);
				if (candidato.esito()>30){
					output.println(
							"Esito teoria: "+candidato.getEsitoTeoria()
					);
					output.println(
							"Esito progetto: "+esito
					);
					output.println(
							"Esame superato con 30 e lode"
					);
				} else if (candidato.esito()<18){
					output.println(
							"Esito teoria: "+candidato.getEsitoTeoria()		
					);
					output.println(
							"Esito progetto: "+esito
					);
					output.println(
							"Esame non superato"
					);
				} else{
					output.println(
							"Esito teoria: "+candidato.getEsitoTeoria()
					);
					output.println(
							"Esito progetto: "+esito
					);
					output.println(
							"Esame superato con "+(int)Utility.arrotonda(candidato.esito(), 0)
					);
							
				}
				output.flush();
				output.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cancella file
	 * @param c
	 */
	public static void cancellaFile(Candidato c){
		File file = new File(c.getCognome()+" "+c.getNome()+".txt");
		if (file.exists()){
			file.delete();
		}
	}
	
	public static void stampaCandidatiTeoria(Iterator<Candidato> iterator){
		Iterator<Candidato> iter = iterator;
		try {
			PrintStream output = new PrintStream(
					new File("InserimentoTeoria.txt")
			);
			if (!iter.hasNext()){
				output.println("Non bisogna inserire esiti della parte teorica");
			} else {
				output.println("\nCANDIDATI DI CUI INSERIRE L'ESITO DELLA TEORIA:\n");
				while (iter.hasNext()){
					Candidato c = iter.next();
					output.println(c.getCognome()+" "+c.getNome());
				}
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Stampa su file l'elenco dei candidati da interrogare
	 * @param candidati
	 */
	public static void stampaCandidatiDaInterrogare(HashSet<Candidato> candidati){
		try {
			PrintStream output = new PrintStream(
					new File("Elenco candidati da interrogare.txt")
			);
			if (candidati.size()==0){
				output.println("Non ci sono candidati da interrogare");
			} else {
				output.println("\nCANDIDATI DA INTERROGARE\n:");
				for (Candidato c:candidati){
					output.println(c.getCognome()+" "+c.getNome());
				}
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

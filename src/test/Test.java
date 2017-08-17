package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import candidati.Candidato;
import candidati.Progetto;
import database.Cancellazione;
import database.Inizializzazione;
import database.Inserimento;
import database.Lettura;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;
import file.PrintOnFile;

public class Test {

	private static String teoria(){
		String teoria = null;
		Random random = new Random();
		int a = random.nextInt(8);
		switch (a){
		case 0: teoria= "quasi sufficiente";
		break;
		case 1: teoria= "sufficiente";
		break;
		case 2: teoria= "più che sufficiente";
		break;
		case 3: teoria= "quasi buono";
		break;
		case 4: teoria= "buono";
		break;
		case 5: teoria= "più che buono";
		break;
		case 6: teoria= "quasi ottimo";
		break;
		case 7: teoria= "ottimo";
		break;
		}
		return teoria;
	}
	
	private static ArrayList<Candidato> candidati() {
		ArrayList<Candidato> candidati = new ArrayList<>();
		for (int i=0;i<31;i++){
			Progetto p = null;
			try {
				p = new Progetto(i,i,i);
			} catch (VotoException e1) {
				System.out.println("Voto non valido");
			}
			String teoria = teoria();
			Candidato c = null;
			try {
				c = new Candidato("nome"+i,"cognome"+i,teoria,p);
			} catch (EsitoTeoriaException e) {
				System.out.println("Esito della teoria non valido");
			}
			candidati.add(c);
		} 
		return candidati;
	}
	
	private static void database(ArrayList<Candidato> candidati){
		for (Candidato c:candidati){
			try {
				Inserimento.inserisciPrenotazione(c.getNome(), c.getCognome());
				Inserimento.inserisciEsitoTeoria(c.getNome(), c.getCognome(), c.getEsitoTeoria());
				Inserimento.inserisciEsitoProgetto(
						c.getNome(), c.getCognome(), 
						Integer.toString(c.getProgetto().getLibreria()),
						Integer.toString(c.getProgetto().getTest()),
						Integer.toString(c.getProgetto().getMain())
				);
				System.out.println(c.getNome()+" "+c.getCognome()+" inserito nel database");
			} catch (SQLException e) {
				System.out.println("Candidato già prenotato");
			}
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Candidato> candidati = candidati();
		try {
			for (Candidato c:candidati){
				Cancellazione.cancellaCandidato(c.getNome(), c.getCognome());
				System.out.println(c.getNome()+" "+c.getCognome()+" cancellato da database");
			}
		} catch (SQLException e1) {
			System.out.println("Impossibile cancellazione il candidato dal database");
		}
		try {
			Inizializzazione.inizializzaDatabase();
		} catch (SQLException e) {
			System.out.println("Impossibile inizializzare il database");
		}
		database(candidati);
		try {
			PrintOnFile.printOnFile(Lettura.proveCompletate());
		} catch (SQLException e) {
			System.out.println("Impossibile leggere da database");
		}
	}

}

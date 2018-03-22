package database;

import java.util.ArrayList;

import candidati.Candidato;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Utility.pass());
		System.out.println(Utility.user());
		try {
			ArrayList<Candidato> candidati = new ArrayList<>(database.Lettura.proveCompletate());
			for (Candidato c: candidati) {
				System.out.println(c);
			}
		} catch (VotoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EsitoTeoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

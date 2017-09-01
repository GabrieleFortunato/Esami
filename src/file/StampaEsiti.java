package file;

import javax.swing.JOptionPane;
import database.Lettura;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;

public class StampaEsiti {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			PrintOnFile.printOnFile(Lettura.proveCompletate());
		} catch (VotoException e) {
			JOptionPane.showMessageDialog(null,"Voto progetto non valido");
		} catch (EsitoTeoriaException e) {
			JOptionPane.showMessageDialog(null,"Esito teoria non valido");
		}

	}

}

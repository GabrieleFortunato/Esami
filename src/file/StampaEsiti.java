package file;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.swing.JOptionPane;
import database.Lettura;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;

public class StampaEsiti {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		try {
			PrintOnFile.printOnFile(Lettura.proveCompletate());
		} catch (VotoException e) {
			JOptionPane.showMessageDialog(null,"Voto progetto non valido");
		} catch (EsitoTeoriaException e) {
			JOptionPane.showMessageDialog(null,"Esito teoria non valido");
		} catch (NamingException e) {
			JOptionPane.showMessageDialog(null,"Problemi di collegamento con il database");
		}

		try {
			PrintOnFile.daInserireTeoria(Lettura.candidatoInserireTeoria());
		} catch (VotoException | EsitoTeoriaException | NamingException e) {
			JOptionPane.showMessageDialog(null,"Esito teoria non valido");
		}
	}

}

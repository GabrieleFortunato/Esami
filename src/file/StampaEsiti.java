package file;

import java.sql.SQLException;

import javax.naming.NamingException;

import database.Lettura;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;

public class StampaEsiti {

	public static void main(String[] args) throws NamingException {
		// TODO Auto-generated method stub
		
		try {
			PrintOnFile.printOnFile(Lettura.proveCompletate());
		} catch (SQLException e) {
			
		} catch (VotoException e) {
		
		} catch (EsitoTeoriaException e) {
		
		}

	}

}

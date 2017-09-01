package test;

import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.NamingException;

import database.Lettura;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;
import file.PrintOnFile;

public class Test {

	public static void main(String[] args) {
		try {
			PrintOnFile.printOnFile(Lettura.proveCompletate());
		} catch (SQLException e) {
			Logger.getLogger("");
		} catch (VotoException e) {
			Logger.getLogger("");
		} catch (EsitoTeoriaException e) {
			Logger.getLogger("");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

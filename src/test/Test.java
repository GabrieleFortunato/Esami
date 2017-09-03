package test;

import java.sql.SQLException;
import java.util.logging.Logger;
import database.Lettura;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;
import file.PrintOnFile;

public class Test {

	public static void main(String[] args) throws SQLException {
		try {
			PrintOnFile.printOnFile(Lettura.proveCompletate());
			PrintOnFile.daInserireTeoria(Lettura.candidatoInserireTeoria());
		}  catch (VotoException e) {
			Logger.getLogger("");
		} catch (EsitoTeoriaException e) {
			Logger.getLogger("");
		}
	}

}

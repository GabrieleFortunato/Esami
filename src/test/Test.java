package test;

import java.util.logging.Logger;
import database.Lettura;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;
import file.PrintOnFile;

public class Test {

	public static void main(String[] args) {
		try {
			PrintOnFile.printOnFile(Lettura.proveCompletate());
		}  catch (VotoException e) {
			Logger.getLogger("");
		} catch (EsitoTeoriaException e) {
			Logger.getLogger("");
		}
	}

}

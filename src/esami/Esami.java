package esami;

import java.sql.SQLException;

import eccezioni.EsitoTeoriaException;
import eccezioni.VotoNonValidoException;
import file.PrintOnFile;

public class Esami {

	public static void main(String[] args) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, 
			SQLException, VotoNonValidoException, EsitoTeoriaException {
		// TODO Auto-generated method stub
		EsitoEsame esito = new EsitoEsame();
		PrintOnFile.printOnFile(esito.iterator());
	}

}

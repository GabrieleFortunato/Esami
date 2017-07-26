package esami;

import file.PrintOnFile;

public class StampaEsiti {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EsitoEsame esiti = new EsitoEsame();
		PrintOnFile.printOnFile(esiti.iterator());

	}

}

package simulation;

public class Simulation {

	/**
	 * Cette classe repr�sente l'application dans son ensemble.
	 */
	public static void main(String[] args) {
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

//		List<Usine> usineList = XmlParsing.usineListParse();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

}

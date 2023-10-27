package simulation;

import Materials.UsineAile;
import Materials.UsineMatiere;
import Platform.Chemin;
import ressources.XMLUtils;

import javax.swing.SwingWorker;
import java.util.stream.Collectors;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 100;
	Chemin cheminMetal1;
	Chemin cheminMetal2;
	Chemin cheminMetal3;
	Chemin cheminAile;
	Chemin cheminAvion;
	Chemin cheminMoteur;

	public Environnement() {
	}

	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			/**
			 * C'est ici que vous aurez à faire la gestion de la notion de tour.
			 */
			cheminMetal1 = XMLUtils.getInstance().chemins.stream().filter(ch -> ch == ch.getChemin(11, 21)).collect(Collectors.toList()).get(0);
			cheminAile = XMLUtils.getInstance().chemins.stream().filter(ch -> ch == ch.getChemin(21, 41)).collect(Collectors.toList()).get(0);
			firePropertyChange("TEST", null, "Ceci est un test");
			firePropertyChange("chemin1", null, cheminMetal1);
		}
		return null;
	}

}

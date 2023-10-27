package Materials;

import ressources.XMLUtils;

import java.util.stream.Collectors;

public class VenteAleatoire implements IStrategyVente {

    private Entrepot entrepot;

    public VenteAleatoire() {
        if (XMLUtils.getInstance() != null)
            this.entrepot = (Entrepot) XMLUtils.getInstance().chemins.stream().filter(ch -> ch == ch.getChemin(41, 51)).collect(Collectors.toList()).get(0).getDestination().getUsine();
    }

    @Override
    public void vente() {
        // Générer un nombre aléatoire entre 1 et 5
        int randomNumber = (int) (Math.random() * entrepot.getQuantite()) + 1;

        System.out.println("Nombre aléatoire entre 1 et 5 : " + randomNumber);
        int quantite = entrepot.getQuantite();
        if(quantite > randomNumber) {
            quantite -= randomNumber;
            entrepot.setQuantite(quantite);
        }
    }
}

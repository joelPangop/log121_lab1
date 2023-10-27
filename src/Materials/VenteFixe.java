package Materials;

import ressources.XMLUtils;

import java.util.stream.Collectors;

public class VenteFixe implements IStrategyVente {
    private Entrepot entrepot;

    public VenteFixe() {
        if (XMLUtils.getInstance() != null)
            this.entrepot = (Entrepot) XMLUtils.getInstance().chemins.stream().filter(ch -> ch == ch.getChemin(41, 51)).collect(Collectors.toList()).get(0).getDestination().getUsine();
    }

    @Override
    public void vente() {
        int intervalle = 2; // Intervalle en secondes
        int tempsTotal = this.entrepot.getQuantite(); // Temps total en secondes
        int quantiteRestante = 3;
        for (int tempsRestant = tempsTotal; tempsRestant >= 0; tempsRestant--) {
            System.out.println("Temps restant : " + tempsRestant + " secondes");
            quantiteRestante = tempsRestant;
            // Pause de 2 secondes
            try {
                Thread.sleep(intervalle * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.entrepot.setQuantite(quantiteRestante);

        System.out.println("Décompte terminé !");
    }
}

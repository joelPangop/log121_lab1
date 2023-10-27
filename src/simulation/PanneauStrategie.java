package simulation;

import Materials.IProductionObserver;
import Materials.IStrategyVente;
import Materials.VenteAleatoire;
import Materials.VenteFixe;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class PanneauStrategie extends JPanel {

    private static final long serialVersionUID = 1L;

    protected List<IProductionObserver> observers;

    public PanneauStrategie() {

        ButtonGroup groupeBoutons = new ButtonGroup();
        JRadioButton strategie1 = new JRadioButton("Vente fixe");
        JRadioButton strategie2 = new JRadioButton("Vente aleatoire");

        JButton boutonConfirmer = new JButton("Confirmer");
        observers = new ArrayList<>();
        boutonConfirmer.addActionListener((ActionEvent e) -> {
            // TODO - Appeler la bonne strat�gie
            System.out.println(getSelectedButtonText(groupeBoutons));
            IStrategyVente TypeVente = null;
            if (getSelectedButtonText(groupeBoutons).equals("Vente aleatoire")) {
                TypeVente = new VenteAleatoire();
                TypeVente.vente();
            }
            if (getSelectedButtonText(groupeBoutons).equals("Vente fixe")) {
                TypeVente = new VenteFixe();
                TypeVente.vente();
            }
            // Fermer la fen�tre du composant
            SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
        });

        JButton boutonAnnuler = new JButton("Annuler");

        boutonAnnuler.addActionListener((ActionEvent e) -> {
            // Fermer la fen�tre du composant
            SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
        });

        groupeBoutons.add(strategie1);
        groupeBoutons.add(strategie2);
        add(strategie1);
        add(strategie2);
        add(boutonConfirmer);
        add(boutonAnnuler);

    }

    /**
     * Retourne le bouton s�lectionn� dans un groupe de boutons.
     *
     * @param groupeBoutons
     * @return
     */
    public String getSelectedButtonText(ButtonGroup groupeBoutons) {
        for (Enumeration<AbstractButton> boutons = groupeBoutons.getElements(); boutons.hasMoreElements(); ) {
            AbstractButton bouton = boutons.nextElement();
            if (bouton.isSelected()) {
                return bouton.getText();
            }
        }

        return null;
    }

}

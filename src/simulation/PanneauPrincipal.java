package simulation;

import Platform.Reseau;

import javax.swing.*;
import java.awt.*;

public class PanneauPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;

    // Variables temporaires de la demonstration:
    private Point position = new Point(0, 0);
    private Point vitesse = new Point(1, 1);
    private int taille = 32;
    Reseau reseau;

    public PanneauPrincipal() {
        reseau = new Reseau();
        add(reseau);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // On ajoute à la position le delta x et y de la vitesse
//        position.translate(vitesse.x, vitesse.y);
//        g.fillRect(position.x, position.y, taille, taille);
        reseau.paint(g);
    }

}

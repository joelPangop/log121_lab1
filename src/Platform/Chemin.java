package Platform;

import Materials.AbstractComposante;
import Materials.ComposantEnum;
import Materials.ComposanteUsine;
import ressources.XMLUtils;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chemin extends JPanel {

    private Noeud source;
    private Noeud destination;
    private Point vitesse;

    Timer timer;

    private int repeatCount = 3;  // Nombre de répétitions
    private int currentRepeat = 0;  // Répétition actuelle

    public Chemin(Noeud source, Noeud destination, Point vitesse) {
        this.source = source;
        this.destination = destination;
        this.vitesse = vitesse;
    }

    public void dessinerChemin(Graphics g, int sourceX, int sourceY, int destinationX, int destinationY) {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.BLACK);
        g.drawLine(sourceX, sourceY, destinationX, destinationY);
    }

    public void deplacement(Graphics g, ComposanteUsine matiere, Timer timer) {

        if (source.getId() == 11 && destination.getId() == 21) {
//            ComposanteUsine matiere = XMLUtils.getInstance().composanteUsines.get(source.getUsine().getType());
//            timer = new Timer(source.getUsine().getIntervaleProduction(), null);  // Met à jour toutes les 1000 millisecondes (1 seconde)
            matiere.getPosition().translate(1, 0);

            if (source.getPosition().getX() < destination.getPosition().getX()) {
                matiere.getPosition().translate(1, 0);
                matiere.drawImage(g, (int) matiere.getPosition().getX(), (int) matiere.getPosition().getY());
            }

            // Vérifiez si l'animation doit être répétée
            if (matiere.getPosition().getX() <= destination.getPosition().getX()) {
                currentRepeat++;
                if (currentRepeat < repeatCount) {
                    matiere.setPosition(new Point((int) source.getPosition().getX(), (int) source.getPosition().getY()));
                    timer.restart();
                } else {
                    // Arrêtez le Timer après le nombre de répétitions spécifié
                    timer.stop();
                }
            }
        }
        repaint();
    }

    public Noeud getSource() {
        return source;
    }

    public void setSource(Noeud source) {
        this.source = source;
    }

    public Noeud getDestination() {
        return destination;
    }

    public void setDestination(Noeud destination) {
        this.destination = destination;
    }

    public Point getVitesse() {
        return vitesse;
    }

    public void setVitesse(Point vitesse) {
        this.vitesse = vitesse;
    }
}

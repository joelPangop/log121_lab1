package simulation;

import Materials.ComposanteUsine;
import Platform.Chemin;
import Platform.Reseau;
import ressources.XMLUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;

    // Variables temporaires de la demonstration:
    private Point position = new Point(0, 0);
    private Point vitesse = new Point(1, 1);
    private int taille = 32;

    private int repeatCount = 3;  // Nombre de répétitions
    private int currentRepeat = 0;  // Répétition actuelle

    private int currentImageIndex = 0;

    private Reseau reseau;

    java.util.List<ComposanteUsine> composanteUsines;
    private Timer timer;

    MenuFenetre menuFenetre;

    public PanneauPrincipal() {
//        reseau = new Reseau();
        reseau = new Reseau();
        XMLUtils.getInstance().loadXMLData("C:\\Users\\User\\WorkSpace\\log121_lab1\\src\\ressources\\configuration.xml");
//
//        if (XMLUtils.getInstance().chemins != null) {
//            for (Chemin chemin : XMLUtils.getInstance().chemins) {
//                if (chemin.getSource().getId() == 11 && chemin.getDestination().getId() == 21) {
//                    int i = 0;
//                    while (i < 10) {
//                        ComposanteUsine composanteUsine = XMLUtils.getInstance().composanteUsines.get("metal");
//                        composanteUsine.setPosition(new Point((int) chemin.getSource().getPosition().getX(), (int) chemin.getSource().getPosition().getY()));
//                        composanteUsines.add(composanteUsine);
//                        i++;
//                    }
//                }
//            }
//        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // On ajoute à la position le delta x et y de la vitesse
//        position.translate(vitesse.x, vitesse.y);
//        g.fillRect(position.x, position.y, taille, taille);

        if (reseau != null) {
            add(reseau);
            reseau.paint(g);
//            composanteUsine.drawImage(g);
            for (Chemin chemin : XMLUtils.getInstance().chemins) {
                if (chemin.getSource().getId() == 11 && chemin.getDestination().getId() == 21) {
//                    ComposanteUsine composanteUsine = XMLUtils.getInstance().composanteUsines.get("metal");
//                    composanteUsine.setPosition(new Point((int) chemin.getSource().getPosition().getX(), (int) chemin.getSource().getPosition().getY()));
//                    composanteUsine.drawImage(g);
//                    deplacerMateriel(composanteUsine);

//                    timer = new Timer(5000, new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
                            ComposanteUsine composanteUsine = XMLUtils.getInstance().composanteUsines.get("metal");
                            composanteUsine.setPosition(new Point((int) chemin.getSource().getPosition().getX(), (int) chemin.getSource().getPosition().getY()));
                            composanteUsine.drawImage(g);
//                        }
//                    });
//                    timer.start();
                }
            }
//            for (Chemin chemin : XMLUtils.getInstance().chemins) {
//                if (chemin.getSource().getId() == 11 && chemin.getDestination().getId() == 21) {
//                    timer = new Timer(chemin.getSource().getUsine().getIntervaleProduction(), null);  // Met à jour toutes les 1000 millisecondes (1 seconde)
//                    timer.setInitialDelay(0);  // Démarrage immédiat
//                    timer.start();
//                    composanteUsine.getPosition().translate(1, 0);
//                }
//            }
//            for (Chemin chemin : XMLUtils.getInstance().chemins) {
//                if (chemin.getSource().getId() == 11 && chemin.getDestination().getId() == 21) {
//                    if (chemin.getSource().getPosition().getX() < chemin.getDestination().getPosition().getX()) {
//                        composanteUsine.getPosition().translate(1, 0);
//                        composanteUsine.drawImage(g, (int) composanteUsine.getPosition().getX(), (int) composanteUsine.getPosition().getY());
//                    }
//
//                    // Vérifiez si l'animation doit être répétée
//                    if (composanteUsine.getPosition().getX() == chemin.getDestination().getPosition().getX()) {
//                        currentRepeat++;
//                        if (currentRepeat < repeatCount) {
//                            composanteUsine.setPosition(new Point((int) chemin.getSource().getPosition().getX(), (int) chemin.getSource().getPosition().getY()));
//                            timer.restart();
//                        } else {
//                            // Arrêtez le Timer après le nombre de répétitions spécifié
//                            timer.stop();
//                        }
//                    }
//                }
//            }
//            repaint();zz
        }
    }

    public void deplacerMateriel(ComposanteUsine composanteUsine) {
        if (reseau != null && XMLUtils.getInstance().chemins != null) {
            for (Chemin chemin : XMLUtils.getInstance().chemins) {
                if (chemin.getSource().getId() == 11 && chemin.getDestination().getId() == 21) {

//                        timer = new Timer(chemin.getSource().getUsine().getIntervaleProduction(), null);  // Met à jour toutes les 1000 millisecondes (1 seconde)
                    timer = new Timer(chemin.getSource().getUsine().getIntervaleProduction(), new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
//                            composanteUsine.deplacer(g, chemin);
                            while ((int) composanteUsine.getPosition().getX() <= chemin.getDestination().getPosition().getX()) {
                                composanteUsine.getPosition().translate(1, 0);
                                repaint();

                            }
                        }
                    });
                }
            }
        }
    }

    public void startAnimation() {
        timer.setInitialDelay(0);  // Démarrage immédiat

        timer.start();
    }

    public Reseau getReseau() {
        return reseau;
    }

    public void setReseau(Reseau reseau) {
        this.reseau = reseau;
    }
}

package simulation;

import Platform.Chemin;
import Platform.Reseau;
import ressources.XMLUtils;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class PanneauPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;

    //On declare une variable pour dessiner le reseau dans le panneau
    private Reseau reseau;
    Chemin cheminMetal1;
    Chemin cheminAile;
    Chemin cheminAvion;
    Chemin cheminMoteur;
    Chemin cheminMetal2;
    Chemin cheminMetal3;

    public PanneauPrincipal() {
//        reseau = new Reseau();
        reseau = new Reseau();

        XMLUtils.getInstance().loadXMLData("C:\\Users\\User\\WorkSpace\\log121_lab1\\src\\ressources\\configuration.xml");
        if (XMLUtils.getInstance().chemins != null) {
            cheminMetal1 = XMLUtils.getInstance().chemins.stream().filter(ch -> ch == ch.getChemin(11, 21)).collect(Collectors.toList()).get(0);
            cheminAile = XMLUtils.getInstance().chemins.stream().filter(ch -> ch == ch.getChemin(21, 41)).collect(Collectors.toList()).get(0);
            cheminMetal1.getDestination().getUsine().attachObserver(cheminAile);
//            cheminAile.getProductedImages().add(XMLUtils.getInstance().composanteUsines.get("aile").getImage());
//            cheminAile.getProductedImagePositions().add(new Point(cheminAile.getSource().getPosition().x,cheminAile.getSource().getPosition().y));
            cheminAvion = XMLUtils.getInstance().chemins.stream().filter(ch -> ch == ch.getChemin(41, 51)).collect(Collectors.toList()).get(0);
            cheminMoteur = XMLUtils.getInstance().chemins.stream().filter(ch -> ch == ch.getChemin(31, 41)).collect(Collectors.toList()).get(0);
            cheminMetal2 = XMLUtils.getInstance().chemins.stream().filter(ch -> ch == ch.getChemin(12, 31)).collect(Collectors.toList()).get(0);
            cheminMetal2.getDestination().getUsine().attachObserver(cheminMoteur);
            cheminMetal3 = XMLUtils.getInstance().chemins.stream().filter(ch -> ch == ch.getChemin(13, 31)).collect(Collectors.toList()).get(0);
            cheminMetal2.getDestination().getUsine().attachObserver(cheminMoteur);
            cheminMoteur.getDestination().getUsine().attachObserver(cheminAvion);
            cheminAile.getDestination().getUsine().attachObserver(cheminAvion);

            cheminAvion.getDestination().getUsine().attachObserver(cheminMetal1);
            cheminAvion.getDestination().getUsine().attachObserver(cheminMetal2);
            cheminAvion.getDestination().getUsine().attachObserver(cheminMetal3);
            cheminAvion.getDestination().getUsine().attachObserver(cheminAile);
            cheminAvion.getDestination().getUsine().attachObserver(cheminMoteur);

            add(cheminMetal1);
            add(cheminAile);
            add(cheminAvion);
            add(cheminMoteur);
            add(cheminMetal2);
            add(cheminMetal3);
            cheminMetal1.startAnimation();
            cheminMetal2.startAnimation();
            cheminMetal3.startAnimation();
        }
    }
int i = 0;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        position.translate(vitesse.x, vitesse.y);
//        g.fillRect(position.x, position.y, taille, taille);
        i++;
        if (reseau != null) {
//            add(reseau);
            reseau.paint(g);

            cheminMetal1.paint(g);
            cheminAile.paint(g);
            cheminAvion.paint(g);
            cheminMoteur.paint(g);
            cheminMetal2.paint(g);
            cheminMetal3.paint(g);

        }
    }

    public Reseau getReseau() {
        return reseau;
    }

    public void setReseau(Reseau reseau) {
        this.reseau = reseau;
    }

    public Chemin getCheminMetal1() {
        return cheminMetal1;
    }

    public void setCheminMetal1(Chemin cheminMetal1) {
        this.cheminMetal1 = cheminMetal1;
    }

    public Chemin getCheminAile() {
        return cheminAile;
    }

    public void setCheminAile(Chemin cheminAile) {
        this.cheminAile = cheminAile;
    }

    public Chemin getCheminAvion() {
        return cheminAvion;
    }

    public void setCheminAvion(Chemin cheminAvion) {
        this.cheminAvion = cheminAvion;
    }

    public Chemin getCheminMoteur() {
        return cheminMoteur;
    }

    public void setCheminMoteur(Chemin cheminMoteur) {
        this.cheminMoteur = cheminMoteur;
    }

    public Chemin getCheminMetal2() {
        return cheminMetal2;
    }

    public void setCheminMetal2(Chemin cheminMetal2) {
        this.cheminMetal2 = cheminMetal2;
    }

    public Chemin getCheminMetal3() {
        return cheminMetal3;
    }

    public void setCheminMetal3(Chemin cheminMetal3) {
        this.cheminMetal3 = cheminMetal3;
    }
}

package Platform;

import Materials.*;
import ressources.XMLUtils;

import javax.swing.*;
import java.awt.*;

public class Reseau extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        XMLUtils xmlInstance = XMLUtils.getInstance();
        for(Noeud noeud: xmlInstance.noeuds){
            if(noeud.getUsine() instanceof UsineMatiere)
                xmlInstance.usineMatiere.drawImage(g, noeud.getPosition().x, noeud.getPosition().y);
            if(noeud.getUsine() instanceof UsineAile)
                xmlInstance.usineAile.drawImage(g, noeud.getPosition().x, noeud.getPosition().y);
            if(noeud.getUsine() instanceof UsineAssemblage)
                xmlInstance.usineAssemblage.drawImage(g, noeud.getPosition().x, noeud.getPosition().y);
            if(noeud.getUsine() instanceof UsineMoteur)
                xmlInstance.usineMoteur.drawImage(g, noeud.getPosition().x, noeud.getPosition().y);
            if(noeud.getUsine() instanceof Entrepot)
                xmlInstance.entrepot.drawImage(g, noeud.getPosition().x, noeud.getPosition().y);
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        for (Chemin chemin : xmlInstance.chemins) {
            int sourceX = 0;
            int sourceY = 0;
            int destinationX = 0;
            int destinationY = 0;
            if (chemin.getSource().getId() == 11 && chemin.getDestination().getId() == 21) {
                sourceX = (int)(chemin.getSource().getPosition().getX() + chemin.getSource().getUsine().getImage().getWidth());
                sourceY = (int)(chemin.getSource().getPosition().getY() + chemin.getSource().getUsine().getImage().getHeight() / 2);
                destinationX = (int) (chemin.getDestination().getPosition().getX());
                destinationY = (int) (chemin.getDestination().getPosition().getY() + chemin.getDestination().getUsine().getImage().getHeight() / 2);
            }
            if (chemin.getSource().getId() == 21 && chemin.getDestination().getId() == 41) {
                sourceX = (int)(chemin.getSource().getPosition().getX() + 3);
                sourceY = (int)(chemin.getSource().getPosition().getY() + chemin.getSource().getUsine().getImage().getHeight() - 3);
                destinationX = (int) (chemin.getDestination().getPosition().getX() + chemin.getDestination().getUsine().getImage().getWidth() - 3);
                destinationY = (int) (chemin.getDestination().getPosition().getY() + 3);
            }
            if (chemin.getSource().getId() == 41 && chemin.getDestination().getId() == 51) {
                sourceX = (int)(chemin.getSource().getPosition().getX() + chemin.getSource().getUsine().getImage().getWidth());
                sourceY = (int)(chemin.getSource().getPosition().getY() + chemin.getSource().getUsine().getImage().getHeight() / 2);
                destinationX = (int) (chemin.getDestination().getPosition().getX());
                destinationY = (int) (chemin.getDestination().getPosition().getY() + chemin.getSource().getUsine().getImage().getHeight() / 2);
            }
            if (chemin.getSource().getId() == 12 && chemin.getDestination().getId() == 31) {
                sourceX = (int)(chemin.getSource().getPosition().getX() + chemin.getSource().getUsine().getImage().getWidth());
                sourceY = (int)(chemin.getSource().getPosition().getY() + chemin.getSource().getUsine().getImage().getHeight() / 2);
                destinationX = (int) (chemin.getDestination().getPosition().getX());
                destinationY = (int) (chemin.getDestination().getPosition().getY() + chemin.getDestination().getUsine().getImage().getHeight() / 2);
            }
            if (chemin.getSource().getId() == 13 && chemin.getDestination().getId() == 31) {
                sourceX = (int)(chemin.getSource().getPosition().getX());
                sourceY = (int)(chemin.getSource().getPosition().getY());
                destinationX = (int) (chemin.getDestination().getPosition().getX() + chemin.getDestination().getUsine().getImage().getWidth() - 3);
                destinationY = (int) (chemin.getDestination().getPosition().getY() + chemin.getDestination().getUsine().getImage().getHeight() - 3);
            }
            if (chemin.getSource().getId() == 31 && chemin.getDestination().getId() == 41) {
                sourceX = (int)(chemin.getSource().getPosition().getX() + 3);
                sourceY = (int)(chemin.getSource().getPosition().getY() + 3);
                destinationX = (int) (chemin.getDestination().getPosition().getX() + chemin.getDestination().getUsine().getImage().getWidth() - 3);
                destinationY = (int) (chemin.getDestination().getPosition().getY() + chemin.getDestination().getUsine().getImage().getHeight() - 3);
            }
            chemin.dessinerChemin(g, sourceX, sourceY, destinationX, destinationY);
        }
    }
}

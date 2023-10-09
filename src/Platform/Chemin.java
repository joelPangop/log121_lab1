package Platform;

import javax.swing.*;
import java.awt.*;

public class Chemin extends JPanel {

    private Noeud source;

    private Noeud destination;

    public Chemin(Noeud source, Noeud destination) {
        this.source = source;
        this.destination = destination;
    }

    public void dessinerChemin(Graphics g, int sourceX, int sourceY, int destinationX, int destinationY) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g.drawLine(sourceX, sourceY, destinationX, destinationY);
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
}

package simulation;

import com.sun.org.apache.xerces.internal.dom.DeferredTextImpl;
import models.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class PanneauPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;

    // Variables temporaires de la demonstration:
    private Point position = new Point(0, 0);
    private Point vitesse = new Point(1, 1);
    private int taille = 32;

    public PanneauPrincipal() {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // On ajoute à la position le delta x et y de la vitesse
        position.translate(vitesse.x, vitesse.y);
        g.fillRect(position.x, position.y, taille, taille);
    }

}

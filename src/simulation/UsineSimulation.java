package simulation;

import com.sun.org.apache.xerces.internal.dom.DeferredTextImpl;
import models.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsineSimulation extends JPanel {

    List<Usine> usineList;

    private Map<Integer, Point> positions;
    private List<Chemin> chemins;

    public UsineSimulation() {
        usineList = new ArrayList<>();
        positions = new HashMap<>();
        chemins = new ArrayList<>();
        loadXMLData();
    }

    public void loadXMLData() {

        try {
            // Créez un parseur DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Chargez le fichier XML
            File xmlFile = new File("src/ressources/configuration.xml"); // Remplacez par le chemin de votre fichier XML
            Document document = builder.parse(xmlFile);

            // Obtenez l'élément racine du document
            Element root = document.getDocumentElement();

            // Accédez aux éléments de "metadonnees"
            NodeList metadonnees = root.getElementsByTagName("metadonnees");

            NodeList simulations = root.getElementsByTagName("simulation");
            for (int i = 0; i < metadonnees.getLength(); i++) {
                Element simulation = (Element) simulations.item(i);

                // Accédez aux éléments "usine" sous "metadonnees"
                NodeList usines = simulation.getElementsByTagName("usine");

                for (int j = 0; j < usines.getLength(); j++) {

                    Usine usine = new Usine();
                    Element usineElement = (Element) usines.item(j);
                    String type = usineElement.getAttribute("type");
                    System.out.println("Type d'usine : " + type);
                    usine.setType(type);
                    String id = usineElement.getAttribute("id");
                    System.out.println("Id : " + id);
                    usine.setId(Integer.parseInt(id));

                    String x = usineElement.getAttribute("x");
                    System.out.println("X : " + x);

                    String y = usineElement.getAttribute("y");
                    System.out.println("Y : " + y);

                    usine.setX(Integer.parseInt(x));
                    usine.setY(Integer.parseInt(y));

                    positions.put(Integer.parseInt(id), new Point(Integer.parseInt(x), Integer.parseInt(y)));
                    usineList.add(usine);
                }

                // Charger les informations sur les chemins
                NodeList cheminNodes = document.getElementsByTagName("chemin");
                for (int k = 0; k < cheminNodes.getLength(); k++) {
                    Element cheminElement = (Element) cheminNodes.item(k);
                    int de = Integer.parseInt(cheminElement.getAttribute("de"));
                    int vers = Integer.parseInt(cheminElement.getAttribute("vers"));
                    Usine source = usineList.stream().filter(e -> e.getId() == de).collect(Collectors.toList()).get(0);
                    Usine destination = usineList.stream().filter(e -> e.getId() == vers).collect(Collectors.toList()).get(0);
                    Chemin chemin = new Chemin(source, destination);
                    chemins.add(chemin);
                }
            }

            for (int i = 0; i < metadonnees.getLength(); i++) {
                Element metadonnee = (Element) metadonnees.item(i);

                // Accédez aux éléments "usine" sous "metadonnees"
                NodeList usines = metadonnee.getElementsByTagName("usine");

                for (int j = 0; j < usines.getLength(); j++) {

                    for (Usine usine : usineList) {

                        Element usineElement = (Element) usines.item(j);

                        String type = usineElement.getAttribute("type");
                        System.out.println("Type d'usine : " + type);
                        if (usine.getType().equals(type)) {

                            usine.setType(type);

                            // Accédez aux éléments "icones" sous "usine"
                            Element iconesElements = (Element) usineElement.getElementsByTagName("icones").item(0);

                            // Accédez aux éléments "icone" sous "icones"
                            NodeList iconeList = iconesElements.getElementsByTagName("icone");
                            Icone[] icones = new Icone[4];

                            for (int k = 0; k < iconeList.getLength(); k++) {

                                Element icone = (Element) iconeList.item(k);
                                String iconeType = icone.getAttribute("type");
                                String path = icone.getAttribute("path");
                                System.out.println("Type d'icone : " + iconeType);
                                System.out.println("Chemin d'icone : " + path);
                                Icone icon = new Icone(iconeType, path);
                                icones[k] = icon;

                            }
                            usine.setImage(icones[0]);
                            usine.setIcones(icones);

                            Element sortieElements = (Element) usineElement.getElementsByTagName("sortie").item(0);
                            String typeSortie = null;
                            if (sortieElements != null) {
                                typeSortie = sortieElements.getAttribute("type");
                            }
                            Sortie sortie = new Sortie(typeSortie);
                            Element entreeElements = (Element) usineElement.getElementsByTagName("entree").item(0);
                            Entree entree = null;
                            if (entreeElements != null) {
                                String type1 = entreeElements.getAttribute("type");
                                int quantite = 0;
                                if (type.equals("entrepot")) {
                                    quantite = Integer.parseInt(entreeElements.getAttribute("capacite"));
                                } else {
                                    quantite = Integer.parseInt(entreeElements.getAttribute("quantite"));
                                }
                                entree = new Entree(type1, quantite);
                            }
                            usine.setEntree(entree);
                            usine.setSortie(sortie);

                            Element int_prod_Elements = (Element) usineElement.getElementsByTagName("interval-production").item(0);
                            if (int_prod_Elements != null) {
                                int interval_production = Integer.parseInt(((DeferredTextImpl) int_prod_Elements.getFirstChild()).getData());
                                usine.setInterval_production(interval_production);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessinez les usines à leurs positions
        for (Usine usine : usineList) {
            int id = usine.getId();
            Point position = positions.get(id);
            usine.drawImage(g, usine.getIcones()[0], position.x, position.y);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            for (Chemin entry : chemins) {
                if (entry.getSource().getId() == 11 && entry.getDestination().getId() == 21) {
                    g2d.drawLine(entry.getSource().getX() + entry.getSource().getImage().getWidth(), entry.getSource().getY() + entry.getSource().getImage().getHeight() / 2, entry.getDestination().getX(), entry.getDestination().getY() + entry.getDestination().getImage().getHeight() / 2);
                }
                if (entry.getSource().getId() == 21 && entry.getDestination().getId() == 41) {
                    g2d.drawLine(entry.getSource().getX() + 3, entry.getSource().getY() + entry.getSource().getImage().getHeight() - 3, entry.getDestination().getX() + entry.getDestination().getImage().getWidth() - 3, entry.getDestination().getY() + 3);
                }
                if (entry.getSource().getId() == 41 && entry.getDestination().getId() == 51) {
                    g2d.drawLine(entry.getSource().getX() + entry.getSource().getImage().getWidth(), entry.getSource().getY() + entry.getSource().getImage().getHeight() / 2, entry.getDestination().getX(), entry.getDestination().getY() + entry.getSource().getImage().getHeight() / 2);
                }
                if (entry.getSource().getId() == 12 && entry.getDestination().getId() == 31) {
                    g2d.drawLine(entry.getSource().getX() + entry.getSource().getImage().getWidth(), entry.getSource().getY() + entry.getSource().getImage().getHeight() / 2, entry.getDestination().getX(), entry.getDestination().getY() + entry.getDestination().getImage().getHeight() / 2);
                }
                if (entry.getSource().getId() == 13 && entry.getDestination().getId() == 31) {
                    g2d.drawLine(entry.getSource().getX(), entry.getSource().getY(), entry.getDestination().getX() + entry.getDestination().getImage().getWidth() - 3, entry.getDestination().getY() + entry.getDestination().getImage().getHeight() - 3);
                }
                if (entry.getSource().getId() == 31 && entry.getDestination().getId() == 41) {
                    g2d.drawLine(entry.getSource().getX() + 3, entry.getSource().getY() + 3, entry.getDestination().getX() + entry.getDestination().getImage().getWidth() - 3, entry.getDestination().getY() + entry.getDestination().getImage().getHeight() - 3);
                }
            }
        }
    }
}

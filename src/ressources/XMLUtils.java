package ressources;

import Materials.*;
import Platform.Chemin;
import Platform.Noeud;
import com.sun.org.apache.xerces.internal.dom.DeferredTextImpl;
import Materials.ComposantEnum;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLUtils {

    public UsineAile usineAile;
    public UsineMoteur usineMoteur;
    public UsineAssemblage usineAssemblage;

    public Entrepot entrepot;
    public UsineMatiere usineMatiere;
    public Map<String, ComposanteUsine> composanteUsines;

    public ComposanteEntrepot composanteEntrepot;

    public List<Noeud> noeuds;

    public List<Chemin> chemins;

    private static XMLUtils instance;

    private XMLUtils() {
//        loadXMLData();
    }

    public void loadXMLData(String xmlFilePath) {
        noeuds = new ArrayList<>();
        chemins = new ArrayList<>();
        composanteUsines = new HashMap<>();
        try {
            // Créez un parseur DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Chargez le fichier XML
//            File xmlFile = new File(xmlFilePath); // Remplacez par le chemin de votre fichier XML
            File xmlFile = new File("src/ressources/configuration.xml"); // Remplacez par le chemin de votre fichier XML
            Document document = builder.parse(xmlFile);

            // Obtenez l'élément racine du document
            Element root = document.getDocumentElement();

            // Accédez aux éléments de "metadonnees"
            NodeList metadonnees = root.getElementsByTagName("metadonnees");

            for (int i = 0; i < metadonnees.getLength(); i++) {
                Element metadonnee = (Element) metadonnees.item(i);

                // Accédez aux éléments "usine" sous "metadonnees"
                NodeList usines = metadonnee.getElementsByTagName("usine");

                for (int j = 0; j < usines.getLength(); j++) {

                    Element usineElement = (Element) usines.item(j);

                    String type = usineElement.getAttribute("type");
                    System.out.println("Type d'usine : " + type);

                    // Accédez aux éléments "icones" sous "usine"
                    Element iconesElements = (Element) usineElement.getElementsByTagName("icones").item(0);

                    // Accédez aux éléments "icone" sous "icones"
                    NodeList iconeList = iconesElements.getElementsByTagName("icone");
                    Map<String, String> icones = new HashMap<>();

                    for (int k = 0; k < iconeList.getLength(); k++) {
                        Element icone = (Element) iconeList.item(k);
                        String iconeType = icone.getAttribute("type");
                        String path = icone.getAttribute("path");
                        System.out.println("Type d'icone : " + iconeType);
                        System.out.println("Chemin d'icone : " + path);
                        icones.put(iconeType, path);
                    }

                    Element int_prod_Elements = (Element) usineElement.getElementsByTagName("interval-production").item(0);
                    int interval_production = 0;
                    if (int_prod_Elements != null) {
                        interval_production = Integer.parseInt(((DeferredTextImpl) int_prod_Elements.getFirstChild()).getData());
                    }
                    Element sortieElements = (Element) usineElement.getElementsByTagName("sortie").item(0);
                    String typeSortie = "";
                    if (sortieElements != null) {
                        typeSortie = sortieElements.getAttribute("type");
                    }

                    Element entreeElements = (Element) usineElement.getElementsByTagName("entree").item(0);
                    String typeEntree = "";
                    if (entreeElements != null) {
                        typeEntree = entreeElements.getAttribute("type");
                    }

                    int quantite = 0;

                    switch (type) {
                        case "usine-matiere":
                            usineMatiere = new UsineMatiere(type, interval_production, new ComposanteUsine(quantite, ComposantEnum.METAL.getPath(), typeSortie), icones);
                            composanteUsines.put(typeSortie,  new ComposanteUsine(quantite, ComposantEnum.METAL.getPath(), typeSortie));
                            break;
                        case "usine-aile":
                            quantite = Integer.parseInt(entreeElements.getAttribute("quantite"));
                            ComposanteUsine aileEntree = new ComposanteUsine(quantite, ComposantEnum.METAL.getPath(), typeEntree);
                            ComposanteUsine aileSortie = new ComposanteUsine(quantite, ComposantEnum.AILE.getPath(), typeSortie);
                            usineAile = new UsineAile(type, interval_production, aileEntree, aileSortie, icones);
                            composanteUsines.put(typeSortie, aileSortie);
                            composanteUsines.put(typeEntree, aileEntree);

                            break;
                        case "usine-moteur":
                            quantite = Integer.parseInt(entreeElements.getAttribute("quantite"));
                            ComposanteUsine moteurEntree = new ComposanteUsine(quantite, ComposantEnum.METAL.getPath(), typeEntree);
                            ComposanteUsine moteurSortie = new ComposanteUsine(quantite, ComposantEnum.MOTEUR.getPath(), typeSortie);
                            usineMoteur = new UsineMoteur(type, interval_production, moteurEntree, moteurSortie, icones);
                            composanteUsines.put(typeSortie, moteurSortie);
                            composanteUsines.put(typeEntree, moteurEntree);
                            break;
                        case "usine-assemblage":
                            quantite = Integer.parseInt(entreeElements.getAttribute("quantite"));
                            Element entreeElement = (Element) usineElement.getElementsByTagName("entree").item(1);
                            String typeEntree1 = "";
                            if (entreeElement != null) {
                                typeEntree1 = sortieElements.getAttribute("type");
                            }
                            int quantite1 = Integer.parseInt(entreeElement.getAttribute("quantite"));

                            ComposanteUsine assemblageEntree = new ComposanteUsine(quantite, ComposantEnum.AILE.getPath(), typeEntree);
                            ComposanteUsine assemblageEntree1 = new ComposanteUsine(quantite1, ComposantEnum.MOTEUR.getPath(), typeEntree1);
                            ComposanteUsine assemblageSortie = new ComposanteUsine(quantite, ComposantEnum.AVION.getPath(), typeSortie);
                            usineAssemblage = new UsineAssemblage(type, interval_production, assemblageEntree, assemblageEntree1, assemblageSortie, icones);
                            composanteUsines.put(typeSortie, assemblageSortie);
                            composanteUsines.put(typeEntree1, assemblageEntree1);
                            composanteUsines.put(typeEntree, assemblageEntree);
                            break;

                        case "entrepot":
                            quantite = Integer.parseInt(entreeElements.getAttribute("capacite"));
                            composanteEntrepot = new ComposanteEntrepot(quantite, ComposantEnum.AVION.getPath(), typeEntree);
                            entrepot = new Entrepot(type, composanteEntrepot, icones);
                            break;
                    }
                }
            }

            NodeList simulations = root.getElementsByTagName("simulation");
            for (int i = 0; i < metadonnees.getLength(); i++) {
                Element simulation = (Element) simulations.item(i);

                // Accédez aux éléments "usine" sous "simulation"
                NodeList usines = simulation.getElementsByTagName("usine");

                for (int j = 0; j < usines.getLength(); j++) {

                    Noeud noeud;
                    Element usineElement = (Element) usines.item(j);
                    String type = usineElement.getAttribute("type");
                    System.out.println("Type d'usine : " + type);

                    String id = usineElement.getAttribute("id");
                    System.out.println("Id : " + id);

                    String x = usineElement.getAttribute("x");
                    System.out.println("X : " + x);

                    String y = usineElement.getAttribute("y");
                    System.out.println("Y : " + y);

                    Point position = new Point(Integer.parseInt(x), Integer.parseInt(y));

                    switch (type) {
                        case "usine-matiere":
                            noeud = new Noeud(Integer.parseInt(id), type, usineMatiere, position);
                            break;
                        case "usine-aile":
                            noeud = new Noeud(Integer.parseInt(id), type, usineAile, position);
                            break;
                        case "usine-moteur":
                            noeud = new Noeud(Integer.parseInt(id), type, usineMoteur, position);
                            break;
                        case "usine-assemblage":
                            noeud = new Noeud(Integer.parseInt(id), type, usineAssemblage, position);
                            break;
                        case "entrepot":
                            noeud = new Noeud(Integer.parseInt(id), type, entrepot, position);
                            break;
                        default:
                            noeud = new Noeud(Integer.parseInt(id), type, usineMatiere, position);
                            break;
                    }
                    noeuds.add(noeud);
                }

                // Charger les informations sur les chemins
                NodeList cheminNodes = document.getElementsByTagName("chemin");
                for (int k = 0; k < cheminNodes.getLength(); k++) {
                    Element cheminElement = (Element) cheminNodes.item(k);
                    int de = Integer.parseInt(cheminElement.getAttribute("de"));
                    int vers = Integer.parseInt(cheminElement.getAttribute("vers"));
                    Noeud source = noeuds.stream()
                            .filter(x -> x.getId() == de)  // Filtrez les éléments supérieurs à 5
                            .findAny().get();

                    Noeud destination = noeuds.stream()
                            .filter(x -> x.getId() == vers)  // Filtrez les éléments supérieurs à 5
                            .findAny().get();

                    Point vitesse = null;
                    if(de == 11 && vers == 21)
                        vitesse = new Point(5,0);
                    if(de == 21 && vers == 41)
                        vitesse = new Point(-5,+5);
                    if(de == 41 && vers == 51)
                        vitesse = new Point(5,0);
                    if(de == 12 && vers == 31)
                        vitesse = new Point(5,0);
                    if(de == 13 && vers == 31)
                        vitesse = new Point(-5,-5);
                    if(de == 31 && vers == 41)
                        vitesse = new Point(-5,-5);

                    Chemin chemin = new Chemin(source, destination, vitesse);
                    chemins.add(chemin);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static XMLUtils getInstance() {
        // Si l'instance n'a pas encore été créée, créez-la.
        if (instance == null) {
            instance = new XMLUtils();
        }
        // Renvoie l'instance unique.
        return instance;
    }

}

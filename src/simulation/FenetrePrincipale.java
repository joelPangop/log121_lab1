package simulation;

import Platform.Chemin;
import Platform.Reseau;
import ressources.XMLUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
    private static final Dimension DIMENSION = new Dimension(700, 700);
    PanneauPrincipal panneauPrincipal;
    MenuFenetre menuFenetre;
    private java.util.List<BufferedImage> images;
    private java.util.List<Point> imagePositions;
    private int currentImageIndex = 0;
    private int speed = 5;
    private Timer timer;


    public FenetrePrincipale() {
        panneauPrincipal = new PanneauPrincipal();

        menuFenetre = new MenuFenetre();

        add(panneauPrincipal);
        add(menuFenetre, BorderLayout.NORTH);

        menuFenetre.getMenuCharger().addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Sélectionnez un fichier de configuration");
            fileChooser.setAcceptAllFileFilterUsed(false);
            // Créer un filtre
            FileNameExtensionFilter filtre = new FileNameExtensionFilter(".xml", "xml");
            fileChooser.addChoosableFileFilter(filtre);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // TODO - Parser le fichier XML sélectionné
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
//				PanneauPrincipal panneauPrincipal= new PanneauPrincipal();
                XMLUtils.getInstance().loadXMLData(selectedFile.getAbsolutePath());
                Reseau reseau = new Reseau();
                panneauPrincipal.setReseau(reseau);
                reseau.repaint();
                panneauPrincipal.repaint();

            }
        });

        // Faire en sorte que le X de la fenêtre ferme la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITRE_FENETRE);
        setSize(DIMENSION);

        // Rendre la fenêtre visible
        setVisible(true);
        // Mettre la fenêtre au centre de l'écran
        setLocationRelativeTo(null);
        // Empêcher la redimension de la fenêtre
        setResizable(false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("TEST")) {
            repaint();
            System.out.println(evt.getNewValue());

        }
        if (evt.getPropertyName().equals("chemin1")) {
            panneauPrincipal.setCheminMetal1((Chemin) evt.getNewValue());
            repaint();
//            System.out.println(evt.getNewValue());

        }
        if (evt.getPropertyName().equals("chemin")) {
            panneauPrincipal.setCheminMetal1((Chemin) evt.getNewValue());
            repaint();
//            System.out.println(evt.getNewValue());

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
//        panneauPrincipal.startAnimation();
    }
}

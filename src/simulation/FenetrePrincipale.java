package simulation;

import Materials.AbstractComposante;
import Materials.ComposantEnum;
import Materials.ComposanteUsine;
import Platform.Chemin;
import Platform.Reseau;
import ressources.XMLUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
    private static final Dimension DIMENSION = new Dimension(700, 700);
    PanneauPrincipal panneauPrincipal;
    MenuFenetre menuFenetre;

    private Timer timer;

    private int repeatCount = 3;  // Nombre de répétitions
    private int currentRepeat = 0;  // Répétition actuelle

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
            System.out.println(ComposantEnum.AILE.getPath());
            System.out.println(evt.getNewValue());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
//        panneauPrincipal.startAnimation();
    }
}

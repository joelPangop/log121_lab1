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

    private int repeatCount = 3;  // Nombre de r�p�titions
    private int currentRepeat = 0;  // R�p�tition actuelle

    public FenetrePrincipale() {
        panneauPrincipal = new PanneauPrincipal();

        menuFenetre = new MenuFenetre();

        add(panneauPrincipal);
        add(menuFenetre, BorderLayout.NORTH);

        menuFenetre.getMenuCharger().addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("S�lectionnez un fichier de configuration");
            fileChooser.setAcceptAllFileFilterUsed(false);
            // Cr�er un filtre
            FileNameExtensionFilter filtre = new FileNameExtensionFilter(".xml", "xml");
            fileChooser.addChoosableFileFilter(filtre);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // TODO - Parser le fichier XML s�lectionn�
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

        // Faire en sorte que le X de la fen�tre ferme la fen�tre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(TITRE_FENETRE);
        setSize(DIMENSION);

        // Rendre la fen�tre visible
        setVisible(true);
        // Mettre la fen�tre au centre de l'�cran
        setLocationRelativeTo(null);
        // Emp�cher la redimension de la fen�tre
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

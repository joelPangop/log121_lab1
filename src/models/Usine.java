package models;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Cette classe représente une usine.
 * Une usine a un identifiant unique, une sortie, une entrée, un intervalle de production
 * et une liste d'icônes associées.
 */
public class Usine extends JPanel {

    private int id;

    private String type;
    private Sortie sortie;
    private Entree entree;
    private int interval_production;

    BufferedImage image;

    private Icone[] icones;

    private int x;

    private int y;

    /**
     * Constructeur par défaut de la classe Usine.
//     */
    public Usine() {
        super();
    }

    public void drawImage(Graphics g, Icone icone, int x, int y) {
        // Dessinez l'image de l'usine-matière à la position (x, y)
        // Remplacez par le bon chemin d'image
        BufferedImage image = loadImage(icone.getPath());
        g.drawImage(image, x, y, null);
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Obtient l'identifiant de l'usine.
     *
     * @return L'identifiant de l'usine.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'usine.
     *
     * @param id Le nouvel identifiant de l'usine.
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Obtient la sortie de l'usine.
     *
     * @return La sortie de l'usine.
     */
    public Sortie getSortie() {
        return sortie;
    }

    /**
     * Définit la sortie de l'usine.
     *
     * @param sortie La nouvelle sortie de l'usine.
     */
    public void setSortie(Sortie sortie) {
        this.sortie = sortie;
    }


    /**
     * Obtient l'entrée de l'usine.
     *
     * @return L'entree de l'usine.
     */
    public Entree getEntree() {
        return entree;
    }

    /**
     * Définit l'entrée de l'usine.
     *
     * @param entree La nouvelle entrée de l'usine.
     */
    public void setEntree(Entree entree) {
        this.entree = entree;
    }

    /**
     * Obtient l'intervalle de production de l'usine.
     *
     * @return L'intervalle de production de l'usine.
     */
    public int getInterval_production() {
        return interval_production;
    }

    /**
     * Définit l'intervalle de production de l'usine.
     *
     * @param interval_production La intervalle de production de l'usine.
     */
    public void setInterval_production(int interval_production) {
        this.interval_production = interval_production;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(Icone icone) {
        try {
            this.image = ImageIO.read(new File(icone.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Icone[] getIcones() {
        return icones;
    }

    public void setIcones(Icone[] icones) {
        this.icones = icones;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

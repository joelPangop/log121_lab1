package models;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Materiel extends JPanel {
    BufferedImage image;

    private int x;

    private int y;

    private Chemin chemin;

//    public Materiel(String path, int x, int y) {
//        try {
//            this.image = ImageIO.read(new File(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//            this.image = null;
//        }
//        this.x = x;
//        this.y = y;
//    }

    public Materiel(String path, Chemin chemin, int x, int y) {
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
        this.chemin = chemin;
        this.x = x;
        this.y = y;
    }

    public void drawImage(Graphics g) {
        super.paintComponent(g);
        // Dessinez l'image à la position actuelle (x, y)
        g.drawImage(image, x, y, this);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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

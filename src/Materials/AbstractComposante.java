package Materials;

import Platform.Chemin;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class AbstractComposante implements Iimage {

    protected String type;
    protected BufferedImage image;
    protected Point position;

    protected Timer timer;

    public AbstractComposante(String type) {
        this.type = type;
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    @Override
    public void setImage(BufferedImage image) {

    }

    @Override
    public void drawImage(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public void drawImage(Graphics g) {
        g.drawImage(image, (int)position.getX(), (int) position.getY(), null);
    }

    public void deplacer(Graphics g, Chemin chemin) {
        if (chemin.getSource().getId() == 11 && chemin.getDestination().getId() == 21) {
//            while ((int) this.position.getX() <= chemin.getDestination().getPosition().getX()) {
//                this.position.translate(1, 0);
//            }
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}

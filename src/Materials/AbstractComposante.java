package Materials;

import javax.swing.*;
import java.awt.*;
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
        return this.image;
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

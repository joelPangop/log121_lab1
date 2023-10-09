package Platform;

import Materials.AbstractUsine;

import javax.swing.*;
import java.awt.*;

public class Noeud extends JPanel {

    private int id;
    private String type;
    private AbstractUsine usine;
    private Point position;

    public Noeud(int id, String type, AbstractUsine usine, Point position) {
        this.id = id;
        this.type = type;
        this.usine = usine;
        this.position = position;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AbstractUsine getUsine() {
        return usine;
    }

    public void setUsine(AbstractUsine usine) {
        this.usine = usine;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

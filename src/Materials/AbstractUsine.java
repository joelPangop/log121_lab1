package Materials;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AbstractUsine implements Iimage {

    protected String type;

    protected BufferedImage image;

    protected Map<String, String> iconesMap;

    protected int intervaleProduction;

    public AbstractUsine() {
       this.iconesMap = new HashMap<>();
    }

    public void drawImage(Graphics g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getIconesMap() {
        return iconesMap;
    }

    public void setIcones(Map<String, String> iconesMap) {
        this.iconesMap = iconesMap;
    }

    public int getIntervaleProduction() {
        return intervaleProduction;
    }

    public void setIntervaleProduction(int intervaleProduction) {
        this.intervaleProduction = intervaleProduction;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}

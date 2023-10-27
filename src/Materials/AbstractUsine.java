package Materials;

import Platform.Chemin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractUsine implements Iimage {

    protected String type;

    protected BufferedImage image;

    protected Map<String, String> iconesMap;

    protected int intervaleProduction;

    protected boolean productionDone = false;

    protected List<IProductionObserver> observers;

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

    public void changeImage(int index) {
        try {
            if (index == 0)
                this.image = ImageIO.read(new File(this.iconesMap.get("vide")));
            if (index == 1)
                this.image = ImageIO.read(new File(this.iconesMap.get("un-tiers")));
            if (index == 2)
                this.image = ImageIO.read(new File(this.iconesMap.get("deux-tiers")));
            if (index == 3)
                this.image = ImageIO.read(new File(this.iconesMap.get("plein")));
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

    public boolean isProductionDone() {
        return productionDone;
    }

    public void setProductionDone(boolean productionDone) {
        this.productionDone = productionDone;
    }

    public List<IProductionObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<IProductionObserver> observers) {
        this.observers = observers;
    }

    public void attachObserver(IProductionObserver obs) {
        if (!this.observers.contains(obs))
            this.observers.add(obs);
    }

    public void detachObserver(IProductionObserver obs) {
        this.observers.remove(obs);
    }

    public void notifyObservers(BufferedImage image, Point point) {
        for (IProductionObserver productionObserver : observers) {
            ((Chemin) productionObserver).setProductedImage(image);
            ((Chemin) productionObserver).setProductedImagePosition(point);
            productionObserver.moveProductedImage();
        }
    }

    public void stopProcess() {
        for (IProductionObserver productionObserver : observers) {
            productionObserver.stopAnimation();
        }
    }
}

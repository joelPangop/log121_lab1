package Materials;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class UsineMoteur extends AbstractUsine {

    private ComposanteUsine entree;
    private ComposanteUsine sortie;

    private int quantiteEntree;
    private int quantiteSortie;

    public UsineMoteur(String type, int intervaleProduction, ComposanteUsine entree, ComposanteUsine sortie, Map<String, String> iconesMap) {
        this.type = type;
        this.intervaleProduction = intervaleProduction;
        this.entree = entree;
        this.sortie = sortie;
        this.iconesMap = iconesMap;
        quantiteEntree = 0;
        quantiteSortie = 0;
        observers = new ArrayList<>();
        try {
            this.image = ImageIO.read(new File(this.iconesMap.get("vide")));
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

    public ComposanteUsine getEntree() {
        return entree;
    }

    public void setEntree(ComposanteUsine entree) {
        this.entree = entree;
    }

    public ComposanteUsine getSortie() {
        return sortie;
    }

    public void setSortie(ComposanteUsine sortie) {
        this.sortie = sortie;
    }

    public int getQuantiteEntree() {
        return quantiteEntree;
    }

    public void setQuantiteEntree(int quantiteEntree) {
        this.quantiteEntree = quantiteEntree;
    }

    public int getQuantiteSortie() {
        return quantiteSortie;
    }

    public void setQuantiteSortie(int quantiteSortie) {
        this.quantiteSortie = quantiteSortie;
    }

    public int fabricationMoteur(){
        int nbMoteur = 0;
        if(quantiteEntree == 4) {
            nbMoteur = 1;
        }
        return nbMoteur;
    }
}

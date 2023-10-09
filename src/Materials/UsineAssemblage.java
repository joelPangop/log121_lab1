package Materials;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class UsineAssemblage extends AbstractUsine {

    private ComposanteUsine entreeAile;
    private ComposanteUsine entreeMoteur;
    private ComposanteUsine sortie;

    public UsineAssemblage(String type, int intervaleProduction, ComposanteUsine entreeAile,ComposanteUsine entreeMoteur, ComposanteUsine sortie, Map<String, String> iconesMap) {
        this.type = type;
        this.intervaleProduction = intervaleProduction;
        this.entreeAile = entreeAile;
        this.entreeMoteur = entreeMoteur;
        this.sortie = sortie;
        this.iconesMap = iconesMap;
        try {
            this.image = ImageIO.read(new File(this.iconesMap.get("vide")));
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

    public ComposanteUsine getEntreeAile() {
        return entreeAile;
    }

    public void setEntreeAile(ComposanteUsine entreeAile) {
        this.entreeAile = entreeAile;
    }

    public ComposanteUsine getEntreeMoteur() {
        return entreeMoteur;
    }

    public void setEntreeMoteur(ComposanteUsine entreeMoteur) {
        this.entreeMoteur = entreeMoteur;
    }

    public ComposanteUsine getSortie() {
        return sortie;
    }

    public void setSortie(ComposanteUsine sortie) {
        this.sortie = sortie;
    }
}

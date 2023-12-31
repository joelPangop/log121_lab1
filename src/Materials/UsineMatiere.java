package Materials;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class UsineMatiere extends AbstractUsine {

    private ComposanteUsine sortie;

    private int quantite;

    public UsineMatiere(String type, int intervaleProduction, ComposanteUsine sortie, Map<String, String> iconesMap) {
        this.type = type;
        this.intervaleProduction = intervaleProduction;
        this.sortie = sortie;
        this.iconesMap = iconesMap;
        quantite = 0;
        try {
            this.image = ImageIO.read(new File(this.iconesMap.get("vide")));
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

    public ComposanteUsine getSortie() {
        return sortie;
    }

    public void setSortie(ComposanteUsine sortie) {
        this.sortie = sortie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

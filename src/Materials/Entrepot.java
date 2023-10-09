package Materials;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Entrepot extends AbstractUsine {
    ComposanteEntrepot entree;

    public Entrepot(String type, ComposanteEntrepot entree, Map<String, String> iconesMap) {
        this.type = type;
        this.entree = entree;
        this.iconesMap = iconesMap;
        try {
            this.image = ImageIO.read(new File(this.iconesMap.get("vide")));
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

    public ComposanteEntrepot getEntree() {
        return entree;
    }

    public void setEntree(ComposanteEntrepot entree) {
        this.entree = entree;
    }
}

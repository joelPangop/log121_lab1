package Materials;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ComposanteEntrepot extends AbstractComposante {

    private int capacite;

    public ComposanteEntrepot(int capacite, String path, String type) {
        super(type);
        this.capacite = capacite;
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}

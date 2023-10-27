package Materials;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ComposanteUsine extends AbstractComposante {

    private int quantite;

    public ComposanteUsine(int quantite, String path, String type) {
        super(type);
        this.quantite = quantite;

        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}

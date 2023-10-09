package Materials;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Iimage {

    BufferedImage getImage();
    void setImage(BufferedImage image);
    void drawImage(Graphics g, int x, int y);
}

package Platform;

import Materials.*;
import ressources.XMLUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Chemin extends JPanel implements Runnable, IProductionObserver {

    private Noeud source;
    private Noeud destination;
    private Point vitesse;
    private java.util.List<BufferedImage> images;
    private java.util.List<Point> imagePositions;

    private java.util.List<BufferedImage> productedImages;
    BufferedImage productedImage;
    private java.util.List<Point> productedImagePositions;
    //    private java.util.List<Point> productedImagePositions;
    Point productedImagePosition;
    private int speed = 5; // Vitesse de déplacement
    private Timer timer;
    Timer moveTimer;

    private boolean running; // Indicateur pour contrôler l'exécution du thread
    private int repeatCount = 3;  // Nombre de répétitions
    private int currentRepeat = 0;  // Répétition actuelle

    public Chemin(Noeud source, Noeud destination, Point vitesse) {
        this.source = source;
        this.destination = destination;
        this.vitesse = vitesse;
        images = new ArrayList<>();
        imagePositions = new ArrayList<>();
        productedImages = new ArrayList<>();
        productedImagePositions = new ArrayList<>();
//        productedImagePosition = new Point(source.getPosition().x, source.getPosition().y);

        if (source.getId() == 11 || source.getId() == 12 || source.getId() == 13) {
            timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    move(); // Appel de la méthode pour mettre à jour la position
                    repaint(); // Redessine le composant
                }
            });
        }
    }

    public void dessinerChemin(Graphics g, int sourceX, int sourceY, int destinationX, int destinationY) {
        g.drawLine(sourceX, sourceY, destinationX, destinationY);
    }

    @Override
    public void startAnimation() {
        if (!running) {
            running = true;

            int intervale = destination.getUsine() instanceof Entrepot ? 30 : destination.getUsine().getIntervaleProduction();
            if (source.getUsine() instanceof UsineMatiere) {
                timer.start(); // Démarre le timer
                moveTimer = new Timer(intervale, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Iterator<Point> iterator = imagePositions.iterator();
                        Iterator<BufferedImage> imageIterator = images.iterator();

                        while (iterator.hasNext()) {
                            Point position = iterator.next();

                            if (source.getId() == 11 && destination.getId() == 21) {
                                if (position.x >= 32 && position.x <= 320) {
                                    position.translate(vitesse.x, vitesse.y);
                                }
                                // Supprimez l'image et la position si elles atteignent x >= 320
                                if (position.x > 320) {
                                    iterator.remove();
                                    imageIterator.next();
                                    imageIterator.remove();
                                    int quantite = ((UsineAile) destination.getUsine()).getQuantiteEntree();

                                    if (quantite == ((UsineAile) destination.getUsine()).getEntree().getQuantite()) {
                                        ((UsineAile) destination.getUsine()).setProductionDone(true);
                                        try {
                                            Thread.sleep(intervale);
                                            destination.getUsine().notifyObservers(((UsineAile) destination.getUsine()).getSortie().getImage(), new Point(320, 32));
                                        } catch (InterruptedException exception) {
                                            exception.printStackTrace();
                                        }
                                        quantite = 0;
                                    } else {
                                        quantite++;
                                    }
                                    ((UsineAile) destination.getUsine()).setQuantiteEntree(quantite);
                                }
                            }

                            if (source.getId() == 12 && destination.getId() == 31) {
                                if (position.x >= 96 && position.x <= 320) {
                                    position.translate(vitesse.x, vitesse.y);
                                }
                                // Supprimez l'image et la position si elles atteignent x = 320
                                if (position.x >= 320) {
                                    iterator.remove();
                                    imageIterator.next();
                                    imageIterator.remove();

                                    int quantite = ((UsineMoteur) destination.getUsine()).getQuantiteEntree();

                                    if (((UsineMoteur) destination.getUsine()).fabricationMoteur() == 1) {
                                        destination.getUsine().setProductionDone(true);
                                        try {
                                            Thread.sleep(intervale);
                                            destination.getUsine().notifyObservers(((UsineMoteur) destination.getUsine()).getSortie().getImage(), new Point(320, 352));
                                        } catch (InterruptedException exception) {
                                            exception.printStackTrace();
                                        }
                                        quantite = 0;
                                    } else {
                                        quantite++;
                                    }
                                    ((UsineMoteur) destination.getUsine()).setQuantiteEntree(quantite);
                                }
                            }
                            if (source.getId() == 13 && destination.getId() == 31) {
                                if (position.x >= 320 && position.x <= 544) {
                                    position.translate(vitesse.x, vitesse.y);
                                }
                                // Supprimez l'image et la position si elles atteignent x = 320
                                if (position.x < 320) {
                                    iterator.remove();
                                    imageIterator.next();
                                    imageIterator.remove();

                                    int quantite = ((UsineMoteur) destination.getUsine()).getQuantiteEntree();

                                    if (quantite == ((UsineMoteur) destination.getUsine()).getEntree().getQuantite()) {
                                        destination.getUsine().setProductionDone(true);
                                        try {
                                            Thread.sleep(intervale);
                                            destination.getUsine().notifyObservers(((UsineMoteur) destination.getUsine()).getSortie().getImage(), new Point(320, 352));
                                        } catch (InterruptedException exception) {
                                            exception.printStackTrace();
                                        }
                                        quantite = 0;
                                    } else {
                                        quantite++;
                                    }
                                    ((UsineMoteur) destination.getUsine()).setQuantiteEntree(quantite);
                                }
                            }

                        }
                        repaint();
                    }
                });
                moveTimer.start();

            }
            Thread thread = new Thread(this);
            thread.start(); // Démarre le thread

        }
    }

    // Méthode pour arrêter l'animation
    public void stopAnimation() {
        running = false;
        if (timer != null)
            timer.stop(); // Arrête le timer

        if (moveTimer != null)
            moveTimer.stop();

    }


    private void move() {
        BufferedImage newImage = XMLUtils.getInstance().composanteUsines.get("metal").getImage();

        if (source.getId() == 11 && destination.getId() == 21) {
            newImage = XMLUtils.getInstance().composanteUsines.get("metal").getImage();
            Point point = new Point(source.getPosition().x, source.getPosition().y);
            images.add(newImage);
            imagePositions.add(point);
        }
        if (source.getId() == 21 && destination.getId() == 41) {
            productedImage = XMLUtils.getInstance().composanteUsines.get("aile").getImage();
            productedImagePosition = new Point(160, 192);
        }
        if (source.getId() == 41 && destination.getId() == 51) {
            productedImage = XMLUtils.getInstance().composanteUsines.get("avion").getImage();
            productedImagePosition = new Point(160, 192);
        }
        if (source.getId() == 31 && destination.getId() == 41) {
            productedImage = XMLUtils.getInstance().composanteUsines.get("moteur").getImage();
            productedImagePosition = new Point(320, 352);
        }
        if (source.getId() == 12 && destination.getId() == 31) {
            newImage = XMLUtils.getInstance().composanteUsines.get("metal").getImage();
            Point point = new Point(source.getPosition().x, source.getPosition().y);
            images.add(newImage);
            imagePositions.add(point);
        }
        if (source.getId() == 13 && destination.getId() == 31) {
            newImage = XMLUtils.getInstance().composanteUsines.get("metal").getImage();
            Point point = new Point(source.getPosition().x, source.getPosition().y);
            images.add(newImage);
            imagePositions.add(point);
        }
    }

    @Override
    public void moveProductedImage() {
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (source.getId() == 21 && destination.getId() == 41) {
//                productedImage = source.getUsine().getSortie().
                    if (productedImagePosition.x >= 160 && productedImagePosition.x <= 320) {
                        productedImagePosition.translate(vitesse.x, vitesse.y);
                    }

                    if (productedImagePosition.x < 160) {
                        productedImagePosition = new Point(320, 32);
                        timer.stop();
                        int quantite = ((UsineAssemblage) destination.getUsine()).getQuantiteEntreeAile();
                        if (((UsineAssemblage) destination.getUsine()).fabricationAvion() == 1) {
                            destination.getUsine().setProductionDone(true);
                            try {
                                Thread.sleep(destination.getUsine().getIntervaleProduction());
                                destination.getUsine().notifyObservers(((UsineAssemblage) destination.getUsine()).getSortie().getImage(), new Point(160, 192));
                                ((UsineAssemblage) destination.getUsine()).setQuantiteEntreeAile(quantite);
                            } catch (InterruptedException exception) {
                                exception.printStackTrace();
                            }
                            quantite = 0;
                        } else {
                            if (quantite < 2)
                                quantite++;
                        }
                        ((UsineAssemblage) destination.getUsine()).setQuantiteEntreeAile(quantite);
                    }
                }
                if (source.getId() == 31 && destination.getId() == 41) {
                    if (productedImagePosition.x >= 160 && productedImagePosition.x <= 320) {
                        productedImagePosition.translate(vitesse.x, vitesse.y);
                    }
                    if (productedImagePosition.x < 160) {
                        productedImagePosition = new Point(320, 352);
                        timer.stop();
                        int quantite = ((UsineAssemblage) destination.getUsine()).getQuantiteEntreeMoteur();

                        if (((UsineAssemblage) destination.getUsine()).fabricationAvion() == 1) {
                            destination.getUsine().setProductionDone(true);
                            try {
                                Thread.sleep(destination.getUsine().getIntervaleProduction());
                                destination.getUsine().notifyObservers(((UsineAssemblage) destination.getUsine()).getSortie().getImage(), new Point(160, 192));
                                ((UsineAssemblage) destination.getUsine()).setQuantiteEntreeMoteur(0);
                            } catch (InterruptedException exception) {
                                exception.printStackTrace();
                            }
                            quantite = 0;
                        } else {
                            if (quantite < 4)
                                quantite++;
                        }
                        ((UsineAssemblage) destination.getUsine()).setQuantiteEntreeMoteur(quantite);
                    }
                }
                if (source.getId() == 41 && destination.getId() == 51) {
                    if (productedImagePosition.x >= 160 && productedImagePosition.x <= 640) {
                        productedImagePosition.translate(vitesse.x, vitesse.y);
                    }

                    if (productedImagePosition.x > 640) {
                        productedImagePosition = new Point(160, 192);
                        timer.stop();
                        int quantite = ((Entrepot) destination.getUsine()).getQuantite();
                        if (quantite == ((Entrepot) destination.getUsine()).getEntree().getCapacite()) {
                            destination.getUsine().stopProcess();
                            ((Entrepot) destination.getUsine()).setQuantite(0);
                        } else {
                            quantite++;
                        }
                        ((Entrepot) destination.getUsine()).setQuantite(quantite);
                    }
                }
                repaint(); // Redessine le composant
            }
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!images.isEmpty()) {
            // Dessinez l'image actuelle à la position (x, y)
            for (int i = 0; i < images.size(); i++) {
                BufferedImage image = images.get(i);
                Point position = imagePositions.get(i);
                g.drawImage(image, position.x, position.y, this);
            }
        }

        if (productedImage != null) {
            g.drawImage(productedImage, productedImagePosition.x, productedImagePosition.y, this);
        }

    }

    public Noeud getSource() {
        return source;
    }

    public void setSource(Noeud source) {
        this.source = source;
    }

    public Noeud getDestination() {
        return destination;
    }

    public void setDestination(Noeud destination) {
        this.destination = destination;
    }

    public Point getVitesse() {
        return vitesse;
    }

    public void setVitesse(Point vitesse) {
        this.vitesse = vitesse;
    }


    public java.util.List<BufferedImage> getImages() {
        return images;
    }

    public void setImages(java.util.List<BufferedImage> images) {
        this.images = images;
    }

    public java.util.List<Point> getImagePositions() {
        return imagePositions;
    }

    public void setImagePositions(java.util.List<Point> imagePositions) {
        this.imagePositions = imagePositions;
    }

    public java.util.List<BufferedImage> getProductedImages() {
        return productedImages;
    }

    public void setProductedImages(java.util.List<BufferedImage> productedImages) {
        this.productedImages = productedImages;
    }

    public java.util.List<Point> getProductedImagePositions() {
        return productedImagePositions;
    }

    public void setProductedImagePositions(java.util.List<Point> productedImagePositions) {
        this.productedImagePositions = productedImagePositions;
    }

    public BufferedImage getProductedImage() {
        return productedImage;
    }

    public void setProductedImage(BufferedImage productedImage) {
        this.productedImage = productedImage;
    }

    public Point getProductedImagePosition() {
        return productedImagePosition;
    }

    public void setProductedImagePosition(Point productedImagePosition) {
        this.productedImagePosition = productedImagePosition;
    }

    public Chemin getChemin(int sourceID, int destinationID) {
        if (source.getId() == sourceID && destination.getId() == destinationID)
            return this;
        else
            return null;
    }

    @Override
    public void run() {
        while (running) {
            // Mettez à jour l'animation en continu si nécessaire
            // Par exemple, appelez move() ici pour déplacer l'animation

//            System.out.println("roulement: " + currentRepeat);
        }
    }

    @Override
    public Chemin clone() throws CloneNotSupportedException {
        return new Chemin(source, destination, vitesse);
    }
}

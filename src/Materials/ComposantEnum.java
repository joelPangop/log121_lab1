package Materials;

public enum ComposantEnum {

    METAL("src/ressources/metal.png"),
    AILE("src/ressources/aile.png"),
    AVION("src/ressources/avion.png"),
    MOTEUR("src/ressources/moteur.png");

    private final String path;

    ComposantEnum(String s) {
        this.path = s;
    }

    public String getPath() {
        return path;
    }

}

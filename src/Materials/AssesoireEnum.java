package Materials;

public enum AssesoireEnum {

    METAL("src/ressources/metal.png"),
    AILE("src/ressources/aile.png"),
    AVION("src/ressources/avion.png"),
    MOTEUR("src/ressources/moteur");

    private final String path;

    AssesoireEnum(String s) {
        this.path = s;
    }

    public String getPath() {
        return path;
    }

}

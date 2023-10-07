package models;

public enum TypeUsineEnum {
    USINE_MATERIEL("usine-matiere"),
    USINE_AILE("usine-aile"),
    USINE_ASSEMBLAGE("usine-assemblage"),
    ENTREPOT("entrepot"),
    USINE_MOTEUR("usine-moteur");

    private String name;
    TypeUsineEnum(String name) {
        this.name = name;
    }
}

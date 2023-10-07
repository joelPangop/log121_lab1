package models;

public class Chemin {
    private Usine source;

    private Usine destination;

    public Chemin(Usine source, Usine destination) {
        this.source = source;
        this.destination = destination;
    }

    public Usine getSource() {
        return source;
    }

    public void setSource(Usine source) {
        this.source = source;
    }

    public Usine getDestination() {
        return destination;
    }

    public void setDestination(Usine destination) {
        this.destination = destination;
    }
}

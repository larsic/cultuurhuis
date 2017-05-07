package JavaFiles;

public class Reservatie {

    private Voorstelling voorstelling;
    private int aantalTickets;

    public Reservatie(Voorstelling voorstelling, int aantalTickets) {
        this.voorstelling = voorstelling;
        this.aantalTickets = aantalTickets;
    }

    public Reservatie() {
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }

    public void setVoorstelling(Voorstelling voorstelling) {
        this.voorstelling = voorstelling;
    }

    public int getAantalTickets() {
        return aantalTickets;
    }

    public void setAantalTickets(int aantalTickets) {
        this.aantalTickets = aantalTickets;
    }

}

package JavaFiles;

import java.sql.Timestamp;

public class Voorstelling {

    Timestamp date;
    String titel;
    String uitvoerders;
    String prijs;
    int vrijePlaatsen;
    int id2;

    public Voorstelling() {
    }

    public Voorstelling(Timestamp date, String titel, String uitvoerders, String prijs, int vrijePlaatsen, int id2) {
        this.date = date;
        this.titel = titel;
        this.uitvoerders = uitvoerders;
        this.prijs = prijs;
        this.vrijePlaatsen = vrijePlaatsen;
        this.id2 = id2;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUitvoerders() {
        return uitvoerders;
    }

    public void setUitvoerders(String uitvoerders) {
        this.uitvoerders = uitvoerders;
    }

    public String getPrijs() {
        return prijs;
    }

    public void setPrijs(String prijs) {
        this.prijs = prijs;
    }

    public int getVrijePlaatsen() {
        return vrijePlaatsen;
    }

    public void setVrijePlaatsen(int vrijePlaatsen) {
        this.vrijePlaatsen = vrijePlaatsen;
    }
}

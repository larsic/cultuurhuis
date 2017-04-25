/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFiles;

import java.sql.Timestamp;



/**
 *
 * @author Administrator
 */
public class Voorstelling {
    
    Timestamp date;
    String titel;
    String uitvoerders;
    String prijs;
    int vrijePlaatsen;

    public Voorstelling() {
    }

    public Voorstelling(Timestamp date, String titel, String uitvoerders, String prijs, int vrijePlaatsen) {
        this.date = date;
        this.titel = titel;
        this.uitvoerders = uitvoerders;
        this.prijs = prijs;
        this.vrijePlaatsen = vrijePlaatsen;
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

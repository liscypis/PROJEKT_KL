package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Wojtek on 13.06.2017.
 */
public class Zamowienia {
    private StringProperty opis_zam;
    private StringProperty wplata;
    private StringProperty ubezpieczenie;

    public Zamowienia() {
        this.opis_zam = new SimpleStringProperty();
        this.ubezpieczenie = new SimpleStringProperty();
        this.wplata = new SimpleStringProperty();
    }

    public String getOpis_zam() {
        return opis_zam.get();
    }

    public StringProperty opis_zamProperty() {
        return opis_zam;
    }

    public void setOpis_zam(String opis_zam) {
        this.opis_zam.set(opis_zam);
    }

    public String getWplata() {
        return wplata.get();
    }

    public StringProperty wplataProperty() {
        return wplata;
    }

    public void setWplata(String wplata) {
        this.wplata.set(wplata);
    }

    public String getUbezpieczenie() {
        return ubezpieczenie.get();
    }

    public StringProperty ubezpieczenieProperty() {
        return ubezpieczenie;
    }

    public void setUbezpieczenie(String ubezpieczenie) {
        this.ubezpieczenie.set(ubezpieczenie);
    }
}

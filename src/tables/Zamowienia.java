package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Klasa Zamowienia przechowuje dane z tab Zamowienia
 */
public class Zamowienia implements Externalizable{
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

    /**
     * Metoda zapisuje obiekt do strumienia
     * @param out typu ObjectOutput
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getOpis_zam());
        out.writeObject(getWplata());
        out.writeObject(getUbezpieczenie());
    }
    /**
     * Metoda odczytuje obiekt ze strumienia
     * @param in typu ObjectInput
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setOpis_zam((String)in.readObject());
        setWplata((String)in.readObject());
        setUbezpieczenie((String)in.readObject());
    }
}

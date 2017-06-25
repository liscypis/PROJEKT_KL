package tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by Wojtek on 13.06.2017.
 */
public class Uzytkownicy  implements Externalizable{
    private IntegerProperty id_zamowienia;
    private IntegerProperty id_uzytkownika;
    private StringProperty imie;
    private StringProperty nazwisko;
    private IntegerProperty id_wycieczki;
    private StringProperty wplata;
    private StringProperty ubezpieczenie;

    public Uzytkownicy() {
        this.id_zamowienia = new SimpleIntegerProperty();
        this.id_uzytkownika = new SimpleIntegerProperty();
        this.imie = new SimpleStringProperty();
        this.nazwisko = new SimpleStringProperty();
        this.id_wycieczki = new SimpleIntegerProperty();
        this.wplata = new SimpleStringProperty();
        this.ubezpieczenie = new SimpleStringProperty();
    }

    public int getId_zamowienia() {
        return id_zamowienia.get();
    }

    public IntegerProperty id_zamowieniaProperty() {
        return id_zamowienia;
    }

    public void setId_zamowienia(int id_zamowienia) {
        this.id_zamowienia.set(id_zamowienia);
    }

    public int getId_wycieczki() {
        return id_wycieczki.get();
    }

    public IntegerProperty id_wycieczkiProperty() {
        return id_wycieczki;
    }

    public void setId_wycieczki(int id_wycieczki) {
        this.id_wycieczki.set(id_wycieczki);
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

    public int getId_uzytkownika() {
        return id_uzytkownika.get();
    }

    public IntegerProperty id_uzytkownikaProperty() {
        return id_uzytkownika;
    }

    public void setId_uzytkownika(int id_uzytkownika) {
        this.id_uzytkownika.set(id_uzytkownika);
    }

    public String getImie() {
        return imie.get();
    }

    public StringProperty imieProperty() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(getId_zamowienia());
        out.writeInt(getId_uzytkownika());
        out.writeObject(getImie());
        out.writeObject(getNazwisko());
        out.writeInt(getId_wycieczki());
        out.writeObject(getWplata());
        out.writeObject(getUbezpieczenie());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId_zamowienia(in.readInt());
        setId_uzytkownika(in.readInt());
        setImie((String)in.readObject());
        setNazwisko((String)in.readObject());
        setId_wycieczki(in.readInt());
        setWplata((String)in.readObject());
        setUbezpieczenie((String)in.readObject());
    }
}

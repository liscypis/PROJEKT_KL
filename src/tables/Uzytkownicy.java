package tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Wojtek on 13.06.2017.
 */
public class Uzytkownicy {
    private IntegerProperty id_uzytkownika;
    private StringProperty imie;
    private StringProperty nazwisko;
    private IntegerProperty id_wycieczki;
    private StringProperty wplata;
    private StringProperty ubezpieczenie;

    public Uzytkownicy() {
        this.id_uzytkownika = new SimpleIntegerProperty();
        this.imie = new SimpleStringProperty();
        this.nazwisko = new SimpleStringProperty();
        this.id_wycieczki = new SimpleIntegerProperty();
        this.wplata = new SimpleStringProperty();
        this.ubezpieczenie = new SimpleStringProperty();
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
}

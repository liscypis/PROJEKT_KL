package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.ConnectToDatabase;
import tables.Uzytkownicy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa zawiera metody operujace na zamowieniach uzytkownikow
 */
public class UzytkownicyAdmin {

    //*************************************
    //SELECT  FROM Uzytkowncy
    //*************************************

    /**
     * Metoda pobiera z bazy wszystkich uzytkownikow ktorzy zlozyli zamowienie i zapisuje je na ArrayList
     * @return obiekt typu ArrayList
     */
    public static ArrayList<Uzytkownicy> searchUzytkownicy () throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT zamowienia.id_zamowienia, oferty.id_oferty, uzytkownicy.id_uzytkownika, imie, nazwisko, wplata, ubezpieczenie FROM oferty, uzytkownicy, zamowienia where zamowienia.id_uzytkownika = uzytkownicy.id_uzytkownika and zamowienia.id_oferty = oferty.id_oferty";
        ArrayList<Uzytkownicy> uzytkownicyList = null;
        try {
            ResultSet resultUzytkownicy = ConnectToDatabase.executeSelect(selectStmt);
            uzytkownicyList = getUzywkownicyList(resultUzytkownicy);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uzytkownicyList;
    }

    /**
     * Metoda zapisuje dane otrzymane w argumencie
     * @param rs obiekt typu ResultSet
     * @return obiekt typu ArrayList
     */
    public static ArrayList<Uzytkownicy> getUzywkownicyList(ResultSet rs) throws SQLException
    {
        ArrayList<Uzytkownicy> uzytkownicyList = new ArrayList<>();

        while (rs.next()) {
            Uzytkownicy uz = new Uzytkownicy();
            uz.setId_zamowienia(rs.getInt("ID_ZAMOWIENIA"));
            uz.setId_wycieczki(rs.getInt("ID_OFERTY"));
            uz.setId_uzytkownika(rs.getInt("ID_UZYTKOWNIKA"));
            uz.setImie(rs.getString("IMIE"));
            uz.setNazwisko(rs.getString("NAZWISKO"));
            uz.setWplata(rs.getString("WPLATA"));
            uz.setUbezpieczenie(rs.getString("UBEZPIECZENIE"));
            uzytkownicyList.add(uz);
        }
        return uzytkownicyList;
    }

    /**
     * Metoda aktualizuje wpłatę uzytkownika za wycieczke
     * @param uz obiekt typu Uzytkownicy
     */
    public static void updateWplata (Uzytkownicy uz) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE ZAMOWIENIA SET WPLATA='"+uz.getWplata()+"' WHERE ID_ZAMOWIENIA= "+uz.getId_zamowienia()+"";
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.ConnectToDatabase;
import tables.Uzytkownicy;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wojtek on 13.06.2017.
 */
public class UzytkownicyAdmin {

    //*************************************
    //SELECT  FROM Uzytkowncy
    //*************************************
    public static ObservableList<Uzytkownicy> searchUzytkownicy () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT zamowienia.id_zamowienia, oferty.id_oferty, uzytkownicy.id_uzytkownika, imie, nazwisko, wplata, ubezpieczenie FROM oferty, uzytkownicy, zamowienia where zamowienia.id_uzytkownika = uzytkownicy.id_uzytkownika and zamowienia.id_oferty = oferty.id_oferty";
        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultUzytkownicy = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            ObservableList<Uzytkownicy> uzytkownicyList = getUzywkownicyList(resultUzytkownicy);

            //Return oferty object
            return uzytkownicyList;
        } catch (SQLException e) {
            System.out.println("ERROR !!!! " + e);
            //Return exception
            throw e;
        }
    }

    //*************************************
    // Dodanie uzytkownikow do listy
    //*************************************
    public static ObservableList<Uzytkownicy> getUzywkownicyList(ResultSet rs) throws SQLException
    {
        ObservableList<Uzytkownicy> uzytkownicyList = FXCollections.observableArrayList();

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
    //*************************************
    //UPDATE wpłata
    //*************************************
    public static void updateWplata (int id_zam, String wplata) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "UPDATE ZAMOWIENIA SET WPLATA='"+wplata+"' WHERE ID_ZAMOWIENIA= "+id_zam+"";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }
}

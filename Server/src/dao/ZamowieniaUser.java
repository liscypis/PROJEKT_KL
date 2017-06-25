package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.ConnectToDatabase;
import tables.Zamowienia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa zawiera metode pobierającą zamowienai usera
 */
public class ZamowieniaUser {

    /**
     * Metoda pobiera z bazy wszystkie zamowienie uzytkownika o podanym id i zapisuje je na ArrayList
     * @param id_usera id_uzytkownika
     * @return obiekt typu ArrayList
     */
    public static ArrayList<Zamowienia> searchZamowienia (int id_usera) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT oferty.opis,ubezpieczenie,wplata FROM zamowienia, oferty where zamowienia.id_oferty = oferty.id_oferty AND zamowienia.id_uzytkownika="+id_usera+"";
        ArrayList<Zamowienia> zamowieniaList = null;
        try {
            ResultSet resultZamowienia = ConnectToDatabase.executeSelect(selectStmt);
            zamowieniaList = getZamowieniaList(resultZamowienia);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return zamowieniaList;
    }

    /**
     * Metoda sapisuje zamówienia na ArrayList
     * @param rs obiekt typu ResultSet
     * @return obiekt typu ArrayList
     */
    public static ArrayList<Zamowienia> getZamowieniaList(ResultSet rs) throws SQLException
    {
        ArrayList<Zamowienia> ofertyList = new ArrayList<>();

        while (rs.next()) {
            Zamowienia zamowienia = new Zamowienia();
            zamowienia.setOpis_zam(rs.getString("OPIS"));
            zamowienia.setUbezpieczenie(rs.getString("UBEZPIECZENIE"));
            zamowienia.setWplata(rs.getString("WPLATA"));
            ofertyList.add(zamowienia);
        }
        return ofertyList;
    }
}

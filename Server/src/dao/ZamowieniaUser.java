package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.ConnectToDatabase;
import tables.Zamowienia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Wojtek on 13.06.2017.
 */
public class ZamowieniaUser {
    //SELECT * ZAMOWIENIA user
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

    // dodawanie ofert do listy
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

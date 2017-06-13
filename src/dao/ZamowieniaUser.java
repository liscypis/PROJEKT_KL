package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.ConnectToDatabase;
import tables.Zamowienia;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wojtek on 13.06.2017.
 */
public class ZamowieniaUser {
    //SELECT * ZAMOWIENIA user
    public static ObservableList<Zamowienia> searchZamowienia () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT oferty.opis,ubezpieczenie,wplata FROM zamowienia, oferty where zamowienia.id_oferty = oferty.id_oferty";

        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultZamowienia = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            ObservableList<Zamowienia> zamowieniaList = getZamowieniaList(resultZamowienia);

            //Return oferty object
            return zamowieniaList;
        } catch (SQLException e) {
            System.out.println("ERROR !!!! " + e);
            //Return exception
            throw e;
        }
    }

    // dodawanie ofert do listy
    public static ObservableList<Zamowienia> getZamowieniaList(ResultSet rs) throws SQLException
    {
        ObservableList<Zamowienia> ofertyList = FXCollections.observableArrayList();

        if (rs.next()) {
            Zamowienia zamowienia = new Zamowienia();
            zamowienia.setOpis_zam(rs.getString("OPIS"));
            zamowienia.setUbezpieczenie(rs.getString("UBEZPIECZENIE"));
            zamowienia.setWplata(rs.getString("WPLATA"));
            ofertyList.add(zamowienia);
        }
        return ofertyList;
    }
}

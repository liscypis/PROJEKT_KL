package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.ConnectToDatabase;
import tables.Oferty;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wojtek on 13.06.2017.
 */
public class OfertyUser {

    //*************************************
    //SELECT  FROM oferty
    //*************************************
    public static ObservableList<Oferty> searchOfertyUs () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT opis,cena,data_pocz,data_konc FROM oferty";

        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultOferty = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            ObservableList<Oferty> ofertyList = getOfertyListUs(resultOferty);

            //Return oferty object
            return ofertyList;
        } catch (SQLException e) {
            System.out.println("ERROR !!!! " + e);
            //Return exception
            throw e;
        }
    }

    //*************************************
    //Dodawanie elementow do listy
    //*************************************
    public static ObservableList<Oferty> getOfertyListUs(ResultSet rs) throws SQLException
    {
        ObservableList<Oferty> ofertyList = FXCollections.observableArrayList();

        while (rs.next()) {
            Oferty ofe = new Oferty();
            ofe.setOpis(rs.getString("OPIS"));
            ofe.setCena(rs.getDouble("CENA"));
            ofe.setData_pocz(rs.getDate("DATA_POCZ"));
            ofe.setData_konc(rs.getDate("DATA_KONC"));
            ofertyList.add(ofe);
        }
        return ofertyList;
    }

}

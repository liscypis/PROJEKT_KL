package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.ConnectToDatabase;
import tables.Oferty;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wojtek on 09.06.2017.
 */
public class OfertyAdmin {

    //SELECT 1 Oferty Admin
    public static Oferty searchOferta (String id_ofe) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM oferty WHERE id_oferty="+id_ofe;

        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultOferty = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Oferty oferty = getOfertyFromResultSet(resultOferty);

            //Return oferty object
            return oferty;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + id_ofe + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    private static Oferty getOfertyFromResultSet(ResultSet rs) throws SQLException
    {
        Oferty ofe = null;
        if (rs.next()) {
            ofe = new Oferty();
            ofe.setId_oferty(rs.getInt("ID_OFERTY"));
            ofe.setOpis(rs.getString("OPIS"));
            ofe.setCena(rs.getDouble("CENA"));
            ofe.setData_pocz(rs.getDate("DATA_POCZ"));
            ofe.setData_konc(rs.getDate("DATA_KONC"));
            ofe.setIlosc_miejsc(rs.getInt("ILOSC_MIEJSC"));
        }
        return ofe;
    }

    //SELECT * Oferty Admin
    public static ObservableList<Oferty> searchOferty () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM oferty";

        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultOferty = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            ObservableList<Oferty> ofertyList = getOfertyList(resultOferty);

            //Return oferty object
            return ofertyList;
        } catch (SQLException e) {
            System.out.println("ERROR !!!! " + e);
            //Return exception
            throw e;
        }
    }

    // dodawanie ofert do listy
    public static ObservableList<Oferty> getOfertyList(ResultSet rs) throws SQLException
    {
        ObservableList<Oferty> ofertyList = FXCollections.observableArrayList();

        if (rs.next()) {
            Oferty ofe = new Oferty();
            ofe.setId_oferty(rs.getInt("ID_OFERTY"));
            ofe.setOpis(rs.getString("OPIS"));
            ofe.setCena(rs.getDouble("CENA"));
            ofe.setData_pocz(rs.getDate("DATA_POCZ"));
            ofe.setData_konc(rs.getDate("DATA_KONC"));
            ofe.setIlosc_miejsc(rs.getInt("ILOSC_MIEJSC"));
            ofertyList.add(ofe);
        }
        return ofertyList;
    }


}

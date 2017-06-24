package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.ConnectToDatabase;
import tables.Login;
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
        String selectStmt = "SELECT * FROM oferty WHERE ILOSC_MIEJSC !=0 ";

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
    //*************************************
    // Szuka loginu
    //*************************************
    public static Login checkLogintoLabel (Integer id) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT login FROM uzytkownicy WHERE id_uzytkownika="+id+"";

        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultLog = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Login lg = getLogin(resultLog);

            //Return oferty object
            return lg;
        } catch (SQLException e) {
            System.out.println(" an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    //*************************************
    // zapisuje znaleziony login
    //*************************************
    private static Login getLogin(ResultSet rs) throws SQLException
    {
        Login ofe = null;
        if (rs.next()) {
            ofe = new Login();
            ofe.setLogin(rs.getString("LOGIN"));
        }
        return ofe;
    }
    //*************************************
    // dodaje zamownienie
    //*************************************
    public static void addZam (int id_uz, int id_of, String ubezpieczenie) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "insert into ZAMOWIENIA values(zamowienia_seq.nextval,"+id_uz+","+id_of+",'"+ubezpieczenie+"','Nie')";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }
    // pomniejsza ilosc miejsc przy skladaniu zamowienia
    public static void decreaseIloscMiejsc (int iloscMiejsc, int id_ofe) throws SQLException, ClassNotFoundException {
        iloscMiejsc -= 1;
        String updateStmt = "UPDATE oferty SET ILOSC_MIEJSC="+iloscMiejsc+" WHERE id_oferty= "+id_ofe+"";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }

}

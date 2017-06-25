package dao;

import oracle.ConnectToDatabase;
import tables.Login;
import tables.Uzytkownicy;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wojtek on 17.06.2017.
 */
public class EditUser {
    //*************************************
    // pobiera immie i nazwisko o podanym id
    //*************************************
    public static Uzytkownicy getImieAndNazwisko (Uzytkownicy uzytkownicy) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT IMIE, NAZWISKO FROM UZYTKOWNICY WHERE ID_UZYTKOWNIKA= "+uzytkownicy.getId_uzytkownika()+"";

        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultLog = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Uzytkownicy uz = getImieAndNazwiskoFromResult(resultLog);

            //Return oferty object
            return uz;
        } catch (SQLException e) {
            System.out.println(" an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    private static Uzytkownicy getImieAndNazwiskoFromResult(ResultSet rs) throws SQLException
    {
        Uzytkownicy uz = null;
        if (rs.next()) {
            uz = new Uzytkownicy();
            uz.setImie(rs.getString("IMIE"));
            uz.setNazwisko(rs.getString("NAZWISKO"));
        }
        return uz;
    }

    //*************************************
    //UPDATE nazwisko
    //*************************************
    public static void updateSurname (String surname, Login lg) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "UPDATE UZYTKOWNICY SET NAZWISKO='"+surname+"' WHERE ID_UZYTKOWNIKA= "+lg.getId_uz()+"";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }
    public static void updateName (String name, Login lg) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "UPDATE UZYTKOWNICY SET IMIE='"+name+"' WHERE ID_UZYTKOWNIKA= "+lg.getId_uz()+"";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }
    public static void updatePassword (Login lg) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "UPDATE UZYTKOWNICY SET HASLO='"+lg.getHaslo()+"' WHERE ID_UZYTKOWNIKA= "+lg.getId_uz()+"";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }
    public static void updateLogin (Login lg) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "UPDATE UZYTKOWNICY SET LOGIN='"+lg.getLogin()+"' WHERE ID_UZYTKOWNIKA= "+lg.getId_uz()+"";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }

   /* public static Login checkUserPassword (Login lg) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT id_uzytkownika  FROM uzytkownicy WHERE id_uzytkownika= "+lg.getId_uz()+" AND haslo= '"+lg.getHaslo()+"'";
        Login log = null;
        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultLog = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            lg = getIdUser(resultLog);

            //Return oferty object
            return log;
        } catch (SQLException e) {
            System.out.println(" an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    private static Login getIdUser(ResultSet rs) throws SQLException
    {
        Login lg = null;
        if (rs.next()) {
            lg = new Login();
            lg.setId_uz(rs.getInt("ID_UZYTKOWNIKA"));
        }
        return lg;
    }*/
}

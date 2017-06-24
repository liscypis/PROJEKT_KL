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
    public static Uzytkownicy getImieAndNazwisko (int id_uz) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT IMIE, NAZWISKO FROM UZYTKOWNICY WHERE ID_UZYTKOWNIKA= "+id_uz+"";

        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultLog = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Uzytkownicy lg = getImieAndNazwiskoFromResult(resultLog);

            //Return oferty object
            return lg;
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
    public static void updateSurname (int id_uz, String surname) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "UPDATE UZYTKOWNICY SET NAZWISKO='"+surname+"' WHERE ID_UZYTKOWNIKA= "+id_uz+"";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }
    public static void updateName (int id_uz, String name) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "UPDATE UZYTKOWNICY SET IMIE='"+name+"' WHERE ID_UZYTKOWNIKA= "+id_uz+"";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }
    public static void updatePassword (int id_uz, String password) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "UPDATE UZYTKOWNICY SET HASLO='"+password+"' WHERE ID_UZYTKOWNIKA= "+id_uz+"";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }
    public static void updateLogin (int id_uz, String login) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "UPDATE UZYTKOWNICY SET LOGIN='"+login+"' WHERE ID_UZYTKOWNIKA= "+id_uz+"";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }

    public static Login checkUserPassword (int  id_uz, String haslo) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT id_uzytkownika  FROM uzytkownicy WHERE id_uzytkownika= "+id_uz+" AND haslo= '"+haslo+"'";

        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultLog = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Login lg = getIdUser(resultLog);

            //Return oferty object
            return lg;
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
    }
}

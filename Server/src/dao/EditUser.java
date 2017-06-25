package dao;

import oracle.ConnectToDatabase;
import tables.Login;
import tables.Uzytkownicy;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Zawiera metody modyfikujące uzytkownika
 */
public class EditUser {
    //*************************************
    // pobiera immie i nazwisko o podanym id
    //*************************************

    /**
     * Metoda pobiera z bazy imie i nazwisko o podanym id
     * @param uzytkownicy obekt typu Uzytkownicy
     * @return Obiekt typu Uzytkownicy
     */
    public static Uzytkownicy getImieAndNazwisko (Uzytkownicy uzytkownicy) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT IMIE, NAZWISKO FROM UZYTKOWNICY WHERE ID_UZYTKOWNIKA= "+uzytkownicy.getId_uzytkownika()+"";
        Uzytkownicy uz = null;
        try {
            ResultSet resultLog = ConnectToDatabase.executeSelect(selectStmt);
            uz = getImieAndNazwiskoFromResult(resultLog);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uz;
    }
    /**
     * Metoda zapisuje dane otrzymane w argumencie
     * @param rs typu ResultSet
     * @return obiekt typu Uzytkownicy
     */
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

    /**
     * Medota aktualicuje nazwisko uzytkownika
     * @param surname typu String
     * @param lg typu Login
     */
    public static void updateSurname (String surname, Login lg) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE UZYTKOWNICY SET NAZWISKO='"+surname+"' WHERE ID_UZYTKOWNIKA= "+lg.getId_uz()+"";
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda aktualizuje imie uzytkownika
     * @param name typu String
     * @param lg typu Login
     */
    public static void updateName (String name, Login lg) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE UZYTKOWNICY SET IMIE='"+name+"' WHERE ID_UZYTKOWNIKA= "+lg.getId_uz()+"";
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda aktualizje hasło uzytkownika
     * @param lg typu Login
     */
    public static void updatePassword (Login lg) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE UZYTKOWNICY SET HASLO='"+lg.getHaslo()+"' WHERE ID_UZYTKOWNIKA= "+lg.getId_uz()+"";
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Metoda aktualizuje login uzytkownika
     * @param lg typu Login
     */
    public static void updateLogin (Login lg) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE UZYTKOWNICY SET LOGIN='"+lg.getLogin()+"' WHERE ID_UZYTKOWNIKA= "+lg.getId_uz()+"";
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }

}

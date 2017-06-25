package dao;

import oracle.ConnectToDatabase;
import tables.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasa zawiera metody odpowiedzialne za rejestracje
 */
public class Registration {
    /**
     * Metoda sprawdza czy podany login istnieje w bazie
     * @param login obiekt typu String
     * @return obiekt typu Login
     */
    public static Login checkLogin (String  login) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT login  FROM uzytkownicy WHERE login= '"+login+"'";
        Login lg = null;
        try {
            ResultSet resultLog = ConnectToDatabase.executeSelect(selectStmt);
            lg = getLogin(resultLog);
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return lg;
    }

    /**
     * Metoda zapisuje login na podstawie otrzymanego argumentu
     * @param rs biekt typu ResultSet
     * @return obiekt typu Login
     */
    private static Login getLogin(ResultSet rs) throws SQLException
    {
        Login lg = null;
        if (rs.next()) {
            lg = new Login();
            lg.setLogin(rs.getString("LOGIN"));
        }
        return lg;
    }

    //*************************************
    //INSERT uzytkownik
    //*************************************

    /**
     * Metoda dodaje nowego uzytkownika
     * @param imie obiekt typu String
     * @param nazwisko obiekt typu String
     * @param lg obiekt typu Login
     */
    public static void addUser (String imie, String nazwisko, Login lg) throws SQLException, ClassNotFoundException {
        String updateStmt = "insert into uzytkownicy values(uzytkownicy_seq.nextval,'"+imie+"','"+nazwisko+"','"+lg.getLogin()+"','"+lg.getHaslo()+"')";
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}

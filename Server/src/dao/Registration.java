package dao;

import oracle.ConnectToDatabase;
import tables.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wojtek on 16.06.2017.
 */
public class Registration {
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
    //INSERT uzytkownik
    //*************************************
    public static void addUser (String imie, String nazwisko, Login lg) throws SQLException, ClassNotFoundException {
        String updateStmt = "insert into uzytkownicy values(uzytkownicy_seq.nextval,'"+imie+"','"+nazwisko+"','"+lg.getLogin()+"','"+lg.getHaslo()+"')";
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}

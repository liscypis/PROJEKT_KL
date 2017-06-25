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
        //Declare a SELECT statement
        String selectStmt = "SELECT login  FROM uzytkownicy WHERE login= '"+login+"'";

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
        //Declare a insert statement
        String updateStmt = "insert into uzytkownicy values(uzytkownicy_seq.nextval,'"+imie+"','"+nazwisko+"','"+lg.getLogin()+"','"+lg.getHaslo()+"')";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }
}

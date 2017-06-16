package dao;

import oracle.ConnectToDatabase;
import tables.Login;
import tables.Oferty;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wojtek on 15.06.2017.
 */
public class LoginDAO {
    //*************************************
    // pobiera id_uz o podanym log i pass
    //*************************************
    public static Login checkLoginAndPassword (String  login, String haslo) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT id_uzytkownika  FROM uzytkownicy WHERE login= '"+login+"' AND haslo= '"+haslo+"'";

        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultLog = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Login lg = getIdFromLogin(resultLog);

            //Return oferty object
            return lg;
        } catch (SQLException e) {
            System.out.println(" an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    private static Login getIdFromLogin(ResultSet rs) throws SQLException
    {
        Login ofe = null;
        if (rs.next()) {
            ofe = new Login();
            ofe.setId_uz(rs.getInt("ID_UZYTKOWNIKA"));
        }
        return ofe;
    }
}

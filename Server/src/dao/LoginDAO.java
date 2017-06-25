package dao;

import oracle.ConnectToDatabase;
import tables.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wojtek on 15.06.2017.
 */
public class LoginDAO {
    //*************************************
    // pobiera id_uz o podanym log i pass
    //*************************************
    public static Login checkLoginAndPassword (Login lg) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT id_uzytkownika  FROM uzytkownicy WHERE login= '"+lg.getLogin()+"' AND haslo= '"+lg.getHaslo()+"'";
        try {
            ResultSet resultLog = ConnectToDatabase.executeSelect(selectStmt);
            lg = getIdFromLogin(resultLog);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lg;
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

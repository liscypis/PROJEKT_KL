package dao;

import oracle.ConnectToDatabase;
import tables.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Pobiera dane logowania z bazy
 */
public class LoginDAO {
    //*************************************
    // pobiera id_uz o podanym log i pass
    //*************************************

    /**
     * Metoda pobiera z bazy id_uz o podanym loginie i haśle
     * @param lg typu Login
     * @return obiekt Login
     */
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
    /**
     * Metoda zapisuje dane otrzymane w argumencie
     * @param rs typu ResultSet
     * @return obiekt typu Login
     */
    private static Login getIdFromLogin(ResultSet rs) throws SQLException
    {
        Login lg = null;
        if (rs.next()) {
            lg = new Login();
            lg.setId_uz(rs.getInt("ID_UZYTKOWNIKA"));
        }
        return lg;
    }
}

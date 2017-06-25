package dao;

import oracle.ConnectToDatabase;
import tables.Login;
import tables.Oferty;
import tables.Uzytkownicy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa zawiera metody przeprowadzjace operacje na tab Oferty po stronie Uzytkownika
 */
public class OfertyUser {

    /**
     * Metoda pobiera z bazy wyszustkie oferty ktore mają wolne miejsca i zapisuje je do ArrayList
     * @return obiekt typu ArrayList
     */
    public static ArrayList<Oferty> searchOfertyUs () throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM oferty WHERE ILOSC_MIEJSC !=0 ";
        ArrayList<Oferty> ofertyList = null;
        try {
            ResultSet resultOferty = ConnectToDatabase.executeSelect(selectStmt);
            ofertyList = getOfertyListUs(resultOferty);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ofertyList;
    }
    /**
     * Metoda dodaje Oferty do ArrayList
     * @param rs obiekt typu ResultSet
     * @return obiekt typu ArrayList
     */
    public static ArrayList<Oferty> getOfertyListUs(ResultSet rs) throws SQLException
    {
        ArrayList<Oferty> ofertyList = new ArrayList<>();

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

    /**
     * Metoda szuka loginu dla podanego id uzytkownika
     * @param id obiekt typu Integer
     * @return obiekt typu Login
     */
    public static Login checkLogintoLabel (Integer id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT login FROM uzytkownicy WHERE id_uzytkownika="+id+"";
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
     * Metoda zapisuje login
     * @param rs obiekt typu ResultSet
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
    // dodaje zamownienie
    //*************************************

    /**
     * Metoda dodaje zamowienie
     * @param uz obiekt typu Uzytkownicy
     */
    public static void addZam (Uzytkownicy uz) throws SQLException, ClassNotFoundException {
        String updateStmt = "insert into ZAMOWIENIA values(zamowienia_seq.nextval,"+uz.getId_uzytkownika()+","+uz.getId_wycieczki()+",'"+uz.getUbezpieczenie()+"','Nie')";
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    /**
     * Metoda zmniejsza ilosc mniejsc w danej ofercie
     * @param ofe obiekt typu Oferty
     */
    public static void decreaseIloscMiejsc (Oferty ofe) throws SQLException, ClassNotFoundException {
        ofe.setIlosc_miejsc(ofe.getIlosc_miejsc()-1);
        String updateStmt = "UPDATE oferty SET ILOSC_MIEJSC="+ofe.getIlosc_miejsc()+" WHERE id_oferty= "+ofe.getId_oferty()+"";
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda szuka ofert ktore posiadaja podany ciąg znaków i zapisuje je na liste
     * @param ofe obiekt typu Oferty
     * @return obiekt typu ArrayList
     */
    public static ArrayList<Oferty> szukajOferty(Oferty ofe) {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM oferty WHERE OPIS LIKE  '%"+ofe.getOpis()+"%' ";
        ArrayList<Oferty> ofertyList = null;
        try {
            ResultSet resultOferty = ConnectToDatabase.executeSelect(selectStmt);
            ofertyList = getszukajOferty(resultOferty);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ofertyList;
    }
    /**
     * Metoda zapisuje na liste znalecione oferty
     * @param rs obiekt typu ResultSet
     * @return obiekt typu ArrayList
     */
    public static ArrayList<Oferty> getszukajOferty(ResultSet rs) throws SQLException
    {
        ArrayList<Oferty> ofertyList = new ArrayList<>();
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
}

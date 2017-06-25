package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.ConnectToDatabase;
import tables.Oferty;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Zawiera metody odpowiedzialne za operacje na tab Oferty
 */
public class OfertyAdmin {

    /**
     * Metoda pobiera z bazy wszystkie oferty i zapisuje do ArrayList
     * @return obiekt ArrayList
     */
    public static ArrayList<Oferty> searchOferty () throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM oferty";
        ArrayList<Oferty> ofertyList = null;
        try {
            ResultSet resultOferty = ConnectToDatabase.executeSelect(selectStmt);
            ofertyList = getOfertyList(resultOferty);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ofertyList;
    }
    /**
     * Metoda zapisuje orefty w ArrayList
     * @param rs ResultSet
     * @return obiekt typu ArrayList
     */
    public static ArrayList<Oferty> getOfertyList(ResultSet rs) throws SQLException
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
     * Metoda dodaje nową ofertę
     * @param ofe obiekt typu Oferty
     */
    public static void insertoferta (Oferty ofe) throws SQLException, ClassNotFoundException {
        String updateStmt = "insert into oferty values(oferty_seq.nextval,'"+ofe.getOpis()+"',"+ofe.getCena()+",'"+ofe.getData_pocz()+"','"+ofe.getData_konc()+"',"+ofe.getIlosc_miejsc()+")";
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda aktualizuje oferte
     * @param ofe obiekt typu Oferty
     */
    public static void updateOferta (Oferty ofe) throws SQLException, ClassNotFoundException {
        String updateStmt = "UPDATE oferty SET OPIS='"+ofe.getOpis()+"', CENA="+ofe.getCena()+", DATA_POCZ='"+ofe.getData_pocz()+"',DATA_KONC='"+ofe.getData_konc()+"'WHERE id_oferty= "+ofe.getId_oferty()+"";
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Metoda zamenia Stringa na date
     * @param strDate typu String
     * @return obiekt typu java.sql.Date
     */
    public static Date toSqlDate(String strDate)
    {
        DateFormat dateFrm = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date myDate;
        java.sql.Date sqlDate;

        try
        {
            myDate = dateFrm.parse(strDate);
            sqlDate = new java.sql.Date(myDate.getTime());
        }
        catch (Exception e)
        {
            sqlDate = null;
        }

        return (sqlDate);
    }
}

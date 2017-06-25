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
 * Created by Wojtek on 09.06.2017.
 */
public class OfertyAdmin {

   /* //SELECT 1 Oferty Admin
    public static Oferty searchOferta (int id_ofe) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT opis, cena, data_pocz, data_konc FROM oferty WHERE id_oferty= "+id_ofe+"";

        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultOferta = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Oferty oferta = getOfertaFromResultSet(resultOferta);

            //Return oferty object
            return oferta;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + id_ofe + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    private static Oferty getOfertaFromResultSet(ResultSet rs) throws SQLException
    {
        Oferty ofe = null;
        if (rs.next()) {
            ofe = new Oferty();
            ofe.setOpis(rs.getString("OPIS"));
            ofe.setCena(rs.getDouble("CENA"));
            ofe.setData_pocz(rs.getDate("DATA_POCZ"));
            ofe.setData_konc(rs.getDate("DATA_KONC"));
        }
        return ofe;
    }
*/
   //*************************************
   //SELECT * FROM oferty
   //*************************************
    public static ArrayList<Oferty> searchOferty () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM oferty";

        //Execute SELECT statement
        try {
            //Get ResultSet from executeSelect method
            ResultSet resultOferty = ConnectToDatabase.executeSelect(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            ArrayList<Oferty> ofertyList = getOfertyList(resultOferty);

            //Return oferty object
            return ofertyList;
        } catch (SQLException e) {
            System.out.println("ERROR !!!! " + e);
            //Return exception
            throw e;
        }
    }

    //*************************************
    // DOdawanie ofert do listy
    //*************************************
    public static ArrayList<Oferty> getOfertyList(ResultSet rs) throws SQLException
    {
        ArrayList<Oferty> ofertyList = new ArrayList<>(); // FXCollections.observableArrayList();

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
    //*************************************
    //INSERT oferta
    //*************************************
    public static void insertoferta (Oferty ofe) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "insert into oferty values(oferty_seq.nextval,'"+ofe.getOpis()+"',"+ofe.getCena()+",'"+ofe.getData_pocz()+"','"+ofe.getData_konc()+"',"+ofe.getIlosc_miejsc()+")";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            throw e;
        }
    }
    //*************************************
    //UPDATE oferta
    //*************************************
    public static void updateOferta (Oferty ofe) throws SQLException, ClassNotFoundException {
        //Declare a insert statement
        String updateStmt = "UPDATE oferty SET OPIS='"+ofe.getOpis()+"', CENA="+ofe.getCena()+", DATA_POCZ='"+ofe.getData_pocz()+"',DATA_KONC='"+ofe.getData_konc()+"'WHERE id_oferty= "+ofe.getId_oferty()+"";
        //Execute insert
        try {
            ConnectToDatabase.executeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while insert Operation: " + e);
            e.printStackTrace();
        }
    }
    // zamiana stringa na date
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

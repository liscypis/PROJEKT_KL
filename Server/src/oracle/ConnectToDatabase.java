package oracle;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

/**
 * Klasa zawiera medody odpowiedzialne za łączenie się z bazą i wykonywanie operacji na niej
 */
public class ConnectToDatabase {
    private static Connection connection = null;

    /**
     * Metoda łączy się z bazą
     */
    public static void connect() {
        System.out.println("-------- Oracle JDBC Connection ------");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Nie znaleziono sterownika Oracle JDBC");
            e.printStackTrace();
            return;
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            System.out.println("Udało się nawiązać połączenie z bazą");
        } else {
            System.out.println("Nie udało się połączyć");
        }
    }

    /**
     * Metoda zamyka połączenie z bazą
     */
    public static void disconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e){
            throw e;
        }
    }

    /**
     * Metoda wykonuje zapytania podaje jej w argumencie
     * @param queryStmt obiekt typu String
     * @return obiekt typu CachedRowSetImpl
     */
    public static ResultSet executeSelect(String queryStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            connect();
            System.out.println("Select : " + queryStmt + "\n");
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(queryStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            disconnect();
        }
        return crs;
    }

    /**
     * Metoda wykonuje Update/delete/insert podane jako argument
     * @param sqlStmt obiekt typu String
     */
    public static void executeUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            connect();
            System.out.println("Update/delete/insert " +sqlStmt);
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            disconnect();
        }
    }
}

package tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;

/**
 * Created by Wojtek on 15.06.2017.
 */
public class Login implements Serializable{
    private Integer id_uz;
    private String login;
    private String haslo;
    private static Integer id;

    public Login(){
    }

    public Integer getId_uz() {
        return id_uz;
    }

    public void setId_uz(Integer id_uz) {
        this.id_uz = id_uz;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        Login.id = id;
    }

}

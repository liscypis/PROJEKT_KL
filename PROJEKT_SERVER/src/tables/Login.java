package tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Wojtek on 15.06.2017.
 */
public class Login {
    private IntegerProperty id_uz;
    private StringProperty login;
    private StringProperty haslo;
    private static Integer id;

    public Login(){
        this.id_uz = new SimpleIntegerProperty();
        this.login = new SimpleStringProperty();
        this.haslo = new SimpleStringProperty();
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        Login.id = id;
    }

    public int getId_uz() {
        return id_uz.get();
    }

    public IntegerProperty id_uzProperty() {
        return id_uz;
    }

    public void setId_uz(int id_uz) {
        this.id_uz.set(id_uz);
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getHaslo() {
        return haslo.get();
    }

    public StringProperty hasloProperty() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo.set(haslo);
    }
}

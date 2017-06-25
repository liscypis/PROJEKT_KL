package tables;


import java.io.*;

/**
 * Klasa Login przechowuje dane logowania
 */
public class Login implements Serializable{
    private Integer id_uz;
    private String login;
    private String haslo;
    private static Integer id;

    /**
     * Domyślny konstruktor
     */
    public Login(){
    }

    /**
     * Zwraca id_uzytkownika
     * @return Integer
     */
    public Integer getId_uz() {
        return id_uz;
    }

    /**
     * Ustawia id_uzytkownika
     * @param id_uz typu Integer
     */
    public void setId_uz(Integer id_uz) {
        this.id_uz = id_uz;
    }

    /**
     * Zwraca login uzytkownika
     * @return String
     */
    public String getLogin() {
        return login;
    }

    /**
     * Ustawia login uzytkownika
     * @param login typu String
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Zwraca hasło użytkownika
     * @return String
     */
    public String getHaslo() {
        return haslo;
    }

    /**
     * Ustawia hasło użytkownika
     * @param haslo typu String
     */
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    /**
     * Zwraca id_uzytkownika
     * @return Integer
     */
    public static Integer getId() {
        return id;
    }

    /**
     * Ustawia id_uzytkownika
     * @param id typu Integer
     */
    public static void setId(Integer id) {
        Login.id = id;
    }

}

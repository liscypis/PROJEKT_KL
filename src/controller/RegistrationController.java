package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import tables.Login;

import java.io.IOException;
import java.sql.SQLException;

import static sample.ClientSocket.connectToSerwer;

/**
 * RegistrationController obsługuje interfejs rejestracji
 */
public class RegistrationController {

    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordField_2;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private Label loginStatement;
    @FXML
    private Label infoStatement;
    @FXML
    private TextField loginField;
    @FXML
    private Button add;
    @FXML
    private Button logIn;

    /**
     * Metoda ustawia button logIn na nieaktywny
     */
    @FXML
    private void initialize () throws SQLException, ClassNotFoundException{
        logIn.setVisible(false);
    }

    /**
     * Metoda sprawdza czy login jest wolny/ zajęty
     * @return wartość logiczna
     */
    @FXML
    private boolean checkFreeLogin() throws SQLException, ClassNotFoundException, IOException {
        Login lg = (Login) connectToSerwer("Wolny","Login",loginField.getText());
        if(lg == null) {
            return true;
        }
        else {
            loginStatement.setText("LOGIN ZAJETY, WYBIERZ INNY");
            return false;
        }
    }
    /**
     * Metoda sprawdza czy uzupełniono wszytie pola
     * @return wartość logiczna
     */
    private boolean checkFields(){
        if(nameField.getText().trim().equals("") || surnameField.getText().trim().equals("")
                || loginField.getText().trim().equals("") || passwordField.getText().trim().equals("") || passwordField_2.getText().trim().equals("")) {
            return false;
        }
        else
            return true;
    }

    /**
     * Metoda sprawdza hasła czy są takie same
     * @return wartość logiczna
     */
    private boolean checkPassword() {
        if(passwordField.getText().trim().equals(passwordField_2.getText().trim())){
            return true;
        }
        else return false;
    }

    /**
     * Metoda zamyka okno rejestracji
     * @param actionEvent obiekt typu ActionEvent
     */
    @FXML
    private void closeRegistration(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();  // zamyka okno logowania
    }

    /**
     * Metoda dodaje nowego uzytkownika
     */
    @FXML
    private void addNewUser () throws ClassNotFoundException, IOException, SQLException {
        if(checkFields() == false)
        {
            infoStatement.setText("UZUPELNIJ WSZYTKIE POLA");
        }
        else
        {
            if(checkPassword() == false) {
                infoStatement.setText("PODALEŚ DWA ROZNE HASLA");
            }
            else{
                if(checkFreeLogin() == false) {

                }
                else{
                    Login lg = new Login();
                    lg.setLogin(loginField.getText());
                    lg.setHaslo(passwordField.getText().trim());
                    connectToSerwer(nameField.getText(),surnameField.getText(),lg);
                    infoStatement.setText("REJESTRACJA UDANA");
                    add.setDisable(true);
                    logIn.setVisible(true);
                }
            }
        }
    }
}

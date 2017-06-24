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
 * Created by Wojtek on 16.06.2017.
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


    @FXML
    private void initialize () throws SQLException, ClassNotFoundException{
        logIn.setVisible(false);
    }
    //*************************************
    // sprawdza czy login jest wolny, pusty
    //*************************************
    @FXML
    private boolean checkFreeLogin() throws SQLException, ClassNotFoundException, IOException {
        //Get all information
        Login lg = (Login) connectToSerwer("Wolny","Login",loginField.getText());
        if(lg == null) {
            if(loginField.getText().trim().equals("")) {
                loginStatement.setText("NIE PODANO LOGINU ");
                return false;
            }else{
                loginStatement.setText("Wolny");
                return true;
            }
        }
        else {
            loginStatement.setText("LOGIN ZAJĘTY, WYBIERZ INNY");
            return false;
        }
    }
    //*************************************
    // sprawdza czy uzupełniono wszytie pola
    //*************************************
    private boolean checkFields(){
        if(nameField.getText().trim().equals("") || surnameField.getText().trim().equals("")
                || loginField.getText().trim().equals("") || passwordField.getText().trim().equals("") || passwordField_2.getText().trim().equals("")) {
            return false;
        }
        else
            return true;
    }
    //*************************************
    // sprawdza hasła czy są takie same
    //*************************************
    private boolean checkPassword() {
        if(passwordField.getText().trim().equals(passwordField_2.getText().trim())){
            return true;
        }
        else return false;
    }
    //*************************************
    // zamyka okno rejestracji
    //*************************************
    @FXML
    private void closeRegistration(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();  // zamyka okno logowania
    }
    //*************************************
    // dodaje nowego uzytkownika
    //*************************************
    @FXML
    private void addNewUser () throws ClassNotFoundException, IOException, SQLException {
        if(checkFields() == false)
        {
            infoStatement.setText("UZUPEŁNIJ WSZYTKIE POLA");
        }
        else
        {
            if(checkPassword() == false) {
                infoStatement.setText("PODAŁEŚ DWA RÓŻNE HASŁA");
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

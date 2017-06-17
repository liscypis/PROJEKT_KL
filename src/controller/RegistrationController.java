package controller;

import dao.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import tables.Login;

import java.io.IOException;
import java.sql.SQLException;

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
        try {
            //Get all information
            Login lg = Registration.checkLogin(loginField.getText());
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
        } catch (SQLException e){
            System.out.println("Error occurred while getting information from DB.\n" + e);
            throw e;
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
    private void addNewUser () throws SQLException, ClassNotFoundException, IOException {
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
                    try {
                        Registration.addUser(nameField.getText(),surnameField.getText(),loginField.getText(),passwordField.getText().trim());
                        infoStatement.setText("REJESTRACJA UDANA");
                        add.setDisable(true);
                        logIn.setVisible(true);
                    } catch (SQLException e) {
                        System.out.println("Wystapił poblem przy dodawaniu oferty " + e);
                        throw e;
                    }
                }
            }
        }
    }

}

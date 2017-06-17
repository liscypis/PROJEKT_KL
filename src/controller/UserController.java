package controller;

import dao.EditUser;
import dao.OfertyUser;
import dao.Registration;
import dao.ZamowieniaUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import tables.Login;
import tables.Oferty;
import tables.Uzytkownicy;
import tables.Zamowienia;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import static dao.EditUser.checkUserPassword;
import static dao.EditUser.getImieAndNazwisko;
import static dao.OfertyUser.checkLogintoLabel;

/**
 * Created by Wojtek on 13.06.2017.
 */
public class UserController {

    @FXML
    private TableView<Oferty> oferty_user;
    @FXML
    private TableColumn<Oferty, Integer>  id_oferty;
    @FXML
    private TableColumn<Oferty, Integer>  ilosc_miejsc;
    @FXML
    private TableColumn<Oferty, String>  opis;
    @FXML
    private TableColumn<Oferty, Double> cena;
    @FXML
    private TableColumn<Oferty, Date> data_pocz;
    @FXML
    private TableColumn<Oferty, Date> data_konc;

    @FXML
    private TableView zamowienia_user;
    @FXML
    private TableColumn<Zamowienia, String>  opis_zam;
    @FXML
    private TableColumn<Zamowienia, String>  wplata;
    @FXML
    private TableColumn<Zamowienia, String>  ubezpieczenie;

    @FXML
    private ChoiceBox ubezpieczenieCB;
    @FXML
    ObservableList<String> ubezpieczenieCBl = FXCollections.observableArrayList("Tak","Nie");
    @FXML
    private TextField cenaWycieczki;
    @FXML
    private TextField idOfertyField;
    @FXML
    private TextField miejscaField;
    @FXML
    private TextField idUzytkownikaField;

    // do wyswietlania loginu
    @FXML
    private Label loginUz;
    private Login log;

    // do edycji
    @FXML
    private TextField nameEdit;
    @FXML
    private TextField newNameEdit;
    @FXML
    private TextField surnameEdit;
    @FXML
    private TextField newSurameEdit;
    @FXML
    private TextField loginEdit;
    @FXML
    private TextField newLoginEdit;
    @FXML
    private PasswordField passwordEdit;
    @FXML
    private PasswordField newPassworEdit;
    @FXML
    private PasswordField newPassworRepeatEdit;
    @FXML
    private Label statement;
    private Uzytkownicy uz;


    @FXML
    private void initialize () throws SQLException, ClassNotFoundException {
        id_oferty.setCellValueFactory(cellData -> cellData.getValue().id_ofertyProperty().asObject());
        ilosc_miejsc.setCellValueFactory(cellData -> cellData.getValue().ilosc_miejscProperty().asObject());
        opis.setCellValueFactory(cellData -> cellData.getValue().opisProperty());
        cena.setCellValueFactory(cellData -> cellData.getValue().cenaProperty().asObject());
        data_pocz.setCellValueFactory(cellData -> cellData.getValue().data_poczProperty());
        data_konc.setCellValueFactory(cellData -> cellData.getValue().data_koncProperty());
        searchOfertyUs();
        opis_zam.setCellValueFactory(cellData -> cellData.getValue().opis_zamProperty());
        wplata.setCellValueFactory(cellData -> cellData.getValue().wplataProperty());
        ubezpieczenie.setCellValueFactory(cellData -> cellData.getValue().ubezpieczenieProperty());

        // do wyswietlania loginu
        log = checkLogintoLabel(Login.getId());
        loginUz.setText(log.getLogin());
        loginEdit.setText(log.getLogin());
        ubezpieczenieCB.setValue("Tak");
        ubezpieczenieCB.setItems(ubezpieczenieCBl);
        idUzytkownikaField.setText(String.valueOf(Login.getId()));
        searchZamowienia();

        // do uzupełniania edycji
        uz = getImieAndNazwisko(Integer.valueOf(idUzytkownikaField.getText()));
        nameEdit.setText(uz.getImie());
        surnameEdit.setText(uz.getNazwisko());

    }
    //*************************************
    // Szuka i Dodaje oferty do tabview
    //*************************************
    @FXML
    private void searchOfertyUs() throws SQLException, ClassNotFoundException {
        try {
            //Get all information
            ObservableList<Oferty> oferty = OfertyUser.searchOfertyUs();
            //Populate TableView
            populateOfertyUs(oferty);
        } catch (SQLException e){
            System.out.println("Error occurred while getting information from DB.\n" + e);
            throw e;
        }
    }

    //*************************************
    // Dodaje oferty do tabview
    //*************************************
    @FXML
    private void populateOfertyUs (ObservableList<Oferty> oferty)  {
        //Set items to the oferty_user
        oferty_user.setItems(oferty);
    }

    //*************************************
    // Szuka i Dodaje zamowienia do tabview
    //*************************************
    @FXML
    private void searchZamowienia() throws SQLException, ClassNotFoundException {
        try {
            //Get all information
            ObservableList<Zamowienia> zam = ZamowieniaUser.searchZamowienia(Integer.valueOf(idUzytkownikaField.getText()));
            //Populate TableView
            populateZamowienia(zam);
        } catch (SQLException e){
            System.out.println("Error occurred while getting information from DB.\n" + e);
            throw e;
        }
    }

    //*************************************
    // Dodaje zamowienia do tabview
    //*************************************
    @FXML
    private void populateZamowienia (ObservableList<Zamowienia> zam)  {
        //Set items to the oferty_user
        zamowienia_user.setItems(zam);
    }
    //*************************************
    // Uzupelnia textField'y po kliknieciu na element w tabview
    //*************************************
    @FXML
    private void getCenaOnClick(){
        oferty_user.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            cenaWycieczki.setText(String.valueOf(newValue.getCena()));
            idOfertyField.setText(String.valueOf(newValue.getId_oferty()));
            miejscaField.setText(String.valueOf(newValue.getIlosc_miejsc()));
        });
    }
    //*************************************
    // Dodaje zamownienie i dekrementuje ilosc mniejsc w tab oferty dajen oferty
    //*************************************
    @FXML
    private void addZamowienie () throws SQLException, ClassNotFoundException {
        try {
            OfertyUser.addZam(Integer.valueOf(idUzytkownikaField.getText()),Integer.valueOf(idOfertyField.getText()),String.valueOf(ubezpieczenieCB.getValue()));
            System.out.println("Zamoienie zlozone! \n");
            OfertyUser.decreaseIloscMiejsc(Integer.valueOf(miejscaField.getText()),Integer.valueOf(idOfertyField.getText()));
            searchOfertyUs();
            searchZamowienia();
        } catch (SQLException e) {
            System.out.println("Wystapił poblem przy dodawaniu oferty " + e);
            throw e;
        }
    }
    @FXML
    private void editUserName () throws SQLException, ClassNotFoundException {
        if(newNameEdit.getText().trim().equals("")) {
            statement.setText("NIE PODANO IMIENIA");
        }
        else {
            try {
                EditUser.updateName(Integer.valueOf(idUzytkownikaField.getText()) ,newNameEdit.getText().trim());
                statement.setText("IMIE ZOSTAłO ZMIENIONE");
                nameEdit.setText(newNameEdit.getText());
                newNameEdit.setText(null);
            } catch (SQLException e) {
                System.out.println("Wystapił poblem przy aktualizacji imienia " + e);
                throw e;
            }
        }
    }
    @FXML
    private void editUserSurname () throws SQLException, ClassNotFoundException {
        if(newSurameEdit.getText().trim().equals("")) {
            statement.setText("NIE PODANO NAZWISKA");
        }
        else {
            try {
                EditUser.updateSurname(Integer.valueOf(idUzytkownikaField.getText()) ,newSurameEdit.getText().trim());
                statement.setText("NAZWISKO ZOSTAłO ZMIENIONE");
                surnameEdit.setText(newSurameEdit.getText());
                newSurameEdit.setText(null);
            } catch (SQLException e) {
                System.out.println("Wystapił poblem przy aktualizacji nazwiska " + e);
                throw e;
            }
        }
    }

    @FXML
    private void editUserLogin () throws SQLException, ClassNotFoundException, IOException {
        if(checkLogin() == false){
        }
        else {
            try {
                EditUser.updateLogin(Integer.valueOf(idUzytkownikaField.getText()) ,newLoginEdit.getText());
                statement.setText("LOGIN ZOSTAł ZMIENIONY");
                loginEdit.setText(newLoginEdit.getText());
                newLoginEdit.setText(null);
            } catch (SQLException e) {
                System.out.println("Wystapił poblem przy aktualizacji loginu " + e);
                throw e;
            }
        }
    }
    @FXML
    private void editUserPassword () throws SQLException, ClassNotFoundException {
        Login lg = checkUserPassword(Integer.valueOf(idUzytkownikaField.getText()),passwordEdit.getText());
        if(lg == null) {
            statement.setText("PODANO BLEDNE HASLO!");
        }
        else{
            if(newPassworEdit.getText().equals("") || newPassworRepeatEdit.getText().equals("")) {
                statement.setText("PODAJ NOWE HASłA");
            }
            else {
                if(checkPassword() == false) {
                    statement.setText("PODALES DWA ROZNE HASLA");
                }
                else {
                    try {
                        EditUser.updatePassword(Integer.valueOf(idUzytkownikaField.getText()) ,newPassworEdit.getText());
                        statement.setText("HASLO ZOSTAłO ZMIENIONE");
                        passwordEdit.setText(newSurameEdit.getText());
                        newPassworEdit.setText(null);
                        newPassworRepeatEdit.setText(null);
                    } catch (SQLException e) {
                        System.out.println("Wystapił poblem przy aktualizacji hasla " + e);
                        throw e;
                    }
                }
            }
        }
    }
    private boolean checkLogin() throws SQLException, ClassNotFoundException, IOException {
        try {
            //Get all information
            Login lg = Registration.checkLogin(newLoginEdit.getText());
            if(lg == null) {
                if(newLoginEdit.getText().trim().equals("")) {
                    statement.setText("NIE PODANO LOGINU ");
                    return false;
                }else{
                    return true;
                }
            }
            else {
                statement.setText("LOGIN ZAJĘTY, WYBIERZ INNY");
                return false;
            }
        } catch (SQLException e){
            System.out.println("Error occurred while getting information from DB.\n" + e);
            throw e;
        }
    }
    private boolean checkPassword() {
        if(newPassworEdit.getText().trim().equals(newPassworRepeatEdit.getText().trim())){
            return true;
        }
        else return false;
    }

}

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
import java.util.ArrayList;
import java.util.Date;

import static dao.EditUser.checkUserPassword;
import static dao.EditUser.getImieAndNazwisko;
import static sample.ClientSocket.connectToSerwer;

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
    private void initialize () throws SQLException, ClassNotFoundException, IOException {
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
        log = (Login) connectToSerwer("Uzytkownik","SprLogin",Login.getId());
        loginUz.setText(log.getLogin());
        loginEdit.setText(log.getLogin());
        ubezpieczenieCB.setValue("Tak");
        ubezpieczenieCB.setItems(ubezpieczenieCBl);
        idUzytkownikaField.setText(String.valueOf(Login.getId()));
        searchZamowienia();

        // do uzupełniania edycji
        Uzytkownicy uzytkownicy = new Uzytkownicy();
        uzytkownicy.setId_uzytkownika(Integer.valueOf(idUzytkownikaField.getText()));
        uz = (Uzytkownicy) connectToSerwer("Pobierz","ImieNazwisko",uzytkownicy);
        nameEdit.setText(uz.getImie());
        surnameEdit.setText(uz.getNazwisko());

    }
    //*************************************
    // Szuka i Dodaje oferty do tabview
    //*************************************
    @FXML
    private void searchOfertyUs() throws ClassNotFoundException {
        try {
            //Get all information
            ObservableList<Oferty> oferty = FXCollections.observableArrayList((ArrayList<Oferty>) connectToSerwer("Uzytkownicy","Oferty",null));
            //Populate TableView
            populateOfertyUs(oferty);
        } catch (IOException e) {
            e.printStackTrace();
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
            ObservableList<Zamowienia> zam = FXCollections.observableArrayList((ArrayList<Zamowienia>) connectToSerwer("Zamowienia","Usera",Integer.valueOf(idUzytkownikaField.getText())));
            //Populate TableView
            populateZamowienia(zam);
        } catch (IOException e) {
            e.printStackTrace();
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
    private void addZamowienie () throws  ClassNotFoundException {
        try {
            Uzytkownicy uz = new Uzytkownicy();
            Oferty ofe = new Oferty();

            uz.setId_uzytkownika(Integer.valueOf(idUzytkownikaField.getText()));
            uz.setId_wycieczki(Integer.valueOf(idOfertyField.getText()));
            uz.setUbezpieczenie(String.valueOf(ubezpieczenieCB.getValue()));
            connectToSerwer("Kup","Oferte",uz);
            System.out.println("Zamoienie zlozone! \n");

            ofe.setIlosc_miejsc(Integer.valueOf(miejscaField.getText()));
            ofe.setId_oferty(Integer.valueOf(idOfertyField.getText()));
            connectToSerwer("Odejmij","Oferte",ofe);
            searchOfertyUs();
            searchZamowienia();
        } catch (SQLException e) {
            System.out.println("Wystapił poblem przy dodawaniu oferty " + e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void editUserName () throws ClassNotFoundException {
        if(newNameEdit.getText().equals(null)) {
            statement.setText("NIE PODANO IMIENIA");
        }
        else {
            try {
                Login lg = new Login();
                lg.setId_uz(Integer.valueOf(idUzytkownikaField.getText()));
                connectToSerwer("updateName",newNameEdit.getText().trim(),lg);
                statement.setText("IMIE ZOSTAłO ZMIENIONE");
                nameEdit.setText(newNameEdit.getText());
                newNameEdit.setText(null);
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void editUserSurname () throws ClassNotFoundException {
        if(newSurameEdit.getText().trim().equals("")) {
            statement.setText("NIE PODANO NAZWISKA");
        }
        else {
            try {
                Login lg = new Login();
                lg.setId_uz(Integer.valueOf(idUzytkownikaField.getText()));
                connectToSerwer("updateSurname",newSurameEdit.getText().trim(),lg);
                statement.setText("NAZWISKO ZOSTAłO ZMIENIONE");
                surnameEdit.setText(newSurameEdit.getText());
                newSurameEdit.setText(null);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void editUserLogin () throws SQLException, ClassNotFoundException, IOException {
        if(checkLogin() == false){
        }
        else {
            Login lg = new Login();
            lg.setId_uz(Integer.valueOf(idUzytkownikaField.getText()));
            lg.setLogin(newLoginEdit.getText());
            connectToSerwer("Update","Login",lg);
            statement.setText("LOGIN ZOSTAł ZMIENIONY");
            loginEdit.setText(newLoginEdit.getText());
            newLoginEdit.setText(null);
        }
    }
    @FXML
    private void editUserPassword () throws SQLException, ClassNotFoundException, IOException {
        Login login = new Login();
        login.setId_uz(Integer.valueOf(idUzytkownikaField.getText()));
        login.setHaslo(passwordEdit.getText());
        Login lg = (Login) connectToSerwer("Sprawdz","Haslo",login);
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
                        Login log = new Login();
                        log.setId_uz(Integer.valueOf(idUzytkownikaField.getText()));
                        log.setHaslo(newPassworEdit.getText());
                        connectToSerwer("Update","Password",log);
                        statement.setText("HASLO ZOSTAłO ZMIENIONE");
                        passwordEdit.setText(newSurameEdit.getText());
                        newPassworEdit.setText(null);
                        newPassworRepeatEdit.setText(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private boolean checkLogin() throws ClassNotFoundException, IOException {
        //Get all information
        Login login = new Login();
        login.setLogin(newLoginEdit.getText());
        Login lg = (Login) connectToSerwer("Wolny","Login",login);
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
    }
    private boolean checkPassword() {
        if(newPassworEdit.getText().trim().equals(newPassworRepeatEdit.getText().trim())){
            return true;
        }
        else return false;
    }

}

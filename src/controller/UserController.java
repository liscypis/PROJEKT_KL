package controller;

import dao.OfertyUser;
import dao.ZamowieniaUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import tables.Login;
import tables.Oferty;
import tables.Zamowienia;


import java.sql.SQLException;
import java.util.Date;

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
        ubezpieczenieCB.setValue("Tak");
        ubezpieczenieCB.setItems(ubezpieczenieCBl);
        idUzytkownikaField.setText(String.valueOf(Login.getId()));
        searchZamowienia();

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
    private void addZamowienie (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
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

}

package controller;

import dao.OfertyAdmin;
import dao.UzytkownicyAdmin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import tables.Oferty;
import tables.Uzytkownicy;
import java.sql.SQLException;
import java.util.Date;

import static dao.OfertyAdmin.toSqlDate;


/**
 * Created by Wojtek on 09.06.2017.
 */
public class AdminController {


    // populate oferty
    @FXML
    private TableView<Oferty> oferty_admin;
    @FXML
    private TableColumn<Oferty, Integer> id_oferty;
    @FXML
    private TableColumn<Oferty, String>  opis;
    @FXML
    private TableColumn<Oferty, Double> cena;
    @FXML
    private TableColumn<Oferty, Date> data_pocz;
    @FXML
    private TableColumn<Oferty, Date> data_konc;
    @FXML
    private TableColumn<Oferty, Integer> ilosc_miejsc;

    // populate uzytkownicy
    @FXML
    private TableView<Uzytkownicy> uzytkownicy_admin;
    @FXML
    private TableColumn<Uzytkownicy, Integer> ID_ZAMOWIENIA;
    @FXML
    private TableColumn<Uzytkownicy, Integer> ID_WYCIECZKI;
    @FXML
    private TableColumn<Uzytkownicy, Integer> ID_UZYTKOWNIKA;
    @FXML
    private TableColumn<Uzytkownicy, String > NAZWISKO;
    @FXML
    private TableColumn<Uzytkownicy, String> IMIE;
    @FXML
    private TableColumn<Uzytkownicy, String > UBEZPIECZENIE;
    @FXML
    private TableColumn<Uzytkownicy, String > WPLATA;

    // dodawanie danych
    @FXML
    private TextField opisField;
    @FXML
    private TextField cenaField;
    @FXML
    private TextField dataPoczField;
    @FXML
    private TextField dataKoncField;
    @FXML
    private TextField iloscField;

    // edycja danych
    @FXML
    private TextField idOfertyEdit;
    @FXML
    private TextField opisFieldEdit;
    @FXML
    private TextField dataPoczFieldEdit;
    @FXML
    private TextField dataKoncFieldEdit;
    @FXML
    private TextField cenaFieldEdit;

    @FXML
    private ChoiceBox wplata;
    @FXML
    private TextField idZamowieniaEdit;
    @FXML
    ObservableList<String> wplatal = FXCollections.observableArrayList("Tak","Nie");

    //inicjalizacja
    @FXML
    private void initialize () throws SQLException, ClassNotFoundException {
        id_oferty.setCellValueFactory(cellData -> cellData.getValue().id_ofertyProperty().asObject());
        opis.setCellValueFactory(cellData -> cellData.getValue().opisProperty());
        cena.setCellValueFactory(cellData -> cellData.getValue().cenaProperty().asObject());
        data_pocz.setCellValueFactory(cellData -> cellData.getValue().data_poczProperty());
        data_konc.setCellValueFactory(cellData -> cellData.getValue().data_koncProperty());
        ilosc_miejsc.setCellValueFactory(cellData -> cellData.getValue().ilosc_miejscProperty().asObject());
        searchOferty();
        ID_ZAMOWIENIA.setCellValueFactory(cellData -> cellData.getValue().id_zamowieniaProperty().asObject());
        ID_WYCIECZKI.setCellValueFactory(cellData -> cellData.getValue().id_wycieczkiProperty().asObject());
        ID_UZYTKOWNIKA.setCellValueFactory(cellData -> cellData.getValue().id_uzytkownikaProperty().asObject());
        NAZWISKO.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());
        IMIE.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        UBEZPIECZENIE.setCellValueFactory(cellData -> cellData.getValue().ubezpieczenieProperty());
        WPLATA.setCellValueFactory(cellData -> cellData.getValue().wplataProperty());
        searchUzytkownicy();
        wplata.setValue("Tak");
        wplata.setItems(wplatal);

    }
    //*************************************
    //Wyszukuje wszytkie oferty i dodaje je do tabview
    //*************************************
    @FXML
    private void searchOferty() throws SQLException, ClassNotFoundException {
        try {
            //Get all information
            ObservableList<Oferty> oferty = OfertyAdmin.searchOferty();
            //Populate TableView
            populateOferty(oferty);
        } catch (SQLException e){
            System.out.println("Error occurred while getting information from DB.\n" + e);
            throw e;
        }

    }

    //*************************************
    //Dodaje elementy do oferty_admin
    //*************************************
    @FXML
    private void populateOferty (ObservableList<Oferty> oferty)  {
        //Set items to the employeeTable
        oferty_admin.itemsProperty().setValue(oferty);
    }
    //*************************************
    //Wyszukuje wszytkich uz i dodaje do tabview
    //*************************************
    @FXML
    private void searchUzytkownicy() throws SQLException, ClassNotFoundException {
        try {
            //Get all information
            ObservableList<Uzytkownicy> uz = UzytkownicyAdmin.searchUzytkownicy();
            //Populate TableView
            populateUzytkownicy(uz);
        } catch (SQLException e){
            System.out.println("Error occurred while getting information from DB.\n" + e);
            throw e;
        }
    }

    //*************************************
    //dodaje elementy do uzytkoicy_admin
    //*************************************
    @FXML
    private void populateUzytkownicy (ObservableList<Uzytkownicy> uz) throws ClassNotFoundException {
        //Set items to the employeeTable
        uzytkownicy_admin.setItems(uz);
    }
    //*************************************
    //Dodaje oferte
    //*************************************
    @FXML
    private void insertOfe (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            OfertyAdmin.insertoferta(opisField.getText(),Double.valueOf(cenaField.getText()),toSqlDate(dataPoczField.getText()),toSqlDate(dataKoncField.getText()),Integer.valueOf(iloscField.getText()));
            System.out.println("Oferta dodana! \n");
            searchOferty();
        } catch (SQLException e) {
            System.out.println("Wystapił poblem przy dodawaniu oferty " + e);
            throw e;
        }
    }
    //*************************************
    // Aktualizuje oferte
    //*************************************
    @FXML
    private void updateOfe (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            OfertyAdmin.updateOferta(Integer.valueOf(idOfertyEdit.getText()),opisFieldEdit.getText(),Double.valueOf(cenaFieldEdit.getText()),toSqlDate(dataPoczFieldEdit.getText()),toSqlDate(dataKoncFieldEdit.getText()));
            System.out.println("Oferta dodana! \n");
            searchOferty();
        } catch (SQLException e) {
            System.out.println("Wystapił poblem przy dodawaniu oferty " + e);
            throw e;
        }
    }
    //*************************************
    // Aktualicuje wplate
    //*************************************
    @FXML
    private void updateWpl (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            UzytkownicyAdmin.updateWplata(Integer.valueOf(idZamowieniaEdit.getText()),String.valueOf(wplata.getValue()));
            System.out.println("ZMIENONO POLE WPLATA! \n");
            searchUzytkownicy();
        } catch (SQLException e) {
            System.out.println("Wystapił poblem przy dodawaniu oferty " + e);
            throw e;
        }
    }
    //*************************************
    // Uzupelnia textField'y po kliknieciu na element w tabview
    //*************************************
    @FXML
    private void getOfertaOnClick(){
        oferty_admin.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
                idOfertyEdit.setText(String.valueOf(newValue.getId_oferty()));
                opisFieldEdit.setText(newValue.getOpis());
                dataPoczFieldEdit.setText(String.valueOf(newValue.getData_pocz()));
                dataKoncFieldEdit.setText(String.valueOf(newValue.getData_konc()));
                cenaFieldEdit.setText(String.valueOf(newValue.getCena()));
        });
    }
    //*************************************
    // Uzupelnia textField'y po kliknieciu na element w tabview
    //*************************************
    @FXML
    private void getZamowienieOnClick(){
        uzytkownicy_admin.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
                idZamowieniaEdit.setText(String.valueOf(newValue.getId_zamowienia()));
                wplata.setValue(newValue.getWplata());
        });
    }
}

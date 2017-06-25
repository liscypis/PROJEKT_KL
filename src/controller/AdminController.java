package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import tables.Oferty;
import tables.Uzytkownicy;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static sample.ClientSocket.connectToSerwer;


/**
 * Klasa AdminController obsługuje interfejs admina
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
    private Button edytujZapisz;
    @FXML
    private Button edytujWplata;
    @FXML
    private ChoiceBox wplata;
    @FXML
    private TextField idZamowieniaEdit;
    @FXML
    ObservableList<String> wplatal = FXCollections.observableArrayList("Tak","Nie");

    /**
     * Medota wykonuje się podczas inicjalizacji okna
     */
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
        edytujWplata.setDisable(true);
        edytujZapisz.setDisable(true);

    }

    /**
     * Pobiera wyszystkie oferty z bazy, następnie wywołuje funkcję wypełniającą tableview
     */
    @FXML
    private void searchOferty() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Oferty> oferty = FXCollections.observableArrayList((ArrayList<Oferty>) connectToSerwer("Oferty","Admin",null));
            populateOferty(oferty);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Dodaje elementy do tableview oferty_admin
     * @param oferty parametr typu ObservableList
     */
    @FXML
    private void populateOferty (ObservableList<Oferty> oferty)  {
        oferty_admin.itemsProperty().setValue(oferty);
    }

    /**
     * Pobiera wyszystkich Uzytkownikow z bazy, następnie wywołuje funkcję wypełniającą tableview
     */
    @FXML
    private void searchUzytkownicy() throws ClassNotFoundException {
        try {
            ObservableList<Uzytkownicy> uz = FXCollections.observableArrayList((ArrayList<Uzytkownicy>) connectToSerwer("Uzytkownicy","Admin",null));
            populateUzytkownicy(uz);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dodaje elementy do tableview uzytkownicy_admin
     * @param uz parametr typu ObservableList
     */
    @FXML
    private void populateUzytkownicy (ObservableList<Uzytkownicy> uz) {uzytkownicy_admin.setItems(uz);}

    /**
     * Medoda dodaje nową ofertę
     */
    @FXML
    private void insertOfe () throws SQLException, ClassNotFoundException {
        try {
            if(opisField.getText() == null || opisField.getText().trim().isEmpty() || cenaField.getText() == null || cenaField.getText().trim().isEmpty()
                    || dataPoczField.getText() == null || dataPoczField.getText().trim().isEmpty() || dataKoncField.getText() == null ||
                    dataKoncField.getText().trim().isEmpty() || iloscField.getText() == null || iloscField.getText().trim().isEmpty()) {
                System.out.println("Podaj wszystkie pola");
            }
            else {
                Oferty ofe = new Oferty();
                ofe.setOpis(opisField.getText());
                ofe.setCena(Double.valueOf(cenaField.getText()));
                ofe.setData_pocz(toSqlDate(dataPoczField.getText()));
                ofe.setData_konc(toSqlDate(dataKoncField.getText()));
                ofe.setIlosc_miejsc(Integer.valueOf(iloscField.getText()));
                connectToSerwer("Oferty","Dodaj",ofe);
                System.out.println("Oferta dodana! \n");
                searchOferty();
            }

        } catch (SQLException e) {
            System.out.println("Wystapił poblem przy dodawaniu oferty " + e);
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda edutująca ofertę
     */
    @FXML
    private void updateOfe () throws SQLException, ClassNotFoundException {
        try {
            Oferty ofe = new Oferty();
            ofe.setId_oferty(Integer.valueOf(idOfertyEdit.getText()));
            ofe.setOpis(opisFieldEdit.getText());
            ofe.setCena(Double.valueOf(cenaFieldEdit.getText()));
            ofe.setData_pocz(toSqlDate(dataPoczFieldEdit.getText()));
            ofe.setData_konc(toSqlDate(dataKoncFieldEdit.getText()));
            connectToSerwer("Oferty","Edytuj",ofe);
            System.out.println("Oferta zmodyfikowa! \n");
            searchOferty();
        } catch (SQLException e) {
            System.out.println("Wystapił poblem przy dodawaniu oferty " + e);
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda aktualizująca wpłatę
     */
    @FXML
    private void updateWpl () throws ClassNotFoundException {
        try {
            Uzytkownicy uz = new Uzytkownicy();
            uz.setId_zamowienia(Integer.valueOf(idZamowieniaEdit.getText()));
            uz.setWplata(String.valueOf(wplata.getValue()));
            connectToSerwer("Admin","Wpłata",uz);
            System.out.println("ZMIENONO POLE WPLATA! \n");
            searchUzytkownicy();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda pobiera dane oferty po kliknięciu na daną ofertę
     */
    @FXML
    private void getOfertaOnClick(){
        oferty_admin.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
                edytujZapisz.setDisable(false);
                idOfertyEdit.setText(String.valueOf(newValue.getId_oferty()));
                opisFieldEdit.setText(newValue.getOpis());
                dataPoczFieldEdit.setText(String.valueOf(newValue.getData_pocz()));
                dataKoncFieldEdit.setText(String.valueOf(newValue.getData_konc()));
                cenaFieldEdit.setText(String.valueOf(newValue.getCena()));
        });
    }

    /**
     * Metoda pobiera dane wpłaty po kliknięciu za zamówienie
     */
    @FXML
    private void getZamowienieOnClick(){
        uzytkownicy_admin.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
                edytujWplata.setDisable(false);
                idZamowieniaEdit.setText(String.valueOf(newValue.getId_zamowienia()));
                wplata.setValue(newValue.getWplata());
        });
    }

    /**
     * Metoda zamieniająca Stringa na Date
     * @param strDate String do zamiany na datę
     */
    public static Date toSqlDate(String strDate)
    {
        DateFormat dateFrm = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date myDate;
        java.sql.Date sqlDate;
        try
        {
            myDate = dateFrm.parse(strDate);
            sqlDate = new java.sql.Date(myDate.getTime());
        }
        catch (Exception e)
        {
            sqlDate = null;
        }
        return (sqlDate);
    }
}

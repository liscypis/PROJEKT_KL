package controller;

import dao.OfertyUser;
import dao.ZamowieniaUser;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tables.Oferty;
import tables.Zamowienia;


import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Wojtek on 13.06.2017.
 */
public class UserController {
    @FXML
    private TableView oferty_user;
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
    private void initialize () throws SQLException, ClassNotFoundException {
        opis.setCellValueFactory(cellData -> cellData.getValue().opisProperty());
        cena.setCellValueFactory(cellData -> cellData.getValue().cenaProperty().asObject());
        data_pocz.setCellValueFactory(cellData -> cellData.getValue().data_poczProperty());
        data_konc.setCellValueFactory(cellData -> cellData.getValue().data_koncProperty());
        searchOfertyUs();
        opis_zam.setCellValueFactory(cellData -> cellData.getValue().opis_zamProperty());
        wplata.setCellValueFactory(cellData -> cellData.getValue().wplataProperty());
        ubezpieczenie.setCellValueFactory(cellData -> cellData.getValue().ubezpieczenieProperty());
        searchZamowienia();
    }
    //wszytkie oferty
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

    //Populate TableView Oferty_admin
    @FXML
    private void populateOfertyUs (ObservableList<Oferty> oferty)  {
        //Set items to the oferty_user
        oferty_user.setItems(oferty);
    }

    //wszytkie zam
    @FXML
    private void searchZamowienia() throws SQLException, ClassNotFoundException {
        try {
            //Get all information
            ObservableList<Zamowienia> zam = ZamowieniaUser.searchZamowienia();
            //Populate TableView
            populateZamowienia(zam);
        } catch (SQLException e){
            System.out.println("Error occurred while getting information from DB.\n" + e);
            throw e;
        }
    }

    //Populate TableView zam_user
    @FXML
    private void populateZamowienia (ObservableList<Zamowienia> zam)  {
        //Set items to the oferty_user
        zamowienia_user.setItems(zam);
    }

}

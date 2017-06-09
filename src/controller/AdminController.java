package controller;

import dao.OfertyAdmin;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tables.Oferty;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Wojtek on 09.06.2017.
 */
public class AdminController {
    @FXML
    private TableView oferty_admin;
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
    }
    //wszytkie oferty
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

    //Populate TableView
    @FXML
    private void populateOferty (ObservableList<Oferty> oferty)  {
        //Set items to the employeeTable
        oferty_admin.setItems(oferty);
    }
}

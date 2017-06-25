package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import static sample.ClientSocket.connectToSerwer;

/**
 * UserController obsługuje interfejs użytkownika
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
    private TextField szukajOferty;
    @FXML
    private Button kupTeraz;

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
    private PasswordField newPassworEdit;
    @FXML
    private PasswordField newPassworRepeatEdit;
    @FXML
    private Label statement;
    private Uzytkownicy uz;

    /**
     * Medota wykonuje się podczas inicjalizacji okna
     */
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
        kupTeraz.setDisable(true);

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

    /**
     * Pobiera wyszystkie oferty z bazy, następnie wywołuje metode wypełniającą tableview
     */
    @FXML
    private void searchOfertyUs() throws ClassNotFoundException {
        try {
            ObservableList<Oferty> oferty = FXCollections.observableArrayList((ArrayList<Oferty>) connectToSerwer("Uzytkownicy","Oferty",null));
            populateOfertyUs(oferty);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dodaje elementy do tableview oferty_user
     * @param oferty parametr typu ObservableList
     */
    @FXML
    private void populateOfertyUs (ObservableList<Oferty> oferty)  {
        oferty_user.setItems(oferty);
    }

    /**
     * Pobiera wyszystkie zamowienia uzytkownika z bazy, następnie wywołuje metode wypełniającą tableview
     */
    @FXML
    private void searchZamowienia() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Zamowienia> zam = FXCollections.observableArrayList((ArrayList<Zamowienia>) connectToSerwer("Zamowienia","Usera",Integer.valueOf(idUzytkownikaField.getText())));
            populateZamowienia(zam);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Dodaje elementy do tableview zamowienia_user
     * @param zam parametr typu ObservableList
     */
    @FXML
    private void populateZamowienia (ObservableList<Zamowienia> zam)  {
        zamowienia_user.setItems(zam);
    }
    //*************************************
    // Uzupelnia textField'y po kliknieciu na element w tabview
    //*************************************

    /**
     * Metoda pobiera dane oferty po klikniecu na dana ofertę
     */
    @FXML
    private void getCenaOnClick(){
        oferty_user.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            kupTeraz.setDisable(false);
            cenaWycieczki.setText(String.valueOf(newValue.getCena()));
            idOfertyField.setText(String.valueOf(newValue.getId_oferty()));
            miejscaField.setText(String.valueOf(newValue.getIlosc_miejsc()));
        });
    }
    //*************************************
    // Dodaje zamownienie i dekrementuje ilosc mniejsc w tab oferty dajen oferty
    //*************************************

    /**
     * Metoda dodaje zamownienie i zmniejsza ilosc mniejsc w danej ofercie
     */
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

    /**
     * Metoda szuka zamowien które zawierają podany ciąg znaków, następnie wywołuje metode wypełniającą tableview
     */
    @FXML
    private void findOferta() throws SQLException, ClassNotFoundException {
        try {
            if(szukajOferty.getText() == null || szukajOferty.getText().trim().isEmpty()) {
                searchOfertyUs();
            }else {
                Oferty of = new Oferty();
                of.setOpis(szukajOferty.getText());
                ObservableList<Oferty> ofe = FXCollections.observableArrayList((ArrayList<Oferty>) connectToSerwer("Szukaj","Ofert",of));
                populateOfertyUs(ofe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zmienia imie uzytkownika
     */
    @FXML
    private void editUserName () throws ClassNotFoundException {
        try {
            Login lg = new Login();
            lg.setId_uz(Integer.valueOf(idUzytkownikaField.getText()));
            connectToSerwer("updateName",newNameEdit.getText().trim(),lg);
            nameEdit.setText(newNameEdit.getText());
            newNameEdit.setText(null);
            statement.setText("IMIE ZOSTAłO ZMIENIONE");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zmienia nazwisko uzytkownika
     */
    @FXML
    private void editUserSurname () throws ClassNotFoundException {
        try {
            Login lg = new Login();
            lg.setId_uz(Integer.valueOf(idUzytkownikaField.getText()));
            connectToSerwer("updateSurname", newSurameEdit.getText().trim(), lg);
            surnameEdit.setText(newSurameEdit.getText());
            newSurameEdit.setText(null);
            statement.setText("NAZWISKO ZOSTAłO ZMIENIONE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zmienia login uzytkownika
     */
    @FXML
    private void editUserLogin () throws ClassNotFoundException, IOException, InterruptedException {
        if(checkLogin() == false){
        }
        else {
            Login lg = new Login();
            lg.setId_uz(Integer.valueOf(idUzytkownikaField.getText()));
            lg.setLogin(newLoginEdit.getText());
            connectToSerwer("Update","Login",lg);
            loginEdit.setText(newLoginEdit.getText());
            loginUz.setText(newLoginEdit.getText());
            newLoginEdit.setText(null);
            statement.setText("LOGIN ZOSTAł ZMIENIONY");
        }
    }

    /**
     * Metoda zmienia hasło uzytkownika
     */
    @FXML
    private void editUserPassword () throws SQLException, ClassNotFoundException, IOException {
        try {
            if(checkPassword() == false) {
                statement.setText("PODAłES DWA ROZNE HASłA");
            }
            else {
                Login log = new Login();
                log.setId_uz(Integer.valueOf(idUzytkownikaField.getText()));
                log.setHaslo(newPassworEdit.getText());
                connectToSerwer("Update","Password",log);
                newPassworEdit.setText(null);
                newPassworRepeatEdit.setText(null);
                statement.setText("HASłO ZOSTAłO ZMIENIONE");
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda aktualizuje dane uzytkownika
     */
    @FXML
    private void updateDataUser() throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        if(newSurameEdit.getText() == null || newSurameEdit.getText().trim().isEmpty()){
            System.out.println("");
        }else{
            editUserSurname();
        }

        if(newNameEdit.getText() == null || newNameEdit.getText().trim().isEmpty()){
            System.out.println("");
        }else {
            editUserName();
        }
        if(newLoginEdit.getText() == null || newLoginEdit.getText().trim().isEmpty()) {
            System.out.println("");
        } else {
            editUserLogin();
        }

        if(newPassworEdit.getText() == null || newPassworEdit.getText().trim().isEmpty() || newPassworRepeatEdit.getText() == null || newPassworRepeatEdit.getText().trim().isEmpty()) {
            System.out.println("");
        }else {
            editUserPassword();
        }
    }

    /**
     * Metoda sprawdza czy podany login w newLoginEdit jest wolny czy zajęty
     * @return wartość logiczna
     */
    private boolean checkLogin() throws ClassNotFoundException, IOException, InterruptedException {
        Login lg = (Login) connectToSerwer("Wolny","Login",newLoginEdit.getText());
        if(lg == null) {
            return true;
        }
        else {
            statement.setText("LOGIN ZAJETY, WYBIERZ INNY");
            return false;
        }
    }

    /**
     * Metoda sprawdza czy podane hasla są takie same
     * @return wartość logiczna
     */
    private boolean checkPassword() {
        if(newPassworEdit.getText().trim().equals(newPassworRepeatEdit.getText().trim())){
            return true;
        }
        else return false;
    }
}

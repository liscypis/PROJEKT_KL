package sample;


import controller.AdminController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oracle.ConnectToDatabase;


import java.io.IOException;

/*
    Uzytkownik może wybrać jedna ofertę.
    Jak zaznaczy jakąś ofertę to na dole będzie przyciść REZERWUJ
    Oferty powinny mieć termin tzn od kiedy do kiedy trwa wycieczka,
    i powinny mieć też ilość wonych miejsc + trasę wycieczki np KRR - Berlin - Koniec świata.
    W zamówieniach może tylko zobacyzć czy jest zapłacone za wycieczkę

    PANEL ADMINA
    MOŻE ustawić wpłatę tzn. czy jest zapłacone czy nie :D

    LOGOWANIE
    DODAć logowanie :D

*/
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("Biuro podróży nigdy więcej");
        primaryStage.setScene(new Scene(root, 200, 200));
        primaryStage.show();
        ConnectToDatabase.connect();

    }
    @FXML
    public void loadAdminInterface(ActionEvent event)throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();  // zamyka okno logowania
        Parent root = FXMLLoader.load(getClass().getResource("view/admin.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Biuro podróży nigdy więcej");
        stage.setScene(new Scene(root));
        stage.show();



    }

    @FXML
    public void loadUserInterface(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();  // zamyka okno logowania
        Parent root = FXMLLoader.load(getClass().getResource("view/user.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Biuro podróży nigdy więcej");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);


    }
}

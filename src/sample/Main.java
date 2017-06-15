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
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
        primaryStage.setResizable(false);
        ConnectToDatabase.connect();

    }


    public static void main(String[] args) {
        launch(args);


    }
}

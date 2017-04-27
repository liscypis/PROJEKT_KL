package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Biuro podróży nigdy więcej");

        primaryStage.setScene(new Scene(root, 200, 200));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}

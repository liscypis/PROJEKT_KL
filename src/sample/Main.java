package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Klasa Main uruchamia cały interface
 */
public class Main extends Application {
    /**
     * Metoda wyświetla interface logowania
     * @param primaryStage typu Stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("Biuro podróży nigdy więcej");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    /**
     * Metoda startuje cały program
     * @param args typu String[]
     */
    public static void main(String[] args) {
        launch(args);
    }
}

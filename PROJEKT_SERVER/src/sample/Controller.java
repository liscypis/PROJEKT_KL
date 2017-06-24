package sample;

import javafx.fxml.FXML;

import java.io.IOException;

public class Controller {
    @FXML
    private void initialize() {
        System.out.println("dede");
    }
    @FXML
    private void startServer() {
            try {
                Server.startSerwer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("dede");

    }
}

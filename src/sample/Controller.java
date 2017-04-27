package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


public class Controller {

    @FXML
    private Button button;

    @FXML
    public void loadUserInterface(ActionEvent event) throws IOException{
       ((Node)(event.getSource())).getScene().getWindow().hide();  // zamyka okno logowania
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Biuro podróży nigdy więcej");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void loadAdminInterface(ActionEvent event)throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();  // zamyka okno logowania
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Biuro podróży nigdy więcej");
        stage.setScene(new Scene(root));
        stage.show();

    }
}

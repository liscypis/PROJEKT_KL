package controller;


import dao.LoginDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tables.Login;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Wojtek on 15.06.2017.
 */
public class LoginController {
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Label statement;

    @FXML
    private void initialize () throws SQLException, ClassNotFoundException{

    }
    @FXML
    private void checkLog(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        try {
            //Get all information
            Login lg = LoginDAO.checkLoginAndPassword(login.getText(),password.getText());
            if(lg == null) {
                statement.setText("Błędne haslo lub login");
            }
            else {
                if(login.getText().equals("admin") && password.getText().equals("admin"))
                {
                    Login.setId(lg.getId_uz());
                    loadAdminInterface(event);
                }
                else
                {
                    Login.setId(lg.getId_uz());
                    loadUserInterface(event);
                }

            }
        } catch (SQLException e){
            System.out.println("Error occurred while getting information from DB.\n" + e);
            throw e;
        }

    }
    @FXML
    public void loadUserInterface(ActionEvent event) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();  // zamyka okno logowania
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/user.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Biuro podróży nigdy więcej");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
    }
    @FXML
    public void loadAdminInterface(ActionEvent event)throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();  // zamyka okno logowania
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/admin.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Biuro podróży nigdy więcej");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
    }
    @FXML
    public void loadRegistrationInterface(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/registration.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Biuro podróży nigdy więcej");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
    }
    @FXML
    private void test(){

    }

}

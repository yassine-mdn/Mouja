package com.projet.mouja.uiContollers;

import com.projet.mouja.HelloApplication;
import com.projet.mouja.entitiesControllers.CompteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void quiter(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void store(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/store-front.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    public void login(ActionEvent e) throws SQLException, IOException {
        String userName = username.getText();
        String passWord = password.getText();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ResultSet rs = CompteController.login(userName,passWord);
        if (rs.next()){
            if (rs.getBoolean(4)){
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/menu-admin.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                storeFrontController.setUserId(rs.getInt(1));
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/store-front.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
            }
        } else {
            Notifications.create()
                    .owner(stage)
                    .title("Try Again!")
                    .text("wrong username or password")
                    .position(Pos.BOTTOM_CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showWarning();
        }

    }

}

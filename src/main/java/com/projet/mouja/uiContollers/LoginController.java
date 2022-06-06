package com.projet.mouja.uiContollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

import java.sql.ResultSet;


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
    public void login(ActionEvent e) {
        String userName = username.getText();
        String passWord = password.getText();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println("username = " + userName + "\npassword = " + passWord);
        if (!userName.equals("yassine") && !passWord.equals("Yassine")) {
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

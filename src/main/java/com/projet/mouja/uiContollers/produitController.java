package com.projet.mouja.uiContollers;

import com.projet.mouja.entities.ProduitEntity;
import com.projet.mouja.entitiesControllers.CartController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

import java.io.File;
import java.sql.SQLException;
import java.util.Objects;


public class produitController {

    private static int idC = -1;

    @FXML
    private ImageView image;

    @FXML Label ref;

    @FXML
    private Label nom;

    @FXML
    private Label prix;

    @FXML
    private Rating ratting;

    @FXML
    void addToCart(MouseEvent e) throws SQLException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        if (idC == -1){
            Notifications.create()
                    .owner(stage)
                    .title("Erreur!")
                    .text("veillez vous connecter puis reessayer")
                    .position(Pos.BOTTOM_CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showWarning();
        }else {
            System.out.println();
            CartController.addToCart(idC,ref.getText(),Float.parseFloat(prix.getText()));
            Notifications.create()
                    .owner(stage)
                    .title("Success!")
                    .text("Item Added To Cart")
                    .position(Pos.BOTTOM_CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showConfirm();
        }
    }

    public void setdata(ProduitEntity produit){
        Image productImage = new Image(new File(produit.getImagePath()).toURI().toString());
        image.setImage(productImage);

        ref.setText(produit.getReference());
        nom.setText(produit.getNom());
        prix.setText(String.valueOf((produit.getPrixUnitaire())));
        ratting.setRating(produit.getRating());
    }

    public static void setidC(int id) {
        idC = id;
    }
}


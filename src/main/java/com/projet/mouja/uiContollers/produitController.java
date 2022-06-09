package com.projet.mouja.uiContollers;

import com.projet.mouja.entities.ProduitEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.Objects;


public class produitController {

    @FXML
    private ImageView image;

    @FXML Label ref;

    @FXML
    private Label nom;

    @FXML
    private Label prix;

    @FXML
    void addToCart(MouseEvent event) {

    }

    public void setdata(ProduitEntity produit){
        Image productImage = new Image(new File(produit.getImagePath()).toURI().toString());
        image.setImage(productImage);

        ref.setText(produit.getReference());
        nom.setText(produit.getNom());
        prix.setText(String.valueOf(produit.getPrixUnitaire())+" MAD");

    }
}

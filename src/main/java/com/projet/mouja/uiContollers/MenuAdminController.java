package com.projet.mouja.uiContollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MenuAdminController {

    @FXML
    private AnchorPane fournisseurSide, compteSide, produitSide, commandeSide;

    @FXML
    public void fournisseurButton(ActionEvent e){
        fournisseurSide.toFront();
    }

    @FXML
    public void produitButton(ActionEvent e){
        produitSide.toFront();
    }

    @FXML
    public void commandeButton(ActionEvent e){
        commandeSide.toFront();
    }

    @FXML
    public void compteButton(ActionEvent e){
        compteSide.toFront();
    }
}

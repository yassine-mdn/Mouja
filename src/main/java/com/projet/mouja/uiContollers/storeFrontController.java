package com.projet.mouja.uiContollers;

import com.projet.mouja.HelloApplication;
import com.projet.mouja.entities.ProduitEntity;
import com.projet.mouja.entitiesControllers.ProduitController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class storeFrontController implements Initializable {

    @FXML
    private AnchorPane homePane;

    @FXML
    private GridPane productPane;

    private static int userId = -1;
    private ArrayList<ProduitEntity> produits = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initProduits();

        int cols = 0;
        int rows = 0;
        try {
            for (ProduitEntity produit : produits) {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/produit.fxml"));
                VBox vBox = fxmlLoader.load();
                produitController produitController = fxmlLoader.getController();
                produitController.setdata(produit);

                if (cols == 4){
                    cols = 0;
                    ++rows;
                }

                productPane.add(vBox,cols++,rows);
                GridPane.setMargin(vBox, new Insets(20));


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void backToHome(MouseEvent event) {

    }

    @FXML
    void showCart(MouseEvent event) {

    }

    @FXML
    void showFavorites(MouseEvent event) {

    }

    @FXML
    void userSettings(MouseEvent event) {

    }


    private void initProduits() {
        ResultSet rs = ProduitController.getAll();

        try {
            while (rs.next()) {
                ProduitEntity produit = new ProduitEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDouble(5), rs.getInt(6), rs.getInt(7), rs.getInt(8),rs.getString(9));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void setUserId(int id) {
        userId = id;
    }
}

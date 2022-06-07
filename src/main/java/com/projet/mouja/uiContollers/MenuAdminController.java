package com.projet.mouja.uiContollers;

import com.projet.mouja.HelloApplication;
import com.projet.mouja.entities.FournisseurEntity;
import com.projet.mouja.entities.ProduitEntity;
import com.projet.mouja.entitiesControllers.FournisseurController;
import com.projet.mouja.entitiesControllers.ProduitController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class MenuAdminController implements Initializable {

    @FXML
    private AnchorPane fournisseurSide, compteSide, produitSide, commandeSide;

    @FXML
    private AnchorPane fournisseurListe, compteListe, produitListe, commandeListe;

    @FXML
    private AnchorPane shadowPane, ajoutProduitPane, ajoutComptePane, ajoutFournisseurPane;

    @FXML
    private ToggleButton settingsButton;

    @FXML
    private TextField filtreFournisseur;
    @FXML
    private TableView<FournisseurEntity> tableFournisseur;
    @FXML
    private TableColumn<FournisseurEntity, String> nomF;
    @FXML
    private TableColumn<FournisseurEntity, String> adresseF;
    @FXML
    private TableColumn<FournisseurEntity, String> paysF;
    @FXML
    private TableColumn<FournisseurEntity, String> villeF;
    @FXML
    private TableColumn<FournisseurEntity, String> emailF;
    @FXML
    private TableColumn<FournisseurEntity, String> numTelF;

    @FXML
    private TextField filtreProduit;
    @FXML
    private TableView<ProduitEntity> tableProduit;
    @FXML
    private TableColumn<ProduitEntity,String> refP;
    @FXML
    private TableColumn<ProduitEntity,String> nomP;
    @FXML
    private TableColumn<ProduitEntity,String> catP;
    @FXML
    private TableColumn<ProduitEntity,String> prixP;
    @FXML
    private TableColumn<ProduitEntity,String> stockP;
    @FXML
    private TableColumn<ProduitEntity,String > qteVendueP;
    @FXML
    private TableColumn<ProduitEntity,String> fournisseurP;



    private ObservableList<FournisseurEntity> fournisseurObservableList = FXCollections.observableArrayList();
    private ObservableList<ProduitEntity> produitObservableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        generateTableFournisseur();
        generateTableProduit();

    }


    @FXML
    public void fournisseurButton(ActionEvent e) {
        fournisseurSide.toFront();
    }

    @FXML
    public void produitButton(ActionEvent e) {
        produitSide.toFront();
    }

    @FXML
    public void commandeButton(ActionEvent e) {
        commandeSide.toFront();
    }

    @FXML
    public void compteButton(ActionEvent e) {
        compteSide.toFront();
    }

    @FXML
    public void afficherListeProduit(ActionEvent e) {
        produitListe.toFront();
    }

    @FXML
    public void afficherListeCommande(ActionEvent e) {
        commandeListe.toFront();
    }

    @FXML
    public void afficherListeFournisseur(ActionEvent e) {
        fournisseurListe.toFront();
    }

    @FXML
    public void afficherListeCompte(ActionEvent e) {
        compteListe.toFront();
    }

    @FXML
    public void ajouterFournisseurClicked(ActionEvent e) {
        ajoutFournisseurPane.setVisible(true);
        ajoutFournisseurPane.toFront();
        shadowPane.toFront();
    }

    @FXML
    public void ajouterProduitClicked(ActionEvent e) {
        ajoutProduitPane.setVisible(true);
        ajoutProduitPane.toFront();
        shadowPane.toFront();
    }

    @FXML
    public void ajouterCompteClicked(ActionEvent e) {
        ajoutFournisseurPane.setVisible(false);
        ajoutProduitPane.setVisible(false);
        ajoutComptePane.toFront();
        shadowPane.toFront();
    }


    @FXML
    public void annulerAjout(ActionEvent e) {
        shadowPane.toBack();
    }

    @FXML
    public void filtrageFournisseur(KeyEvent k) {

        FilteredList<FournisseurEntity> filteredFournisseur = new FilteredList<>(fournisseurObservableList, b -> true);

        filteredFournisseur.setPredicate(fournisseurEntity -> {

            if (filtreFournisseur.getText().isEmpty() || filtreFournisseur.getText().isBlank())
                return true;

            String searchKeyword = filtreFournisseur.getText().toLowerCase();

            if (fournisseurEntity.getNom().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (fournisseurEntity.getAdresse().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (fournisseurEntity.getPays().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (fournisseurEntity.getVille().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (fournisseurEntity.getEmail().toLowerCase().contains(searchKeyword)) {
                    return true;
            } else if (fournisseurEntity.getNumTel().toLowerCase().contains(searchKeyword)){
                return true;
            }else{
                return false;
            }
        });


        SortedList<FournisseurEntity> sortedFournisseur = new SortedList<>(filteredFournisseur);
        sortedFournisseur.comparatorProperty().bind(tableFournisseur.comparatorProperty());

        tableFournisseur.setItems(sortedFournisseur);
        tableFournisseur.refresh();
    }

    @FXML
    public void filtrageProduit(KeyEvent k){

        FilteredList<ProduitEntity> filteredProduit = new FilteredList<>(produitObservableList, b -> true);

        filteredProduit.setPredicate(produitEntity -> {

            if (filtreProduit.getText().isEmpty() || filtreProduit.getText().isBlank())
                return true;

            String searchKeyword = filtreProduit.getText().toLowerCase();

            if (produitEntity.getReference().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (produitEntity.getNom().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (produitEntity.getCategory().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (produitEntity.getPrixUnitaire().toString().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (produitEntity.getStock().toString().toLowerCase().contains(searchKeyword)) {
                    return true;
            } else if (produitEntity.getQteVendue().toString().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (produitEntity.getNomf().toLowerCase().contains(searchKeyword)) {
                return true;
            }else
                return false;
        });


        SortedList<ProduitEntity> sortedProduit = new SortedList<>(filteredProduit);
        sortedProduit.comparatorProperty().bind(tableProduit.comparatorProperty());

        tableProduit.setItems(sortedProduit);
        tableProduit.refresh();
    }

    @FXML
    public void addFournisseur(ActionEvent e) {

    }

    @FXML
    public void settingsButton(ActionEvent e) {
        Hyperlink logout = new Hyperlink("Log Out");
        Hyperlink quit = new Hyperlink("Quit");
        logout.getStylesheets().add("com/projet/mouja/css/main.css");
        logout.setOnAction(ActionEvent -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/login.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            stage.setScene(scene);
        });
        quit.setOnAction(ActionEvent -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();
        });
        VBox vBox = new VBox(logout, quit);
        vBox.setMinSize(150, 50);
        PopOver popOver = new PopOver(vBox);
        popOver.show(settingsButton);
    }


    public void generateTableFournisseur(){
        nomF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        adresseF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAdresse()));
        paysF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPays()));
        villeF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getVille()));
        emailF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));
        numTelF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNumTel()));
        try {
            ResultSet rs = FournisseurController.getAll();
            while (rs.next()) {
                fournisseurObservableList.add(new FournisseurEntity(rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7)));
            }

            tableFournisseur.setItems(fournisseurObservableList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateTableProduit(){
        refP.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getReference()));
        nomP.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        catP.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getCategory()));
        prixP.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getPrixUnitaire().toString()));
        stockP.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getStock().toString()));
        qteVendueP.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getQteVendue().toString()));
        fournisseurP.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getNomf()));

        try {
            ResultSet rs = ProduitController.getTable();
            while (rs.next()) {
                ProduitEntity produit = new ProduitEntity(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getDouble(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
                produit.setNomf(rs.getString(8));
                produitObservableList.add(produit);
            }

            tableProduit.setItems(produitObservableList);
            tableProduit.refresh();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

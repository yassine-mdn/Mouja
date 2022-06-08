package com.projet.mouja.uiContollers;

import com.projet.mouja.HelloApplication;
import com.projet.mouja.entities.*;
import com.projet.mouja.entitiesControllers.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.SearchableComboBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MenuAdminController implements Initializable {

    @FXML
    private AnchorPane fournisseurSide, compteSide, produitSide, commandeSide;

    @FXML
    private AnchorPane fournisseurListe, compteListe, produitListe, commandeListe, clientListe;

    @FXML Button modifierFournisseur,modifierProduit;

    @FXML
    private AnchorPane shadowPane, ajoutProduitPane, ajoutComptePane, ajoutFournisseurPane,modifierFournisseurPane,modifierProduitPane,modifierComptePane;
    @FXML
    private TextField fieldNomF, fieldAddF, fieldPayF, fieldVilleF, fieldEmailF, fieldNumTelF;
    @FXML
    private TextField fieldRefP,fieldNomP,fieldCatP,fieldPrixP,fieldStockP;
    @FXML
    private SearchableComboBox<String> comboBoxF;
    @FXML
    private  TextField fieldIdentifiantC;
    @FXML
    private PasswordField fieldPwdC;
    @FXML
    private TextField fieldNomF1, fieldAddF1, fieldPayF1, fieldVilleF1, fieldEmailF1, fieldNumTelF1;
    @FXML
    private TextField fieldRefP1,fieldNomP1,fieldCatP1,fieldPrixP1,fieldStockP1;
    @FXML
    private SearchableComboBox<String> comboBoxF1;

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
    private TableColumn<ProduitEntity, String> refP;
    @FXML
    private TableColumn<ProduitEntity, String> nomP;
    @FXML
    private TableColumn<ProduitEntity, String> catP;
    @FXML
    private TableColumn<ProduitEntity, String> prixP;
    @FXML
    private TableColumn<ProduitEntity, String> stockP;
    @FXML
    private TableColumn<ProduitEntity, String> qteVendueP;
    @FXML
    private TableColumn<ProduitEntity, String> fournisseurP;


    @FXML
    private TextField filtreCommande;
    @FXML
    private TableView<CommandeEntity> tableCommande;
    @FXML
    private TableColumn<CommandeEntity, String> idCMD;
    @FXML
    private TableColumn<CommandeEntity, String> clientCMD;
    @FXML
    private TableColumn<CommandeEntity, String> produitCMD;
    @FXML
    private TableColumn<CommandeEntity, String> qteCMD;
    @FXML
    private TableColumn<CommandeEntity, String> addresseCMD;
    @FXML
    private TableColumn<CommandeEntity, String> dateCMD;

    @FXML
    private TextField filtreCompte;
    @FXML
    private TableView<CompteEntity> tableCompte;
    @FXML
    private TableColumn<CompteEntity,String> idC;
    @FXML
    private TableColumn<CompteEntity,String> identifiantC;
    @FXML
    private TableColumn<CompteEntity,String> statutC;

    @FXML
    private TextField filtreClient;
    @FXML
    private TableView<ClientEntity> tableClient;
    @FXML
    private TableColumn<ClientEntity,String> idClient;
    @FXML
    private TableColumn<ClientEntity,String> nomClient;
    @FXML
    private TableColumn<ClientEntity,String> prenomClient;
    @FXML
    private TableColumn<ClientEntity,String> addClient;
    @FXML
    private TableColumn<ClientEntity,String> villeClient;
    @FXML
    private TableColumn<ClientEntity,String> emailClient;
    @FXML
    private TableColumn<ClientEntity,String> numTelClient;


    private ObservableList<FournisseurEntity> fournisseurObservableList = FXCollections.observableArrayList();
    private ObservableList<ProduitEntity> produitObservableList = FXCollections.observableArrayList();
    private ObservableList<CommandeEntity> commandeObservableList = FXCollections.observableArrayList();
    private ObservableList<CompteEntity> compteObservableList = FXCollections.observableArrayList();
    private ObservableList<ClientEntity> clientObservableList = FXCollections.observableArrayList();
    
    private HashMap<String,Integer> fournisseurMap = new HashMap<>();

    private int selctedFournissuer;
    private int selectedProduit;
    private int selctedCompte;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        generateTableFournisseur();
        initComboBoxFournisseur();
        generateTableProduit();
        generateTableCommande();
        generateTableCompte();
        generateTableClient();

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
        modifierFournisseurPane.setVisible(false);
        ajoutComptePane.toFront();
        shadowPane.toFront();
    }


    @FXML
    public void annulerAjout(ActionEvent e) {
        shadowPane.toBack();
    }

    @FXML
    public void escape(KeyEvent k){
        if (k.getCode() == KeyCode.ESCAPE)
            shadowPane.toBack();
    }

    @FXML
    public void compteClientsClicked(ActionEvent e){
        clientListe.toFront();
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
            } else if (fournisseurEntity.getNumTel().toLowerCase().contains(searchKeyword)) {
                return true;
            } else {
                return false;
            }
        });


        SortedList<FournisseurEntity> sortedFournisseur = new SortedList<>(filteredFournisseur);
        sortedFournisseur.comparatorProperty().bind(tableFournisseur.comparatorProperty());

        tableFournisseur.setItems(sortedFournisseur);
        tableFournisseur.refresh();
    }

    @FXML
    public void filtrageProduit(KeyEvent k) {

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
            } else
                return false;
        });


        SortedList<ProduitEntity> sortedProduit = new SortedList<>(filteredProduit);
        sortedProduit.comparatorProperty().bind(tableProduit.comparatorProperty());

        tableProduit.setItems(sortedProduit);
        tableProduit.refresh();
    }

    @FXML
    public void filtrageCommande(KeyEvent k) {
        FilteredList<CommandeEntity> filteredCommande = new FilteredList<>(commandeObservableList, b -> true);

        filteredCommande.setPredicate(commandeEntity -> {

            if (filtreCommande.getText().isEmpty() || filtreCommande.getText().isBlank())
                return true;

            String searchKeyword = filtreCommande.getText().toLowerCase();

            if (Integer.toString(commandeEntity.getIdCmd()).toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (commandeEntity.getNomC().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (commandeEntity.getNomP().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (commandeEntity.getQte().toString().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (commandeEntity.getAdresseLivraison().toLowerCase().contains(searchKeyword)) {
                return true;
            } else if (commandeEntity.getDate().toString().toLowerCase().contains(searchKeyword)) {
                return true;
            } else
                return false;
        });


        SortedList<CommandeEntity> sortedCommande = new SortedList<>(filteredCommande);
        sortedCommande.comparatorProperty().bind(tableCommande.comparatorProperty());

        tableCommande.setItems(sortedCommande);
        tableCommande.refresh();
    }

    @FXML
    public void addFournisseur(ActionEvent e) {

        if (fieldNomF.getText().isEmpty() || fieldAddF.getText().isEmpty() || fieldPayF.getText().isEmpty()
                || fieldVilleF.getText().isEmpty() || fieldEmailF.getText().isEmpty() || fieldNumTelF.getText().isEmpty()){

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Notifications.create()
                    .owner(stage)
                    .title("Try Again!")
                    .text("Invalid Input")
                    .position(Pos.BOTTOM_CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showWarning();
        } else {

            FournisseurEntity fournisseur = new FournisseurEntity(fieldNomF.getText(),fieldAddF.getText(),
                    fieldPayF.getText(),fieldVilleF.getText(),fieldEmailF.getText(),fieldNumTelF.getText());
            FournisseurController.add(fournisseur);
            generateTableFournisseur();
            initComboBoxFournisseur();
        }

    }

    @FXML
    public void addProduit(ActionEvent e){

        if(fieldRefP.getText().isEmpty()||fieldNomP.getText().isEmpty()||fieldCatP.getText().isEmpty()
                ||fieldPrixP.getText().isEmpty()||fieldStockP.getText().isEmpty()||comboBoxF.getValue().isEmpty()){
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Notifications.create()
                    .owner(stage)
                    .title("Try Again!")
                    .text("Invalid Input")
                    .position(Pos.BOTTOM_CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showWarning();
        } else {
            ProduitEntity produit = new ProduitEntity(fieldRefP.getText(),fieldNomP.getText(),fieldCatP.getText(),
                    Double.parseDouble(fieldPrixP.getText()),Integer.parseInt(fieldStockP.getText()),0,fournisseurMap.get(comboBoxF.getValue()));
            ProduitController.add(produit);
            generateTableProduit();
        }
    }

    @FXML
    public void addCompte(ActionEvent e){

        if(fieldIdentifiantC.getText().isEmpty()|| fieldPwdC.getText().isEmpty()){
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Notifications.create()
                    .owner(stage)
                    .title("Try Again!")
                    .text("Invalid Input")
                    .position(Pos.BOTTOM_CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showWarning();
        } else {
            CompteEntity compte = new CompteEntity(fieldIdentifiantC.getText(),fieldPwdC.getText(),true);
            CompteController.add(compte);
            generateTableCompte();
        }
    }

    @FXML
    public void fournisseurSelected(MouseEvent e){
        selctedFournissuer = fournisseurObservableList.indexOf(tableFournisseur.getSelectionModel().getSelectedItem());
        modifierFournisseur.setDisable(false);
    }

    @FXML
    public void modifierFournisseurClicked(ActionEvent e){
        fieldNomF1.setText(fournisseurObservableList.get(selctedFournissuer).getNom());
        fieldAddF1.setText(fournisseurObservableList.get(selctedFournissuer).getAdresse());
        fieldPayF1.setText(fournisseurObservableList.get(selctedFournissuer).getPays());
        fieldVilleF1.setText(fournisseurObservableList.get(selctedFournissuer).getVille());
        fieldEmailF1.setText(fournisseurObservableList.get(selctedFournissuer).getEmail());
        fieldNumTelF1.setText(fournisseurObservableList.get(selctedFournissuer).getNumTel());
        modifierFournisseurPane.setVisible(true);
        modifierFournisseurPane.toFront();
        shadowPane.toFront();

    }

    @FXML
    public void modifierFournisseur(ActionEvent e){
        if (fieldNomF1.getText().isEmpty() || fieldAddF1.getText().isEmpty() || fieldPayF1.getText().isEmpty()
                || fieldVilleF1.getText().isEmpty() || fieldEmailF1.getText().isEmpty() || fieldNumTelF1.getText().isEmpty()){

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Notifications.create()
                    .owner(stage)
                    .title("Try Again!")
                    .text("Invalid Input")
                    .position(Pos.BOTTOM_CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showWarning();
        } else {

            FournisseurEntity fournisseur = new FournisseurEntity(fieldNomF1.getText(),fieldAddF1.getText(),
                    fieldPayF1.getText(),fieldVilleF1.getText(),fieldEmailF1.getText(),fieldNumTelF1.getText());
            FournisseurController.update(fournisseurObservableList.get(selctedFournissuer).getIdf(),fournisseur);
            fournisseurObservableList.set(selctedFournissuer,fournisseur);
            tableFournisseur.refresh();
            initComboBoxFournisseur();
            shadowPane.toBack();
            modifierFournisseur.setDisable(true);
        }
    }

    @FXML
    public  void suprimerFournisseur(ActionEvent e){
        FournisseurController.delete(fournisseurObservableList.get(selctedFournissuer).getIdf());
        fournisseurObservableList.remove(selctedFournissuer);
        tableFournisseur.refresh();
        initComboBoxFournisseur();
        shadowPane.toBack();
        modifierFournisseur.setDisable(true);
    }

    @FXML
    public void produitSelected(MouseEvent e){
        selectedProduit = produitObservableList.indexOf(tableProduit.getSelectionModel().getSelectedItem());
        modifierProduit.setDisable(false);
    }

    @FXML
    public void modifierProduitClicked(ActionEvent e){

        fieldRefP1.setText(produitObservableList.get(selectedProduit).getReference());
        fieldNomP1.setText(produitObservableList.get(selectedProduit).getNom());
        fieldCatP1.setText(produitObservableList.get(selectedProduit).getCategory());
        fieldPrixP1.setText(produitObservableList.get(selectedProduit).getPrixUnitaire().toString());
        fieldStockP1.setText(produitObservableList.get(selectedProduit).getStock().toString());
        comboBoxF1.getSelectionModel().select(fournisseurMap.get(produitObservableList.get(selectedProduit).getNomf()));
        modifierProduitPane.setVisible(true);
        modifierProduitPane.toFront();
        shadowPane.toFront();
    }

    @FXML
    public void modifierProduit(ActionEvent e){
        if(fieldRefP1.getText().isEmpty()||fieldNomP1.getText().isEmpty()||fieldCatP1.getText().isEmpty()
                ||fieldPrixP1.getText().isEmpty()||fieldStockP1.getText().isEmpty()||comboBoxF1.getValue().isEmpty()){
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Notifications.create()
                    .owner(stage)
                    .title("Try Again!")
                    .text("Invalid Input")
                    .position(Pos.BOTTOM_CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showWarning();
        } else {
            ProduitEntity produit = new ProduitEntity(fieldRefP1.getText(),fieldNomP1.getText(),fieldCatP1.getText(),
                    Double.parseDouble(fieldPrixP1.getText()),Integer.parseInt(fieldStockP1.getText()),produitObservableList.get(selectedProduit).getQteVendue(),fournisseurMap.get(comboBoxF1.getValue()));
                produit.setNomf(comboBoxF1.getValue());
            ProduitController.update(produitObservableList.get(selectedProduit).getIdp(),produit);
            produitObservableList.set(selectedProduit,produit);
            tableProduit.refresh();
            shadowPane.toBack();
            modifierProduit.setDisable(true);
        }
    }

    @FXML
    public  void suprimerProduit(ActionEvent e){
        ProduitController.delete(produitObservableList.get(selectedProduit).getIdp());
        produitObservableList.remove(selectedProduit);
        tableProduit.refresh();
        shadowPane.toBack();
        modifierProduit.setDisable(true);
    }

    @FXML
    public void compteSelected(MouseEvent e){

    }

    @FXML
    public void modifierCompteClicked(ActionEvent e){

    }

    @FXML
    public void modifierCompte(ActionEvent e){

    }

    @FXML
    public void suprimerCompte(ActionEvent e){

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


    public void generateTableFournisseur() {
        nomF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        adresseF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAdresse()));
        paysF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPays()));
        villeF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getVille()));
        emailF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));
        numTelF.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNumTel()));

        fournisseurObservableList.removeAll();
        try {
            ResultSet rs = FournisseurController.getAll();
            while (rs.next()) {
                fournisseurObservableList.add(new FournisseurEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7)));
            }

            tableFournisseur.setItems(fournisseurObservableList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateTableProduit() {
        refP.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getReference()));
        nomP.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        catP.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCategory()));
        prixP.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPrixUnitaire().toString()));
        stockP.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getStock().toString()));
        qteVendueP.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getQteVendue().toString()));
        fournisseurP.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNomf()));

        try {
            ResultSet rs = ProduitController.getTable();
            while (rs.next()) {
                ProduitEntity produit = new ProduitEntity(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDouble(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
                produit.setNomf(rs.getString(9));
                produitObservableList.add(produit);
            }

            tableProduit.setItems(produitObservableList);
            tableProduit.refresh();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateTableCommande() {
        idCMD.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("" + cellData.getValue().getIdCmd()));
        clientCMD.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNomC()));
        produitCMD.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNomP()));
        qteCMD.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getQte().toString()));
        addresseCMD.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAdresseLivraison()));
        dateCMD.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDate().toString()));

        try {
            ResultSet rs = CommandeController.getTable();
            while (rs.next()) {
                CommandeEntity commande = new CommandeEntity(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getDate(6));
                commandeObservableList.add(commande);
            }

            tableCommande.setItems(commandeObservableList);
            tableCommande.refresh();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateTableCompte(){
        idC.setCellValueFactory(cellDate -> new ReadOnlyStringWrapper(""+cellDate.getValue().getId()));
        identifiantC.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getIdentifiant()));
        statutC.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getStatutName()));

        try{
            ResultSet rs = CompteController.getAll();
            while (rs.next()){
                CompteEntity compte = new CompteEntity(rs.getInt("id"),rs.getString("identifiant"),rs.getString("mot_de_passe"),rs.getBoolean("statut"));
            compteObservableList.add(compte);
            }
            tableCompte.setItems(compteObservableList);
            tableCompte.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateTableClient(){
        idClient.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(""+cellData.getValue().getId()));
        nomClient.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        prenomClient.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPrenom()));
        addClient.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAdresseDeFacturation()));
        villeClient.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getVille()));
        emailClient.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));
        numTelClient.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNumTel()));

        try {
            ResultSet rs = ClientController.getAll();
            while (rs.next()){
                ClientEntity client = new ClientEntity(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                clientObservableList.add(client);
            }

            tableClient.setItems(clientObservableList);
            tableClient.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initComboBoxFournisseur(){

        comboBoxF.getItems().clear();
        comboBoxF1.getItems().clear();
        fournisseurMap.clear();
        for (int i = 0; i < fournisseurObservableList.size(); i++) {
            comboBoxF.getItems().add(fournisseurObservableList.get(i).getNom());
            comboBoxF1.getItems().add(fournisseurObservableList.get(i).getNom());
            fournisseurMap.put(fournisseurObservableList.get(i).getNom(),i);
        }
    }

}

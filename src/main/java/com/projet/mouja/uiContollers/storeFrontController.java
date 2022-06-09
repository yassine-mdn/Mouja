package com.projet.mouja.uiContollers;

import com.projet.mouja.HelloApplication;
import com.projet.mouja.entities.*;
import com.projet.mouja.entitiesControllers.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class storeFrontController implements Initializable {

    @FXML
    private AnchorPane homePane,cartPane,registerPane,registerFormPane,updateFormPane;

    @FXML
    private GridPane productPane;

    @FXML
    private ComboBox<String> sortCB;

    @FXML
    private TableView<CartEntity> tableCart;
    @FXML
    private TableColumn<CartEntity, String> produitName;
    @FXML
    private TableColumn<CartEntity, String> quantiteProduit;
    @FXML
    private TableColumn<CartEntity, String> prixProduit;

    @FXML
    private Button remouveIrem,userButton;

    @FXML
    private TextField fieldIdentifiantC,nom,prenom,add,ville,email,numTel,nom1,prenom1,add1,ville1,email1,numTel1;
    @FXML
    private PasswordField fieldPwdC;

    private static int userId = -1;
    private ArrayList<ProduitEntity> produits = new ArrayList<>();
    private ObservableList<CartEntity> cartObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initCB();
        initProduits();
        populateGrid();
        generateCart();
        produitController.setidC(userId);

    }

    @FXML
    void backToHome(ActionEvent event) {
        homePane.toFront();
    }

    @FXML
    public void userMenu(ActionEvent e){
        if (userId != -1){
            Hyperlink logout = new Hyperlink("Log Out");
            Hyperlink settings = new Hyperlink("Settings");
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
                userId = -1;
                stage.setScene(scene);
            });
            settings.setOnAction(ActionEvent -> {
                try {
                    ClientEntity client = ClientController.getById(userId);
                    nom1.setText(client.getNom());
                    prenom1.setText(client.getPrenom());
                    add1.setText(client.getAdresseDeFacturation());
                    ville1.setText(client.getVille());
                    email1.setText(client.getEmail());
                    numTel1.setText(client.getNumTel());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                updateFormPane.toFront();
            });
            VBox vBox = new VBox(logout, settings);
            vBox.setMinSize(150, 50);
            PopOver popOver = new PopOver(vBox);
            popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
            popOver.show(userButton);
        }else{
            Hyperlink signIn = new Hyperlink("Log in");
            Hyperlink register = new Hyperlink("register");
            signIn.getStylesheets().add("com/projet/mouja/css/main.css");
            signIn.setOnAction(ActionEvent -> {
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
            register.setOnAction(ActionEvent -> {
                registerPane.toFront();
            });
            VBox vBox = new VBox(signIn, register);
            vBox.setMinSize(150, 50);
            PopOver popOver = new PopOver(vBox);
            popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
            popOver.show(userButton);
        }
    }

    @FXML
    void addCompte(ActionEvent e) throws SQLException {

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
            CompteEntity compte = new CompteEntity(fieldIdentifiantC.getText(),fieldPwdC.getText(),false);
            CompteController.add(compte);
            userId = CompteController.getIdFromIdentifiant(fieldIdentifiantC.getText());
            produitController.setidC(userId);
            registerFormPane.toFront();
        }
    }

    @FXML
    void register(ActionEvent e){
        ClientEntity client = new ClientEntity(userId,nom.getText(),prenom.getText(),add.getText(),ville.getText(),email.getText(),numTel.getText());
        ClientController.add(client);
        homePane.toFront();
    }

    @FXML
    void update(ActionEvent e){
        ClientEntity client = new ClientEntity(userId,nom1.getText(),prenom1.getText(),add1.getText(),ville1.getText(),email1.getText(),numTel1.getText());
        ClientController.update(userId,client);
        homePane.toFront();
    }

    @FXML
    void showCart(ActionEvent event) {
        if (userId != -1){
            generateCart();
            cartPane.toFront();
        }
    }

    @FXML
    void showFavorites(ActionEvent event) {

    }

    @FXML
    void lancerCommande(ActionEvent event) throws SQLException {

        ClientEntity client = ClientController.getById(userId);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());


        for (int i = 0; i < cartObservableList.size(); i++) {

            int idP = ProduitController.getIdFromRef(cartObservableList.get(i).getRefProd());
            ProduitController.decrStockby(cartObservableList.get(i).getQuantite(),idP);
            CommandeController.add(new CommandeEntity(client.getAdresseDeFacturation(),date,cartObservableList.get(i).getQuantite(),idP,userId));
            CartController.remouveItem(userId,cartObservableList.get(i).getRefProd());
        }

        cartObservableList.clear();
        tableCart.refresh();
    }

    @FXML
    void produitSelected(MouseEvent event) {
        remouveIrem.setDisable(false);
    }

    @FXML
    void remouveCartItem(ActionEvent event) {
        int id = tableCart.getSelectionModel().getSelectedIndex();
        CartController.remouveItem(userId,cartObservableList.get(id).getRefProd());
        cartObservableList.remove(id);
        tableCart.refresh();
    }

    @FXML
    public void sort(ActionEvent e){
        if (sortCB.getValue().equals("Price :High to Low"))
            hightToLowProduit();
        else if (sortCB.getValue().equals("Price :Low to High"))
            lowToHightProduit();
        else if (sortCB.getValue().equals("Most Popular"))
            mostPopularProduit();
        else
            bestRatedProduit();
        populateGrid();
    }


    private void lowToHightProduit(){
        ResultSet rs = ProduitController.priceLowToHight();

        produits.clear();
        try {
            while (rs.next()) {
                ProduitEntity produit = new ProduitEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDouble(5), rs.getInt(6), rs.getInt(7), rs.getInt(8),rs.getString(9),rs.getDouble(10));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostPopularProduit(){
        ResultSet rs = ProduitController.mostPopular();

        produits.clear();
        try {
            while (rs.next()) {
                ProduitEntity produit = new ProduitEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDouble(5), rs.getInt(6), rs.getInt(7), rs.getInt(8),rs.getString(9),rs.getDouble(10));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void hightToLowProduit(){
        ResultSet rs = ProduitController.priceHighToLow();

        produits.clear();
        try {
            while (rs.next()) {
                ProduitEntity produit = new ProduitEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDouble(5), rs.getInt(6), rs.getInt(7), rs.getInt(8),rs.getString(9),rs.getDouble(10));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void bestRatedProduit(){
        ResultSet rs = ProduitController.sortRating();

        produits.clear();
        try {
            while (rs.next()) {
                ProduitEntity produit = new ProduitEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDouble(5), rs.getInt(6), rs.getInt(7), rs.getInt(8),rs.getString(9),rs.getDouble(10));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initProduits() {
        ResultSet rs = ProduitController.getAll();

        produits.clear();
        try {
            while (rs.next()) {
                ProduitEntity produit = new ProduitEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDouble(5), rs.getInt(6), rs.getInt(7), rs.getInt(8),rs.getString(9),rs.getDouble(10));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void populateGrid(){
        int cols = 0;
        int rows = 0;
        productPane.getChildren().clear();
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

    private void generateCart(){

        produitName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNomProd()));
        quantiteProduit.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getQuantite())));
        prixProduit.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getPrix())));

        cartObservableList.clear();

        try {
            ResultSet rs = CartController.getAll(userId);
            while (rs.next()) {
                CartEntity cart = new CartEntity(userId,rs.getString("ref"),rs.getFloat("prix"),rs.getInt("quantite"),rs.getString("nom"));
                cartObservableList.add(cart);
            }

            tableCart.setItems(cartObservableList);
            tableCart.refresh();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initCB(){
        sortCB.getItems().add("Price :High to Low");
        sortCB.getItems().add("Price :Low to High");
        sortCB.getItems().add("Most Popular");
        sortCB.getItems().add("Best reviewed");
    }

    public static void setUserId(int id) {
        userId = id;
    }
}

package com.projet.mouja.entitiesControllers;

import com.projet.mouja.entities.ProduitEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProduitController {

    public static ResultSet getAll() {
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select idp, reference , nom, category, prix_unitaire, stock, qte_vendue, idf,image_url,rating from projet.mouja.produit";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet sortRating(){
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select idp, reference , nom, category, prix_unitaire, stock, qte_vendue, idf,image_url,rating from projet.mouja.produit order by rating desc ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static  ResultSet priceHighToLow(){
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select idp, reference , nom, category, prix_unitaire, stock, qte_vendue, idf,image_url,rating from projet.mouja.produit order by prix_unitaire desc ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet priceLowToHight(){
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select idp, reference , nom, category, prix_unitaire, stock, qte_vendue, idf,image_url,rating from projet.mouja.produit order by prix_unitaire asc";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet mostPopular(){
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select idp, reference , nom, category, prix_unitaire, stock, qte_vendue, idf,image_url,rating from projet.mouja.produit order by  qte_vendue desc ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean add(ProduitEntity produit) {

        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "insert into projet.mouja.produit (nom , reference, category, prix_unitaire, stock, qte_vendue, idf) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setString(2, produit.getReference());
            preparedStatement.setString(3, produit.getCategory());
            preparedStatement.setDouble(4,produit.getPrixUnitaire());
            preparedStatement.setInt(5,produit.getStock());
            preparedStatement.setInt(6,produit.getQteVendue());
            preparedStatement.setInt(7,produit.getIdf());

            int i = preparedStatement.executeUpdate();
            success = (i != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean delete(int idP) {
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "delete from projet.mouja.produit where idp = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,idP);
            int i = preparedStatement.executeUpdate();
            success = i != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean update(int idP, ProduitEntity produit){
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "update projet.mouja.produit set nom = ?, reference = ?, category = ?, prix_unitaire= ?, stock = ?, qte_vendue = ?, idf =? where idp = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setString(2, produit.getReference());
            preparedStatement.setString(3, produit.getCategory());
            preparedStatement.setDouble(4,produit.getPrixUnitaire());
            preparedStatement.setInt(5,produit.getStock());
            preparedStatement.setInt(6,produit.getQteVendue());
            preparedStatement.setInt(7,produit.getIdf());
            preparedStatement.setInt(8,idP);

            int i = preparedStatement.executeUpdate();
            success = (i != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean updateStock(int ref){
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "update projet.mouja.produit set stock = stock-1 , qte_vendue = qte_vendue+1 where reference = ? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,ref);
            int i = preparedStatement.executeUpdate();
            success = i != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static ResultSet getTable(){
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select  idp,reference, produit.nom, category, prix_unitaire, stock, qte_vendue,f.idf, f.nom from projet.mouja.produit join projet.mouja.fournisseur f on produit.idf = f.idf";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean decrStockby(int dec, int id){
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "update projet.mouja.produit set stock = stock-? , qte_vendue = qte_vendue+? where idp = ? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,dec);
            preparedStatement.setInt(2,dec);
            preparedStatement.setInt(3,id);
            int i = preparedStatement.executeUpdate();
            success = i != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static int  getIdFromRef(String ref) throws SQLException {
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select idp from projet.mouja.produit where reference = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1,ref);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs.next())
            return rs.getInt(1);
        else
            return -1;
    }

}

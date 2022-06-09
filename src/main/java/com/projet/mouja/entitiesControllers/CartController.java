package com.projet.mouja.entitiesControllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartController {

    public static ResultSet getAll(int idC){
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select ref,nom , quantite, prix from projet.mouja.cart join projet.mouja.produit on reference = ref where idc = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,idC);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void addToCart(int idC, String ref,float prix) throws SQLException {
        if (!exists(idC,ref))
            add(idC, ref, prix);
        else
           update(idC,ref);
    }

    private static boolean exists(int idC, String ref) throws SQLException {
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select * from projet.mouja.cart where idc = ? and ref = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,idC);
            preparedStatement.setString(2, ref);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert rs != null;
        if (rs.next())
            return  true;
        else
            return false;
    }

    private static boolean add(int idC, String ref,float prix){
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "insert into projet.mouja.cart (idc, ref, prix, quantite) VALUES (?,?,?,1)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, idC);
            preparedStatement.setString(2, ref);
            preparedStatement.setDouble(3,prix);
            int i = preparedStatement.executeUpdate();
            success = (i != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    private static boolean update(int idC, String ref){
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "update projet.mouja.cart set quantite = quantite+1, prix = prix+cart.prix where idc = ? and ref = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, idC);
            preparedStatement.setString(2, ref);
            int i = preparedStatement.executeUpdate();
            success = (i != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean remouveItem(int idC, String ref){

        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "delete from projet.mouja.cart where idc = ? and ref = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, idC);
            preparedStatement.setString(2,ref);
            int i = preparedStatement.executeUpdate();
            success = (i != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean remouveALL(int idC){

        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "delete from projet.mouja.cart where idc = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, idC);
            int i = preparedStatement.executeUpdate();
            success = (i != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}

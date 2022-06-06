package com.projet.mouja.entitiesControllers;

import com.projet.mouja.entities.CommandeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeController {

    public static ResultSet getAll() {
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select * from projet.mouja.commande";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean add(CommandeEntity commande) {

        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "insert into projet.mouja.commande (adresse_livraison, date, qte, idp, idc) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, commande.getAdresseLivraison());
            preparedStatement.setDate(2, commande.getDate());
            preparedStatement.setInt(3, commande.getQte());
            preparedStatement.setInt(4, commande.getIdp());
            preparedStatement.setInt(5, commande.getIdc());

            int i = preparedStatement.executeUpdate();
            success = i != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static ResultSet getByIdC(int idC){
        ResultSet rs = null;
        String SQL = "Select * from projet.mouja.commande where idc = ?";
        try {
            Connection connection = SingletonConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,idC);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getByIdP(int idP){
        ResultSet rs = null;
        String SQL = "Select * from projet.mouja.commande where idp = ?";
        try {
            Connection connection = SingletonConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,idP);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}

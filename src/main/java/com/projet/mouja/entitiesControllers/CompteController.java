package com.projet.mouja.entitiesControllers;


import com.projet.mouja.entities.CompteEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CompteController {

    public static ResultSet getAll() {
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select * from projet.mouja.compte";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean add(CompteEntity compte) {

        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "insert into projet.mouja.compte (identifiant, mot_de_passe, statut) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, compte.getIdentifiant());
            preparedStatement.setString(2, compte.getMotDePasse());
            preparedStatement.setBoolean(3, compte.getStatut());

            int i = preparedStatement.executeUpdate();
            success = i != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean delete(int id) {
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "delete from projet.mouja.compte where id =" + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            int i = preparedStatement.executeUpdate();
            success = i != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean update(int id, CompteEntity compte){
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "update projet.mouja.compte set identifiant =? , mot_de_passe = ?, statut = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, compte.getIdentifiant());
            preparedStatement.setString(2, compte.getMotDePasse());
            preparedStatement.setBoolean(3, compte.getStatut());
            preparedStatement.setInt(4,id);

            int i = preparedStatement.executeUpdate();
            success = i != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static ResultSet login(String login, String password) {
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select * from projet.mouja.compte where identifiant = ? and mot_de_passe = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}

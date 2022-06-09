package com.projet.mouja.entitiesControllers;

import com.projet.mouja.entities.ClientEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientController {
    public static ResultSet getAll() {
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select * from projet.mouja.client";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean delete(int id) {
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "delete from projet.mouja.client where id =" + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            int i = preparedStatement.executeUpdate();
            success = i != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean add (ClientEntity client){
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "insert into projet.mouja.client (id, nom, prenom, adresse_de_facturation, ville, email, num_tel) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,client.getId());
            preparedStatement.setString(2, client.getNom());
            preparedStatement.setString(3, client.getPrenom());
            preparedStatement.setString(4, client.getAdresseDeFacturation());
            preparedStatement.setString(5,client.getVille());
            preparedStatement.setString(6,client.getEmail());
            preparedStatement.setString(7, client.getNumTel());
            int i = preparedStatement.executeUpdate();
            success = i != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean update (int id , ClientEntity client){
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "update projet.mouja.client set nom = ?, prenom = ?, adresse_de_facturation = ?, ville = ?, email = ?, num_tel = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setString(3, client.getAdresseDeFacturation());
            preparedStatement.setString(4,client.getVille());
            preparedStatement.setString(5,client.getEmail());
            preparedStatement.setString(6, client.getNumTel());
            preparedStatement.setInt(7,id);
            int i = preparedStatement.executeUpdate();
            success = i != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static ClientEntity getById(int id) throws SQLException {
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select * from projet.mouja.client where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ClientEntity client = null;
        if (rs.next())
            client = new ClientEntity(rs.getInt(1),rs.getString(2),rs.getString(3),
                rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
        return client;
    }
}

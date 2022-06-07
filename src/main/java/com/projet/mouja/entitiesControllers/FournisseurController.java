package com.projet.mouja.entitiesControllers;

import com.projet.mouja.entities.FournisseurEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FournisseurController {

    public static ResultSet getAll() {
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "Select * from projet.mouja.fournisseur";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean add(FournisseurEntity fournisseur) {

        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "insert into projet.mouja.fournisseur (nom, adresse, pays, ville, email, num_tel) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, fournisseur.getNom());
            preparedStatement.setString(2, fournisseur.getAdresse());
            preparedStatement.setString(3,fournisseur.getPays());
            preparedStatement.setString(4,fournisseur.getVille());
            preparedStatement.setString(5,fournisseur.getEmail());
            preparedStatement.setString(6,fournisseur.getNumTel());

            int i = preparedStatement.executeUpdate();
            success = (i != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean delete(int idF) {
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "delete from projet.mouja.fournisseur where idf = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,idF);
            int i = preparedStatement.executeUpdate();
            success = i != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean update(int idf, FournisseurEntity fournisseur){
        boolean success = false;
        Connection connection = SingletonConnection.getConnection();
        String SQL = "update projet.mouja.fournisseur set nom = ?, adresse = ?, pays= ?, ville = ?, email =? , num_tel = ? where idf = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, fournisseur.getNom());
            preparedStatement.setString(2, fournisseur.getAdresse());
            preparedStatement.setString(3,fournisseur.getPays());
            preparedStatement.setString(4,fournisseur.getVille());
            preparedStatement.setString(5,fournisseur.getEmail());
            preparedStatement.setString(6,fournisseur.getNumTel());
            preparedStatement.setInt(7,idf);

            int i = preparedStatement.executeUpdate();
            success = (i != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     *
     * @param filter syntax "filter:value"
     * @return filtered result set
     */
    public static ResultSet filter(String filter){
        String[] fv = filter.split(":");
        String SQL;
        if (fv.length == 2) {
           SQL = "Select * from projet.mouja.fournisseur where "+fv[0]+" like \'%"+fv[1]+"%\'";
        }else{
            SQL ="Select * from projet.mouja.fournisseur";
        }
        ResultSet rs = null;
        Connection connection = SingletonConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            rs = preparedStatement.executeQuery();
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}

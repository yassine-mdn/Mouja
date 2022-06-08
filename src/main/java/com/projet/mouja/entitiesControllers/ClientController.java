package com.projet.mouja.entitiesControllers;

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
}

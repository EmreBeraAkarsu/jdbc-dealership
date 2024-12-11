package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        String query = "insert into inventory (vin, dealershipId) Values (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vin);
            preparedStatement.setInt(2, dealershipId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Added the vehicle!");
            } else {
                System.out.println("Couldn't add the vehicle");
            }
        } catch (SQLException e) {

            System.err.println("Error adding thge vehicle" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        String query = "Delete from inventory where vin = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vin);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted the vehicle!");
            } else {
                System.out.println("Couldn't delete the vehicle");
            }
        } catch (SQLException e) {

            System.err.println("Error deleting thge vehicle" + e.getMessage());
            e.printStackTrace();
        }    }
}

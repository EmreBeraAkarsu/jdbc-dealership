package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        String query = "insert into sales_contract (contract_id, vin, sale_date, price) Values (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, salesContract.getContractId());
            preparedStatement.setString(2, salesContract.getVin());
            preparedStatement.setDate(3, java.sql.Date.valueOf(salesContract.getSaleDate()));
            preparedStatement.setDouble(4, salesContract.getPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Added the lease contract!");
            } else {
                System.out.println("Couldn't add the lease contract");
            }
        } catch (SQLException e) {

            System.err.println("Error adding thge lease contract" + e.getMessage());
            e.printStackTrace();
        }
    }
}

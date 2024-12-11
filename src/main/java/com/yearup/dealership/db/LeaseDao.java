package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        String query = "insert into lease_contracts (contract_id, vin, lease_start, lease_end, monthly_payment) Values (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, leaseContract.getContractId());
            preparedStatement.setString(2, leaseContract.getVin());
            preparedStatement.setDate(3, java.sql.Date.valueOf(leaseContract.getLeaseStart()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(leaseContract.getLeaseEnd()));
            preparedStatement.setDouble(5, leaseContract.getMonthlyPayment());

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

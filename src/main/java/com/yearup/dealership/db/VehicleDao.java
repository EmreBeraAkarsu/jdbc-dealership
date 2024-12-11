package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        String query = "insert into vehicles (vin, make, model, year, sold, color, vehicleType, odometer, price) Values (?, ?, ?, ?, ?, ?, ?, ?, ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setBoolean(5, vehicle.isSold());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getVehicleType());
            preparedStatement.setInt(8, vehicle.getOdometer());
            preparedStatement.setDouble(9, vehicle.getPrice());

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

    public void removeVehicle(String VIN) {
        String query = "delete from vehicles where vin = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, VIN);


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

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where price >= ? and price <= ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    createVehicleFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {

            System.err.println("Error searching thge vehicle" + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where make = ? and model = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    createVehicleFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {

            System.err.println("Error searching thge vehicle" + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where year between ? and ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, minYear);
            preparedStatement.setInt(2, maxYear);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    createVehicleFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {

            System.err.println("Error searching thge vehicle" + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where color = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, color);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    createVehicleFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {

            System.err.println("Error searching thge vehicle" + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where odometer between ? and ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, minMileage);
            preparedStatement.setInt(2, maxMileage);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    createVehicleFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {

            System.err.println("Error searching thge vehicle" + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "select * from vehicles where type = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, type);



            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    createVehicleFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {

            System.err.println("Error searching thge vehicle" + e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}

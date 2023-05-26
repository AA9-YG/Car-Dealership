package org.cardealership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO{

    private Connection connection;

    public CarDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addCar(Car car) {
        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO cars (model, yearOfCar, price) VALUES(?,?,?); "
            );

            statement.setString(1, car.getModel());
            statement.setInt(2, car.getYear());
            statement.setDouble(3, car.getPrice());

            // Execute SQL statement
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCar(Car car) {
        try {

            PreparedStatement statement = connection.prepareStatement(
                    """
                    UPDATE cars 
                    SET model = ?,
                       price = ?,
                       yearOfCar = ?
                    WHERE stock = ?;
                    """
            );

            statement.setString(1, car.getModel());
            statement.setInt(2, car.getYear());
            statement.setDouble(3, car.getPrice());
            statement.setInt(4, car.getStock());

            // Execute SQL statement
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCar(int stock) {
        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM cars WHERE stock =?;");

            statement.setInt(1, stock);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car getCar(int stock) {

        Car car = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars WHERE stock=?;");
            statement.setInt(1, stock);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int stockNum = resultSet.getInt("stock");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("yearOfCar");
                double price = resultSet.getDouble("price");

                car = new Car(stockNum, model, price, year);

            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cars");

            while (resultSet.next()){
                int stock = resultSet.getInt("stock");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("yearOfCar");
                double price = resultSet.getDouble("price");

                Car car = new Car(stock, model, price, year);
                carList.add(car);

            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return carList;
    }
}

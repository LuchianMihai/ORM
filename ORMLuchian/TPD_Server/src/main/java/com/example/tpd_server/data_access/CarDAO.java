package com.example.tpd_server.data_access;

import com.example.tpd_server.models.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDAO {
    public static ArrayList<Car> getAll() {
        ArrayList<Car> result = new ArrayList<>();

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM public.\"Cars\"")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new Car(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)));
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Car get(int id) {
        Car car = null;
        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM public.\"Cars\" WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                car = new Car(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

    public static void add(Car car) {
        if (car == null) {
            return;
        }

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO public.\"Cars\"(\n" +
                     "\tid, brand, \"yearOfProduction\")\n" +
                     "\tVALUES (?, ?, ?);")) {

            preparedStatement.setInt(1, car.getId());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setInt(3, car.getYearOfProduction());

            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int carId) {

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM public.\"Cars\" WHERE id = ?")) {

            preparedStatement.setInt(1, carId);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

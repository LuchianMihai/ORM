package com.example.tpd_server.data_access;

import com.example.tpd_server.models.Car;
import com.example.tpd_server.models.UserCars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCarDAO {

    public static ArrayList<UserCars> getAll() {
        ArrayList<UserCars> result = new ArrayList<>();

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM public.\"UserCars\"")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new UserCars(resultSet.getInt(1),
                        resultSet.getInt(2)));
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<Car> getCarsForUser(int userId){
        List<UserCars> ownedCars = UserCarDAO.getAll().stream().filter(userCar -> userCar.getUserId() == userId).collect(Collectors.toList());
        List<Car> cars = new ArrayList<>();
        for(UserCars userCar: ownedCars){
           cars.add(CarDAO.get(userCar.getCarId()));
        }

        return cars;
    }

    public static void add(UserCars userCar) {
        if (userCar == null) {
            return;
        }

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO public.\"UserCars\"(\n" +
                     "\t\"userId\", carId)\n" +
                     "\tVALUES (?, ?);")) {

            preparedStatement.setInt(1, userCar.getUserId());
            preparedStatement.setInt(2, userCar.getCarId());

            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(int userId, int carId) {

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM public.\"UserCars\" WHERE \"userId\" = ? AND \"carId\" = ?")) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, carId);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

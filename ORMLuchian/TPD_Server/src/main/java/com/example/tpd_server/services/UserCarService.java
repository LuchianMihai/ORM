package com.example.tpd_server.services;

import com.example.tpd_server.data_access.UserCarDAO;
import com.example.tpd_server.models.Car;
import com.example.tpd_server.models.UserCars;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class UserCarService {
    public ArrayList<UserCars> getAll() {
        return UserCarDAO.getAll();
    }

    public List<Car> getCarsForUser(int userId) {
        if(userId < 0){
            return null;
        }
        return UserCarDAO.getCarsForUser(userId);
    }

    public void add(String response) {
        if (response.isEmpty()) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            UserCars userMotorcycle = mapper.readValue(response, new TypeReference<>() {
            });
            UserCarDAO.add(userMotorcycle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int userId, int motorcycleId) {
        if (userId < 0 || motorcycleId < 0) {
            return;
        }

        try {
            UserCarDAO.delete(userId, motorcycleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

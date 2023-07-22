package com.example.tpd_server.services;

import com.example.tpd_server.data_access.CarDAO;
import com.example.tpd_server.models.Car;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class CarService {
    public ArrayList<Car> getAll() {
        return CarDAO.getAll();
    }

    public Car get(int id) {
        if (id < 0) {
            return null;
        }
        return CarDAO.get(id);
    }

    public void add(String response) {
        if (response.isEmpty()) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            Car motorcycle = mapper.readValue(response, new TypeReference<>() {
            });
            CarDAO.add(motorcycle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int motorcycleId) {
        if (motorcycleId < 0) {
            return;
        }

        try {
            CarDAO.delete(motorcycleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package com.example.tpd_server.models;

import com.example.tpd_server.interfaces.UserCarInterface;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class UserCars implements UserCarInterface, Serializable {
    private int userId;
    private int carId;

    public UserCars(){

    }
    public UserCars(int userId, int carId) {
        this.userId = userId;
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}

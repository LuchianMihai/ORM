package com.example.tpd_server.interfaces;

import javax.ejb.Remote;

@Remote
public interface UserCarInterface {
    int getUserId();
    void setUserId(int userId);
    int getCarId();
    void setCarId(int carId);
}

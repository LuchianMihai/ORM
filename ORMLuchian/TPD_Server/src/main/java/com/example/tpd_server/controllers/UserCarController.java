package com.example.tpd_server.controllers;

import com.example.tpd_server.models.Car;
import com.example.tpd_server.models.UserCars;
import com.example.tpd_server.services.UserCarService;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/user-cars")
public class UserCarController {
    private static final UserCarService userCarService = new UserCarService();

    @GET
    @Produces("application/json")
    public List<UserCars> getAll() {
        return userCarService.getAll();
    }

    @GET
    @Produces("application/json")
    @Path("/{userId}")
    public List<Car> getCarsForUser(@PathParam("userId") int userId) {
        return userCarService.getCarsForUser(userId);
    }

    @POST
    @Consumes("application/json")
    public void add(String response) {
        userCarService.add(response);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/{userId}/{carId}")
    public void delete(@PathParam("userId") int userId, @PathParam("carId") int carId){
        userCarService.delete(userId, carId);
    }
}

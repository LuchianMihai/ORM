package com.example.tpd_server.controllers;

import com.example.tpd_server.models.Car;
import com.example.tpd_server.services.CarService;
import jakarta.ws.rs.*;

import java.util.ArrayList;

@Path("/cars")
public class CarController {
    private static final CarService carService = new CarService();

    @GET
    @Produces("application/json")
    public ArrayList<Car> getAll() {
        return carService.getAll();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Car get(@PathParam("id") int id) {
        return carService.get(id);
    }

    @POST
    @Consumes("application/json")
    public void add(String response) {
        carService.add(response);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/{carId}")
    public void delete(@PathParam("carId") int carId){
        carService.delete(carId);
    }
}

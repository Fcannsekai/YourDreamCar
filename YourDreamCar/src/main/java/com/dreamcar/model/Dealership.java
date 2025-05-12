package com.dreamcar.model;

import com.dreamcar.ui.User;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<User.Vehicle> inventory = new ArrayList<>();

    public Dealership(String name, String address, String phone, List<User.Vehicle> inventory) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = inventory;
    }

    // Constructor and methods: addVehicle, removeVehicle, searchByPrice, etc.
}

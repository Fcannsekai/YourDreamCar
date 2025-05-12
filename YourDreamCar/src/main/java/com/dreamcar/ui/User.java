package com.dreamcar.ui;

import com.dreamcar.data.DealershipFileManager;
import com.dreamcar.model.*;

import java.util.List;

public class UserInterface {
    private Dealership dealership;

    public void display() {
        init();
        // Main menu loop with switch for each command
    }

    private void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        this.dealership = dfm.getDealership();
    }

    private void displayVehicles(List<com.dreamcar.ui.vehicle> vehicles) {
        // Nicely format and print the list
    }
}
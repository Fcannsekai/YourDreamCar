package com.dreamcar.ui;

import com.dreamcar.data.DealershipFileManager;
import com.dreamcar.model.Dealership;
import com.dreamcar.model.Vehicle;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public void display() {
        init();

        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║        Welcome to " + dealership.getName());
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ 1  -> List All Vehicles                    ║");
            System.out.println("║ 2  -> Add a Vehicle                        ║");
            System.out.println("║ 3  -> Remove a Vehicle                     ║");
            System.out.println("║ 4  -> Search by Price                      ║");
            System.out.println("║ 5  -> Search by Make & Model               ║");
            System.out.println("║ 6  -> Search by Year Range                 ║");
            System.out.println("║ 7  -> Search by Color                      ║");
            System.out.println("║ 8  -> Search by Mileage                    ║");
            System.out.println("║ 9  -> Search by Type                       ║");
            System.out.println("║ 0  -> Quit                                 ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("> ");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> processAllVehiclesRequest();
                case "2" -> processAddVehicleRequest();
                case "3" -> processRemoveVehicleRequest();
                case "4" -> processPriceSearch();
                case "5" -> processMakeModelSearch();
                case "6" -> processYearSearch();
                case "7" -> processColorSearch();
                case "8" -> processMileageSearch();
                case "9" -> processTypeSearch();
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid input.");
            }
        }
    }

    private void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        this.dealership = dfm.getDealership();

        if (this.dealership == null) {
            System.out.println("Dealership not loaded!");
        } else {
            System.out.println("Dealership loaded: " + dealership.getName());
            System.out.println("Vehicles loaded: " + dealership.getAllVehicles().size());
        }
    }

    private void processAllVehiclesRequest() {
        System.out.println("Listing all vehicles...");
        displayVehicles(dealership.getAllVehicles());
    }

    private void processAddVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter VIN: ");
            int vin = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter year: ");
            int year = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter make: ");
            String make = scanner.nextLine().trim();
            System.out.print("Enter model: ");
            String model = scanner.nextLine().trim();
            System.out.print("Enter type: ");
            String type = scanner.nextLine().trim();
            System.out.print("Enter color: ");
            String color = scanner.nextLine().trim();
            System.out.print("Enter odometer: ");
            int odometer = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
            dealership.addVehicle(vehicle);
            new DealershipFileManager().saveDealership(dealership);
            System.out.println("Vehicle added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding vehicle: " + e.getMessage());
        }
    }

    private void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter VIN of vehicle to remove: ");
        int vinToRemove = Integer.parseInt(scanner.nextLine().trim());

        Vehicle vehicleToRemove = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vinToRemove) {
                vehicleToRemove = v;
                break;
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            new DealershipFileManager().saveDealership(dealership);
            System.out.println("Vehicle removed.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("═════════════════════════════════════════════════════════════");
        for (Vehicle vehicle : vehicles) {
            vehicle.display();
        }
        System.out.println("═════════════════════════════════════════════════════════════");
    }

    private void processPriceSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum price: ");
        double min = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter maximum price: ");
        double max = Double.parseDouble(scanner.nextLine().trim());
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    private void processMakeModelSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter make: ");
        String make = scanner.nextLine().trim();
        System.out.print("Enter model: ");
        String model = scanner.nextLine().trim();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    private void processYearSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter start year: ");
        int min = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter end year: ");
        int max = Integer.parseInt(scanner.nextLine().trim());
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    private void processColorSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    private void processMileageSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum mileage: ");
        int min = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter maximum mileage: ");
        int max = Integer.parseInt(scanner.nextLine().trim());
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    private void processTypeSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle type (Car, Truck, SUV, Van): ");
        String type = scanner.nextLine().trim();
        displayVehicles(dealership.getVehiclesByType(type));
    }
}
package com.dreamcar.data;

import com.dreamcar.model.Dealership;
import com.dreamcar.model.Vehicle;

import java.io.*;
import java.util.Scanner;

public class DealershipFileManager {
    public Dealership getDealership() {
        try (Scanner scanner = new Scanner(new File("src/main/java/com/dreamcar/data/Dealership.csv"))) {
            String[] dealershipInfo = scanner.nextLine().split("\\|");
            Dealership dealership = new Dealership(dealershipInfo[0], dealershipInfo[1], dealershipInfo[2]);

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\\|");
                int vin = Integer.parseInt(data[0]);
                int year = Integer.parseInt(data[1]);
                String make = data[2];
                String model = data[3];
                String type = data[4];
                String color = data[5];
                int odometer = Integer.parseInt(data[6]);
                double price = Double.parseDouble(data[7]);

                dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, odometer, price));
            }

            return dealership;
        } catch (IOException e) {
            System.out.println("Error reading dealership file: " + e.getMessage());
            return null;
        }
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/com/dreamcar/data/Dealership.csv"))) {
            writer.printf("%s|%s|%s%n", dealership.getName(), dealership.getAddress(), dealership.getPhone());

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.printf("%d|%d|%s|%s|%s|%s|%d|%.2f%n",
                        vehicle.getVin(),
                        vehicle.getYear(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getType(),
                        vehicle.getColor(),
                        vehicle.getOdometer(),
                        vehicle.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error writing to dealership file: " + e.getMessage());
        }
    }
}
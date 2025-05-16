# Dream Car Dealership CLI Application

This is a console-based Java application that simulates a used car dealership system. Users can view, search, add, and remove vehicles stored in a pipe-delimited CSV file.

## Features

- Load dealership and vehicle inventory from a CSV file
- View all vehicles
- Search vehicles by:
  - Price range
  - Make & Model
  - Year range
  - Color
  - Mileage range
  - Type (Car, Truck, SUV, Van)
- Add or remove vehicles
- Save all changes back to the file

##  How to Run This Project

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/car-dealership-cli.git
cd car-dealership-cli
```

### 2. Open in IntelliJ or VS Code

- Import as a Maven or plain Java project.
- Make sure `src` is marked as the **source root**.

### 3. Run the Application

Run the `Main` class inside:
```
src/main/java/com/dreamcar/ui/Main.java
```

Or use the terminal:

```bash
javac src/main/java/com/dreamcar/ui/Main.java
java -cp src/main/java com.dreamcar.ui.Main
```

## Requirements

- Java 17 or higher
- IntelliJ IDEA, VS Code, or any Java IDE

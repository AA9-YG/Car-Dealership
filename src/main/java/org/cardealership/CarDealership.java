package org.cardealership;

import java.util.Calendar;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class CarDealership {

    private static CarDAO carDAO = new CarDAOImpl(JDBCConnection.getConnection());

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int choice = 0;

        do {
            System.out.println("Welcome to our Car Dealership!");
            System.out.println("1. Sell a car");
            System.out.println("2. Modify a car");
            System.out.println("3. Buy a car");
            System.out.println("4. Get car info");
            System.out.println("5. View all cars");
            System.out.println("6. Exit");

            try {
                choice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a number that matches an option.");
            }

            scan.nextLine();

            switch (choice) {
                case 0 -> System.out.println();
                case 1 -> sellCar(scan);
                case 2 -> modifyCar(scan);
                case 3 -> buyCar(scan);
                case 4 -> getCarByID(scan);
                case 5 -> getAllCars();
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid Choice. Try again.");
            }

        } while (true);

    }

    private static void getAllCars() {
        if (!carDAO.getAllCars().isEmpty()) {
            for (Car car : carDAO.getAllCars()) {
                System.out.println(car);
            }
        }
    }

    private static void getCarByID(Scanner scan) {

        int stock = 0;

        while (stock == 0) {
            System.out.println("Enter the stock number: ");

            try {
                stock = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input.");
                scan.nextLine();
            }
        }

        Car car = carDAO.getCar(stock);

        if (car == null) System.out.println("Car not found");
        else System.out.println(car);
    }

    // Buying a car from the dealership
    // Customer will need to specify a model name and year to purchase a car.
    // Since we only have 4 attributes for a car, if there are duplicates we will say that the dealership has more in stock.
    // So if a customer buys a duplicate it should only remove one from the database.
    private static void buyCar(Scanner scan) {
        String model = "";
        int year = 0;

        while (true) {
            try {
                System.out.println("Enter the model of the car you want to buy: ");
                model = scan.nextLine();
                System.out.println("Enter the year of the car you want to buy: ");
                year = scan.nextInt();

                break;

            } catch (Exception e) {
                System.out.println("Invalid input.");
            }

            scan.nextLine();
            System.out.println("Please enter a name for the model and a number for the year.");
        }

        // Get info for car before deletion
        Car newCar = carDAO.getCar(model, year);

        if (newCar != null) {
            Calendar calendar = Calendar.getInstance();
            System.out.println("Today's Offer: ");
            todaysOffer(calendar.get(Calendar.DAY_OF_WEEK));

            System.out.println("\nThank you for your purchasing the " + newCar.getModel() + ". Enjoy your car!\n");

            // Remove car from database
            carDAO.deleteCar(model, year);

        } else {
            System.out.println("Car cannot be found");
        }
    }

    private static void modifyCar(Scanner scan) {
        int stock = 0;

        while (stock == 0) {
            System.out.println("Please enter the stock number for the car you want to modify: ");

            try {
                stock = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }

            scan.nextLine();
        }

        Car car = carDAO.getCar(stock);

        if (car == null)
            System.out.println("Car not found");
        else {
            String model;
            int year;
            double price;


            while (true) {
                try {
                    System.out.println("Enter the car model: ");
                    model = scan.nextLine();
                    System.out.println("Enter the car year: ");
                    year = scan.nextInt();
                    System.out.println("Enter the price of the car: ");
                    price = scan.nextDouble();

                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. Enter the name for the model and a number for the year and the price.");
                    scan.nextLine();
                }
            }

            car.setModel(model.isEmpty() ? car.getModel() : model);
            car.setYear(year < 2000 || year > 2023 ? car.getYear() : year);
            car.setPrice(price < 0 ? car.getPrice() : price);

            carDAO.updateCar(car);
            System.out.println("Car updated successfully");
        }

    }

    // Customer selling a car to the dealership
    private static void sellCar(Scanner scan) {
        String model;
        int year;
        double price;

        while (true) {
            try {
                System.out.println("Enter the information of the car you want to sell.");
                System.out.println("Enter the model: ");
                model = scan.nextLine();
                System.out.println("Enter the year: ");
                year = scan.nextInt();
                System.out.println("Enter the price: ");
                price = scan.nextDouble();

                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a name for the model and a number for the year and price.");
                scan.nextLine();
            }
        }

        Car car = new Car(model, price, year);
        carDAO.addCar(car);
        System.out.println("Thank you for selling your car to our dealership!");
    }

    private static void todaysOffer(int day) {

        switch (day) {
            case 1 -> System.out.println("On Monday you get free set of wheels");
            case 2 -> System.out.println("On Tuesday you get free maintenance for a year");
            case 3 -> System.out.println("On Wednesday you get free painting");
            case 4, 5 -> System.out.println("On Thurdays and Fridays you get free GPS");
            case 6, 7 -> System.out.println("There are no offers on weekends");
        }
    }


}
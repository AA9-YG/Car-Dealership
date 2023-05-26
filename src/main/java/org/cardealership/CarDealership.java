package org.cardealership;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class CarDealership {

    private static CarDAO carDAO = new CarDAOImpl(JDBCConnection.getConnection());

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int choice;

        do {
            System.out.println("Welcome to our Car Dealership!");
            System.out.println("1. Sell a car");
            System.out.println("2. Modify a car");
            System.out.println("3. Buy a car");
            System.out.println("4. Get car info");
            System.out.println("5. View all cars");
            System.out.println("6. Exit");

            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> addCar(scan);
                case 2 -> updateCar(scan);
                case 3 -> deleteCar(scan);
                case 4 -> getCarByID(scan);
                case 5 -> getAllCars();
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid Choice. Try again.");
            }
        } while (choice != 0);

    }
    private static void getAllCars() {
        if (!carDAO.getAllCars().isEmpty()) {
            for (Car car : carDAO.getAllCars()) {
                System.out.println(car);
            }
        }
    }

    private static void getCarByID(Scanner scan) {
        System.out.println("Enter the stock number: ");
        int stock = scan.nextInt();

        Car car = carDAO.getCar(stock);
        if(car == null) System.out.println("Car not found");
        else System.out.println(car);
    }

    private static void deleteCar(Scanner scan) {
        System.out.println("Enter the stock number of the car you want to buy: ");
        int stock = scan.nextInt();

        carDAO.deleteCar(stock);
        System.out.println("Enjoy your new car!");
    }

    private static void updateCar(Scanner scan) {
        System.out.println("Please enter the stock number for the car you want to modify: ");
        int stock = scan.nextInt();
        scan.nextLine();

        Car car = carDAO.getCar(stock);

        if (car == null)
            System.out.println("Car not found");
        else {

            System.out.println("Enter the car model: ");
            String model = scan.nextLine();
            System.out.println("Enter the car year: ");
            int year = scan.nextInt();
            System.out.println("Enter the price of the car: ");
            double price = scan.nextDouble();

            car.setModel(model.isEmpty() ? car.getModel() : model);
            car.setYear(year < 2000 || year > 2023 ? car.getYear() : year);
            car.setPrice(price < 0 ? car.getPrice() : price);

            carDAO.updateCar(car);
            System.out.println("Car updated successfully");
        }

    }

    private static void addCar(Scanner scan) {
        System.out.println("Enter the information of the car you want to sell.");
        System.out.println("Enter the model: ");
        String model = scan.nextLine();
        System.out.println("Enter the year: ");
        int year = scan.nextInt();
        System.out.println("Enter the price: ");
        double price = scan.nextDouble();

        Car car = new Car(model, price, year);
        carDAO.addCar(car);
        System.out.println("Thank you for selling your car to our dealership!");

    }


}
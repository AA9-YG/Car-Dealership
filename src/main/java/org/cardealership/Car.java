package org.cardealership;

public class Car {

    // Properties for Car Class: stock, model, price, year
    private int stock;

    private String model;

    private double price;

    private int year;

    public Car(int stock, String model, double price, int year) {
        this.stock = stock;
        this.model = model;
        this.price = price;
        this.year = year;
    }

    public Car(String model, double price, int year) {
        this.model = model;
        this.price = price;
        this.year = year;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "stock=" + stock +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", year=" + year +
                '}';
    }
}

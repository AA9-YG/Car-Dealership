package org.cardealership;

import java.util.List;

public interface CarDAO {
    // Car Data Access Operator
    void addCar(Car car);

    void updateCar(Car car);

    void deleteCar(String model, int year);

    Car getCar(int stock);

    Car getCar(String model, int year);

    List<Car> getAllCars();

}

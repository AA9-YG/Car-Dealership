package org.cardealership;

import java.util.List;

public interface CarDAO {
    // Car Data Access Operator
    void addCar(Car car);

    void updateCar(Car car);

    void deleteCar(int stock);

    Car getCar(int stock);

    List<Car> getAllCars();

}

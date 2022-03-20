package edu.umb.cs681.hw2;

import java.util.ArrayList;


public class Car {

    private String model, make;
    private int mileage, year, dominationCount;
    private int price;

    public Car(String make, String model, int mileage, int year, int price) {
        super();
        this.model = model;
        this.make = make;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDominationCount() {
        return this.dominationCount;
    }

    public void setDominationCount(ArrayList<Car> cars) {
        int count = 0;
        for (Car car : cars) {
            if (!car.equals(this)) {
                float price = car.getPrice();
                int year = car.getYear();
                int mileage = car.getMileage();

                if (this.getYear() >= year && this.getMileage() <= mileage && this.getPrice() <= price) {
                    if (this.getYear() > year || this.getMileage() < mileage || this.getPrice() < price) {
                        count++;
                    }
                }
            }
        }
        ;
        this.dominationCount = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void setUp(ArrayList<Car> cars) {
        cars.add(new Car("Audi", "A8", 20, 2018, 35000));
        cars.add(new Car("Honda", "CR-v", 18, 2019, 50000));
        cars.add(new Car("BMW", "Q5", 15, 2012, 40000));
        cars.add(new Car("Mercedes", "C-Class", 20, 2020, 55000));

    }

    static int minPrice(ArrayList<Car> cars) {
        return cars.stream().map((Car car) -> car.getPrice()).reduce(0, (result, carPrice) -> {
            if (result == 0)
                return carPrice;
            else if (carPrice < result)
                return carPrice;
            else
                return result;
        });
    }

    static int maxPrice(ArrayList<Car> cars) {
        return cars.stream().map((Car car) -> car.getPrice()).reduce(0, (result, carPrice) -> {
            if (result == 0)
                return carPrice;
            else if (carPrice > result)
                return carPrice;
            else
                return result;
        });
    }


    static int getAvgPrice(ArrayList<Car> listOfCars) {
        return listOfCars.stream().map((Car car) ->car.getPrice() )
                .reduce(0, (res,price) -> res+price, (finalRes, intermediateResult) -> finalRes)/listOfCars.size();
    }

    public static void main(String args[]) {
        ArrayList<Car> cars = new ArrayList<>();
        setUp(cars);

        cars.forEach((Car car) -> car.setDominationCount(cars));

        int minPrice = minPrice(cars);

        System.out.println("Minimum Price:      " + minPrice);

        int maxPrice = maxPrice(cars);

        System.out.println("Max Price:          " + maxPrice);


        int avgPrice = getAvgPrice(cars);

        System.out.println("The AVG Price:      " + avgPrice);
    }
}

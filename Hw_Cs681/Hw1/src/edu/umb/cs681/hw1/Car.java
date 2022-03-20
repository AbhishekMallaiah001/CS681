package edu.umb.cs681.hw1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Car {

    private String model, make;
    private int mileage, year, dominationCount;
    private float price;

    public Car(String make, String model, int mileage, int year, float price) {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public static void setUp(ArrayList<Car> cars) {
        cars.add(new Car("Audi", "A8", 20, 2018, 35000));
        cars.add(new Car("Honda", "CR-v", 18, 2019, 50000));
        cars.add(new Car("BMW", "Q5", 15, 2012, 40000));
        cars.add(new Car("Mercedes", "C-Class", 20, 2020, 55000));

    }

    public static void main(String args[]) {
        ArrayList<Car> cars = new ArrayList<>();
        setUp(cars);

        cars.forEach((Car car) -> car.setDominationCount(cars));

        System.out.println("\n\n");
        System.out.println("###################################");
        List<Car> sortByPrice = cars.stream().sorted(Comparator.comparing(Car::getPrice)).collect(Collectors.toList());
        System.out.println("Sorted Ascending order by PRICE"+ "\n");
        sortByPrice.forEach((Car car) -> System.out
                .println(car.getMake() + "      " + car.model + " " + car.mileage + " " + car.year + " " + car.getPrice()));
        System.out.println("\n\n");

        System.out.println("###################################");
        List<Car> sortByYear = cars.stream().sorted(Comparator.comparing(Car::getYear)).collect(Collectors.toList());
        System.out.println("Sorted Ascending order by YEAR"+"\n");
        sortByYear.forEach((Car car) -> System.out
                .println(car.getMake() + "      " + car.model + " " + car.mileage + " " + car.year + " " + car.getPrice()));
        System.out.println("\n\n");



        System.out.println("###################################");
        List<Car> sortByMileage = cars.stream().sorted(Comparator.comparing(Car::getMileage))
                .collect(Collectors.toList());
        System.out.println("Sorted Ascending order by MILEAGE"+"\n");
        sortByMileage.forEach((Car car) -> System.out
                .println(car.getMake() + "      " + car.model + " " + car.mileage + " " + car.year + " " + car.getPrice()));
        System.out.println("\n\n");


        System.out.println("###################################");
        List<Car> sortByDominationCount = cars.stream().sorted(Comparator.comparing(Car::getDominationCount))
                .collect(Collectors.toList());
        System.out.println("Sorted  by Domination COUNT"+"\n");
        sortByDominationCount.forEach((Car car) -> System.out.println(car.getMake() + "         " + car.model + " "
                + car.mileage + " " + car.year + " " + car.getPrice() + " " + car.getDominationCount()));

    }

}
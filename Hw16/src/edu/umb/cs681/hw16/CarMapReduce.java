package edu.umb.cs681.hw16;

import java.util.ArrayList;
import java.util.List;

public class CarMapReduce {

    private String model, make;
    private int mileage, year;
    private float price;

    public CarMapReduce(String make, String model, int mileage, int year, float price) {
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


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public static void main(String[] args) {

        List<CarMapReduce> list=new ArrayList<CarMapReduce>();
        list.add(new CarMapReduce("Audi", "A8", 20, 2018, 35000));
        list.add(new CarMapReduce("Honda", "CR-v", 18, 2019, 50000));
        list.add(new CarMapReduce("BMW", "Q5", 15, 2012, 40000));
        list.add(new CarMapReduce("Ford", "Fiesta", 10, 2009, 3800));
        list.add(new CarMapReduce("Mercedes", "C-Class", 20, 2020, 55000));


        int max_milage = list.stream().parallel().map((CarMapReduce car) ->car.getMileage() )
                .reduce(0, (maxMileage,mileage)->maxMileage > mileage ? maxMileage : mileage);

        System.out.println("The Highest Mileage among the Cars :  "+max_milage);


        int oldestCar = list.stream().parallel().map((CarMapReduce car) ->car.getYear() )
                .reduce(Integer.MAX_VALUE, (oldest, year)->oldest < year ? oldest : year);

        System.out.println("The Oldest Car:  "+oldestCar);


        int j=0;
        int count = list.stream().parallel().map(i->j+1).reduce(0,(res1,res2)->res1+res2);
        System.out.println("The Cars Count:   "+count);
    }
}
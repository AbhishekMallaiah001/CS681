package edu.umb.cs681.hw10;

public class ClientCode {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            Position position = new Position(34.5, 56, 7.9);
            Aircraft aircraft = new Aircraft(position);

            @Override
            public void run() {
                Position oldPosition = aircraft.getPosition().get();
                System.out.println("Creating New postiion");
                Position newPosition = new Position(oldPosition.getLatitude() + 1,
                                 oldPosition.getLongitude() + 1, oldPosition.getAltitude() + 1);

                System.out.println("Setting New position");
                aircraft.setPosition(newPosition);
                System.out.println("New Position :"+ aircraft.getPosition());

            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread Exception occured "+ e.getStackTrace());
        }

        Thread t3 = new Thread(r1);

        t3.start();

        try {
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread Exception occured" + e.getStackTrace());
        }

    }
}

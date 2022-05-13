package edu.umb.cs681.hw10;

import java.util.ArrayList;

public final class Position {
    private final double latitude, longitude, altitude;


    public Position(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getLatitude() {return this.latitude;}

    public double getLongitude() {return this.longitude;}

    public double getAltitude() {return this.altitude;}

    ArrayList<Double> getCoordinates () {
        ArrayList<Double> coordinates = new ArrayList<>();
        coordinates.add(this.latitude);
        coordinates.add(this.longitude);
        coordinates.add(this.altitude);
        return coordinates;
    }

    @Override
    public String toString() {
        return  "The position in the format Latitude-Longitude-Altitude : "
                + latitude +"---" + longitude +"---" + altitude;
    }

    public boolean equals (Position anotherPosition) {
        if (this.toString().equals(anotherPosition.toString()))
            return true;
        else
            return false;
    }

    public Position changeLat (double newLat) { return  new Position(newLat, this.longitude, this.altitude);}

    public Position changeLon (double newLon) { return  new Position(this.latitude, newLon, this.altitude); }

    public Position changeAlt (double newAlt) { return new Position(this.latitude, this.longitude, newAlt);}

    public double distanceTo (Position anotherPosition) {
        final int radius = 6371;  // Radius of the Earth

        double latitudeDist = Math.toRadians(anotherPosition.latitude -  this.latitude);
        double longitudeDist = Math.toRadians(anotherPosition.longitude - this.longitude);
        double tmp1 = Math.sin(latitudeDist/2) * Math.sin(latitudeDist/2)
                     + Math.cos(Math.toRadians(anotherPosition.latitude)) * Math.cos(Math.toRadians(this.latitude))
                     * Math.sin(longitudeDist/2) * Math.sin(longitudeDist/2);
        double tmp2 = 2 * Math.atan2(Math.sqrt(tmp1), Math.sqrt(1-tmp1));

        double distance = radius * tmp2 * 1000; //converting to meters

        double altitudeDist = anotherPosition.altitude - this.altitude;

        distance = Math.pow(distance, 2) * Math.pow(altitudeDist, 2);

        return distance;
    }
}


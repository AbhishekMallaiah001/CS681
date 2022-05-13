package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicReference;

public class Aircraft {
    private AtomicReference<Position> position;

    public Aircraft(Position pos) {
        position  = new AtomicReference<>(pos);
    }

    public void setPosition (Position pos) {
        position.set(pos);
    }

    public AtomicReference<Position> getPosition () {
        return position;
    }
}

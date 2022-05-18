package edu.umb.cs681.hw9;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton {
    private ConcurrentSingleton() {};
    private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<>();

    public static AtomicReference<ConcurrentSingleton> getInstance() {
        instance.compareAndSet(null, new ConcurrentSingleton());
        return instance;
    }
}

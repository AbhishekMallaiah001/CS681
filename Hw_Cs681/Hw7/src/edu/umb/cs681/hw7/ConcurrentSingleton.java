package edu.umb.cs681.hw7;


import java.util.concurrent.locks.ReentrantLock;



public class ConcurrentSingleton implements Runnable{
    private ConcurrentSingleton() {
    };

    private static ConcurrentSingleton instance = null;
    private static ReentrantLock lock = new ReentrantLock();

    // Factory method to create or return the singleton instance
    public static ConcurrentSingleton getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ConcurrentSingleton();
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(() -> {
                System.out.println(ConcurrentSingleton.getInstance());
            });

            t.start();
        }
    }
    @Override
    public void run() {
        System.out.println(ConcurrentSingleton.getInstance());

    }
}
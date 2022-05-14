package edu.umb.cs681.hw14;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {

    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;


    public void setDone() {
        lock.lock();
        try {
            done = true;

        } finally {
            lock.unlock();
        }
    }

    public void run() {
        Random rand = new Random();
        List<String> givenList = Arrays.asList("dummyFile1.txt", "dummyFile2.txt", "dummyFile3.txt", "dummyFile4.txt", "dummyFile5.txt");


        while (true) {
            lock.lock();
            try {
                if (done) {
                    System.out.println("Threads execution done" +
                            "");
                    break;
                }
            } finally {
                lock.unlock();
            }

            int randomIndex = rand.nextInt(givenList.size());
            String randomFile = givenList.get(randomIndex);
            Path filePath = Paths.get(randomFile);

            AccessCounter.getInstance().increment(filePath);
            System.out.println("The Current Thread : " + Thread.currentThread().getName() + " FileName :  " + randomFile + " AccessCount:  " + AccessCounter.getInstance().getCount(filePath));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Interrupt Exception Happened: " + Thread.currentThread().getName() + " " + e);
                continue;
            }
        }
    }

    public static void main(String args[]) {
        ArrayList<RequestHandler> rhs = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            rhs.add(new RequestHandler());
            threads.add(new Thread(rhs.get(i)));
            threads.get(i).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        for (int i = 0; i < 10; i++) {
            rhs.get(i).setDone();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        threads.forEach(t -> t.interrupt());

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
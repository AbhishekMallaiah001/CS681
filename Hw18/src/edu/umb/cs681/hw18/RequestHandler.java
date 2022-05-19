package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Callable {

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

    @Override
    public Object call() {
        Random rand = new Random();
        List<String> givenList = Arrays.asList("dummyFile1.txt", "dummyFile2.txt", "dummyFile3.txt", "dummyFile4.txt", "dummyFile5.txt");


        while (true) {
            lock.lock();
            try {
                if (done) {
                    System.out.println("Callable task completed");
                    break;
                }
            } finally {
                lock.unlock();
            }

            int randomIndex = rand.nextInt(givenList.size());
            String randomFile = givenList.get(randomIndex);
            Path filePath = Paths.get(randomFile);

            AccessCounter.getInstance().increment(filePath);
            System.out.println("The Current Thread in the Thread pool: " + Thread.currentThread().getName() + " FileName :  " + randomFile + " AccessCount:  " + AccessCounter.getInstance().getCount(filePath));
            setDone();
        }
        return null;
    }

    public static void main(String args[]) {
        ArrayList<RequestHandler> rhs = new ArrayList<>();
        ExecutorService executer = Executors.newFixedThreadPool(10);
        List<Callable<Void>> callables = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            rhs.add(new RequestHandler());
            callables.add(rhs.get(i));
        }

        try {
            executer.invokeAny(callables);
            executer.shutdownNow();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
package edu.umb.cs681.hw4;

import java.util.ArrayList;
import java.util.List;

public class ClientCode {

    public static void main(String[] args) {

        System.out.println(" The Threads Run time");
        for (int i = 1; i <= 16; i*=2) {
            runThreads(i);
        }
    }

    public static void runThreads(int threadCount) {
        long interval = 2000000/threadCount;
        List<Thread> threads = new ArrayList<>();
        List<RunnablePrimeGenerator> rpgs = new ArrayList<>();
        long end = interval;
        long start = 1;
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < threadCount; i++) {

            RunnablePrimeGenerator rpg = new RunnablePrimeGenerator(start, end);

            Thread t = new Thread(rpg);
            t.start();
            threads.add(t);
            rpgs.add(rpg);
            start = end+1;
            end+= interval;
        }

       for(Thread t1:threads) {
           try {
               t1.join();
           } catch (InterruptedException e) {
           }
       }

       long primeNumbers = 0;
        for(RunnablePrimeGenerator r:rpgs) {
            primeNumbers += r.getPrimes().size();
        }

        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;

        System.out.println("\n"+ primeNumbers + " are the total Prime numbers found");

        System.out.println("For threadCount "+threadCount+" Run time is : " +runTime+"  ms");
    }

}

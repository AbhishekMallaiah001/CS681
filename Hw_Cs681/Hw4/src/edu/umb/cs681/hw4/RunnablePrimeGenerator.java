package edu.umb.cs681.hw4;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {

    public RunnablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void run() {
        generatePrimes();
    }

    public static void main(String[] args) {

        System.out.println("Calculating Threads elapsed time using RunnablePrimeGenerator");

        // Single-threaded prime number generation (with generatePrimes())
        RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1, 2000000);
        Thread t = new Thread(gen1);

        long startTimeT1 = System.currentTimeMillis();
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
        }

        long stopTimeT1 = System.currentTimeMillis();
        long elapsedTimeT1 = stopTimeT1 - startTimeT1;

        gen1.getPrimes().forEach((Long prime) -> System.out.print(prime + ", "));

        long primeNum = gen1.getPrimes().size();
        System.out.println("\n" + primeNum + " prime numbers are found in total.");
        System.out.println("1 Thread elapsed time: " + elapsedTimeT1 + " ms");

        // Multi-threaded (2 threads) prime number generation (with generatePrimes())
        RunnablePrimeGenerator gen1Case2 = new RunnablePrimeGenerator(1, 1000000);
        RunnablePrimeGenerator gen2Case2 = new RunnablePrimeGenerator(1000001, 2000000);
        Thread t1 = new Thread(gen1Case2);
        Thread t2 = new Thread(gen2Case2);

        long startTimeT2 = System.currentTimeMillis();
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }

        long stopTimeT2 = System.currentTimeMillis();
        long elapsedTimeT2 = stopTimeT2 - startTimeT2;
        System.out.println("2 Threads elapsed time: " + elapsedTimeT2 + " ms");

        // Multi-threaded (4 threads) prime number generation (with generatePrimes())
        RunnablePrimeGenerator gen1Case3 = new RunnablePrimeGenerator(1, 500000);
        RunnablePrimeGenerator gen2Case3 = new RunnablePrimeGenerator(500001, 1000000);
        RunnablePrimeGenerator gen3Case3 = new RunnablePrimeGenerator(1000001, 1500000);
        RunnablePrimeGenerator gen4Case3 = new RunnablePrimeGenerator(1500001, 2000000);
        Thread t4_1 = new Thread(gen1Case3);
        Thread t4_2 = new Thread(gen2Case3);
        Thread t4_3 = new Thread(gen3Case3);
        Thread t4_4 = new Thread(gen4Case3);

        long startTimeT4 = System.currentTimeMillis();
        t4_1.start();
        t4_2.start();
        t4_3.start();
        t4_4.start();
        try {
            t4_1.join();
            t4_2.join();
            t4_3.join();
            t4_4.join();
        } catch (InterruptedException e) {
        }

        long stopTimeT4 = System.currentTimeMillis();
        long elapsedTimeT4 = stopTimeT4 - startTimeT4;
        System.out.println("4 Threads elapsed time: " + elapsedTimeT4 + " ms");

        // Multi-threaded (8 threads) prime number generation (with generatePrimes())
        RunnablePrimeGenerator gen1Case4 = new RunnablePrimeGenerator(1, 250000);
        RunnablePrimeGenerator gen2Case4 = new RunnablePrimeGenerator(250001, 500000);
        RunnablePrimeGenerator gen3Case4 = new RunnablePrimeGenerator(500001, 750000);
        RunnablePrimeGenerator gen4Case4 = new RunnablePrimeGenerator(750001, 1000000);
        RunnablePrimeGenerator gen5Case4 = new RunnablePrimeGenerator(1000001, 1250000);
        RunnablePrimeGenerator gen6Case4 = new RunnablePrimeGenerator(1250001, 1500000);
        RunnablePrimeGenerator gen7Case4 = new RunnablePrimeGenerator(1500001, 1750000);
        RunnablePrimeGenerator gen8Case4 = new RunnablePrimeGenerator(1750001, 2000000);
        Thread t8_1 = new Thread(gen1Case4);
        Thread t8_2 = new Thread(gen2Case4);
        Thread t8_3 = new Thread(gen3Case4);
        Thread t8_4 = new Thread(gen4Case4);
        Thread t8_5 = new Thread(gen5Case4);
        Thread t8_6 = new Thread(gen6Case4);
        Thread t8_7 = new Thread(gen7Case4);
        Thread t8_8 = new Thread(gen8Case4);

        long startTimeT8 = System.currentTimeMillis();
        t8_1.start();
        t8_2.start();
        t8_3.start();
        t8_4.start();
        t8_5.start();
        t8_6.start();
        t8_7.start();
        t8_8.start();
        try {
            t8_1.join();
            t8_2.join();
            t8_3.join();
            t8_4.join();
            t8_5.join();
            t8_6.join();
            t8_7.join();
            t8_8.join();
        } catch (InterruptedException e) {
        }

        long stopTimeT8 = System.currentTimeMillis();
        long elapsedTimeT8 = stopTimeT8 - startTimeT8;
        System.out.println("8 Threads elapsed time: " + elapsedTimeT8 + " ms");

        // Multi-threaded (16 threads) prime number generation (with generatePrimes())
        RunnablePrimeGenerator gen1Case5 = new RunnablePrimeGenerator(1, 125000);
        RunnablePrimeGenerator gen2Case5 = new RunnablePrimeGenerator(125001, 250000);
        RunnablePrimeGenerator gen3Case5 = new RunnablePrimeGenerator(250001, 375000);
        RunnablePrimeGenerator gen4Case5 = new RunnablePrimeGenerator(375001, 500000);
        RunnablePrimeGenerator gen5Case5 = new RunnablePrimeGenerator(500001, 625000);
        RunnablePrimeGenerator gen6Case5 = new RunnablePrimeGenerator(625001, 750000);
        RunnablePrimeGenerator gen7Case5 = new RunnablePrimeGenerator(750001, 875000);
        RunnablePrimeGenerator gen8Case5 = new RunnablePrimeGenerator(875001, 1000000);
        RunnablePrimeGenerator gen9Case5 = new RunnablePrimeGenerator(1000001, 1125000);
        RunnablePrimeGenerator gen10Case5 = new RunnablePrimeGenerator(1125001, 1250000);
        RunnablePrimeGenerator gen11Case5 = new RunnablePrimeGenerator(1250001, 1375000);
        RunnablePrimeGenerator gen12Case5 = new RunnablePrimeGenerator(1375001, 1500000);
        RunnablePrimeGenerator gen13Case5 = new RunnablePrimeGenerator(1500001, 1625000);
        RunnablePrimeGenerator gen14Case5 = new RunnablePrimeGenerator(1625001, 1750000);
        RunnablePrimeGenerator gen15Case5 = new RunnablePrimeGenerator(1750001, 1875000);
        RunnablePrimeGenerator gen16Case5 = new RunnablePrimeGenerator(1875001, 2000000);

        Thread t16_1 = new Thread(gen1Case5);
        Thread t16_2 = new Thread(gen2Case5);
        Thread t16_3 = new Thread(gen3Case5);
        Thread t16_4 = new Thread(gen4Case5);
        Thread t16_5 = new Thread(gen5Case5);
        Thread t16_6 = new Thread(gen6Case5);
        Thread t16_7 = new Thread(gen7Case5);
        Thread t16_8 = new Thread(gen8Case5);
        Thread t16_9 = new Thread(gen9Case5);
        Thread t16_10 = new Thread(gen10Case5);
        Thread t16_11 = new Thread(gen11Case5);
        Thread t16_12 = new Thread(gen12Case5);
        Thread t16_13 = new Thread(gen13Case5);
        Thread t16_14 = new Thread(gen14Case5);
        Thread t16_15 = new Thread(gen15Case5);
        Thread t16_16 = new Thread(gen16Case5);
        long startTimeT16 = System.currentTimeMillis();
        t16_1.start();
        t16_2.start();
        t16_3.start();
        t16_4.start();
        t16_5.start();
        t16_6.start();
        t16_7.start();
        t16_8.start();
        t16_9.start();
        t16_10.start();
        t16_11.start();
        t16_12.start();
        t16_13.start();
        t16_14.start();
        t16_15.start();
        t16_16.start();
        try {
            t16_1.join();
            t16_2.join();
            t16_3.join();
            t16_4.join();
            t16_5.join();
            t16_6.join();
            t16_7.join();
            t16_8.join();
            t16_9.join();
            t16_10.join();
            t16_11.join();
            t16_12.join();
            t16_13.join();
            t16_14.join();
            t16_15.join();
            t16_16.join();
        } catch (InterruptedException e) {
        }

        long stopTimeT16 = System.currentTimeMillis();
        long elapsedTimeT16 = stopTimeT16 - startTimeT16;
        System.out.println("16 Threads elapsed time: " + elapsedTimeT16 + " ms");

    }

}
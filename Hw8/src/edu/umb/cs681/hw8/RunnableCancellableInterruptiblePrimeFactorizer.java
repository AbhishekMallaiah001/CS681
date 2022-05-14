package edu.umb.cs681.hw8;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
    private boolean done = false;
    private final ReentrantLock lock = new ReentrantLock();

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to) {
            lock.lock();
            try {
                if (done) {
                    System.out.println("Done flag set to true, Generation of PrimerFactors stopped  ");
                    break;
                }
                if (divisor > 2 && divisor % 2 == 0) {
                    divisor++;
                    continue;
                }
                if (dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                } else {
                    if (divisor == 2) {
                        divisor++;
                    } else {
                        divisor += 2;
                    }
                }
            } finally {
                lock.unlock();
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }
    }

    public static void main(String[] args) {
        /* Two step Terminating Threads */
        int input = 100000;
        System.out.println("Prime factors of " + input);
        RunnableCancellableInterruptiblePrimeFactorizer rpg = new RunnableCancellableInterruptiblePrimeFactorizer(
                input, 2, (long) Math.sqrt(input));
        Thread t = new Thread(rpg);
        System.out.println("Thread #" + t.getId() + " performs factorization in the range of "
                + rpg.getFrom() + "->" + rpg.getTo());
        t.start();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
        rpg.setDone();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The Prime factors of " + input + " are " + rpg.getPrimeFactors());
    }
}
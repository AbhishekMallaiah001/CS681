package edu.umb.cs681.hw8;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

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
                    break;
                }
                if (divisor > 2 && isEven(divisor)) {
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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }
    }

    public static void main(String[] args) {

        //   2-step thread termination
        System.out.println("Factorization of 131 with 2-step thread termination");
        RunnableCancellableInterruptiblePrimeFactorizer runnable2 = new RunnableCancellableInterruptiblePrimeFactorizer(
                131, 2, (long) Math.sqrt(131));
        Thread thread2 = new Thread(runnable2);
        System.out.println("Thread #" + thread2.getId() + " performs factorization in the range of "
                + runnable2.getFrom() + "->" + runnable2.getTo());
        thread2.start();
        runnable2.setDone();
        thread2.interrupt();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final result: " + runnable2.getPrimeFactors() + "\n");

    }
}

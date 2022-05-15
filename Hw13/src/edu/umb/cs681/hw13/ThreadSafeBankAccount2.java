package edu.umb.cs681.hw13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount2 {


    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();

    private Condition sufficientFundsCondition = lock.newCondition();
    private Condition belowUpperLimitFundsCondition = lock.newCondition();

    public ThreadSafeBankAccount2() {
    }


    public void deposit(double amount) {
        lock.lock();
        try {

            while (balance >= 300) {
                try {
                    System.out.println(Thread.currentThread().getName() + " Upper Limit reached for Deposit");
                    belowUpperLimitFundsCondition.await();

                } catch (InterruptedException ie) {
                    System.out.println(ie);
                }
            }
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " Balance after deposited :" + balance);
            sufficientFundsCondition.signalAll();

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " Deposit Thread Unlocked");
        }

    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            while (balance <= 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " Low Balance, Cannot withdraw");
                    sufficientFundsCondition.await();

                } catch (InterruptedException ie) {
                    System.out.println(ie);

                }
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + "  Balance after Withdrawn : " + balance);
            belowUpperLimitFundsCondition.signalAll();

        } catch (Exception e) {
            System.out.println(e);

        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+" Withdraw Thread unlocked.");
        }
    }
}
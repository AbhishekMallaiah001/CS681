package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;

public class DepositRunnable implements Runnable{

    private ThreadSafeBankAccount2 account = null;
    private ReentrantLock lock = new ReentrantLock();
    private volatile boolean done = false;

    public DepositRunnable(ThreadSafeBankAccount2 account) {
        this.account = account;
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void run() {
        while (true) {
            lock.lock();
            try{
                if (done) {
                    System.out.println(Thread.currentThread().getId() + " Deposit Thread Execution Done");
                    break;
                }
            } finally {
                lock.unlock();
            }
            account.deposit(100);
            System.out.println(Thread.currentThread().getId() + " Deposited $100 to the Account");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e);
                continue;
            }
        }

    }
}
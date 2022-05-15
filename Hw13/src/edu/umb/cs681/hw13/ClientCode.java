package edu.umb.cs681.hw13;

import java.util.ArrayList;

public class ClientCode {

    public static void main(String[] args) {

        ThreadSafeBankAccount2 account = new ThreadSafeBankAccount2();
        DepositRunnable deposit = new DepositRunnable(account);
        WithdrawRunnable withdraw = new WithdrawRunnable(account);
        ArrayList<Thread> depositThreads = new ArrayList<>();
        ArrayList<Thread> withdrawThreads = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            depositThreads.add(new Thread(deposit));
            depositThreads.get(i).start();
        }

        for (int i = 0; i < 8; i++) {
            withdrawThreads.add(new Thread(withdraw));
            withdrawThreads.get(i).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        deposit.setDone();
        withdraw.setDone();


        for (int i = 0; i < 8; i++) {
            depositThreads.get(i).interrupt();
        }

        for (int i = 0; i < 8; i++) {
            withdrawThreads.get(i).interrupt();
        }

    }

}
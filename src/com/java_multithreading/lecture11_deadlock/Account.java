package com.java_multithreading.lecture11_deadlock;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/Deadlock_11/Account.java
 */
public class Account {

    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account acct1, Account acct2, int amount) {
        acct1.withdraw(amount);
        acct2.deposit(amount);
    }

}

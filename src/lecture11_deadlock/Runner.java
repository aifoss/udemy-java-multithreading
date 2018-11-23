package lecture11_deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/Deadlock_11/Runner.java
 */
public class Runner {

    private Account acct1 = new Account();
    private Account acct2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while (true) {
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            }
            finally {
                if (gotFirstLock && gotSecondLock) {
                    return;
                }
                if (gotFirstLock) {
                    firstLock.unlock();
                }
                if (gotSecondLock) {
                    secondLock.unlock();
                }
            }

            Thread.sleep(1);
        }
    }

    public void firstThread() throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);

            try {
                Account.transfer(acct1, acct2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }

    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock2, lock1);

            try {
                Account.transfer(acct2, acct1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finish() {
        System.out.println("Account 1 balance: "+acct1.getBalance());
        System.out.println("Account 2 balance: "+acct2.getBalance());
        System.out.println("Total balance: "+(acct1.getBalance()+acct2.getBalance()));
    }

}

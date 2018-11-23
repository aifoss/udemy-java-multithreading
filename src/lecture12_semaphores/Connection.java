package lecture12_semaphores;

import java.util.concurrent.Semaphore;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/Semaphores_12/Connection.java
 */
public class Connection {

    private static Connection instance = new Connection();

    private Semaphore sem = new Semaphore(10, true);

    private int connections = 0;

    private Connection() {

    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            sem.acquire();
            doConnect();
        } catch (InterruptedException e) {

        } finally {
            sem.release();
        }
    }

    public void doConnect() {
        synchronized (this) {
            connections++;
            System.out.println("Connections incremented by thread: "+Thread.currentThread().getName());
            System.out.println("Current connections: "+connections);
        }

        try {
            System.out.println("Thread sleeping: "+Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        synchronized (this) {
            connections--;
            System.out.println("Connections decremented by thread: "+Thread.currentThread().getName());
            System.out.println("Current connections: "+connections);
        }
    }

}

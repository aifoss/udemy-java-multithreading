package lecture04_multiple_locks_using_synchronized_code_blocks;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/LockObjects_4/App.java
 */
public class App {

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.main();
    }

}

package ru.geekbrains.preparation.lesson3;

import java.util.concurrent.locks.Lock;

public class Counter extends Thread {
    private static int counter = 0;
    private static final int LIMIT = 20;
    private final Lock lock;

    public Counter(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (counter < LIMIT) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " : " + counter);
                counter++;
            } finally {
                lock.unlock();
            }
        }
    }
}

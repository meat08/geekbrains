package ru.geekbrains.preparation.lesson3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLesson3 {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Object lockObject = new Object();
        AtomicInteger counter = new AtomicInteger(0);
        ex.submit(new PingPong(lockObject, "ping", counter));
        ex.submit(new PingPong(lockObject, "pong", counter));

        Lock lock = new ReentrantLock();
        ex.submit(new Counter(lock));
        ex.submit(new Counter(lock));

        ex.shutdown();
    }
}

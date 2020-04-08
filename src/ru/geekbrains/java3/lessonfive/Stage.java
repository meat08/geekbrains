package ru.geekbrains.java3.lessonfive;

import java.util.concurrent.CountDownLatch;

public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c, CountDownLatch latchRace);
}
package ru.geekbrains.java1.lessonsix;

import java.util.Random;

public abstract class Animals {
    private String name;
    private static int count;
    public static Random random = new Random();

    public abstract void run(int distance);
    public abstract void swim(int distance);
    public abstract void jump(double height);

    public Animals(String name) {
        this.name = name;
        count++;
    }

    public String getName(){ return name; }
    public static int getCount() { return count; }

}

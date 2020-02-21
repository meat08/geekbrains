package ru.geekbrains.java2.lessonone;

import java.util.Random;

public class Human implements Members {
    public final String name;
    public final int age;
    private final int maxDistance;
    private final int maxHeight;
    private boolean successAction = true;

    public Human (String name, int age) {
        this.name = name;
        this.age = age;
        Random random = new Random();
        this.maxDistance = random.nextInt(1001);
        this.maxHeight = random.nextInt(3);
    }

    @Override
    public int jump() {
        System.out.print("Человек по имени " + this.name + ", возрастом " + this.age + " лет прыгнул");
        return this.maxHeight;
    }

    @Override
    public int run() {
        System.out.print("Человек по имени " + this.name + ", возрастом " + this.age + " лет побежал");
        return this.maxDistance;
    }

    @Override
    public void setSuccessAction(boolean act) {
        this.successAction = act;
    }

    @Override
    public boolean getSuccessAction() {
        return successAction;
    }
}

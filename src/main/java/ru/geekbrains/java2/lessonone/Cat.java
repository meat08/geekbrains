package ru.geekbrains.java2.lessonone;

import java.util.Random;

public class Cat implements Members {
    public final String name;
    private final int maxDistance;
    private final int maxHeight;
    private boolean successAction = true;

    public Cat(String name) {
        this.name = name;
        Random random = new Random();
        this.maxDistance = random.nextInt(601);
        this.maxHeight = random.nextInt(5);
    }

    @Override
    public int jump() {
        System.out.print("Кот по имени " + this.name + " прыгнул");
        return maxHeight;
    }

    @Override
    public int run() {
        System.out.print("Кот по имени " + this.name + " побежал");
        return maxDistance;
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

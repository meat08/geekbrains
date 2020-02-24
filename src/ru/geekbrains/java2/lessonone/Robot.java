package ru.geekbrains.java2.lessonone;

import java.util.Random;

public class Robot implements Members {
    public final String serialNumber;
    private final int maxDistance;
    private final int maxHeight;
    private boolean successAction = true;

    public Robot(String serial) {
        this.serialNumber = serial;
        Random random = new Random();
        this.maxDistance = random.nextInt(5001);
        this.maxHeight = random.nextInt(10);
    }

    @Override
    public int run() {
        System.out.print("Учетная единица " + this.serialNumber + " побежал");
        return maxDistance;
    }

    @Override
    public int jump() {
        System.out.print("Учетная единица " + this.serialNumber + " прыгнул");
        return maxHeight;
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

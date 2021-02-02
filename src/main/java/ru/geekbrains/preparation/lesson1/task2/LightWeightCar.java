package ru.geekbrains.preparation.lesson1.task2;

public class LightWeightCar extends Car implements Movable {
    @Override
    public void open() {
        System.out.println("Car is open");
    }

    @Override
    public void move() {
        super.start();
        System.out.println("Car is moving");
    }
}

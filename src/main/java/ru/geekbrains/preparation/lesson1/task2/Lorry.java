package ru.geekbrains.preparation.lesson1.task2;

//Исправления:
// implements вместо extends для интерфейсов,
// отсутствующая реализация метода open() класса Car
public class Lorry extends Car implements Movable, Stoppable {
    @Override
    public void open() {
        System.out.println("Car is open");
    }

    @Override
    public void move() {
        super.start();
        System.out.println("Car is moving");
    }

    @Override
    public void stop() {
        System.out.println("Car is stop");
    }
}

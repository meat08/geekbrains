package ru.geekbrains.preparation.lesson1.task3;

public class Circle implements Shape {
    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public int calculateArea() {
        return (int) (radius * Math.PI);
    }
}

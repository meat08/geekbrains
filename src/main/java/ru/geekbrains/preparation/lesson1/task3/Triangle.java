package ru.geekbrains.preparation.lesson1.task3;

public class Triangle implements Shape {
    private final int base;
    private final int height;

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public int calculateArea() {
        return (base * height)/2;
    }
}

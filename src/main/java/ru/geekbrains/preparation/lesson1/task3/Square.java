package ru.geekbrains.preparation.lesson1.task3;

public class Square extends Shape {
    private final int sideLength;

    public Square(int sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public int calculateArea() {
        return (int) Math.pow(sideLength, 2);
    }
}

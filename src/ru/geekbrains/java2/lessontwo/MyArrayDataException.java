package ru.geekbrains.java2.lessontwo;

public class MyArrayDataException extends  NumberFormatException {
    private int x;
    private int y;

    public MyArrayDataException (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getMessage() {
        return "Ошибка преобразования. В ячейке [" + x + "][" + y + "] не число.";
    }
}

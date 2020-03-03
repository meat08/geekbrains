package ru.geekbrains.java2.lessontwo;

public class MyArrayDataException extends  NumberFormatException {
    public MyArrayDataException (int x, int y) {
        super("Ошибка преобразования. В ячейке [" + x + "][" + y + "] не число.");
    }
}

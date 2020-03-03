package ru.geekbrains.java2.lessontwo;

public class MyArraySizeException extends ArrayIndexOutOfBoundsException {
    public MyArraySizeException() {
        super("Ошибка размера массива. Максимальный размер 4.");
    }
}

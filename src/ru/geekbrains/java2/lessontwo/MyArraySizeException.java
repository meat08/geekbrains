package ru.geekbrains.java2.lessontwo;

public class MyArraySizeException extends ArrayIndexOutOfBoundsException {
    @Override
    public String getMessage() {
        return "Ошибка размера массива. Максимальный размер 4.";
    }
}
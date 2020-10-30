package ru.geekbrains.algorithms.base.myarray;

public class CustomArrayIndexOutOfBoundException extends RuntimeException {
    public CustomArrayIndexOutOfBoundException(int index, int size) {
        super(String.format("Invalid index %d for array with length %d", index, size));
    }
}

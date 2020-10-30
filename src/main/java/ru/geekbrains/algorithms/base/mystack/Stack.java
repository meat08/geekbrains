package ru.geekbrains.algorithms.base.mystack;

public interface Stack<E> {
    void push(E value);
    E pop();
    E peek();
    int size();
    boolean isFull();
    default boolean isEmpty() {
        return size() == 0;
    }
}

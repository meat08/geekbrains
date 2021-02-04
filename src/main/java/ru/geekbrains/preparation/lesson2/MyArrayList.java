package ru.geekbrains.preparation.lesson2;

public interface MyArrayList<E> {
    void add(E value);
    void add(E value, int index);
    boolean remove(E value);
    E remove(int index);
    E get(int index);
    int indexOf(E value);
    default boolean contains(E value) {
        return indexOf(value) != -1;
    }
    int size();
    default boolean isEmpty() {
        return size() == 0;
    }
    void trimToSize();


}

package ru.geekbrains.preparation.lesson2;

import java.util.Arrays;

public class MyArrayListImpl<E extends Comparable<? super E>> implements MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 8;
    protected E[] data;
    protected int size;

    public MyArrayListImpl() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyArrayListImpl(int capacity) {
        this.data = (E[]) new Comparable[capacity];
    }

    @Override
    public void add(E value) {
        checkAndGrow();
        data[size++] = value;
    }

    @Override
    public void add(E value, int index) {
        checkAndGrow();
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            if (size - index >= 0)  {
                System.arraycopy(data, index, data, index + 1, size - index);
            }
            data[index] = value;
            size++;
        }
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        return index != -1 && (remove(index) != null);
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedValue = data[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(data, index +1, data, index, size - 1 - index);
        }
        data[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void trimToSize() {
        data = Arrays.copyOf(data, size);
    }

    private void checkAndGrow() {
        if (data.length == size) {
            data = Arrays.copyOf(data, calculateNewLength());
        }
    }

    private int calculateNewLength() {
        return size > 0 ? size * 2 : 1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}

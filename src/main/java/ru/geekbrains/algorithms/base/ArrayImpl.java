package ru.geekbrains.algorithms.base;

import java.util.Arrays;

public class ArrayImpl <E extends Comparable<? super E>> implements Array<E> {
    private static final int DEFAULT_CAPACITY = 8;
    protected E[] data;
    protected int size;

    public ArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayImpl(int initCapacity) {
        this.data = (E[]) new Comparable[initCapacity];
    }

    @Override
    public void add(E value) {
        checkAndGrow();
        data[size++] = value;
    }

    @Override
    public void insert(E value, int index) {
        checkAndGrow();
        if (index == size) {
            add(value);
        }
        else {
            checkIndex(index);
            if (size - index >= 0)  {
                System.arraycopy(data, index, data, index + 1, size - index);
            }
            data[index] = value;
            size++;
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
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
    public void display() {
        System.out.println(toString());
    }

    @Override
    public void trimToSize() {
        data = Arrays.copyOf(data, size);
    }

    @Override
    public void sortBubble() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (data[j].compareTo(data[j+1]) > 0) {
                    swap(j, j+1);
                }
            }
        }
    }

    @Override
    public void sortSelect() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(minIndex, i);
        }
    }

    @Override
    public void sortInsert() {
        for (int i = 1; i < size; i++) {
            E temp = data[i];
            int in = i;
            while (in > 0 && data[in - 1].compareTo(temp) >= 0) {
                data[in] = data[in - 1];
                in--;
            }
            data[in] = temp;
        }
    }

    private void swap(int indexA, int indexB) {
        E temp = data[indexA];
        data[indexA] = data[indexB];
        data[indexB] = temp;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new CustomArrayIndexOutOfBoundException(index, size);
        }
    }

    private void checkAndGrow() {
        if (data.length == size) {
            data = Arrays.copyOf(data, calculateNewLength());
        }
    }

    private int calculateNewLength() {
        return size > 0 ? size *2 : 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]);
            sb.append(", ");
        }
        if (size > 0) {
            sb.append(data[size - 1]);
        }
        sb.append("]");
        return sb.toString();
    }
}

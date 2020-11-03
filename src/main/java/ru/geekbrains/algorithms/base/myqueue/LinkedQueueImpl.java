package ru.geekbrains.algorithms.base.myqueue;

import ru.geekbrains.algorithms.base.mylinkedlist.TwoSideLinkedList;
import ru.geekbrains.algorithms.base.mylinkedlist.TwoSideLinkedListImpl;

public class LinkedQueueImpl<E> implements Queue<E> {

    private TwoSideLinkedList<E> data;

    public LinkedQueueImpl() {
        this.data = new TwoSideLinkedListImpl<>();
    }

    @Override
    public boolean insert(E value) {
        data.insertLast(value);
        return true;
    }

    @Override
    public E remove() {
        return data.removeFirst();
    }

    @Override
    public E peekHead() {
        return data.getFirst();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isFull() {
        return false;
    }
}

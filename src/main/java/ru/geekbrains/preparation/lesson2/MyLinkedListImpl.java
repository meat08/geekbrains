package ru.geekbrains.preparation.lesson2;

import java.util.Iterator;

public class MyLinkedListImpl<E> implements MyLinkedList<E> {
    protected int size;
    protected Node<E> firstElement;

    @Override
    public void add(E value) {
        firstElement = new Node<>(value, firstElement);
        size++;
    }

    @Override
    public boolean remove(E value) {
        Node<E> current = firstElement;
        Node<E> previous = null;
        while (current != null) {
            if (current.item.equals(value)) {
                break;
            }
            previous = current;
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        if (current == firstElement) {
            firstElement = firstElement.next;
        }
        else {
            previous.next = current.next;
        }
        current.next = null;
        current.item = null;
        return true;
    }

    @Override
    public boolean contains(E value) {
        Node<E> current = firstElement;
        while (current != null) {
            if (current.item.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public E getFirst() {
        return firstElement.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(firstElement);
    }
}

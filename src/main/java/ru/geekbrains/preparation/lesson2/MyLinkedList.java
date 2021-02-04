package ru.geekbrains.preparation.lesson2;

import java.util.Iterator;

//Простой связанный список, без методов вставки в начало, без индексов и пр.
public interface MyLinkedList<E> extends Iterable<E> {
    void add(E value);
    boolean remove(E value);
    boolean contains(E value);
    E getFirst();
    int size();
    boolean isEmpty();
    Iterator<E> iterator();

    class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    class ListIterator<E> implements Iterator<E> {
        private Node<E> current;

        public ListIterator(Node<E> first) {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E temp = current.item;
            current = current.next;
            return temp;
        }
    }
}

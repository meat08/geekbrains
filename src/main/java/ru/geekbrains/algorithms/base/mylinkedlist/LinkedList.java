package ru.geekbrains.algorithms.base.mylinkedlist;

import java.util.Iterator;

public interface LinkedList<E> extends Iterable<LinkedList.Node<E>> {

    void insertFirst(E value);
    E removeFirst();
    boolean remove(E value);
    boolean contains(E value);
    E getFirst();
    int size();
    boolean isEmpty();
    void display();
    Iterator<Node<E>> iterator();

    class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        public E getData() {
            return item;
        }
    }

    class ListIterator<E> implements Iterator<Node<E>> {

        private LinkedList.Node<E> current;

        public ListIterator(LinkedList.Node<E> first) {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public LinkedList.Node<E> next() {
            LinkedList.Node<E> temp = current;
            current = current.next;
            return temp;
        }
    }
}

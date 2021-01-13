package ru.geekbrains.algorithms.base.mylinkedlist;


public interface LinkedList<E> {

    void insertFirst(E value);
    E removeFirst();
    boolean remove(E value);
    boolean contains(E value);
    E getFirst();
    int size();
    boolean isEmpty();
    void display();

    class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}

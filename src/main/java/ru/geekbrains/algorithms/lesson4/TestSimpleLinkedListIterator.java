package ru.geekbrains.algorithms.lesson4;

import ru.geekbrains.algorithms.base.mylinkedlist.LinkedList;
import ru.geekbrains.algorithms.base.mylinkedlist.SimpleLinkedListImpl;

public class TestSimpleLinkedListIterator {
    public static void main(String[] args) {
        LinkedList<Integer> list = new SimpleLinkedListImpl<>();
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertFirst(4);
        list.insertFirst(5);
        list.display();

        for (LinkedList.Node<Integer> i : list) {
            System.out.println(i.getData());
        }

    }
}

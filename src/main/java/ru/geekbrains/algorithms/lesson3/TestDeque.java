package ru.geekbrains.algorithms.lesson3;

import ru.geekbrains.algorithms.base.myqueue.Deque;
import ru.geekbrains.algorithms.base.myqueue.DequeImpl;

public class TestDeque {
    public static void main(String[] args) {
        Deque<Integer> deque = new DequeImpl<>(5);
        System.out.println(deque.insertLeft(3));
        System.out.println(deque.insertLeft(2));
        System.out.println(deque.insertRight(4));
        System.out.println(deque.insertRight(5));
        System.out.println(deque.insertLeft(1));
        System.out.println(deque.insertLeft(6));

        System.out.println("Deque peek first: " + deque.peekFirst());
        System.out.println("Deque peek last: " + deque.peekLast());
        System.out.println("Deque size: " + deque.size());
        System.out.println("Deque is full: " + deque.isFull());

        while (!deque.isEmpty()) {
            System.out.println(deque.removeLeft());
        }

//        while (!deque.isEmpty()) {
//            System.out.println(deque.removeRight());
//        }

//        System.out.println(deque.removeLeft());
//        System.out.println(deque.removeRight());
//        System.out.println(deque.removeRight());
//        System.out.println(deque.removeRight());
//        System.out.println(deque.removeLeft());

    }
}

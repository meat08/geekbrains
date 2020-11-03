package ru.geekbrains.algorithms.lesson4.task2;

public class LinkIteratorApp {
    public static void main(String[] args) {
        IteratedLinkedList list = new IteratedLinkedList();
        LinkIterator itr = list.getIterator();
        itr.insertAfter("Artem", 20);
        itr.insertAfter("Artem1", 21);
        itr.insertAfter("Artem2", 22);
        itr.insertBefore("Sergey", 10);
        list.display();
        itr.reset();

        while (true) {
            if (itr.atEnd()) {
                System.out.println(itr.deleteCurrent());
                break;
            }
            else {
                System.out.println(itr.deleteCurrent());
                itr.nextLink();
                itr.reset();
            }
        }
    }

}

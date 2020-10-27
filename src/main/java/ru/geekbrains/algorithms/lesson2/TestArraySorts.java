package ru.geekbrains.algorithms.lesson2;

import ru.geekbrains.algorithms.base.ArrayImpl;

public class TestArraySorts {

    private static final int ARRAY_CAPACITY = 100000;

    public static void main(String[] args) {
        //Реализацию ArrayImpl взял из материалов урока.
        //Переписывал руками, чтобы лучше вникнуть.
        ArrayImpl<Integer> arrayBubble = new ArrayImpl<>(ARRAY_CAPACITY);
        ArrayImpl<Integer> arraySelect = new ArrayImpl<>(ARRAY_CAPACITY);
        ArrayImpl<Integer> arrayInsert = new ArrayImpl<>(ARRAY_CAPACITY);

        for (int i = 0; i < ARRAY_CAPACITY; i++) {
            arrayBubble.insert((int) (Math.random() * ARRAY_CAPACITY + 1), i);
        }

        for (int i = 0; i < ARRAY_CAPACITY; i++) {
            arrayInsert.insert(arrayBubble.get(i), i);
            arraySelect.insert(arrayBubble.get(i), i);
        }

        System.out.println("Start bubble sort.");
        long startTime = System.currentTimeMillis();
        arrayBubble.sortBubble();
        long endTime = System.currentTimeMillis();
        long bubbleSortTime = endTime - startTime;

        System.out.println("Start select sort.");
        startTime = System.currentTimeMillis();
        arraySelect.sortSelect();
        endTime = System.currentTimeMillis();
        long selectSortTime = endTime - startTime;

        System.out.println("Start insert sort.");
        startTime = System.currentTimeMillis();
        arrayInsert.sortInsert();
        endTime = System.currentTimeMillis();
        long insertSortTime = endTime - startTime;

        System.out.println("Finish. Sort times is: ");
        System.out.println("Bubble: " + bubbleSortTime + "ms.");
        System.out.println("Select: " + selectSortTime + "ms.");
        System.out.println("Insert: " + insertSortTime + "ms.");
    }
}

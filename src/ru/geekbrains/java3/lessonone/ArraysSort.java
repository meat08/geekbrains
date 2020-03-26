package ru.geekbrains.java3.lessonone;

import java.util.ArrayList;

public class ArraysSort <T> {

    public T[] changeArrayElement(T[] array) {
        T temp;
        if (array.length > 1) {
            temp = array[0];
            array[0] = array[1];
            array[1] = temp;
        }
        return array;
    }

    public ArrayList<T> convertToArrayList(T[] array) {
        ArrayList<T> list = new ArrayList<>();
        //Без использования Arrays.asList()
        for (T t : array) {
            list.add(t);
        }
        return list;
    }
}

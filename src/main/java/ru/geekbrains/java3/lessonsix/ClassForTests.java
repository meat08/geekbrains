package ru.geekbrains.java3.lessonsix;

import java.util.Arrays;

public class ClassForTests {

    public int[] cutArray (int[] array) {
        int fourIndex = -1;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == 4) fourIndex = i;
        }
        if(fourIndex == -1) throw new RuntimeException();
        else {
            array = Arrays.copyOfRange(array, fourIndex + 1, array.length);
        }
        return array;
    }

    public boolean checkOeAndFourInArray(int[] array) {
        boolean isOne = false;
        boolean isFour = false;
        for (int i : array) {
            if (i == 1) isOne = true;
            else if (i == 4) isFour = true;
        }
        return isOne & isFour;
    }
}

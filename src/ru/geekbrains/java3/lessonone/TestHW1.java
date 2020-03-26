package ru.geekbrains.java3.lessonone;

import ru.geekbrains.java3.lessonone.fruits.Apple;
import ru.geekbrains.java3.lessonone.fruits.Box;
import ru.geekbrains.java3.lessonone.fruits.Orange;

//import java.util.ArrayList;
import java.util.Arrays;

public class TestHW1 {
    public static void main(String[] args) {
        taskOneAndTwo();
        taskThree();
    }

    private static void taskOneAndTwo() {
        Integer[] arr1 = new Integer[] {1, 2, 3, 4};
        String[] arr2 = new String[] {"one", "two", "three"};
        Object[] arr3 = new Object[] {"one", 2, 1.2f};

        ArraysSort<Integer> integerArraysSort = new ArraysSort<>();
        ArraysSort<String> stringArraysSort = new ArraysSort<>();
        ArraysSort<Object> objectArraysSort = new ArraysSort<>();

        System.out.println(Arrays.toString(integerArraysSort.changeArrayElement(arr1)));
        System.out.println(Arrays.toString(stringArraysSort.changeArrayElement(arr2)));
        System.out.println(Arrays.toString(objectArraysSort.changeArrayElement(arr3)));

//        ArrayList<Integer> list1 = integerArraysSort.convertToArrayList(arr1);
//        ArrayList<String> list2 = stringArraysSort.convertToArrayList(arr2);
    }

    private static void taskThree() {
        Apple apple = new Apple(1.0f);
        Apple apple1 = new Apple(1.5f);
        Orange orange = new Orange(1.5f);
        Orange orange1 = new Orange(0.5f);

        Box<Apple> boxApple = new Box<>();
        Box<Apple> boxApple2 = new Box<>();
        Box<Orange> boxOrange = new Box<>();

        boxApple.addFruitToBox(apple);
        boxApple.addFruitToBox(apple);
        boxApple.addFruitToBox(apple1);

        boxApple2.addFruitToBox(apple);
        boxApple2.addFruitToBox(apple1);
        boxApple2.addFruitToBox(apple);

        boxOrange.addFruitToBox(orange);
        boxOrange.addFruitToBox(orange1);
        boxOrange.addFruitToBox(orange1);
        boxOrange.addFruitToBox(orange);
        boxOrange.addFruitToBox(orange);
        boxOrange.addFruitToBox(orange);

        System.out.println(boxApple2.compareBox(boxOrange));
        boxApple.moveFruitToAnotherBox(boxApple2);
        System.out.println(boxApple2.compareBox(boxOrange));

    }
}

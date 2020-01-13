package ru.geekbrains.lessonthree;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TaskTwo {

    public static String[] enWords = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
            "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive",
            "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

    public static String[] rusWords = {"яблоко", "апельсин", "лимон", "банан", "абрикос", "авокадо", "брокколи",
            "морковь", "вишня", "чеснок", "виноград", "дыня", "арбуз", "киви", "манго", "гриб", "орех", "оливки",
            "горох", "арахис", "груша", "перец", "ананас", "тыква", "картофель"};

    public static void quest(String[] arr) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int randIndex = random.nextInt(arr.length);
        String[] arrCompare = new String[arr[randIndex].length()];
        Arrays.fill(arrCompare, "#");

        System.out.println("Давайте сыграем в игру. Вам нужно отгадать слово, которое загадал компьютер");
        while(true) {
            System.out.println("Введите слово:");
            String answer = scanner.nextLine().toLowerCase();
            if(answer.equals(arr[randIndex])) {
                System.out.println("Вы угадали!");
                scanner.close();
                break;
            } else {
                //Эта дикая конструкция сравнивает ответ и вопрос посимвольно и заносит совпадения в массив
                //с соответствующими индексами
                for(int i = 0; i < answer.length() && i < arr[randIndex].length(); i++){
                    if(answer.charAt(i) == arr[randIndex].charAt(i)) {
                        arrCompare[i] = String.valueOf(answer.charAt(i));
                    }
                }
                System.out.println("Неверно. Букв угадано :");
                for(String letter : arrCompare) System.out.print(letter);
                System.out.print("##########\n");
            }
        }
    }

}
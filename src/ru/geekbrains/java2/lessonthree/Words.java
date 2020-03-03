package ru.geekbrains.java2.lessonthree;

import java.util.*;

public class Words {

    private String[] wordsArray = new String[]
            {"каждый", "охотник", "желает", "знать", "где", "сидит", "фазан",
                    "фазан", "сидит", "на", "ветке", "можжевелового", "дерева"};
    private Set<String> wordsSet = new TreeSet<>(Arrays.asList(wordsArray));
    private Map<String, Integer> wordsMap = new TreeMap<>();

    public void printWords() {
        System.out.printf("В массиве %s %s уникальными являются слова: ",
                Arrays.toString(wordsArray), System.lineSeparator());
        for (String word : wordsSet) {
            System.out.print(word + " ");
        }

        for (String wordSet : wordsSet) {
            int wordEntry = 0;
            for (String wordArr : wordsArray) {
                if(wordArr.equals(wordSet)) wordEntry++;
            }
            wordsMap.put(wordSet, wordEntry);
        }
        System.out.println();
        System.out.println("Количество повторений каждого слова:");
        for (String key : wordsMap.keySet()) {
            System.out.println(key + " " + wordsMap.get(key));
        }
    }
}

package ru.geekbrains.java2.lessonthree;

import java.util.*;

public class PhoneBook {
    private Map<String, String> phoneBook = new HashMap<>();

    public void add(String number, String firstName) {
        phoneBook.put(number, firstName);
    }

    public String get(String firstName) {
        List<String> numList = new ArrayList<>();
        for (String num : phoneBook.keySet()) {
            if(phoneBook.get(num).equals(firstName)) numList.add(num);
        }
        return numList.toString();
    }

}

package ru.geekbrains.java2.lessonthree;

public class Main {
    public static void main(String[] args) {
        Words words = new Words();
        words.printWords();

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("79991111111", "Иванов");
        phoneBook.add("79992222222", "Петров");
        phoneBook.add("79993333333", "Сидорович");
        phoneBook.add("79994444444", "Петров");
        System.out.println(phoneBook.get("Петров"));
    }
}

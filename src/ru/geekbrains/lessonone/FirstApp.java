package ru.geekbrains.lessonone;

import java.util.Scanner;

public class FirstApp {

    //Пункт 2.
    byte valByte = 127;
    short valShort = -32768;
    int valInt = 10000;
    long valLong = 99999999;
    float valFloat = 3.14f;
    double valDouble = 3.14;
    char valChar = 'a';
    boolean valBool = true;
    String valString = "foobar";
    String[] valArr = {"My", "first", "app"};

    //Пункт 1.
    public static void main(String[] args) {
        runMethods();
    }

    //Пункт 3.
    private static int calc(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    //Пункт 4.
    private static boolean checkNum(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    //Пункт 5.
    private static void checkPositive(int a) {
        if(a >= 0) {
            System.out.println("Число положительное");
        } else System.out.println("Число отрицательное");
    }

    //Пункт 6.
    private static boolean checkPositBool(int a) {
        return a < 0;
    }

    //Пункт 7.
    private static void printHello(String name) {
        System.out.println("Привет, " + name);
    }

    //Пункт 8. Я родил только способ с остатоком от деления
    private static void checkYear(int year) {
        if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            System.out.println("Год " + year + " високосный");
        } else System.out.println("Год " + year + " не является високосным");
    }

    //Вынес выполнение в отдельный метод.
    // Не уверен, что это было нужно, но мне показалось правильным.
    private static void runMethods() {

        Scanner scanner = new Scanner(System.in);
        int a, b, c, d;

        //Задание 3.
        //По умолчанию верим, что ввели число. Проверку добавлять не стал.
        System.out.println("Задание 3. Вычисление a * (b + (c / d)).");
        System.out.println("Введите число a");
        a = scanner.nextInt();
        System.out.println("Введите число b");
        b = scanner.nextInt();
        System.out.println("Введите число c");
        c = scanner.nextInt();
        System.out.println("Введите число d");
        d = scanner.nextInt();
        int intResult = calc(a,b,c,d);
        System.out.println("Результат = " + intResult);

        //Задание 4
        System.out.println("\nЗадание 4. Сумма a и b в пределах от 10 до 20.");
        System.out.println("Введите число a");
        a = scanner.nextInt();
        System.out.println("Введите число b");
        b = scanner.nextInt();
        if(checkNum(a, b)) {
            System.out.println("Сумма чисел " + a + " и " + b + " в диапазоне от 10 до 20");
        } else System.out.println("Сумма чисел " + a + " и " + b + " вне диапазона от 10 до 20");

        //Задание 5.
        System.out.println("\nЗадание 5. Положительное или отрицательное число.");
        System.out.println("Введите число a");
        a = scanner.nextInt();
        checkPositive(a);

        //Задание 6.
        System.out.println("\nЗадание 6. Отрицательное ли число.");
        System.out.println("Введите число a");
        a = scanner.nextInt();
        if(checkPositBool(a)) {
            System.out.println("Число отрицательное");
        } else System.out.println("Число вероятней всего положительное. Или ноль.");

        //Задание 7.
        System.out.println("\nЗадание 7. Вывод приветствия.");
        scanner.nextLine();
        System.out.println("Введите имя.");
        String name = scanner.nextLine();
        printHello(name);

        //Задание 8.
        System.out.println("\nЗадание 8. Определение високосного года");
        System.out.println("Введите год");
        int year = scanner.nextInt();
        checkYear(year);
    }
}
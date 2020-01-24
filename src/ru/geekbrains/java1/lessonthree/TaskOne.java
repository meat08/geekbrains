package ru.geekbrains.java1.lessonthree;

import java.util.Random;
import java.util.Scanner;

public class TaskOne {

    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);
    private static int tryCount = 3;
    private static int number = random.nextInt(10);

    public static void guessNum() {
        while (true) {
            if (tryCount <= 0) {
                System.out.println("Вы проиграли. Было загадано число: " + number);
                if (restartGame()) break;
            } else {
                System.out.println("Попробуйте угадать число от 0 до 9");
                while(!scanner.hasNextInt()) {
                    System.out.println("Введите число!");
                    scanner.next();
                }
                int answer = scanner.nextInt();
                tryCount--;
                if (answer == number) {
                    System.out.println("Вы победили!");
                    if (restartGame()) break;
                } else if (answer > number) {
                    System.out.println("Введенное число больше загаданного. Попыток осталось: " + tryCount);
                } else {
                    System.out.println("Введенное число меньше загаданного Попыток осталось: " + tryCount);
                }
            }
        }
        scanner.close();
    }

    private static boolean restartGame() {
        System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
        while(!scanner.hasNextInt()) {
            System.out.println("Введите число!");
            scanner.next();
        }
        int restart = scanner.nextInt();
        if (restart == 1) {
            number = random.nextInt(10);
            tryCount = 3;
            return false;
        } else return true;
    }
}
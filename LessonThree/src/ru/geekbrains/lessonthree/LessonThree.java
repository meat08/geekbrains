package ru.geekbrains.lessonthree;

import java.util.Scanner;

public class LessonThree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите игру, в которую хотите поиграть:");
        System.out.println(" Введите 1, чтобы выбрать игру \"Угадай число\".");
        System.out.println(" Введите 2, чтобы выбрать игру \"Угадай слово\".");
        while (true) {
                while(!scanner.hasNextInt()) {
                    System.out.println("Введите число!");
                    scanner.next();
                }
                int game = scanner.nextInt();
                if(game == 1) {
                    TaskOne.guessNum();
                    break;
                } else if(game == 2) {
                    System.out.println("Выберите набор слов для игры \"Угадай слово\"");
                    System.out.println(" Введите 1, чтобы выбрать английские слова.");
                    System.out.println(" Введите 2, чтобы выбрать русские слова.");
                    while (true) {
                        while (!scanner.hasNextInt()) {
                            System.out.println("Введите число!");
                            scanner.next();
                        }
                        int wordsType = scanner.nextInt();
                        if (wordsType == 1) {
                            TaskTwo.quest(TaskTwo.enWords);
                            break;
                        } else if (wordsType == 2) {
                            TaskTwo.quest(TaskTwo.rusWords);
                            break;
                        } else {
                            System.out.println("Введите 1 или 2");
                        }
                    } break;
                } else {
                    System.out.println("Введите 1 или 2");
                }
        }
        scanner.close();
    }
}
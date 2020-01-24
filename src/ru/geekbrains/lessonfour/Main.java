package ru.geekbrains.lessonfour;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Игра \"Крестики-нолики\". Выберите тип игры:");
        System.out.println("Введите 1 - игра с компьютером.");
        System.out.println("Введите 2 - игра на 2 игроков.");
        while(true) {
            while(!scanner.hasNextInt()) {
                System.out.println("Введите число!");
                scanner.next();
            }
            int gameType = scanner.nextInt();
            if(gameType == 1) {
                GameAi.startGame();
                break;
            }
            else if (gameType == 2) {
                GameMult.startGame();
                break;
            }
            else System.out.println("Введите 1 или 2");
        }
        scanner.close();

    }


}

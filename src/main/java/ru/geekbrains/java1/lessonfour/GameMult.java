package ru.geekbrains.java1.lessonfour;

import java.util.Random;

public class GameMult {
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static int turnCount = 0;
    private static Random random = new Random();
    private static GameMap gameMap = new GameMap();


    private static void humanTurn(char dot) {
        int x, y;
        do {
            System.out.println("Ход игрока " + dot);
            System.out.println("Введите координаты в формате X Y.\nОт 0 до " + GameMap.SIZE);
            while(!Main.scanner.hasNextInt()) {
                System.out.println("Введите число!");
                Main.scanner.next();
            }
            x = Main.scanner.nextInt() - 1;
            while(!Main.scanner.hasNextInt()) {
                System.out.println("Введите число!");
                Main.scanner.next();
            }
            y = Main.scanner.nextInt() - 1;
        } while (gameMap.isCellValid(x, y));
        gameMap.setMap(x, y, dot);
        turnCount++;
    }

    private static boolean endTurn(char dot) {
        if(gameMap.checkWin(dot)) {
            System.out.println("Победил игрок " + dot);
            return true;
        }
        if (gameMap.isMapFull()) {
            System.out.println("Ничья");
            return true;
        }
        return false;
    }

    public static void startGame(){
        gameMap.getMap();
        while(true){
            humanTurn(DOT_X);
            gameMap.getMap();
            if(turnCount >= 4) {
                if(endTurn(DOT_X)) break;
            }
            humanTurn(DOT_O);
            gameMap.getMap();
            if(turnCount >= 4) {
                if(endTurn(DOT_O)) break;
            }
        }
    }

}
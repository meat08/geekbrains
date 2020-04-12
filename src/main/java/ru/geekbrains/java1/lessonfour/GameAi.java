package ru.geekbrains.java1.lessonfour;

import java.util.Random;

public class GameAi {
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static int turnCount = 0;
    private static Random random = new Random();
    private static GameMap gameMap = new GameMap();


    private static void humanTurn() {
        int x, y;
        do {
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
        gameMap.setMap(x, y, DOT_X);
        turnCount++;
    }
    private static void aiTurn() {
        int x, y;
        do {
            if(turnCount >= 3) {
                x = gameMap.getAiX(DOT_X, DOT_O);
                y = gameMap.getAiY(DOT_X, DOT_O);
                if(x == 3 || y == 3) {
                    x = random.nextInt(GameMap.SIZE);
                    y = random.nextInt(GameMap.SIZE);
                }
            } else {
                x = random.nextInt(GameMap.SIZE);
                y = random.nextInt(GameMap.SIZE);
            }
        } while (gameMap.isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        gameMap.setMap(x, y, DOT_O);
        turnCount++;
    }

    private static boolean endTurn(char dot) {
        if(gameMap.checkWin(dot)) {
            if(dot == DOT_X) System.out.println("Победил человек");
            else if(dot == DOT_O) System.out.println("Победил компьютер");
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
            humanTurn();
            gameMap.getMap();
            if(turnCount >= 4) {
                if(endTurn(DOT_X)) break;
            }
            aiTurn();
            gameMap.getMap();
            if(turnCount >= 4) {
                if(endTurn(DOT_O)) break;
            }
        }
    }

}
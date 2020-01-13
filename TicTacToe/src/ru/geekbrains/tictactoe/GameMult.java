package ru.geekbrains.tictactoe;

import java.util.Random;

public class GameMult {
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static int turnCount = 0;
    private static Random random = new Random();
    private static Map map = new Map();


    private static void humanTurn(char dot) {
        int x, y;
        do {
            System.out.println("Ход игрока " + dot);
            System.out.println("Введите координаты в формате X Y.\nОт 0 до " + Map.SIZE);
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
        } while (map.isCellValid(x, y));
        map.setMap(x, y, dot);
        turnCount++;
    }

    private static boolean endTurn(char dot) {
        if(map.checkWin(dot)) {
            System.out.println("Победил игрок " + dot);
            return true;
        }
        if (map.isMapFull()) {
            System.out.println("Ничья");
            return true;
        }
        return false;
    }

    public static void startGame(){
        map.getMap();
        while(true){
            humanTurn(DOT_X);
            map.getMap();
            if(turnCount >= 4) {
                if(endTurn(DOT_X)) break;
            }
            humanTurn(DOT_O);
            map.getMap();
            if(turnCount >= 4) {
                if(endTurn(DOT_O)) break;
            }
        }
    }

}
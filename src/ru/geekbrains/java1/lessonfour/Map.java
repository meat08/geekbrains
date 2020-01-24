package ru.geekbrains.java1.lessonfour;

public class Map {
    private static final char DOT_EMPTY = '•';
    public static int SIZE = 3;
    private static char[][] map;

    public Map() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public void getMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setMap(int x, int y, char dot) {
        map[y][x] = dot;
    }

    public boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return true;
        return map[y][x] != DOT_EMPTY;
    }

    public boolean checkWin(char symbol) {
        for (int i = 0; i < SIZE; i++)
            if ((map[i][0] == symbol && map[i][1] == symbol && map[i][2] == symbol) ||
                    (map[0][i] == symbol && map[1][i] == symbol && map[2][i] == symbol))
                return true;
            return (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol) ||
                (map[2][0] == symbol && map[1][1] == symbol && map[0][2] == symbol);
    }
    //Методы getAiX и getAiY получают координату, чтобы перекрыть победный ход игроку (задание 4)
    public int getAiX(char symbolHum, char symbolAi) {
        for (int i = 0; i < SIZE; i++) {
            if (map[i][1] == symbolHum && map[i][2] == symbolHum && map[i][0] != symbolAi) return 0;
            else if (map[i][0] == symbolHum && map[i][2] == symbolHum && map[i][1] != symbolAi) return 1;
            else if (map[i][0] == symbolHum && map[i][1] == symbolHum && map[i][2] != symbolAi) return 2;

            else if (map[1][i] == symbolHum && map[2][i] == symbolHum && map[0][i] != symbolAi) return i;
            else if (map[0][i] == symbolHum && map[2][i] == symbolHum && map[1][i] != symbolAi) return i;
            else if (map[0][i] == symbolHum && map[1][i] == symbolHum && map[2][i] != symbolAi) return i;
        }
        if (map[1][1] == symbolHum && map[2][2] == symbolHum && map[0][0] != symbolAi) return 0;
        else if (map[0][0] == symbolHum && map[2][2] == symbolHum && map[1][1] != symbolAi) return 1;
        else if (map[0][0] == symbolHum && map[1][1] == symbolHum && map[2][2] != symbolAi) return 2;

        else if (map[0][2] == symbolHum && map[1][1] == symbolHum  && map[2][0] != symbolAi) return 0;
        else if (map[0][2] == symbolHum && map[2][0] == symbolHum  && map[1][1] != symbolAi) return 1;
        else if (map[1][1] == symbolHum && map[2][0] == symbolHum  && map[0][2] != symbolAi) return 2;
        return 3;
    }

    public int getAiY(char symbolHum, char symbolAi) {
        for (int i = 0; i < SIZE; i++){
            if (map[i][1] == symbolHum && map[i][2] == symbolHum && map[i][0] != symbolAi) return i;
            else if (map[i][0] == symbolHum && map[i][2] == symbolHum && map[i][1] != symbolAi) return i;
            else if (map[i][0] == symbolHum && map[i][1] == symbolHum && map[i][2] != symbolAi) return i;

            else if (map[1][i] == symbolHum && map[2][i] == symbolHum && map[0][i] != symbolAi) return 0;
            else if (map[0][i] == symbolHum && map[2][i] == symbolHum && map[1][i] != symbolAi) return 1;
            else if (map[0][i] == symbolHum && map[1][i] == symbolHum && map[2][i] != symbolAi) return 2;
        }
        if (map[1][1] == symbolHum && map[2][2] == symbolHum && map[0][0] != symbolAi) return 0;
        else if (map[0][0] == symbolHum && map[2][2] == symbolHum && map[1][1] != symbolAi) return 1;
        else if (map[0][0] == symbolHum && map[1][1] == symbolHum && map[2][2] != symbolAi) return 2;

        else if (map[0][2] == symbolHum && map[1][1] == symbolHum  && map[2][0] != symbolAi) return 2;
        else if (map[0][2] == symbolHum && map[2][0] == symbolHum  && map[1][1] != symbolAi) return 1;
        else if (map[1][1] == symbolHum && map[2][0] == symbolHum  && map[0][2] != symbolAi) return 0;
        return 3;
    }

    public boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }




}
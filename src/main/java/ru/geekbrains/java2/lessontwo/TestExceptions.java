package ru.geekbrains.java2.lessontwo;

public class TestExceptions {
    public static void main(String[] args) {

        //Проверка исключений выполняется заменой входного массива
        String[][] arr = new String[][]{{"1", "2", "3", "4"}, {"5", "6", "7", "8"},
                {"9", "a", "11", "b"}, {"13", "14", "15", "16"}};
/*
        String[][] arr = new String[][]{{"1", "2", "3", "4"}, {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}, {"13", "14", "15", "16"}};
*/
//        String[][] arr = new String[4][5];

        try {
            System.out.println("Сумма элементов массива = " + myExceptions(arr));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int myExceptions(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4) throw new MyArraySizeException();
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].length != 4) throw new MyArraySizeException();
        }
        int sum = 0;
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[x].length; y++) {
                if(!isInteger(arr[x][y])) throw new MyArrayDataException(x, y);
                else sum += Integer.parseInt(arr[x][y]);
            }
        }
        return sum;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}

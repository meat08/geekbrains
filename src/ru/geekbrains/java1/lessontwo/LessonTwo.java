package ru.geekbrains.java1.lessontwo;

import java.util.Arrays;

public class LessonTwo {

    public static void main(String[] args) {

        //Задание 1

        int[] arrOne = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Задание 1, массив на входе: " + Arrays.toString(arrOne));
        for(int i=0; i < arrOne.length; i++) {
            //Тут для подстраховки можно было добавить else if с условием, но в данном случае это избыточно
            if(arrOne[i] == 0) {
                arrOne[i] = 1;
            } else arrOne[i] = 0;
        }
        System.out.println("Массив на выходе: " + Arrays.toString(arrOne));

        //Задание 2

        int[] arrTwo = new int[8];
        //Для упрощения засунул в for сразу 2 переменные
        for(int i= 0, j = 0; i < arrTwo.length; i++, j += 3){
            arrTwo[i] = j;
        }
        System.out.println("\nЗадание 2, массив: " + Arrays.toString(arrTwo));

        //Задание 3

        int[] arrThree = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("\nЗадание 3, массив на входе: " + Arrays.toString(arrThree));
        for(int i = 0; i < arrThree.length; i++) {
            if(arrThree[i] < 6) arrThree[i] *= 2;
        }
        System.out.println("Массив на выходе: " + Arrays.toString(arrThree));

        //Задание 4

        int[][] arrFour = new int[5][5];
        //Т.к. диагональные элементы с равнозначными индексами
        //просто используем один счетчик для индекса строки и столбца
        for(int i = 0; i < arrFour.length; i++) arrFour[i][i] = 1;

        //Можно заполнить диагональ и в другую сторону
        //Но тут потребуются уже 2 счетчика
//        for(int i = 0, j = (arrFour.length - 1); i < arrFour.length; i++, j--) {
//            arrFour[i][j] = 1;
//        }
        System.out.println("\nЗадание 4, массив:");
        //System.out.println("Массив на выходе: " + Arrays.deepToString(arrFour));
        //Более наглядный вид
        for (int[] ints : arrFour) {
            for (int j = 0; j < arrFour.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }

        //Задание 5

        int[] arrFive = {12, 22, -10, 2, 5, -12, 30, 99, 1, 0, -1};
        //Переменным valMin и valMax сразу присваиваем значения нулевого элемента
        //для дальнейшего сравнения.
        int valMin = arrFive[0];
        int valMax = arrFive[0];
        int indexMax = 0;
        int indexMin = 0;
        //В цикле сравниваем поочередно каждый элемент с предыдущим
        // и если он больше или меньше - перезаписываем переменную.
        for (int i = 0; i < arrFive.length; i++) {
            if (arrFive[i] > valMax) {
                valMax = arrFive[i];
                indexMax = i;
            } else if (arrFive[i] < valMin) {
                valMin = arrFive[i];
                indexMin = i;
            }
        }
        System.out.println("\nЗадание 5. В массиве " + Arrays.toString(arrFive));
        System.out.println("Минимальное значение: " + valMin + " с индексом: " + indexMin);
        System.out.println("Максимальное значение: " + valMax + " с индексом: " + indexMax);

        //Можно сделать еще проще, но это уже похоже на читерство :)
//        Arrays.sort(arrFive);
//        System.out.println("Минимальное значение: " + arrFive[0] + " с индексом: 0");
//        System.out.println("Максимальное значение: " + arrFive[arrFive.length - 1] + " с индексом: " + (arrFive.length - 1));

        //Задание 6

        int[] arrSix = {1, 2, 5, 2, 10};
        System.out.println("\nЗадание 6.\nРезультат анализа массива - " + arrBalance(arrSix));

        //Задание 7

        int [] arrSeven = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("\nЗадание 7. Массив сдвинут " + Arrays.toString(sortArr(arrSeven, -3)));


    }

    private static boolean arrBalance(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            //При каждой интерации цикла обнуляем переменные
            int leftSum = 0;
            int rightSum = 0;

            //Считаем сумму элементов с 0 по i
            for (int j = 0; j <= i; j++) {
                leftSum += arr[j];
            }
            //Считаем сумму элементов с i до последнего.
            //i+1 добавил, чтобы первый проход по корневому циклу не считал все элементы
            for (int j = i+1; j < arr.length; j++) {
                rightSum += arr[j];
            }
            if(leftSum == rightSum) return true;
        }
        return false;
    }

    private static int[] sortArr(int[] arr, int n) {
        //Если n = 0 - то просто вернется исходный массив
        //Если n положительное - двигаем массив вправо
        while(n > 0) {
            //Объявляем переменную и пишем в нее последний элемент массива
            int tmpVal = arr[arr.length - 1];
            //В цикле перебираем все переменные справа налево
            //и сдвигаем их по очереди на 1 позицию
            for (int i = (arr.length - 1); i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            //пишем в нулевой элемент последний
            arr[0] = tmpVal;
            //Уменьшаем n на 1
            n--;
        }
        //Если n отрицательное - двигаем массив влево
        while(n < 0) {
            //Объявляем переменную и пишем в нее первый элемент массива
            int tempVal = arr[0];
            //В цикле перебираем все переменные слева направо
            //и сдвигаем их по очереди на 1 позицию
            for(int i = 0; i < (arr.length - 1); i++) {
                arr[i] = arr[i+1];
            }
            //Пишем в последний элемент нулевой
            arr[arr.length -1] = tempVal;
            //Увеличиваем n на 1
            n++;
        }
        return arr;
    }
}

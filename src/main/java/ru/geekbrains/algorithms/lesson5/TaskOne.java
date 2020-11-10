package ru.geekbrains.algorithms.lesson5;

public class TaskOne {
    public static void main(String[] args) {
        System.out.println(exponentiation(2, 2));
    }

    public static int exponentiation(int n, int degree) {
        if (degree == 1) {
            return n;
        }
        return n * exponentiation(n, degree - 1);
    }
}

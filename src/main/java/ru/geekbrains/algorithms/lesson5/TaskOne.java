package ru.geekbrains.algorithms.lesson5;

public class TaskOne {
    public static void main(String[] args) {
        System.out.println(exponentiation(2, -3));
        System.out.println(exponentiation(3, 3));
        System.out.println(exponentiation(8, 6));
        System.out.println(exponentiation(128, 0));
    }

    public static double exponentiation(double n, int degree) {
        if (n == 0) {
            throw new IllegalArgumentException("n cannot be 0");
        }
        if (degree > 0) {
            return n * exponentiation(n, degree - 1);
        }
        if (degree < 0) {
            return 1 / (n * exponentiation(n, -degree - 1));
        }
        else {
            return 1;
        }
    }
}

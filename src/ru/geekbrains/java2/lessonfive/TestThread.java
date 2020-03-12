package ru.geekbrains.java2.lessonfive;

import java.util.Arrays;

public class TestThread {
    private static final int size = 10000000;
    private static final int h = size / 2;
    private static float[] arr = new float[size];

    public static void main(String[] args) {
        calculateWithoutThread();
        try {
            calculateWithThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void calculateArr(float[] arr, int offset) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (i + offset) / 5) * Math.cos(0.2f + (i + offset) / 5) * Math.cos(0.4f + (i + offset) / 2));
        }
    }

    private static void calculateWithoutThread() {
        Arrays.fill(arr, 1);
        System.out.println("Процесс подсчета запущен (без потоков).");
        long startTime = System.currentTimeMillis();
        calculateArr(arr, 0);
        long endTime = System.currentTimeMillis();
        System.out.println("Время, затраченное на задачу без потоков: " + (endTime - startTime));
    }

    private static void calculateWithThread() throws InterruptedException {
        Arrays.fill(arr, 1);
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.out.println("Процесс подсчета запущен (с потоками).");
        long startTime = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread thread1 = new Thread(() -> calculateArr(a1, 0));
        Thread thread2 = new Thread(() -> calculateArr(a2, h));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        long endTime = System.currentTimeMillis();
        System.out.println("Время, затраченное на задачу с потоками: " + (endTime - startTime));
    }
}

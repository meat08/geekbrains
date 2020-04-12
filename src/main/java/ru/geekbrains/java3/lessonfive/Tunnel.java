package ru.geekbrains.java3.lessonfive;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private static Semaphore semaphore;

    public Tunnel(int CARS_COUNT) {
        semaphore = new Semaphore(CARS_COUNT / 2, true);
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            if(semaphore.availablePermits() == 0) {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            }
            semaphore.acquire();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(c.getName() + " закончил этап: " + description);
            semaphore.release();
        }
    }
}
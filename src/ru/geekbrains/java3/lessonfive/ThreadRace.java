package ru.geekbrains.java3.lessonfive;

import java.util.concurrent.CountDownLatch;

public class ThreadRace {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        CountDownLatch latchCars = new CountDownLatch(CARS_COUNT);
        CountDownLatch latchRaces = new CountDownLatch(race.getStages().size() * CARS_COUNT);
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(latchCars, latchRaces, race, 20 + (int) (Math.random() * 10));
        }
        for (Car car : cars) {
            new Thread(car).start();
        }
        try {
            latchCars.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            latchRaces.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

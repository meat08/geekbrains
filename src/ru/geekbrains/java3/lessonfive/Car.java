package ru.geekbrains.java3.lessonfive;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT = 0;
    private static AtomicInteger CARS_PLACE = new AtomicInteger(0);
    private CountDownLatch latchCars;
    private CountDownLatch latchRaces;
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(CountDownLatch latchCars, CountDownLatch latchRaces, Race race, int speed) {
        this.latchCars = latchCars;
        this.latchRaces = latchRaces;
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            latchCars.countDown();
            System.out.println(this.name + " готов");
            latchCars.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this, latchRaces);
            }
            CARS_PLACE.getAndAdd(1);
            if(CARS_PLACE.get() == 1) System.out.println(this.name + " WIN");
            else System.out.printf("%s занял %d место%n", this.name, CARS_PLACE.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
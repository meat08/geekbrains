package ru.geekbrains.java1.lessonsix;

public class Dog extends Animals {
    private String result;
    private static int countDog;
    private int dogRunDistance;
    private int dogSwimDistance;
    private double dogJumpDistance;

    public Dog(String name) {
        super(name);
        this.dogRunDistance = random.nextInt(801);
        this.dogSwimDistance = random.nextInt(21);
        this.dogJumpDistance = random.nextInt(3) + random.nextFloat();
        countDog++;
    }

    public static int getCountDog() { return countDog; }

    @Override
    public void run(int distance) {
        result = (distance <= dogRunDistance) ? " пробежал дистанцию успешно" : " побежал и свалился у финиша";
        System.out.println(super.getName() + result);
    }

    @Override
    public void swim(int distance) {
        result = (distance <= dogSwimDistance) ? " проплыл дистанцию успешно" : " пошел ко дну";
        System.out.println(super.getName() + result);
    }

    @Override
    public void jump(double height) {
        result = (height <= dogJumpDistance) ? " перепрыгнул препятствие" : " не долетел";
        System.out.println(super.getName() + result);
    }
}

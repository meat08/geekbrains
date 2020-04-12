package ru.geekbrains.java1.lessonsix;

public class Cat extends Animals {
    private String result;
    private static int countCat;
    private int catRunDistance;
    private double catJumpDistance;

    public Cat(String name) {
        super(name);
        this.catRunDistance = random.nextInt(301);
        this.catJumpDistance = random.nextInt(4) + random.nextFloat();
        countCat++;
    }

    public static int getCountCat() { return countCat; }

    @Override
    public void run(int distance) {
        result = (distance <= catRunDistance) ? " пробежал дистанцию успешно" : " побежал и свалился у финиша";
        System.out.println(super.getName() + result);
    }

    @Override
    public void swim(int distance) {
        System.out.println(super.getName() + " презрительно посмотрел на Вас и даже не полез в воду.");
    }

    @Override
    public void jump(double height) {
        result = (height <= catJumpDistance) ? " перепрыгнул препятствие" : " не смог допрыгнуть (и не пытался, это же кот)";
        System.out.println(super.getName() + result);
    }
}

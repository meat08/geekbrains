package ru.geekbrains.java1.lessonsix;

public class Main {
    public static void main(String[] args) {

        Dog bobik = new Dog("Бобик");
        Cat barsik = new Cat("Барсик");
        Dog killer = new Dog("Киллер");
        Cat tom = new Cat("Том");
        Dog dogmeat = new Dog("Псина");
        bobik.run(800);
        bobik.swim(30);
        bobik.jump(0.2);
        barsik.run(20);
        barsik.swim(2);
        barsik.jump(1);
        killer.run(900);
        killer.swim(10);
        killer.jump(2);
        tom.run(3);
        tom.jump(1);
        dogmeat.run(100);
        dogmeat.swim(12);
        dogmeat.jump(1.5);

        System.out.println();
        System.out.println("Всего поучаствовало животных: " + Animals.getCount());
        System.out.println("Из них собак: " + Dog.getCountDog());
        System.out.println("Из них кошек: " + Cat.getCountCat());
    }
}

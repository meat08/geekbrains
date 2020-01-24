package ru.geekbrains.lessonseven;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cat> catsArr = new ArrayList<>();
        catsArr.add(new Cat("Барсик", 15));
        catsArr.add(new Cat("Мурзик", 20));
        catsArr.add(new Cat("Борис", 18));
        catsArr.add(new Cat("Леонид", 9));
        Plate plate = new Plate(60);

        for(Cat cat : catsArr) cat.eat(plate);
        for(Cat cat : catsArr) System.out.println("Кот " + cat.getName() +
                (cat.getSatiety() ? " наелся." : " остался голоден."));
        plate.info();
        System.out.println();

        //Мы добрые, поэтому докормим голодных котов
        for(Cat cat : catsArr) {
            if(!cat.getSatiety()) {
                System.out.println(cat.getName() + " все еще голоден, покормим его.");
                plate.addFood((cat.getAppetite() - plate.getFood()));
                cat.eat(plate);
                System.out.println("Кот " + cat.getName() + " наелся.");
            }
        }

    }
}

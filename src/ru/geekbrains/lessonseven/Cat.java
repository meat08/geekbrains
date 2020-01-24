package ru.geekbrains.lessonseven;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public void eat(Plate p) {
        if(p.decreaseFood(appetite) & !satiety) this.satiety = true;
    }

    public boolean getSatiety() { return satiety; }
    public String getName() { return name; }
    public int getAppetite() { return appetite; }

}

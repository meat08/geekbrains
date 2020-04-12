package ru.geekbrains.java1.lessonseven;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        if(food < n) return false;
        else {
            this.food -= n;
            return true;
        }
    }

    public void addFood(int food) {
        this.food += food;
    }

    public int getFood() { return food; }
    public void info() {
        System.out.println((food > 0) ? "\nВ тарелке осталось: " + food + " еды." : "\nВ тарелке пусто.");
    }
}

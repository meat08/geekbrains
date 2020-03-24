package ru.geekbrains.java3.lessonone.fruits;

public abstract class Fruit {
    private float weight;

    public Fruit(float weight) {
        this.weight = weight;
    }

    public float getFruitWeight() {
        return weight;
    }
}

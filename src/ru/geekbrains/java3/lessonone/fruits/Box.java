package ru.geekbrains.java3.lessonone.fruits;

import java.util.ArrayList;

public class Box <T extends Fruit> {

    private ArrayList<T> fruitList = new ArrayList<>();

    public void addFruitToBox(T fruit) {
        fruitList.add(fruit);
    }

    public void moveFruitToAnotherBox(Box<T> box) {
        if(this == box) return;
        box.fruitList.addAll(this.fruitList);
        this.fruitList.clear();
    }

    public boolean compareBox (Box<?> box) {
        return this.getBoxWeight() == box.getBoxWeight();
    }

    private float getBoxWeight() {
        float fruitWeight = 0.0f;
        for (Fruit fruit : fruitList) {
            fruitWeight += fruit.getFruitWeight();
        }
        return fruitWeight;
    }

}

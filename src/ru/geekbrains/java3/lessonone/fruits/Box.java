package ru.geekbrains.java3.lessonone.fruits;

import java.util.ArrayList;

public class Box<T> {

    private ArrayList<Fruit> fruitList = new ArrayList<>();

    public void addFruitToBox(T fruit) {
        fruitList.add((Fruit) fruit);
    }

    public void moveFruitToAnotherBox(Box<T> box) {
        if(this == box) return;
        box.getFruitList().addAll(this.getFruitList());
        this.clearFruitList();
    }

    public boolean compareBox (Box<?> box) {
        return this.getBoxWeight() == box.getBoxWeight();
    }

    private ArrayList<Fruit> getFruitList() {
        return fruitList;
    }

    private void clearFruitList() {
        fruitList.clear();
    }

    private float getBoxWeight() {
        float fruitWeight = 0.0f;
        for (Fruit fruit : fruitList) {
            fruitWeight += fruit.getFruitWeight();
        }
        return fruitWeight;
    }

}

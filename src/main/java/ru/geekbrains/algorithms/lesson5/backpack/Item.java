package ru.geekbrains.algorithms.lesson5.backpack;

public class Item {
    private final int weight;
    private final int price;

    public Item(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item weight: " + weight
                + ", price: " + price;
    }
}

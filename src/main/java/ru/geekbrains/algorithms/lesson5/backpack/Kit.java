package ru.geekbrains.algorithms.lesson5.backpack;

import java.util.List;

public class Kit {
    private final List<Item> items;
    private final int sumPrice;
    private final int sumWeight;

    public Kit(List<Item> items, int sumPrice, int sumWeight) {
        this.items = items;
        this.sumPrice = sumPrice;
        this.sumWeight = sumWeight;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public int getSumWeight() {
        return sumWeight;
    }
}

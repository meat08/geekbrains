package ru.geekbrains.algorithms.lesson5.backpack;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Backpack {
    private final int size;
    private List<Item> items;
    private final List<Kit> itemPack = new LinkedList<>();
    private int backpackWeight;
    private int backpackItemsPrice;

    public Backpack(List<Item> items, int size) {
        this.items = items;
        this.size = size;
    }

    public void calculateItemsForBackpack() {
        for (int i = 0; i <= items.size(); i++) {
            calculateItems(items, 0, i);
        }
        calculateItemsMaxPrice();
    }

    private void calculateItems(List<Item> itemsList, int index, int limit) {
        List<Item> tempItems = new ArrayList<>();
        int sumWeight = 0, sumPrice = 0;
        if(index >= limit) {
            for (int i = 0; i < limit; i++) {
                sumWeight += itemsList.get(i).getWeight();
                sumPrice += itemsList.get(i).getPrice();
                tempItems.add(itemsList.get(i));
            }
            if (sumWeight <= size) {
                itemPack.add(new Kit(tempItems, sumPrice, sumWeight));
            }
            return;
        }

        for(int i = index; i < items.size(); i++) {
            Item temp = itemsList.get(index);
            itemsList.set(index, itemsList.get(i));
            itemsList.set(i, temp);

            calculateItems(itemsList, index+1, limit);
        }
    }

    private void calculateItemsMaxPrice() {
        int maxPrice = 0;
        Kit kitMaxPrice = null;
        for (Kit kit : itemPack) {
            if (kit.getSumPrice() > maxPrice) {
                maxPrice = kit.getSumPrice();
                kitMaxPrice = kit;
            }
        }
        if (kitMaxPrice != null) {
            items = kitMaxPrice.getItems();
            backpackWeight = kitMaxPrice.getSumWeight();
            backpackItemsPrice = kitMaxPrice.getSumPrice();
        }  else {
            items = null;
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public int getBackpackWeight() {
        return backpackWeight;
    }

    public int getBackpackItemsPrice() {
        return backpackItemsPrice;
    }
}

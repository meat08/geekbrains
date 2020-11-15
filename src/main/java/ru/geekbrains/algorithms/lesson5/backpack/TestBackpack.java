package ru.geekbrains.algorithms.lesson5.backpack;

import java.util.ArrayList;
import java.util.List;

public class TestBackpack {

    public static void main(String[] args) {
        int backpackSize = 20;

        Item item1 = new Item(10, 12);
        Item item2 = new Item(13, 2);
        Item item3 = new Item(5, 23);
        Item item4 = new Item(2, 11);
        Item item5 = new Item(16, 20);
        Item item6 = new Item(19, 80);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
//        items.add(item6);

        Backpack backpack = new Backpack(items, backpackSize);
        backpack.calculateItemsForBackpack();

        System.out.printf("Backpack weight: %d.%nCost item in backpack: %d.%n",
                backpack.getBackpackWeight(), backpack.getBackpackItemsPrice());
        System.out.println("Items:");
        System.out.println("--------------------");
        if (backpack.getItems() != null) {
            for (Item item : backpack.getItems()) {
                System.out.println(item.toString());
            }
        }
        System.out.println("--------------------");




    }
}

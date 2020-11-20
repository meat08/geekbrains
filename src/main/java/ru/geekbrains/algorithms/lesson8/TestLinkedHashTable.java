package ru.geekbrains.algorithms.lesson8;

import ru.geekbrains.algorithms.base.myhashtable.HashTable;
import ru.geekbrains.algorithms.base.myhashtable.LinkedHashTableImpl;

public class TestLinkedHashTable {
    public static void main(String[] args) {
        HashTable<Product, Integer> hashTable = new LinkedHashTableImpl<>(5);

        hashTable.put(new Product(1, "Orange"), 150);
        hashTable.put(new Product(77, "Banana"), 100);
        hashTable.put(new Product(77, "Carrot"), 228);
        hashTable.put(new Product(60, "Lemon"), 250);
        hashTable.put(new Product(52, "Milk"), 120);
        hashTable.put(new Product(21, "Potato"), 67);

        System.out.println("Size is " + hashTable.size());
        hashTable.display();

        System.out.println("Cost potato is " + hashTable.get(new Product(21, "Potato")));
        System.out.println("Cost banana is " + hashTable.get(new Product(77, "Banana")));

        hashTable.remove(new Product(21, "Potato"));
        hashTable.remove(new Product(77, "Banana"));

        System.out.println("Cost potato is " + hashTable.get(new Product(21, "Potato")));
        System.out.println("Cost banana is " + hashTable.get(new Product(77, "Banana")));

        hashTable.display();
    }
}

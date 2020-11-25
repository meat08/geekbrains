package ru.geekbrains.algorithms.base.myhashtable;

public class LinkedHashTableImpl<K, V> extends HashTableImpl<K, V> {

    public LinkedHashTableImpl(int maxSize) {
        super(maxSize);
    }

    @Override
    public boolean put(K key, V value) {
        int index = hash(key);
        if (index >= maxSize) {
            return false;
        }
        if (data[index] == null) {
            data[index] = new Node<>(key, value);
            size++;
            return true;
        }
        Node<K, V> node = data[index];
        while (node.getNext() != null) {
            if (node.getKey().equals(key)) {
                node.setValue(value);
                return true;
            }
            node = node.getNext();
        }
        node.setNext(new Node<>(key, value));
        size++;
        return true;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        if (data[index] == null) {
            return null;
        }
        Node<K, V> node = data[index];
        while (node != null && !node.getKey().equals(key)) {
            node = node.getNext();
        }
        if (node == null) {
            return null;
        }
        return node.getValue();
    }

    @Override
    public V remove(K key) {
        int index = hash(key);
        if (data[index] == null) {
            return null;
        }
        Node<K, V> current = data[index];
        Node<K, V> previous = null;
        while (current != null) {
            if (current.getKey().equals(key)) {
                if (previous == null) {
                    data[index] = current.getNext();
                    if (current.getNext() == null) {
                        size--;
                    }
                } else {
                    previous.setNext(current.getNext());
                    size--;
                }
                return current.getValue();
            }
            previous = current;
            current = current.getNext();
        }
        return null;
    }

    @Override
    public void display() {
        System.out.println("----------");
        for (int i = 0; i < data.length; i++) {
            System.out.print(i + " = ");
            Node<K, V> current = data[i];
            while (current != null) {
                System.out.print(current + (current.getNext() != null ? ", " : ""));
                current = current.getNext();
            }
            System.out.println();
        }
        System.out.println("----------");
    }
}

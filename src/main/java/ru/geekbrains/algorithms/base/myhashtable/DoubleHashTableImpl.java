package ru.geekbrains.algorithms.base.myhashtable;

public class DoubleHashTableImpl<K, V> extends HashTableImpl<K, V> {

    private static final int DOUBLE_HASH_CONST = 5;

    public DoubleHashTableImpl(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int getStep(K key) {
        return doubleHash(key);
    }

    private int doubleHash(K key) {
        return DOUBLE_HASH_CONST - (key.hashCode() % DOUBLE_HASH_CONST);
    }
}

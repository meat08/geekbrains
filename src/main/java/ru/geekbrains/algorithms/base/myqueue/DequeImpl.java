package ru.geekbrains.algorithms.base.myqueue;

public class DequeImpl<E> extends QueueImpl<E> implements Deque<E> {

    public DequeImpl(int maxSize) {
        super(maxSize);
    }

    @Override
    public boolean insertLeft(E value) {
        if (isFull()) {
            return false;
        }
        if (head == DEFAULT_HEAD) {
            head = data.length;
        }
        data[--head] = value;
        size++;
        return true;
    }

    @Override
    public boolean insertRight(E value) {
        return super.insert(value);
    }

    @Override
    public E removeLeft() {
        return super.remove();
    }

    @Override
    public E removeRight() {
        if (isEmpty()) {
            return null;
        }
        if (tail == DEFAULT_TAIL) {
            tail = data.length - 1;
        }
        size--;
        return data[tail--];
    }

    @Override
    public E peekFirst() {
        return super.peekHead();
    }

    @Override
    public E peekLast() {
        return data[tail];
    }
}

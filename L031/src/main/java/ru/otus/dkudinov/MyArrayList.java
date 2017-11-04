package ru.otus.dkudinov;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<E> extends AbstractList<E> {
    private int size;
    private Object[] data;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_SIZE = Integer.MAX_VALUE - 10;

    public MyArrayList(int capacity) {
        data = new Object[capacity];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public E get(int index) {
        return (E) data[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        ensureCapacity(size + 1);
        data[size++] = e;
        return true;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity < data.length) {
            return;
        }

        if (minCapacity >= MAX_SIZE) {
            throw new OutOfMemoryError();
        }

        int newCapacity;

        if (minCapacity + (minCapacity >> 1) >= MAX_SIZE) {
            newCapacity = MAX_SIZE;
        } else {
            newCapacity = minCapacity + (minCapacity >> 1);
        }

        data = Arrays.copyOf(data, newCapacity);
    }


}

package ru.otus.dkudinov;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.*;

/**
 * Class MyArrayList implements only those methods which is using in methods
 * {@link Collections#addAll},
 * {@link Collections#copy},
 * {@link Collections#sort}
 */

public class MyArrayList<E> extends AbstractList<E> {
    private int size;
    private E[] data;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_SIZE = Integer.MAX_VALUE - 10;

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public E get(int index) {
        return data[index];
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

    @Override
    public E set(int index, E element)
    {
        E prev = data[index];
        data[index] = element;
        return prev;
    }

    @Override
    public ListIterator<E> listIterator()
    {
        return new ListItr();
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

    private class ListItr implements ListIterator<E>
    {
        int cursor = 0;
        int lastReturned = -1;

        @Override
        public boolean hasNext()
        {
            return cursor != size;
        }

        @Override
        public E next()
        {
            if (cursor == size) {
                throw new NoSuchElementException();
            }

            lastReturned = cursor;
            return data[cursor++];
        }

        @Override
        public void set(E e)
        {
            if (lastReturned == -1) {
                throw new IllegalStateException();
            }

            MyArrayList.this.set(lastReturned, e);
        }

        @Override
        public boolean hasPrevious()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E p1)
        {
            throw new UnsupportedOperationException();
        }
    }
}

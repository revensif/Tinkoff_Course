package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {
    private int index;
    private final List<T> list;

    public BackwardIterator(List<T> list) {
        this.list = list;
        index = list.size();
    }

    @Override
    public boolean hasNext() {
        return index != 0;
    }

    @Override
    public T next() {
        return list.get(--index);
    }
}

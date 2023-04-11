package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.Iterator;

public class 窥探迭代器 implements Iterator<Integer> {

    Iterator<Integer> iterator;
    Integer top;

    public 窥探迭代器(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        if (iterator.hasNext()) {
            top = iterator.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return top;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer result = top;
        if (iterator.hasNext()) {
            top = iterator.next();
        } else {
            top = null;
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return top != null;
    }
}

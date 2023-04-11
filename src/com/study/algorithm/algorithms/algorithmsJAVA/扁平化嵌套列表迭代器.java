package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class 扁平化嵌套列表迭代器 implements Iterator<Integer> {

    //DFS
    //深度优先搜索，先把嵌套列表转换成普通列表，再从普通列表中取值
    //也可以使用“栈”代替DFS的递归
    private List<Integer> nestedIntegers;
    private int           next = 0;

    public 扁平化嵌套列表迭代器(List<NestedInteger> nestedList) {
        nestedIntegers = new ArrayList<>();
        initList(nestedList);
    }

    private void initList(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                nestedIntegers.add(nestedInteger.getInteger());
            } else {
                initList(nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return nestedIntegers.get(next++);
    }

    @Override
    public boolean hasNext() {
        return next != nestedIntegers.size();
    }


    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        //Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        //Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
